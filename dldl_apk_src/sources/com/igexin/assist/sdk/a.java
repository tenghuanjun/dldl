package com.igexin.assist.sdk;

import android.content.Context;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.igexin.assist.control.AbstractPushManager;
import com.igexin.assist.util.AssistUtils;
import com.igexin.b.a.c.a.d;
import com.igexin.push.core.e.e;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    public static final String a = "AssistMangerFactory";
    private static a d;
    public AbstractPushManager b;
    private static final String c = "com.igexin.assist.control." + AssistUtils.getDeviceBrand() + ".ManufacturePushManager";
    private static final String[] e = {"com.igexin.assist.control.xiaomi.MiuiPushManager", "com.igexin.assist.control.meizu.FlymePushManager", "com.igexin.assist.control.huawei.HmsPushManager", "com.igexin.assist.control.oppo.OppoPushManager", "com.igexin.assist.control.vivo.VivoPushManager", "com.igexin.assist.control.st.SmartisanPushManager"};

    public static a a() {
        if (d == null) {
            synchronized (AbstractPushManager.class) {
                if (d == null) {
                    d = new a();
                }
            }
        }
        return d;
    }

    private void b(Context context) {
        AbstractPushManager abstractPushManager = this.b;
        if (abstractPushManager != null && abstractPushManager.isSupport()) {
            if (this.b.getBrandCode().equals("3")) {
                try {
                    Class.forName("com.xiaomi.mipush.sdk.MiPushClient").getDeclaredMethod("clearNotification", Context.class).invoke(null, context);
                } catch (Throwable th) {
                    com.igexin.b.a.c.a.a("AssistMangerFactory | cancelAllAssistNotification() err " + th.toString(), new Object[0]);
                }
                com.igexin.b.a.c.a.a("AssistMangerFactory | cancelAllAssistNotification() XM ", new Object[0]);
                return;
            }
            if (this.b.getBrandCode().equals("4")) {
                try {
                    Class.forName("com.meizu.cloud.pushsdk.PushManager").getDeclaredMethod("clearNotification", Context.class).invoke(null, context);
                } catch (Throwable th2) {
                    com.igexin.b.a.c.a.a("AssistMangerFactory | cancelAllAssistNotification() err " + th2.toString(), new Object[0]);
                }
                com.igexin.b.a.c.a.a("AssistMangerFactory | cancelAllAssistNotification() MZ ", new Object[0]);
            }
        }
    }

    private static void c() {
        for (String str : e) {
            try {
                Class.forName(str);
                d.a().a("UnSupport plugin [" + str + "]. Please change plugin to 3.0.");
                return;
            } catch (Throwable unused) {
            }
        }
    }

    private static void c(Context context) {
        try {
            Class.forName("com.xiaomi.mipush.sdk.MiPushClient").getDeclaredMethod("clearNotification", Context.class).invoke(null, context);
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("AssistMangerFactory | cancelAllAssistNotification() err " + th.toString(), new Object[0]);
        }
        com.igexin.b.a.c.a.a("AssistMangerFactory | cancelAllAssistNotification() XM ", new Object[0]);
    }

    private String d() {
        AbstractPushManager abstractPushManager = this.b;
        return abstractPushManager == null ? "" : abstractPushManager.getBrandCode();
    }

    private static void d(Context context) {
        try {
            Class.forName("com.meizu.cloud.pushsdk.PushManager").getDeclaredMethod("clearNotification", Context.class).invoke(null, context);
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("AssistMangerFactory | cancelAllAssistNotification() err " + th.toString(), new Object[0]);
        }
        com.igexin.b.a.c.a.a("AssistMangerFactory | cancelAllAssistNotification() MZ ", new Object[0]);
    }

    public final AbstractPushManager a(Context context) {
        if (com.igexin.push.config.d.Q.contains(AssistUtils.getDeviceBrand().toLowerCase())) {
            com.igexin.b.a.c.a.a("AssistMangerFactory|getPushManager = null, setToken = false", new Object[0]);
            e.a().b(ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_FALSE);
            return null;
        }
        try {
            this.b = (AbstractPushManager) Class.forName(c).getConstructor(Context.class).newInstance(context);
        } catch (Exception e2) {
            d.a().a(AssistUtils.getDeviceBrand().toUpperCase() + " PushManager = null");
            com.igexin.b.a.c.a.a("AssistMangerFactory|ManufacturePushManager = null", new Object[0]);
            e.a().b(ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_FALSE);
            if (e2 instanceof ClassNotFoundException) {
                c();
            }
        }
        StringBuilder sb = new StringBuilder("AssistMangerFactory|ManufacturePushManager is null = ");
        sb.append(this.b == null);
        com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
        return this.b;
    }

    public final boolean b() {
        if (com.igexin.push.config.d.Q.contains(AssistUtils.getDeviceBrand().toLowerCase())) {
            return false;
        }
        AbstractPushManager abstractPushManager = this.b;
        if (abstractPushManager != null) {
            return abstractPushManager.isSupport();
        }
        return true;
    }
}
