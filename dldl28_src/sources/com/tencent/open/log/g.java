package com.tencent.open.log;

import android.text.format.Time;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import org.apache.commons.lang3.ClassUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f1075a = new g();

    public final String a(int i) {
        if (i == 1) {
            return ExifInterface.GPS_MEASUREMENT_INTERRUPTED;
        }
        if (i == 2) {
            return "D";
        }
        if (i == 4) {
            return "I";
        }
        if (i == 8) {
            return ExifInterface.LONGITUDE_WEST;
        }
        if (i == 16) {
            return ExifInterface.LONGITUDE_EAST;
        }
        if (i == 32) {
            return ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
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
        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
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
