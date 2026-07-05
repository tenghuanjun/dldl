package cn.thinkingdata.android;

import com.taptap.sdk.db.constant.Common;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class a {
    String a;
    private final cn.thinkingdata.android.utils.f b;
    final cn.thinkingdata.android.utils.m c;
    private String d;
    private String e;
    private final JSONObject f;
    private Map<String, String> g;
    boolean h = true;
    final String i;

    a(ThinkingAnalyticsSDK thinkingAnalyticsSDK, cn.thinkingdata.android.utils.m mVar, JSONObject jSONObject, cn.thinkingdata.android.utils.f fVar) {
        this.c = mVar;
        this.f = jSONObject;
        this.b = fVar;
        this.i = thinkingAnalyticsSDK.getToken();
        this.d = thinkingAnalyticsSDK.getDistinctId();
        this.e = thinkingAnalyticsSDK.getLoginId();
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("#type", this.c.a());
            jSONObject.put("#time", this.b.b());
            jSONObject.put("#distinct_id", this.d);
            if (this.e != null) {
                jSONObject.put("#account_id", this.e);
            }
            if (this.g != null) {
                for (Map.Entry<String, String> entry : this.g.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            }
            if (this.c.b()) {
                jSONObject.put("#event_name", this.a);
                Double dA = this.b.a();
                if (dA != null && !TDPresetProperties.disableList.contains("#zone_offset")) {
                    this.f.put("#zone_offset", dA);
                }
            }
            jSONObject.put(Common.Predefined.PROPERTIES, this.f);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    void a(Map<String, String> map) {
        this.g = map;
    }

    void b() {
        this.h = false;
    }
}
