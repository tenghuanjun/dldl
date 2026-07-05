package com.igexin.push.core;

import android.content.Context;
import com.jiguang.h5.PermissionUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class h {
    static final String a = b.d + h.class.getName();
    private static volatile h d;
    private final Context b = p.b;
    private final String c = "/sdcard/libs/com.getui.sdk.deviceId.db";

    private h() {
    }

    public static h a() {
        if (d == null) {
            synchronized (h.class) {
                if (d == null) {
                    d = new h();
                }
            }
        }
        return d;
    }

    private void a(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                while (file2.exists()) {
                    if (file2.isFile()) {
                        file2.delete();
                    } else if (!file2.delete()) {
                        a(file2);
                    }
                }
            }
        }
        file.delete();
    }

    static void c(String str) {
        try {
            com.igexin.push.core.e.e.a().a(str);
        } catch (Throwable unused) {
        }
    }

    private void e() throws Throwable {
        String strD = d();
        String str = e.F;
        com.igexin.b.a.c.a.a(a + "|read deviceId.db = " + strD + "; CoreRuntimeInfo.deviceId = " + e.F, new Object[0]);
        if (strD == null) {
            if (e.F != null) {
                b(e.F);
            }
        } else {
            if (strD.equals(e.F)) {
                return;
            }
            e.F = strD;
            c(strD);
        }
    }

    public final String a(String str) {
        String str2 = "";
        try {
            str2 = e.aJ + com.igexin.assist.util.a.a(str) + ".bin";
            File file = new File(str2);
            if (!file.exists() || !file.canRead()) {
                str2 = "";
                if (this.b.getPackageManager().checkPermission(PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE, this.b.getPackageName()) != 0) {
                    e.aJ = this.b.getCacheDir() + "/ImgCache/";
                }
            }
        } catch (Exception unused) {
        }
        return str2;
    }

    public final void b() {
        File[] fileArrListFiles;
        File file = new File(e.aJ);
        if (file.exists() && (fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.igexin.push.core.h.1
            final long a = System.currentTimeMillis();
            final long b = com.igexin.push.e.b.d.b;

            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                return this.a - file2.lastModified() >= com.igexin.push.e.b.d.b;
            }
        })) != null) {
            for (File file2 : fileArrListFiles) {
                file2.delete();
            }
        }
    }

    final void b(String str) throws Throwable {
        com.igexin.b.a.c.a.a(a + "|save deviceId = " + str + " to " + this.c, new Object[0]);
        ReentrantReadWriteLock.WriteLock writeLock = new ReentrantReadWriteLock().writeLock();
        FileOutputStream fileOutputStream = null;
        try {
            try {
                if (writeLock.tryLock()) {
                    File file = new File(this.c);
                    if (file.exists() || file.createNewFile()) {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(this.c);
                        try {
                            fileOutputStream2.write(com.igexin.b.b.a.b("V1|".concat(String.valueOf(str)).getBytes("utf-8")));
                            fileOutputStream = fileOutputStream2;
                        } catch (Exception e) {
                            e = e;
                            fileOutputStream = fileOutputStream2;
                            com.igexin.b.a.c.a.a(a + "|" + e.toString(), new Object[0]);
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            com.igexin.b.a.b.g.a(fileOutputStream);
                            writeLock.unlock();
                            throw th;
                        }
                    } else {
                        com.igexin.b.a.c.a.a(a + "|create file " + file.toString() + " failed", new Object[0]);
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
            com.igexin.b.a.b.g.a(fileOutputStream);
            writeLock.unlock();
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final void c() {
        File[] fileArrListFiles;
        File file = new File(b.N);
        if (file.exists() && (fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.igexin.push.core.h.2
            final long a = System.currentTimeMillis();
            final long b = com.igexin.push.e.b.d.b;

            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                return this.a - file2.lastModified() >= com.igexin.push.e.b.d.b;
            }
        })) != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.exists()) {
                    a(file2);
                }
            }
        }
    }

    final String d() throws Throwable {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        String str = null;
        if (new File(this.c).exists()) {
            byte[] bArr = new byte[1024];
            try {
                fileInputStream = new FileInputStream(this.c);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (Exception unused) {
                    byteArrayOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    byteArrayOutputStream = null;
                }
            } catch (Exception unused2) {
                fileInputStream = null;
                byteArrayOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                byteArrayOutputStream = null;
            }
            while (true) {
                try {
                    int i = fileInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                } catch (Exception unused3) {
                } catch (Throwable th3) {
                    th = th3;
                    com.igexin.b.a.b.g.a(fileInputStream);
                    com.igexin.b.a.b.g.a(byteArrayOutputStream);
                    throw th;
                }
                com.igexin.b.a.b.g.a(fileInputStream);
                com.igexin.b.a.b.g.a(byteArrayOutputStream);
            }
            String[] strArrSplit = new String(com.igexin.b.b.a.c(byteArrayOutputStream.toByteArray()), "utf-8").split("\\|");
            if (strArrSplit.length > 1 && g.e.equals(strArrSplit[0])) {
                str = strArrSplit[1];
            }
            com.igexin.b.a.b.g.a(fileInputStream);
            com.igexin.b.a.b.g.a(byteArrayOutputStream);
        }
        return str;
    }
}
