package a.a.a.d;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ResponseResult.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f101a;
    public String b;
    public b c;

    /* JADX INFO: compiled from: ResponseResult.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f102a;
    }

    /* JADX INFO: compiled from: ResponseResult.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f103a;
        public boolean b;
        public a c = new a();
    }

    public void a(JSONObject jSONObject) {
        this.f101a = jSONObject.optInt("result");
        this.b = jSONObject.optString("error_msg");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
        if (jSONObjectOptJSONObject == null) {
            String strOptString = jSONObject.optString("data");
            if (!TextUtils.isEmpty(strOptString)) {
                try {
                    jSONObjectOptJSONObject = new JSONObject(strOptString);
                } catch (JSONException unused) {
                }
            }
        }
        if (jSONObjectOptJSONObject != null) {
            b bVar = new b();
            this.c = bVar;
            bVar.f103a = jSONObjectOptJSONObject.optString("globalId");
            this.c.b = jSONObjectOptJSONObject.optBoolean("checkResult");
            a aVar = this.c.c;
            JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("extMap");
            aVar.getClass();
            if (jSONObjectOptJSONObject2 != null) {
                aVar.f102a = jSONObjectOptJSONObject2.optBoolean("uploadOriginOaid", false);
            }
        }
    }
}
