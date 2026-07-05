package com.bytedance.sdk.openadsdk.api.plugin;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.bykv.vk.openvk.api.proto.EventListener;
import com.bykv.vk.openvk.api.proto.Result;
import com.bykv.vk.openvk.api.proto.ValueSet;
import com.bytedance.pangle.GlobalParam;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusPluginInstallListener;
import com.bytedance.pangle.ZeusPluginStateListener;
import com.bytedance.pangle.download.PluginDownloadBean;
import com.bytedance.pangle.log.IZeusLogger;
import com.bytedance.pangle.log.IZeusReporter;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.TTPluginListener;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class e {
    private static volatile BaseDexClassLoader d;
    private static volatile TTPluginListener l;
    private final Context i;
    private static final String a = "next" + File.separator;
    private static final HashMap<String, TTPluginListener> b = new HashMap<>();
    private static final HashMap<String, Handler> c = new HashMap<>();
    private static volatile e e = null;
    private final CountDownLatch f = new CountDownLatch(1);
    private volatile boolean g = false;
    private volatile String h = com.igexin.push.a.i;
    private JSONObject j = new JSONObject();
    private EventListener k = null;

    private e(Context context) {
        this.i = context.getApplicationContext();
        try {
            GlobalParam.getInstance().closeHookHuaweiOnInit(true);
        } catch (Throwable th) {
            com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", "Unexpected error for closeHookHuaweiOnInit.", th);
        }
        b(context.getApplicationContext());
    }

    public static e a(Context context) {
        if (e == null) {
            synchronized (e.class) {
                if (e == null) {
                    e = new e(context);
                }
            }
        }
        return e;
    }

    public JSONObject a() {
        return this.j;
    }

    public BaseDexClassLoader a(d dVar) throws Exception {
        return a(dVar, 60000);
    }

    public BaseDexClassLoader a(d dVar, int i) throws Exception {
        if (!this.g) {
            com.bytedance.sdk.openadsdk.api.c.d("TTPluginManager", "Zeus init failed.");
            throw new com.bytedance.sdk.openadsdk.api.plugin.a(4, this.h);
        }
        if (!Zeus.isPluginInstalled("com.byted.pangle")) {
            try {
                com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", "wait start");
                this.f.await(i, TimeUnit.MILLISECONDS);
                com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", "wait done");
                dVar.b("wait_install_cost");
            } catch (Exception unused) {
                com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", "Install wait time out");
                throw new com.bytedance.sdk.openadsdk.api.plugin.a(8, "install wait timeout");
            }
        }
        boolean z = false;
        if (Zeus.isPluginLoaded("com.byted.pangle") || Zeus.loadPlugin("com.byted.pangle")) {
            d = Zeus.getPlugin("com.byted.pangle").mClassLoader;
            z = true;
        }
        dVar.b("get_classloader_cost");
        Zeus.installFromDownloadDir();
        if (d == null) {
            if (this.f.getCount() != 0) {
                com.bytedance.sdk.openadsdk.api.c.d("TTPluginManager", "Install wait time out");
                throw new com.bytedance.sdk.openadsdk.api.plugin.a(8, "install wait timeout");
            }
            if (z) {
                com.bytedance.sdk.openadsdk.api.c.d("TTPluginManager", "Get null after load");
                throw new com.bytedance.sdk.openadsdk.api.plugin.a(9, "Get null after load");
            }
        }
        dVar.b("get_classloader_done");
        return d;
    }

    private void b(Context context) {
        try {
            IZeusReporter iZeusReporter = new IZeusReporter() { // from class: com.bytedance.sdk.openadsdk.api.plugin.e.1
                @Override // com.bytedance.pangle.log.IZeusReporter
                public void report(String str, JSONObject jSONObject) {
                    if ("load_finish".equals(str) && jSONObject != null && "com.byted.pangle".endsWith(jSONObject.optString("plugin_package_name"))) {
                        try {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put(MediationConstant.EXTRA_DURATION, jSONObject.opt(MediationConstant.EXTRA_DURATION));
                            jSONObject2.put(com.igexin.push.core.b.Z, jSONObject.opt(com.igexin.push.core.b.Z));
                            e.this.j.put("zeus", jSONObject2);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    if (TTAdSdk.isInitSuccess()) {
                        com.bytedance.sdk.openadsdk.api.plugin.c.a(str, jSONObject);
                    } else {
                        com.bytedance.sdk.openadsdk.api.plugin.c.b(str, jSONObject);
                    }
                }

                @Override // com.bytedance.pangle.log.IZeusReporter
                public void saveRecord(String str, String str2) {
                    com.bytedance.sdk.openadsdk.api.plugin.b.a(str, str2);
                }
            };
            GlobalParam globalParam = GlobalParam.getInstance();
            globalParam.setReporter(iZeusReporter);
            globalParam.setCheckPermission(false);
            globalParam.setDownloadDir(c(context));
            globalParam.setLogger(new b());
            globalParam.setSignature("com.byted.pangle", "MIIDfTCCAmWgAwIBAgIEfRwYPjANBgkqhkiG9w0BAQsFADBvMQswCQYDVQQGEwJDTjEQMA4GA1UECBMHQmVpamluZzEQMA4GA1UEBxMHQmVpamluZzESMBAGA1UEChMJQnl0ZURhbmNlMQ8wDQYDVQQLEwZQYW5nbGUxFzAVBgNVBAMTDkNodWFuIFNoYW4gSmlhMB4XDTIxMTEwODA2MjQzOVoXDTQ2MTEwMjA2MjQzOVowbzELMAkGA1UEBhMCQ04xEDAOBgNVBAgTB0JlaWppbmcxEDAOBgNVBAcTB0JlaWppbmcxEjAQBgNVBAoTCUJ5dGVEYW5jZTEPMA0GA1UECxMGUGFuZ2xlMRcwFQYDVQQDEw5DaHVhbiBTaGFuIEppYTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAIBKeRL+4mfCn1SLYv6OemfwwItkjlLPyqOEugkV6lanFTcZgLwEl5LIkL0y28UncPtMX1Mii6DzCdJ/plw7S9+RT/hYDneu339IKWojaU2qai/5FokHlQ0MMnYl5yry00ghVPsl1u+03cQA2ZnjIMiFhrBJpQzHt7IYvq2aEEMBcY8uT7iFoBI848e1mL1joVS2z02C3NliP7ZNARkXH+rTQAlCJulT5IZk+V/PTaKqzgNrkhsKh0/tBmU7m8u79x/xpgGsE19H18AgS4P/9/MDCRe2Z35boZeccaUy2MXCwv3djzUcDk3rRzQPYzdpyyRnrFMuhiKesc5VHgUMs9kCAwEAAaMhMB8wHQYDVR0OBBYEFENENrNWGzc2WhxdvhoMDs57U70zMA0GCSqGSIb3DQEBCwUAA4IBAQAHqDCrmvyBBmIGXwuL1rwS/Qv9ZJIZykBIaNMm+H1IfitCl4yXd9N2n+PjE0UZtxZ21UZOt9wAr+RFiSl5YRXqpt7WLARTy4YW3RiQ+wiL7bshzeSYBoSiC427Bfeq0WjwY0/jHlr8uouppyJOz++6U9hrYX2EW/6UjH5XlWiKQJ6b2ZzPcP8Xpg/TJn4tWvXJP6jw9kRRP2GmMttY78leWQst2QEZILmWJubXRLPj9O+qx2uP9oGTD4sc1vb9hzkOHBIHzGaalqLFbbGaeFpLFHoGTsnOfPTwUVKDZYmxbkcmR1bp7eYOW+nSQNMLn0FjDewZl5l37Sa/gz0WVHon");
            globalParam.setSignature("com.byted.csj.ext", "MIIDezCCAmOgAwIBAgIENkE1KDANBgkqhkiG9w0BAQsFADBtMQswCQYDVQQGEwI4NjEQMA4GA1UECBMHYmVpamluZzEQMA4GA1UEBxMHYmVpamluZzESMBAGA1UEChMJYnl0ZWRhbmNlMRIwEAYDVQQLEwlieXRlZGFuY2UxEjAQBgNVBAMTCWJ5dGVkYW5jZTAgFw0yMjExMDIwODI3MzlaGA8yMDUwMDMyMDA4MjczOVowbTELMAkGA1UEBhMCODYxEDAOBgNVBAgTB2JlaWppbmcxEDAOBgNVBAcTB2JlaWppbmcxEjAQBgNVBAoTCWJ5dGVkYW5jZTESMBAGA1UECxMJYnl0ZWRhbmNlMRIwEAYDVQQDEwlieXRlZGFuY2UwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCc9Z2F3xxOMX1qTXMy2aPmS9OSkqrp8C8bHwS1hkNVR4umKREuqOn73INNo+R706jaCVnlPwxDwWjtX6H74DE4CveivyM9f2wNC3yIyDW+5j7lW/keTQcOlGLDEJQv4O/6FbB/jNU6epjyNaNIZhgZcvTpgaSixbdyHzRTFmvMh+WovdVK/J9LnHOQ+pmPZj7NB6MQRGMUrPEotLHQca3cmnLrnPAaZQaVoaFE9lOt9syyqEuf361SprNIGDtbkJuX3EqV/QOKWFwZX94IS7ZGSvfyCojcD4kaUSbaSoZC7zEuBb7l69g+ZMrJ/v6wkm01wxsNNssUwF7k6Sp0zubbAgMBAAGjITAfMB0GA1UdDgQWBBSxk+gVdDco1dP65hP67qoKNlMEYDANBgkqhkiG9w0BAQsFAAOCAQEAfosExl/AYEbS2xqHBTHa28cvnp/SElUQuzW6aWLqkfk9cxmFSI/euUV3/eB8RN+U2X47Y05u6+XUxTv0tSSEtyXNawm0qWH8jkR4gZY38YqBChKjhea668oT5X3Uocrw7SYXO/BfI8SKPa0uI/U8Cyl3uctbmmq/pPUkd3mKAy+HgyJoThD6K0oyiADlygngUMVTv6Uvid4qPj/bBnxI+LvVeX4l1dxGqWkiafQW9sz+RbFdge3X2XsSH4eo01BsCwOYEv1lHO2FrbAtFNpnIsSqrERdFaAJZ3tlJmg9bA03png8A2AajEjkhaOhduJB8zkSlvHNpoQMIAS9WtkG/w==");
            globalParam.setSignature("com.byted.live.lite", "MIIDSTCCAjGgAwIBAgIEaLy5tzANBgkqhkiG9w0BAQsFADBVMQswCQYDVQQGEwIxMTEMMAoGA1UECBMDMTExMQ4wDAYDVQQHEwUxMTExMTEMMAoGA1UEChMDMTExMQwwCgYDVQQLEwMxMTExDDAKBgNVBAMTAzExMTAeFw0yMDEyMDMxMjQyMTJaFw00NTExMjcxMjQyMTJaMFUxCzAJBgNVBAYTAjExMQwwCgYDVQQIEwMxMTExDjAMBgNVBAcTBTExMTExMQwwCgYDVQQKEwMxMTExDDAKBgNVBAsTAzExMTEMMAoGA1UEAxMDMTExMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA45E52YdkJm4gPCXZq7KDoM1h6pgSswllC/CwDOmh8pDGvX4ROaYP1vr2biRlXMHg7G0iXpxWVdlTtx+4QFd3dC+cGJQk0f6apGo2n2RpMA0zIsSf0VO1a3GjWLei5INo+4RDdciqJ4jfsoqBIjZETRkky+UU4eO/oyrAwOu4KdMln3Bg3u7eHWU4kMFrXxrRruT3Q/9gzlO90yQa0CZPWVDrk6cGJtJwJGhWm+62S3U8D26HE++eGP7ve83QBDGtKqx7HpCAFWUiYBgXGq12H0amQDkKcPcr/EFCaBlombSgkN0t6zBX80m+wcUPC75IBTmMV/DT2dXcgjZ2I1JSCQIDAQABoyEwHzAdBgNVHQ4EFgQUPDyIeKI0KhZFPHyn36gMMIYrpukwDQYJKoZIhvcNAQELBQADggEBAHkl0DoCRwn+XKsDJE+wGMpBBqUDzL6DSOnJx4SNqb7YZZU0ThcDK7jY4If3QRkvMio6ODrVZc2U/m/Tc3VeMk5h2W2UZRUWHNH3k9Xe0720uL20ZeH2Y6IG4L5HG8kIbTbFtX3gJpPG/xAcez+CzyCFLWQAZt1N+csG0syWkXJ0Nryq8VrgSCyCXD1KzFxrOe+65wtu50Vi68Vlbk7BZe/G8Qm0RhKmxq5BPMBJ4uY3be+03Ba5qC//o1XQHOEAjrJKXcN5wqHdFZTkmuxVyIPogZOzx4JlNl0zOrYGDJxp7aZfKF9FkXQyF7x0Ns3mZEtjx/+flXRzAAU9MDhPr/0=");
            Zeus.registerPluginStateListener(new ZeusPluginStateListener() { // from class: com.bytedance.sdk.openadsdk.api.plugin.e.2
                @Override // com.bytedance.pangle.ZeusPluginStateListener
                public void onPluginStateChange(String str, int i, Object... objArr) {
                    com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", str + " state changed, " + i);
                    if (i != 9 || TextUtils.equals(str, "com.byted.pangle")) {
                        return;
                    }
                    e.this.c(str);
                }
            });
            globalParam.closeBgDex2oat(true);
            Zeus.init((Application) context, true);
            Zeus.registerPluginInstallListener(new ZeusPluginInstallListener() { // from class: com.bytedance.sdk.openadsdk.api.plugin.e.3
                @Override // com.bytedance.pangle.ZeusPluginInstallListener
                public void onPluginInstall(String str, int i, String str2) {
                    if (i == 7) {
                        e.this.b(str, i);
                        return;
                    }
                    if (i == 6) {
                        e.this.b(str, i);
                        if (e.this.k != null && "com.byted.pangle".equals(str)) {
                            e.this.k.onEvent(0, com.bykv.a.a.a.a.a.a().a(true).b());
                        } else {
                            com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", "no main pl");
                        }
                    }
                }
            });
            this.g = true;
        } catch (Throwable th) {
            com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", "Unexpected error for init zeus.", th);
            this.h = th.getMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        Plugin plugin = Zeus.getPlugin(str, false);
        try {
            if (plugin.mClassLoader != null) {
                TTAdSdk.getAdManager().register(com.bykv.a.a.a.a.b.a(3).a(0, 1).a(1, str).a(2, plugin.mClassLoader).b());
            }
        } catch (Throwable th) {
            com.bytedance.sdk.openadsdk.api.c.a("TTPluginManager", "initPluginService failed in " + str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, int i) {
        if ("com.byted.pangle".equals(str) && i == 6) {
            com.bytedance.sdk.openadsdk.api.c.d("TTPluginManager", "notify to end wait");
            this.f.countDown();
        }
        a(i == 6, str);
    }

    private static File c(Context context) {
        return new File(new File(context.getDir("tt_pangle_bykv_file", 0), "pangle_com.byted.pangle"), a);
    }

    public Bundle a(String str, Bundle bundle) {
        String strA = a(str);
        if (!TextUtils.isEmpty(strA)) {
            bundle.putString(PluginConstants.KEY_PLUGIN_VERSION, strA);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putBundle(str, bundle);
        Bundle bundle3 = new Bundle();
        bundle3.putBundle(PluginConstants.KEY_PL_CONFIG_INFO, bundle2);
        return bundle3;
    }

    public static String a(String str) {
        Plugin plugin;
        try {
            if (!Zeus.isPluginInstalled(str) || (plugin = Zeus.getPlugin(str)) == null) {
                return null;
            }
            return a(plugin.getVersion());
        } catch (Throwable unused) {
            com.bytedance.sdk.openadsdk.api.c.d("TTPluginManager", "Get local version failed");
            return null;
        }
    }

    public void a(final TTPluginListener tTPluginListener) {
        if (!this.g) {
            com.bytedance.sdk.openadsdk.api.c.d("TTPluginManager", "Zeus init failed.");
            if (tTPluginListener != null) {
                tTPluginListener.onPluginListener(1002, null, null, null);
                return;
            }
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.e.4
            @Override // java.lang.Runnable
            public void run() {
                com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", "Load plugin failed, caused by timeout.");
                tTPluginListener.onPluginListener(1001, null, null, null);
            }
        }, 180000L);
        String strPackageName = tTPluginListener.packageName();
        Plugin plugin = (Zeus.isPluginInstalled(strPackageName) && (Zeus.isPluginLoaded(strPackageName) || Zeus.loadPlugin(strPackageName))) ? Zeus.getPlugin(strPackageName) : null;
        StringBuilder sb = new StringBuilder();
        sb.append("Find plugin:");
        sb.append(plugin != null);
        com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", sb.toString());
        if (plugin != null) {
            b(plugin);
            handler.removeCallbacksAndMessages(null);
            tTPluginListener.onPluginListener(1000, plugin.mClassLoader, plugin.mResources, null);
        } else {
            b.put(strPackageName, tTPluginListener);
            c.put(strPackageName, handler);
        }
    }

    public void b(final TTPluginListener tTPluginListener) {
        com.bytedance.sdk.openadsdk.e.a.a().b(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.e.5
            @Override // java.lang.Runnable
            public void run() {
                String strPackageName = tTPluginListener.packageName();
                Plugin plugin = (Zeus.isPluginInstalled(strPackageName) && (Zeus.isPluginLoaded(strPackageName) || Zeus.loadPlugin(strPackageName))) ? Zeus.getPlugin(strPackageName) : null;
                StringBuilder sb = new StringBuilder();
                sb.append("Find plugin:");
                sb.append(plugin != null);
                com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", sb.toString());
                if (plugin != null) {
                    e.b(plugin);
                    tTPluginListener.onPluginListener(1000, plugin.mClassLoader, plugin.mResources, null);
                } else {
                    TTPluginListener unused = e.l = tTPluginListener;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static a d(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return a(new JSONObject(str));
        } catch (JSONException unused) {
            com.bytedance.sdk.openadsdk.api.c.e("TTPluginManager", "Invalid plugin info:" + str);
            return null;
        }
    }

    private static a a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        a aVar = new a();
        aVar.mPackageName = jSONObject.optString("package_name");
        aVar.mVersionCode = jSONObject.optInt("version_code");
        aVar.mUrl = jSONObject.optString("download_url");
        aVar.mMd5 = jSONObject.optString("md5");
        aVar.mApiVersionMin = jSONObject.optInt("min_version");
        aVar.mApiVersionMax = jSONObject.optInt("max_version");
        aVar.a = jSONObject.optString("sign");
        aVar.mFlag = jSONObject.optBoolean("is_revert") ? 3 : 2;
        aVar.b = new File(jSONObject.optString("plugin_file"));
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Plugin plugin) {
        if (plugin == null) {
            com.bytedance.sdk.openadsdk.api.c.d("TTPluginManager", "plugin is null.");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("action", 0);
        bundle.putString("plugin_pkg_name", plugin.mPkgName);
        bundle.putString(PluginConstants.KEY_PLUGIN_VERSION, a(plugin.getVersion()));
        TTAdManager adManager = TTAdSdk.getAdManager();
        if (adManager != null) {
            adManager.getExtra(Bundle.class, bundle);
        }
    }

    public static String a(int i) {
        char[] charArray = String.valueOf(i).toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            sb.append(charArray[i2]);
            if (i2 < charArray.length - 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    private static boolean a(TTPluginListener tTPluginListener, String str) {
        if (tTPluginListener == null || tTPluginListener.packageName() == null) {
            return false;
        }
        return tTPluginListener.packageName().equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(a aVar) {
        if (aVar == null || aVar.b == null) {
            com.bytedance.sdk.openadsdk.api.c.d("TTPluginManager", "plugin config is null");
            return false;
        }
        boolean zSyncInstallPlugin = Zeus.syncInstallPlugin(aVar.mPackageName, aVar.b.getAbsolutePath());
        a(zSyncInstallPlugin, aVar.mPackageName);
        return zSyncInstallPlugin;
    }

    private static void a(boolean z, String str) {
        TTPluginListener tTPluginListener = b.get(str);
        StringBuilder sb = new StringBuilder();
        sb.append("Install dl plugin ");
        sb.append(str);
        sb.append(z ? " success" : " failed");
        sb.append(", need notify: ");
        sb.append(tTPluginListener != null);
        com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", sb.toString());
        Handler handler = c.get(str);
        if (z) {
            TTPluginListener tTPluginListener2 = l;
            if (!a(tTPluginListener2, str) && (tTPluginListener == null || handler == null)) {
                return;
            }
            if (Zeus.loadPlugin(str)) {
                Plugin plugin = Zeus.getPlugin(str);
                b(plugin);
                if (handler != null) {
                    handler.removeCallbacksAndMessages(null);
                }
                if (tTPluginListener != null) {
                    tTPluginListener.onPluginListener(1000, plugin.mClassLoader, plugin.mResources, null);
                }
                if (a(tTPluginListener2, str)) {
                    tTPluginListener2.onPluginListener(1000, plugin.mClassLoader, plugin.mResources, null);
                    l = null;
                }
            } else {
                com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", "handle installed, load failed");
                c(str, 1002);
            }
        } else {
            com.bytedance.sdk.openadsdk.api.c.b("TTPluginManager", "handle installed failed");
            c(str, 1003);
        }
        b.remove(str);
        c.remove(str);
    }

    public static void a(Throwable th) {
        if (th instanceof AbstractMethodError) {
            Zeus.unInstallPlugin("com.byted.pangle");
            com.bytedance.sdk.openadsdk.api.c.d("TTPluginManager", "AbstractMethodError, rollback to builtin version.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, int i) {
        com.bytedance.sdk.openadsdk.api.c.d("TTPluginManager", "plugin update failed");
        Bundle bundle = new Bundle();
        bundle.putInt("code", i);
        TTPluginListener tTPluginListener = b.get(str);
        if (tTPluginListener != null) {
            tTPluginListener.onPluginListener(1001, null, null, bundle);
        }
    }

    private static final class b implements IZeusLogger {
        private b() {
        }

        @Override // com.bytedance.pangle.log.IZeusLogger
        public void v(String str, String str2) {
            com.bytedance.sdk.openadsdk.api.c.a(str, str2);
        }

        @Override // com.bytedance.pangle.log.IZeusLogger
        public void i(String str, String str2) {
            com.bytedance.sdk.openadsdk.api.c.c(str, str2);
        }

        @Override // com.bytedance.pangle.log.IZeusLogger
        public void w(String str, String str2) {
            com.bytedance.sdk.openadsdk.api.c.d(str, str2);
        }

        @Override // com.bytedance.pangle.log.IZeusLogger
        public void w(String str, String str2, Throwable th) {
            com.bytedance.sdk.openadsdk.api.c.b(str, str2, th);
        }

        @Override // com.bytedance.pangle.log.IZeusLogger
        public void e(String str, String str2, Throwable th) {
            com.bytedance.sdk.openadsdk.api.c.c(str, str2, th);
        }
    }

    static final class c implements EventListener, Serializable {
        c() {
        }

        @Override // com.bykv.vk.openvk.api.proto.EventListener
        public ValueSet onEvent(int i, Result result) {
            com.bykv.a.a.a.a.b bVarA = com.bykv.a.a.a.a.b.a();
            if (i == 1) {
                com.bytedance.sdk.openadsdk.api.plugin.b.a("plugin_download", "plugin update start");
                ValueSet valueSetValues = result.values();
                if (valueSetValues == null) {
                    com.bytedance.sdk.openadsdk.api.plugin.b.a("plugin_download", "valueSet empty");
                    return null;
                }
                String strStringValue = valueSetValues.stringValue(3);
                int iCode = result.code();
                if (result.isSuccess()) {
                    a aVarD = e.d(valueSetValues.stringValue(2));
                    if (aVarD == null || TextUtils.isEmpty(aVarD.mPackageName)) {
                        com.bytedance.sdk.openadsdk.api.plugin.b.a("plugin_download", "plugin update received with invalid config");
                        com.bytedance.sdk.openadsdk.api.c.d("TTPluginManager", "plugin update received with invalid config");
                        return null;
                    }
                    com.bytedance.sdk.openadsdk.api.plugin.b.a("plugin_download", "plugin update received: " + aVarD.mPackageName);
                    com.bytedance.sdk.openadsdk.api.c.d("TTPluginManager", "plugin update received: " + aVarD.mPackageName);
                    if (aVarD.isRevert()) {
                        com.bytedance.sdk.openadsdk.api.plugin.b.a("plugin_download", "plugin revert " + aVarD.mPackageName);
                        Zeus.unInstallPlugin(aVarD.mPackageName);
                    } else {
                        a(aVarD);
                        com.bytedance.sdk.openadsdk.api.plugin.b.a("plugin_download", "plugin install");
                        if (e.b(aVarD)) {
                            bVarA.a(4, true);
                        }
                    }
                } else {
                    com.bytedance.sdk.openadsdk.api.plugin.b.a("plugin_download", "plugin update received failed");
                    com.bytedance.sdk.openadsdk.api.c.d("TTPluginManager", "plugin update received failed");
                    e.c(strStringValue, iCode);
                    return null;
                }
            }
            return bVarA.b();
        }

        private void a(a aVar) {
            try {
                if (TextUtils.isEmpty(aVar.mPackageName) || !aVar.mPackageName.equals("com.byted.pangle")) {
                    return;
                }
                e.a(TTAppContextHolder.getContext()).b();
            } catch (Exception unused) {
            }
        }
    }

    public void b() {
        EventListener eventListener = this.k;
        if (eventListener != null) {
            eventListener.onEvent(1, com.bykv.a.a.a.a.a.a().a(true).b());
        }
    }

    private static final class a extends PluginDownloadBean {
        public String a = "";
        public File b = null;

        a() {
            this.mBackupUrlList = null;
        }
    }
}
