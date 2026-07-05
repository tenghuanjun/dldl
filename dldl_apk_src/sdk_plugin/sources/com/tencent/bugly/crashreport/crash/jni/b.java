package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sqwan.bugless.core.Constant;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class b {
    private static List<File> a = new ArrayList();

    private static Map<String, Integer> d(String str) {
        if (str == null) {
            return null;
        }
        try {
            HashMap map = new HashMap();
            for (String str2 : str.split(",")) {
                String[] strArrSplit = str2.split(":");
                if (strArrSplit.length != 2) {
                    x.e("error format at %s", str2);
                    return null;
                }
                map.put(strArrSplit[0], Integer.valueOf(Integer.parseInt(strArrSplit[1])));
            }
            return map;
        } catch (Exception e) {
            x.e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    protected static String a(String str) {
        if (str == null) {
            return "";
        }
        String[] strArrSplit = str.split(ShellAdbUtils.COMMAND_LINE_END);
        if (strArrSplit == null || strArrSplit.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArrSplit) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                sb.append(str2);
                sb.append(ShellAdbUtils.COMMAND_LINE_END);
            }
        }
        return sb.toString();
    }

    private static CrashDetailBean a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        String str;
        String str2;
        HashMap map2;
        if (map == null) {
            return null;
        }
        if (com.tencent.bugly.crashreport.common.info.a.a(context) == null) {
            x.e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str3 = map.get("intStateStr");
        if (str3 == null || str3.trim().length() <= 0) {
            x.e("no intStateStr", new Object[0]);
            return null;
        }
        Map<String, Integer> mapD = d(str3);
        if (mapD == null) {
            x.e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            mapD.get("sino").intValue();
            mapD.get("sud").intValue();
            String str4 = map.get("soVersion");
            if (str4 == null) {
                x.e("error format at version", new Object[0]);
                return null;
            }
            String str5 = map.get("errorAddr");
            String str6 = "unknown";
            String str7 = str5 == null ? "unknown" : str5;
            String str8 = map.get("codeMsg");
            if (str8 == null) {
                str8 = "unknown";
            }
            String str9 = map.get("tombPath");
            String str10 = str9 == null ? "unknown" : str9;
            String str11 = map.get("signalName");
            if (str11 == null) {
                str11 = "unknown";
            }
            map.get("errnoMsg");
            String str12 = map.get(Constant.CRASH_STACK);
            if (str12 == null) {
                str12 = "unknown";
            }
            String str13 = map.get("jstack");
            if (str13 != null) {
                str12 = str12 + "java:\n" + str13;
            }
            Integer num = mapD.get("sico");
            if (num == null || num.intValue() <= 0) {
                str = str8;
                str2 = str11;
            } else {
                str2 = str11 + "(" + str8 + ")";
                str = "KERNEL";
            }
            String str14 = map.get("nativeLog");
            byte[] bArrA = (str14 == null || str14.isEmpty()) ? null : z.a((File) null, str14, "BuglyNativeLog.txt");
            String str15 = map.get("sendingProcess");
            if (str15 == null) {
                str15 = "unknown";
            }
            Integer num2 = mapD.get("spd");
            if (num2 != null) {
                str15 = str15 + "(" + num2 + ")";
            }
            String str16 = str15;
            String str17 = map.get("threadName");
            if (str17 == null) {
                str17 = "unknown";
            }
            Integer num3 = mapD.get("et");
            if (num3 != null) {
                str17 = str17 + "(" + num3 + ")";
            }
            String str18 = str17;
            String str19 = map.get("processName");
            if (str19 != null) {
                str6 = str19;
            }
            Integer num4 = mapD.get("ep");
            if (num4 != null) {
                str6 = str6 + "(" + num4 + ")";
            }
            String str20 = str6;
            String str21 = map.get("key-value");
            if (str21 != null) {
                HashMap map3 = new HashMap();
                String[] strArrSplit = str21.split(ShellAdbUtils.COMMAND_LINE_END);
                int length = strArrSplit.length;
                int i = 0;
                while (i < length) {
                    String[] strArrSplit2 = strArrSplit[i].split(SimpleComparison.EQUAL_TO_OPERATION);
                    String[] strArr = strArrSplit;
                    if (strArrSplit2.length == 2) {
                        map3.put(strArrSplit2[0], strArrSplit2[1]);
                    }
                    i++;
                    strArrSplit = strArr;
                }
                map2 = map3;
            } else {
                map2 = null;
            }
            CrashDetailBean crashDetailBeanPackageCrashDatas = nativeExceptionHandler.packageCrashDatas(str20, str18, (((long) mapD.get("etms").intValue()) / 1000) + (((long) mapD.get("ets").intValue()) * 1000), str2, str7, a(str12), str, str16, str10, map.get("sysLogPath"), map.get("jniLogPath"), str4, bArrA, map2, false, false);
            if (crashDetailBeanPackageCrashDatas != null) {
                String str22 = map.get("userId");
                if (str22 != null) {
                    x.c("[Native record info] userId: %s", str22);
                    crashDetailBeanPackageCrashDatas.m = str22;
                }
                String str23 = map.get("sysLog");
                if (str23 != null) {
                    crashDetailBeanPackageCrashDatas.w = str23;
                }
                String str24 = map.get(FeedBackConstants.KEY_FB_APPVERSION);
                if (str24 != null) {
                    x.c("[Native record info] appVersion: %s", str24);
                    crashDetailBeanPackageCrashDatas.f = str24;
                }
                String str25 = map.get("isAppForeground");
                if (str25 != null) {
                    x.c("[Native record info] isAppForeground: %s", str25);
                    crashDetailBeanPackageCrashDatas.N = str25.equalsIgnoreCase("true");
                }
                String str26 = map.get("launchTime");
                if (str26 != null) {
                    x.c("[Native record info] launchTime: %s", str26);
                    try {
                        crashDetailBeanPackageCrashDatas.M = Long.parseLong(str26);
                    } catch (NumberFormatException e) {
                        if (!x.a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
                crashDetailBeanPackageCrashDatas.z = null;
                crashDetailBeanPackageCrashDatas.k = true;
            }
            return crashDetailBeanPackageCrashDatas;
        } catch (Throwable th) {
            x.e("error format", new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    private static String a(BufferedInputStream bufferedInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        if (bufferedInputStream == null) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(1024);
            while (true) {
                try {
                    int i = bufferedInputStream.read();
                    if (i == -1) {
                        byteArrayOutputStream.close();
                        break;
                    }
                    if (i == 0) {
                        String str = new String(byteArrayOutputStream.toByteArray(), "UTf-8");
                        byteArrayOutputStream.close();
                        return str;
                    }
                    byteArrayOutputStream.write(i);
                } catch (Throwable th) {
                    th = th;
                    try {
                        x.a(th);
                        return null;
                    } finally {
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [boolean] */
    public static CrashDetailBean a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) throws Throwable {
        BufferedInputStream bufferedInputStream;
        String str2;
        String strA;
        BufferedInputStream bufferedInputStream2 = 0;
        if (context == null || str == null || nativeExceptionHandler == null) {
            x.e("get eup record file args error", new Object[0]);
            return null;
        }
        File file = new File(str, "rqd_record.eup");
        if (file.exists()) {
            ?? CanRead = file.canRead();
            try {
                if (CanRead != 0) {
                    try {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                        try {
                            String strA2 = a(bufferedInputStream);
                            if (strA2 != null && strA2.equals("NATIVE_RQD_REPORT")) {
                                HashMap map = new HashMap();
                                loop0: while (true) {
                                    str2 = null;
                                    while (true) {
                                        strA = a(bufferedInputStream);
                                        if (strA == null) {
                                            break loop0;
                                        }
                                        if (str2 == null) {
                                            str2 = strA;
                                        }
                                    }
                                    map.put(str2, strA);
                                }
                                if (str2 != null) {
                                    x.e("record not pair! drop! %s", str2);
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    return null;
                                }
                                CrashDetailBean crashDetailBeanA = a(context, map, nativeExceptionHandler);
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                                return crashDetailBeanA;
                            }
                            x.e("record read fail! %s", strA2);
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            return null;
                        } catch (IOException e4) {
                            e = e4;
                            e.printStackTrace();
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            return null;
                        }
                    } catch (IOException e6) {
                        e = e6;
                        bufferedInputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedInputStream2 != 0) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e7) {
                                e7.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = CanRead;
            }
        }
        return null;
    }

    private static String b(String str, String str2) {
        BufferedReader bufferedReaderA = z.a(str, "reg_record.txt");
        if (bufferedReaderA == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = bufferedReaderA.readLine();
            if (line != null && line.startsWith(str2)) {
                int i = 18;
                int i2 = 0;
                int length = 0;
                while (true) {
                    String line2 = bufferedReaderA.readLine();
                    if (line2 == null) {
                        break;
                    }
                    if (i2 % 4 == 0) {
                        if (i2 > 0) {
                            sb.append(ShellAdbUtils.COMMAND_LINE_END);
                        }
                        sb.append("  ");
                    } else {
                        if (line2.length() > 16) {
                            i = 28;
                        }
                        sb.append("                ".substring(0, i - length));
                    }
                    length = line2.length();
                    sb.append(line2);
                    i2++;
                }
                sb.append(ShellAdbUtils.COMMAND_LINE_END);
                return sb.toString();
            }
            if (bufferedReaderA != null) {
                try {
                    bufferedReaderA.close();
                } catch (Exception e) {
                    x.a(e);
                }
            }
            return null;
        } catch (Throwable th) {
            try {
                x.a(th);
                if (bufferedReaderA != null) {
                    try {
                        bufferedReaderA.close();
                    } catch (Exception e2) {
                        x.a(e2);
                    }
                }
                return null;
            } finally {
                if (bufferedReaderA != null) {
                    try {
                        bufferedReaderA.close();
                    } catch (Exception e3) {
                        x.a(e3);
                    }
                }
            }
        }
    }

    private static String c(String str, String str2) {
        BufferedReader bufferedReaderA = z.a(str, "map_record.txt");
        if (bufferedReaderA == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = bufferedReaderA.readLine();
            if (line != null && line.startsWith(str2)) {
                while (true) {
                    String line2 = bufferedReaderA.readLine();
                    if (line2 == null) {
                        break;
                    }
                    sb.append("  ");
                    sb.append(line2);
                    sb.append(ShellAdbUtils.COMMAND_LINE_END);
                }
                return sb.toString();
            }
            if (bufferedReaderA != null) {
                try {
                    bufferedReaderA.close();
                } catch (Exception e) {
                    x.a(e);
                }
            }
            return null;
        } catch (Throwable th) {
            try {
                x.a(th);
                if (bufferedReaderA != null) {
                    try {
                        bufferedReaderA.close();
                    } catch (Exception e2) {
                        x.a(e2);
                    }
                }
                return null;
            } finally {
                if (bufferedReaderA != null) {
                    try {
                        bufferedReaderA.close();
                    } catch (Exception e3) {
                        x.a(e3);
                    }
                }
            }
        }
    }

    public static String a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String strB = b(str, str2);
        if (strB != null && !strB.isEmpty()) {
            sb.append("Register infos:\n");
            sb.append(strB);
        }
        String strC = c(str, str2);
        if (strC != null && !strC.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(ShellAdbUtils.COMMAND_LINE_END);
            }
            sb.append("System SO infos:\n");
            sb.append(strC);
        }
        return sb.toString();
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str, "backup_record.txt");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public static void c(String str) {
        File[] fileArrListFiles;
        if (str == null) {
            return;
        }
        try {
            File file = new File(str);
            if (file.canRead() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                for (File file2 : fileArrListFiles) {
                    if (file2.canRead() && file2.canWrite() && file2.length() == 0) {
                        file2.delete();
                        x.c("Delete empty record file %s", file2.getAbsoluteFile());
                    }
                }
            }
        } catch (Throwable th) {
            x.a(th);
        }
    }

    public static void a(boolean z, String str) {
        if (str != null) {
            a.add(new File(str, "rqd_record.eup"));
            a.add(new File(str, "reg_record.txt"));
            a.add(new File(str, "map_record.txt"));
            a.add(new File(str, "backup_record.txt"));
            if (z) {
                c(str);
            }
        }
        List<File> list = a;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (File file : a) {
            if (file.exists() && file.canWrite()) {
                file.delete();
                x.c("Delete record file %s", file.getAbsoluteFile());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.String] */
    public static String a(String str, int i, String str2, boolean z) {
        BufferedReader bufferedReader = null;
        if (str != null && i > 0) {
            File file = new File(str);
            if (file.exists() && file.canRead()) {
                x.a("Read system log from native record file(length: %s bytes): %s", Long.valueOf(file.length()), file.getAbsolutePath());
                a.add(file);
                x.c("Add this record file to list for cleaning lastly.", new Object[0]);
                if (str2 == null) {
                    return z.a(new File(str), i, z);
                }
                String sb = new StringBuilder();
                try {
                    try {
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
                        while (true) {
                            try {
                                String line = bufferedReader2.readLine();
                                if (line == null) {
                                    break;
                                }
                                if (Pattern.compile(str2 + "[ ]*:").matcher(line).find()) {
                                    sb.append(line);
                                    sb.append(ShellAdbUtils.COMMAND_LINE_END);
                                }
                                if (i > 0 && sb.length() > i) {
                                    if (z) {
                                        sb.delete(i, sb.length());
                                        break;
                                    }
                                    sb.delete(0, sb.length() - i);
                                }
                            } catch (Throwable th) {
                                th = th;
                                bufferedReader = bufferedReader2;
                                try {
                                    x.a(th);
                                    sb.append("\n[error:" + th.toString() + "]");
                                    String string = sb.toString();
                                    if (bufferedReader == null) {
                                        return string;
                                    }
                                    bufferedReader.close();
                                    sb = string;
                                } catch (Throwable th2) {
                                    if (bufferedReader != null) {
                                        try {
                                            bufferedReader.close();
                                        } catch (Exception e) {
                                            x.a(e);
                                        }
                                    }
                                    throw th2;
                                }
                            }
                        }
                        String string2 = sb.toString();
                        bufferedReader2.close();
                        sb = string2;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                    return sb;
                } catch (Exception e2) {
                    x.a(e2);
                    return sb;
                }
            }
        }
        return null;
    }
}
