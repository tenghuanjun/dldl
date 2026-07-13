package com.kwai.monitor.oaid;

import a.a.a.b.d;
import a.a.a.f.a;
import android.text.TextUtils;
import android.util.Log;
import com.bun.miitmdid.e;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;

/* JADX INFO: loaded from: classes3.dex */
public class OADIDSDKHelper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f491a = false;
    public static boolean b = false;

    public static class IIdentifierListenerImpl implements IIdentifierListener {
        public final a mOaidListener;
        public final long mStartTime;

        public IIdentifierListenerImpl(long j, a aVar) {
            this.mStartTime = j;
            this.mOaidListener = aVar;
        }

        @Override // com.bun.miitmdid.interfaces.IIdentifierListener
        public void onSupport(IdSupplier idSupplier) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mStartTime;
            if (idSupplier != null) {
                String oaid = idSupplier.getOAID();
                if (TextUtils.isEmpty(oaid)) {
                    boolean unused = OADIDSDKHelper.b = true;
                } else {
                    Log.d("KS_LOG", "OADIDSDKHelper:oaid time=" + jCurrentTimeMillis + "--OAID:" + oaid);
                    a.C0008a c0008a = (a.C0008a) this.mOaidListener;
                    c0008a.getClass();
                    a.a.a.f.a.f116a = oaid;
                    d.a(c0008a.f117a, "ks_oaid", oaid);
                    a.a.a.f.a.a();
                }
            }
            boolean unused2 = OADIDSDKHelper.f491a = false;
        }
    }

    public interface a {
    }

    public static boolean a() {
        try {
            new IIdentifierListener() { // from class: com.kwai.monitor.oaid.OADIDSDKHelper.1
                @Override // com.bun.miitmdid.interfaces.IIdentifierListener
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
