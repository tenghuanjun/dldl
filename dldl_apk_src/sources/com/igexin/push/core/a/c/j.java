package com.igexin.push.core.a.c;

import android.content.Intent;
import android.text.TextUtils;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class j implements PushMessageInterface {
    private static final String a = com.igexin.push.config.c.a;

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final boolean executeAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        String id;
        String messageId;
        String doActionId;
        com.igexin.push.core.b.m mVar = (com.igexin.push.core.b.m) baseActionBean;
        try {
            Intent uri = Intent.parseUri(mVar.b, 0);
            uri.setPackage(com.igexin.push.core.e.i.getPackageName());
            uri.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            if (com.igexin.push.f.c.b(uri, com.igexin.push.core.e.i)) {
                com.igexin.push.core.e.i.startActivity(uri);
                com.igexin.push.core.a.b.d();
                id = pushTaskBean.getTaskId();
                messageId = pushTaskBean.getMessageId();
                doActionId = mVar.getDoActionId();
            } else {
                com.igexin.b.a.c.a.a(a + "|execute failed, activity not exist", new Object[0]);
                com.igexin.push.core.a.b.d();
                id = pushTaskBean.getId();
                messageId = pushTaskBean.getMessageId();
                doActionId = mVar.a;
            }
            com.igexin.push.core.a.b.a(id, messageId, doActionId);
            return true;
        } catch (Throwable th) {
            th.getMessage();
            com.igexin.b.a.c.a.a(a + "|execute exception = " + th.getMessage(), new Object[0]);
            com.igexin.push.core.a.b.d();
            com.igexin.push.core.a.b.a(pushTaskBean.getId(), pushTaskBean.getMessageId(), mVar.a);
            return true;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final BaseActionBean parseAction(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("do") || !jSONObject.has("actionid") || !jSONObject.has("type") || !jSONObject.has("uri") || !jSONObject.has("do_failed")) {
                return null;
            }
            String strOptString = jSONObject.optString("uri");
            if (TextUtils.isEmpty(strOptString)) {
                return null;
            }
            com.igexin.push.core.b.m mVar = new com.igexin.push.core.b.m();
            mVar.setType(com.igexin.push.core.b.n);
            mVar.setActionId(jSONObject.getString("actionid"));
            mVar.setDoActionId(jSONObject.getString("do"));
            mVar.b = strOptString;
            mVar.a = jSONObject.optString("do_failed");
            return mVar;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        com.igexin.push.core.b.m mVar = (com.igexin.push.core.b.m) baseActionBean;
        try {
            Intent uri = Intent.parseUri(mVar.b, 0);
            uri.setPackage(com.igexin.push.core.e.i.getPackageName());
            uri.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            if (com.igexin.push.f.c.b(uri, com.igexin.push.core.e.i)) {
                return PushMessageInterface.ActionPrepareState.success;
            }
            com.igexin.b.a.c.a.a(a + "|execute failed, activity not exist", new Object[0]);
            com.igexin.push.core.a.b.d();
            com.igexin.push.core.a.b.a(pushTaskBean.getId(), pushTaskBean.getMessageId(), mVar.a);
            return PushMessageInterface.ActionPrepareState.stop;
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(a + "|execute exception = " + th.toString(), new Object[0]);
            com.igexin.push.core.a.b.d();
            com.igexin.push.core.a.b.a(pushTaskBean.getId(), pushTaskBean.getMessageId(), mVar.a);
            return PushMessageInterface.ActionPrepareState.stop;
        }
    }
}
