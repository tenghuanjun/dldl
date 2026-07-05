package cn.thinkingdata.android.r;

import android.text.TextUtils;
import cn.thinkingdata.android.utils.TDLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class g {
    private static final String[] a = {"ThirdParty"};

    public static Map<String, d> a() {
        HashMap map = new HashMap();
        for (String str : a) {
            try {
                Map map2 = (Map) Class.forName("cn.thinkingdata.module.routes." + str + "ModuleRouter").getDeclaredMethod("getRouterMap", new Class[0]).invoke(null, new Object[0]);
                if (map2 != null) {
                    for (String str2 : map2.keySet()) {
                        String str3 = (String) map2.get(str2);
                        if (str3 != null && !TextUtils.isEmpty(str3)) {
                            JSONObject jSONObject = new JSONObject(str3);
                            map.put(str2, d.a(e.a(jSONObject.optInt("type")), str2, jSONObject.optString("name"), jSONObject.optBoolean("needCache")));
                        }
                    }
                }
            } catch (ClassNotFoundException unused) {
                TDLog.d("ThinkingAnalytics.TRouterMap", "未找到路由表");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
