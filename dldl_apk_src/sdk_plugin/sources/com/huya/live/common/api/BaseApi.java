package com.huya.live.common.api;

import android.os.Looper;
import com.duowan.HUYA.UserId;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.live.common.api.report.ReportApi;
import com.huya.live.common.api.signal.SignalCenterApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BaseApi {
    private static IBaseApiCallback mBaseApiCallback;
    private static OnCrashListener sOnCrashListener;
    private static ReportApi sReportApi;
    private static SignalCenterApi sSignalCenterApi;

    public interface IBaseApiCallback {
        UserId getUserId();
    }

    public interface OnCrashListener {
        void onCrashIfDebug(String str, Throwable th);
    }

    public static void init(IBaseApiCallback iBaseApiCallback, OnCrashListener onCrashListener) {
        mBaseApiCallback = iBaseApiCallback;
        sOnCrashListener = onCrashListener;
    }

    public static void crashIfDebug(String str, Object... objArr) {
        crashIfDebug(null, str, objArr);
    }

    public static void crashIfDebug(Throwable th, String str, Object... objArr) {
        String str2 = String.format(str, objArr);
        L.error("crashIfDebug: %s", str2);
        OnCrashListener onCrashListener = sOnCrashListener;
        if (onCrashListener != null) {
            onCrashListener.onCrashIfDebug(str2, th);
        }
        if (ArkValue.debuggable()) {
            if (th != null) {
                throw new RuntimeException(str2, th);
            }
        }
    }

    public static void crashIfInMainThreadDebug(String str, Object... objArr) {
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            crashIfDebug(null, str, objArr);
        }
    }

    public static void crashIfNotInMainThreadDebug(String str, Object... objArr) {
        if (Thread.currentThread().getId() != Looper.getMainLooper().getThread().getId()) {
            crashIfDebug(null, str, objArr);
        }
    }

    public static UserId getUserId() {
        IBaseApiCallback iBaseApiCallback = mBaseApiCallback;
        if (iBaseApiCallback == null) {
            return null;
        }
        return iBaseApiCallback.getUserId();
    }

    public static void setOnCrashListener(OnCrashListener onCrashListener) {
        sOnCrashListener = onCrashListener;
    }

    public static SignalCenterApi getSignalCenterApi() {
        if (sSignalCenterApi == null) {
            crashIfDebug("%s fail(NullPointerException)!", "SignalCenterApi");
        }
        return sSignalCenterApi;
    }

    public static ReportApi getReportApi() {
        if (sReportApi == null) {
            crashIfDebug("%s fail(NullPointerException)!", "sReportApi");
        }
        return sReportApi;
    }

    public static void setReportApi(ReportApi reportApi) {
        sReportApi = reportApi;
    }

    public static void setSignalCenterApi(SignalCenterApi signalCenterApi) {
        sSignalCenterApi = signalCenterApi;
    }
}
