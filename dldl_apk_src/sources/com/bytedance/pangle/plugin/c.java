package com.bytedance.pangle.plugin;

import android.content.ComponentCallbacks;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.text.TextUtils;
import com.bytedance.pangle.ComponentManager;
import com.bytedance.pangle.PluginClassLoader;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusApplication;
import com.bytedance.pangle.ZeusPluginEventCallback;
import com.bytedance.pangle.ZeusPluginStateListener;
import com.bytedance.pangle.c.b;
import com.bytedance.pangle.d.e;
import com.bytedance.pangle.e.g;
import com.bytedance.pangle.i;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.provider.ContentProviderManager;
import com.bytedance.pangle.res.PluginResources;
import com.bytedance.pangle.transform.ZeusTransformUtils;
import com.bytedance.pangle.util.FieldUtils;
import com.bytedance.pangle.util.MethodUtils;
import com.bytedance.pangle.wrapper.PluginApplicationWrapper;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c {
    private static final i a = i.a();

    c() {
    }

    final boolean a(String str) {
        Plugin plugin = PluginManager.getInstance().getPlugin(str);
        if (plugin == null) {
            ZeusLogger.w(ZeusLogger.TAG_LOAD, "PluginLoader loadPlugin, plugin == null, pkg = ".concat(String.valueOf(str)));
            return false;
        }
        synchronized (plugin) {
            if (!plugin.isInstalled()) {
                ZeusLogger.w(ZeusLogger.TAG_LOAD, "PluginLoader loadPlugin, UN_INSTALLED, ".concat(String.valueOf(str)));
                return false;
            }
            if (plugin.isLoaded()) {
                return true;
            }
            a.a(2000, 0, plugin.mPkgName, plugin.getVersion(), null);
            com.bytedance.pangle.log.a aVarA = com.bytedance.pangle.log.a.a(ZeusLogger.TAG_LOAD, "PluginLoader", "loadPlugin:".concat(String.valueOf(str)));
            a(com.bytedance.pangle.c.b.i, b.a.D, plugin.mPkgName, plugin.getVersion(), -1L, (String) null);
            ZeusPluginStateListener.postStateChange(str, 8, new Object[0]);
            StringBuilder sb = new StringBuilder();
            boolean zA = a(str, plugin, sb);
            aVarA.a("loadPluginInternal:".concat(String.valueOf(zA)));
            if (zA) {
                plugin.setLifeCycle(3);
                a(com.bytedance.pangle.c.b.j, b.a.E, plugin.mPkgName, plugin.getVersion(), aVarA.a(), sb.toString());
                ZeusPluginStateListener.postStateChange(str, 9, new Object[0]);
                a.a(ZeusPluginEventCallback.EVENT_FINISH_LOAD, 0, plugin.mPkgName, plugin.getVersion(), null);
            } else {
                sb.append("plugin:");
                sb.append(plugin.mPkgName);
                sb.append(" versionCode:");
                sb.append(plugin.getVersion());
                sb.append("load failed;");
                a(com.bytedance.pangle.c.b.j, b.a.F, plugin.mPkgName, plugin.getVersion(), -1L, sb.toString());
                ZeusPluginStateListener.postStateChange(str, 10, new Object[0]);
                a.a(ZeusPluginEventCallback.EVENT_FINISH_LOAD, -1, plugin.mPkgName, plugin.getVersion(), null);
            }
            ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginLoader loadFinished, ".concat(String.valueOf(plugin)));
            if (!plugin.isLoaded()) {
                return false;
            }
            ZeusLogger.d(ZeusLogger.TAG_LOAD, "PluginLoader postResult, LOADED " + plugin.mPkgName);
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x01a9 A[Catch: all -> 0x024b, TryCatch #0 {all -> 0x024b, blocks: (B:39:0x0185, B:41:0x01a9, B:47:0x01d1, B:49:0x01d7, B:50:0x01da, B:52:0x01e2, B:54:0x0245, B:53:0x0218), top: B:67:0x0185 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01d1 A[Catch: all -> 0x024b, TryCatch #0 {all -> 0x024b, blocks: (B:39:0x0185, B:41:0x01a9, B:47:0x01d1, B:49:0x01d7, B:50:0x01da, B:52:0x01e2, B:54:0x0245, B:53:0x0218), top: B:67:0x0185 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01e2 A[Catch: all -> 0x024b, TryCatch #0 {all -> 0x024b, blocks: (B:39:0x0185, B:41:0x01a9, B:47:0x01d1, B:49:0x01d7, B:50:0x01da, B:52:0x01e2, B:54:0x0245, B:53:0x0218), top: B:67:0x0185 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0218 A[Catch: all -> 0x024b, TryCatch #0 {all -> 0x024b, blocks: (B:39:0x0185, B:41:0x01a9, B:47:0x01d1, B:49:0x01d7, B:50:0x01da, B:52:0x01e2, B:54:0x0245, B:53:0x0218), top: B:67:0x0185 }] */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v11 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(final java.lang.String r22, final com.bytedance.pangle.plugin.Plugin r23, final java.lang.StringBuilder r24) {
        /*
            Method dump skipped, instruction units count: 726
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.plugin.c.a(java.lang.String, com.bytedance.pangle.plugin.Plugin, java.lang.StringBuilder):boolean");
    }

    private static void a(Plugin plugin, StringBuilder sb, PackageInfo packageInfo) {
        ActivityInfo[] activityInfoArr = packageInfo.activities;
        if (activityInfoArr != null) {
            for (ActivityInfo activityInfo : activityInfoArr) {
                if (TextUtils.isEmpty(activityInfo.processName) || !activityInfo.processName.contains(":")) {
                    activityInfo.processName = "main";
                } else {
                    activityInfo.processName = activityInfo.processName.split(":")[1];
                }
                plugin.pluginActivities.put(activityInfo.name, activityInfo);
            }
        }
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        if (serviceInfoArr != null) {
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                if (TextUtils.isEmpty(serviceInfo.processName) || !serviceInfo.processName.contains(":")) {
                    serviceInfo.processName = "main";
                } else {
                    serviceInfo.processName = serviceInfo.processName.split(":")[1];
                }
                plugin.pluginServices.put(serviceInfo.name, serviceInfo);
            }
        }
        ActivityInfo[] activityInfoArr2 = packageInfo.receivers;
        if (activityInfoArr2 != null) {
            for (ActivityInfo activityInfo2 : activityInfoArr2) {
                if (TextUtils.isEmpty(activityInfo2.processName) || !activityInfo2.processName.contains(":")) {
                    activityInfo2.processName = "main";
                } else {
                    activityInfo2.processName = activityInfo2.processName.split(":")[1];
                }
                plugin.pluginReceiver.put(activityInfo2.name, activityInfo2);
            }
        }
        ProviderInfo[] providerInfoArr = packageInfo.providers;
        if (providerInfoArr != null) {
            for (ProviderInfo providerInfo : providerInfoArr) {
                if (TextUtils.isEmpty(providerInfo.processName) || !providerInfo.processName.contains(":")) {
                    providerInfo.processName = "main";
                } else {
                    providerInfo.processName = providerInfo.processName.split(":")[1];
                }
                plugin.pluginProvider.put(providerInfo.name, providerInfo);
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (plugin.pluginProvider != null && plugin.pluginProvider.size() > 0) {
            ContentProviderManager.getInstance().installContentProviders(plugin.pluginProvider.values(), plugin);
        }
        sb.append("installProvider cost:");
        sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
        sb.append(com.alipay.sdk.util.i.b);
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        if (!TextUtils.isEmpty(packageInfo.applicationInfo.className)) {
            plugin.mApplication = (ZeusApplication) plugin.mClassLoader.loadClass(packageInfo.applicationInfo.className).newInstance();
            plugin.mApplication.attach(plugin, Zeus.getAppApplication());
        }
        sb.append("makeApplication cost:");
        sb.append(System.currentTimeMillis() - jCurrentTimeMillis2);
        sb.append(com.alipay.sdk.util.i.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PackageInfo a(String str, final Plugin plugin, StringBuilder sb, String str2, File file) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        PackageInfo packageArchiveInfo = Zeus.getAppApplication().getPackageManager().getPackageArchiveInfo(str2, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_MIME_TYPE);
        plugin.mHostApplication = (PluginApplicationWrapper) ZeusTransformUtils.wrapperContext2Application(Zeus.getAppApplication(), plugin.mPkgName);
        plugin.mHostApplicationInfoHookSomeField = new ApplicationInfo(Zeus.getAppApplication().getApplicationInfo());
        plugin.mHostApplicationInfoHookSomeField.nativeLibraryDir = file.getAbsolutePath();
        plugin.mHostApplicationInfoHookSomeField.dataDir = plugin.mHostApplication.getDataDir().getAbsolutePath();
        plugin.mHostApplicationInfoHookSomeField.sourceDir = str2;
        if (TextUtils.isEmpty(packageArchiveInfo.applicationInfo.sourceDir)) {
            packageArchiveInfo.applicationInfo.sourceDir = str2;
        }
        if (TextUtils.isEmpty(packageArchiveInfo.applicationInfo.publicSourceDir)) {
            packageArchiveInfo.applicationInfo.publicSourceDir = str2;
        }
        plugin.mResources = new PluginResources(Zeus.getAppApplication().getPackageManager().getResourcesForApplication(packageArchiveInfo.applicationInfo), str);
        Zeus.getAppApplication().registerComponentCallbacks(new ComponentCallbacks() { // from class: com.bytedance.pangle.plugin.c.3
            @Override // android.content.ComponentCallbacks
            public final void onLowMemory() {
            }

            @Override // android.content.ComponentCallbacks
            public final void onConfigurationChanged(Configuration configuration) {
                plugin.mResources.updateConfiguration(configuration, Zeus.getAppApplication().getResources().getDisplayMetrics());
            }
        });
        sb.append("makeResources cost:");
        sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
        sb.append(com.alipay.sdk.util.i.b);
        return packageArchiveInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Plugin plugin, String str, File file, File file2, StringBuilder sb) throws IllegalAccessException, IOException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        a(plugin, str, file, file2);
        sb.append("classLoader cost:");
        sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
        sb.append(" ;");
        if (plugin.mOpenLoadClassOpt) {
            e.a(new Runnable() { // from class: com.bytedance.pangle.plugin.c.4
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        plugin.mClassLoader.setAllPluginClasses((HashSet) MethodUtils.invokeStaticMethod(plugin.mClassLoader.loadClass("com.volcengine.PluginClassHolder"), "getPluginClasses", new Object[0]));
                    } catch (Throwable unused) {
                    }
                }
            });
        }
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        try {
            String str2 = (String) FieldUtils.readStaticField(plugin.mClassLoader.loadClass("com.volcengine.StubConfig"), "actStubV1");
            if (str2 != null) {
                a(plugin, str2);
            }
        } catch (ClassNotFoundException unused) {
        } catch (Throwable th) {
            sb.append("actStubV1 cost:");
            sb.append(System.currentTimeMillis() - jCurrentTimeMillis2);
            sb.append(com.alipay.sdk.util.i.b);
            throw th;
        }
        sb.append("actStubV1 cost:");
        sb.append(System.currentTimeMillis() - jCurrentTimeMillis2);
        sb.append(com.alipay.sdk.util.i.b);
    }

    private static void a(Plugin plugin, String str) throws JSONException {
        JSONObject jSONObjectOptJSONObject;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("mapping");
        HashMap map = new HashMap();
        if (jSONObjectOptJSONObject2 != null) {
            Iterator<String> itKeys = jSONObjectOptJSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObjectOptJSONObject2.getString(next));
            }
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("forceMappings");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                int iOptInt = jSONObject2.optInt("minApi", 0);
                int iOptInt2 = jSONObject2.optInt("maxApi", Integer.MAX_VALUE);
                int apiVersionCode = plugin.getApiVersionCode();
                if (apiVersionCode <= iOptInt2 && apiVersionCode >= iOptInt && (jSONObjectOptJSONObject = jSONObject2.optJSONObject("mapping")) != null) {
                    Iterator<String> itKeys2 = jSONObjectOptJSONObject.keys();
                    while (itKeys2.hasNext()) {
                        String next2 = itKeys2.next();
                        map.put(next2, jSONObjectOptJSONObject.getString(next2));
                    }
                }
            }
        }
        for (String str2 : map.keySet()) {
            String str3 = (String) map.get(str2);
            String str4 = plugin.mPkgName;
            StringBuilder sb = new StringBuilder();
            sb.append((str3 == null || !str3.contains(".")) ? plugin.mPkgName + "." : "");
            sb.append((String) map.get(str2));
            ComponentManager.registerActivity(str4, sb.toString(), str2);
        }
    }

    private static void a(String str, int i, String str2, int i2, long j, String str3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.putOpt(MonitorConstants.STATUS_CODE, com.bytedance.pangle.log.b.a(Integer.valueOf(i)));
            jSONObject.putOpt("plugin_package_name", com.bytedance.pangle.log.b.a(str2));
            jSONObject.putOpt("version_code", com.bytedance.pangle.log.b.a(Integer.valueOf(i2)));
            jSONObject3.putOpt(MediationConstant.EXTRA_DURATION, Integer.valueOf(com.bytedance.pangle.log.b.b(Long.valueOf(j))));
            jSONObject2.putOpt(com.igexin.push.core.b.Z, com.bytedance.pangle.log.b.a(str3));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.bytedance.pangle.c.b.a().a(str, jSONObject, jSONObject3, jSONObject2);
    }

    private static void a(Object obj, String str) {
        if (str != null) {
            try {
                for (String str2 : str.split(File.pathSeparator)) {
                    new File(str2).setReadOnly();
                }
            } catch (Throwable th) {
                ZeusLogger.errReport(ZeusLogger.TAG_LOAD, "PluginLoader createPluginClassLoader#addDexPath fail >>>".concat(String.valueOf(str)), th);
                return;
            }
        }
        MethodUtils.getAccessibleMethod(BaseDexClassLoader.class, "addDexPath", String.class).invoke(obj, str);
        ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginLoader createPluginClassLoader#addDexPath success >>>".concat(String.valueOf(str)));
    }

    private static void a(Plugin plugin, String str, File file, File file2) throws IllegalAccessException, IOException {
        if (com.bytedance.pangle.util.i.k()) {
            plugin.mClassLoader = new PluginClassLoader("", file2, file.getAbsolutePath(), null);
            a(plugin.mClassLoader, str);
            return;
        }
        if (com.bytedance.pangle.util.i.b()) {
            String strA = g.a(plugin.mPkgName, plugin.getVersion());
            String[] strArrSplit = strA.split(":");
            long jCurrentTimeMillis = System.currentTimeMillis();
            boolean z = !com.bytedance.pangle.e.b.a(file2.getAbsolutePath(), strArrSplit);
            ZeusLogger.d(ZeusLogger.TAG_LOAD, "useDirect:" + (System.currentTimeMillis() - jCurrentTimeMillis) + " " + z);
            plugin.mClassLoader = new PluginClassLoader(z ? "" : strA, file2, file.getAbsolutePath(), null);
            if (z) {
                com.bytedance.pangle.dex.a.a(plugin.mClassLoader, plugin.mPkgName, plugin.getVersion());
                return;
            }
            return;
        }
        plugin.mClassLoader = new PluginClassLoader(str, file2, file.getAbsolutePath(), null);
    }
}
