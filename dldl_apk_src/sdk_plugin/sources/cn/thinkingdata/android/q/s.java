package cn.thinkingdata.android.q;

import android.content.SharedPreferences;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class s extends h<JSONObject> {
    public s(Future<SharedPreferences> future) {
        super(future, "superProperties");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.thinkingdata.android.q.h
    public JSONObject a() {
        return new JSONObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // cn.thinkingdata.android.q.h
    public void a(SharedPreferences.Editor editor, JSONObject jSONObject) {
        editor.putString(this.b, jSONObject == null ? null : jSONObject.toString());
        editor.apply();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, org.json.JSONObject] */
    @Override // cn.thinkingdata.android.q.h
    void a(SharedPreferences sharedPreferences) {
        String string = sharedPreferences.getString(this.b, null);
        if (string == null) {
            a(a());
            return;
        }
        try {
            this.a = new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
