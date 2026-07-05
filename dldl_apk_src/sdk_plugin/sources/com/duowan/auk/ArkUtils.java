package com.duowan.auk;

import android.os.Looper;
import com.duowan.auk.asignal.SignalCenter;
import com.duowan.auk.util.L;
import com.huya.live.common.api.BaseApi;
import java.util.concurrent.TimeUnit;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ArkUtils {
    private static final String TAG = "ArkUtils";

    public static <T> void call(T t) {
        L.debug(ArkUtils.class, "call interface: %s", t);
        SignalCenter.send(t);
    }

    public static <T> void send(T t) {
        send(t, null, true);
    }

    public static <T> void send(T t, Object obj) {
        send(t, obj, true);
    }

    public static <T> void send(T t, Object obj, boolean z) {
        if (z) {
            Object[] objArr = new Object[2];
            objArr[0] = t;
            objArr[1] = obj == null ? AbstractJsonLexerKt.NULL : obj;
            L.debug(ArkUtils.class, "send callback: %s, receiver: %s", objArr);
        }
        SignalCenter.send(t, obj);
    }

    public static <T> void register(T t) {
        SignalCenter.register(t);
    }

    public static <T> void unregister(T t) {
        SignalCenter.unregister(t);
    }

    public static long currentTimeSeconds() {
        return TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis());
    }

    public static void crashIfDebug(String str, Object... objArr) {
        crashIfDebug(null, str, objArr);
    }

    public static void crashIfDebug(Throwable th, String str, Object... objArr) {
        BaseApi.crashIfDebug(th, str, objArr);
    }

    public static void crashIfInMainThreadDebug(String str, Object... objArr) {
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            crashIfDebug(str, objArr);
        }
    }

    public static void crashIfNotInMainThreadDebug(String str, Object... objArr) {
        if (Thread.currentThread().getId() != Looper.getMainLooper().getThread().getId()) {
            crashIfDebug(str, objArr);
        }
    }
}
