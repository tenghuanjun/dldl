package com.bun.miitmdid;

import android.content.Context;
import com.bun.miitmdid.interfaces.IPermissionCallbackListener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class p0 {
    public static p0 a;
    public IPermissionCallbackListener b;

    public static native synchronized p0 a();

    public native void a(Context context, IPermissionCallbackListener iPermissionCallbackListener);

    public native IPermissionCallbackListener b();
}
