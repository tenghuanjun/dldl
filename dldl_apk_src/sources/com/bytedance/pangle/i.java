package com.bytedance.pangle;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.bytedance.pangle.apm.ApmUtils;
import com.bytedance.pangle.log.IZeusReporter;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.provider.ContentProviderManager;
import com.bytedance.pangle.servermanager.MainServerManager;
import com.bytedance.pangle.util.FieldUtils;
import com.bytedance.pangle.util.MethodUtils;
import com.bytedance.pangle.util.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class i {
    private static volatile i d;
    boolean a;
    public final List<ZeusPluginStateListener> b = new CopyOnWriteArrayList();
    final List<ZeusPluginEventCallback> c = new ArrayList();
    private final Handler e = new Handler(Looper.getMainLooper());

    public static i a() {
        if (d == null) {
            synchronized (i.class) {
                if (d == null) {
                    d = new i();
                }
            }
        }
        return d;
    }

    final synchronized void a(Application application, boolean z) {
        if (this.a) {
            ZeusLogger.w(ZeusLogger.TAG_INIT, "ZeusManager zeus has been inited!");
            return;
        }
        a(3000, 0, null, -1, null);
        Zeus.setAppContext(application);
        GlobalParam globalParam = GlobalParam.getInstance();
        globalParam.init();
        if (application == null) {
            GlobalParam.getInstance().getReporter().saveRecord(IZeusReporter.ZEUS_STAGE_ERROR, "context is null");
            throw new IllegalArgumentException("context must be not null !!!");
        }
        ZeusLogger.setDebug(globalParam.isDebug());
        ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusManager init, context = " + application + ", hParam = " + globalParam);
        com.bytedance.pangle.d.e.a(new Runnable() { // from class: com.bytedance.pangle.i.1
            @Override // java.lang.Runnable
            public final void run() {
                j.b();
            }
        });
        if (GlobalParam.getInstance().isPostBgDexOptByInit()) {
            com.bytedance.pangle.e.f.a();
        }
        if (z) {
            Zeus.onPrivacyAgreed();
        }
        com.bytedance.pangle.c.b bVarA = com.bytedance.pangle.c.b.a();
        com.bytedance.pangle.c.a aVar = new com.bytedance.pangle.c.a() { // from class: com.bytedance.pangle.i.2
            @Override // com.bytedance.pangle.c.a
            public final void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                if (jSONObject2 == null) {
                    jSONObject2 = new JSONObject();
                }
                if (jSONObject3 == null) {
                    jSONObject3 = new JSONObject();
                }
                String strA = null;
                if (Zeus.getAppApplication() != null) {
                    Zeus.getAppApplication();
                    strA = com.bytedance.pangle.d.d.a(com.bytedance.pangle.d.d.a());
                }
                String packageName = Zeus.getAppApplication() != null ? Zeus.getAppApplication().getPackageName() : "";
                if (strA == null) {
                    strA = "unknown";
                }
                try {
                    jSONObject.putOpt(ContentProviderManager.PLUGIN_PROCESS_NAME, com.bytedance.pangle.log.b.a(strA));
                    jSONObject.putOpt("host_package_name", packageName);
                    Plugin plugin = Zeus.getPlugin(jSONObject.optString("plugin_package_name", ""), false);
                    jSONObject.putOpt("plugin_api_version", com.bytedance.pangle.log.b.a(Integer.valueOf(plugin != null ? plugin.getApiVersionCode() : -1)));
                    jSONObject.putOpt("zeus_sdk_version", com.bytedance.pangle.log.b.a("0.0.1-beta.4200.115-bugfix.24-pangle"));
                    ZeusLogger.v(ZeusLogger.TAG_REPORTER, "eventName: " + str + "\ncategoryData:" + jSONObject.toString(1) + "\nmetricData:" + jSONObject2.toString(1) + "\nlogExtrData:" + jSONObject3.toString(1));
                    IZeusReporter reporter = GlobalParam.getInstance().getReporter();
                    if (reporter != null) {
                        JSONObject jSONObject4 = new JSONObject();
                        Iterator<String> itKeys = jSONObject.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            jSONObject4.putOpt(next, jSONObject.opt(next));
                        }
                        Iterator<String> itKeys2 = jSONObject2.keys();
                        while (itKeys2.hasNext()) {
                            String next2 = itKeys2.next();
                            jSONObject4.putOpt(next2, jSONObject2.opt(next2));
                        }
                        Iterator<String> itKeys3 = jSONObject3.keys();
                        while (itKeys3.hasNext()) {
                            String next3 = itKeys3.next();
                            jSONObject4.putOpt(next3, jSONObject3.opt(next3));
                        }
                        reporter.report(str, jSONObject4);
                    }
                    ApmUtils.getApmInstance().monitorEvent(str, jSONObject, jSONObject2, jSONObject3);
                } catch (JSONException e) {
                    ZeusLogger.w(ZeusLogger.TAG_REPORTER, e.getMessage(), e);
                }
            }
        };
        synchronized (bVarA.a) {
            bVarA.a.add(aVar);
        }
        if (!globalParam.isCloseFlipped()) {
            b.a();
        }
        if (Build.VERSION.SDK_INT == 29) {
            com.bytedance.pangle.d.e.a(new Runnable() { // from class: com.bytedance.pangle.i.3
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        GlobalParam.getInstance().getReporter().saveRecord(IZeusReporter.ZEUS_STAGE_COMMON, "invoke SystemConfig");
                        MethodUtils.invokeStaticMethod(Class.forName("com.android.server.SystemConfig"), "getInstance", new Object[0]);
                    } catch (Throwable th) {
                        GlobalParam.getInstance().getReporter().saveRecord(IZeusReporter.ZEUS_STAGE_ERROR, "invoke SystemConfig error : ".concat(String.valueOf(th)));
                    }
                }
            });
        }
        if (com.bytedance.pangle.util.i.j()) {
            try {
                GlobalParam.getInstance().getReporter().saveRecord(IZeusReporter.ZEUS_STAGE_COMMON, "invoke disableApiWarning");
                FieldUtils.writeField(com.bytedance.pangle.d.a.a(), "mHiddenApiWarningShown", Boolean.TRUE);
                ZeusLogger.w(ZeusLogger.TAG_INIT, "ZeusManager disableApiWarningShownForAndroidP, true");
            } catch (Exception e) {
                ZeusLogger.errReport(ZeusLogger.TAG_INIT, "disableApiWarningShownForAndroidP failed", e);
            }
        }
        b();
        ContentProviderManager.getInstance().initSystemContentProviderInfo();
        if (!globalParam.closeHookHuaweiOnInit()) {
            Zeus.hookHuaWeiVerifier(application);
        }
        this.a = true;
        a(ZeusPluginEventCallback.EVENT_FINISH_INITIALIZATION, 0, null, -1, null);
    }

    private static void b() {
        GlobalParam.getInstance().getReporter().saveRecord(IZeusReporter.ZEUS_STAGE_SERVER_MANAGER, "start");
        try {
            PackageInfo packageInfo = Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 8);
            if (packageInfo != null && packageInfo.providers != null) {
                for (ProviderInfo providerInfo : packageInfo.providers) {
                    if (!TextUtils.isEmpty(providerInfo.authority)) {
                        if (providerInfo.authority.contains(Zeus.getAppApplication().getPackageName() + ".pangle.servermanager.")) {
                            String str = (TextUtils.isEmpty(providerInfo.processName) || !providerInfo.processName.contains(":")) ? "main" : providerInfo.processName.split(":")[1];
                            if (Zeus.getServerManagerHashMap().get(str) == null || !TextUtils.equals(str, "main") || !TextUtils.equals(providerInfo.name, MainServerManager.class.getName())) {
                                Zeus.getServerManagerHashMap().put(str, providerInfo);
                            }
                        }
                    }
                }
                GlobalParam.getInstance().getReporter().saveRecord(IZeusReporter.ZEUS_STAGE_SERVER_MANAGER, "finish");
                return;
            }
            ZeusLogger.errReport(ZeusLogger.TAG_INIT, "initServerManager failed. packageInfo:".concat(String.valueOf(packageInfo)));
        } catch (Exception e) {
            GlobalParam.getInstance().getReporter().saveRecord(IZeusReporter.ZEUS_STAGE_SERVER_MANAGER, "error:".concat(String.valueOf(e)));
            throw new RuntimeException(e);
        }
    }

    public final void a(final int i, final int i2, final String str, final int i3, final Throwable th) {
        for (final Object obj : c()) {
            this.e.post(new Runnable() { // from class: com.bytedance.pangle.i.4
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        ((ZeusPluginEventCallback) obj).onPluginEvent(i, i2, str, i3, th);
                    } catch (Throwable unused) {
                    }
                }
            });
        }
    }

    private Object[] c() {
        Object[] array;
        synchronized (this.c) {
            array = !this.c.isEmpty() ? this.c.toArray() : null;
        }
        return array == null ? new Object[0] : array;
    }
}
