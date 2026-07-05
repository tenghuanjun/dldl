package com.sqwan.liveshow.im;

import android.content.Context;
import android.text.TextUtils;
import com.google.sqgson.Gson;
import com.sqwan.common.util.LogUtil;
import com.sqwan.liveshow.LiveshowBaseManager;
import com.sqwan.liveshow.LiveshowManager;
import com.sqwan.liveshow.error.LiveshowResult;
import com.sqwan.liveshow.trackaction.LiveshowTrackManager;
import com.youme.im.IMEngine;
import com.youme.im.IMEngine.IntegerVal;
import com.youme.imsdk.YIMClient;
import com.youme.imsdk.callback.YIMEventCallback;
import com.youme.imsdk.internal.ChatRoom;
import com.youme.imsdk.internal.SendMessage;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowImManager extends LiveshowBaseManager {
    private static LiveshowImManager sInstance;
    private String chatRoomId;
    private boolean hasInited;
    public ILiveshowImCallback iLiveshowImCallback;
    private String recordInput;
    private String userId;
    private String TAG = getClass().getSimpleName();
    public boolean isRecordInput = false;

    private void resetDatas() {
    }

    public static LiveshowImManager getInstance() {
        if (sInstance == null) {
            synchronized (LiveshowImManager.class) {
                if (sInstance == null) {
                    sInstance = new LiveshowImManager();
                }
            }
        }
        return sInstance;
    }

    public String getRecordInput() {
        return this.recordInput;
    }

    public void setRecordInput(String str) {
        if (this.isRecordInput) {
            this.recordInput = str;
        }
    }

    public void clearRecordInput() {
        if (this.isRecordInput) {
            this.recordInput = "";
        }
    }

    private void initYouMeIMEngine(Context context) {
        if (this.hasInited) {
            return;
        }
        int iInit = YIMClient.getInstance().init(context.getApplicationContext(), LiveshowManager.getInstance().liveshowConfig.appkey, LiveshowManager.getInstance().liveshowConfig.appsecret, 0);
        LogUtil.i(this.TAG, "initYouMeIMEngine result " + iInit);
        if (iInit == 0) {
            this.hasInited = true;
        }
    }

    public void joinRoom(Context context, String str, String str2, ILiveshowImCallback iLiveshowImCallback) {
        this.iLiveshowImCallback = iLiveshowImCallback;
        iLiveshowImCallback.init();
        initYouMeIMEngine(context);
        init();
        login(str, str2);
    }

    private boolean hasLogined() {
        return !TextUtils.isEmpty(this.userId);
    }

    private boolean joinedRoom() {
        return !TextUtils.isEmpty(this.chatRoomId);
    }

    public void leaveRoom() {
        if (joinedRoom()) {
            leaveChatRoom(this.chatRoomId);
        } else {
            release();
        }
    }

    private void init() {
        YIMClient.getInstance().switchMsgTransType(1);
        YIMClient.getInstance().registerReconnectCallback(this.iLiveshowImCallback);
        YIMClient.getInstance().registerKickOffCallback(this.iLiveshowImCallback);
        YIMClient.getInstance().registerMsgEventCallback(this.iLiveshowImCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release() {
        YIMClient.getInstance().unRegisterReconnectCallback();
        YIMClient.getInstance().unRegisterKickOffCallback();
        YIMClient.getInstance().unRegisterMsgEventCallback();
        ILiveshowImCallback iLiveshowImCallback = this.iLiveshowImCallback;
        if (iLiveshowImCallback != null) {
            iLiveshowImCallback.release();
            this.iLiveshowImCallback = null;
        }
    }

    private void login(String str, final String str2) {
        YIMClient.getInstance().login(str, str, "", new YIMEventCallback.ResultCallback<String>() { // from class: com.sqwan.liveshow.im.LiveshowImManager.1
            @Override // com.youme.imsdk.callback.YIMEventCallback.ResultCallback
            public void onSuccess(String str3) {
                LogUtil.i(LiveshowImManager.this.TAG, "用户: " + str3 + " 登录成功");
                LiveshowImManager.this.userId = str3;
                LiveshowImManager.this.joinChatRoom(str2);
            }

            @Override // com.youme.imsdk.callback.YIMEventCallback.ResultCallback
            public void onFailed(int i, String str3) {
                LogUtil.i(LiveshowImManager.this.TAG, "用户: " + str3 + " 登录失败:" + i);
                if (i == 7) {
                    onSuccess(str3);
                } else {
                    LiveshowImManager liveshowImManager = LiveshowImManager.this;
                    liveshowImManager.callbackInvokeFail(liveshowImManager.iLiveshowImCallback, LiveshowResult.error_im_join);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logout() {
        YIMClient.getInstance().logout(new YIMEventCallback.OperationCallback() { // from class: com.sqwan.liveshow.im.LiveshowImManager.2
            @Override // com.youme.imsdk.callback.YIMEventCallback.OperationCallback
            public void onSuccess() {
                LogUtil.i(LiveshowImManager.this.TAG, "用户:  已经退出登录");
                onResult();
            }

            @Override // com.youme.imsdk.callback.YIMEventCallback.OperationCallback
            public void onFailed(int i) {
                LogUtil.i(LiveshowImManager.this.TAG, "用户:  已经退出登录，但是可能本来就没有登录");
                onResult();
            }

            public void onResult() {
                LiveshowImManager.this.release();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void joinChatRoom(String str) {
        YIMClient.getInstance().joinChatRoom(str, new YIMEventCallback.ResultCallback<ChatRoom>() { // from class: com.sqwan.liveshow.im.LiveshowImManager.3
            @Override // com.youme.imsdk.callback.YIMEventCallback.ResultCallback
            public void onSuccess(ChatRoom chatRoom) {
                LogUtil.i(LiveshowImManager.this.TAG, "进入频道 onSuccess " + chatRoom.groupId);
                LiveshowImManager.this.chatRoomId = chatRoom.groupId;
                LiveshowImManager liveshowImManager = LiveshowImManager.this;
                liveshowImManager.callbackInvokeSuccess(liveshowImManager.iLiveshowImCallback, null, LiveshowResult.success_im_join);
            }

            @Override // com.youme.imsdk.callback.YIMEventCallback.ResultCallback
            public void onFailed(int i, ChatRoom chatRoom) {
                LogUtil.e(LiveshowImManager.this.TAG, "进入频道失败 onFailed errorCode " + i);
                LiveshowImManager liveshowImManager = LiveshowImManager.this;
                liveshowImManager.callbackInvokeFail(liveshowImManager.iLiveshowImCallback, LiveshowResult.error_im_join);
            }
        });
    }

    private void leaveChatRoom(String str) {
        YIMClient.getInstance().leaveChatRoom(str, new YIMEventCallback.ResultCallback<ChatRoom>() { // from class: com.sqwan.liveshow.im.LiveshowImManager.4
            @Override // com.youme.imsdk.callback.YIMEventCallback.ResultCallback
            public void onSuccess(ChatRoom chatRoom) {
                LogUtil.i(LiveshowImManager.this.TAG, "离开频道 onSuccess " + chatRoom);
                onResult();
            }

            @Override // com.youme.imsdk.callback.YIMEventCallback.ResultCallback
            public void onFailed(int i, ChatRoom chatRoom) {
                LogUtil.e(LiveshowImManager.this.TAG, "离开频道 onFailed errorCode " + i);
                onResult();
            }

            public void onResult() {
                LiveshowImManager.this.chatRoomId = "";
                LiveshowImManager.this.logout();
            }
        });
    }

    public void sendTextMessage(String str, final YIMEventCallback.ResultCallback<String> resultCallback) {
        if (!joinedRoom() || TextUtils.isEmpty(str)) {
            LogUtil.e(this.TAG, "not joinRoom");
            return;
        }
        IMEngine iMEngine = new IMEngine();
        iMEngine.getClass();
        IMEngine.IntegerVal integerVal = iMEngine.new IntegerVal();
        integerVal.setValue(1);
        final String filterText = YIMClient.getInstance().getFilterText(str, integerVal);
        if (!TextUtils.equals(str, filterText)) {
            if (resultCallback != null) {
                resultCallback.onFailed(0, "消息包含敏感内容，发送失败");
                return;
            }
            return;
        }
        ImMsgChatExtBean imMsgChatExtBean = new ImMsgChatExtBean(getUsernick(), filterText);
        String json = new Gson().toJson(imMsgChatExtBean);
        LogUtil.i(this.TAG, "imMsgChatExtBean " + imMsgChatExtBean);
        LogUtil.i(this.TAG, "attachParam " + json);
        YIMClient.getInstance().sendTextMessage(this.chatRoomId, 2, filterText, json, new YIMEventCallback.ResultCallback<SendMessage>() { // from class: com.sqwan.liveshow.im.LiveshowImManager.5
            @Override // com.youme.imsdk.callback.YIMEventCallback.ResultCallback
            public void onSuccess(SendMessage sendMessage) {
                LogUtil.i(LiveshowImManager.this.TAG, "发送消息成功: " + filterText);
                LiveshowTrackManager.getInstance().commentsAction(LiveshowManager.getInstance().getRoomId(), LiveshowManager.getInstance().getAnchorId());
                YIMEventCallback.ResultCallback resultCallback2 = resultCallback;
                if (resultCallback2 != null) {
                    resultCallback2.onSuccess(filterText);
                }
            }

            @Override // com.youme.imsdk.callback.YIMEventCallback.ResultCallback
            public void onFailed(int i, SendMessage sendMessage) {
                LogUtil.i(LiveshowImManager.this.TAG, "发送消息失败:" + filterText + " errorcode " + i);
                YIMEventCallback.ResultCallback resultCallback2 = resultCallback;
                if (resultCallback2 != null) {
                    resultCallback2.onFailed(i, "发送消息失败");
                }
            }
        });
    }

    public void test() {
        ILiveshowImCallback iLiveshowImCallback = this.iLiveshowImCallback;
        if (iLiveshowImCallback != null) {
            iLiveshowImCallback.test();
        }
    }

    public void stopTask() {
        ILiveshowImCallback iLiveshowImCallback = this.iLiveshowImCallback;
        if (iLiveshowImCallback != null) {
            iLiveshowImCallback.stopTask();
        }
    }

    public void startTask() {
        ILiveshowImCallback iLiveshowImCallback = this.iLiveshowImCallback;
        if (iLiveshowImCallback != null) {
            iLiveshowImCallback.startTask();
        }
    }
}
