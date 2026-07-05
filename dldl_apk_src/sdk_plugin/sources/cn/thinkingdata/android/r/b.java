package cn.thinkingdata.android.r;

import android.content.Context;
import cn.thinkingdata.android.utils.TDLog;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class b {
    public static Map<String, d> a;
    public static Map<String, d> b = new HashMap();

    public static synchronized void a(Context context) {
        a = g.a();
    }

    public static boolean a(c cVar) {
        d dVar = b.get(cVar.e());
        if (dVar == null) {
            dVar = a.get(cVar.e());
        }
        if (dVar != null) {
            cVar.a(dVar.b());
            cVar.a(dVar.a());
            cVar.a(dVar.c());
            return true;
        }
        TDLog.e("ThinkingAnalytics.TRouter", "未找到插件：" + cVar.e());
        return false;
    }
}
