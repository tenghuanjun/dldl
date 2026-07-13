package a.a.a.e;

import a.a.a.b.e;
import android.text.TextUtils;
import com.volcengine.common.contant.CommonConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: HttpManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ String f114a;
    public final /* synthetic */ d b;

    public b(a.a.a.e.a aVar, String str, d dVar) {
        this.f114a = str;
        this.b = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        JSONObject jSONObject = new JSONObject();
        a.a.a.b.d.a(jSONObject, "sdkVersion", -1.0d);
        a.a.a.b.d.a(jSONObject, "version", "1.0.15");
        a.a.a.b.d.a(jSONObject, CommonConstants.key_appId, e.c.INSTANCE.f74a.d);
        a.a.a.g.b.c("HttpManager", "querySwitchMessage:" + jSONObject);
        new c().a(this.f114a, jSONObject.toString(), new a());
    }

    /* JADX INFO: compiled from: HttpManager.java */
    public class a implements d {
        public a() {
        }

        @Override // a.a.a.e.d
        public void a(String str) {
            if (TextUtils.isEmpty(str)) {
                b.this.b.a(new a.a.a.d.b(-9997, "jsonResult is null or empty"));
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.optInt("result") == 1) {
                    b.this.b.a(jSONObject.optString("data"));
                }
            } catch (JSONException e) {
                a.a.a.g.b.c("HttpManager", "querySwitchMessage parse error:" + e.getMessage());
                e.printStackTrace();
                b.this.b.a(new a.a.a.d.b(-9998, e.getMessage()));
            }
        }

        @Override // a.a.a.e.d
        public void a(a.a.a.d.b bVar) {
            a.a.a.g.b.a("HttpManager", "querySwitchMessage error:" + bVar);
            b.this.b.a(new a.a.a.d.b(-9999, "querySwitchMessage error:" + bVar));
        }
    }
}
