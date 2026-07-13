package a.a.a.c;

import a.a.a.b.e;
import android.content.Context;
import android.os.Debug;
import android.text.TextUtils;
import android.util.Log;
import com.vivo.identifier.IdentifierConstant;

/* JADX INFO: compiled from: SafeController.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile boolean f86a = false;

    public static boolean a(String str) {
        boolean zA = a.a.a.b.d.a(a.a.a.b.d.f70a, 32L);
        if (f86a) {
            d.a("sfc", "has debug return in inject " + str);
            return true;
        }
        if (Debug.isDebuggerConnected()) {
            f86a = true;
            d.a("sfc", "has debug return in inject " + str);
            return true;
        }
        Context context = e.c.INSTANCE.f74a.c;
        if (!zA) {
            try {
                String property = System.getProperty("http.proxyHost");
                String property2 = System.getProperty("http.proxyPort");
                if (property2 == null) {
                    property2 = IdentifierConstant.OAID_STATE_DEFAULT;
                }
                int i = Integer.parseInt(property2);
                if (!TextUtils.isEmpty(property) && i != -1) {
                    d.d("sfc", str + " return by net");
                    return true;
                }
            } catch (Throwable th) {
                d.d("Utils", Log.getStackTraceString(th));
            }
        }
        return false;
    }
}
