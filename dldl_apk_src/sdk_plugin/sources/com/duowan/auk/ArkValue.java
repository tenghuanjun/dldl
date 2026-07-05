package com.duowan.auk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.duowan.auk.helper.FileStorage;
import com.duowan.auk.util.Config;
import com.duowan.auk.util.L;
import com.huya.live.common.api.BaseApi;
import com.huya.mtp.utils.FP;
import com.huya.mtp.utils.ResourceUtils;
import com.huya.mtp.utils.StringUtils;
import com.huya.mtp.utils.Utils;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class ArkValue {
    private static final int CONFIG_DEBUGGABLE_DEFAULT = -1;
    private static final String CONFIG_DEBUGGABLE_KEY = "ark_debuggable";
    private static final String TAG = "ArkValue";
    private static String gChannelName;
    public static Application gContext;
    private static boolean gDebuggable;
    public static boolean gIsSnapshot;
    public static int gLongSide;
    public static Handler gMainHandler;
    public static int gShortSide;
    public static String sPackageName;
    private static int sVersionCode;
    private static final long sStartTimeStamp = System.currentTimeMillis();
    private static AtomicLong sLastPauseTimestamp = new AtomicLong(-1);
    private static Activity sCurrentActiveActivity = null;

    public static void init(Application application) {
        gContext = application;
        gMainHandler = new Handler(Looper.getMainLooper());
        initPackageName();
        initDebugValue(application);
        initLog();
        initSnapshotValue(application);
        initScreenValue(application);
        initCommonValue(application);
        initActivityCallback(application);
    }

    private static void initDebugValue(final Context context) {
        gDebuggable = Utils.isDebugMode(context);
        new Thread(new Runnable() { // from class: com.duowan.auk.ArkValue.1
            @Override // java.lang.Runnable
            public void run() {
                int i = Config.getInstance(context).getInt(ArkValue.CONFIG_DEBUGGABLE_KEY, -1);
                if (i != -1) {
                    boolean unused = ArkValue.gDebuggable = i > 0;
                }
            }
        }).start();
    }

    private static void initSnapshotValue(Context context) {
        try {
            gIsSnapshot = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName.contains("-SNAPSHOT");
        } catch (PackageManager.NameNotFoundException unused) {
            gIsSnapshot = false;
        }
    }

    private static void initLog() {
        if (gDebuggable) {
            L.LOG_LEVEL = 2;
        }
        L.isStoreExist = FileStorage.isStoreExist(FileStorage.Location.SDCard);
        L.sRootDir = FileStorage.getInstance().getRootDir(FileStorage.Location.SDCard).getParentFile();
        L.sLogPath = String.format("/%s/logs", sPackageName);
    }

    private static void initScreenValue(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        gLongSide = Math.max(i, i2);
        gShortSide = Math.min(i, i2);
    }

    private static void initCommonValue(Context context) {
        try {
            sVersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean debuggable() {
        if (gContext == null) {
            L.warn("ArkValue not init yet!");
        }
        return gDebuggable;
    }

    public static int versionCode() {
        return sVersionCode;
    }

    public static String channelName() {
        L.error(TAG, "gChannelName = null ? " + TextUtils.isEmpty(gChannelName));
        return gChannelName;
    }

    public static void setChannelName(String str) {
        gChannelName = str;
        if (StringUtils.isNullOrEmpty(str)) {
            gChannelName = "official";
        }
    }

    public static void setDebuggable(boolean z) {
        if (!Config.getInstance(gContext).setInt(CONFIG_DEBUGGABLE_KEY, z ? 1 : 0)) {
            BaseApi.crashIfDebug("config save fail", new Object[0]);
        }
        gDebuggable = z;
    }

    public static long uptime() {
        return System.currentTimeMillis() - sStartTimeStamp;
    }

    public static boolean hasActivityForeground() {
        long j = sLastPauseTimestamp.get();
        return j == -1 || System.currentTimeMillis() - j < TimeUnit.SECONDS.toMillis(30L);
    }

    private static void initPackageName() {
        String metaValue = ResourceUtils.getMetaValue(gContext, "TAG");
        sPackageName = metaValue;
        if (FP.empty(metaValue)) {
            sPackageName = gContext.getPackageName().split("\\.")[r0.length - 1];
        }
    }

    public static Activity getCurrentActiveActivity() {
        return sCurrentActiveActivity;
    }

    private static void initActivityCallback(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.duowan.auk.ArkValue.2
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
}
