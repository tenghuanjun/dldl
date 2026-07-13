package com.kwai.monitor.oaid;

import a.a.a.b.d;
import a.a.a.f.a;
import android.text.TextUtils;
import android.util.Log;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class OADIDSDKHelper25 {
    public static boolean a = false;
    public static boolean b = false;

    public static class IIdentifierListener25 implements IIdentifierListener {
        public final a mOaidListener;
        public final long mStartTime;

        public IIdentifierListener25(long j, a aVar) {
            this.mStartTime = j;
            this.mOaidListener = aVar;
        }

        public void OnSupport(boolean z, IdSupplier idSupplier) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mStartTime;
            if (idSupplier != null) {
                String oaid = idSupplier.getOAID();
                if (TextUtils.isEmpty(oaid)) {
                    boolean unused = OADIDSDKHelper25.b = true;
                } else {
                    Log.d("KS_LOG", "OADIDSDKHelper25:oaid time=" + jCurrentTimeMillis + "--OAID:" + oaid);
                    a.b bVar = this.mOaidListener;
                    bVar.getClass();
                    a.a.a.f.a.a = oaid;
                    d.a(bVar.a, "ks_oaid", oaid);
                    a.a.a.f.a.a();
                }
            }
            boolean unused2 = OADIDSDKHelper25.a = false;
        }
    }

    public interface a {
    }

    public static boolean a() {
        try {
            new IIdentifierListener() { // from class: com.kwai.monitor.oaid.OADIDSDKHelper25.1
                public void OnSupport(boolean z, IdSupplier idSupplier) {
                }
            }.OnSupport(true, (IdSupplier) null);
            try {
                Class.forName("com.bun.miitmdid.core.MdidSdkHelper", false, OADIDSDKHelper25.class.getClassLoader());
                return true;
            } catch (Throwable unused) {
                Log.d("KS_LOG", "OADIDSDKHelper25:com.bun.miitmdid.core.MdidSdkHelper oaid sdk not find ");
                return false;
            }
        } catch (Throwable unused2) {
            Log.d("KS_LOG", "OADIDSDKHelper25:isSupport oaid sdk not find ");
            return false;
        }
    }
}
