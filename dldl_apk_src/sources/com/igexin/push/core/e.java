package com.igexin.push.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.main.SdkInitSwitch;
import com.igexin.sdk.main.SdkPushSwitch;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class e {
    public static String A = null;
    public static String B = null;
    public static String C = null;
    public static String D = null;
    public static String E = null;
    public static String F = null;
    public static String G = null;
    public static String H = null;
    public static String I = null;
    public static String J = null;
    public static String X = null;
    public static String a = "";
    public static long aE = 0;
    private static final String aK = "CoreRuntimeInfo";
    private static Map<String, Integer> aL = null;
    public static String aa = null;
    public static String ab = null;
    public static String ac = null;
    public static String ad = null;
    public static String ae = null;
    public static byte[] af = null;
    public static boolean ag = false;
    public static boolean ah = false;
    public static boolean ai = false;
    public static Map<String, PushTaskBean> aj = null;
    public static Map<String, Integer> ak = null;
    public static HashMap<String, Long> al = null;
    public static String an = null;
    public static long ao = 0;
    public static String ap = null;
    public static String aq = null;
    public static String ar = null;
    public static String as = null;
    public static String at = null;
    public static String au = null;
    public static long av = 0;
    public static boolean aw = false;
    public static int ax = 0;
    public static byte[] ay = null;
    public static String b = "";
    public static String c = "";
    public static String d = "";
    public static String e = null;
    public static String f = "";
    public static int g;
    public static int h;
    public static Context i;
    public static volatile boolean p;
    public static volatile boolean r;
    public static volatile boolean s;
    public static String x;
    public static String y;
    public static String z;
    public static AtomicBoolean j = new AtomicBoolean(false);
    public static boolean k = true;
    public static HashMap<String, ClassLoader> l = new HashMap<>();
    public static volatile boolean m = true;
    public static volatile boolean n = false;
    public static int o = 0;
    public static boolean q = true;
    public static boolean t = true;
    public static int u = 0;
    public static int v = 0;
    public static long w = 0;
    public static String K = "";
    public static long L = -1;
    public static long M = -1;
    public static long N = 0;
    public static long O = 0;
    public static long P = 0;
    public static long Q = 0;
    public static long R = 0;
    public static long S = 0;
    public static String T = null;
    public static boolean U = false;
    public static long V = 0;
    public static long W = 0;
    public static long Y = 0;
    public static int Z = 0;
    public static int am = 0;
    public static String az = null;
    public static int aA = 3600;
    public static boolean aB = false;
    public static long aC = 7200000;
    public static long aD = 7200000;
    public static String aF = "oppo r9";
    public static int aG = 200;
    public static String aH = "com.meitu.meipaimv,com.baidu.video,com.cmcc.cmvideo,tv.acfundanmaku.video,dopool.player,com.lesports.glivesports,air.fyzb3,com.gameabc.zhanqiAndroid,com.meitu.meiyancamera,com.meitu.airvid,com.meitu.wheecam,com.meitu.cloudphotos,com.meitu.makeup,com.mt.mtxx.mtxx,com.meitu.boxxcam,com.meitu.poster,com.meitu.zhi.beauty,cn.wps.moffice_eng,com.google.android.apps.maps";
    public static String aI = "HUAWEI:false:true,HONOR:false:true,Xiaomi:false:true,HUAWEI:com.huawei.bone:false:false,HONOR:com.huawei.bone:false:false,BAOFENG_TV:false:true,HUAWEI:cmccwm.mobilemusic:false:false,HONOR:cmccwm.mobilemusic:false:false";
    public static String aJ = b.M;

    public static int a(String str) {
        int iIntValue;
        synchronized (e.class) {
            if (aL.get(str) == null) {
                aL.put(str, 0);
            }
            iIntValue = aL.get(str).intValue() - 1;
            aL.put(str, Integer.valueOf(iIntValue));
            if (iIntValue == 0) {
                aL.remove(str);
            }
        }
        return iIntValue;
    }

    private static void a() {
        if (new SdkInitSwitch(i).isSwitchOn()) {
            com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.b, Boolean.TRUE);
            new SdkInitSwitch(i).delete();
        }
        if (new SdkPushSwitch(i).isSwitchOn()) {
            p = true;
            com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.c, Boolean.TRUE);
            new SdkPushSwitch(i).delete();
        }
    }

    public static void a(long j2) {
        w = j2;
        x = com.igexin.b.b.a.a(String.valueOf(j2));
    }

    public static boolean a(Context context) {
        i = context;
        d = context.getPackageName();
        f = com.igexin.push.f.o.b(context, com.igexin.push.f.o.d, "").toString();
        if (!d()) {
            com.igexin.b.a.c.a.a("CoreRuntimeInfo|parseManifests failed", new Object[0]);
            throw new IllegalArgumentException("parseManifests failed");
        }
        af = com.igexin.b.b.a.a(a + context.getPackageName()).getBytes();
        c();
        com.igexin.push.config.e.b();
        if (Build.VERSION.SDK_INT < 29) {
            System.currentTimeMillis();
            A = com.igexin.push.f.n.e();
            System.currentTimeMillis();
            B = com.igexin.push.f.n.d();
            System.currentTimeMillis();
        }
        C = Build.MODEL;
        D = Build.BRAND;
        k = com.igexin.push.f.c.f();
        aj = new ConcurrentHashMap();
        ak = new ConcurrentHashMap();
        al = new HashMap<>();
        p = com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.c, new boolean[0]);
        aL = new HashMap();
        aw = true;
        com.igexin.b.a.c.a.a("CoreRuntimeInfo|getui sdk init success ##########", new Object[0]);
        if (new SdkInitSwitch(i).isSwitchOn()) {
            com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.b, Boolean.TRUE);
            new SdkInitSwitch(i).delete();
        }
        if (new SdkPushSwitch(i).isSwitchOn()) {
            p = true;
            com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.c, Boolean.TRUE);
            new SdkPushSwitch(i).delete();
        }
        return true;
    }

    public static boolean a(String str, Integer num) {
        synchronized (e.class) {
            int iIntValue = num.intValue();
            if (aL.get(str) == null || (iIntValue = aL.get(str).intValue() + num.intValue()) != 0) {
                aL.put(str, Integer.valueOf(iIntValue));
                return true;
            }
            aL.remove(str);
            return false;
        }
    }

    public static ClassLoader b(String str) {
        String str2 = str.split("_")[0];
        if (l.containsKey(str2)) {
            return l.get(str2);
        }
        return null;
    }

    private static String b() {
        return SDKUrlConfig.getConfigServiceUrl();
    }

    private static void c() {
        File[] fileArrListFiles;
        try {
            File file = new File("/sdcard/libs");
            if (file.exists() && file.isFile()) {
                com.igexin.b.a.c.a.a("CoreRuntimeInfo|libs is file not directory, delete libs file ++++", new Object[0]);
                file.delete();
            }
            if (!file.exists() && !file.mkdir()) {
                com.igexin.b.a.c.a.a("CoreRuntimeInfo|create libs directory failed ++++++", new Object[0]);
            }
            i.getFilesDir();
            try {
                File file2 = new File(b.L + "/system/tmp/local");
                if (!file2.exists() && (fileArrListFiles = file2.listFiles(new FilenameFilter() { // from class: com.igexin.push.core.e.1
                    @Override // java.io.FilenameFilter
                    public final boolean accept(File file3, String str) {
                        return str.startsWith("tdata");
                    }
                })) != null && fileArrListFiles.length > 0) {
                    for (File file3 : fileArrListFiles) {
                        file3.delete();
                    }
                }
            } catch (Throwable th) {
                com.igexin.b.a.c.a.a("CoreRuntimeInfo|" + th.toString(), new Object[0]);
            }
            ab = "/sdcard/libs/" + d + ".db";
            ac = "/sdcard/libs/com.igexin.sdk.deviceId.db";
            aa = "/sdcard/libs/" + d + ".properties";
            ad = "/sdcard/libs/" + d + ".bin";
            ae = i.getExternalFilesDir("") + "/" + d + ".properties";
        } catch (Throwable th2) {
            com.igexin.b.a.c.a.a("CoreRuntimeInfo|initFile exception = " + th2.toString(), new Object[0]);
        }
    }

    private static boolean d() {
        try {
            ApplicationInfo applicationInfo = i.getPackageManager().getApplicationInfo(d, 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return false;
            }
            String string = applicationInfo.metaData.getString(b.a);
            if (TextUtils.isEmpty(string)) {
                string = applicationInfo.metaData.getString("GETUI_APPID");
            }
            if (string != null) {
                string = string.trim();
            }
            b = applicationInfo.metaData.getString(b.c);
            if (TextUtils.isEmpty(string)) {
                com.igexin.b.a.c.a.a("CoreRuntimeInfo|getui sdk init error, missing parm #####", new Object[0]);
                return false;
            }
            a = string;
            c = SDKUrlConfig.getLocation();
            return true;
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("CoreRuntimeInfo|get ApplicationInfo meta data exception :" + th.toString(), new Object[0]);
            return false;
        }
    }

    private static void e() {
        if (Build.VERSION.SDK_INT < 29) {
            System.currentTimeMillis();
            A = com.igexin.push.f.n.e();
            System.currentTimeMillis();
            B = com.igexin.push.f.n.d();
            System.currentTimeMillis();
        }
        C = Build.MODEL;
        D = Build.BRAND;
    }
}
