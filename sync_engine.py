# -*- coding: utf-8 -*-
"""
窗口鼠标同步引擎 (headless) — 由 DLDL-Proxy 主进程 (electron-main.js) 拉起。

来源: 移植自 tongbuqi/window_sync.py 的 SyncEngine (v12.32 纯 PostMessage 版),
去掉 Tkinter GUI, 改为 stdin/stdout 的行分隔 JSON 协议, 供 Electron 主进程调用。
同步核心逻辑 (钩子捕获主控鼠标 → PostMessage 投递到各同步窗口的
Chrome_RenderWidgetHostHWND 渲染窗口) 与原同步器完全一致, 未做行为改动。

dldl 的每个游戏窗口 = 一个独立 Electron 子进程 (类名 Chrome_WidgetWin_1),
主进程持有其 PID, 故 enumerate 可按 PID 精准识别多开的斗罗窗口。

── 协议 (每行一个 JSON) ──
electron → python (stdin):
  {"cmd":"ping"}
  {"cmd":"enumerate","pids":[123,456]}            # pids 可省略=枚举全部可同步窗口
  {"cmd":"highlight","hwnd":123,"times":3}         # 主控窗口红框高亮
  {"cmd":"start","master":123,"targets":[456,789],"mode":"web"}
  {"cmd":"stop"}
  {"cmd":"status"}
  {"cmd":"window-op","action":"close|set_size|arrange|hide|show","hwnds":[...],"w":,"h":,"cols":,"gap":,"winW":,"winH":,"startX":,"startY":}
  {"cmd":"quit"}
python → electron (stdout):
  {"type":"ready"}
  {"type":"pong"}
  {"type":"windows","list":[{hwnd,pid,title,class,type,rect,is_dldl}]}
  {"type":"status","running":bool,"mode":str,"master":int,"targets":int}
  {"type":"ok","cmd":str}
  {"type":"window-op","action":str,"count":int,"failed":[int]}
  {"type":"error","msg":str}
"""

import ctypes
import ctypes.wintypes
import json
import sys
import time
import threading
import collections
import math

import win32gui
import win32con
import win32api
import win32clipboard

# ─── Win32 常量 ───
WM_MOUSEMOVE      = 0x0200
WM_LBUTTONDOWN    = 0x0201
WM_LBUTTONUP      = 0x0202
WM_LBUTTONDBLCLK  = 0x0203
WM_RBUTTONDOWN    = 0x0204
WM_RBUTTONUP      = 0x0205
WM_MBUTTONDOWN    = 0x0208
WM_MBUTTONUP      = 0x0209
WM_MOUSEWHEEL     = 0x020A

MK_LBUTTON = 0x0001
MK_RBUTTON = 0x0002
MK_MBUTTON = 0x0010

WH_MOUSE_LL = 14
WH_KEYBOARD_LL = 13
LLMHF_INJECTED = 0x00000001

# ─── 窗口管理常量 ───
WM_CLOSE      = 0x0010
SW_HIDE       = 0
SW_SHOWNORMAL = 1
SW_SHOW       = 5
SW_RESTORE    = 9
SWP_NOSIZE    = 0x0001
SWP_NOMOVE    = 0x0002
SWP_NOZORDER  = 0x0004
SWP_NOACTIVATE = 0x0010

# ─── 鼠标/键盘模拟常量 ───
MOUSEEVENTF_LEFTDOWN = 0x0002
MOUSEEVENTF_LEFTUP   = 0x0004
VK_CONTROL = 0x11
VK_C       = 0x43
VK_MENU    = 0x12   # Alt 键，用于释放前台锁


# ─── 结构体 ───
class POINT(ctypes.Structure):
    _fields_ = [("x", ctypes.c_long), ("y", ctypes.c_long)]


class MSLLHOOKSTRUCT(ctypes.Structure):
    _fields_ = [
        ("pt",          POINT),
        ("mouseData",   ctypes.c_ulong),
        ("flags",       ctypes.c_ulong),
        ("time",        ctypes.c_ulong),
        ("dwExtraInfo", ctypes.c_ulong),
    ]


# ─── 窗口识别 ───

SYNC_WINDOW_CLASSES = {
    "Chrome_WidgetWin_1": "Electron/Chrome",
    "Chrome_WidgetWin_0": "CEF/Chrome",
    "CefBrowserWindow": "CEF Browser",
}


def _emit(obj):
    """向 electron 主进程发送一行 JSON (stdout 只走协议, 调试信息一律走 stderr)."""
    try:
        sys.stdout.write(json.dumps(obj, ensure_ascii=False) + "\n")
        sys.stdout.flush()
    except Exception:
        pass


def _log(msg):
    try:
        sys.stderr.write("[sync_engine] " + str(msg) + "\n")
        sys.stderr.flush()
    except Exception:
        pass


