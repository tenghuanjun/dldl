package com.igexin.push.core.a.b;

import android.content.Intent;
import android.os.Bundle;
import com.igexin.push.core.m;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.message.FeedbackCmdMessage;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class i extends a {
    @Override // com.igexin.push.core.a.b.a
    public final boolean a(Object obj, JSONObject jSONObject) {
        long j;
        try {
            if (!jSONObject.has("action") || !jSONObject.getString("action").equals("sendmessage_feedback")) {
                return true;
            }
            String string = jSONObject.getString("appid");
            String string2 = jSONObject.getString("taskid");
            String string3 = jSONObject.getString("actionid");
            String string4 = jSONObject.getString("result");
            long j2 = jSONObject.getLong("timestamp");
            com.igexin.b.a.c.a.a("SendMessageFeedbackAction|appid:" + string + "|taskid:" + string2 + "|actionid:" + string3, new Object[0]);
            m mVarA = m.a();
            if (com.igexin.push.core.e.a == null || !com.igexin.push.core.e.a.equals(string)) {
                j = j2;
            } else {
                Bundle bundle = new Bundle();
                bundle.putInt("action", 10010);
                j = j2;
                bundle.putSerializable(PushConsts.KEY_CMD_MSG, new FeedbackCmdMessage(string2, string3, string4, j2, 10006));
                mVarA.a(bundle);
            }
            Intent intentD = m.d();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("action", 10006);
            bundle2.putString("appid", string);
            bundle2.putString("taskid", string2);
            bundle2.putString("actionid", string3);
            bundle2.putString("result", string4);
            bundle2.putLong("timestamp", j);
            intentD.putExtras(bundle2);
            com.igexin.push.core.e.i.sendBroadcast(intentD);
            return true;
        } catch (Exception unused) {
            return true;
        }
    }
}
