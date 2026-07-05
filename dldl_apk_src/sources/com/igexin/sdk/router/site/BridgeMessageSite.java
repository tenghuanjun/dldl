package com.igexin.sdk.router.site;

import android.text.TextUtils;
import com.igexin.b.a.c.a;
import com.igexin.base.api.ShipsManager;
import com.igexin.base.boatman.receive.IBoatResult;
import com.igexin.base.boatman.receive.Site;
import com.igexin.push.core.b;
import com.igexin.push.extension.mod.PushTaskBean;
import com.igexin.sdk.main.FeedbackImpl;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class BridgeMessageSite extends Site<JSONObject, JSONObject> {
    private static final String TAG = "BridgeMessageSite";

    @Override // com.igexin.base.boatman.receive.Site
    public String getTag() {
        return ShipsManager.TAG_FEEDBACK;
    }

    @Override // com.igexin.base.boatman.receive.Site
    public JSONObject onArrived(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        a.a("BridgeMessageSiteboatman_feedback |" + jSONObject.toString(), new Object[0]);
        if (jSONObject.has("active")) {
            try {
                String string = jSONObject.getString("active");
                if (b.at.equals(string)) {
                    String string2 = jSONObject.getString("doAction");
                    String string3 = jSONObject.getString("taskid");
                    String string4 = jSONObject.getString("messageid");
                    com.igexin.push.core.a.b.d();
                    com.igexin.push.core.a.b.a(string3, string4, string2);
                } else if (b.au.equals(string)) {
                    PushTaskBean pushTaskBean = new PushTaskBean();
                    pushTaskBean.parse(jSONObject);
                    if (jSONObject.has("actionid") && !TextUtils.isEmpty(jSONObject.getString("actionid"))) {
                        if (jSONObject.has("result")) {
                            FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, jSONObject.getString("actionid"), jSONObject.getString("result"));
                        } else {
                            FeedbackImpl.getInstance().feedbackMessageAction(pushTaskBean, jSONObject.getString("actionid"));
                        }
                    }
                }
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    @Override // com.igexin.base.boatman.receive.Site
    public void onArrived(JSONObject jSONObject, IBoatResult<JSONObject> iBoatResult) {
    }
}
