package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class y {
    public static boolean a = true;
    private static SimpleDateFormat b = null;
    private static int c = 5120;
    private static StringBuilder d;
    private static StringBuilder e;
    private static boolean f;
    private static a g;
    private static String h;
    private static String i;
    private static Context j;
    private static String k;
    private static boolean l;
    private static boolean m;
    private static int n;
    private static final Object o = new Object();

    static /* synthetic */ boolean b(boolean z) {
        f = false;
        return false;
    }

    static {
        try {
            b = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable unused) {
        }
    }

    private static boolean b(String str, String str2, String str3) {
        try {
            com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
            if (aVarB == null || aVarB.D == null) {
                return false;
            }
            return aVarB.D.appendLogToNative(str, str2, str3);
        } catch (Throwable th) {
            if (x.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    private static String f() {
        try {
            com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
            if (aVarB == null || aVarB.D == null) {
                return null;
            }
            return aVarB.D.getLogFromNative();
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static synchronized void a(Context context) {
        if (l || context == null || !a) {
            return;
        }
        try {
            e = new StringBuilder(0);
            d = new StringBuilder(0);
            j = context;
            com.tencent.bugly.crashreport.common.info.a aVarA = com.tencent.bugly.crashreport.common.info.a.a(context);
            h = aVarA.d;
            aVarA.getClass();
            i = "";
            k = j.getFilesDir().getPath() + "/buglylog_" + h + "_" + i + ".txt";
            n = Process.myPid();
        } catch (Throwable unused) {
        }
        l = true;
    }

    public static void a(int i2) {
        synchronized (o) {
            c = i2;
            if (i2 < 0) {
                c = 0;
            } else if (i2 > 10240) {
                c = 10240;
            }
        }
    }

    public static void a(boolean z) {
        x.a("[LogUtil] Whether can record user log into native: " + z, new Object[0]);
        m = z;
    }

    public static void a(String str, String str2, Throwable th) {
        if (th == null) {
            return;
        }
        String message = th.getMessage();
        if (message == null) {
            message = "";
        }
        a(str, str2, message + '\n' + z.b(th));
    }

    public static synchronized void a(String str, String str2, String str3) {
        if (l && a) {
            if (m && b(str, str2, str3)) {
                return;
            }
            long jMyTid = Process.myTid();
            d.setLength(0);
            if (str3.length() > 30720) {
                str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
            }
            Date date = new Date();
            String string = b != null ? b.format(date) : date.toString();
            StringBuilder sb = d;
            sb.append(string);
            sb.append(" ");
            sb.append(n);
            sb.append(" ");
            sb.append(jMyTid);
            sb.append(" ");
            sb.append(str);
            sb.append(" ");
            sb.append(str2);
            sb.append(": ");
            sb.append(str3);
            sb.append("\u0001\r\n");
            String string2 = d.toString();
            synchronized (o) {
                e.append(string2);
                if (e.length() <= c) {
                    return;
                }
                if (f) {
                    return;
                }
                f = true;
                w.a().a(new Runnable() { // from class: com.tencent.bugly.proguard.y.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        synchronized (y.o) {
                            try {
                                if (y.g == null) {
                                    a unused = y.g = new a(y.k);
                                } else if (y.g.b == null || y.g.b.length() + ((long) y.e.length()) > y.g.e) {
                                    y.g.a();
                                }
                                if (y.g.a(y.e.toString())) {
                                    y.e.setLength(0);
                                    y.b(false);
                                }
                            } catch (Throwable unused2) {
                            }
                        }
                    }
                });
            }
        }
    }

    public static byte[] a() {
        if (!a) {
            return null;
        }
        if (m) {
            x.a("[LogUtil] Get user log from native.", new Object[0]);
            String strF = f();
            if (strF != null) {
                x.a("[LogUtil] Got user log from native: %d bytes", Integer.valueOf(strF.length()));
                return z.a((File) null, strF, "BuglyNativeLog.txt");
            }
        }
        StringBuilder sb = new StringBuilder();
        synchronized (o) {
            if (g != null && g.a && g.b != null && g.b.length() > 0) {
                sb.append(z.a(g.b, 30720, true));
            }
            if (e != null && e.length() > 0) {
                sb.append(e.toString());
            }
        }
        return z.a((File) null, sb.toString(), "BuglyLog.txt");
    }

    /* JADX INFO: compiled from: BUGLY */
    public static class a {
        private boolean a;
        private File b;
        private String c;
        private long d;
        private long e = 30720;

        public a(String str) {
            if (str == null || str.equals("")) {
                return;
            }
            this.c = str;
            this.a = a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a() {
            try {
                File file = new File(this.c);
                this.b = file;
                if (file.exists() && !this.b.delete()) {
                    this.a = false;
                    return false;
                }
                if (this.b.createNewFile()) {
                    return true;
                }
                this.a = false;
                return false;
            } catch (Throwable th) {
                x.a(th);
                this.a = false;
                return false;
            }
        }

        public final boolean a(String str) {
            if (!this.a) {
                return false;
            }
            FileOutputStream fileOutputStream = null;
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(this.b, true);
                try {
                    byte[] bytes = str.getBytes("UTF-8");
                    fileOutputStream2.write(bytes);
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                    this.d += (long) bytes.length;
                    this.a = true;
                    try {
                        fileOutputStream2.close();
                    } catch (IOException unused) {
                    }
                    return true;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    try {
                        x.a(th);
                        this.a = false;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                        return false;
                    } catch (Throwable th2) {
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused3) {
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }
}
