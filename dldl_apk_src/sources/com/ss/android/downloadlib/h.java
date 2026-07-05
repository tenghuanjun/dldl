package com.ss.android.downloadlib;

import com.ss.android.downloadad.api.constant.AdBaseConstants;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.g.l;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.appdownloader.c.o;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class h {
    private static final String a = h.class.getSimpleName();
    private static boolean b = false;

    private static class a {
        private static h a = new h();
    }

    private h() {
    }

    public static h a() {
        return a.a;
    }

    public void a(com.ss.android.downloadlib.addownload.b.e eVar, final com.ss.android.downloadad.api.a.b bVar) {
        if (!com.ss.android.downloadlib.g.e.c(bVar)) {
            l.a().a(a, "checkMarketInstallFinishEvent", "开关未开启, 不使用动态广播监听能力");
            return;
        }
        if (bVar == null) {
            l.a().b(a, "checkMarketInstallFinishEvent", "nativeDownloadModel为空,不符合预期");
            return;
        }
        if (bVar.Y()) {
            l.a().b(a, "checkMarketInstallFinishEvent", "正在监听中,不重复监听");
            return;
        }
        try {
            l.a().a(a, "checkMarketInstallFinishEvent", "针对商店直投广告,开始检测安装完成事件");
            if (com.ss.android.downloadlib.g.e.d(bVar)) {
                l.a().a(a, "checkMarketInstallFinishEvent", "开始进行动态广播监听");
                int iOptInt = k.j().optInt("market_install_finish_check_time", AdBaseConstants.DEFAULT_BROADCAST_CHECK_TIME);
                if (bVar.ag() == 0 || System.currentTimeMillis() - bVar.ag() > ((long) iOptInt) * 1000) {
                    bVar.j(System.currentTimeMillis());
                    a(iOptInt, new o() { // from class: com.ss.android.downloadlib.h.1
                        @Override // com.ss.android.socialbase.appdownloader.c.o
                        public void a() {
                            l.a().a(h.a, "checkMarketInstallFinishEvent", "注册广播监听成功,注册耗时" + (System.currentTimeMillis() - bVar.ag()));
                            bVar.j(true);
                            com.ss.android.downloadlib.addownload.b.f.a().a(bVar);
                        }

                        @Override // com.ss.android.socialbase.appdownloader.c.o
                        public void b() {
                            l.a().a(h.a, "checkMarketInstallFinishEvent", "广播监听时间结束,主动解除了广播监听");
                            if (!m.b(bVar)) {
                                l.a().a(h.a, "checkMarketInstallFinishEvent", "监听结束依然没有完成安装");
                            }
                            bVar.j(false);
                            com.ss.android.downloadlib.addownload.b.f.a().a(bVar);
                        }
                    });
                } else {
                    l.a().a(a, "checkMarketInstallFinishEvent", "目前仍在广播监听的生效期内,不进行重复注册");
                }
            }
            if (com.ss.android.downloadlib.g.e.e(bVar)) {
                l.a().a(a, "checkMarketInstallFinishEvent", "开启轮询线程能力,作为容灾手段");
                com.ss.android.downloadlib.addownload.l.a().a(bVar);
            }
            com.ss.android.downloadlib.addownload.b.i.a().a(bVar);
        } catch (Exception unused) {
            com.ss.android.downloadlib.e.c.a().a(false, "监听商店场景安装完成事件发生异常");
        }
    }

    public void a(final com.ss.android.downloadad.api.a.b bVar) {
        if (!com.ss.android.downloadlib.g.e.c(bVar)) {
            l.a().a(a, "checkMarketInstallFinishEventForReboot", "线程轮询总开关未开启,因此不执行兜底逻辑");
            return;
        }
        if (bVar == null) {
            l.a().b(a, "checkMarketInstallFinishEventForReboot", "nativeDownloadModel为空,不符合预期");
            return;
        }
        if (bVar.e.get() && System.currentTimeMillis() - bVar.ag() > com.igexin.push.config.c.k) {
            l.a().a(a, "checkMarketInstallFinishEventForReboot", "兜底过一次了,不进行重复兜底");
            return;
        }
        if (com.ss.android.downloadlib.g.e.d(bVar)) {
            long jOptInt = ((long) k.j().optInt("market_install_finish_check_time", AdBaseConstants.DEFAULT_BROADCAST_CHECK_TIME)) * 1000;
            if (System.currentTimeMillis() - bVar.ag() >= jOptInt) {
                l.a().a(a, "checkMarketInstallFinishEventForReboot", "当前时间距离首次检测时间超出了广播生效期,不再执行兜底策略");
            } else {
                a((int) (((bVar.ag() + jOptInt) - System.currentTimeMillis()) / 1000), new o() { // from class: com.ss.android.downloadlib.h.2
                    @Override // com.ss.android.socialbase.appdownloader.c.o
                    public void a() {
                        l.a().a(h.a, "checkMarketInstallFinishEventForReboot", "进程被杀,重新注册广播监听成功,正式执行冷启兜底逻辑");
                        bVar.e.compareAndSet(false, true);
                        com.ss.android.downloadlib.addownload.b.i.a().a(bVar);
                    }

                    @Override // com.ss.android.socialbase.appdownloader.c.o
                    public void b() {
                        l.a().a(h.a, "checkMarketInstallFinishEventForReboot", "兜底监听执行完毕,解除广播监听");
                        if (!m.b(bVar)) {
                            l.a().a(h.a, "checkMarketInstallFinishEventForReboot", "监听结束依然没有完成安装");
                        }
                        bVar.e.compareAndSet(true, false);
                        bVar.j(false);
                        com.ss.android.downloadlib.addownload.b.i.a().a(bVar);
                    }
                });
            }
        }
    }

    public void a(int i, o oVar) {
        l.a().a(a, "registerMarketInstallFinishBroadcast", "动态广播监听的持续时间为:" + i);
        if (!b) {
            com.ss.android.socialbase.appdownloader.d.j().a(oVar);
            b = true;
        }
        com.ss.android.socialbase.appdownloader.d.j().a(i);
    }
}
