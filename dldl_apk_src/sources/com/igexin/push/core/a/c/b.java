package com.igexin.push.core.a.c;

import com.getui.gtc.api.GtcManager;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b implements PushMessageInterface {
    private static final String a = "CleanExtAction";

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final boolean executeAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        if (pushTaskBean != null && baseActionBean != null) {
            com.igexin.push.core.b.e eVar = (com.igexin.push.core.b.e) baseActionBean;
            if (eVar.a != null && eVar.a.length > 0) {
                Arrays.toString(eVar.a);
                GtcManager.getInstance().removeExt(com.igexin.push.core.b.h, eVar.a);
            }
        }
        if ("".equals(baseActionBean.getDoActionId())) {
            return true;
        }
        com.igexin.push.core.a.b.d();
        com.igexin.push.core.a.b.a(pushTaskBean.getTaskId(), pushTaskBean.getMessageId(), baseActionBean.getDoActionId());
        return true;
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final BaseActionBean parseAction(JSONObject jSONObject) {
        if (!jSONObject.has("ids")) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(jSONObject.getString("ids"));
            if (jSONArray.length() <= 0) {
                return null;
            }
            int[] iArr = new int[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                iArr[i] = jSONArray.getInt(i);
            }
            com.igexin.push.core.b.e eVar = new com.igexin.push.core.b.e();
            eVar.setType(com.igexin.push.core.b.t);
            eVar.a = iArr;
            eVar.setActionId(jSONObject.getString("actionid"));
            eVar.setDoActionId(jSONObject.getString("do"));
            return eVar;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        return PushMessageInterface.ActionPrepareState.success;
    }
}
