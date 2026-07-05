package com.bun.miitmdid;

import android.content.Context;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IPermissionCallbackListener;
import com.bun.miitmdid.interfaces.IdSupplier;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class e {
    public static boolean a;
    public static boolean b;
    public static AtomicLong c = new AtomicLong(OAIDHelper.TIMEOUT);
    public static boolean d = true;
    public static boolean e = true;
    public static boolean f = true;

    public e(boolean z, long j) {
        AtomicLong atomicLong;
        m0.a(z);
        if (j <= 0) {
            atomicLong = c;
            j = OAIDHelper.TIMEOUT;
        } else {
            atomicLong = c;
        }
        atomicLong.set(j);
    }

    public e(boolean z, long j, boolean z2, boolean z3, boolean z4) {
        AtomicLong atomicLong;
        m0.a(z);
        if (j <= 0) {
            atomicLong = c;
            j = OAIDHelper.TIMEOUT;
        } else {
            atomicLong = c;
        }
        atomicLong.set(j);
        a(z2, z3, z4);
    }

    public static native String a();

    public static native void a(Context context, IPermissionCallbackListener iPermissionCallbackListener);

    public static native boolean a(Context context, String str);

    public static native int b();

    public final native int a(int i, IdSupplier idSupplier);

    public native int a(Context context, IIdentifierListener iIdentifierListener);

    public native void a(boolean z, boolean z2, boolean z3);
}
