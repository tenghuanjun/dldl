package com.igexin.assist.sdk;

import android.content.Context;
import com.igexin.assist.control.AbstractPushManager;
import com.igexin.push.core.e;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class AssistPushManager {
    private static final String a = "Assist_OtherPushManager";
    private AbstractPushManager b;

    static class a {
        private static final AssistPushManager a = new AssistPushManager(0);

        private a() {
        }
    }

    private AssistPushManager() {
    }

    /* synthetic */ AssistPushManager(byte b) {
        this();
    }

    public static AssistPushManager getInstance() {
        return a.a;
    }

    public static String getToken() {
        return e.G;
    }

    public void initialize(Context context) {
        this.b = com.igexin.assist.sdk.a.a().a(context);
    }

    public void register(Context context) {
        AbstractPushManager abstractPushManager = this.b;
        if (abstractPushManager != null) {
            abstractPushManager.register(context);
        }
    }

    public void setSilentTime(Context context, int i, int i2) {
        AbstractPushManager abstractPushManager = this.b;
        if (abstractPushManager != null) {
            abstractPushManager.setSilentTime(context, i, i2);
        }
    }

    public void turnOffPush(Context context) {
        AbstractPushManager abstractPushManager = this.b;
        if (abstractPushManager != null) {
            abstractPushManager.turnOffPush(context);
        }
    }

    public void turnOnPush(Context context) {
        AbstractPushManager abstractPushManager = this.b;
        if (abstractPushManager != null) {
            abstractPushManager.turnOnPush(context);
        }
    }

    public void unregister(Context context) {
        AbstractPushManager abstractPushManager = this.b;
        if (abstractPushManager != null) {
            abstractPushManager.unregister(context);
        }
    }
}