def _get_pid(hwnd):
    """用 ctypes 取窗口所属进程 PID (跨 WOW64 正常)."""
    try:
        pid = ctypes.c_ulong()
        ctypes.windll.user32.GetWindowThreadProcessId(int(hwnd), ctypes.byref(pid))
        return pid.value
    except Exception:
        return None


def classify_window(hwnd):
    try:
        cn = win32gui.GetClassName(hwnd)
    except Exception:
        return "", ""
    if cn in SYNC_WINDOW_CLASSES:
        return SYNC_WINDOW_CLASSES[cn], cn
    if cn.startswith("Chrome_WidgetWin"):
        return "Electron/Chrome", cn
    return "", cn


def find_render_child(parent_hwnd):
    """找 Chrome_RenderWidgetHostHWND 渲染子窗口 (CEF/Chrome 真正的滚动/命中目标)."""
    result = None

    def enum_child(hwnd, _):
        nonlocal result
        try:
            cn = win32gui.GetClassName(hwnd)
        except Exception:
            return True
        if cn == "Chrome_RenderWidgetHostHWND":
            result = hwnd
            return False
        return True

    try:
        win32gui.EnumChildWindows(parent_hwnd, enum_child, None)
    except Exception:
        pass
    return result


def get_windows_by_pids(pids, native_pids=None):
    """枚举可见的、可同步的顶层窗口。

    - 传入 pids (dldl 各游戏窗口子进程 PID) 时: 返回 PID 命中的窗口 (即多开斗罗窗口);
      若一个都没命中 (极端情况), 回退返回全部可同步窗口, 避免面板空列表。
    - native_pids: 批量启动的「原生客户端」进程 PID。这些窗口的窗口类可能不是
      CEF/Chrome (classify_window 认不出), 故对命中 native_pids 的窗口放宽窗口类限制,
      即使 type_label 为空也列出 (标为 "原生客户端"), 否则会被过滤掉看不到。
    - 每个窗口都带 is_dldl 标记, 供面板默认勾选 dldl 窗口。
    """
    pid_set = set(int(p) for p in pids) if pids else None
    native_set = set(int(p) for p in native_pids) if native_pids else set()
    out = []

    def cb(hwnd, _):
        if not win32gui.IsWindowVisible(hwnd):
            return True
        title = win32gui.GetWindowText(hwnd)
        if not title:
            return True
        pid = _get_pid(hwnd)
        is_native = pid in native_set
        type_label, cn = classify_window(hwnd)
        if not type_label:
            # 非 CEF/Chrome 窗口: 仅当它是批量启动的原生客户端时才保留
            if not is_native:
                return True  # 只列可同步窗口
            type_label = "原生客户端"
        try:
            rect = win32gui.GetWindowRect(hwnd)
        except Exception:
            return True
        render = find_render_child(hwnd) or hwnd
        out.append({
            "hwnd": int(hwnd),
            "pid": pid,
            "title": title,
            "class": cn,
            "type": type_label,
            "rect": list(rect),
            "is_dldl": bool(pid_set is not None and pid in pid_set),
        })
        return True

    win32gui.EnumWindows(cb, None)

    if pid_set is not None:
        matched = [w for w in out if w["is_dldl"]]
        if matched:
            return matched
    return out


# ─── 窗口管理 (关闭/宽高/排列/隐藏/显示) ───

def _op_windows(hwnds, op_name, fn):
    """对一批 hwnd 执行同一窗口操作, 收集成功/失败。

    注意: 调用方 (electron 主进程) 只传入多开游戏窗口 hwnd,
    账号列表主窗口不会进入此列表, 故这些操作不会误关账号程序。
    """
    results = {"ok": [], "failed": []}
    for h in hwnds or []:
        h = int(h)
        if not win32gui.IsWindow(h):
            results["failed"].append(h)
            continue
        try:
            fn(h)
            results["ok"].append(h)
        except Exception as e:
            _log("[window-op] %s 失败 hwnd=%s: %s" % (op_name, h, e))
            results["failed"].append(h)
    return results


def _read_clipboard():
    """读取剪贴板 Unicode 文本，失败或非文本返回空字符串。"""
    try:
        win32clipboard.OpenClipboard()
        if win32clipboard.IsClipboardFormatAvailable(win32con.CF_UNICODETEXT):
            return win32clipboard.GetClipboardData(win32con.CF_UNICODETEXT) or ""
        return ""
    except Exception:
        return ""
    finally:
        try:
            win32clipboard.CloseClipboard()
        except Exception:
            pass


