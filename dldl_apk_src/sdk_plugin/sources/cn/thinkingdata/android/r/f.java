package cn.thinkingdata.android.r;

import android.content.Context;
import cn.thinkingdata.android.utils.TDLog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class f {
    private static volatile f a;
    private static volatile boolean b;

    private f() {
    }

    public static f a() {
        if (!b) {
            throw new a("TRouter::Init::Invoke init(context) first!");
        }
        if (a == null) {
            synchronized (f.class) {
                if (a == null) {
                    a = new f();
                }
            }
        }
        return a;
    }

    public static void a(Context context) {
        if (b) {
            return;
        }
        TDLog.i("ThinkingAnalytics.TRouter", "TRouter init start.");
        b = h.a(context.getApplicationContext());
        TDLog.i("ThinkingAnalytics.TRouter", "ARouter init over.");
    }

    public c a(String str) {
        return h.a().a(str);
    }

    public Object a(Context context, c cVar) {
        return h.a().a(context, cVar);
    }
}
