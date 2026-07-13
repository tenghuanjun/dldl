package com.cmic.sso.sdk.d;

import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class c extends b {
    public static ArrayList<Throwable> b = new ArrayList<>();
    private JSONObject c;
    private JSONArray d;

    @Override // com.cmic.sso.sdk.d.b
    public void a(JSONArray jSONArray) {
        this.d = jSONArray;
    }

    @Override // com.cmic.sso.sdk.d.b, com.mobile.auth.j.g
    public JSONObject b() {
        JSONObject jSONObjectB = super.b();
        try {
            jSONObjectB.put(NotificationCompat.CATEGORY_EVENT, this.c);
            jSONObjectB.put("exceptionStackTrace", this.d);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObjectB;
    }
}
