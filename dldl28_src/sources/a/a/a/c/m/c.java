package a.a.a.c.m;

import com.lzy.okgo.cache.CacheEntity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: WxConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f94a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public int m;
    public int n;
    public int o;
    public int p;
    public List<a> q;
    public a r;

    /* JADX INFO: compiled from: WxConfig.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f95a;
        public int b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;

        public void a(a aVar, JSONObject jSONObject) {
            if (jSONObject == null) {
                return;
            }
            aVar.f95a = jSONObject.optInt("minVersion");
            aVar.b = jSONObject.optInt("maxVersion");
            aVar.c = jSONObject.optString("holderName");
            if (JSONObject.NULL.toString().equals(aVar.c)) {
                aVar.c = "";
            }
            aVar.d = jSONObject.optString("holderFiledName");
            if (JSONObject.NULL.toString().equals(aVar.d)) {
                aVar.d = "";
            }
            aVar.e = jSONObject.optString("outputCallback");
            if (JSONObject.NULL.toString().equals(aVar.e)) {
                aVar.e = "";
            }
            aVar.f = jSONObject.optString("handleIntentKey");
            if (JSONObject.NULL.toString().equals(aVar.f)) {
                aVar.f = "";
            }
            aVar.g = jSONObject.optString("sendReqKey");
            if (JSONObject.NULL.toString().equals(aVar.g)) {
                aVar.g = "";
            }
        }
    }

    public void a(c cVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        cVar.f94a = jSONObject.optString(CacheEntity.KEY);
        if (JSONObject.NULL.toString().equals(cVar.f94a)) {
            cVar.f94a = "";
        }
        cVar.b = jSONObject.optString("payKey");
        if (JSONObject.NULL.toString().equals(cVar.b)) {
            cVar.b = "";
        }
        cVar.c = jSONObject.optString("apiImpl");
        if (JSONObject.NULL.toString().equals(cVar.c)) {
            cVar.c = "";
        }
        cVar.d = jSONObject.optString("api");
        if (JSONObject.NULL.toString().equals(cVar.d)) {
            cVar.d = "";
        }
        cVar.e = jSONObject.optString("methodHandle");
        if (JSONObject.NULL.toString().equals(cVar.e)) {
            cVar.e = "";
        }
        cVar.f = jSONObject.optString("factoryName");
        if (JSONObject.NULL.toString().equals(cVar.f)) {
            cVar.f = "";
        }
        cVar.g = jSONObject.optString("methodCreate");
        if (JSONObject.NULL.toString().equals(cVar.g)) {
            cVar.g = "";
        }
        cVar.h = jSONObject.optString("eventHandler");
        if (JSONObject.NULL.toString().equals(cVar.h)) {
            cVar.h = "";
        }
        cVar.i = jSONObject.optString("methodResp");
        if (JSONObject.NULL.toString().equals(cVar.i)) {
            cVar.i = "";
        }
        cVar.j = jSONObject.optString("codeKey");
        if (JSONObject.NULL.toString().equals(cVar.j)) {
            cVar.j = "";
        }
        cVar.k = jSONObject.optString("msgKey");
        if (JSONObject.NULL.toString().equals(cVar.k)) {
            cVar.k = "";
        }
        cVar.l = jSONObject.optString("versionMethodName");
        if (JSONObject.NULL.toString().equals(cVar.l)) {
            cVar.l = "";
        }
        cVar.n = jSONObject.optInt("element", -1);
        cVar.o = jSONObject.optInt("supportVersionMin");
        cVar.p = jSONObject.optInt("supportVersionMax");
        cVar.q = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("versionConfigList");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                a aVar = new a();
                aVar.a(aVar, jSONArrayOptJSONArray.optJSONObject(i));
                cVar.q.add(aVar);
            }
        }
        a aVar2 = new a();
        cVar.r = aVar2;
        aVar2.a(aVar2, jSONObject.optJSONObject("defaultVersionConfig"));
    }
}
