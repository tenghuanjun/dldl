package com.getui.gtc.dim.c;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.text.TextUtils;
import com.parameters.performfeatureconfig.PerformFeatureKey;
import java.security.MessageDigest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b {
    static final a a;
    static Context b;
    static boolean c;
    static boolean d;
    public static ThreadPoolExecutor e;
    private static volatile b f;
    private InterfaceC0040b g = null;

    interface a {
        boolean a(Context context);

        String b(Context context);

        boolean c(Context context);
    }

    /* JADX INFO: renamed from: com.getui.gtc.dim.c.b$b, reason: collision with other inner class name */
    public interface InterfaceC0040b {
    }

    static class c implements a {
        private static String a;
        private static boolean b;
        private static boolean c;
        private static final CountDownLatch d = new CountDownLatch(1);
        private String e;
        private String f;
        private String g;
        private String h;
        private f i;

        public c(String str, String str2, String str3, String str4) {
            this.e = str;
            this.f = str2;
            this.g = str3;
            this.h = str4;
        }

        protected String a() {
            return null;
        }

        @Override // com.getui.gtc.dim.c.b.a
        public boolean a(Context context) {
            if (c) {
                return b;
            }
            boolean z = false;
            if (context != null && !TextUtils.isEmpty(this.e)) {
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.e, 0);
                    if (Build.VERSION.SDK_INT >= 28) {
                        if (packageInfo != null) {
                            if (packageInfo.getLongVersionCode() >= 1) {
                                return true;
                            }
                        }
                        return false;
                    }
                    if (packageInfo != null && packageInfo.versionCode > 0) {
                        z = true;
                    }
                } catch (Throwable unused) {
                    return false;
                }
            }
            b = z;
            c = true;
            return b;
        }

        protected int b() {
            return 1;
        }

        @Override // com.getui.gtc.dim.c.b.a
        public String b(Context context) {
            f fVar;
            if (!TextUtils.isEmpty(a) || (fVar = this.i) == null || fVar.a == null) {
                return a;
            }
            try {
                String strA = this.i.a.a(d(context), e(context), a(), b());
                a = strA;
                if (!TextUtils.isEmpty(strA) && this.i != null) {
                    context.unbindService(this.i);
                }
            } catch (Throwable unused) {
            }
            return a;
        }

        @Override // com.getui.gtc.dim.c.b.a
        public boolean c(Context context) {
            if (context == null || TextUtils.isEmpty(this.e)) {
                return false;
            }
            if (this.i == null) {
                this.i = new f(this.h, d);
            }
            Intent intent = new Intent();
            if (TextUtils.isEmpty(this.f)) {
                intent.setPackage(this.e);
            } else {
                intent.setComponent(new ComponentName(this.e, this.f));
            }
            if (!TextUtils.isEmpty(this.g)) {
                intent.setAction(this.g);
            }
            return this.i.a(context, intent);
        }

        protected String d(Context context) {
            return null;
        }

        protected String e(Context context) {
            return null;
        }
    }

    public static class d implements a {
        protected static boolean b;
        private static String d;
        String[] a;
        protected boolean c = false;
        private String e;
        private String f;

        public d(String str, String str2) {
            this.e = str;
            this.f = str2;
        }

        @Override // com.getui.gtc.dim.c.b.a
        public boolean a(Context context) {
            if (this.c) {
                return b;
            }
            if (context == null) {
                return false;
            }
            try {
                PackageManager packageManager = context.getPackageManager();
                b = (packageManager == null || packageManager.resolveContentProvider(this.e, 0) == null) ? false : true;
            } catch (Throwable unused) {
                b = false;
            }
            this.c = true;
            return b;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0059  */
        @Override // com.getui.gtc.dim.c.b.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.String b(android.content.Context r9) throws java.lang.Throwable {
            /*
                r8 = this;
                java.lang.String r0 = com.getui.gtc.dim.c.b.d.d
                boolean r0 = android.text.TextUtils.isEmpty(r0)
                if (r0 == 0) goto L5d
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "content://"
                r0.<init>(r1)
                java.lang.String r1 = r8.e
                r0.append(r1)
                java.lang.String r1 = "/"
                r0.append(r1)
                java.lang.String r1 = r8.f
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                android.net.Uri r2 = android.net.Uri.parse(r0)
                r0 = 0
                android.content.ContentResolver r1 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L4d
                r3 = 0
                r4 = 0
                java.lang.String[] r5 = r8.a     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L4d
                r6 = 0
                android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L48 java.lang.Throwable -> L4d
                if (r9 == 0) goto L45
                r9.moveToFirst()     // Catch: java.lang.Throwable -> L4e java.lang.Throwable -> L56
                java.lang.String r1 = "value"
                int r1 = r9.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L4e java.lang.Throwable -> L56
                java.lang.String r1 = r9.getString(r1)     // Catch: java.lang.Throwable -> L4e java.lang.Throwable -> L56
                com.getui.gtc.dim.c.b.d.d = r1     // Catch: java.lang.Throwable -> L4e java.lang.Throwable -> L56
            L45:
                if (r9 == 0) goto L5d
                goto L52
            L48:
                r9 = move-exception
                r7 = r0
                r0 = r9
                r9 = r7
                goto L57
            L4d:
                r9 = r0
            L4e:
                com.getui.gtc.dim.c.b.d.d = r0     // Catch: java.lang.Throwable -> L56
                if (r9 == 0) goto L5d
            L52:
                r9.close()
                goto L5d
            L56:
                r0 = move-exception
            L57:
                if (r9 == 0) goto L5c
                r9.close()
            L5c:
                throw r0
            L5d:
                java.lang.String r9 = com.getui.gtc.dim.c.b.d.d
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.c.b.d.b(android.content.Context):java.lang.String");
        }

        @Override // com.getui.gtc.dim.c.b.a
        public final boolean c(Context context) {
            return true;
        }
    }

    public static class e implements IInterface {
        private IBinder a;
        private String b;

        private e(IBinder iBinder, String str) {
            this.a = iBinder;
            this.b = str;
        }

        static e a(IBinder iBinder, String str) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(str);
            return iInterfaceQueryLocalInterface instanceof e ? (e) iInterfaceQueryLocalInterface : new e(iBinder, str);
        }

        final String a(String str, String str2, String str3, int i) {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(this.b);
                if (!TextUtils.isEmpty(str)) {
                    parcelObtain.writeString(str);
                }
                if (!TextUtils.isEmpty(str2)) {
                    parcelObtain.writeString(str2);
                }
                if (!TextUtils.isEmpty(str3)) {
                    parcelObtain.writeString(str3);
                }
                this.a.transact(i, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } catch (Throwable unused) {
                try {
                    parcelObtain.recycle();
                    parcelObtain2.recycle();
                    return "";
                } catch (Exception unused2) {
                    return "";
                }
            }
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this.a;
        }
    }

    public static class f implements ServiceConnection {
        e a;
        private String b;
        private CountDownLatch c;
        private IBinder d;

        f(String str, CountDownLatch countDownLatch) {
            this.b = str;
            this.c = countDownLatch;
        }

        final boolean a(Context context, Intent intent) {
            if (context == null) {
                return false;
            }
            if (this.a != null) {
                return true;
            }
            try {
                boolean zBindService = context.bindService(intent, this, 1);
                this.c.await(1L, TimeUnit.SECONDS);
                this.a = e.a(this.d, this.b);
                return zBindService;
            } catch (Throwable unused) {
                return false;
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.d = iBinder;
                this.c.countDown();
            } catch (Throwable unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            this.a = null;
            this.d = null;
        }
    }

    public static class g extends c {
        public g() {
            super("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService", "com.asus.msa.action.ACCESS_DID", "com.asus.msa.SupplementaryDID.IDidAidlInterface");
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ boolean a(Context context) {
            return super.a(context);
        }

        @Override // com.getui.gtc.dim.c.b.c
        protected final int b() {
            return 2;
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ String b(Context context) {
            return super.b(context);
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ boolean c(Context context) {
            return super.c(context);
        }
    }

    public static class h extends c {
        public h() {
            super("com.coolpad.deviceidsupport", "com.coolpad.deviceidsupport.DeviceIdService", null, "com.coolpad.deviceidsupport.IDeviceIdManager");
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ boolean a(Context context) {
            return super.a(context);
        }

        @Override // com.getui.gtc.dim.c.b.c
        protected final int b() {
            return 2;
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ String b(Context context) {
            return super.b(context);
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ boolean c(Context context) {
            return super.c(context);
        }
    }

    public static class i extends c {
        public i() {
            super("com.huawei.hwid", null, "com.uodis.opendevice.OPENIDS_SERVICE", "com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ boolean a(Context context) {
            return super.a(context);
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ String b(Context context) {
            return super.b(context);
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ boolean c(Context context) {
            return super.c(context);
        }
    }

    public static class j extends d {
        public j() {
            super("com.meizu.flyme.openidsdk", "");
        }

        @Override // com.getui.gtc.dim.c.b.d, com.getui.gtc.dim.c.b.a
        public final boolean a(Context context) {
            if (super.a(context)) {
                b = true;
            } else {
                try {
                    Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://com.meizu.flyme.openidsdk/"), null, null, new String[]{"support"}, null);
                    if (cursorQuery == null) {
                        return false;
                    }
                    cursorQuery.moveToFirst();
                    int columnIndex = cursorQuery.getColumnIndex("value");
                    if (columnIndex >= 0) {
                        String string = cursorQuery.getString(columnIndex);
                        if (TextUtils.isEmpty(string)) {
                            return false;
                        }
                        b = "0".equals(string);
                    } else {
                        b = false;
                    }
                } catch (Throwable unused) {
                    b = false;
                    return false;
                }
            }
            this.c = true;
            return b;
        }

        @Override // com.getui.gtc.dim.c.b.d, com.getui.gtc.dim.c.b.a
        public final String b(Context context) {
            this.a = new String[]{PerformFeatureKey.KEY_OAID};
            return super.b(context);
        }
    }

    public static class k extends c {
        private String a;
        private String b;

        public k() {
            super("com.heytap.openid", "com.heytap.openid.IdentifyService", "action.com.heytap.openid.OPEN_ID_SERVICE", "com.heytap.openid.IOpenID");
        }

        @Override // com.getui.gtc.dim.c.b.c
        protected final String a() {
            return "OUID";
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ boolean a(Context context) {
            return super.a(context);
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ String b(Context context) {
            return super.b(context);
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ boolean c(Context context) {
            return super.c(context);
        }

        @Override // com.getui.gtc.dim.c.b.c
        protected final String d(Context context) {
            if (TextUtils.isEmpty(this.b)) {
                this.b = context.getPackageName();
            }
            return this.b;
        }

        @Override // com.getui.gtc.dim.c.b.c
        @SuppressLint({"PackageManagerGetSignatures"})
        protected final String e(Context context) {
            if (TextUtils.isEmpty(this.a)) {
                try {
                    this.b = d(context);
                    Signature[] signatureArr = context.getPackageManager().getPackageInfo(this.b, 64).signatures;
                    if (signatureArr != null && signatureArr.length > 0) {
                        byte[] bArrDigest = MessageDigest.getInstance("SHA1").digest(signatureArr[0].toByteArray());
                        StringBuilder sb = new StringBuilder();
                        for (byte b : bArrDigest) {
                            sb.append(Integer.toHexString((b & UByte.MAX_VALUE) | 256).substring(1, 3));
                        }
                        this.a = sb.toString();
                    }
                } catch (Throwable unused) {
                }
            }
            return this.a;
        }
    }

    public static class l extends c {
        public l() {
            super("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService", null, "com.samsung.android.deviceidservice.IDeviceIdService");
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ boolean a(Context context) {
            return super.a(context);
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ String b(Context context) {
            return super.b(context);
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ boolean c(Context context) {
            return super.c(context);
        }
    }

    public static class m extends d {
        public m() {
            super("com.vivo.vms.IdProvider", "IdentifierId/OAID");
        }
    }

    public static class n implements a {
        private static String b;
        private Class a = null;

        @Override // com.getui.gtc.dim.c.b.a
        @SuppressLint({"PrivateApi"})
        public final boolean a(Context context) {
            try {
                this.a = Class.forName("com.android.id.impl.IdProviderImpl");
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }

        @Override // com.getui.gtc.dim.c.b.a
        public final String b(Context context) {
            if (TextUtils.isEmpty(b)) {
                try {
                    b = String.valueOf(this.a.getMethod("getOAID", Context.class).invoke(this.a.newInstance(), context));
                } catch (Throwable unused) {
                    b = null;
                }
            }
            return b;
        }

        @Override // com.getui.gtc.dim.c.b.a
        public final boolean c(Context context) {
            return true;
        }
    }

    public static class o extends c {
        public o() {
            super("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService", null, "com.zui.deviceidservice.IDeviceidInterface");
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ boolean a(Context context) {
            return super.a(context);
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ String b(Context context) {
            return super.b(context);
        }

        @Override // com.getui.gtc.dim.c.b.c, com.getui.gtc.dim.c.b.a
        public final /* bridge */ /* synthetic */ boolean c(Context context) {
            return super.c(context);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b1  */
    static {
        /*
            Method dump skipped, instruction units count: 336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.c.b.<clinit>():void");
    }

    public static b a() {
        if (f == null) {
            synchronized (b.class) {
                if (f == null) {
                    f = new b();
                }
            }
        }
        return f;
    }

    static boolean b() {
        try {
            if (b == null || a == null) {
                return false;
            }
            return a.a(b);
        } catch (Throwable unused) {
            return false;
        }
    }

    static String c() {
        try {
            if (b != null && a != null && c) {
                return a.b(b);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
