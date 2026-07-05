package com.igexin.push.core.a.c;

import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import com.igexin.push.core.e.e.AnonymousClass8;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d implements PushMessageInterface {
    public static final String a = "BasicAction";

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final boolean executeAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        long jCurrentTimeMillis = System.currentTimeMillis() + (((com.igexin.push.core.b.f) baseActionBean).a * 1000);
        com.igexin.push.core.e.e.a().a(true);
        com.igexin.push.core.e.e eVarA = com.igexin.push.core.e.e.a();
        if (com.igexin.push.core.e.V != jCurrentTimeMillis) {
            com.igexin.push.core.e.V = jCurrentTimeMillis;
            com.igexin.b.a.b.e.a().a((com.igexin.b.a.d.f) eVarA.new AnonymousClass8(), false, true);
        }
        return true;
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final BaseActionBean parseAction(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("do") || !jSONObject.has("actionid") || !jSONObject.has(MediationConstant.EXTRA_DURATION)) {
                return null;
            }
            com.igexin.push.core.b.f fVar = new com.igexin.push.core.b.f();
            fVar.setType(jSONObject.getString("type"));
            fVar.setActionId(jSONObject.getString("actionid"));
            fVar.setDoActionId(jSONObject.getString("do"));
            if (jSONObject.has(MediationConstant.EXTRA_DURATION)) {
                fVar.a = Long.valueOf(jSONObject.getString(MediationConstant.EXTRA_DURATION)).longValue();
            }
            return fVar;
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        return PushMessageInterface.ActionPrepareState.success;
    }
}
