package com.bytedance.bdtracker;

import com.bytedance.applog.log.IAppLogLogger;
import com.bytedance.applog.log.LoggerImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final class j0 extends Throwable {
    @Override // java.lang.Throwable
    public void printStackTrace() {
        String string;
        IAppLogLogger iAppLogLoggerGlobal = LoggerImpl.global();
        StringBuilder sbA = a.a("AppLog assert failed: ");
        Throwable cause = getCause();
        if (cause == null || (string = cause.getMessage()) == null) {
            StackTraceElement[] stackTrace = getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "stackTrace");
            string = !(stackTrace.length == 0) ? getStackTrace()[0].toString() : "function interrupt";
        }
        sbA.append((Object) string);
        iAppLogLoggerGlobal.ast(sbA.toString(), getCause(), new Object[0]);
    }
}
