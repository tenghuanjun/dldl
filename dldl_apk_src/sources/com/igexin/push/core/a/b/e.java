package com.igexin.push.core.a.b;

import com.igexin.assist.action.MessageManger;
import com.igexin.push.c.c.n;
import com.igexin.push.core.o;
import com.igexin.sdk.main.FeedbackImpl;
import com.tencent.connect.common.Constants;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class e extends a {
    private static final String a = "PushMessageAction";

    @Override // com.igexin.push.core.a.b.a
    public final boolean a(Object obj, JSONObject jSONObject) {
        FeedbackImpl feedbackImpl;
        String str;
        try {
            n nVar = (n) obj;
            if (jSONObject.has("action") && jSONObject.getString("action").equals(com.igexin.push.core.b.A)) {
                byte[] bArr = null;
                if (nVar.g instanceof byte[]) {
                    try {
                        new String((byte[]) nVar.g, "UTF-8");
                    } catch (Exception e) {
                        com.igexin.b.a.c.a.a(a + e.toString(), new Object[0]);
                    }
                    bArr = (byte[]) nVar.g;
                }
                String string = jSONObject.getString("taskid");
                com.igexin.b.a.c.a.a("getui receive message : %s", jSONObject);
                if (bArr == null || !com.igexin.assist.sdk.a.a().b()) {
                    o.a().a(jSONObject, bArr, true);
                } else {
                    com.igexin.push.core.e.c cVar = new com.igexin.push.core.e.c(com.igexin.push.core.e.i);
                    if (cVar.a(string)) {
                        feedbackImpl = FeedbackImpl.getInstance();
                        str = "1" + MessageManger.getInstance().getBrandCode();
                    } else {
                        cVar.b(string);
                        o.a().a(jSONObject, bArr, true);
                        feedbackImpl = FeedbackImpl.getInstance();
                        str = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
                    }
                    feedbackImpl.feedbackMultiBrandMessageAction(jSONObject, str);
                }
            }
        } catch (Exception e2) {
            com.igexin.b.a.c.a.a("PushMessageAction|" + e2.toString(), new Object[0]);
        }
        return true;
    }
}
