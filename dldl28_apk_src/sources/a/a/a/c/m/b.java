package a.a.a.c.m;

import com.tencent.connect.common.Constants;
import org.json.JSONObject;

/* JADX INFO: compiled from: DetectConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f93a;
    public double b = 1.0d;
    public String c;
    public String d;
    public String e;
    public String f;

    public void a(b bVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        bVar.f93a = jSONObject.optLong("funcSwitch");
        bVar.b = jSONObject.optDouble("sampleRate");
        bVar.c = jSONObject.optString("wwConfigStr");
        if (JSONObject.NULL.toString().equals(bVar.c)) {
            bVar.c = "";
        }
        bVar.d = jSONObject.optString("aConfigStr");
        if (JSONObject.NULL.toString().equals(bVar.d)) {
            bVar.d = "";
        }
        bVar.e = jSONObject.optString("monitorSdkClass");
        if (JSONObject.NULL.toString().equals(bVar.e)) {
            bVar.e = "";
        }
        bVar.f = jSONObject.optString(Constants.PARAM_PLATFORM_ID);
        if (JSONObject.NULL.toString().equals(bVar.f)) {
            bVar.f = "";
        }
    }

    public String toString() {
        return "DetectConfig{funcSwitch=" + this.f93a + ", sampleRate=" + this.b + ", wxConfigStr='" + this.c + "', aliConfigStr='" + this.d + "', monitorSdkClass='" + this.e + "', pf='" + this.f + "'}";
    }
}
