package com.bun.miitmdid;

import android.content.Context;
import com.bun.miitmdid.interfaces.IIdConfig;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class b implements IIdConfig {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f145a = "";
    public a b = new a();

    public class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public b0 f146a;

        public a() {
        }
    }

    public static native b a(Context context);

    public static boolean a(b bVar, JSONObject jSONObject) {
        if (jSONObject == null || bVar == null) {
            return false;
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("vivo");
        b0 b0Var = new b0();
        if (jSONObjectOptJSONObject != null) {
            String strOptString = jSONObjectOptJSONObject.optString("appid");
            b0Var.f147a = strOptString;
            f145a = strOptString;
            bVar.b.f146a = b0Var;
        }
        return bVar.b.f146a != null;
    }

    @Override // com.bun.miitmdid.interfaces.IIdConfig
    public native String getVivoAppID();
}
