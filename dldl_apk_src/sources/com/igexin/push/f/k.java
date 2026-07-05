package com.igexin.push.f;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.igexin.base.util.IOUtils;
import com.igexin.sdk.main.SdkInitSwitch;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class k {
    private static final String a = "FileUtils";
    private static final String c = "init_c1.pid";
    private static final Object b = new Object();
    private static String d = "";

    /* JADX WARN: Removed duplicated region for block: B:35:0x00bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a() {
        /*
            r0 = 0
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            java.lang.String r3 = com.igexin.push.core.e.ab     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            boolean r3 = r2.exists()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            if (r3 != 0) goto L32
            boolean r3 = r2.createNewFile()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            if (r3 != 0) goto L32
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            java.lang.String r4 = "FileUtils | create file : "
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            r3.append(r2)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            java.lang.String r2 = " failed !!!"
            r3.append(r2)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            java.lang.String r2 = r3.toString()     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            com.igexin.b.a.c.a.a(r2, r3)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            return
        L32:
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            java.lang.String r3 = com.igexin.push.core.e.ab     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L98
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            java.lang.String r3 = "v01"
            r1.<init>(r3)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            java.lang.String r3 = com.igexin.push.core.e.E     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            r1.append(r3)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            r3.<init>()     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            r3.append(r1)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            long r4 = com.igexin.push.core.e.w     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            r3.append(r4)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            java.lang.String r1 = "|"
            r3.append(r1)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            java.lang.String r1 = com.igexin.push.core.e.a     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            r3.append(r1)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            java.lang.String r1 = "|"
            r3.append(r1)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            java.lang.String r1 = com.igexin.push.core.e.x     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            r3.append(r1)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            java.lang.String r1 = "|"
            r3.append(r1)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            com.igexin.push.core.p.a.a()     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            android.content.Context r1 = com.igexin.push.core.e.i     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            java.lang.String r1 = com.igexin.push.core.p.c(r1)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            r3.append(r1)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            java.lang.String r1 = r3.toString()     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            byte[] r1 = r1.getBytes()     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            java.lang.String r3 = com.igexin.push.core.e.J     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            byte[] r1 = com.igexin.b.a.a.a.b(r1, r3)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            r2.write(r1)     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> Lb9
            r2.close()     // Catch: java.io.IOException -> L92
        L92:
            return
        L93:
            r1 = move-exception
            goto L9c
        L95:
            r0 = move-exception
            r2 = r1
            goto Lba
        L98:
            r2 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
        L9c:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r4 = "FileUtils | "
            r3.<init>(r4)     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lb9
            r3.append(r1)     // Catch: java.lang.Throwable -> Lb9
            java.lang.String r1 = r3.toString()     // Catch: java.lang.Throwable -> Lb9
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> Lb9
            com.igexin.b.a.c.a.a(r1, r0)     // Catch: java.lang.Throwable -> Lb9
            if (r2 == 0) goto Lb8
            r2.close()     // Catch: java.io.IOException -> Lb8
        Lb8:
            return
        Lb9:
            r0 = move-exception
        Lba:
            if (r2 == 0) goto Lbf
            r2.close()     // Catch: java.io.IOException -> Lbf
        Lbf:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.f.k.a():void");
    }

    public static void a(byte[] bArr, String str, boolean z) {
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(new File(str), z);
            try {
                fileOutputStream2.write(bArr);
                try {
                    fileOutputStream2.close();
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception unused3) {
                    }
                }
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean a(Context context) {
        return (com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.b, new boolean[0]) || new SdkInitSwitch(context).isSwitchOn()) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r6v6 */
    public static byte[] a(String str) throws Throwable {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] byteArray = null;
        if (!new File((String) str).exists()) {
            com.igexin.b.a.c.a.a("FileUtils|get data from file = " + ((String) str) + " file not exist ######", new Object[0]);
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            try {
                fileInputStream = new FileInputStream((String) str);
            } catch (Throwable th) {
                th = th;
            }
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception e) {
                e = e;
                byteArrayOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                str = 0;
                IOUtils.close(fileInputStream);
                IOUtils.close(str);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            byteArrayOutputStream = null;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            str = 0;
            fileInputStream = null;
        }
        while (true) {
            try {
                int i = fileInputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            } catch (Exception e3) {
                e = e3;
                com.igexin.b.a.c.a.a("FileUtils|" + e.toString(), new Object[0]);
            }
            IOUtils.close(fileInputStream);
            IOUtils.close(byteArrayOutputStream);
            return byteArray;
        }
        byteArray = byteArrayOutputStream.toByteArray();
        IOUtils.close(fileInputStream);
        IOUtils.close(byteArrayOutputStream);
        return byteArray;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b() throws java.lang.Throwable {
        /*
            java.lang.String r0 = com.igexin.push.core.e.ab
            r0 = 0
            r1 = 0
            java.lang.String r2 = com.igexin.push.core.e.ab     // Catch: java.lang.Exception -> L37
            byte[] r2 = a(r2)     // Catch: java.lang.Exception -> L37
            if (r2 != 0) goto L14
            java.lang.String r2 = "FileUtils | read file cid id = null"
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L37
            com.igexin.b.a.c.a.a(r2, r3)     // Catch: java.lang.Exception -> L37
            return r1
        L14:
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Exception -> L37
            java.lang.String r4 = com.igexin.push.core.e.J     // Catch: java.lang.Exception -> L37
            byte[] r2 = com.igexin.b.a.a.a.a(r2, r4)     // Catch: java.lang.Exception -> L37
            r3.<init>(r2)     // Catch: java.lang.Exception -> L37
            java.lang.String r2 = "\\|"
            java.lang.String[] r2 = r3.split(r2)     // Catch: java.lang.Exception -> L37
            int r3 = r2.length     // Catch: java.lang.Exception -> L37
            r4 = 2
            if (r3 <= r4) goto L37
            r2 = r2[r4]     // Catch: java.lang.Exception -> L37
            if (r2 == 0) goto L36
            java.lang.String r3 = "null"
            boolean r3 = r2.equals(r3)     // Catch: java.lang.Exception -> L36
            if (r3 == 0) goto L36
            goto L37
        L36:
            r1 = r2
        L37:
            java.lang.String r2 = "FileUtils|get cid from file cid = "
            java.lang.String r3 = java.lang.String.valueOf(r1)
            java.lang.String r2 = r2.concat(r3)
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.igexin.b.a.c.a.a(r2, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.f.k.b():java.lang.String");
    }

    public static List<JSONObject> b(String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        FileInputStream fileInputStream;
        byte[] byteArray;
        ArrayList arrayList = new ArrayList();
        try {
            File file = new File(str);
            if (!file.exists()) {
                return null;
            }
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    if (file2 != null && file2.isFile() && file2.getName().indexOf(".db") > 0 && !file2.getName().equals("com.gt.sdk.deviceId.db") && !file2.getName().equals("com.getui.sdk.deviceId.db") && !file2.getName().equals("app.db") && !file2.getName().equals("imsi.db")) {
                        file2.getName();
                        String strSubstring = file2.getName().substring(0, file2.getName().length() - 3);
                        if (e(strSubstring) && !com.igexin.push.core.e.i.getPackageName().equals(strSubstring)) {
                            byte[] bArr = new byte[1024];
                            try {
                                fileInputStream = new FileInputStream(file2);
                                try {
                                    byteArrayOutputStream = new ByteArrayOutputStream();
                                    while (true) {
                                        try {
                                            try {
                                                int i = fileInputStream.read(bArr);
                                                if (i == -1) {
                                                    break;
                                                }
                                                byteArrayOutputStream.write(bArr, 0, i);
                                            } catch (Throwable th) {
                                                th = th;
                                                if (fileInputStream != null) {
                                                    fileInputStream.close();
                                                }
                                                if (byteArrayOutputStream != null) {
                                                    byteArrayOutputStream.close();
                                                }
                                                throw th;
                                            }
                                        } catch (Exception e) {
                                            e = e;
                                            com.igexin.b.a.c.a.a("FileUtils| read " + strSubstring + "excetpion:" + e.toString(), new Object[0]);
                                            if (fileInputStream != null) {
                                                fileInputStream.close();
                                            }
                                            if (byteArrayOutputStream != null) {
                                                byteArrayOutputStream.close();
                                            }
                                            byteArray = null;
                                        }
                                    }
                                    byteArray = byteArrayOutputStream.toByteArray();
                                    fileInputStream.close();
                                    byteArrayOutputStream.close();
                                } catch (Exception e2) {
                                    e = e2;
                                    byteArrayOutputStream = null;
                                } catch (Throwable th2) {
                                    th = th2;
                                    byteArrayOutputStream = null;
                                }
                            } catch (Exception e3) {
                                e = e3;
                                byteArrayOutputStream = null;
                                fileInputStream = null;
                            } catch (Throwable th3) {
                                th = th3;
                                byteArrayOutputStream = null;
                                fileInputStream = null;
                            }
                            if (byteArray == null) {
                                com.igexin.b.a.c.a.a("FileUtils|read " + strSubstring + "bytes == null", new Object[0]);
                            } else {
                                String[] strArrSplit = new String(com.igexin.b.a.a.a.a(byteArray, com.igexin.push.core.e.J)).split("\\|");
                                if (strArrSplit.length > 2) {
                                    try {
                                        JSONObject jSONObject = new JSONObject();
                                        jSONObject.put("cid", strArrSplit[2]);
                                        jSONObject.put("appid", strArrSplit[1]);
                                        arrayList.add(jSONObject);
                                    } catch (Exception unused) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return arrayList;
        } catch (Throwable unused2) {
            return null;
        }
    }

    public static void b(final Context context) {
        if (!com.igexin.push.config.d.u) {
            com.igexin.b.a.c.a.a("FileUtils|isReportInitialize = false", new Object[0]);
            return;
        }
        final long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - com.igexin.push.core.e.av < 1000) {
            com.igexin.b.a.c.a.a("FileUtils|not allowed to save initialization twice within 1s", new Object[0]);
            return;
        }
        com.igexin.push.core.e.av = jCurrentTimeMillis;
        if (com.igexin.push.core.e.j.get()) {
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) new com.igexin.push.e.d() { // from class: com.igexin.push.f.k.1
                @Override // com.igexin.push.e.d
                public final void b() {
                    k.b(context, String.valueOf(jCurrentTimeMillis));
                }
            }, false, true);
        } else {
            new Thread(new Runnable() { // from class: com.igexin.push.f.k.2
                @Override // java.lang.Runnable
                public final void run() {
                    k.b(context, String.valueOf(jCurrentTimeMillis));
                }
            }).start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        String str2 = context.getFilesDir().getPath() + "/init_c1.pid";
        synchronized (b) {
            if (str.length() == 0) {
                a(str.getBytes(), str2, false);
            } else {
                a((str + "|").getBytes(), str2, true);
            }
        }
    }

    public static String c() {
        String str;
        byte[] bArrA;
        try {
            String str2 = com.igexin.push.core.e.ac;
            com.igexin.b.a.c.a.a("FileUtils|get device id from file : " + com.igexin.push.core.e.ac, new Object[0]);
            bArrA = a(com.igexin.push.core.e.ac);
        } catch (Exception e) {
            e = e;
            str = null;
        }
        if (bArrA == null) {
            com.igexin.b.a.c.a.a("FileUtils|read file device id = null", new Object[0]);
            return null;
        }
        str = new String(bArrA, "utf-8");
        try {
            com.igexin.b.a.c.a.a("FileUtils|read file device id = ".concat(String.valueOf(str)), new Object[0]);
        } catch (Exception e2) {
            e = e2;
            com.igexin.b.a.c.a.a("FileUtils|get device id from file : " + e.toString(), new Object[0]);
        }
        return str;
    }

    public static String c(Context context) {
        return context.getExternalFilesDir("gtpush") + "/log/";
    }

    public static String c(String str) throws Throwable {
        String[] strArrSplit;
        try {
            byte[] bArrA = a("/sdcard/libs/" + str + ".db");
            if (bArrA == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            String strA = com.igexin.b.b.a.a(com.igexin.push.core.b.ah);
            String strA2 = com.igexin.b.b.a.a("");
            if (strA != null && ((strA.equals(com.igexin.push.core.e.J) || com.igexin.push.core.e.J.equals(strA2)) && !TextUtils.isEmpty(com.igexin.push.core.e.A))) {
                arrayList.add(com.igexin.b.b.a.a(com.igexin.push.core.e.A));
            }
            arrayList.add(com.igexin.push.core.e.J);
            arrayList.add(strA2);
            arrayList.add(com.igexin.b.b.a.a("000000000000000"));
            arrayList.add(strA);
            String strF = n.f();
            if (!TextUtils.isEmpty(strF)) {
                arrayList.add(com.igexin.b.b.a.a(strF));
            }
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    strArrSplit = null;
                    break;
                }
                String str2 = new String(com.igexin.b.a.a.a.a(bArrA, (String) it.next()));
                if (Pattern.matches("[\\.:0-9a-zA-Z\\|]+", str2)) {
                    strArrSplit = str2.split("\\|");
                    break;
                }
            }
            if (strArrSplit == null || strArrSplit.length <= 3) {
                return null;
            }
            String str3 = strArrSplit[3];
            if (str3 != null) {
                try {
                    if (str3.equals("null")) {
                        return null;
                    }
                } catch (Exception unused) {
                }
            }
            return str3;
        } catch (Exception unused2) {
            return null;
        }
    }

    public static long d() {
        byte[] bArrA;
        String str = com.igexin.push.core.e.ab;
        long j = 0;
        try {
            bArrA = a(com.igexin.push.core.e.ab);
        } catch (Exception e) {
            com.igexin.b.a.c.a.a("FileUtils|" + e.toString(), new Object[0]);
        }
        if (bArrA == null) {
            com.igexin.b.a.c.a.a("FileUtils|read session from file, not exist", new Object[0]);
            return 0L;
        }
        String str2 = new String(com.igexin.b.a.a.a.a(bArrA, com.igexin.push.core.e.J));
        String strSubstring = str2.contains("null") ? str2.substring(7) : str2.substring(20);
        int iIndexOf = strSubstring.indexOf("|");
        if (iIndexOf >= 0) {
            strSubstring = strSubstring.substring(0, iIndexOf);
        }
        long j2 = Long.parseLong(strSubstring);
        if (j2 != 0) {
            j = j2;
        }
        com.igexin.b.a.c.a.a("FileUtils|session : ".concat(String.valueOf(j)), new Object[0]);
        return j;
    }

    private static String d(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()) + "|" + com.igexin.push.core.e.x + "|" + com.igexin.push.core.e.a + "|1|" + str;
    }

    public static void e() {
        if (com.igexin.push.core.e.F == null) {
            return;
        }
        String str = com.igexin.push.core.e.ac;
        com.igexin.b.a.c.a.a("FileUtils|save device id to file : " + com.igexin.push.core.e.ac, new Object[0]);
        FileOutputStream fileOutputStream = null;
        ReentrantReadWriteLock.WriteLock writeLock = new ReentrantReadWriteLock().writeLock();
        try {
            try {
                if (writeLock.tryLock()) {
                    File file = new File(com.igexin.push.core.e.ac);
                    if (!file.exists() && !file.createNewFile()) {
                        com.igexin.b.a.c.a.a("FileUtils|create file : " + file.toString() + " failed !!!", new Object[0]);
                        writeLock.unlock();
                        return;
                    }
                    FileOutputStream fileOutputStream2 = new FileOutputStream(com.igexin.push.core.e.ac);
                    try {
                        byte[] bytes = com.igexin.push.core.e.F.getBytes("utf-8");
                        new String(bytes, "utf-8");
                        fileOutputStream2.write(bytes);
                        fileOutputStream = fileOutputStream2;
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        com.igexin.b.a.c.a.a("FileUtils|" + e.toString(), new Object[0]);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        writeLock.unlock();
                        return;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                        writeLock.unlock();
                        throw th;
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                writeLock.unlock();
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean e(String str) {
        try {
            com.igexin.push.core.e.i.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static String f(String str) {
        return g(str);
    }

    public static void f() {
        byte[] bytes = com.igexin.push.core.e.x.getBytes();
        byte[] bArr = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            bArr[i] = (byte) (bytes[i] ^ com.igexin.push.core.e.af[i]);
        }
        com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.a, Base64.encodeToString(bArr, 0));
    }

    private static String g(String str) throws Throwable {
        byte[] bArrA;
        String str2 = "";
        try {
            bArrA = a(str);
        } catch (Exception unused) {
        }
        if (bArrA == null) {
            com.igexin.b.a.c.a.a("FileUtils | read file info id = null", new Object[0]);
            return null;
        }
        String[] strArrSplit = new String(com.igexin.b.a.a.a.a(bArrA, com.igexin.push.core.e.J)).split("\\|");
        if (strArrSplit.length > 2 && (str2 = strArrSplit[1]) != null && str2.equals("null")) {
            str2 = null;
        }
        com.igexin.b.a.c.a.a("FileUtils|get info from file info = ".concat(String.valueOf(str2)), new Object[0]);
        return str2;
    }

    public static void g() {
        b(com.igexin.push.core.e.i, "");
    }

    public static String h() {
        byte[] bArrA;
        String str = com.igexin.push.core.e.i.getFilesDir().getPath() + "/init_c1.pid";
        try {
            synchronized (b) {
                bArrA = a(str);
            }
            if (bArrA == null) {
                return null;
            }
            String str2 = new String(bArrA);
            if (TextUtils.isEmpty(str2)) {
                return null;
            }
            if (str2.endsWith("|")) {
                str2 = str2.substring(0, str2.length() - 1);
            }
            String[] strArrSplit = str2.split("\\|");
            if (strArrSplit.length > 300 && System.currentTimeMillis() - Long.parseLong(strArrSplit[0]) > com.igexin.push.e.b.d.b) {
                g();
                return null;
            }
            StringBuilder sb = new StringBuilder();
            for (String str3 : strArrSplit) {
                sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()) + "|" + com.igexin.push.core.e.x + "|" + com.igexin.push.core.e.a + "|1|" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(Long.parseLong(str3))));
                sb.append("\n");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            com.igexin.b.a.c.a.a("FileUtils|init type10 data = " + sb.toString(), new Object[0]);
            return sb.toString();
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("FileUtils|upload init data error = " + th.toString(), new Object[0]);
            g();
            return null;
        }
    }

    public static boolean i() {
        try {
            File file = new File("/sdcard/libs/test.log");
            if (!file.exists()) {
                file.createNewFile();
            }
            file.delete();
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
