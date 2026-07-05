package com.tencent.bugly;

import android.content.Context;
import android.util.Log;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.x;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class b {
    public static boolean a = true;
    public static List<a> b = new ArrayList();
    public static boolean c;
    private static p d;
    private static boolean e;

    private static boolean a(com.tencent.bugly.crashreport.common.info.a aVar) {
        List<String> list = aVar.o;
        aVar.getClass();
        return list != null && list.contains("bugly");
    }

    public static synchronized void a(Context context) {
        a(context, null);
    }

    public static synchronized void a(Context context, BuglyStrategy buglyStrategy) {
        if (e) {
            x.d("[init] initial Multi-times, ignore this.", new Object[0]);
            return;
        }
        if (context == null) {
            Log.w(x.a, "[init] context of init() is null, check it.");
            return;
        }
        com.tencent.bugly.crashreport.common.info.a aVarA = com.tencent.bugly.crashreport.common.info.a.a(context);
        if (a(aVarA)) {
            a = false;
            return;
        }
        String strF = aVarA.f();
        if (strF == null) {
            Log.e(x.a, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
        } else {
            a(context, strF, aVarA.u, buglyStrategy);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x021c A[Catch: all -> 0x022f, TryCatch #2 {, blocks: (B:4:0x0009, B:6:0x000e, B:10:0x0019, B:14:0x0024, B:18:0x002e, B:20:0x0032, B:21:0x006e, B:23:0x00bb, B:26:0x00bf, B:28:0x00cd, B:30:0x00db, B:32:0x00e1, B:33:0x00f7, B:34:0x0106, B:36:0x010c, B:38:0x0116, B:40:0x011c, B:41:0x0132, B:47:0x0162, B:53:0x0176, B:55:0x0180, B:57:0x0186, B:58:0x019c, B:59:0x01ab, B:61:0x01b1, B:63:0x01b7, B:64:0x01cd, B:65:0x01d9, B:42:0x0146, B:44:0x0151, B:46:0x015b, B:50:0x016f, B:52:0x0173, B:66:0x01e5, B:67:0x01e9, B:77:0x0217, B:79:0x021c, B:81:0x0223, B:74:0x020e, B:76:0x0214, B:69:0x01f1, B:71:0x0201), top: B:91:0x0009, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized void a(android.content.Context r20, java.lang.String r21, boolean r22, com.tencent.bugly.BuglyStrategy r23) {
        /*
            Method dump skipped, instruction units count: 562
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.b.a(android.content.Context, java.lang.String, boolean, com.tencent.bugly.BuglyStrategy):void");
    }

    public static synchronized void a(a aVar) {
        if (!b.contains(aVar)) {
            b.add(aVar);
        }
    }
}
