package com.getui.gtc.dim.c;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Settings;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.aliyunface.utils.MobileUtil;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.annotation.MutableMethod;
import com.getui.gtc.base.util.CommonUtil;
import com.jiguang.h5.PermissionUtils;
import com.parameters.performfeatureconfig.PerformFeatureKey;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    public static final BroadcastReceiver b;
    public static final Map<String, String> c;
    private static final Lock e;
    private static final Condition f;
    private static final Map<String, String> g;
    private static final Map<String, String> d = new HashMap<String, String>() { // from class: com.getui.gtc.dim.c.a.1
        {
            put("huawei", "ro.build.version.emui");
            put("xiaomi", "ro.build.version.incremental");
            put("redmi", "ro.build.version.incremental");
            put("blackshark", "ro.build.version.incremental");
            put("samsang", "ro.build.version.incremental");
            put("vivo", "ro.vivo.os.version");
            put("oppo", "ro.build.version.opporom");
            put("meizu", "ro.build.display.id");
            put("lenovo", "ro.build.version.incremental");
            put("smartisan", "ro.modversion");
            put("htc", "ro.build.sense.version");
            put("oneplus", "ro.rom.version");
            put("yunos", "ro.cta.yunos.version");
            put("360", "ro.build.uiversion");
            put("nubia", "ro.build.rom.internal.id");
        }
    };
    public static final Map<String, String> a = new HashMap();

    /* JADX INFO: renamed from: com.getui.gtc.dim.c.a$a, reason: collision with other inner class name */
    class ServiceConnectionC0039a implements ServiceConnection {
        boolean a = false;
        final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);

        ServiceConnectionC0039a() {
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (Throwable th) {
                com.getui.gtc.dim.d.a.a(th);
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
        }
    }

    class b implements IInterface {
        private IBinder a;

        public b(IBinder iBinder) {
            this.a = iBinder;
        }

        public final String a() throws RemoteException {
            String string;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                try {
                    parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    this.a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    string = parcelObtain2.readString();
                } catch (Exception e) {
                    com.getui.gtc.dim.d.a.a(e);
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    string = null;
                }
                return string;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this.a;
        }
    }

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        e = reentrantLock;
        f = reentrantLock.newCondition();
        b = new BroadcastReceiver() { // from class: com.getui.gtc.dim.c.a.2
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                try {
                    if ("android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                        try {
                            a.e.tryLock(5000L, TimeUnit.MILLISECONDS);
                            a.f.signal();
                        } catch (Throwable th) {
                            com.getui.gtc.dim.d.a.a(th);
                            try {
                                a.e.unlock();
                            } catch (Throwable unused) {
                            }
                        }
                    }
                } finally {
                    try {
                        a.e.unlock();
                    } catch (Throwable unused2) {
                    }
                }
            }
        };
        g = new HashMap<String, String>() { // from class: com.getui.gtc.dim.c.a.3
            {
                put("huawei", "com.android.permission.GET_INSTALLED_APP");
                put("honor", "com.android.permission.GET_INSTALLED_APPS");
            }
        };
        c = new HashMap();
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00a8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:? A[RETURN, SYNTHETIC] */
    @com.getui.gtc.base.annotation.MutableMethod(name = "mac")
    @android.annotation.SuppressLint({"MissingPermission"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a() throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8a java.lang.Throwable -> L8f
            r1.<init>()     // Catch: java.lang.Throwable -> L8a java.lang.Throwable -> L8f
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L8a java.lang.Throwable -> L8f
            java.lang.String r3 = "ip addr"
            java.lang.Process r2 = r2.exec(r3)     // Catch: java.lang.Throwable -> L8a java.lang.Throwable -> L8f
            java.io.InputStream r3 = r2.getInputStream()     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L87
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L87
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L87
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L87
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L84 java.lang.Throwable -> L87
        L1e:
            java.lang.String r0 = r3.readLine()     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            if (r0 == 0) goto L75
            java.lang.String r4 = "^\\d+: ((wlan\\d+)|(eth\\d+)): .*"
            boolean r4 = java.util.regex.Pattern.matches(r4, r0)     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            if (r4 == 0) goto L1e
            java.lang.String r4 = ": "
            int r4 = r0.indexOf(r4)     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            int r4 = r4 + 2
            java.lang.String r0 = r0.substring(r4)     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            int r4 = r1.length()     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            if (r4 != 0) goto L41
            java.lang.String r4 = ""
            goto L43
        L41:
            java.lang.String r4 = ","
        L43:
            r1.append(r4)     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            r4 = 0
            java.lang.String r5 = ": "
            int r5 = r0.indexOf(r5)     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            java.lang.String r0 = r0.substring(r4, r5)     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            r1.append(r0)     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            java.lang.String r0 = "#"
            r1.append(r0)     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            java.lang.String r0 = r3.readLine()     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            if (r0 == 0) goto L1e
            java.lang.String r4 = "link/ether "
            int r4 = r0.indexOf(r4)     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            int r4 = r4 + 11
            java.lang.String r5 = " brd"
            int r5 = r0.indexOf(r5)     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            java.lang.String r0 = r0.substring(r4, r5)     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            r1.append(r0)     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            goto L1e
        L75:
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L82 java.lang.Throwable -> La5
            r3.close()     // Catch: java.lang.Throwable -> L7c
        L7c:
            if (r2 == 0) goto L81
            r2.destroy()     // Catch: java.lang.Throwable -> L81
        L81:
            return r0
        L82:
            r0 = move-exception
            goto L93
        L84:
            r1 = move-exception
            r3 = r0
            goto L8d
        L87:
            r1 = move-exception
            r3 = r0
            goto L92
        L8a:
            r1 = move-exception
            r2 = r0
            r3 = r2
        L8d:
            r0 = r1
            goto La6
        L8f:
            r1 = move-exception
            r2 = r0
            r3 = r2
        L92:
            r0 = r1
        L93:
            com.getui.gtc.dim.d.a.a(r0)     // Catch: java.lang.Throwable -> La5
            if (r3 == 0) goto L9d
            r3.close()     // Catch: java.lang.Throwable -> L9c
            goto L9d
        L9c:
        L9d:
            if (r2 == 0) goto La2
            r2.destroy()     // Catch: java.lang.Throwable -> La2
        La2:
            java.lang.String r0 = ""
            return r0
        La5:
            r0 = move-exception
        La6:
            if (r3 == 0) goto Lad
            r3.close()     // Catch: java.lang.Throwable -> Lac
            goto Lad
        Lac:
        Lad:
            if (r2 == 0) goto Lb2
            r2.destroy()     // Catch: java.lang.Throwable -> Lb2
        Lb2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.c.a.a():java.lang.String");
    }

    @MutableMethod(name = "imei")
    public static String a(int i, Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                throw new RuntimeException("can not get imei above 29");
            }
            if ("vivo".equalsIgnoreCase(b()) && Build.VERSION.SDK_INT < 26) {
                throw new RuntimeException("do not get imei from vivo below 29");
            }
            com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_READ_PHONE_STATE, true);
            Object objA = com.getui.gtc.dim.d.b.a(i, "getDeviceId", context);
            return objA != null ? (String) objA : "";
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = "imei")
    @SuppressLint({"MissingPermission"})
    public static String a(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                throw new RuntimeException("can not get imei above 29");
            }
            com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_READ_PHONE_STATE, true);
            String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            return TextUtils.isEmpty(deviceId) ? "" : deviceId;
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = "brand")
    public static String b() {
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = "imsi")
    public static String b(int i, Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                throw new RuntimeException("can not get imsi above 29");
            }
            com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_READ_PHONE_STATE, true);
            Object objA = com.getui.gtc.dim.d.b.a(i, "getSubscriberId", context);
            return objA != null ? (String) objA : "";
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = "imsi")
    @SuppressLint({"MissingPermission"})
    public static String b(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                throw new RuntimeException("can not get imsi above 29");
            }
            com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_READ_PHONE_STATE, true);
            String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            return !TextUtils.isEmpty(subscriberId) ? subscriberId : "";
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0060 A[DONT_GENERATE, PHI: r1 r2
  0x0060: PHI (r1v2 android.database.Cursor) = (r1v1 android.database.Cursor), (r1v5 android.database.Cursor) binds: [B:30:0x006e, B:21:0x005e] A[DONT_GENERATE, DONT_INLINE]
  0x0060: PHI (r2v3 int) = (r2v1 int), (r2v9 int) binds: [B:30:0x006e, B:21:0x005e] A[DONT_GENERATE, DONT_INLINE]] */
    @com.getui.gtc.base.annotation.MutableMethod(name = "imsi")
    @android.annotation.SuppressLint({"MissingPermission"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int c(int r10, android.content.Context r11) {
        /*
            r0 = -1
            r1 = 0
            java.lang.String r2 = "android.permission.READ_PHONE_STATE"
            r3 = 1
            com.getui.gtc.dim.d.b.a(r11, r2, r3)     // Catch: java.lang.Throwable -> L67 java.lang.Throwable -> L69
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L67
            r4 = 22
            if (r2 < r4) goto L1b
            android.telephony.SubscriptionManager r2 = android.telephony.SubscriptionManager.from(r11)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L67
            android.telephony.SubscriptionInfo r2 = r2.getActiveSubscriptionInfoForSimSlotIndex(r10)     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L67
            int r2 = r2.getSubscriptionId()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L67
            goto L1c
        L1b:
            r2 = -1
        L1c:
            if (r2 == r0) goto L1f
            goto L5e
        L1f:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L27 java.lang.Throwable -> L67
            java.lang.String r4 = "invalid subId"
            r0.<init>(r4)     // Catch: java.lang.Throwable -> L27 java.lang.Throwable -> L67
            throw r0     // Catch: java.lang.Throwable -> L27 java.lang.Throwable -> L67
        L27:
            r0 = r2
        L28:
            android.content.ContentResolver r4 = r11.getContentResolver()     // Catch: java.lang.Throwable -> L64 java.lang.Throwable -> L67
            java.lang.String r11 = "content://telephony/siminfo"
            android.net.Uri r5 = android.net.Uri.parse(r11)     // Catch: java.lang.Throwable -> L64 java.lang.Throwable -> L67
            java.lang.String r11 = "_id"
            java.lang.String r2 = "sim_id"
            java.lang.String[] r6 = new java.lang.String[]{r11, r2}     // Catch: java.lang.Throwable -> L64 java.lang.Throwable -> L67
            java.lang.String r7 = "sim_id = ?"
            java.lang.String[] r8 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L64 java.lang.Throwable -> L67
            r11 = 0
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch: java.lang.Throwable -> L64 java.lang.Throwable -> L67
            r8[r11] = r10     // Catch: java.lang.Throwable -> L64 java.lang.Throwable -> L67
            r9 = 0
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L64 java.lang.Throwable -> L67
            if (r1 == 0) goto L5d
            boolean r10 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L64 java.lang.Throwable -> L67
            if (r10 == 0) goto L5d
            java.lang.String r10 = "_id"
            int r10 = r1.getColumnIndex(r10)     // Catch: java.lang.Throwable -> L64 java.lang.Throwable -> L67
            int r2 = r1.getInt(r10)     // Catch: java.lang.Throwable -> L64 java.lang.Throwable -> L67
            goto L5e
        L5d:
            r2 = r0
        L5e:
            if (r1 == 0) goto L71
        L60:
            r1.close()
            goto L71
        L64:
            r10 = move-exception
            r2 = r0
            goto L6b
        L67:
            r10 = move-exception
            goto L72
        L69:
            r10 = move-exception
            r2 = -1
        L6b:
            com.getui.gtc.dim.d.a.a(r10)     // Catch: java.lang.Throwable -> L67
            if (r1 == 0) goto L71
            goto L60
        L71:
            return r2
        L72:
            if (r1 == 0) goto L77
            r1.close()
        L77:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.c.a.c(int, android.content.Context):int");
    }

    @MutableMethod(name = "model")
    public static String c() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = "iccid,serialnumber")
    @SuppressLint({"MissingPermission"})
    public static String c(Context context) {
        String str;
        str = "";
        try {
            com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_READ_PHONE_STATE, true);
            String simSerialNumber = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            str = TextUtils.isEmpty(simSerialNumber) ? "" : simSerialNumber;
            return !TextUtils.isEmpty(str) ? str.length() < 20 ? "" : str : str;
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return str;
        }
    }

    @MutableMethod(name = "rom")
    public static String d() {
        try {
            String strB = b();
            if (!TextUtils.isEmpty(strB)) {
                String lowerCase = strB.toLowerCase();
                if (a.containsKey(lowerCase)) {
                    return com.getui.gtc.dim.d.b.a(a.get(lowerCase), "");
                }
                if (d.containsKey(lowerCase)) {
                    return com.getui.gtc.dim.d.b.a(d.get(lowerCase), "");
                }
            }
            String strE = e();
            if (TextUtils.isEmpty(strE)) {
                return "";
            }
            String lowerCase2 = strE.toLowerCase();
            return a.containsKey(lowerCase2) ? com.getui.gtc.dim.d.b.a(a.get(lowerCase2), "") : d.containsKey(lowerCase2) ? com.getui.gtc.dim.d.b.a(d.get(lowerCase2), "") : "";
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = "iccid,serialnumber")
    public static String d(int i, Context context) {
        String str;
        str = "";
        try {
            com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_READ_PHONE_STATE, true);
            Object objA = com.getui.gtc.dim.d.b.a(i, "getSimSerialNumber", context);
            str = objA != null ? (String) objA : "";
            return !TextUtils.isEmpty(str) ? str.length() < 20 ? "" : str : str;
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return str;
        }
    }

    @MutableMethod(name = "androidId")
    public static String d(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = "manufacturer")
    public static String e() {
        try {
            return Build.MANUFACTURER;
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = "advertisingId")
    public static String e(Context context) {
        try {
            if (CommonUtil.isMainThread()) {
                throw new RuntimeException("cannot get advertisingId from main thread");
            }
            try {
                context.getPackageManager().getPackageInfo("com.android.vending", 0);
            } catch (Throwable th) {
                com.getui.gtc.dim.d.a.a(th);
            }
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            ServiceConnectionC0039a serviceConnectionC0039a = new ServiceConnectionC0039a();
            try {
                if (!context.bindService(intent, serviceConnectionC0039a, 1)) {
                    throw new IOException("Google Play connection failed");
                }
                try {
                    if (serviceConnectionC0039a.a) {
                        throw new IllegalStateException();
                    }
                    serviceConnectionC0039a.a = true;
                    return new b(serviceConnectionC0039a.b.poll(3000L, TimeUnit.MILLISECONDS)).a();
                } catch (Exception e2) {
                    throw e2;
                }
            } finally {
                context.unbindService(serviceConnectionC0039a);
            }
        } catch (Throwable th2) {
            com.getui.gtc.dim.d.a.a(th2);
            return "";
        }
    }

    @MutableMethod(name = "sysVersion")
    public static String f() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = "serialnumber")
    public static String f(Context context) {
        Object objInvoke;
        try {
            com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_READ_PHONE_STATE, true);
            if (Build.VERSION.SDK_INT < 26) {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                objInvoke = cls.getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class).invoke(cls, "ro.serialno");
            } else {
                Class<?> cls2 = Class.forName("android.os.Build");
                objInvoke = cls2.getMethod("getSerial", new Class[0]).invoke(cls2, new Object[0]);
            }
            return (String) objInvoke;
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return null;
        }
    }

    public static String g() {
        try {
            com.getui.gtc.dim.d.b.a(GtcProvider.context(), PermissionUtils.PERMISSION_READ_EXTERNAL_STORAGE, true);
            return new String(com.getui.gtc.dim.d.b.a(new File(GtcProvider.getSdcardPath() + "/libs", "com.igexin.sdk.deviceId.db")), "UTF-8");
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = PerformFeatureKey.KEY_OAID)
    public static String g(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new RuntimeException("can not get oaid at main thread");
            }
            com.getui.gtc.dim.c.b.a();
            if (com.getui.gtc.dim.c.b.a != null && context != null) {
                com.getui.gtc.dim.c.b.b = context.getApplicationContext();
                boolean zB = com.getui.gtc.dim.c.b.b();
                com.getui.gtc.dim.c.b.d = zB;
                if (zB) {
                    com.getui.gtc.dim.c.b.c = com.getui.gtc.dim.c.b.a.c(com.getui.gtc.dim.c.b.b);
                }
            }
            if (com.getui.gtc.dim.c.b.c) {
                return com.getui.gtc.dim.c.b.c();
            }
            return null;
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = "mac")
    @SuppressLint({"MissingPermission"})
    public static String h(Context context) {
        byte[] hardwareAddress;
        String string = "";
        try {
            if (Build.VERSION.SDK_INT < 23) {
                com.getui.gtc.dim.d.b.a(context, "android.permission.ACCESS_WIFI_STATE", true);
                return ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
            }
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if ("wlan0".equalsIgnoreCase(networkInterfaceNextElement.getName()) && (hardwareAddress = networkInterfaceNextElement.getHardwareAddress()) != null && hardwareAddress.length != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b2)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    string = sb.toString();
                }
            }
            return string;
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = "carrier")
    @SuppressLint({"MissingPermission"})
    public static String i(Context context) {
        try {
            return com.getui.gtc.dim.d.b.a(((TelephonyManager) context.getSystemService("phone")).getSimOperator());
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = "networkType")
    @SuppressLint({"MissingPermission"})
    public static String j(Context context) {
        try {
            com.getui.gtc.dim.d.b.a(context, "android.permission.ACCESS_NETWORK_STATE", true);
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                throw new IllegalStateException("getSystemService: CONNECTIVITY_SERVICE failed");
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                throw new IllegalStateException("getActiveNetworkInfo failed");
            }
            if (!activeNetworkInfo.isAvailable()) {
                throw new IllegalStateException("no available activeNetwork");
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                return MobileUtil.NETWORK_WIFI;
            }
            int subtype = activeNetworkInfo.getSubtype();
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                subtype = telephonyManager.getNetworkType();
            }
            if (subtype == 20) {
                return "5G";
            }
            switch (subtype) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return MobileUtil.NETWORK_2G;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return MobileUtil.NETWORK_3G;
                case 13:
                    return MobileUtil.NETWORK_4G;
                default:
                    return "NULL";
            }
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "NULL";
        }
    }

    @MutableMethod(name = "ip")
    public static String k(Context context) {
        try {
            if (!com.getui.gtc.dim.d.b.a(context)) {
                throw new IllegalStateException("network not connected");
            }
            boolean zB = com.getui.gtc.dim.d.b.b(context);
            boolean zC = com.getui.gtc.dim.d.b.c(context);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if ((zB && networkInterfaceNextElement.getName().toLowerCase().contains("rmnet")) || (zC && networkInterfaceNextElement.getName().toLowerCase().contains("wlan0"))) {
                    List<InterfaceAddress> interfaceAddresses = networkInterfaceNextElement.getInterfaceAddresses();
                    ArrayList arrayList3 = new ArrayList();
                    Iterator<InterfaceAddress> it = interfaceAddresses.iterator();
                    while (it.hasNext()) {
                        InetAddress address = it.next().getAddress();
                        if (!address.isLoopbackAddress()) {
                            arrayList3.add(address.getHostAddress());
                        }
                    }
                    if (zB) {
                        arrayList.addAll(arrayList3);
                    }
                    if (zC) {
                        arrayList2.addAll(arrayList3);
                    }
                }
            }
            if (zB) {
                StringBuilder sb = new StringBuilder();
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    sb.append((String) it2.next());
                    sb.append(com.igexin.push.core.b.aj);
                }
                if (sb.toString().endsWith(com.igexin.push.core.b.aj)) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString();
            }
            if (!zC) {
                return "";
            }
            StringBuilder sb2 = new StringBuilder();
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                sb2.append((String) it3.next());
                sb2.append(com.igexin.push.core.b.aj);
            }
            if (sb2.toString().endsWith(com.igexin.push.core.b.aj)) {
                sb2.deleteCharAt(sb2.length() - 1);
            }
            return sb2.toString();
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = "ip")
    public static String l(Context context) {
        try {
            if (!com.getui.gtc.dim.d.b.a(context)) {
                throw new IllegalStateException("network not connected");
            }
            boolean zB = com.getui.gtc.dim.d.b.b(context);
            boolean zC = com.getui.gtc.dim.d.b.c(context);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                String lowerCase = networkInterfaceNextElement.getName().toLowerCase();
                if ((zB && (lowerCase.contains("rmnet") || lowerCase.contains("ccmni"))) || (zC && lowerCase.contains("wlan0"))) {
                    List<InterfaceAddress> interfaceAddresses = networkInterfaceNextElement.getInterfaceAddresses();
                    ArrayList arrayList3 = new ArrayList();
                    boolean z = false;
                    Iterator<InterfaceAddress> it = interfaceAddresses.iterator();
                    while (it.hasNext()) {
                        InetAddress address = it.next().getAddress();
                        if (!address.isLoopbackAddress()) {
                            if (address instanceof Inet6Address) {
                                arrayList3.add(address.getHostAddress());
                            } else if (address instanceof Inet4Address) {
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        if (zB) {
                            arrayList.addAll(arrayList3);
                        }
                        if (zC) {
                            arrayList2.addAll(arrayList3);
                        }
                    }
                }
            }
            if (zB) {
                StringBuilder sb = new StringBuilder();
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    sb.append((String) it2.next());
                    sb.append(com.igexin.push.core.b.aj);
                }
                if (sb.toString().endsWith(com.igexin.push.core.b.aj)) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString();
            }
            if (!zC) {
                return "";
            }
            StringBuilder sb2 = new StringBuilder();
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                sb2.append((String) it3.next());
                sb2.append(com.igexin.push.core.b.aj);
            }
            if (sb2.toString().endsWith(com.igexin.push.core.b.aj)) {
                sb2.deleteCharAt(sb2.length() - 1);
            }
            return sb2.toString();
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    @MutableMethod(name = RequestParameters.SUBRESOURCE_LOCATION)
    @SuppressLint({"MissingPermission"})
    public static Location m(Context context) {
        try {
            com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_ACCESS_FINE_LOCATION, true);
            return ((LocationManager) context.getSystemService(RequestParameters.SUBRESOURCE_LOCATION)).getLastKnownLocation("gps");
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return null;
        }
    }

    @MutableMethod(name = RequestParameters.SUBRESOURCE_LOCATION)
    @SuppressLint({"MissingPermission"})
    public static Location n(Context context) {
        try {
            com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_ACCESS_COARSE_LOCATION, true);
            return ((LocationManager) context.getSystemService(RequestParameters.SUBRESOURCE_LOCATION)).getLastKnownLocation("network");
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return null;
        }
    }

    @MutableMethod(name = "wifi")
    @SuppressLint({"MissingPermission"})
    public static WifiInfo o(Context context) {
        try {
            com.getui.gtc.dim.d.b.a(context, "android.permission.ACCESS_WIFI_STATE", true);
            return ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return null;
        }
    }

    @MutableMethod(name = "wifi")
    @SuppressLint({"MissingPermission"})
    public static List p(Context context) {
        try {
            try {
                try {
                    e.tryLock(5000L, TimeUnit.MILLISECONDS);
                } catch (Throwable th) {
                    com.getui.gtc.dim.d.a.a(th);
                    context.unregisterReceiver(b);
                }
                if (CommonUtil.isMainThread()) {
                    throw new IllegalStateException("cannot get wifi list from the main thread");
                }
                com.getui.gtc.dim.d.b.a(context, "android.permission.CHANGE_WIFI_STATE", true);
                com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_ACCESS_COARSE_LOCATION, true);
                context.registerReceiver(b, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                if (wifiManager.startScan()) {
                    f.await(4000L, TimeUnit.MILLISECONDS);
                    List<ScanResult> scanResults = wifiManager.getScanResults();
                    if (scanResults != null && scanResults.size() > 0) {
                        return scanResults;
                    }
                }
                context.unregisterReceiver(b);
                e.unlock();
                return null;
            } catch (Throwable unused) {
                return null;
            }
        } finally {
            try {
                context.unregisterReceiver(b);
                e.unlock();
            } catch (Throwable unused2) {
            }
        }
    }

    @MutableMethod(name = "wifi,mac")
    @SuppressLint({"MissingPermission"})
    public static String q(Context context) {
        try {
            com.getui.gtc.dim.d.b.a(context, "android.permission.ACCESS_WIFI_STATE", true);
            if (!com.getui.gtc.dim.d.b.c(context)) {
                return "2##";
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            String ssid = wifiManager.getConnectionInfo().getSSID();
            String strB = com.getui.gtc.dim.d.b.b(com.getui.gtc.dim.d.b.a(wifiManager.getDhcpInfo().gateway));
            return "1#" + ssid.replace("\"", "") + "#" + strB;
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [int] */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r9v6, types: [java.lang.StringBuilder] */
    @MutableMethod(name = "cell")
    @SuppressLint({"MissingPermission"})
    public static String r(Context context) {
        int systemId;
        int i;
        int baseStationId;
        ?? r7;
        boolean z;
        ?? r72;
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_ACCESS_FINE_LOCATION, true);
            } else if (!com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_ACCESS_COARSE_LOCATION) && !com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_ACCESS_FINE_LOCATION)) {
                throw new IllegalStateException("permission coarse/fine location not granted");
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            List list = null;
            if (telephonyManager.getSimState() == 5) {
                String networkOperator = telephonyManager.getNetworkOperator();
                if (networkOperator == null || networkOperator.length() < 3) {
                    systemId = 0;
                    i = 0;
                } else {
                    i = Integer.parseInt(networkOperator.substring(0, 3));
                    systemId = Integer.parseInt(networkOperator.substring(3));
                }
                try {
                    CellLocation cellLocation = telephonyManager.getCellLocation();
                    z = cellLocation instanceof GsmCellLocation;
                    try {
                        if (z) {
                            int lac = ((GsmCellLocation) cellLocation).getLac();
                            baseStationId = ((GsmCellLocation) cellLocation).getCid();
                            r72 = lac;
                        } else if (cellLocation instanceof CdmaCellLocation) {
                            int networkId = ((CdmaCellLocation) cellLocation).getNetworkId();
                            if (systemId == 0) {
                                systemId = ((CdmaCellLocation) cellLocation).getSystemId();
                            }
                            baseStationId = ((CdmaCellLocation) cellLocation).getBaseStationId();
                            r72 = networkId;
                        } else {
                            baseStationId = 0;
                            r72 = 0;
                        }
                    } catch (Throwable th) {
                        th = th;
                        com.getui.gtc.dim.d.a.a(th);
                        baseStationId = 0;
                        r72 = z;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    z = false;
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    r7 = r72;
                } else {
                    list = (List) telephonyManager.getClass().getMethod("getNeighboringCellInfo", new Class[0]).invoke(telephonyManager, new Object[0]);
                    r7 = r72;
                }
            } else {
                systemId = 0;
                i = 0;
                baseStationId = 0;
                r7 = 0;
            }
            ?? sb = new StringBuilder();
            sb.append(i);
            sb.append("|");
            sb.append(systemId);
            sb.append("|");
            sb.append(r7);
            sb.append("|");
            sb.append(baseStationId);
            sb.append("|");
            for (int i2 = 0; list != null && i2 < list.size(); i2++) {
                sb.append(((NeighboringCellInfo) list.get(i2)).getCid());
                if (i2 < list.size() - 1) {
                    sb.append(com.igexin.push.core.b.aj);
                }
            }
            return sb.toString();
        } catch (Throwable th3) {
            com.getui.gtc.dim.d.a.a(th3);
            return "0|0|0|0|";
        }
    }

    @MutableMethod(name = "cell")
    @SuppressLint({"MissingPermission"})
    public static String s(Context context) {
        try {
            if (Build.VERSION.SDK_INT < 17) {
                throw new RuntimeException("device version < 4.2, won't make sense");
            }
            com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_ACCESS_COARSE_LOCATION, true);
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            HashSet hashSet = new HashSet();
            List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
            if (telephonyManager.getSimState() != 5) {
                return "0|0|0|0|";
            }
            if (allCellInfo != null && allCellInfo.size() != 0) {
                int mcc = 0;
                int mnc = 0;
                int lac = 0;
                int cid = 0;
                for (int i = 0; i < allCellInfo.size(); i++) {
                    if (allCellInfo.get(i) instanceof CellInfoLte) {
                        if (mcc == 0) {
                            try {
                                CellIdentityLte cellIdentity = ((CellInfoLte) allCellInfo.get(i)).getCellIdentity();
                                mcc = cellIdentity.getMcc();
                                mnc = cellIdentity.getMnc();
                                lac = cellIdentity.getTac();
                                cid = cellIdentity.getCi();
                            } catch (Exception e2) {
                                e = e2;
                                com.getui.gtc.dim.d.a.a(e);
                            }
                        } else {
                            hashSet.add(Integer.valueOf(((CellInfoLte) allCellInfo.get(i)).getCellIdentity().getCi()));
                        }
                    } else if (Build.VERSION.SDK_INT >= 18 && (allCellInfo.get(i) instanceof CellInfoWcdma)) {
                        if (mcc == 0) {
                            try {
                                CellIdentityWcdma cellIdentity2 = ((CellInfoWcdma) allCellInfo.get(i)).getCellIdentity();
                                mcc = cellIdentity2.getMcc();
                                mnc = cellIdentity2.getMnc();
                                lac = cellIdentity2.getLac();
                                cid = cellIdentity2.getCid();
                            } catch (Exception e3) {
                                e = e3;
                                com.getui.gtc.dim.d.a.a(e);
                            }
                        } else {
                            hashSet.add(Integer.valueOf(((CellInfoWcdma) allCellInfo.get(i)).getCellIdentity().getCid()));
                        }
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append(mcc);
                sb.append("|");
                sb.append(mnc);
                sb.append("|");
                sb.append(lac);
                sb.append("|");
                sb.append(cid);
                sb.append("|");
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    sb.append((Integer) it.next());
                    sb.append(com.igexin.push.core.b.aj);
                }
                if (sb.charAt(sb.length() - 1) == ',') {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString();
            }
            return r(context);
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return "0|0|0|0|";
        }
    }

    @MutableMethod(name = "appList")
    public static List<PackageInfo> t(Context context) {
        try {
            ArrayList arrayList = new ArrayList();
            Intent intent = new Intent();
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (listQueryIntentActivities.size() > 0) {
                Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
                while (it.hasNext()) {
                    PackageInfo packageInfoB = com.getui.gtc.dim.d.b.b(context, it.next().activityInfo.packageName);
                    if (packageInfoB != null) {
                        arrayList.add(packageInfoB);
                    }
                }
            }
            return arrayList;
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return Collections.emptyList();
        }
    }

    @MutableMethod(name = "appList")
    public static List<PackageInfo> u(Context context) {
        String str;
        try {
            String lowerCase = b().toLowerCase();
            if (c.containsKey(lowerCase)) {
                str = c.get(lowerCase);
            } else {
                if (!g.containsKey(lowerCase)) {
                    throw new RuntimeException("not support brand: ".concat(String.valueOf(lowerCase)));
                }
                str = g.get(lowerCase);
            }
            com.getui.gtc.dim.d.b.a(context, str, false);
            return context.getPackageManager().getInstalledPackages(TTDownloadField.CALL_DOWNLOAD_MODEL_SET_ID);
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return Collections.emptyList();
        }
    }

    @MutableMethod(name = "appList")
    public static List<PackageInfo> v(Context context) {
        String[] list;
        File parentFile;
        try {
            com.getui.gtc.dim.d.b.a(context, PermissionUtils.PERMISSION_READ_EXTERNAL_STORAGE, true);
            File externalCacheDir = context.getExternalCacheDir();
            File parentFile2 = null;
            if (externalCacheDir != null && (parentFile = externalCacheDir.getParentFile()) != null) {
                parentFile2 = parentFile.getParentFile();
            }
            if (parentFile2 != null && parentFile2.isDirectory() && (list = parentFile2.list(new FilenameFilter() { // from class: com.getui.gtc.dim.c.a.4
                @Override // java.io.FilenameFilter
                public final boolean accept(File file, String str) {
                    try {
                        if (file.isDirectory()) {
                            if (str.contains(".")) {
                                return true;
                            }
                        }
                    } catch (Throwable unused) {
                    }
                    return false;
                }
            })) != null) {
                ArrayList arrayList = new ArrayList();
                PackageManager packageManager = context.getPackageManager();
                for (String str : list) {
                    try {
                        arrayList.add(packageManager.getPackageInfo(str, 0));
                    } catch (Throwable unused) {
                    }
                }
                return arrayList;
            }
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
        }
        return Collections.emptyList();
    }
}
