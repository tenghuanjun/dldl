package com.bytedance.pangle;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.pm.ProviderInfo;
import android.os.RemoteException;
import android.text.TextUtils;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.apm.ApmUtils;
import com.bytedance.pangle.log.IZeusReporter;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.plugin.PluginManager;
import com.bytedance.pangle.util.FieldUtils;
import com.bytedance.pangle.util.MethodUtils;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class Zeus {
    private static Application sApplication;
    private static final HashMap<String, ProviderInfo> serverManagerHashMap = new HashMap<>();
    static final Object wait = new Object();
    private static volatile boolean onPrivacyAgreed = false;

    public static Application getAppApplication() {
        if (sApplication == null) {
            b.a();
            try {
                sApplication = (Application) MethodUtils.invokeMethod(com.bytedance.pangle.d.a.a(), "getApplication", new Object[0]);
            } catch (Throwable unused) {
            }
        }
        return sApplication;
    }

    public static void setAppContext(Application application) {
        if (application != null && TextUtils.equals(application.getClass().getSimpleName(), "PluginApplicationWrapper")) {
            try {
                sApplication = (Application) FieldUtils.readField(application, "mOriginApplication");
                return;
            } catch (Throwable unused) {
            }
        }
        sApplication = application;
    }

    public static HashMap<String, ProviderInfo> getServerManagerHashMap() {
        return serverManagerHashMap;
    }

    public static void init(Application application, boolean z) {
        GlobalParam.getInstance().getReporter().saveRecord(IZeusReporter.ZEUS_STAGE_COMMON, "start init");
        i.a().a(application, z);
        synchronized (wait) {
            wait.notifyAll();
        }
        com.bytedance.pangle.service.a.a aVarB = com.bytedance.pangle.service.a.a.b();
        for (Runnable runnable : aVarB.b) {
            if (runnable != null) {
                aVarB.a.post(runnable);
            }
        }
        aVarB.b.clear();
    }

    public static boolean waitInit(int i) {
        if (i.a().a) {
            return true;
        }
        synchronized (wait) {
            if (!i.a().a) {
                try {
                    if (i == -1) {
                        wait.wait();
                    } else {
                        wait.wait(i);
                    }
                } catch (InterruptedException unused) {
                }
            }
        }
        return i.a().a;
    }

    public static synchronized void onPrivacyAgreed() {
        if (onPrivacyAgreed) {
            return;
        }
        ApmUtils.getApmInstance().init();
        onPrivacyAgreed = true;
    }

    public static void hookHuaWeiVerifier(Application application) {
        com.bytedance.pangle.receiver.b.a(application);
    }

    public static void installFromDownloadDir() {
        if (com.bytedance.pangle.d.d.a(getAppApplication())) {
            PluginManager.getInstance().installFromDownloadDir();
        }
    }

    public static void triggerBgDexOpt() {
        com.bytedance.pangle.e.f.a();
    }

    public static boolean hasInit() {
        return i.a().a;
    }

    public static void fetchPlugin(final String str) {
        com.bytedance.pangle.download.a aVarA = com.bytedance.pangle.download.a.a();
        if (com.bytedance.pangle.d.d.a(getAppApplication())) {
            if (GlobalParam.getInstance().autoFetch()) {
                final com.bytedance.pangle.download.b bVarA = com.bytedance.pangle.download.b.a();
                Runnable runnable = bVarA.c.get(str);
                if (runnable != null) {
                    bVarA.b.removeCallbacks(runnable);
                }
                Runnable runnable2 = new Runnable() { // from class: com.bytedance.pangle.download.b.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        List<ActivityManager.RunningTaskInfo> runningTasks;
                        ComponentName componentName;
                        Application appApplication = Zeus.getAppApplication();
                        String packageName = appApplication.getPackageName();
                        if ((TextUtils.isEmpty(packageName) || (runningTasks = ((ActivityManager) appApplication.getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningTasks(1)) == null || runningTasks.isEmpty() || (componentName = runningTasks.get(0).topActivity) == null || !packageName.equals(componentName.getPackageName())) ? false : true) {
                            b.this.b.postDelayed(this, 1800000L);
                        }
                    }
                };
                bVarA.c.put(str, runnable2);
                bVarA.b.postDelayed(runnable2, 1800000L);
                com.bytedance.pangle.download.b.a();
                if (aVarA.a.contains(str)) {
                    return;
                }
                aVarA.a.add(str);
                return;
            }
            com.bytedance.pangle.download.b.a();
        }
    }

    public static void registerPluginStateListener(ZeusPluginStateListener zeusPluginStateListener) {
        i.a().b.add(zeusPluginStateListener);
    }

    public static void unregisterPluginStateListener(ZeusPluginStateListener zeusPluginStateListener) {
        i iVarA = i.a();
        if (iVarA.b != null) {
            iVarA.b.remove(zeusPluginStateListener);
        }
    }

    public static void addPluginEventCallback(ZeusPluginEventCallback zeusPluginEventCallback) {
        i iVarA = i.a();
        if (zeusPluginEventCallback != null) {
            synchronized (iVarA.c) {
                iVarA.c.add(zeusPluginEventCallback);
            }
        }
    }

    public static void removePluginEventCallback(ZeusPluginEventCallback zeusPluginEventCallback) {
        i iVarA = i.a();
        if (zeusPluginEventCallback != null) {
            synchronized (iVarA.c) {
                iVarA.c.remove(zeusPluginEventCallback);
            }
        }
    }

    public static Plugin getPlugin(String str) {
        return getPlugin(str, true);
    }

    public static Plugin getPlugin(String str, boolean z) {
        return PluginManager.getInstance().getPlugin(str, z);
    }

    public static boolean isPluginInstalled(String str) {
        Plugin plugin = PluginManager.getInstance().getPlugin(str);
        return plugin != null && plugin.isInstalled();
    }

    public static boolean isPluginLoaded(String str) {
        return PluginManager.getInstance().isLoaded(str);
    }

    public static boolean loadPlugin(String str) {
        return PluginManager.getInstance().loadPlugin(str);
    }

    public static boolean syncInstallPlugin(String str, String str2) {
        GlobalParam.getInstance().getReporter().saveRecord(IZeusReporter.ZEUS_STAGE_PLUGIN_INSTALL, "start");
        c cVarA = com.bytedance.pangle.servermanager.b.a();
        if (cVarA == null) {
            return false;
        }
        try {
            return cVarA.a(str, str2);
        } catch (RemoteException e) {
            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "syncInstallPlugin error.", e);
            return false;
        }
    }

    public static void registerPluginInstallListener(ZeusPluginInstallListener zeusPluginInstallListener) {
        try {
            c cVarA = com.bytedance.pangle.servermanager.b.a();
            if (cVarA != null) {
                cVarA.a(zeusPluginInstallListener.hashCode(), new com.bytedance.pangle.f.b(zeusPluginInstallListener));
            }
        } catch (RemoteException e) {
            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "registerPluginInstallListener error.", e);
        }
    }

    public void unregisterPluginInstallListener(ZeusPluginInstallListener zeusPluginInstallListener) {
        try {
            c cVarA = com.bytedance.pangle.servermanager.b.a();
            if (cVarA != null) {
                cVarA.a(zeusPluginInstallListener.hashCode());
            }
        } catch (RemoteException e) {
            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "unregisterPluginInstallListener error.", e);
        }
    }

    public static void unInstallPlugin(String str) {
        PluginManager.getInstance().unInstallPackage(str);
    }

    public static void setAllowDownloadPlugin(String str, int i, boolean z) {
        PluginManager.getInstance().setAllowDownloadPlugin(str, i, z);
    }

    public static String getHostAbi() {
        return com.bytedance.pangle.d.b.a();
    }

    public static int getMaxInstallVer(String str) {
        if (com.bytedance.pangle.d.d.a(getAppApplication())) {
            return getPlugin(str).getInstalledMaxVer();
        }
        return -1;
    }

    public static int getHostAbiBit() {
        return com.bytedance.pangle.d.b.b();
    }

    public static int getInstalledPluginVersion(String str) {
        Plugin plugin = PluginManager.getInstance().getPlugin(str);
        if (plugin == null) {
            return -1;
        }
        int version = plugin.getVersion();
        ZeusLogger.d(ZeusLogger.TAG_DOWNLOAD, " getInstalledPluginVersion, " + str + " = " + version);
        return version;
    }

    public static void addExternalAssetsForPlugin(String str, String str2) {
        Plugin plugin;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (plugin = getPlugin(str)) == null || plugin.mResources == null) {
            return;
        }
        new com.bytedance.pangle.res.a().a(plugin.mResources.getAssets(), str2, false);
    }

    public static String getZeusDid() {
        String did = GlobalParam.getInstance().getDid();
        return !TextUtils.isEmpty(did) ? did : ApmUtils.getApmInstance().getDid();
    }
}
