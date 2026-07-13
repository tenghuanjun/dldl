package com.volcengine.i;

import android.text.TextUtils;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.SDKContext;
import com.volcengine.common.contant.CommonConstants;
import com.volcengine.j.j;
import com.volcengine.j.n;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.Thread;
import java.util.Collections;
import java.util.Locale;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a {
    private static String a() {
        return n.a("CloudPlayerPreference").getString("throwableMessage", "");
    }

    private static String a(Throwable th) throws IOException {
        if (th == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            th.printStackTrace(new PrintStream(byteArrayOutputStream));
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toString();
        } catch (Throwable th2) {
            byteArrayOutputStream.close();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, Thread thread, Throwable th) {
        try {
            try {
                String str = String.format(Locale.getDefault(), " %d  %s ", Long.valueOf(System.currentTimeMillis()), a(th));
                boolean zA = a(str);
                a(false);
                AcLog.e("CrashHandler", "UncaughtException: threadId: " + thread.getId() + ", threadName: " + thread.getName() + ", log: " + str + "isSuccess:" + zA);
                if (uncaughtExceptionHandler == null) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (uncaughtExceptionHandler == null) {
                    return;
                }
            }
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } catch (Throwable th2) {
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
            throw th2;
        }
    }

    private static void a(boolean z) {
        n.a("CloudPlayerPreference").edit().putBoolean("hasReported", z).apply();
    }

    private static boolean a(String str) {
        return n.a("CloudPlayerPreference").edit().putString("throwableMessage", str).commit();
    }

    private static boolean b() {
        return n.a("CloudPlayerPreference").getBoolean("hasReported", false);
    }

    public static void c() {
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.volcengine.i.a$$ExternalSyntheticLambda0
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th) {
                a.a(defaultUncaughtExceptionHandler, thread, th);
            }
        });
    }

    public static void d() {
        String strA = a();
        if (TextUtils.isEmpty(strA) || b()) {
            return;
        }
        SDKContext.getMonitorService().onEvent(CommonConstants.EVENT_SDK_CRASH, j.b(Collections.singletonMap(CommonConstants.M_EVENT_CRASH, strA)));
        a(true);
    }
}
