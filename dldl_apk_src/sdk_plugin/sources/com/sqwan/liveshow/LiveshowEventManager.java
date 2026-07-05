package com.sqwan.liveshow;

import android.os.Handler;
import android.os.Message;
import com.sqwan.common.util.LogUtil;
import com.youme.voiceengine.MemberChange;
import com.youme.voiceengine.YouMeCallBackInterface;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowEventManager implements YouMeCallBackInterface {
    protected String TAG = getClass().getSimpleName();
    private String strTips = "请先初始化";
    public boolean mInit = false;
    private final int INIT_OK = 0;
    private final int INIT_NOK = 1;
    private final int ANCHOR_MODE = 1;
    private final int LISTEN_ANCHOR_MODE = 2;
    private final int NORMAL_ROOM_MODE = 3;
    private final int WHITE_USER_MODE = 4;
    private final int CONTROL_OTHER_MIC_SPEAK = 5;
    private final int JOIN_FAIL = 100;
    public int mMode = 2;
    private Handler initHandler = new Handler() { // from class: com.sqwan.liveshow.LiveshowEventManager.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                LiveshowEventManager liveshowEventManager = LiveshowEventManager.this;
                liveshowEventManager.log(liveshowEventManager.strTips);
                LiveshowEventManager.this.liveshowEventWrapperDispatcher.dispatchInit(true);
            } else {
                if (i != 1) {
                    return;
                }
                LiveshowEventManager liveshowEventManager2 = LiveshowEventManager.this;
                liveshowEventManager2.log(liveshowEventManager2.strTips);
                LiveshowEventManager.this.liveshowEventWrapperDispatcher.dispatchInit(false);
            }
        }
    };
    private Handler joinHandler = new Handler() { // from class: com.sqwan.liveshow.LiveshowEventManager.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 2) {
                LiveshowEventManager.this.liveshowEventWrapperDispatcher.dispatchJoinRoom(true);
            } else {
                if (i != 100) {
                    return;
                }
                LiveshowEventManager liveshowEventManager = LiveshowEventManager.this;
                liveshowEventManager.log(liveshowEventManager.strTips);
                LiveshowEventManager.this.liveshowEventWrapperDispatcher.dispatchJoinRoom(false);
            }
        }
    };
    private Handler leaveHandler = new Handler() { // from class: com.sqwan.liveshow.LiveshowEventManager.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                LiveshowEventManager.this.log("已成功退出主播模式");
                return;
            }
            if (i == 2) {
                LiveshowEventManager.this.log("已成功退出听众模式");
                LiveshowEventManager.this.liveshowEventWrapperDispatcher.dispatchLeaveRoom(true);
            } else {
                if (i != 3) {
                    return;
                }
                LiveshowEventManager.this.log("已成功退出普通房间");
            }
        }
    };
    public LiveshowEventWrapperDispatcher liveshowEventWrapperDispatcher = new LiveshowEventWrapperDispatcher();

    @Override // com.youme.voiceengine.YouMeCallBackInterface
    public void onBroadcast(int i, String str, String str2, String str3, String str4) {
    }

    void log(String str) {
        LogUtil.d(this.TAG, str);
    }

    @Override // com.youme.voiceengine.YouMeCallBackInterface
    public void onEvent(int i, int i2, String str, Object obj) {
        log("OnEvent:event " + i + ",error " + i2 + ",channel " + str + ",param_" + obj.toString());
        Message message = new Message();
        if (i == 62) {
            log("对指定频道设置白名单成功");
            if (i2 == -501) {
                log("设置白名单部分用户异常：已不在房间");
                return;
            }
            return;
        }
        if (i != 63) {
            switch (i) {
                case 0:
                    log("Talk 初始化成功");
                    this.strTips = "初始化成功";
                    this.mInit = true;
                    message.what = 0;
                    this.initHandler.sendMessage(message);
                    break;
                case 1:
                    log("Talk 初始化失败");
                    this.strTips = "初始化失败";
                    this.mInit = false;
                    message.what = 1;
                    this.initHandler.sendMessage(message);
                    break;
                case 2:
                    log("Talk 进入频道成功，频道：" + str + " 用户id:" + obj);
                    message.what = this.mMode;
                    this.joinHandler.sendMessage(message);
                    break;
                case 3:
                    log("Talk 进入频道:" + str + "失败,code:" + i2);
                    StringBuilder sb = new StringBuilder();
                    sb.append("进入房间失败,error code:");
                    sb.append(i2);
                    this.strTips = sb.toString();
                    message.what = 100;
                    this.joinHandler.sendMessage(message);
                    break;
                case 4:
                    log("Talk 离开单个频道:" + str);
                    message.what = this.mMode;
                    this.leaveHandler.sendMessage(message);
                    break;
                case 5:
                    log("Talk 离开所有频道，这个回调channel参数为空字符串");
                    message.what = this.mMode;
                    this.leaveHandler.sendMessage(message);
                    break;
                case 6:
                    log("Talk 暂停");
                    this.liveshowEventWrapperDispatcher.dispatchChannelChange(false);
                    break;
                case 7:
                    this.liveshowEventWrapperDispatcher.dispatchChannelChange(true);
                    log("Talk 恢复");
                    break;
                default:
                    switch (i) {
                        case 10:
                            log("Talk 正在重连");
                            break;
                        case 11:
                            log("Talk 重连成功");
                            break;
                        case 12:
                            log("录音启动失败，code：" + i2);
                            break;
                        case 13:
                            log("背景音乐播放结束,path：" + obj);
                            break;
                        case 14:
                            log("背景音乐播放失败,code：" + i2);
                            break;
                        default:
                            switch (i) {
                                case 16:
                                    log("其他用户麦克风打开,userid:" + obj);
                                    if (obj instanceof String) {
                                        this.liveshowEventWrapperDispatcher.dispatcheOthersMicChange(true, (String) obj);
                                    }
                                    break;
                                case 17:
                                    log("其他用户麦克风关闭,userid:" + obj);
                                    if (obj instanceof String) {
                                        this.liveshowEventWrapperDispatcher.dispatcheOthersMicChange(false, (String) obj);
                                    }
                                    break;
                                case 18:
                                    log("其他用户扬声器打开,userid:" + obj);
                                    break;
                                case 19:
                                    log("其他用户扬声器关闭,userid:" + obj);
                                    break;
                                case 20:
                                    log("开始讲话,userid:" + obj);
                                    break;
                                case 21:
                                    log("停止讲话userid:" + obj);
                                    break;
                                case 22:
                                    log("我当前讲话的音量级别是,数值：" + i2);
                                    break;
                                case 23:
                                    log("自己的麦克风被其他用户打开，userid：" + obj);
                                    break;
                                case 24:
                                    log("自己的麦克风被其他用户关闭，userid：" + obj);
                                    break;
                                case 25:
                                    log("自己的扬声器被其他用户打开，userid：" + obj);
                                    break;
                                case 26:
                                    log("自己的扬声器被其他用户关闭，userid：" + obj);
                                    break;
                                case 27:
                                    log("取消屏蔽某人语音，userid：" + obj);
                                    break;
                                case 28:
                                    log("屏蔽某人语音,userid：" + obj);
                                    break;
                            }
                            break;
                    }
                    break;
            }
            return;
        }
        log("对指定频道设置白名单失败");
    }

    @Override // com.youme.voiceengine.YouMeCallBackInterface
    public void onRequestRestAPI(int i, int i2, String str, String str2) {
        log("onRequestRestAPI requestID:" + i + " errorCode:" + i2 + " queryParam" + str);
        log(str2);
    }

    @Override // com.youme.voiceengine.YouMeCallBackInterface
    public void onMemberChange(String str, MemberChange[] memberChangeArr, boolean z) {
        log("OnMemberChange:" + str + " member count:" + memberChangeArr.length);
        for (MemberChange memberChange : memberChangeArr) {
            log("userid:" + memberChange.userID + " isJoin:" + memberChange.isJoin);
        }
        this.liveshowEventWrapperDispatcher.dispatchMemberChange(str, memberChangeArr, z);
    }
}
