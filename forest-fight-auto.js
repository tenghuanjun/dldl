/**
 * 魂兽森林（ForestFight）自动化脚本
 * 注入到游戏页面后，自动执行魂兽森林任务
 */
(function () {
  'use strict';

  const LOG_PREFIX = '[魂兽森林]';
  const CONFIG = {
    CHECK_INTERVAL: 2000,     // 检查游戏加载的间隔 (ms)
    ACTION_DELAY: 1500,       // 操作间隔 (ms)
    MOVE_DELAY: 800,          // 移动间隔 (ms)
    MAX_RETRIES: 30,          // 最大重试次数
    AUTO_START: true,         // 是否自动开始
    FOREST_FIGHT_FROM: 3,     // ForestFight 的 from 值
  };

  let isRunning = false;
  let retryCount = 0;
  let logArea = null;

  function log(msg) {
    const text = `${LOG_PREFIX} ${msg}`;
    console.log(text);
    if (logArea) {
      const time = new Date().toLocaleTimeString();
      logArea.textContent += `[${time}] ${msg}\n`;
      logArea.scrollTop = logArea.scrollHeight;
    }
  }

  // 创建日志面板
  function createLogPanel() {
    if (document.getElementById('dldl-forest-log')) return;
    const panel = document.createElement('div');
    panel.id = 'dldl-forest-log';
    panel.style.cssText = `
      position: fixed; left: 10px; bottom: 10px; z-index: 99999;
      width: 380px; max-height: 300px; background: rgba(0,0,0,0.85);
      border: 1px solid #2f81f7; border-radius: 8px; padding: 10px;
      font-family: monospace; font-size: 12px; color: #0f0;
      overflow-y: auto; pointer-events: auto;
    `;

    const title = document.createElement('div');
    title.style.cssText = 'color: #2f81f7; font-weight: bold; margin-bottom: 6px; font-size: 13px;';
    title.textContent = '🌲 魂兽森林自动化';

    const status = document.createElement('div');
    status.id = 'dldl-forest-status';
    status.style.cssText = 'color: #ff0; margin-bottom: 6px;';
    status.textContent = '状态: 等待游戏加载...';

    logArea = document.createElement('div');
    logArea.style.cssText = 'white-space: pre-wrap; word-break: break-all;';

    const btnRow = document.createElement('div');
    btnRow.style.cssText = 'display: flex; gap: 6px; margin-top: 8px;';

    const startBtn = document.createElement('button');
    startBtn.textContent = '开始任务';
    startBtn.style.cssText = 'padding: 4px 10px; background: #2f81f7; color: #fff; border: none; border-radius: 4px; cursor: pointer; font-size: 12px;';
    startBtn.onclick = () => startForestFight();

    const stopBtn = document.createElement('button');
    stopBtn.textContent = '停止';
    stopBtn.style.cssText = 'padding: 4px 10px; background: #d14343; color: #fff; border: none; border-radius: 4px; cursor: pointer; font-size: 12px;';
    stopBtn.onclick = () => { isRunning = false; log('已停止'); updateStatus('已停止'); };

    const closeBtn = document.createElement('button');
    closeBtn.textContent = '关闭面板';
    closeBtn.style.cssText = 'padding: 4px 10px; background: #555; color: #fff; border: none; border-radius: 4px; cursor: pointer; font-size: 12px;';
    closeBtn.onclick = () => panel.remove();

    btnRow.appendChild(startBtn);
    btnRow.appendChild(stopBtn);
    btnRow.appendChild(closeBtn);

    panel.appendChild(title);
    panel.appendChild(status);
    panel.appendChild(logArea);
    panel.appendChild(btnRow);
    document.body.appendChild(panel);
  }

  function updateStatus(text) {
    const el = document.getElementById('dldl-forest-status');
    if (el) el.textContent = `状态: ${text}`;
  }

  // 等待 X5WebApp 加载（登录面板）
  function waitForX5WebApp() {
    return new Promise((resolve, reject) => {
      let retries = 0;
      const check = () => {
        retries++;
        if (retries > 60) {
          reject(new Error('X5WebApp 加载超时'));
          return;
        }
        if (window.X5WebApp && window.X5WebApp.instance) {
          log('X5WebApp 已加载');
          resolve();
        } else {
          setTimeout(check, 1000);
        }
      };
      check();
    });
  }

  // 等待游戏核心对象加载（进入游戏后）
  function waitForGame() {
    return new Promise((resolve, reject) => {
      const check = () => {
        retryCount++;
        if (retryCount > CONFIG.MAX_RETRIES) {
          reject(new Error('游戏加载超时'));
          return;
        }
        // 检查游戏核心对象是否存在
        if (typeof window.com !== 'undefined' &&
            window.com.game &&
            window.com.game.timeCorridor) {
          log('游戏核心已加载');
          resolve();
        } else if (typeof window.TimeCorridorControl !== 'undefined') {
          log('TimeCorridorControl 已加载');
          resolve();
        } else {
          setTimeout(check, CONFIG.CHECK_INTERVAL);
        }
      };
      check();
    });
  }

  // 自动点击「进入游戏」
  async function autoEnterGame() {
    log('等待登录面板加载...');
    try {
      await waitForX5WebApp();
    } catch (e) {
      log('X5WebApp 加载失败: ' + e.message);
      return false;
    }

    // 等待一下让面板完全初始化
    await delay(2000);

    // 检查是否需要同意隐私政策
    try {
      const checkBtn = document.querySelector('.check-btn, [class*="privacy"]');
      if (checkBtn && !checkBtn.checked) {
        checkBtn.click();
        log('已勾选隐私政策');
        await delay(500);
      }
    } catch (e) {}

    // 方式1：直接调用 enterGame API
    try {
      if (window.X5WebApp.instance.selectedServer) {
        log('正在进入游戏...');
        window.X5WebApp.instance.enterGame();
        return true;
      }
    } catch (e) {
      log('enterGame 调用失败: ' + e.message);
    }

    // 方式2：查找并点击「进入游戏」按钮
    try {
      const allImgs = document.querySelectorAll('img, [resource*="enterGame"], [resource*="img_enter"]');
      for (const img of allImgs) {
        const src = img.getAttribute('resource') || img.getAttribute('src') || '';
        if (src.includes('enterGame') || src.includes('img_enter')) {
          img.click();
          log('已点击进入游戏按钮');
          return true;
        }
      }
    } catch (e) {
      log('点击按钮失败: ' + e.message);
    }

    // 方式3：查找包含「进入游戏」文字的元素
    try {
      const allElements = document.querySelectorAll('*');
      for (const el of allElements) {
        if (el.children.length === 0 && el.textContent && el.textContent.includes('进入游戏')) {
          el.click();
          log('已点击进入游戏文字');
          return true;
        }
      }
    } catch (e) {}

    log('未找到进入游戏按钮，请手动点击');
    return false;
  }

  // 获取 TimeCorridorControl 实例
  function getTimeCorridorControl() {
    // 尝试多种方式获取
    if (window.TimeCorridorControl) return window.TimeCorridorControl;
    if (window.com && window.com.game && window.com.game.timeCorridor) {
      return window.com.game.timeCorridor.control;
    }
    // 尝试从全局搜索
    try {
      // 某些游戏版本将 Control 挂在不同的命名空间
      const keys = Object.keys(window);
      for (const key of keys) {
        const obj = window[key];
        if (obj && obj.ins && obj.ins.protrol && obj.ins.protrol.CM_TimeCorridorStartAdventureHandler) {
          return obj;
        }
      }
    } catch (e) {}
    return null;
  }

  // 获取 ForestFightControl 实例
  function getForestFightControl() {
    if (window.ForestFightControl) return window.ForestFightControl;
    if (window.com && window.com.game && window.com.game.forestFight) {
      return window.com.game.forestFight.control;
    }
    return null;
  }

  // 延迟函数
  function delay(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  // 发送协议消息（通过 hook 方式）
  function sendProtocol(protocolName, ...args) {
    try {
      const TC = getTimeCorridorControl();
      if (!TC || !TC.ins || !TC.ins.protrol) {
        log('错误: TimeCorridorControl 未就绪');
        return false;
      }
      const handler = TC.ins.protrol[protocolName];
      if (typeof handler === 'function') {
        handler.apply(TC.ins.protrol, args);
        log(`已发送: ${protocolName}`);
        return true;
      } else {
        log(`错误: 未找到协议 ${protocolName}`);
        return false;
      }
    } catch (e) {
      log(`发送协议失败: ${e.message}`);
      return false;
    }
  }

  // 开始魂兽森林任务
  async function startForestFight() {
    if (isRunning) {
      log('已经在运行中');
      return;
    }
    isRunning = true;
    updateStatus('正在启动...');

    try {
      // 1. 自动点击「进入游戏」
      log('尝试自动进入游戏...');
      const entered = await autoEnterGame();
      if (entered) {
        log('已自动进入游戏，等待加载...');
      } else {
        log('请手动点击「进入游戏」');
      }

      // 2. 等待游戏加载
      log('等待游戏核心加载...');
      await waitForGame();
      updateStatus('游戏已加载，准备开始任务');

      // 3. 等待游戏完全初始化
      await delay(3000);

      // 4. 设置 TimeCorridor from 为 ForestFight (3)
      const TC = getTimeCorridorControl();
      if (TC && TC.from !== undefined) {
        TC.from = CONFIG.FOREST_FIGHT_FROM;
        log(`已设置 from = ${CONFIG.FOREST_FIGHT_FROM} (ForestFight)`);
      }

      // 5. 打开魂兽森林面板
      const FF = getForestFightControl();
      if (FF && FF.ins && typeof FF.ins.open === 'function') {
        FF.ins.open();
        log('已打开魂兽森林面板');
        await delay(CONFIG.ACTION_DELAY);
      }

      // 6. 开始冒险 - 使用活动ID 10604
      const activityId = 10604;
      log(`准备开始冒险，活动ID: ${activityId}`);

      if (TC && TC.ins && TC.ins.protrol) {
        // 先打开分组信息
        if (TC.ins.protrol.CM_TimeCorridorOpenGroupInfoHandler) {
          TC.ins.protrol.CM_TimeCorridorOpenGroupInfoHandler(activityId);
          log('已请求打开分组信息');
          await delay(CONFIG.ACTION_DELAY);
        }

        // 开始冒险
        if (TC.ins.protrol.CM_TimeCorridorStartAdventureHandler) {
          TC.ins.protrol.CM_TimeCorridorStartAdventureHandler(activityId);
          log('已发送开始冒险请求');
          await delay(CONFIG.ACTION_DELAY);
        }
      }

      updateStatus('任务进行中...');
      log('魂兽森林任务已启动，请观察游戏界面');

      // 7. 监听任务状态
      monitorTask();

    } catch (e) {
      log(`错误: ${e.message}`);
      updateStatus(`错误: ${e.message}`);
      isRunning = false;
    }
  }

  // 监听任务状态
  function monitorTask() {
    const checkStatus = () => {
      if (!isRunning) return;

      try {
        const TC = getTimeCorridorControl();
        if (TC && TC.ins && TC.ins.model) {
          const model = TC.ins.model;
          // 检查是否有需要处理的事件
          if (model.lastMoveBoxIdx !== undefined && model.lastMoveBoxIdx >= 0) {
            log(`检测到可交互格子: ${model.lastMoveBoxIdx}`);
          }
        }
      } catch (e) {
        // 静默处理
      }

      if (isRunning) {
        setTimeout(checkStatus, CONFIG.CHECK_INTERVAL);
      }
    };
    checkStatus();
  }

  // Hook 游戏的协议发送系统（可选，用于调试）
  function hookProtocolSystem() {
    try {
      const TC = getTimeCorridorControl();
      if (!TC || !TC.ins || !TC.ins.protrol) return;

      const protrol = TC.ins.protrol;
      const originalMethods = {};

      // Hook 关键方法
      const methodsToHook = [
        'CM_TimeCorridorMoveHandler',
        'CM_TimeCorridorTriggerEventHandler',
        'CM_TimeCorridorEndBattleHandler',
      ];

      for (const methodName of methodsToHook) {
        if (typeof protrol[methodName] === 'function') {
          originalMethods[methodName] = protrol[methodName];
          protrol[methodName] = function (...args) {
            log(`[Hook] ${methodName} 被调用`);
            return originalMethods[methodName].apply(this, args);
          };
        }
      }

      log('协议系统已 Hook');
    } catch (e) {
      log(`Hook 失败: ${e.message}`);
    }
  }

  // 初始化
  function init() {
    // 检查 URL 参数是否启用自动启动
    const params = new URLSearchParams(location.search);
    const autoStart = params.get('dldl_forest') === '1' || CONFIG.AUTO_START;

    // 读取账号信息
    const uname = params.get('uname') || window.__DLDL_PROXY_UNAME || '';
    const upwd = params.get('upwd') || window.__DLDL_PROXY_UPWD || '';
    if (uname) {
      log(`账号: ${uname}`);
    }

    // 等待 DOM 加载完成
    if (document.readyState === 'loading') {
      document.addEventListener('DOMContentLoaded', () => {
        createLogPanel();
        if (autoStart) {
          log('自动启动模式，3秒后开始...');
          setTimeout(() => startForestFight(), 3000);
        }
      });
    } else {
      createLogPanel();
      if (autoStart) {
        log('自动启动模式，3秒后开始...');
        setTimeout(() => startForestFight(), 3000);
      }
    }
  }

  // 暴露全局接口，方便外部调用
  window.__DLDL_ForestFight = {
    start: startForestFight,
    stop: () => { isRunning = false; log('已停止'); updateStatus('已停止'); },
    hook: hookProtocolSystem,
    config: CONFIG,
  };

  init();
  log('脚本已加载，等待游戏...');
})();
