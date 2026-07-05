package cn.thinkingdata.android.aop.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import cn.thinkingdata.android.utils.TDLog;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TAPushProcess {
    private static final int GT_PUSH_MSG = 100;
    private static TAPushProcess INSTANCE = null;
    private static final String TAG = "ThinkingAnalytics.process";
    private final Map<String, TANotificationInfo> mGeTuiMap = new HashMap();
    private final Handler mPushHandler;

    private TAPushProcess() {
        HandlerThread handlerThread = new HandlerThread("TA.PushThread");
        handlerThread.start();
        this.mPushHandler = new Handler(handlerThread.getLooper()) { // from class: cn.thinkingdata.android.aop.push.TAPushProcess.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 100) {
                    try {
                        String str = (String) message.obj;
                        if (TextUtils.isEmpty(str) || !TAPushProcess.this.mGeTuiMap.containsKey(str)) {
                            return;
                        }
                        TAPushProcess.this.mGeTuiMap.remove(str);
                    } catch (Exception e) {
                        TDLog.e(TAPushProcess.TAG, e.getMessage());
                    }
                }
            }
        };
    }

    public static synchronized TAPushProcess getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TAPushProcess();
        }
        return INSTANCE;
    }

    public void onNotificationClick(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            boolean z = context instanceof Activity;
        } catch (Exception e) {
            TDLog.e(TAG, e.getMessage());
        }
    }

    void trackGTDelayed(String str, String str2, String str3) {
        try {
            Message messageObtain = Message.obtain();
            messageObtain.what = 100;
            messageObtain.obj = str;
            this.mGeTuiMap.put(str, new TANotificationInfo(str2, str3, System.currentTimeMillis()));
            this.mPushHandler.sendMessageDelayed(messageObtain, 200L);
        } catch (Exception e) {
            TDLog.e(TAG, e.getMessage());
        }
    }

    void trackGeTuiReceiveMessageData(String str, String str2) {
        try {
            if (this.mPushHandler.hasMessages(100) && this.mGeTuiMap.containsKey(str2)) {
                this.mPushHandler.removeMessages(100);
                this.mGeTuiMap.get(str2);
                this.mGeTuiMap.remove(str2);
                TDLog.i(TAG, " onGeTuiReceiveMessage:msg id : " + str2);
            }
        } catch (Exception e) {
            TDLog.e(TAG, e.getMessage());
        }
    }
}
