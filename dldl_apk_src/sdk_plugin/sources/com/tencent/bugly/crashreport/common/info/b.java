package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.taptap.sdk.kit.internal.p000const.TrackAction;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class b {
    private static final String[] a = {"/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb"};
    private static final String[] b = {"com.ami.duosupdater.ui", "com.ami.launchmetro", "com.ami.syncduosservices", "com.bluestacks.home", "com.bluestacks.windowsfilemanager", "com.bluestacks.settings", "com.bluestacks.bluestackslocationprovider", "com.bluestacks.appsettings", "com.bluestacks.bstfolder", "com.bluestacks.BstCommandProcessor", "com.bluestacks.s2p", "com.bluestacks.setup", "com.kaopu001.tiantianserver", "com.kpzs.helpercenter", "com.kaopu001.tiantianime", "com.android.development_settings", "com.android.development", "com.android.customlocale2", "com.genymotion.superuser", "com.genymotion.clipboardproxy", "com.uc.xxzs.keyboard", "com.uc.xxzs", "com.blue.huang17.agent", "com.blue.huang17.launcher", "com.blue.huang17.ime", "com.microvirt.guide", "com.microvirt.market", "com.microvirt.memuime", "cn.itools.vm.launcher", "cn.itools.vm.proxy", "cn.itools.vm.softkeyboard", "cn.itools.avdmarket", "com.syd.IME", "com.bignox.app.store.hd", "com.bignox.launcher", "com.bignox.app.phone", "com.bignox.app.noxservice", "com.android.noxpush", "com.haimawan.push", "me.haima.helpcenter", "com.windroy.launcher", "com.windroy.superuser", "com.windroy.launcher", "com.windroy.ime", "com.android.flysilkworm", "com.android.emu.inputservice", "com.tiantian.ime", "com.microvirt.launcher", "me.le8.androidassist", "com.vphone.helper", "com.vphone.launcher", "com.duoyi.giftcenter.giftcenter"};
    private static final String[] c = {"/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/qemud", "/dev/qemu_pipe", "/dev/socket/baseband_genyd", "/dev/socket/genyd"};
    private static String d = null;
    private static String e = null;

    public static String d() {
        return AbstractJsonLexerKt.NULL;
    }

    public static String e() {
        return AbstractJsonLexerKt.NULL;
    }

    public static String f() {
        return AbstractJsonLexerKt.NULL;
    }

    public static String a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (x.a(th)) {
                return TrackAction.FAIL;
            }
            th.printStackTrace();
            return TrackAction.FAIL;
        }
    }

    public static String b() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            if (x.a(th)) {
                return TrackAction.FAIL;
            }
            th.printStackTrace();
            return TrackAction.FAIL;
        }
    }

    public static int c() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            if (x.a(th)) {
                return -1;
            }
            th.printStackTrace();
            return -1;
        }
    }

    public static String a(Context context) {
        String string = TrackAction.FAIL;
        if (context == null) {
            return TrackAction.FAIL;
        }
        try {
            string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            return string == null ? AbstractJsonLexerKt.NULL : string.toLowerCase();
        } catch (Throwable th) {
            if (!x.a(th)) {
                x.a("Failed to get Android ID.", new Object[0]);
            }
            return string;
        }
    }

    public static String b(Context context) {
        String simSerialNumber = TrackAction.FAIL;
        if (context == null) {
            return TrackAction.FAIL;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return TrackAction.FAIL;
            }
            simSerialNumber = telephonyManager.getSimSerialNumber();
            return simSerialNumber == null ? AbstractJsonLexerKt.NULL : simSerialNumber;
        } catch (Throwable unused) {
            x.a("Failed to get SIM serial number.", new Object[0]);
            return simSerialNumber;
        }
    }

    public static String g() {
        try {
            return Build.SERIAL;
        } catch (Throwable unused) {
            x.a("Failed to get hardware serial number.", new Object[0]);
            return TrackAction.FAIL;
        }
    }

    private static boolean t() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Throwable th) {
            if (x.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public static String a(Context context, boolean z) {
        String property = null;
        if (z) {
            try {
                String strA = z.a(context, "ro.product.cpu.abilist");
                if (z.a(strA) || strA.equals(TrackAction.FAIL)) {
                    strA = z.a(context, "ro.product.cpu.abi");
                }
                if (!z.a(strA) && !strA.equals(TrackAction.FAIL)) {
                    x.b(b.class, "ABI list: " + strA, new Object[0]);
                    property = strA.split(",")[0];
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
                return TrackAction.FAIL;
            }
        }
        if (property == null) {
            property = System.getProperty("os.arch");
        }
        return property;
    }

    public static long h() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -1L;
        }
    }

    public static long i() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -1L;
        }
    }

    public static long j() {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
                try {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        long j = Long.parseLong(line.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10;
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                            if (!x.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                        try {
                            fileReader.close();
                        } catch (IOException e3) {
                            if (!x.a(e3)) {
                                e3.printStackTrace();
                            }
                        }
                        return j;
                    }
                    try {
                        bufferedReader.close();
                    } catch (IOException e4) {
                        if (!x.a(e4)) {
                            e4.printStackTrace();
                        }
                    }
                    try {
                        fileReader.close();
                        return -1L;
                    } catch (IOException e5) {
                        if (x.a(e5)) {
                            return -1L;
                        }
                        e5.printStackTrace();
                        return -1L;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        if (!x.a(th)) {
                            th.printStackTrace();
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e6) {
                                if (!x.a(e6)) {
                                    e6.printStackTrace();
                                }
                            }
                        }
                        if (fileReader == null) {
                            return -2L;
                        }
                        try {
                            fileReader.close();
                            return -2L;
                        } catch (IOException e7) {
                            if (x.a(e7)) {
                                return -2L;
                            }
                            e7.printStackTrace();
                            return -2L;
                        }
                    } catch (Throwable th3) {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e8) {
                                if (!x.a(e8)) {
                                    e8.printStackTrace();
                                }
                            }
                        }
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e9) {
                                if (!x.a(e9)) {
                                    e9.printStackTrace();
                                }
                            }
                        }
                        throw th3;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = null;
            }
        } catch (Throwable th5) {
            fileReader = null;
            th = th5;
            bufferedReader = null;
        }
    }

    public static long k() {
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                BufferedReader bufferedReader2 = new BufferedReader(fileReader, 2048);
                try {
                    bufferedReader2.readLine();
                    String line = bufferedReader2.readLine();
                    if (line == null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e2) {
                            if (!x.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                        try {
                            fileReader.close();
                        } catch (IOException e3) {
                            if (!x.a(e3)) {
                                e3.printStackTrace();
                            }
                        }
                        return -1L;
                    }
                    long j = (Long.parseLong(line.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10) + 0;
                    String line2 = bufferedReader2.readLine();
                    if (line2 == null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e4) {
                            if (!x.a(e4)) {
                                e4.printStackTrace();
                            }
                        }
                        try {
                            fileReader.close();
                        } catch (IOException e5) {
                            if (!x.a(e5)) {
                                e5.printStackTrace();
                            }
                        }
                        return -1L;
                    }
                    long j2 = j + (Long.parseLong(line2.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10);
                    String line3 = bufferedReader2.readLine();
                    if (line3 == null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e6) {
                            if (!x.a(e6)) {
                                e6.printStackTrace();
                            }
                        }
                        try {
                            fileReader.close();
                        } catch (IOException e7) {
                            if (!x.a(e7)) {
                                e7.printStackTrace();
                            }
                        }
                        return -1L;
                    }
                    long j3 = j2 + (Long.parseLong(line3.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10);
                    try {
                        bufferedReader2.close();
                    } catch (IOException e8) {
                        if (!x.a(e8)) {
                            e8.printStackTrace();
                        }
                    }
                    try {
                        fileReader.close();
                    } catch (IOException e9) {
                        if (!x.a(e9)) {
                            e9.printStackTrace();
                        }
                    }
                    return j3;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    try {
                        if (!x.a(th)) {
                            th.printStackTrace();
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e10) {
                                if (!x.a(e10)) {
                                    e10.printStackTrace();
                                }
                            }
                        }
                        if (fileReader == null) {
                            return -2L;
                        }
                        try {
                            fileReader.close();
                            return -2L;
                        } catch (IOException e11) {
                            if (x.a(e11)) {
                                return -2L;
                            }
                            e11.printStackTrace();
                            return -2L;
                        }
                    } catch (Throwable th2) {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e12) {
                                if (!x.a(e12)) {
                                    e12.printStackTrace();
                                }
                            }
                        }
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException e13) {
                                if (!x.a(e13)) {
                                    e13.printStackTrace();
                                }
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            fileReader = null;
        }
    }

    public static long l() {
        if (!t()) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (x.a(th)) {
                return -2L;
            }
            th.printStackTrace();
            return -2L;
        }
    }

    public static long m() {
        if (!t()) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (x.a(th)) {
                return -2L;
            }
            th.printStackTrace();
            return -2L;
        }
    }

    public static String n() {
        try {
            return Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return TrackAction.FAIL;
        }
    }

    public static String o() {
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return TrackAction.FAIL;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c(android.content.Context r4) {
        /*
            java.lang.String r0 = "unknown"
            java.lang.String r1 = "connectivity"
            java.lang.Object r1 = r4.getSystemService(r1)     // Catch: java.lang.Exception -> L77
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1     // Catch: java.lang.Exception -> L77
            android.net.NetworkInfo r1 = r1.getActiveNetworkInfo()     // Catch: java.lang.Exception -> L77
            if (r1 != 0) goto L12
            r4 = 0
            return r4
        L12:
            int r2 = r1.getType()     // Catch: java.lang.Exception -> L77
            r3 = 1
            if (r2 != r3) goto L1d
            java.lang.String r0 = "WIFI"
            goto L81
        L1d:
            int r1 = r1.getType()     // Catch: java.lang.Exception -> L77
            if (r1 != 0) goto L81
            java.lang.String r1 = "phone"
            java.lang.Object r4 = r4.getSystemService(r1)     // Catch: java.lang.Exception -> L77
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4     // Catch: java.lang.Exception -> L77
            if (r4 == 0) goto L81
            int r4 = r4.getNetworkType()     // Catch: java.lang.Exception -> L77
            switch(r4) {
                case 1: goto L61;
                case 2: goto L5e;
                case 3: goto L5b;
                case 4: goto L58;
                case 5: goto L55;
                case 6: goto L52;
                case 7: goto L4f;
                case 8: goto L4c;
                case 9: goto L49;
                case 10: goto L46;
                case 11: goto L43;
                case 12: goto L40;
                case 13: goto L3d;
                case 14: goto L3a;
                case 15: goto L37;
                default: goto L34;
            }     // Catch: java.lang.Exception -> L77
        L34:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L77
            goto L64
        L37:
            java.lang.String r0 = "HSPA+"
            goto L81
        L3a:
            java.lang.String r0 = "eHRPD"
            goto L81
        L3d:
            java.lang.String r0 = "LTE"
            goto L81
        L40:
            java.lang.String r0 = "EVDO_B"
            goto L81
        L43:
            java.lang.String r0 = "iDen"
            goto L81
        L46:
            java.lang.String r0 = "HSPA"
            goto L81
        L49:
            java.lang.String r0 = "HSUPA"
            goto L81
        L4c:
            java.lang.String r0 = "HSDPA"
            goto L81
        L4f:
            java.lang.String r0 = "1xRTT"
            goto L81
        L52:
            java.lang.String r0 = "EVDO_A"
            goto L81
        L55:
            java.lang.String r0 = "EVDO_0"
            goto L81
        L58:
            java.lang.String r0 = "CDMA"
            goto L81
        L5b:
            java.lang.String r0 = "UMTS"
            goto L81
        L5e:
            java.lang.String r0 = "EDGE"
            goto L81
        L61:
            java.lang.String r0 = "GPRS"
            goto L81
        L64:
            java.lang.String r2 = "MOBILE("
            r1.<init>(r2)     // Catch: java.lang.Exception -> L77
            r1.append(r4)     // Catch: java.lang.Exception -> L77
            java.lang.String r4 = ")"
            r1.append(r4)     // Catch: java.lang.Exception -> L77
            java.lang.String r4 = r1.toString()     // Catch: java.lang.Exception -> L77
            r0 = r4
            goto L81
        L77:
            r4 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r4)
            if (r1 != 0) goto L81
            r4.printStackTrace()
        L81:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.c(android.content.Context):java.lang.String");
    }

    public static String d(Context context) {
        String strA = z.a(context, "ro.miui.ui.version.name");
        if (!z.a(strA) && !strA.equals(TrackAction.FAIL)) {
            return "XiaoMi/MIUI/" + strA;
        }
        String strA2 = z.a(context, "ro.build.version.emui");
        if (!z.a(strA2) && !strA2.equals(TrackAction.FAIL)) {
            return "HuaWei/EMOTION/" + strA2;
        }
        String strA3 = z.a(context, "ro.lenovo.series");
        if (!z.a(strA3) && !strA3.equals(TrackAction.FAIL)) {
            return "Lenovo/VIBE/" + z.a(context, "ro.build.version.incremental");
        }
        String strA4 = z.a(context, "ro.build.nubia.rom.name");
        if (!z.a(strA4) && !strA4.equals(TrackAction.FAIL)) {
            return "Zte/NUBIA/" + strA4 + "_" + z.a(context, "ro.build.nubia.rom.code");
        }
        String strA5 = z.a(context, "ro.meizu.product.model");
        if (!z.a(strA5) && !strA5.equals(TrackAction.FAIL)) {
            return "Meizu/FLYME/" + z.a(context, "ro.build.display.id");
        }
        String strA6 = z.a(context, "ro.build.version.opporom");
        if (!z.a(strA6) && !strA6.equals(TrackAction.FAIL)) {
            return "Oppo/COLOROS/" + strA6;
        }
        String strA7 = z.a(context, "ro.vivo.os.build.display.id");
        if (!z.a(strA7) && !strA7.equals(TrackAction.FAIL)) {
            return "vivo/FUNTOUCH/" + strA7;
        }
        String strA8 = z.a(context, "ro.aa.romver");
        if (!z.a(strA8) && !strA8.equals(TrackAction.FAIL)) {
            return "htc/" + strA8 + "/" + z.a(context, "ro.build.description");
        }
        String strA9 = z.a(context, "ro.lewa.version");
        if (!z.a(strA9) && !strA9.equals(TrackAction.FAIL)) {
            return "tcl/" + strA9 + "/" + z.a(context, "ro.build.display.id");
        }
        String strA10 = z.a(context, "ro.gn.gnromvernumber");
        if (!z.a(strA10) && !strA10.equals(TrackAction.FAIL)) {
            return "amigo/" + strA10 + "/" + z.a(context, "ro.build.display.id");
        }
        String strA11 = z.a(context, "ro.build.tyd.kbstyle_version");
        if (!z.a(strA11) && !strA11.equals(TrackAction.FAIL)) {
            return "dido/" + strA11;
        }
        return z.a(context, "ro.build.fingerprint") + "/" + z.a(context, "ro.build.rom.id");
    }

    public static String e(Context context) {
        return z.a(context, "ro.board.platform");
    }

    public static boolean p() {
        boolean z;
        String[] strArr = a;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (new File(strArr[i]).exists()) {
                z = true;
                break;
            }
            i++;
        }
        return (Build.TAGS != null && Build.TAGS.contains("test-keys")) || z;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0067 A[Catch: all -> 0x008d, TRY_LEAVE, TryCatch #4 {all -> 0x008d, blocks: (B:6:0x0023, B:8:0x0029, B:9:0x002c, B:11:0x0031, B:13:0x003f, B:20:0x0059, B:22:0x0067, B:28:0x007e), top: B:51:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0084 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String q() {
        /*
            java.lang.String r0 = "/sys/block/mmcblk0/device/cid"
            java.lang.String r1 = "/sys/block/mmcblk0/device/name"
            java.lang.String r2 = ","
            java.lang.String r3 = "/sys/block/mmcblk0/device/type"
            r4 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8f
            r5.<init>()     // Catch: java.lang.Throwable -> L8f
            java.io.File r6 = new java.io.File     // Catch: java.lang.Throwable -> L8f
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L8f
            boolean r6 = r6.exists()     // Catch: java.lang.Throwable -> L8f
            if (r6 == 0) goto L30
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L8f
            java.io.FileReader r7 = new java.io.FileReader     // Catch: java.lang.Throwable -> L8f
            r7.<init>(r3)     // Catch: java.lang.Throwable -> L8f
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L8f
            java.lang.String r3 = r6.readLine()     // Catch: java.lang.Throwable -> L8d
            if (r3 == 0) goto L2c
            r5.append(r3)     // Catch: java.lang.Throwable -> L8d
        L2c:
            r6.close()     // Catch: java.lang.Throwable -> L8d
            goto L31
        L30:
            r6 = r4
        L31:
            r5.append(r2)     // Catch: java.lang.Throwable -> L8d
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L8d
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L8d
            boolean r3 = r3.exists()     // Catch: java.lang.Throwable -> L8d
            if (r3 == 0) goto L59
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L8d
            java.io.FileReader r7 = new java.io.FileReader     // Catch: java.lang.Throwable -> L8d
            r7.<init>(r1)     // Catch: java.lang.Throwable -> L8d
            r3.<init>(r7)     // Catch: java.lang.Throwable -> L8d
            java.lang.String r1 = r3.readLine()     // Catch: java.lang.Throwable -> L57
            if (r1 == 0) goto L52
            r5.append(r1)     // Catch: java.lang.Throwable -> L57
        L52:
            r3.close()     // Catch: java.lang.Throwable -> L57
            r6 = r3
            goto L59
        L57:
            r6 = r3
            goto L90
        L59:
            r5.append(r2)     // Catch: java.lang.Throwable -> L8d
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L8d
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L8d
            boolean r1 = r1.exists()     // Catch: java.lang.Throwable -> L8d
            if (r1 == 0) goto L7e
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L8d
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L8d
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L8d
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L8d
            java.lang.String r0 = r1.readLine()     // Catch: java.lang.Throwable -> L7c
            if (r0 == 0) goto L7a
            r5.append(r0)     // Catch: java.lang.Throwable -> L7c
        L7a:
            r6 = r1
            goto L7e
        L7c:
            r6 = r1
            goto L90
        L7e:
            java.lang.String r0 = r5.toString()     // Catch: java.lang.Throwable -> L8d
            if (r6 == 0) goto L8c
            r6.close()     // Catch: java.io.IOException -> L88
            goto L8c
        L88:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L8c:
            return r0
        L8d:
            goto L90
        L8f:
            r6 = r4
        L90:
            if (r6 == 0) goto L9a
            r6.close()     // Catch: java.io.IOException -> L96
            goto L9a
        L96:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L9a:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.q():java.lang.String");
    }

    public static String f(Context context) {
        StringBuilder sb = new StringBuilder();
        String strA = z.a(context, "ro.genymotion.version");
        if (strA != null) {
            sb.append("ro.genymotion.version");
            sb.append("|");
            sb.append(strA);
            sb.append(ShellAdbUtils.COMMAND_LINE_END);
        }
        String strA2 = z.a(context, "androVM.vbox_dpi");
        if (strA2 != null) {
            sb.append("androVM.vbox_dpi");
            sb.append("|");
            sb.append(strA2);
            sb.append(ShellAdbUtils.COMMAND_LINE_END);
        }
        String strA3 = z.a(context, "qemu.sf.fake_camera");
        if (strA3 != null) {
            sb.append("qemu.sf.fake_camera");
            sb.append("|");
            sb.append(strA3);
        }
        return sb.toString();
    }

    public static String g(Context context) {
        BufferedReader bufferedReader;
        Throwable th;
        String line;
        StringBuilder sb = new StringBuilder();
        if (d == null) {
            d = z.a(context, "ro.secure");
        }
        if (d != null) {
            sb.append("ro.secure");
            sb.append("|");
            sb.append(d);
            sb.append(ShellAdbUtils.COMMAND_LINE_END);
        }
        if (e == null) {
            e = z.a(context, "ro.debuggable");
        }
        if (e != null) {
            sb.append("ro.debuggable");
            sb.append("|");
            sb.append(e);
            sb.append(ShellAdbUtils.COMMAND_LINE_END);
        }
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/self/status"));
            do {
                try {
                    line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        x.a(th);
                        return sb.toString();
                    } finally {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e2) {
                                x.a(e2);
                            }
                        }
                    }
                }
            } while (!line.startsWith("TracerPid:"));
            if (line != null) {
                String strTrim = line.substring(10).trim();
                sb.append(SqTrackCommonKey.tracer_pid);
                sb.append("|");
                sb.append(strTrim);
            }
            String string = sb.toString();
            try {
                bufferedReader.close();
            } catch (IOException e3) {
                x.a(e3);
            }
            return string;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004c A[Catch: all -> 0x00a4, TRY_LEAVE, TryCatch #0 {all -> 0x00a4, blocks: (B:3:0x000e, B:6:0x001b, B:13:0x003e, B:15:0x004c, B:22:0x006e, B:24:0x007c), top: B:41:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006e A[Catch: all -> 0x00a4, PHI: r5
  0x006e: PHI (r5v4 java.io.BufferedReader) = (r5v3 java.io.BufferedReader), (r5v9 java.io.BufferedReader) binds: [B:14:0x004a, B:20:0x006a] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TryCatch #0 {all -> 0x00a4, blocks: (B:3:0x000e, B:6:0x001b, B:13:0x003e, B:15:0x004c, B:22:0x006e, B:24:0x007c), top: B:41:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007c A[Catch: all -> 0x00a4, TRY_LEAVE, TryCatch #0 {all -> 0x00a4, blocks: (B:3:0x000e, B:6:0x001b, B:13:0x003e, B:15:0x004c, B:22:0x006e, B:24:0x007c), top: B:41:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a0 A[Catch: IOException -> 0x00ab, TRY_ENTER, TryCatch #4 {IOException -> 0x00ab, blocks: (B:35:0x00a7, B:32:0x00a0), top: B:50:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a7 A[Catch: IOException -> 0x00ab, TRY_LEAVE, TryCatch #4 {IOException -> 0x00ab, blocks: (B:35:0x00a7, B:32:0x00a0), top: B:50:0x000e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String r() {
        /*
            java.lang.String r0 = "/sys/class/power_supply/battery/capacity"
            java.lang.String r1 = "/sys/class/power_supply/usb/online"
            java.lang.String r2 = "\n"
            java.lang.String r3 = "/sys/class/power_supply/ac/online"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r5 = 0
            java.io.File r6 = new java.io.File     // Catch: java.lang.Throwable -> La4
            r6.<init>(r3)     // Catch: java.lang.Throwable -> La4
            boolean r6 = r6.exists()     // Catch: java.lang.Throwable -> La4
            java.lang.String r7 = "|"
            if (r6 == 0) goto L3e
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> La4
            java.io.FileReader r8 = new java.io.FileReader     // Catch: java.lang.Throwable -> La4
            r8.<init>(r3)     // Catch: java.lang.Throwable -> La4
            r6.<init>(r8)     // Catch: java.lang.Throwable -> La4
            java.lang.String r3 = r6.readLine()     // Catch: java.lang.Throwable -> L3b
            if (r3 == 0) goto L36
            java.lang.String r5 = "ac_online"
            r4.append(r5)     // Catch: java.lang.Throwable -> L3b
            r4.append(r7)     // Catch: java.lang.Throwable -> L3b
            r4.append(r3)     // Catch: java.lang.Throwable -> L3b
        L36:
            r6.close()     // Catch: java.lang.Throwable -> L3b
            r5 = r6
            goto L3e
        L3b:
            r5 = r6
            goto La5
        L3e:
            r4.append(r2)     // Catch: java.lang.Throwable -> La4
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> La4
            r3.<init>(r1)     // Catch: java.lang.Throwable -> La4
            boolean r3 = r3.exists()     // Catch: java.lang.Throwable -> La4
            if (r3 == 0) goto L6e
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> La4
            java.io.FileReader r6 = new java.io.FileReader     // Catch: java.lang.Throwable -> La4
            r6.<init>(r1)     // Catch: java.lang.Throwable -> La4
            r3.<init>(r6)     // Catch: java.lang.Throwable -> La4
            java.lang.String r1 = r3.readLine()     // Catch: java.lang.Throwable -> L6c
            if (r1 == 0) goto L67
            java.lang.String r5 = "usb_online"
            r4.append(r5)     // Catch: java.lang.Throwable -> L6c
            r4.append(r7)     // Catch: java.lang.Throwable -> L6c
            r4.append(r1)     // Catch: java.lang.Throwable -> L6c
        L67:
            r3.close()     // Catch: java.lang.Throwable -> L6c
            r5 = r3
            goto L6e
        L6c:
            r5 = r3
            goto La5
        L6e:
            r4.append(r2)     // Catch: java.lang.Throwable -> La4
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> La4
            r1.<init>(r0)     // Catch: java.lang.Throwable -> La4
            boolean r1 = r1.exists()     // Catch: java.lang.Throwable -> La4
            if (r1 == 0) goto L9e
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> La4
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> La4
            r2.<init>(r0)     // Catch: java.lang.Throwable -> La4
            r1.<init>(r2)     // Catch: java.lang.Throwable -> La4
            java.lang.String r0 = r1.readLine()     // Catch: java.lang.Throwable -> L9c
            if (r0 == 0) goto L97
            java.lang.String r2 = "battery_capacity"
            r4.append(r2)     // Catch: java.lang.Throwable -> L9c
            r4.append(r7)     // Catch: java.lang.Throwable -> L9c
            r4.append(r0)     // Catch: java.lang.Throwable -> L9c
        L97:
            r1.close()     // Catch: java.lang.Throwable -> L9c
            r5 = r1
            goto L9e
        L9c:
            r5 = r1
            goto La5
        L9e:
            if (r5 == 0) goto Laf
            r5.close()     // Catch: java.io.IOException -> Lab
            goto Laf
        La4:
        La5:
            if (r5 == 0) goto Laf
            r5.close()     // Catch: java.io.IOException -> Lab
            goto Laf
        Lab:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        Laf:
            java.lang.String r0 = r4.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.r():java.lang.String");
    }

    public static String h(Context context) {
        StringBuilder sb = new StringBuilder();
        String strA = z.a(context, "gsm.sim.state");
        if (strA != null) {
            sb.append("gsm.sim.state");
            sb.append("|");
            sb.append(strA);
        }
        sb.append(ShellAdbUtils.COMMAND_LINE_END);
        String strA2 = z.a(context, "gsm.sim.state2");
        if (strA2 != null) {
            sb.append("gsm.sim.state2");
            sb.append("|");
            sb.append(strA2);
        }
        return sb.toString();
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0030 -> B:34:0x0041). Please report as a decompilation issue!!! */
    public static long s() {
        float fCurrentTimeMillis = 0.0f;
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/proc/uptime"));
                try {
                    String line = bufferedReader2.readLine();
                    if (line != null) {
                        fCurrentTimeMillis = (System.currentTimeMillis() / 1000) - Float.parseFloat(line.split(" ")[0]);
                    }
                    bufferedReader2.close();
                } catch (Throwable unused) {
                    bufferedReader = bufferedReader2;
                    try {
                        x.a("Failed to get boot time of device.", new Object[0]);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return (long) fCurrentTimeMillis;
                    } catch (Throwable th) {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e2) {
                                x.a(e2);
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable unused2) {
            }
        } catch (IOException e3) {
            x.a(e3);
        }
        return (long) fCurrentTimeMillis;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Duplicate predecessors in PHI insn: B:26:0x0036, 0x0036: PHI (r2v2 ?? I:java.io.File) = (r2v6 ?? I:java.io.File) binds: [B:26:0x0036] A[DONT_GENERATE, DONT_INLINE]
        	at jadx.core.dex.instructions.PhiInsn.bindArg(PhiInsn.java:44)
        	at jadx.core.dex.visitors.ConstructorVisitor.insertPhiInsn(ConstructorVisitor.java:157)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:91)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public static boolean i(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Duplicate predecessors in PHI insn: B:26:0x0036, 0x0036: PHI (r2v2 ?? I:java.io.File) = (r2v6 ?? I:java.io.File) binds: [B:26:0x0036] A[DONT_GENERATE, DONT_INLINE]
        	at jadx.core.dex.instructions.PhiInsn.bindArg(PhiInsn.java:44)
        	at jadx.core.dex.visitors.ConstructorVisitor.insertPhiInsn(ConstructorVisitor.java:157)
        	at jadx.core.dex.visitors.ConstructorVisitor.processInvoke(ConstructorVisitor.java:91)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:56)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r4v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:272)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:159)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
        	at jadx.core.ProcessClass.process(ProcessClass.java:88)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:126)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:343)
        */

    private static String k(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            String[] strArr = b;
            if (i >= strArr.length) {
                break;
            }
            try {
                packageManager.getPackageInfo(strArr[i], 1);
                arrayList.add(Integer.valueOf(i));
            } catch (PackageManager.NameNotFoundException unused) {
            }
            i++;
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList.toString();
    }

    public static boolean j(Context context) {
        return (((l(context) | v()) | w()) | u()) > 0;
    }

    private static int u() {
        try {
            Method method = Class.forName("android.app.ActivityManagerNative").getMethod("getDefault", new Class[0]);
            method.setAccessible(true);
            return method.invoke(null, new Object[0]).getClass().getName().startsWith("$Proxy") ? 256 : 0;
        } catch (Exception unused) {
            return 256;
        }
    }

    private static int l(Context context) {
        int i;
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getInstallerPackageName("de.robv.android.xposed.installer");
            i = 1;
        } catch (Exception unused) {
            i = 0;
        }
        try {
            packageManager.getInstallerPackageName("com.saurik.substrate");
            return i | 2;
        } catch (Exception unused2) {
            return i;
        }
    }

    private static int v() {
        try {
            throw new Exception("detect hook");
        } catch (Exception e2) {
            int i = 0;
            int i2 = 0;
            for (StackTraceElement stackTraceElement : e2.getStackTrace()) {
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("main")) {
                    i |= 4;
                }
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("handleHookedMethod")) {
                    i |= 8;
                }
                if (stackTraceElement.getClassName().equals("com.saurik.substrate.MS$2") && stackTraceElement.getMethodName().equals("invoked")) {
                    i |= 16;
                }
                if (stackTraceElement.getClassName().equals("com.android.internal.os.ZygoteInit") && (i2 = i2 + 1) == 2) {
                    i |= 32;
                }
            }
            return i;
        }
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x00b7: MOVE (r1 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:51:0x00b7 */
    private static int w() throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        IOException e2;
        UnsupportedEncodingException e3;
        FileNotFoundException e4;
        HashSet hashSet;
        int i = 0;
        BufferedReader bufferedReader3 = null;
        try {
        } catch (Throwable th) {
            th = th;
            bufferedReader3 = bufferedReader;
        }
        try {
            try {
                hashSet = new HashSet();
                bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/maps"), "utf-8"));
            } catch (IOException e5) {
                e5.printStackTrace();
            }
        } catch (FileNotFoundException e6) {
            bufferedReader2 = null;
            e4 = e6;
        } catch (UnsupportedEncodingException e7) {
            bufferedReader2 = null;
            e3 = e7;
        } catch (IOException e8) {
            bufferedReader2 = null;
            e2 = e8;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedReader3 != null) {
                try {
                    bufferedReader3.close();
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
            }
            throw th;
        }
        while (true) {
            try {
                String line = bufferedReader2.readLine();
                if (line == null) {
                    break;
                }
                if (line.endsWith(".so") || line.endsWith(".jar")) {
                    hashSet.add(line.substring(line.lastIndexOf(" ") + 1));
                }
            } catch (FileNotFoundException e10) {
                e4 = e10;
                e4.printStackTrace();
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                return i;
            } catch (UnsupportedEncodingException e11) {
                e3 = e11;
                e3.printStackTrace();
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                return i;
            } catch (IOException e12) {
                e2 = e12;
                e2.printStackTrace();
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                return i;
            }
            return i;
        }
        for (Object obj : hashSet) {
            if (((String) obj).toLowerCase().contains("xposed")) {
                i |= 64;
            }
            if (((String) obj).contains("com.saurik.substrate")) {
                i |= 128;
            }
        }
        bufferedReader2.close();
        return i;
    }
}
