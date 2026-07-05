package com.igexin.push.core.a.c;

import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a implements PushMessageInterface {
    private static boolean a(String str) {
        return com.igexin.push.core.e.i.getPackageManager().getPackageInfo(str, 0) != null;
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final boolean executeAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        String str;
        com.igexin.push.core.b.c cVar = (com.igexin.push.core.b.c) baseActionBean;
        String taskId = pushTaskBean.getTaskId();
        String messageId = pushTaskBean.getMessageId();
        if (a(cVar.a)) {
            if (cVar.b == null || cVar.b.equals("")) {
                return true;
            }
            com.igexin.push.core.a.b.d();
            str = cVar.b;
        } else {
            if (cVar.c == null || cVar.c.equals("")) {
                return true;
            }
            com.igexin.push.core.a.b.d();
            str = cVar.c;
        }
        com.igexin.push.core.a.b.a(taskId, messageId, str);
        return true;
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final BaseActionBean parseAction(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("type") || !jSONObject.has("actionid")) {
                return null;
            }
            com.igexin.push.core.b.c cVar = new com.igexin.push.core.b.c();
            cVar.setType(com.igexin.push.core.b.r);
            cVar.setActionId(jSONObject.getString("actionid"));
            if (!jSONObject.has("appstartupid")) {
                return null;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("appstartupid");
            if (!jSONObject2.has("android")) {
                return null;
            }
            cVar.a = jSONObject2.getString("android");
            if (!jSONObject.has("do_installed") && !jSONObject.has("do_uninstalled")) {
                return null;
            }
            if (jSONObject.has("do_installed")) {
                cVar.b = jSONObject.getString("do_installed");
            }
            if (jSONObject.has("do_uninstalled")) {
                cVar.c = jSONObject.getString("do_uninstalled");
            }
            return cVar;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        return PushMessageInterface.ActionPrepareState.success;
    }
}
