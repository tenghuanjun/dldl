package com.igexin.push.core.a.c;

import com.igexin.push.core.b.o;
import com.igexin.push.core.d;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class l implements PushMessageInterface {
    private static final String a = com.igexin.push.core.b.d + l.class.getName();

    private static void a(String str) {
        try {
            com.igexin.b.a.c.a.a(a + "|del condition taskid = " + str, new Object[0]);
            d.a.a.j.a(com.igexin.push.core.b.Z, new String[]{"taskid"}, new String[]{str});
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(a + "|del condition" + th.toString(), new Object[0]);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x011e A[PHI: r0 r4
  0x011e: PHI (r0v3 boolean) = (r0v2 boolean), (r0v24 boolean) binds: [B:48:0x012c, B:39:0x011c] A[DONT_GENERATE, DONT_INLINE]
  0x011e: PHI (r4v7 android.database.Cursor) = (r4v6 android.database.Cursor), (r4v10 android.database.Cursor) binds: [B:48:0x012c, B:39:0x011c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0132 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.igexin.push.extension.mod.PushMessageInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean executeAction(com.igexin.push.extension.mod.PushTaskBean r13, com.igexin.push.extension.mod.BaseActionBean r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 528
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.l.executeAction(com.igexin.push.extension.mod.PushTaskBean, com.igexin.push.extension.mod.BaseActionBean):boolean");
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public BaseActionBean parseAction(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("do") || !jSONObject.has("actionid") || !jSONObject.has("taskid")) {
                return null;
            }
            o oVar = new o();
            oVar.setType(com.igexin.push.core.b.m);
            oVar.setActionId(jSONObject.getString("actionid"));
            oVar.setDoActionId(jSONObject.getString("do"));
            oVar.a = jSONObject.getString("taskid");
            oVar.b = jSONObject.optBoolean("force");
            return oVar;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        return PushMessageInterface.ActionPrepareState.success;
    }
}
