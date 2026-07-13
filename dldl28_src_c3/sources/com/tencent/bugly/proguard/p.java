package com.tencent.bugly.proguard;

import android.content.Context;
import android.util.Log;
import com.tencent.bugly.BuglyStrategy;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class p {
    public static boolean a = true;
    public static List<o> b = new ArrayList();
    public static boolean c;
    private static w d;
    private static boolean e;

    private static boolean a(aa aaVar) {
        List<String> list = aaVar.v;
        aaVar.getClass();
        return list != null && list.contains("bugly");
    }

    public static synchronized void a(Context context) {
        a(context, null);
    }

    public static synchronized void a(Context context, BuglyStrategy buglyStrategy) {
        if (e) {
            al.d("[init] initial Multi-times, ignore this.", new Object[0]);
            return;
        }
        if (context == null) {
            Log.w(al.b, "[init] context of init() is null, check it.");
            return;
        }
        aa aaVarA = aa.a(context);
        if (a(aaVarA)) {
            a = false;
            return;
        }
        String strE = aaVarA.e();
        if (strE == null) {
            Log.e(al.b, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
        } else {
            a(context, strE, aaVarA.D, buglyStrategy);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x0224 A[Catch: all -> 0x0242, TryCatch #2 {, blocks: (B:4:0x0009, B:6:0x000e, B:10:0x0019, B:14:0x0024, B:18:0x002e, B:20:0x0032, B:21:0x006e, B:23:0x00af, B:26:0x00b3, B:28:0x00c5, B:30:0x00d3, B:32:0x00d9, B:33:0x00ef, B:34:0x00fe, B:36:0x0106, B:38:0x0110, B:40:0x0116, B:41:0x012c, B:47:0x0154, B:53:0x0168, B:55:0x0172, B:57:0x017a, B:58:0x0190, B:59:0x019f, B:61:0x01a5, B:63:0x01ad, B:64:0x01c3, B:65:0x01cf, B:67:0x01d5, B:68:0x01e1, B:42:0x013a, B:44:0x0143, B:46:0x014d, B:50:0x0161, B:52:0x0165, B:70:0x01ee, B:80:0x021c, B:81:0x021f, B:83:0x0224, B:85:0x022b, B:77:0x0213, B:79:0x0219, B:72:0x01f6, B:74:0x0206), top: B:95:0x0009, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01f6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized void a(android.content.Context r16, java.lang.String r17, boolean r18, com.tencent.bugly.BuglyStrategy r19) {
        /*
            Method dump skipped, instruction units count: 581
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.p.a(android.content.Context, java.lang.String, boolean, com.tencent.bugly.BuglyStrategy):void");
    }

    public static synchronized void a(o oVar) {
        if (!b.contains(oVar)) {
            b.add(oVar);
        }
    }
}
