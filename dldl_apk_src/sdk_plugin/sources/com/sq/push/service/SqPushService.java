package com.sq.push.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sq.push.service.HttpManager;
import com.sq.push.service.IPush;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class SqPushService {
    private static volatile SqPushService sInstance;
    private HttpManager mHttpManager;
    private volatile IPush mPush;
    private String mRoleId;
    private String mToken;
    private final IPush.TokenListener mTokenListener = new IPush.TokenListener() { // from class: com.sq.push.service.SqPushService.1
        public void onReceiveToken(String token) {
            if (TextUtils.isEmpty(token)) {
                return;
            }
            PushLog.i("接收token: " + token);
            SqPushService.this.mToken = token;
            SqPushService.this.tryPushToken();
        }
    };
    private String mUid;

    public static SqPushService getInstance() {
        if (sInstance == null) {
            synchronized (SqPushService.class) {
                if (sInstance == null) {
                    sInstance = new SqPushService();
                }
            }
        }
        return sInstance;
    }

    private SqPushService() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IPush get() {
        if (this.mPush == null) {
            synchronized (SqPushService.class) {
                if (this.mPush == null) {
                    this.mPush = createPushImpl();
                }
            }
        }
        return this.mPush;
    }

    private static IPush createPushImpl() {
        try {
            return (IPush) SqPushService.class.getClassLoader().loadClass("com.sq.push.service.getui.GeTuiPush").newInstance();
        } catch (Throwable unused) {
            PushLog.w("无法创建推送实例, 使用FakePush");
            return new FakePush();
        }
    }

    public void onCreate(Activity activity, Bundle bundle) {
        PushLog.v("onCreate: " + activity);
        get().onCreate(activity, bundle);
    }

    public void onNewIntent(Activity activity, Intent intent) {
        PushLog.v("onNewIntent: " + activity);
        get().onNewIntent(activity, intent);
    }

    public void init(final Context context, GameConfig config, IHttpRequester request) {
        PushLog.d("初始化");
        HttpManager httpManager = new HttpManager(context, request, config.pid, config.gid);
        this.mHttpManager = httpManager;
        httpManager.requestConfig(new HttpManager.PushConfigCallback() { // from class: com.sq.push.service.SqPushService.2
            @Override // com.sq.push.service.HttpManager.PushConfigCallback
            public void onSuccess(boolean enable) {
                if (enable) {
                    PushLog.i("初始化推送");
                    SqPushService.this.get().init(context);
                    SqPushService.this.get().setTokenListener(SqPushService.this.mTokenListener);
                    return;
                }
                PushLog.w("不启用推送");
            }

            @Override // com.sq.push.service.HttpManager.PushConfigCallback
            public void onFailed() {
                PushLog.w("配置接口异常, 不启用推送");
            }
        });
    }

    public void sendFeedback(Context context, Intent intent) {
        PushLog.d("sendFeedback: " + context);
        get().sendFeedback(context, intent);
    }

    public void sendFeedback(Context context, Map<String, String> params) {
        PushLog.d("sendFeedback: " + context + ", " + params);
        get().sendFeedback(context, params);
    }

    public void setTransmitMessageListener(IPush.TransmitMessageListener listener) {
        PushLog.d("setTransmitMessageListener: " + listener);
        get().setTransmitMessageListener(listener);
    }

    public IPush.TransmitMessageListener getTransmitMessageListener() {
        return get().getTransmitMessageListener();
    }

    public void setUserInfo(String uid, String roleId) {
        PushLog.i("设置uid: " + uid + ", 角色id: " + roleId);
        this.mUid = uid;
        this.mRoleId = roleId;
        tryPushToken();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryPushToken() {
        if (TextUtils.isEmpty(this.mToken) || TextUtils.isEmpty(this.mUid) || TextUtils.isEmpty(this.mRoleId)) {
            return;
        }
        this.mHttpManager.pushToken(this.mToken, this.mUid, this.mRoleId);
    }

    public static class GameConfig {
        final String gid;
        final String pid;

        public GameConfig(String pid, String gid) {
            this.pid = pid;
            this.gid = gid;
        }
    }
}
