package com.sq.diagnostic.assistant.log.utils;

import android.content.Context;
import android.text.TextUtils;
import com.sqwan.bugless.util.FileUtil;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ExtraUtil {
    public static final int DAY_MILLIS = 86400000;

    public static boolean isMainProcess(Context context) throws Throwable {
        String processName = getProcessName();
        return !TextUtils.isEmpty(processName) && processName.equals(context.getPackageName());
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getProcessName() throws java.lang.Throwable {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 0
            r3 = 28
            if (r0 < r3) goto Ld
            java.lang.String r0 = android.app.Application.getProcessName()
            goto L31
        Ld:
            java.lang.String r0 = "android.app.ActivityThread"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.reflect.InvocationTargetException -> L24 java.lang.IllegalAccessException -> L26 java.lang.NoSuchMethodException -> L28 java.lang.ClassCastException -> L2a java.lang.ClassNotFoundException -> L2c
            java.lang.String r3 = "currentProcessName"
            java.lang.Class[] r4 = new java.lang.Class[r1]     // Catch: java.lang.reflect.InvocationTargetException -> L24 java.lang.IllegalAccessException -> L26 java.lang.NoSuchMethodException -> L28 java.lang.ClassCastException -> L2a java.lang.ClassNotFoundException -> L2c
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r3, r4)     // Catch: java.lang.reflect.InvocationTargetException -> L24 java.lang.IllegalAccessException -> L26 java.lang.NoSuchMethodException -> L28 java.lang.ClassCastException -> L2a java.lang.ClassNotFoundException -> L2c
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.reflect.InvocationTargetException -> L24 java.lang.IllegalAccessException -> L26 java.lang.NoSuchMethodException -> L28 java.lang.ClassCastException -> L2a java.lang.ClassNotFoundException -> L2c
            java.lang.Object r0 = r0.invoke(r2, r3)     // Catch: java.lang.reflect.InvocationTargetException -> L24 java.lang.IllegalAccessException -> L26 java.lang.NoSuchMethodException -> L28 java.lang.ClassCastException -> L2a java.lang.ClassNotFoundException -> L2c
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.reflect.InvocationTargetException -> L24 java.lang.IllegalAccessException -> L26 java.lang.NoSuchMethodException -> L28 java.lang.ClassCastException -> L2a java.lang.ClassNotFoundException -> L2c
            goto L31
        L24:
            r0 = move-exception
            goto L2d
        L26:
            r0 = move-exception
            goto L2d
        L28:
            r0 = move-exception
            goto L2d
        L2a:
            r0 = move-exception
            goto L2d
        L2c:
            r0 = move-exception
        L2d:
            r0.printStackTrace()
            r0 = r2
        L31:
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L38
            return r0
        L38:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            java.lang.String r3 = "/proc/self/cmdline"
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6d
            r3 = 256(0x100, float:3.59E-43)
            byte[] r4 = new byte[r3]     // Catch: java.io.IOException -> L69 java.lang.Throwable -> L7d
            r5 = 0
        L44:
            int r6 = r0.read()     // Catch: java.io.IOException -> L69 java.lang.Throwable -> L7d
            if (r6 <= 0) goto L53
            if (r5 >= r3) goto L53
            int r7 = r5 + 1
            byte r6 = (byte) r6     // Catch: java.io.IOException -> L69 java.lang.Throwable -> L7d
            r4[r5] = r6     // Catch: java.io.IOException -> L69 java.lang.Throwable -> L7d
            r5 = r7
            goto L44
        L53:
            if (r5 <= 0) goto L65
            java.lang.String r3 = new java.lang.String     // Catch: java.io.IOException -> L69 java.lang.Throwable -> L7d
            java.lang.String r6 = "UTF-8"
            r3.<init>(r4, r1, r5, r6)     // Catch: java.io.IOException -> L69 java.lang.Throwable -> L7d
            r0.close()     // Catch: java.io.IOException -> L60
            goto L64
        L60:
            r0 = move-exception
            r0.printStackTrace()
        L64:
            return r3
        L65:
            r0.close()     // Catch: java.io.IOException -> L78
            goto L7c
        L69:
            r1 = move-exception
            goto L6f
        L6b:
            r1 = move-exception
            goto L7f
        L6d:
            r1 = move-exception
            r0 = r2
        L6f:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L7d
            if (r0 == 0) goto L7c
            r0.close()     // Catch: java.io.IOException -> L78
            goto L7c
        L78:
            r0 = move-exception
            r0.printStackTrace()
        L7c:
            return r2
        L7d:
            r1 = move-exception
            r2 = r0
        L7f:
            if (r2 == 0) goto L89
            r2.close()     // Catch: java.io.IOException -> L85
            goto L89
        L85:
            r0 = move-exception
            r0.printStackTrace()
        L89:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.diagnostic.assistant.log.utils.ExtraUtil.getProcessName():java.lang.String");
    }

    public static String wrapProcessName() throws Throwable {
        String processName = getProcessName();
        if (TextUtils.isEmpty(processName)) {
            return null;
        }
        return processName.replaceAll(":", "_");
    }

    public static String getToday() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public static String getBeforeDay(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -i);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    public static String genZipFileName(String str, String str2) {
        return str + "-" + str2 + ".zip";
    }

    public static String getLogDateStart(String str) {
        int iIndexOf;
        if (!TextUtils.isEmpty(str) && (iIndexOf = str.indexOf("-")) > -1) {
            String strSubstring = str.substring(0, iIndexOf);
            if (validDate(strSubstring)) {
                return strSubstring;
            }
        }
        return null;
    }

    public static String getLogDateEnd(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int iIndexOf = str.indexOf("-");
        int iIndexOf2 = str.indexOf(FileUtil.FILE_EXTENSION_SEPARATOR);
        if (iIndexOf > -1 && iIndexOf2 > -1 && iIndexOf2 > iIndexOf) {
            String strSubstring = str.substring(iIndexOf + 1, iIndexOf2);
            if (validDate(strSubstring)) {
                return strSubstring;
            }
        }
        return null;
    }

    public static boolean validDate(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            new SimpleDateFormat("yyyyMMdd").parse(str);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static long getMillis(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            return new SimpleDateFormat("yyyyMMdd").parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static void clearExpiredFiles(String str, int i) {
        if (TextUtils.isEmpty(str) || i <= 0 || i == -1) {
            return;
        }
        try {
            long jCurrentTimeMillis = (((System.currentTimeMillis() / 86400000) * 86400000) - ((long) TimeZone.getDefault().getRawOffset())) - (((long) i) * 86400000);
            File file = new File(str);
            if (file.exists()) {
                if (file.isDirectory()) {
                    clearExpiredFilesByDir(file, jCurrentTimeMillis);
                } else {
                    clearExpiredFile(file, jCurrentTimeMillis);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void clearExpiredFilesByDir(File file, long j) {
        File[] fileArrListFiles;
        if (file == null || !file.exists() || !file.isDirectory() || (fileArrListFiles = file.listFiles()) == null || fileArrListFiles.length <= 0) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            clearExpiredFile(file2, j);
        }
    }

    private static void clearExpiredFile(File file, long j) {
        if (file == null || !file.exists() || !file.isFile() || file.lastModified() >= j) {
            return;
        }
        file.delete();
    }
}
