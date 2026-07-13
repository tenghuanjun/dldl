package com.kwai.monitor.oaid;

import a.a.a.b.d;
import a.a.a.f.a;
import android.text.TextUtils;
import android.util.Log;
import com.bun.miitmdid.e;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class OADIDSDKHelper {
    public static boolean a = false;
    public static boolean b = false;

    public static class IIdentifierListenerImpl implements IIdentifierListener {
        public final a mOaidListener;
        public final long mStartTime;

        public IIdentifierListenerImpl(long j, a aVar) {
            this.mStartTime = j;
            this.mOaidListener = aVar;
        }

        public void onSupport(IdSupplier idSupplier) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mStartTime;
            if (idSupplier != null) {
                String oaid = idSupplier.getOAID();
                if (TextUtils.isEmpty(oaid)) {
                    boolean unused = OADIDSDKHelper.b = true;
                } else {
                    Log.d("KS_LOG", "OADIDSDKHelper:oaid time=" + jCurrentTimeMillis + "--OAID:" + oaid);
                    a.a aVar = this.mOaidListener;
                    aVar.getClass();
                    a.a.a.f.a.a = oaid;
                    d.a(aVar.a, "ks_oaid", oaid);
                    a.a.a.f.a.a();
                }
            }
            boolean unused2 = OADIDSDKHelper.a = false;
        }
    }

    public interface a {
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [com.kwai.monitor.oaid.OADIDSDKHelper$1] */
    public static boolean a() {
        try {
            new IIdentifierListener() { // from class: com.kwai.monitor.oaid.OADIDSDKHelper.1
                public void onSupport(IdSupplier idSupplier) {
                }
            }.onSupport(null);
            try {
                Log.d("KS_LOG", "OADIDSDKHelper:oaidVersion" + e.a());
                try {
                    Class.forName("com.bun.miitmdid.core.MdidSdkHelper", false, OADIDSDKHelper.class.getClassLoader());
                    return true;
                } catch (Throwable unused) {
                    Log.d("KS_LOG", "OADIDSDKHelper:com.bun.miitmdid.core.MdidSdkHelper oaid sdk not find ");
                    return false;
                }
            } catch (Throwable unused2) {
                Log.d("KS_LOG", "OADIDSDKHelper:oaidVersion fail");
                return false;
            }
        } catch (Throwable unused3) {
            Log.d("KS_LOG", "OADIDSDKHelper:isSupport oaid sdk not find ");
            return false;
        }
    }
}
