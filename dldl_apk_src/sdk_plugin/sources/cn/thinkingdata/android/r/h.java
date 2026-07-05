package cn.thinkingdata.android.r;

import android.content.Context;
import android.text.TextUtils;
import cn.thinkingdata.android.router.plugin.IPlugin;
import cn.thinkingdata.android.router.plugin.MethodCall;
import cn.thinkingdata.android.router.provider.IProvider;
import cn.thinkingdata.android.utils.TDLog;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
final class h {
    private static volatile h b;
    private static volatile boolean c;
    private static Context d;
    private Map<String, Object> a = new HashMap();

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[e.values().length];
            a = iArr;
            try {
                iArr[e.PROVIDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[e.PLUGIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private h() {
    }

    protected static h a() {
        if (!c) {
            throw new cn.thinkingdata.android.r.a("TRouterCore::Init::Invoke init(context) first!");
        }
        if (b == null) {
            synchronized (h.class) {
                if (b == null) {
                    b = new h();
                }
            }
        }
        return b;
    }

    protected static synchronized boolean a(Context context) {
        d = context;
        b.a(context);
        TDLog.i("ThinkingAnalytics.TRouter", "TRouter init success!");
        c = true;
        return true;
    }

    protected c a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return new c(str);
        }
        TDLog.e("ThinkingAnalytics.TRouter", "TRouter build Parameter is invalid!");
        return new c("");
    }

    protected Object a(Context context, c cVar) {
        IPlugin iPlugin;
        if (!b.a(cVar)) {
            return null;
        }
        int i = a.a[cVar.b().ordinal()];
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i == 1) {
            if (cVar.c() && this.a.get(cVar.a()) != null) {
                return (IProvider) this.a.get(cVar.a());
            }
            IProvider iProvider = (IProvider) Class.forName(cVar.a()).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (cVar.c()) {
                this.a.put(cVar.a(), iProvider);
            }
            return iProvider;
        }
        if (i == 2) {
            MethodCall methodCall = new MethodCall();
            methodCall.method = cVar.d();
            methodCall.arguments = cVar.f;
            if (cVar.c() && this.a.get(cVar.a()) != null && (iPlugin = (IPlugin) this.a.get(cVar.a())) != null) {
                iPlugin.onMethodCall(methodCall);
                return null;
            }
            IPlugin iPlugin2 = (IPlugin) Class.forName(cVar.a()).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (cVar.c()) {
                this.a.put(cVar.a(), iPlugin2);
            }
            iPlugin2.onMethodCall(methodCall);
        }
        return null;
    }
}
