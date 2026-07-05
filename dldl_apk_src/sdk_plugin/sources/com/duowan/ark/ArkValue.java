package com.duowan.ark;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.duowan.ark.helper.FileStorage;
import com.duowan.ark.module.ArkModule;
import com.duowan.ark.thread.ExecutorCenter;
import com.duowan.ark.thread.pool.LogUtil;
import com.duowan.ark.util.Config;
import com.duowan.ark.util.DebugUtils;
import com.duowan.ark.util.FP;
import com.duowan.ark.util.KLog;
import com.duowan.ark.util.LogProxy;
import com.duowan.ark.util.ResourceUtils;
import com.duowan.ark.util.Utils;
import com.mcxiaoke.packer.helper.PackerNg;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ArkValue {
    private static final int CONFIG_IS_TEST_ENV_DEFAULT = -1;
    private static final String CONFIG_IS_TEST_ENV_KEY = "ark_is_test_env";
    private static final String DEBUGGABLE_KEY = "constant_debuggable";
    private static final String FORCE_CLOSE_DEBUGGABLE_KEY = "force_close_debuggable";
    private static final String FORCE_TRUNK_AS_SNAPSHOT_KEY = "trunk_as_snapshot";
    private static final String TAG = "ArkValue";
    public static ArkExtConfig gArkExtConfig;
    private static String gChannelName;
    public static Application gContext;
    private static boolean gIsDebuggable;
    private static boolean gIsForceCloseDebuggable;
    private static boolean gIsLocalBuild;
    private static boolean gIsPreRelease;
    private static boolean gIsSnapshot;
    private static boolean gIsTestEnv;
    public static int gLongSide;
    public static Handler gMainHandler;
    public static int gScreenHeight;
    public static int gScreenWidth;
    public static int gShortSide;
    public static String gTag;
    private static int sHotfixVersion;
    private static String sProcessName;
    private static int sVersionCode;
    private static String sVersionName;
    private static Map<Class<? extends ArkModule>, ModuleInfo> msRunningModule = new HashMap();
    private static final long sStartTimeStamp = System.currentTimeMillis();
    private static AtomicLong sLastPauseTimestamp = new AtomicLong(-1);
    private static Activity sCurrentActiveActivity = null;

    static void init(Application application, int i, boolean z) {
        gContext = application;
        sProcessName = Utils.getProcessName(application);
        initTag();
        ArkExtConfig arkExtConfig = new ArkExtConfig();
        gArkExtConfig = arkExtConfig;
        if (arkExtConfig.exists()) {
            try {
                if (!TextUtils.isEmpty(sProcessName) && !sProcessName.contains(":")) {
                    Toast.makeText(gContext, "ark.config is exists", 0).show();
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("ark.config is exists for ");
                    sb.append(TextUtils.isEmpty(sProcessName) ? "unknown" : sProcessName);
                    Log.i("ark.config", sb.toString());
                }
            } catch (Exception e) {
                Log.e(TAG, "[ArkValue]show toast occurred error", e);
            }
        }
        gMainHandler = new Handler(Looper.getMainLooper());
        initDebugValue(application);
        initCommonValue(application, i);
        initBuildType();
        DebugUtils.setDebuggable(debuggable());
        initExecute();
        initSnapshotValue(application);
        initTrunkValue(application);
        initLog();
        initScreenValue(application);
        initActivityCallback(application);
        if (z) {
            gIsForceCloseDebuggable = true;
        }
    }

    public static ArkModule getModule(Class<? extends ArkModule> cls) {
        ModuleInfo moduleInfo;
        if (cls == null || (moduleInfo = msRunningModule.get(cls)) == null) {
            return null;
        }
        return moduleInfo.module;
    }

    static boolean startModule(Class<? extends ArkModule> cls, Bundle bundle) {
        if (msRunningModule.containsKey(cls)) {
            msRunningModule.get(cls).dependTime++;
        } else {
            try {
                ArkModule arkModuleNewInstance = cls.newInstance();
                ModuleInfo moduleInfo = new ModuleInfo();
                moduleInfo.module = arkModuleNewInstance;
                moduleInfo.dependTime = 1;
                msRunningModule.put(cls, moduleInfo);
                arkModuleNewInstance.setArguments(bundle);
                arkModuleNewInstance.onStart();
            } catch (IllegalAccessException unused) {
                ArkUtils.crashIfDebug("start %s module fail(IllegalAccessException)!", cls.getSimpleName());
                return false;
            } catch (InstantiationException unused2) {
                ArkUtils.crashIfDebug("start %s module fail(InstantiationException)!", cls.getSimpleName());
                return false;
            }
        }
        return true;
    }

    static boolean stopModule(Class<? extends ArkModule> cls) {
        ModuleInfo moduleInfo = msRunningModule.get(cls);
        if (moduleInfo == null) {
            ArkUtils.crashIfDebug("stop null module: %s", cls.getSimpleName());
            return false;
        }
        moduleInfo.dependTime--;
        if (moduleInfo.dependTime == 0) {
            moduleInfo.module.onStop();
            msRunningModule.remove(cls);
        }
        return true;
    }

    private static void initTag() {
        String metaValue = ResourceUtils.getMetaValue(gContext, "TAG");
        gTag = metaValue;
        if (FP.empty(metaValue)) {
            gTag = gContext.getPackageName().split("\\.")[r0.length - 1];
        }
    }

    private static void initDebugValue(Context context) {
        gIsDebuggable = Utils.isDebugMode(context);
        if (Config.getInstance(context).getInt(FORCE_CLOSE_DEBUGGABLE_KEY, -1) != -1) {
            gIsForceCloseDebuggable = true;
        }
        if (gArkExtConfig.exists()) {
            gIsDebuggable = true;
        }
        JSONObject jSONObjectData = gArkExtConfig.data();
        if (jSONObjectData != null) {
            gIsDebuggable = true;
            try {
                if (jSONObjectData.has(DEBUGGABLE_KEY)) {
                    gIsDebuggable = jSONObjectData.getBoolean(DEBUGGABLE_KEY);
                }
            } catch (Exception unused) {
                Utils.dwAssert(false);
            }
        }
        int i = Config.getInstance(context).getInt(CONFIG_IS_TEST_ENV_KEY, -1);
        if (i != -1) {
            gIsTestEnv = i > 0;
        }
    }

    private static void initTrunkValue(Context context) {
        if (gIsSnapshot && "1".equals(ResourceUtils.getMetaValue(context, "IS_PRE_RELEASE", ""))) {
            gIsPreRelease = true;
        }
    }

    private static void initSnapshotValue(Context context) {
        try {
            gIsSnapshot = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName.contains("-SNAPSHOT");
        } catch (PackageManager.NameNotFoundException unused) {
            gIsSnapshot = false;
        }
    }

    private static void initLog() {
        KLog.isStoreExist = FileStorage.isStoreExist(FileStorage.Location.SDCard);
        LogProxy.resetRoot(FileStorage.getRootDir(FileStorage.Location.SDCard).getParentFile());
        LogProxy.resetLogPath(String.format("/%s/logs", gTag));
        LogProxy.sIsSnapshot = gIsSnapshot;
        if (gIsDebuggable) {
            KLog.setLogLevel(2);
        }
    }

    private static void initExecute() {
        ExecutorCenter.setLogger(new LogUtil.Logger() { // from class: com.duowan.ark.ArkValue.1
            @Override // com.duowan.ark.thread.pool.LogUtil.Logger
            public void info(String str, String str2) {
                KLog.info(str, str2);
            }

            @Override // com.duowan.ark.thread.pool.LogUtil.Logger
            public void info(String str, Throwable th) {
                KLog.error(str, th);
            }
        });
    }

    private static void initScreenValue(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        gLongSide = Math.max(i, i2);
        gShortSide = Math.min(i, i2);
        gScreenWidth = i;
        gScreenHeight = i2;
    }

    public static void updateScreenValue(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i <= 0 || i2 <= 0) {
            return;
        }
        gLongSide = Math.max(i, i2);
        gShortSide = Math.min(i, i2);
        gScreenWidth = i;
        gScreenHeight = i2;
    }

    private static void initCommonValue(Context context, int i) {
        gChannelName = PackerNg.getMarket(context, "official");
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            sVersionCode = packageInfo.versionCode;
            sVersionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        sHotfixVersion = i;
    }

    private static void initBuildType() {
        gIsLocalBuild = !isJenkinsBuild();
    }

    public static boolean isSnapshot() {
        if (gContext == null) {
            KLog.warn("ArkValue not init yet!");
        }
        return gIsSnapshot && (!gIsPreRelease || gArkExtConfig.exists());
    }

    public static void forceCloseDebuggable() {
        if (Config.getInstance(gContext).setInt(FORCE_CLOSE_DEBUGGABLE_KEY, 1)) {
            return;
        }
        ArkUtils.crashIfDebug("force close debuggable config save fail", new Object[0]);
    }

    public static boolean isForceCloseDebuggable() {
        if (gContext == null) {
            KLog.warn("ArkValue not init yet!");
        }
        return gIsForceCloseDebuggable;
    }

    public static boolean debuggable() {
        if (gContext == null) {
            KLog.warn("ArkValue not init yet!");
        }
        if (gIsForceCloseDebuggable) {
            return false;
        }
        if (!gIsPreRelease || gArkExtConfig.exists()) {
            return gIsSnapshot || gIsLocalBuild || gIsDebuggable;
        }
        return false;
    }

    public static boolean isTestEnv() {
        if (gContext == null) {
            KLog.warn("ArkValue not init yet!");
        }
        return debuggable() && gIsTestEnv;
    }

    public static void switchTestEnv(boolean z) {
        if (Config.getInstance(gContext).setInt(CONFIG_IS_TEST_ENV_KEY, z ? 1 : 0)) {
            return;
        }
        ArkUtils.crashIfDebug("config save fail", new Object[0]);
    }

    public static int versionCode() {
        return sVersionCode;
    }

    public static int hotfixVersion() {
        return sHotfixVersion;
    }

    public static String versionName() {
        return sVersionName;
    }

    public static String channelName() {
        return gChannelName;
    }

    public static void setChannelName(String str) {
        gChannelName = str;
    }

    public static long uptime() {
        return System.currentTimeMillis() - sStartTimeStamp;
    }

    public static boolean hasActivityForeground() {
        long j = sLastPauseTimestamp.get();
        return j == -1 || System.currentTimeMillis() - j < TimeUnit.SECONDS.toMillis(30L);
    }

    public static Activity getCurrentActiveActivity() {
        return sCurrentActiveActivity;
    }

    private static void initActivityCallback(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.duowan.ark.ArkValue.2
            private boolean first = true;

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                if (this.first) {
                    this.first = false;
                }
                Activity unused = ArkValue.sCurrentActiveActivity = activity;
                ArkValue.sLastPauseTimestamp.set(-1L);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                ArkValue.sLastPauseTimestamp.set(System.currentTimeMillis());
                Activity unused = ArkValue.sCurrentActiveActivity = null;
            }
        });
    }

    private static boolean isJenkinsBuild() {
        return sVersionCode > 0;
    }

    public static boolean isLocalBuild() {
        return !isJenkinsBuild();
    }

    private static class ModuleInfo {
        public int dependTime;
        public ArkModule module;

        private ModuleInfo() {
        }
    }
}
