package cn.thinkingdata.android.q;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class a {
    protected cn.thinkingdata.android.i a;
    protected Future<SharedPreferences> b;

    public a(Context context, String str) {
        cn.thinkingdata.android.i iVar = new cn.thinkingdata.android.i();
        this.a = iVar;
        this.b = iVar.a(context, str);
        a();
    }

    public <T> T a(g gVar) {
        h<T> hVarB = b(gVar);
        if (hVarB != null) {
            return hVarB.b();
        }
        return null;
    }

    protected abstract void a();

    public <T> void a(g gVar, T t) {
        h<T> hVarB = b(gVar);
        if (hVarB != null) {
            hVarB.a(t);
        }
    }

    protected abstract <T> h<T> b(g gVar);
}
