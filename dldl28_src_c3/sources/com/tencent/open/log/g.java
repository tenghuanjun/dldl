package com.tencent.open.log;

import android.text.format.Time;
import android.util.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class g {
    public static final g a = new g();

    public final String a(int i) {
        if (i == 1) {
            return "V";
        }
        if (i == 2) {
            return "D";
        }
        if (i == 4) {
            return "I";
        }
        if (i == 8) {
            return "W";
        }
        if (i == 16) {
            return "E";
        }
        if (i == 32) {
            return "A";
        }
        return "-";
    }

    public String a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        long j2 = j % 1000;
        Time time = new Time();
        time.set(j);
        StringBuilder sb = new StringBuilder();
        sb.append(a(i));
        sb.append('/');
        sb.append(time.format("%Y-%m-%d %H:%M:%S"));
        sb.append('.');
        if (j2 < 10) {
            sb.append("00");
        } else if (j2 < 100) {
            sb.append('0');
        }
        sb.append(j2);
        sb.append(" [");
        if (thread == null) {
            sb.append("N/A");
        } else {
            sb.append(thread.getName());
        }
        sb.append("][");
        sb.append(str);
        sb.append("] ");
        sb.append(str2);
        sb.append('\n');
        if (th != null) {
            sb.append("* Exception : \n");
            sb.append(Log.getStackTraceString(th));
            sb.append('\n');
        }
        return sb.toString();
    }
}
