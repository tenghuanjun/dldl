package com.igexin.sdk.router.site;

import com.igexin.base.api.ShipsManager;
import com.igexin.base.boatman.receive.IBoatResult;
import com.igexin.base.boatman.receive.Site;
import com.igexin.push.core.e;
import com.igexin.push.f.o;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class InitSite extends Site<JSONObject, JSONObject> {
    @Override // com.igexin.base.boatman.receive.Site
    public String getTag() {
        return ShipsManager.TAG_EXTENSION_INIT;
    }

    @Override // com.igexin.base.boatman.receive.Site
    public JSONObject onArrived(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            jSONObject.put("cid", e.x);
            jSONObject.put("deviceId", e.F);
            jSONObject.put("userPushService", o.b(e.i, o.b, ""));
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    @Override // com.igexin.base.boatman.receive.Site
    public void onArrived(JSONObject jSONObject, IBoatResult<JSONObject> iBoatResult) {
        if (jSONObject == null) {
            return;
        }
        try {
            jSONObject.put("cid", e.x);
            jSONObject.put("deviceId", e.F);
            jSONObject.put("userPushService", o.b(e.i, o.a, ""));
            iBoatResult.onResult(jSONObject);
        } catch (JSONException unused) {
        }
    }
}
