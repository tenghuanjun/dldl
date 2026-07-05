package com.jiguang.h5;

import android.content.Context;
import com.alipay.sdk.util.e;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.jiguang.main.MainActivity;
import com.mobile.auth.BuildConfig;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class CustomGetuiIntentService extends GTIntentService {
    private static final String TAG = SdkMgr.TAG;
    public static String s_cid = null;

    @Override // com.igexin.sdk.GTIntentService
    public void onReceiveServicePid(Context context, int i) {
        SdkMgr.Logd("onReceiveServicePid -> " + i);
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onReceiveMessageData(Context context, GTTransmitMessage gTTransmitMessage) {
        String appid = gTTransmitMessage.getAppid();
        String taskId = gTTransmitMessage.getTaskId();
        String messageId = gTTransmitMessage.getMessageId();
        byte[] payload = gTTransmitMessage.getPayload();
        String pkgName = gTTransmitMessage.getPkgName();
        String clientId = gTTransmitMessage.getClientId();
        boolean zSendFeedbackMessage = PushManager.getInstance().sendFeedbackMessage(context, taskId, messageId, PushConsts.MIN_FEEDBACK_ACTION);
        StringBuilder sb = new StringBuilder();
        sb.append("call sendFeedbackMessage = ");
        sb.append(zSendFeedbackMessage ? "success" : e.a);
        SdkMgr.Logd(sb.toString());
        SdkMgr.Logd("onReceiveMessageData -> appid = " + appid + "\ntaskid = " + taskId + "\nmessageid = " + messageId + "\npkg = " + pkgName + "\ncid = " + clientId);
        if (payload == null) {
            SdkMgr.Loge("receiver payload = null");
        } else {
            SdkMgr.Logd("receiver payload = " + new String(payload));
        }
        SdkMgr.Logd("----------------------------------------------------------------------------------------------");
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onReceiveClientId(Context context, final String str) {
        MainActivity.ma.runOnUiThread(new Runnable() { // from class: com.jiguang.h5.CustomGetuiIntentService.1
            @Override // java.lang.Runnable
            public void run() {
                SdkMgr.Logd("onReceiveClientId -> clientid = " + str);
                CustomGetuiIntentService.s_cid = str;
                SdkMgr sdkMgr = SdkMgr.Mgr;
                SdkMgr.CheckCid();
            }
        });
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onReceiveOnlineState(Context context, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("onReceiveOnlineState -> ");
        sb.append(z ? BuildConfig.FLAVOR_env : "offline");
        SdkMgr.Logd(sb.toString());
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onReceiveCommandResult(Context context, GTCmdMessage gTCmdMessage) {
        SdkMgr.Logd("onReceiveCommandResult -> " + gTCmdMessage);
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onNotificationMessageArrived(Context context, GTNotificationMessage gTNotificationMessage) {
        SdkMgr.Logd("onNotificationMessageArrived -> " + gTNotificationMessage);
    }

    @Override // com.igexin.sdk.GTIntentService
    public void onNotificationMessageClicked(Context context, GTNotificationMessage gTNotificationMessage) {
        SdkMgr.Logd("onNotificationMessageClicked -> " + gTNotificationMessage);
    }
}
