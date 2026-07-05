package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class a implements NativeExceptionHandler {
    private final Context a;
    private final com.tencent.bugly.crashreport.crash.b b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final com.tencent.bugly.crashreport.common.strategy.a d;

    public a(Context context, com.tencent.bugly.crashreport.common.info.a aVar, com.tencent.bugly.crashreport.crash.b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, byte[] bArr, Map<String, String> map, boolean z, boolean z2) {
        int i;
        String str12;
        int iIndexOf;
        boolean zK = c.a().k();
        if (zK) {
            x.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.b = 1;
        crashDetailBean.e = this.c.h();
        crashDetailBean.f = this.c.j;
        crashDetailBean.g = this.c.w();
        crashDetailBean.m = this.c.g();
        crashDetailBean.n = str3;
        crashDetailBean.o = zK ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        crashDetailBean.p = str4;
        crashDetailBean.q = str5 != null ? str5 : "";
        crashDetailBean.r = j;
        crashDetailBean.u = z.b(crashDetailBean.q.getBytes());
        crashDetailBean.A = str;
        crashDetailBean.B = str2;
        crashDetailBean.I = this.c.y();
        crashDetailBean.h = this.c.v();
        crashDetailBean.i = this.c.J();
        crashDetailBean.v = str8;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        String dumpFilePath = nativeCrashHandler != null ? nativeCrashHandler.getDumpFilePath() : null;
        String strA = b.a(dumpFilePath, str8);
        if (!z.a(strA)) {
            crashDetailBean.U = strA;
        }
        crashDetailBean.V = b.b(dumpFilePath);
        crashDetailBean.w = b.a(str9, c.e, null, false);
        crashDetailBean.x = b.a(str10, c.e, null, true);
        crashDetailBean.J = str7;
        crashDetailBean.K = str6;
        crashDetailBean.L = str11;
        crashDetailBean.F = this.c.p();
        crashDetailBean.G = this.c.o();
        crashDetailBean.H = this.c.q();
        if (z) {
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.k();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.i();
            crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.m();
            if (crashDetailBean.w == null) {
                crashDetailBean.w = z.a(this.a, c.e, (String) null);
            }
            crashDetailBean.y = y.a();
            crashDetailBean.M = this.c.a;
            crashDetailBean.N = this.c.a();
            crashDetailBean.P = this.c.H();
            crashDetailBean.Q = this.c.I();
            crashDetailBean.R = this.c.B();
            crashDetailBean.S = this.c.G();
            crashDetailBean.z = z.a(c.f, false);
            int iIndexOf2 = crashDetailBean.q.indexOf("java:\n");
            if (iIndexOf2 > 0 && (i = iIndexOf2 + 6) < crashDetailBean.q.length()) {
                String strSubstring = crashDetailBean.q.substring(i, crashDetailBean.q.length() - 1);
                if (strSubstring.length() > 0 && crashDetailBean.z.containsKey(crashDetailBean.B) && (iIndexOf = (str12 = crashDetailBean.z.get(crashDetailBean.B)).indexOf(strSubstring)) > 0) {
                    String strSubstring2 = str12.substring(iIndexOf);
                    crashDetailBean.z.put(crashDetailBean.B, strSubstring2);
                    crashDetailBean.q = crashDetailBean.q.substring(0, i);
                    crashDetailBean.q += strSubstring2;
                }
            }
            if (str == null) {
                crashDetailBean.A = this.c.d;
            }
            this.b.c(crashDetailBean);
        } else {
            crashDetailBean.C = -1L;
            crashDetailBean.D = -1L;
            crashDetailBean.E = -1L;
            if (crashDetailBean.w == null) {
                crashDetailBean.w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            }
            crashDetailBean.M = -1L;
            crashDetailBean.P = -1;
            crashDetailBean.Q = -1;
            crashDetailBean.R = map;
            crashDetailBean.S = this.c.G();
            crashDetailBean.z = null;
            if (str == null) {
                crashDetailBean.A = "unknown(record)";
            }
            if (bArr != null) {
                crashDetailBean.y = bArr;
            }
        }
        return crashDetailBean;
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        x.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        String str8;
        String str9;
        String str10;
        boolean z;
        boolean z2;
        x.a("Native Crash Happen v2", new Object[0]);
        try {
            String strA = b.a(str3);
            if (i3 > 0) {
                str9 = str + "(" + str5 + ")";
                str8 = "UNKNOWN";
                str10 = "KERNEL";
            } else {
                String strA2 = i4 > 0 ? AppInfo.a(i4) : "UNKNOWN";
                str8 = strA2.equals(String.valueOf(i4)) ? strA2 : strA2 + "(" + i4 + ")";
                str9 = str;
                str10 = str5;
            }
            HashMap map = new HashMap();
            if (strArr != null) {
                for (int i7 = 0; i7 < strArr.length; i7++) {
                    String str11 = strArr[i7];
                    if (str11 != null) {
                        x.a("Extra message[%d]: %s", Integer.valueOf(i7), str11);
                        String[] strArrSplit = str11.split(SimpleComparison.EQUAL_TO_OPERATION);
                        if (strArrSplit.length == 2) {
                            map.put(strArrSplit[0], strArrSplit[1]);
                        } else {
                            x.d("bad extraMsg %s", str11);
                        }
                    }
                }
            } else {
                x.c("not found extraMsg", new Object[0]);
            }
            String str12 = (String) map.get("HasPendingException");
            if (str12 == null || !str12.equals("true")) {
                z = false;
            } else {
                x.a("Native crash happened with a Java pending exception.", new Object[0]);
                z = true;
            }
            String str13 = (String) map.get("ExceptionProcessName");
            if (str13 == null || str13.length() == 0) {
                str13 = this.c.d;
            } else {
                x.c("Name of crash process: %s", str13);
            }
            String str14 = str13;
            String str15 = (String) map.get("ExceptionThreadName");
            if (str15 == null || str15.length() == 0) {
                Thread threadCurrentThread = Thread.currentThread();
                str15 = threadCurrentThread.getName() + "(" + threadCurrentThread.getId() + ")";
            } else {
                x.c("Name of crash thread: %s", str15);
                Iterator<Thread> it = Thread.getAllStackTraces().keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z2 = false;
                        break;
                    }
                    Thread next = it.next();
                    if (next.getName().equals(str15)) {
                        str15 = str15 + "(" + next.getId() + ")";
                        z2 = true;
                        break;
                    }
                }
                if (!z2) {
                    str15 = str15 + "(" + i2 + ")";
                }
            }
            String str16 = str15;
            long j3 = (j * 1000) + (j2 / 1000);
            String str17 = (String) map.get("SysLogPath");
            String str18 = (String) map.get("JniLogPath");
            if (!this.d.b()) {
                x.d("no remote but still store!", new Object[0]);
            }
            if (!this.d.c().g && this.d.b()) {
                x.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                com.tencent.bugly.crashreport.crash.b.a("NATIVE_CRASH", z.a(), str14, str16, str9 + ShellAdbUtils.COMMAND_LINE_END + str2 + ShellAdbUtils.COMMAND_LINE_END + strA, null);
                z.b(str4);
                return;
            }
            String str19 = str9;
            try {
                CrashDetailBean crashDetailBeanPackageCrashDatas = packageCrashDatas(str14, str16, j3, str9, str2, strA, str10, str8, str4, str17, str18, str7, null, null, true, z);
                if (crashDetailBeanPackageCrashDatas == null) {
                    x.e("pkg crash datas fail!", new Object[0]);
                    return;
                }
                com.tencent.bugly.crashreport.crash.b.a("NATIVE_CRASH", z.a(), str14, str16, str19 + ShellAdbUtils.COMMAND_LINE_END + str2 + ShellAdbUtils.COMMAND_LINE_END + strA, crashDetailBeanPackageCrashDatas);
                try {
                    boolean z3 = !this.b.a(crashDetailBeanPackageCrashDatas, i3);
                    NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
                    b.a(true, nativeCrashHandler != null ? nativeCrashHandler.getDumpFilePath() : null);
                    if (z3) {
                        this.b.a(crashDetailBeanPackageCrashDatas, 3000L, true);
                    }
                    this.b.b(crashDetailBeanPackageCrashDatas);
                    return;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
        if (x.a(th)) {
            return;
        }
        th.printStackTrace();
    }
}
