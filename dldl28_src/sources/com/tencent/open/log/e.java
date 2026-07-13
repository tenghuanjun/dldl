package com.tencent.open.log;

import android.util.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
public final class e extends Tracer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e f1073a = new e();

    @Override // com.tencent.open.log.Tracer
    protected void doTrace(int i, Thread thread, long j, String str, String str2, Throwable th) {
        if (i == 1) {
            Log.v(str, str2, th);
            return;
        }
        if (i == 2) {
            Log.d(str, str2, th);
            return;
        }
        if (i == 4) {
            Log.i(str, str2, th);
            return;
        }
        if (i == 8) {
            Log.w(str, str2, th);
        } else if (i == 16) {
            Log.e(str, str2, th);
        } else {
            if (i != 32) {
                return;
            }
            Log.e(str, str2, th);
        }
    }
}
