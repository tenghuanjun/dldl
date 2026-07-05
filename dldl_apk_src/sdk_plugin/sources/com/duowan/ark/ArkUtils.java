package com.duowan.ark;

import com.duowan.ark.def.Properties;
import com.duowan.ark.util.Config;
import com.duowan.ark.util.DebugUtils;
import com.duowan.ark.util.KLog;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusException;
import java.util.concurrent.TimeUnit;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ArkUtils {
    private static final String TAG = "ArkUtils";

    public static boolean networkAvailable() {
        return Properties.networkAvailable.get().booleanValue();
    }

    public static <T> void call(T t) {
        KLog.debug(ArkUtils.class, "call interface: %s", t);
        EventBus.getDefault().post(t);
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
            if (obj == null) {
                obj = AbstractJsonLexerKt.NULL;
            }
            objArr[1] = obj;
            KLog.debug(ArkUtils.class, "send callback: %s, receiver: %s", objArr);
        }
        EventBus.getDefault().post(t);
    }

    public static <T> void register(T t) {
        try {
            EventBus.getDefault().register(t);
        } catch (EventBusException unused) {
        }
    }

    public static <T> void unregister(T t) {
        try {
            EventBus.getDefault().unregister(t);
        } catch (EventBusException unused) {
        }
    }

    public static long currentTimeSeconds() {
        return TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis());
    }

    public static Config getConfig() {
        return Config.getInstance(ArkValue.gContext);
    }

    public static void crashIfDebug(String str, Object... objArr) {
        DebugUtils.crashIfDebug(str, objArr);
    }

    public static void crashIfDebug(Throwable th, String str, Object... objArr) {
        DebugUtils.crashIfDebug(th, str, objArr);
    }

    public static void crashIfDebug(boolean z, String str, Object... objArr) {
        if (z) {
            return;
        }
        crashIfDebug((Throwable) null, str, objArr);
    }

    public static void crashIfInMainThreadDebug(String str, Object... objArr) {
        DebugUtils.crashIfInMainThreadDebug(str, objArr);
    }

    public static void crashIfNotInMainThreadDebug(String str, Object... objArr) {
        DebugUtils.crashIfNotInMainThreadDebug(str, objArr);
    }
}
