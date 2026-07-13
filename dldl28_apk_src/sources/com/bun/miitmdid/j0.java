package com.bun.miitmdid;

import android.content.Context;
import com.bun.lib.MsaIdInterface;

/* JADX INFO: loaded from: classes2.dex */
public class j0 extends m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f159a;
    public String b;
    public g0 c;

    public class a implements h0 {
        public a() {
        }

        @Override // com.bun.miitmdid.h0
        public native void a(MsaIdInterface msaIdInterface);
    }

    public j0(Context context) {
        l0.c("ZteProvider", "ZteProvider(Context)");
        this.f159a = context;
        this.b = context.getPackageName();
        try {
            if (context.getPackageManager().getPackageInfo("com.mdid.msa", 0) == null) {
                l0.d("ZteProvider", "Constructor: getPackageInfo is null");
                throw new NullPointerException("Constructor: getPackageInfo is null");
            }
        } catch (Exception unused) {
            l0.d("ZteProvider", "Constructor: MsaService not found");
        }
        try {
            g0.a(this.f159a, this.b);
            l0.c("ZteProvider", "Constructor: MsaService start success");
        } catch (Exception e) {
            l0.b("ZteProvider", "Constructor: MsaService start Exception: " + e.getMessage());
        }
    }

    @Override // com.bun.miitmdid.interfaces.IIdProvider
    public void doStart() {
        l0.c("ZteProvider", "doStart()");
        try {
            this.f159a = checkContext(this.f159a);
            doAsyncCallBefore();
            g0 g0Var = new g0(this.f159a, new a());
            this.c = g0Var;
            g0Var.a(this.b);
            l0.c("ZteProvider", "doStart: BindService success");
            doAsyncCallAfter();
        } catch (Exception e) {
            l0.d("ZteProvider", "doStart: Exception: " + e.getMessage());
            cleanCache();
            onSupportCache();
        }
    }

    @Override // com.bun.miitmdid.interfaces.IIdProvider
    public void shutDown() {
        g0 g0Var = this.c;
        if (g0Var != null) {
            g0Var.e();
        }
    }
}
