package com.sqwan.bugless.core;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import com.sqwan.bugless.model.AppExtension;
import com.sqwan.bugless.model.UserInfo;
import com.sqwan.bugless.net.IHttpClient;
import com.sqwan.bugless.util.LogUtil;
import com.sqwan.bugless.util.SharedPreferencesUtil;
import java.lang.Thread;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Bugless implements Thread.UncaughtExceptionHandler {
    private static String TAG = "Bugless";
    private static Bugless instance = new Bugless();
    private ANRWatchDog anrWatchDog;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private IHttpClient mHttpClient;
    private boolean mMulProcess;
    private String mReportUrl;
    private TrackHandler mTrackHandler;

    private Bugless() {
    }

    public static Bugless getInstance() {
        return instance;
    }

    public void setMulProcess(boolean mulProcess) {
        this.mMulProcess = mulProcess;
    }

    public void init(Context context, IHttpClient client) {
        init(context, client, null);
    }

    public void setHost(String host) {
        if (TextUtils.isEmpty(host)) {
            return;
        }
        this.mReportUrl = "http://track2." + host + "/api/bugless/";
    }

    public void setUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        this.mReportUrl = url;
    }

    public void init(Context context, IHttpClient client, AppExtension extension) {
        init(context, client, extension, null);
    }

    public void init(Context context, IHttpClient client, AppExtension extension, UserInfo userInfo) {
        init(context, client, extension, userInfo, false);
    }

    public void init(Context context, IHttpClient client, AppExtension extension, UserInfo userInfo, boolean printLog) {
        LogUtil.setDebug(printLog);
        if (!this.mMulProcess && !isMainProcess(context)) {
            LogUtil.w("非主进程，不初始化Bugless");
            return;
        }
        this.mContext = context;
        this.mHttpClient = client;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        SharedPreferencesUtil.getInstance().init(context);
        Thread.setDefaultUncaughtExceptionHandler(this);
        ParamsManager.getInstance().init(context, extension);
        if (userInfo != null) {
            setUserInfo(userInfo);
        }
        checkCache();
    }

    private void initANRWatchDog() {
        ANRWatchDog aNRWatchDog = new ANRWatchDog();
        this.anrWatchDog = aNRWatchDog;
        aNRWatchDog.start();
    }

    public void setAppExtension(AppExtension extension) {
        ParamsManager.getInstance().initExtension(extension);
    }

    public void setUserInfo(UserInfo userInfo) {
        ParamsManager.getInstance().initUserInfo(userInfo);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable ex) {
        TrackHandler trackHandler = this.mTrackHandler;
        if (trackHandler != null) {
            trackHandler.onCrash();
        }
        handleException(thread, ex);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.mDefaultHandler;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, ex);
        }
    }

    private boolean handleException(Thread thread, Throwable ex) {
        if (ex == null) {
            return false;
        }
        LogUtil.i("发生异常！");
        BugHandler.getInstance().handleUncatchException(thread, ex);
        LogUtil.i("异常处理完毕！");
        SystemClock.sleep(3000L);
        return true;
    }

    private void checkCache() {
        String str = (String) SharedPreferencesUtil.getInstance().getValue(Constant.LOG_CACHE, "");
        if (str == null || "".equals(str)) {
            return;
        }
        LogUtil.i("读取到日志缓存--->" + str);
        BugHandler.getInstance().handleCacheException(str);
    }

    public Context getContext() {
        return this.mContext;
    }

    public void reportCrash(Throwable e) {
        BugHandler.getInstance().handleCatchedException(e, null, null);
    }

    public void reportCatchedExcaption(Throwable e, String msg, String data) {
        BugHandler.getInstance().handleCatchedException(e, msg, data);
    }

    public void reportCatchedExcaption(Throwable e, String msg, String data, int actionType, boolean isHasPermission) {
        BugHandler.getInstance().handleCatchedException(e, msg, data, actionType, Boolean.valueOf(isHasPermission));
    }

    public void reportCatchedExcaption(Throwable e, String msg, String data, int actionType) {
        BugHandler.getInstance().handleCatchedException(e, msg, data, actionType, true);
    }

    private String getCurrentProcessName(Context context) {
        Object objInvoke;
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread", false, getClass().getClassLoader());
            if (Build.VERSION.SDK_INT >= 18) {
                Method declaredMethod = cls.getDeclaredMethod("currentProcessName", new Class[0]);
                declaredMethod.setAccessible(true);
                objInvoke = declaredMethod.invoke(null, new Object[0]);
            } else {
                Method declaredMethod2 = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
                declaredMethod2.setAccessible(true);
                Method declaredMethod3 = cls.getDeclaredMethod("getProcessName", new Class[0]);
                declaredMethod3.setAccessible(true);
                objInvoke = declaredMethod3.invoke(declaredMethod2.invoke(null, new Object[0]), new Object[0]);
            }
            if (objInvoke instanceof String) {
                return (String) objInvoke;
            }
        } catch (Throwable unused) {
        }
        return getCurrentProcessNameByProcessInfo(context);
    }

    private String getCurrentProcessNameByProcessInfo(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        int iMyPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null || runningAppProcesses.isEmpty()) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == iMyPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public boolean isMainProcess(Context context) {
        String currentProcessName = getCurrentProcessName(context);
        return currentProcessName != null && currentProcessName.equalsIgnoreCase(context.getApplicationInfo().processName);
    }

    public void setTrackHandler(TrackHandler handler) {
        this.mTrackHandler = handler;
    }

    public IHttpClient getHttpClient() {
        return this.mHttpClient;
    }

    public String getReportUrl() {
        return this.mReportUrl;
    }
}
