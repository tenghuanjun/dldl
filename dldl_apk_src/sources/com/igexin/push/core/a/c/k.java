package com.igexin.push.core.a.c;

import com.aliyun.aliyunface.api.ZIMFacade;
import com.igexin.push.core.b.n;
import com.igexin.push.extension.mod.BaseActionBean;
import com.igexin.push.extension.mod.PushMessageInterface;
import com.igexin.push.extension.mod.PushTaskBean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class k implements PushMessageInterface {
    private static final String a = com.igexin.push.config.c.a;

    /* JADX WARN: Removed duplicated region for block: B:12:0x002b A[PHI: r2 r10
  0x002b: PHI (r2v7 java.lang.String) = (r2v5 java.lang.String), (r2v8 java.lang.String) binds: [B:16:0x0065, B:11:0x0029] A[DONT_GENERATE, DONT_INLINE]
  0x002b: PHI (r10v8 java.lang.String) = (r10v7 java.lang.String), (r10v9 java.lang.String) binds: [B:16:0x0065, B:11:0x0029] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(com.igexin.push.core.b.n r9, java.lang.String r10) {
        /*
            java.lang.String r0 = r9.a
            if (r0 != 0) goto L5
            return
        L5:
            int r10 = r0.indexOf(r10)
            r1 = -1
            if (r10 != r1) goto Ld
            return
        Ld:
            java.lang.String r2 = ""
            r3 = 0
            java.lang.String r4 = "&"
            int r4 = r0.indexOf(r4)
            r5 = 0
            if (r4 != r1) goto L38
            int r1 = r10 + (-1)
            java.lang.String r2 = r0.substring(r5, r1)
            java.lang.String r10 = r0.substring(r10)
            java.lang.String r0 = "="
            boolean r0 = r10.contains(r0)
            if (r0 == 0) goto La6
        L2b:
            java.lang.String r0 = "="
            int r0 = r10.indexOf(r0)
            int r0 = r0 + 1
            java.lang.String r3 = r10.substring(r0)
            goto La6
        L38:
            int r6 = r10 + (-1)
            char r7 = r0.charAt(r6)
            r8 = 63
            if (r7 != r8) goto L68
            java.lang.String r1 = r0.substring(r5, r10)
            int r2 = r4 + 1
            java.lang.String r2 = r0.substring(r2)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r1)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            java.lang.String r10 = r0.substring(r10, r4)
            java.lang.String r0 = "="
            boolean r0 = r10.contains(r0)
            if (r0 == 0) goto La6
            goto L2b
        L68:
            char r4 = r0.charAt(r6)
            r7 = 38
            if (r4 != r7) goto La6
            java.lang.String r2 = r0.substring(r5, r6)
            java.lang.String r10 = r0.substring(r10)
            java.lang.String r0 = ""
            java.lang.String r3 = "&"
            int r3 = r10.indexOf(r3)
            if (r3 == r1) goto L8a
            java.lang.String r0 = r10.substring(r3)
            java.lang.String r10 = r10.substring(r5, r3)
        L8a:
            java.lang.String r1 = "="
            int r1 = r10.indexOf(r1)
            int r1 = r1 + 1
            java.lang.String r10 = r10.substring(r1)
            r3 = r10
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r2)
            r10.append(r0)
            java.lang.String r2 = r10.toString()
        La6:
            r9.a = r2
            r9.d = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.k.a(com.igexin.push.core.b.n, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002f A[PHI: r1 r5
  0x002f: PHI (r1v10 java.lang.String) = (r1v9 java.lang.String), (r1v11 java.lang.String) binds: [B:14:0x0068, B:9:0x002d] A[DONT_GENERATE, DONT_INLINE]
  0x002f: PHI (r5v7 java.lang.String) = (r5v5 java.lang.String), (r5v8 java.lang.String) binds: [B:14:0x0068, B:9:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.igexin.push.extension.mod.PushMessageInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean executeAction(com.igexin.push.extension.mod.PushTaskBean r13, com.igexin.push.extension.mod.BaseActionBean r14) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.a.c.k.executeAction(com.igexin.push.extension.mod.PushTaskBean, com.igexin.push.extension.mod.BaseActionBean):boolean");
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final BaseActionBean parseAction(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("url") || !jSONObject.has("do") || !jSONObject.has("actionid")) {
                return null;
            }
            String string = jSONObject.getString("url");
            if (string.equals("")) {
                return null;
            }
            n nVar = new n();
            nVar.setType(com.igexin.push.core.b.q);
            nVar.setActionId(jSONObject.getString("actionid"));
            nVar.setDoActionId(jSONObject.getString("do"));
            nVar.a = string;
            if (jSONObject.has("is_withcid") && ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE.equals(jSONObject.getString("is_withcid"))) {
                nVar.b = true;
            }
            if (jSONObject.has("is_withnettype") && ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE.equals(jSONObject.getString("is_withnettype"))) {
                nVar.c = true;
            }
            return nVar;
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // com.igexin.push.extension.mod.PushMessageInterface
    public final PushMessageInterface.ActionPrepareState prepareExecuteAction(PushTaskBean pushTaskBean, BaseActionBean baseActionBean) {
        return PushMessageInterface.ActionPrepareState.success;
    }
}
