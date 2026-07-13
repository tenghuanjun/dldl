package a.a.a.c.m;

import com.lzy.okgo.cache.CacheEntity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: AliConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f91a;
    public String b;
    public String c;
    public String d;
    public List<C0002a> e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public int k;
    public String l;
    public String m;
    public String n;

    /* JADX INFO: renamed from: a.a.a.c.m.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AliConfig.java */
    public static class C0002a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f92a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
    }

    public void a(a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.f91a = jSONObject.optString(CacheEntity.KEY);
        if (JSONObject.NULL.toString().equals(aVar.f91a)) {
            aVar.f91a = "";
        }
        aVar.b = jSONObject.optString("getVersion");
        if (JSONObject.NULL.toString().equals(aVar.b)) {
            aVar.b = "";
        }
        aVar.c = jSONObject.optString("holderCallback");
        if (JSONObject.NULL.toString().equals(aVar.c)) {
            aVar.c = "";
        }
        aVar.d = jSONObject.optString("holderCallbackMethodName");
        if (JSONObject.NULL.toString().equals(aVar.d)) {
            aVar.d = "";
        }
        aVar.e = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("versionConfigList");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                C0002a c0002a = new C0002a();
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null) {
                    c0002a.f92a = jSONObjectOptJSONObject.optString("minVersion");
                    if (JSONObject.NULL.toString().equals(c0002a.f92a)) {
                        c0002a.f92a = "";
                    }
                    c0002a.b = jSONObjectOptJSONObject.optString("maxVersion");
                    if (JSONObject.NULL.toString().equals(c0002a.b)) {
                        c0002a.b = "";
                    }
                    c0002a.c = jSONObjectOptJSONObject.optString("holderName");
                    if (JSONObject.NULL.toString().equals(c0002a.c)) {
                        c0002a.c = "";
                    }
                    c0002a.d = jSONObjectOptJSONObject.optString("holderFieldName");
                    if (JSONObject.NULL.toString().equals(c0002a.d)) {
                        c0002a.d = "";
                    }
                    c0002a.e = jSONObjectOptJSONObject.optString("resultKey");
                    if (JSONObject.NULL.toString().equals(c0002a.e)) {
                        c0002a.e = "";
                    }
                    c0002a.f = jSONObjectOptJSONObject.optString("preparedKey");
                    if (JSONObject.NULL.toString().equals(c0002a.f)) {
                        c0002a.f = "";
                    }
                }
                aVar.e.add(c0002a);
            }
        }
        aVar.k = jSONObject.optInt("element", -1);
        aVar.l = jSONObject.optString("successStr1");
        aVar.m = jSONObject.optString("successStr2");
        aVar.n = jSONObject.optString("valuePre");
    }
}
