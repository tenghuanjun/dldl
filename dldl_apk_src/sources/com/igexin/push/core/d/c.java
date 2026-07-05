package com.igexin.push.core.d;

import com.igexin.base.util.IOUtils;
import com.igexin.push.core.p;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c {
    public static final String a = "c";
    public static final String b = "i";
    public static final String c = "p";
    public static final String d = "s";
    public static final String e = "ng";
    private static final String f = "RpConfig";
    private static final c g = new c();
    private long i;
    private final Map<String, String> j = new HashMap();
    private final String h = p.b.getFilesDir().getAbsolutePath() + "/grp.prop";

    private c() {
        try {
            File file = new File(this.h);
            if (file.exists()) {
                return;
            }
            file.createNewFile();
        } catch (IOException unused) {
        }
    }

    private int a(String str, int... iArr) {
        try {
            return Integer.parseInt(a(str));
        } catch (NumberFormatException unused) {
            if (iArr == null || iArr.length != 1) {
                return -1;
            }
            return iArr[0];
        }
    }

    public static c a() {
        return g;
    }

    private void a(com.igexin.push.core.g.a<RandomAccessFile> aVar) throws Throwable {
        RandomAccessFile randomAccessFile;
        FileLock fileLockLock = null;
        try {
            randomAccessFile = new RandomAccessFile(new File(this.h), "rw");
            try {
                try {
                    fileLockLock = randomAccessFile.getChannel().lock();
                    if (fileLockLock.isValid()) {
                        aVar.a(randomAccessFile);
                    }
                    if (fileLockLock != null && fileLockLock.isValid()) {
                        try {
                            fileLockLock.release();
                        } catch (IOException unused) {
                        }
                    }
                    IOUtils.close(randomAccessFile);
                } catch (Exception e2) {
                    e = e2;
                    com.igexin.b.a.c.a.a("RpConfig| getProcessLock err：" + e.toString(), new Object[0]);
                    if (fileLockLock != null && fileLockLock.isValid()) {
                        try {
                            fileLockLock.release();
                        } catch (IOException unused2) {
                        }
                    }
                    IOUtils.close(randomAccessFile);
                }
            } catch (Throwable th) {
                th = th;
                if (fileLockLock != null && fileLockLock.isValid()) {
                    try {
                        fileLockLock.release();
                    } catch (IOException unused3) {
                    }
                }
                IOUtils.close(randomAccessFile);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            randomAccessFile = null;
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
            if (fileLockLock != null) {
                fileLockLock.release();
            }
            IOUtils.close(randomAccessFile);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(RandomAccessFile randomAccessFile) {
        int i;
        try {
            this.j.clear();
            while (true) {
                String line = randomAccessFile.readLine();
                if (line == null) {
                    return true;
                }
                int iIndexOf = line.indexOf("=");
                if (iIndexOf >= 0 && (i = iIndexOf + 1) != line.length()) {
                    this.j.put(line.substring(0, iIndexOf), line.substring(i));
                }
            }
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        long jLastModified = new File(this.h).lastModified();
        boolean z = this.i != jLastModified;
        this.i = jLastModified;
        return z;
    }

    public final String a(String str) throws Throwable {
        if (b()) {
            a(new com.igexin.push.core.g.a<RandomAccessFile>() { // from class: com.igexin.push.core.d.c.3
                /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
                private void a2(RandomAccessFile randomAccessFile) {
                    c.this.a(randomAccessFile);
                }

                @Override // com.igexin.push.core.g.a
                public final /* bridge */ /* synthetic */ void a(RandomAccessFile randomAccessFile) {
                    c.this.a(randomAccessFile);
                }
            });
        }
        return this.j.get(str);
    }

    public final void a(final String str, final Object obj) {
        a(new com.igexin.push.core.g.a<RandomAccessFile>() { // from class: com.igexin.push.core.d.c.2
            /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method */
            private void a2(RandomAccessFile randomAccessFile) {
                if (c.this.b()) {
                    c.this.a(randomAccessFile);
                }
                c.this.j.put(str, String.valueOf(obj));
                try {
                    randomAccessFile.setLength(0L);
                    for (Map.Entry entry : c.this.j.entrySet()) {
                        randomAccessFile.writeBytes(((String) entry.getKey()) + "=" + ((String) entry.getValue()));
                        randomAccessFile.writeBytes("\n");
                    }
                } catch (IOException unused) {
                }
            }

            @Override // com.igexin.push.core.g.a
            public final /* synthetic */ void a(RandomAccessFile randomAccessFile) {
                RandomAccessFile randomAccessFile2 = randomAccessFile;
                if (c.this.b()) {
                    c.this.a(randomAccessFile2);
                }
                c.this.j.put(str, String.valueOf(obj));
                try {
                    randomAccessFile2.setLength(0L);
                    for (Map.Entry entry : c.this.j.entrySet()) {
                        randomAccessFile2.writeBytes(((String) entry.getKey()) + "=" + ((String) entry.getValue()));
                        randomAccessFile2.writeBytes("\n");
                    }
                } catch (IOException unused) {
                }
            }
        }.a(new com.igexin.push.core.g.a<RandomAccessFile>() { // from class: com.igexin.push.core.d.c.1
            private void a() {
                c.this.b();
            }

            @Override // com.igexin.push.core.g.a
            public final /* bridge */ /* synthetic */ void a(RandomAccessFile randomAccessFile) {
                c.this.b();
            }
        }));
    }

    public final boolean a(String str, boolean... zArr) throws Throwable {
        String strA = a(str);
        return (strA == null && zArr.length == 1) ? zArr[0] : Boolean.parseBoolean(strA);
    }

    public final long b(String str) {
        try {
            return Long.parseLong(a(str));
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }
}