def _cmd_window_op(req):
    """统一处理窗口管理类指令, 返回 (action, count, failed)。"""
    hwnds = req.get("hwnds") or []

    action = req.get("action")
    if action == "close":
        native_pids = set(int(p) for p in (req.get("native_pids") or []))
        def _fn(h):
            pid = _get_pid(h)
            if pid and pid in native_pids:
                # 原生客户端（非 CEF/Electron）不响应 WM_CLOSE，只好 TerminateProcess 强制杀进程
                try:
                    hproc = win32api.OpenProcess(0x0001, False, pid)   # PROCESS_TERMINATE
                    win32api.TerminateProcess(hproc, 0)
                    win32api.CloseHandle(hproc)
                except Exception:
                    # TerminateProcess 失败兜底：发 WM_CLOSE（最起码有尝试）
                    win32gui.PostMessage(h, WM_CLOSE, 0, 0)
            else:
                win32gui.PostMessage(h, WM_CLOSE, 0, 0)
        r = _op_windows(hwnds, "close", _fn)
        return "close", len(r["ok"]), r["failed"]

    if action == "set_size":
        w = int(req.get("w") or 0)
        h = int(req.get("h") or 0)
        if w <= 0 or h <= 0:
            raise ValueError("宽高必须大于 0")
        def _fn(hw):
            win32gui.SetWindowPos(hw, 0, 0, 0, w, h, SWP_NOMOVE | SWP_NOZORDER)
        r = _op_windows(hwnds, "set_size", _fn)
        return "set_size", len(r["ok"]), r["failed"]

    if action == "arrange":
        n = len(hwnds)
        if n == 0:
            raise ValueError("没有可排列的窗口")
        cols = int(req.get("cols") or 0)
        gap = int(req.get("gap") if req.get("gap") is not None else 4)
        win_w = int(req.get("winW") or 0)
        win_h = int(req.get("winH") or 0)
        start_x = int(req.get("startX") or 0)
        start_y = int(req.get("startY") or 0)
        if cols <= 0:
            # 自动列数：按当前显示器工作区宽度尽量多放几列（先横向铺满再换行），
            # 而非套 ceil(sqrt(n)) 的正方形网格（那样 4 个窗口会每行只放 2 个，才出现"2 个就换行"）。
            cols = int(math.ceil(math.sqrt(n)))  # 兜底
            if win_w > 0 and (win_w + gap) > 0:
                try:
                    mon = win32api.MonitorFromPoint(
                        (start_x, start_y), win32con.MONITOR_DEFAULTTONEAREST)
                    info = win32api.GetMonitorInfo(mon)
                    work = info["Work"]  # (left, top, right, bottom)
                    avail_w = work[2] - work[0] - start_x
                    if avail_w > 0:
                        fit = int(avail_w // (win_w + gap))
                        if fit >= 1:
                            cols = fit
                except Exception:
                    pass
        # 默认宽高取第一个窗口当前尺寸
        if win_w <= 0 or win_h <= 0:
            try:
                r0 = win32gui.GetWindowRect(int(hwnds[0]))
                if win_w <= 0:
                    win_w = r0[2] - r0[0]
                if win_h <= 0:
                    win_h = r0[3] - r0[1]
            except Exception:
                pass
        ok, failed = [], []
        for i, h in enumerate(hwnds):
            h = int(h)
            if not win32gui.IsWindow(h):
                failed.append(h)
                continue
            col = i % cols
            row = i // cols
            x = start_x + col * (win_w + gap)
            y = start_y + row * (win_h + gap)
            try:
                win32gui.MoveWindow(h, x, y, win_w, win_h, True)
                ok.append(h)
            except Exception as e:
                _log("[window-op] arrange 失败 hwnd=%s: %s" % (h, e))
                failed.append(h)
        return "arrange", len(ok), failed

    if action == "hide":
        def _fn(h):
            win32gui.ShowWindow(h, SW_HIDE)
        r = _op_windows(hwnds, "hide", _fn)
        return "hide", len(r["ok"]), r["failed"]

    if action == "show":
        def _fn(h):
            win32gui.ShowWindow(h, SW_RESTORE)
            win32gui.ShowWindow(h, SW_SHOWNORMAL)
        r = _op_windows(hwnds, "show", _fn)
        return "show", len(r["ok"]), r["failed"]

    raise ValueError("未知窗口操作: %s" % action)


# ─── 边框高亮 (闪烁提示, 闪完自动消失) ───

def flash_border(hwnd, color_rgb=(255, 0, 0), times=3, interval=0.25):
    bw = 5
    for _ in range(int(times)):
        try:
            rect = win32gui.GetWindowRect(hwnd)
        except Exception:
            return
        hdc = win32gui.GetDC(0)
        if not hdc:
            return
        try:
            r, g, b = color_rgb
            x1, y1, x2, y2 = rect
            pen = win32gui.CreatePen(win32con.PS_SOLID, 1, win32api.RGB(r, g, b))
            win32gui.SelectObject(hdc, pen)
            brush = win32gui.CreateSolidBrush(win32api.RGB(r, g, b))
            win32gui.SelectObject(hdc, brush)
            win32gui.Rectangle(hdc, x1 - bw, y1 - bw, x2 + bw, y1)
            win32gui.Rectangle(hdc, x1 - bw, y2, x2 + bw, y2 + bw)
            win32gui.Rectangle(hdc, x1 - bw, y1, x1, y2)
            win32gui.Rectangle(hdc, x2, y1, x2 + bw, y2)
            pen2 = win32gui.CreatePen(win32con.PS_SOLID, 2, win32api.RGB(r, g, b))
            win32gui.SelectObject(hdc, pen2)
            win32gui.Rectangle(hdc, x1, y1, x2, y2)
            win32gui.DeleteObject(pen)
            win32gui.DeleteObject(pen2)
            win32gui.DeleteObject(brush)
        except Exception:
            pass
        finally:
            win32gui.ReleaseDC(0, hdc)
        time.sleep(interval)
        try:
            win32gui.InvalidateRect(hwnd, None, True)
            ctypes.windll.user32.RedrawWindow(None, None, None, 0x0085)
        except Exception:
            pass
        time.sleep(interval)


# ─── 同步引擎 (v12.32 纯 PostMessage — 与原同步器逐行一致) ───

class SyncEngine:
    def __init__(self):
        self.master_hwnd = None
        self.master_rect = None
        self.master_render_hwnd = None
        self.sync_targets = []
        self.running = False
        self.sync_mode = "web"   # dldl 多开窗口是网页/CEF, 默认 web
        self._hook_id = None
        self._kb_hook_id = None
        self._proc = None
        self._kb_proc = None
        self._raw_events = None
        self._worker_running = False
        self._injecting = False

    def set_mode(self, mode):
        if mode in ("native", "web"):
            self.sync_mode = mode

    def set_master(self, hwnd, rect, render_hwnd=None):
        self.master_hwnd = hwnd
        self.master_rect = rect
        self.master_render_hwnd = render_hwnd or hwnd

    def set_sync_targets(self, targets):
        self.sync_targets = targets

    def start(self):
        if self.running:
            return
        self.running = True
        threading.Thread(target=self._hook_loop, daemon=True).start()

    def stop(self):
        self.running = False
        self._worker_running = False
        if self._hook_id:
            try:
                ctypes.windll.user32.UnhookWindowsHookEx(self._hook_id)
            except Exception:
                pass
            self._hook_id = None
        if self._kb_hook_id:
            try:
                ctypes.windll.user32.UnhookWindowsHookEx(self._kb_hook_id)
            except Exception:
                pass
            self._kb_hook_id = None

    def _scale_point(self, sx, sy, master_rect, sync_rect):
        """坐标比例映射"""
        rel_x = sx - master_rect[0]
        rel_y = sy - master_rect[1]
        mw = master_rect[2] - master_rect[0]
        mh = master_rect[3] - master_rect[1]
        sw = sync_rect[2] - sync_rect[0]
        sh = sync_rect[3] - sync_rect[1]
        if mw <= 0 or mh <= 0:
            return sx, sy
        nx = int(sync_rect[0] + rel_x * (sw / mw))
        ny = int(sync_rect[1] + rel_y * (sh / mh))
        return nx, ny

    def _make_lparam(self, x, y):
        cx = max(0, min(x, 65535)) & 0xFFFF
        cy = max(0, min(y, 65535)) & 0xFFFF
        return (cy << 16) | cx

    def _screen_to_client(self, hwnd, sx, sy):
        pt = ctypes.wintypes.POINT()
        pt.x = sx
        pt.y = sy
        ctypes.windll.user32.ScreenToClient(hwnd, ctypes.byref(pt))
        return pt.x, pt.y

    def _find_child_at_point(self, top_hwnd, cx, cy):
        user32 = ctypes.windll.user32
        pt = ctypes.wintypes.POINT()
        pt.x = cx
        pt.y = cy
        # CWP_SKIPDISABLED | CWP_SKIPTRANSPARENT | CWP_SKIPINVISIBLE = 0x0007
        return user32.ChildWindowFromPointEx(top_hwnd, pt, 0x0007)

    def _worker_thread(self):
        """纯 PostMessage 滚轮 + PostMessageA 点击 (与原同步器 v12.32 一致)."""
        user32 = ctypes.windll.user32
        user32.PostMessageA.restype = ctypes.wintypes.BOOL
        user32.PostMessageA.argtypes = [
            ctypes.wintypes.HWND, ctypes.wintypes.UINT,
            ctypes.wintypes.WPARAM, ctypes.wintypes.LPARAM,
        ]
        user32.ScreenToClient.restype = ctypes.wintypes.BOOL
        user32.ScreenToClient.argtypes = [
            ctypes.wintypes.HWND, ctypes.POINTER(ctypes.wintypes.POINT)
        ]

        lbutton_down = False
        drag_start_y = 0
        is_dragging = False
        DRAG_THRESHOLD = 20
        WHEEL_DELTA = 120
        last_move_time = 0
        last_wheel_time = 0

        msg_map = {
            "move":     WM_MOUSEMOVE,
            "l_down":   WM_LBUTTONDOWN,
            "l_up":     WM_LBUTTONUP,
            "r_down":   WM_RBUTTONDOWN,
            "r_up":     WM_RBUTTONUP,
            "m_down":   WM_MBUTTONDOWN,
            "m_up":     WM_MBUTTONUP,
            "dblclk":   WM_LBUTTONDBLCLK,
        }

        while self._worker_running:
            if not self._raw_events:
                time.sleep(0.001)
                continue
            try:
                item = self._raw_events.popleft()
            except IndexError:
                continue

            event, screen_x, screen_y, wparam, wheel_delta = item

            try:
                mr = win32gui.GetWindowRect(self.master_hwnd)
            except Exception:
                mr = self.master_rect

            if event == "l_down":
                lbutton_down = True
                is_dragging = False
                drag_start_y = screen_y
            elif event == "l_up":
                lbutton_down = False
                is_dragging = False

            # 滚轮 → PostMessage 投到渲染窗口 (不动光标)
            if event == "wheel":
                now = int(time.time() * 1000)
                if now - last_wheel_time >= 30:
                    last_wheel_time = now
                    self._send_wheel_to_target(screen_x, screen_y, wheel_delta, mr)
                continue

            # 拖拽滚动 → dy 转 wheel, 同滚轮路径
            if event == "move" and lbutton_down:
                dy = screen_y - drag_start_y
                if not is_dragging and abs(dy) >= DRAG_THRESHOLD:
                    is_dragging = True
                if is_dragging and abs(dy) >= 20:
                    wd = -WHEEL_DELTA if dy > 0 else WHEEL_DELTA
                    drag_start_y = screen_y
                    self._send_wheel_to_target(screen_x, screen_y, wd, mr)
                continue

            if event == "move":
                now = int(time.time() * 1000)
                if now - last_move_time < 16:
                    continue
                last_move_time = now

            msg = msg_map.get(event)
            if not msg:
                continue

            is_move_event = (event == "move")

            # 网页模式点击: 主控也作为投递目标 (对主控自身 render 窗口 PostMessage,
            # 与同步窗口同一可靠机制; 真实点击已在钩子里抑制)。移动事件不加主控。
            targets = self.sync_targets
            if (self.sync_mode == "web" and not is_move_event
                    and self.master_hwnd and win32gui.IsWindow(self.master_hwnd)):
                targets = targets + [{
                    "hwnd": self.master_hwnd,
                    "render_hwnd": self.master_render_hwnd or self.master_hwnd,
                    "rect": mr,
                }]

            for target in targets:
                top_hwnd = target["hwnd"]
                render_hwnd = target.get("render_hwnd", top_hwnd)
                if not win32gui.IsWindow(top_hwnd):
                    continue
                try:
                    sr = win32gui.GetWindowRect(top_hwnd)
                except Exception:
                    sr = target["rect"]

                sx, sy = self._scale_point(screen_x, screen_y, mr, sr)
                top_cx, top_cy = self._screen_to_client(top_hwnd, sx, sy)
                top_lparam = self._make_lparam(top_cx, top_cy)

                if is_move_event:
                    user32.PostMessageA(top_hwnd, msg, wparam, top_lparam)
                else:
                    child_hwnd = self._find_child_at_point(top_hwnd, top_cx, top_cy)
                    msg_target = top_hwnd
                    msg_lparam = top_lparam
                    if child_hwnd and child_hwnd != top_hwnd:
                        child_cx, child_cy = self._screen_to_client(child_hwnd, sx, sy)
                        msg_lparam = self._make_lparam(child_cx, child_cy)
                        msg_target = child_hwnd
                    user32.PostMessageA(msg_target, msg, wparam, msg_lparam)
                    if msg_target != top_hwnd:
                        user32.PostMessageA(top_hwnd, msg, wparam, top_lparam)
                    if render_hwnd and render_hwnd != top_hwnd and win32gui.IsWindow(render_hwnd):
                        render_cx, render_cy = self._screen_to_client(render_hwnd, sx, sy)
                        render_lparam = self._make_lparam(render_cx, render_cy)
                        user32.PostMessageA(render_hwnd, msg, wparam, render_lparam)

    def _send_wheel_to_target(self, screen_x, screen_y, wheel_delta, master_rect, wparam_keys=0):
        """纯 PostMessage 滚轮 — 投到渲染子窗口 Chrome_RenderWidgetHostHWND, 不动光标."""
        user32 = ctypes.windll.user32
        wparam_wheel = (wheel_delta << 16) | (wparam_keys & 0xFFFF)

        for target in self.sync_targets:
            top_hwnd = target["hwnd"]
            render_hwnd = target.get("render_hwnd", top_hwnd)
            if not win32gui.IsWindow(top_hwnd):
                continue
            try:
                sr = win32gui.GetWindowRect(top_hwnd)
            except Exception:
                sr = target["rect"]

            sx, sy = self._scale_point(screen_x, screen_y, master_rect, sr)
            wheel_lparam = self._make_lparam(sx, sy)
            target_hwnd = render_hwnd if (render_hwnd and render_hwnd != top_hwnd and
                                          win32gui.IsWindow(render_hwnd)) else top_hwnd
            cx, cy = self._screen_to_client(target_hwnd, sx, sy)
            user32.PostMessageA(target_hwnd, WM_MOUSEMOVE, 0, self._make_lparam(cx, cy))
            user32.PostMessageA(target_hwnd, WM_MOUSEWHEEL, wparam_wheel, wheel_lparam)
            if target_hwnd != top_hwnd:
                user32.PostMessageA(top_hwnd, WM_MOUSEWHEEL, wparam_wheel, wheel_lparam)

    def _hook_loop(self):
        """鼠标钩子 + F10 键盘钩子 (全局强制停止). 与原同步器一致."""
        user32 = ctypes.windll.user32
        _buf = (MSLLHOOKSTRUCT * 1)()

        def low_level_mouse_proc(nCode, wParam, lParam):
            if nCode >= 0 and self.running and self.master_rect and self.sync_targets:
                ctypes.memmove(_buf, lParam, ctypes.sizeof(MSLLHOOKSTRUCT))
                flags = _buf[0].flags
                if flags & LLMHF_INJECTED:
                    return user32.CallNextHookEx(self._hook_id, nCode, wParam, lParam)
                if self._injecting:
                    return user32.CallNextHookEx(self._hook_id, nCode, wParam, lParam)

                x = _buf[0].pt.x
                y = _buf[0].pt.y
                try:
                    mr = win32gui.GetWindowRect(self.master_hwnd)
                except Exception:
                    mr = self.master_rect

                if mr[0] <= x <= mr[2] and mr[1] <= y <= mr[3]:
                    hwnd_at_point = win32gui.WindowFromPoint((x, y))
                    is_master_or_child = False
                    h = hwnd_at_point
                    while h:
                        if h == self.master_hwnd:
                            is_master_or_child = True
                            break
                        h = win32gui.GetParent(h)
                    if not is_master_or_child:
                        return user32.CallNextHookEx(self._hook_id, nCode, wParam, lParam)

                    msg_type = wParam
                    wparam_val = 0
                    wheel_delta = 0
                    event = None

                    if msg_type == WM_MOUSEMOVE:
                        event = "move"
                        if user32.GetKeyState(1) & 0x8000:
                            wparam_val = MK_LBUTTON
                        elif user32.GetKeyState(2) & 0x8000:
                            wparam_val = MK_RBUTTON
                    elif msg_type == WM_LBUTTONDOWN:
                        event = "l_down"; wparam_val = MK_LBUTTON
                    elif msg_type == WM_LBUTTONUP:
                        event = "l_up"; wparam_val = 0
                    elif msg_type == WM_RBUTTONDOWN:
                        event = "r_down"; wparam_val = MK_RBUTTON
                    elif msg_type == WM_RBUTTONUP:
                        event = "r_up"; wparam_val = 0
                    elif msg_type == WM_MBUTTONDOWN:
                        event = "m_down"; wparam_val = MK_MBUTTON
                    elif msg_type == WM_MBUTTONUP:
                        event = "m_up"; wparam_val = 0
                    elif msg_type == WM_LBUTTONDBLCLK:
                        event = "dblclk"; wparam_val = MK_LBUTTON
                    elif msg_type == WM_MOUSEWHEEL:
                        event = "wheel"
                        wheel_delta = _buf[0].mouseData >> 16
                        if wheel_delta > 0x7FFF:
                            wheel_delta -= 0x10000
                        if user32.GetKeyState(1) & 0x8000:
                            wparam_val = MK_LBUTTON
                        elif user32.GetKeyState(2) & 0x8000:
                            wparam_val = MK_RBUTTON
                        else:
                            wparam_val = 0

                    if event:
                        self._raw_events.append((event, x, y, wparam_val, wheel_delta))
                        # 网页模式: 抑制主控真实点击, 改由 worker PostMessage 给主控+同步窗口渲染窗口
                        if self.sync_mode == "web" and event in (
                                "l_down", "l_up", "r_down", "r_up",
                                "m_down", "m_up", "dblclk"):
                            return 1
                        return user32.CallNextHookEx(self._hook_id, nCode, wParam, lParam)

            return user32.CallNextHookEx(self._hook_id, nCode, wParam, lParam)

        def low_level_kb_proc(nCode, wParam, lParam):
            if nCode >= 0 and self.running:
                if wParam == 0x0100 or wParam == 0x0104:  # WM_KEYDOWN / WM_SYSKEYDOWN
                    vkCode = ctypes.cast(lParam, ctypes.POINTER(ctypes.c_ulong))[0]
                    if vkCode == 0x79:  # VK_F10
                        _log("[F10] 强制停止同步")
                        self.running = False
                        self._worker_running = False
                        _emit({"type": "status", "running": False, "reason": "f10"})
                        return 1
            return user32.CallNextHookEx(self._kb_hook_id, nCode, wParam, lParam)

        HOOKPROC = ctypes.WINFUNCTYPE(
            ctypes.c_long, ctypes.c_int, ctypes.c_uint, ctypes.c_long
        )

        self._kb_proc = HOOKPROC(low_level_kb_proc)
        self._kb_hook_id = user32.SetWindowsHookExA(WH_KEYBOARD_LL, self._kb_proc, None, 0)

        self._proc = HOOKPROC(low_level_mouse_proc)
        self._hook_id = user32.SetWindowsHookExA(WH_MOUSE_LL, self._proc, None, 0)

        if not self._hook_id:
            self.running = False
            if self._kb_hook_id:
                user32.UnhookWindowsHookEx(self._kb_hook_id)
                self._kb_hook_id = None
            _emit({"type": "error", "msg": "安装鼠标钩子失败"})
            return

        self._raw_events = collections.deque(maxlen=1024)
        self._worker_running = True
        threading.Thread(target=self._worker_thread, daemon=True).start()

        msg = ctypes.wintypes.MSG()
        while self.running:
            result = user32.PeekMessageW(ctypes.byref(msg), 0, 0, 0, win32con.PM_REMOVE)
            if result:
                user32.TranslateMessage(ctypes.byref(msg))
                user32.DispatchMessageW(ctypes.byref(msg))
            else:
                time.sleep(0.001)

        self._worker_running = False
        if self._hook_id:
            user32.UnhookWindowsHookEx(self._hook_id)
            self._hook_id = None
        if self._kb_hook_id:
            user32.UnhookWindowsHookEx(self._kb_hook_id)
            self._kb_hook_id = None


# ─── 主循环: 读取 stdin JSON 指令 ───

def _build_target(hwnd):
    hwnd = int(hwnd)
    try:
        rect = win32gui.GetWindowRect(hwnd)
    except Exception:
        rect = (0, 0, 0, 0)
    render = find_render_child(hwnd) or hwnd
    return {"hwnd": hwnd, "render_hwnd": render, "rect": rect}


def main():
    try:
        # Windows 下 stdout/stdin 用 utf-8, 防中文标题乱码
        sys.stdout.reconfigure(encoding="utf-8")
        sys.stdin.reconfigure(encoding="utf-8")
    except Exception:
        pass

    engine = SyncEngine()
    engine.set_mode("web")
    _emit({"type": "ready", "version": "12.32-headless"})

    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue
        try:
            req = json.loads(line)
        except Exception:
            _emit({"type": "error", "msg": "invalid json"})
            continue

        cmd = req.get("cmd")
        try:
            if cmd == "ping":
                _emit({"type": "pong"})

            elif cmd == "enumerate":
                wins = get_windows_by_pids(req.get("pids"), req.get("native_pids"))
                _emit({"type": "windows", "list": wins})

            elif cmd == "highlight":
                hwnd = req.get("hwnd")
                if hwnd:
                    threading.Thread(
                        target=flash_border,
                        args=(int(hwnd), (255, 0, 0), int(req.get("times", 3))),
                        daemon=True,
                    ).start()
                _emit({"type": "ok", "cmd": "highlight"})

            elif cmd == "start":
                master = req.get("master")
                targets = req.get("targets") or []
                mode = req.get("mode", "web")
                if not master:
                    _emit({"type": "error", "msg": "缺少主控窗口"})
                    continue
                if not targets:
                    _emit({"type": "error", "msg": "没有可同步的窗口"})
                    continue
                mt = _build_target(master)
                engine.set_master(mt["hwnd"], mt["rect"], mt["render_hwnd"])
                engine.set_sync_targets([_build_target(h) for h in targets])
                engine.set_mode(mode)
                engine.start()
                _emit({"type": "status", "running": True, "mode": mode,
                       "master": mt["hwnd"], "targets": len(targets)})

            elif cmd == "stop":
                engine.stop()
                _emit({"type": "status", "running": False})

            elif cmd == "status":
                _emit({"type": "status", "running": bool(engine.running),
                       "mode": engine.sync_mode,
                       "master": engine.master_hwnd or 0,
                       "targets": len(engine.sync_targets)})

            elif cmd == "window-op":
                try:
                    action, count, failed = _cmd_window_op(req)
                    _emit({"type": "window-op", "action": action,
                           "count": count, "failed": failed})
                except Exception as e:
                    _emit({"type": "error", "msg": "窗口操作失败: %s" % e})

            elif cmd == "batch-copy":
                # 批量复制通行证码：按百分比坐标逐窗口激活 → 双击 → Ctrl+C → 读剪贴板
                hwnds = req.get("hwnds") or []
                px = float(req.get("px") or 0)
                py = float(req.get("py") or 0)
                user32 = ctypes.windll.user32
                codes = []
                for hwnd in hwnds:
                    h = int(hwnd)
                    if not win32gui.IsWindow(h):
                        codes.append("[SKIP:窗口已关闭]")
                        continue
                    try:
                        # 还原最小化窗口
                        if win32gui.IsIconic(h):
                            win32gui.ShowWindow(h, SW_RESTORE)
                            time.sleep(0.2)
                        # Z 序顶层 + Alt 释放前台锁激活
                        user32.SetWindowPos(h, 0, 0, 0, 0, 0,
                            SWP_NOMOVE | SWP_NOSIZE | SWP_NOACTIVATE)
                        user32.keybd_event(VK_MENU, 0, 0, 0)
                        user32.SetForegroundWindow(h)
                        user32.keybd_event(VK_MENU, 0, win32con.KEYEVENTF_KEYUP, 0)
                        # 给客户端足够时间聚焦和渲染
                        time.sleep(0.8)
                        # 按百分比算坐标
                        rect = win32gui.GetWindowRect(h)
                        w = rect[2] - rect[0]
                        hh = rect[3] - rect[1]
                        cx = rect[0] + int(px / 100.0 * w)
                        cy = rect[1] + int(py / 100.0 * hh)
                        old_pos = win32gui.GetCursorPos()
                        win32api.SetCursorPos((cx, cy))
                        time.sleep(0.1)
                        # 双击
                        user32.mouse_event(MOUSEEVENTF_LEFTDOWN, 0, 0, 0, 0)
                        time.sleep(0.08)
                        user32.mouse_event(MOUSEEVENTF_LEFTUP, 0, 0, 0, 0)
                        time.sleep(0.08)
                        user32.mouse_event(MOUSEEVENTF_LEFTDOWN, 0, 0, 0, 0)
                        time.sleep(0.08)
                        user32.mouse_event(MOUSEEVENTF_LEFTUP, 0, 0, 0, 0)
                        # 等选中文本
                        time.sleep(0.8)
                        # Ctrl+C
                        user32.keybd_event(VK_CONTROL, 0, 0, 0)
                        user32.keybd_event(VK_C, 0, 0, 0)
                        time.sleep(0.08)
                        user32.keybd_event(VK_C, 0, win32con.KEYEVENTF_KEYUP, 0)
                        user32.keybd_event(VK_CONTROL, 0, win32con.KEYEVENTF_KEYUP, 0)
                        time.sleep(0.5)
                        # 读剪贴板；空则重试
                        data = _read_clipboard()
                        if not data:
                            time.sleep(0.6)
                            user32.keybd_event(VK_CONTROL, 0, 0, 0)
                            user32.keybd_event(VK_C, 0, 0, 0)
                            time.sleep(0.08)
                            user32.keybd_event(VK_C, 0, win32con.KEYEVENTF_KEYUP, 0)
                            user32.keybd_event(VK_CONTROL, 0, win32con.KEYEVENTF_KEYUP, 0)
                            time.sleep(0.5)
                            data = _read_clipboard()
                        codes.append(data.strip() if data else "[EMPTY:未取到内容]")
                        # 恢复光标
                        win32api.SetCursorPos(old_pos)
                        time.sleep(0.1)
                    except Exception as e:
                        codes.append("[ERR:%s]" % e)
                _emit({"type": "batch-copy-result", "codes": codes, "hwnds": hwnds})

            elif cmd == "quit":
                engine.stop()
                break

            else:
                _emit({"type": "error", "msg": "unknown cmd: %s" % cmd})
        except Exception as e:
            _emit({"type": "error", "msg": "%s" % e})

    engine.stop()


if __name__ == "__main__":
    main()
