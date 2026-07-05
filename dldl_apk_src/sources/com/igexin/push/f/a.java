package com.igexin.push.f;

import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    private static final String a = "BasicCheck";

    private static String a(int i) {
        String strTrim;
        try {
            strTrim = e(String.format("/proc/%d/cmdline", Integer.valueOf(i))).trim();
        } catch (Exception unused) {
            strTrim = null;
        }
        try {
            if (TextUtils.isEmpty(strTrim)) {
                return e(String.format("/proc/%d/stat", Integer.valueOf(i))).split("\\s+")[1].replace("(", "").replace(")", "");
            }
        } catch (Exception unused2) {
        }
        return strTrim;
    }

    public static boolean a() {
        try {
            Class.forName("com.igexin.push.f.h");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean a(String str) {
        if (!TextUtils.isEmpty(com.igexin.push.core.e.aI)) {
            String[] strArrSplit = com.igexin.push.core.e.aI.split(com.igexin.push.core.b.aj);
            if (strArrSplit.length != 0) {
                String str2 = com.igexin.push.core.e.D;
                if (TextUtils.isEmpty(str2)) {
                    return false;
                }
                com.igexin.b.a.c.a.a("BasicCheck|brand = ".concat(String.valueOf(str2)), new Object[0]);
                for (String str3 : strArrSplit) {
                    if (!TextUtils.isEmpty(str3)) {
                        String[] strArrSplit2 = str3.split(":");
                        if (!str2.equalsIgnoreCase(strArrSplit2[0])) {
                            continue;
                        } else if (strArrSplit2.length == 3) {
                            if (!Boolean.parseBoolean(strArrSplit2[1])) {
                                return true;
                            }
                        } else if (strArrSplit2.length == 4) {
                            String str4 = strArrSplit2[1];
                            boolean z = Boolean.parseBoolean(strArrSplit2[2]);
                            if (str.equals(str4) && !z) {
                                return true;
                            }
                        } else {
                            continue;
                        }
                    }
                }
                return false;
            }
        }
        com.igexin.b.a.c.a.a("BasicCheck|pMBlacklist is empty or null", new Object[0]);
        return false;
    }

    public static boolean a(List<String> list) {
        if (list != null && !list.isEmpty() && !TextUtils.isEmpty(com.igexin.push.core.e.aH)) {
            List listAsList = Arrays.asList(com.igexin.push.core.e.aH.split(com.igexin.push.core.b.aj));
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (listAsList.contains(it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean b() {
        try {
            for (String str : com.igexin.push.core.e.aF.split(com.igexin.push.core.b.aj)) {
                if (com.igexin.push.core.e.C.toLowerCase().contains(str.toLowerCase())) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(com.igexin.push.core.e.aH)) {
            return false;
        }
        for (String str2 : com.igexin.push.core.e.aH.split(com.igexin.push.core.b.aj)) {
            if (str.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean c() {
        if (!TextUtils.isEmpty(com.igexin.push.core.e.aH)) {
            for (String str : com.igexin.push.core.e.aH.split(com.igexin.push.core.b.aj)) {
                if (c(str)) {
                    com.igexin.b.a.c.a.a("BasicCheck|" + str + " in gactivityblacklist", new Object[0]);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean c(String str) {
        try {
            com.igexin.push.core.e.i.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static String d(String str) {
        if (Pattern.compile("^([a-zA-Z]+[.][a-zA-Z]+)[.]*.*").matcher(str).find() && !str.startsWith("com.android") && !str.startsWith("android.process") && !str.startsWith("org.")) {
            if (str.contains(":")) {
                str = str.split(":")[0];
            }
            try {
                int i = com.igexin.push.core.e.i.getPackageManager().getPackageInfo(str, 0).applicationInfo.flags;
                if ((i & 1) == 0 || (i & 128) != 0) {
                    return str;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return null;
    }

    public static List<String> d() {
        ArrayList arrayList = new ArrayList();
        for (File file : new File("/proc").listFiles(new FileFilter() { // from class: com.igexin.push.f.a.1
            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                return file2.isDirectory() && Integer.parseInt(file2.getName()) >= 2000;
            }
        })) {
            try {
                String strA = a(Integer.parseInt(file.getName()));
                if (!TextUtils.isEmpty(strA)) {
                    strA = d(strA);
                }
                if (!TextUtils.isEmpty(strA) && !arrayList.contains(strA)) {
                    arrayList.add(strA);
                }
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    private static String e(String str) throws Throwable {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        try {
            StringBuilder sb = new StringBuilder();
            fileReader = new FileReader(str);
            try {
                bufferedReader = new BufferedReader(fileReader);
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                        sb.append("\n");
                    } catch (Exception unused) {
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Exception unused2) {
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception unused3) {
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Exception unused4) {
                            }
                        }
                        if (bufferedReader == null) {
                            throw th;
                        }
                        try {
                            bufferedReader.close();
                            throw th;
                        } catch (Exception unused5) {
                            throw th;
                        }
                    }
                }
                if (sb.length() > 2) {
                    String strSubstring = sb.substring(0, sb.length() - 2);
                    try {
                        fileReader.close();
                    } catch (Exception unused6) {
                    }
                    try {
                        bufferedReader.close();
                    } catch (Exception unused7) {
                    }
                    return strSubstring;
                }
                String string = sb.toString();
                try {
                    fileReader.close();
                } catch (Exception unused8) {
                }
                try {
                    bufferedReader.close();
                } catch (Exception unused9) {
                }
                return string;
            } catch (Exception unused10) {
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        } catch (Exception unused11) {
            bufferedReader = null;
            fileReader = null;
        } catch (Throwable th4) {
            fileReader = null;
            th = th4;
            bufferedReader = null;
        }
    }

    private static List<String> e() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Collections.emptyList();
        }
        List<String> listD = d();
        int i = Build.VERSION.SDK_INT;
        com.igexin.b.a.c.a.a("BasicCheck|" + Build.VERSION.SDK_INT + ",running = " + listD.toString(), new Object[0]);
        return listD;
    }
}
