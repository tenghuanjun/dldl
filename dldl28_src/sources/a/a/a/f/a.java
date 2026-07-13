package a.a.a.f;

import a.a.a.b.d;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.kwai.monitor.log.OAIDListener;
import com.kwai.monitor.oaid.OADIDSDKHelper;
import com.kwai.monitor.oaid.OADIDSDKHelper25;

/* JADX INFO: compiled from: OAIDHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f116a = "";
    public static OAIDListener b = null;
    public static volatile boolean c = false;
    public static boolean d = false;

    /* JADX INFO: renamed from: a.a.a.f.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: OAIDHelper.java */
    public class C0008a implements OADIDSDKHelper.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f117a;

        public C0008a(Context context) {
            this.f117a = context;
        }
    }

    /* JADX INFO: compiled from: OAIDHelper.java */
    public class b implements OADIDSDKHelper25.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f118a;

        public b(Context context) {
            this.f118a = context;
        }
    }

    public static /* synthetic */ boolean c() {
        String strA = a("ro.ssui.product");
        return (TextUtils.isEmpty(strA) || strA.equalsIgnoreCase("unknown")) ? false : true;
    }

    public static String a(Context context) {
        if (!d.c(f116a)) {
            return f116a;
        }
        String strA = d.a(context, "ks_oaid");
        f116a = strA;
        if (!d.c(strA)) {
            return f116a;
        }
        b(context);
        return f116a;
    }

    public static void b(Context context) {
        if (d.c(f116a) && context != null) {
            if (TextUtils.isEmpty(f116a) && !d && !c) {
                c = true;
                new Thread(new a.a.a.f.b(context)).start();
            }
            if (OADIDSDKHelper.a()) {
                C0008a c0008a = new C0008a(context);
                if (OADIDSDKHelper.b) {
                    return;
                }
                if (!OADIDSDKHelper.a()) {
                    OADIDSDKHelper.b = true;
                    return;
                }
                if (OADIDSDKHelper.f491a) {
                    return;
                }
                OADIDSDKHelper.f491a = true;
                try {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    int iInitSdk = MdidSdkHelper.InitSdk(context.getApplicationContext(), true, new OADIDSDKHelper.IIdentifierListenerImpl(jCurrentTimeMillis, c0008a));
                    Log.d("KS_LOG", "OADIDSDKHelper:sdk init time=" + (System.currentTimeMillis() - jCurrentTimeMillis) + "--result=" + iInitSdk);
                    return;
                } catch (Throwable unused) {
                    Log.d("KS_LOG", "OADIDSDKHelper:oaid sdk not find ");
                    OADIDSDKHelper.f491a = false;
                    OADIDSDKHelper.b = true;
                    return;
                }
            }
            if (OADIDSDKHelper25.a()) {
                b bVar = new b(context);
                if (OADIDSDKHelper25.b) {
                    return;
                }
                if (!OADIDSDKHelper25.a()) {
                    OADIDSDKHelper25.b = true;
                    return;
                }
                if (OADIDSDKHelper25.f492a) {
                    return;
                }
                OADIDSDKHelper25.f492a = true;
                try {
                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                    int iInitSdk2 = MdidSdkHelper.InitSdk(context.getApplicationContext(), true, new OADIDSDKHelper25.IIdentifierListener25(jCurrentTimeMillis2, bVar));
                    Log.d("KS_LOG", "OADIDSDKHelper25:sdk init time=" + (System.currentTimeMillis() - jCurrentTimeMillis2) + "--result=" + iInitSdk2);
                } catch (Throwable unused2) {
                    Log.d("KS_LOG", "OADIDSDKHelper25:oaid sdk not find ");
                    OADIDSDKHelper25.f492a = false;
                    OADIDSDKHelper25.b = true;
                }
            }
        }
    }

    public static /* synthetic */ void a() {
        try {
            OAIDListener oAIDListener = b;
            if (oAIDListener != null) {
                oAIDListener.OnOAIDValid(f116a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "unknown");
        } catch (Exception unused) {
            return null;
        }
    }

    public static /* synthetic */ boolean b() {
        String strA = a("ro.build.freeme.label");
        return !TextUtils.isEmpty(strA) && strA.equalsIgnoreCase("FREEMEOS");
    }
}
