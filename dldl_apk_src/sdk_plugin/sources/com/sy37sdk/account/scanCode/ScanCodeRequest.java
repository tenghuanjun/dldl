package com.sy37sdk.account.scanCode;

import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqwan.common.request.CommonParamsV1;
import com.sqwan.msdk.api.IMUrl;
import com.sy37sdk.account.UrlConstant;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ScanCodeRequest {
    /* JADX WARN: Removed duplicated region for block: B:13:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void notifyScan(com.sy37sdk.account.UserInfo r4, java.lang.String r5, com.sq.tool.network.SqHttpCallback<org.json.JSONObject> r6) {
        /*
            java.lang.String r0 = r4.getLoginType()
            int r1 = r0.hashCode()
            r2 = 50
            r3 = 1
            if (r1 == r2) goto L1c
            r2 = 51
            if (r1 == r2) goto L12
            goto L26
        L12:
            java.lang.String r1 = "3"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L26
            r0 = 1
            goto L27
        L1c:
            java.lang.String r1 = "2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L26
            r0 = 0
            goto L27
        L26:
            r0 = -1
        L27:
            if (r0 == 0) goto L31
            if (r0 == r3) goto L2e
            java.lang.String r0 = "common"
            goto L33
        L2e:
            java.lang.String r0 = "wx"
            goto L33
        L31:
            java.lang.String r0 = "phone"
        L33:
            java.lang.String r1 = com.sy37sdk.account.UrlConstant.URL_QRCODE_SCAN
            com.sq.tool.network.SqRequest r1 = com.sq.tool.network.SqRequest.of(r1)
            java.lang.String r2 = "os"
            java.lang.String r3 = "android"
            com.sq.tool.network.SqRequest r1 = r1.addParam(r2, r3)
            java.lang.String r2 = "code"
            com.sq.tool.network.SqRequest r5 = r1.addParam(r2, r5)
            java.lang.String r4 = r4.getToken()
            java.lang.String r1 = "token"
            com.sq.tool.network.SqRequest r4 = r5.addParam(r1, r4)
            java.lang.String r5 = "login_type"
            com.sq.tool.network.SqRequest r4 = r4.addParam(r5, r0)
            com.sqwan.common.request.CommonParamsV1 r5 = new com.sqwan.common.request.CommonParamsV1
            r5.<init>()
            com.sq.tool.network.SqRequest r4 = r4.addParamsTransformer(r5)
            com.sq.tool.network.SqRequest r4 = r4.signV3()
            r4.post(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy37sdk.account.scanCode.ScanCodeRequest.notifyScan(com.sy37sdk.account.UserInfo, java.lang.String, com.sq.tool.network.SqHttpCallback):void");
    }

    public static void confirmAuth(String str, String str2, SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.URL_QRCODE_CONFIRM_AUTH).addParam("os", IMUrl.OS).addParam("token", str).addParam("code", str2).addParamsTransformer(new CommonParamsV1()).signV3().post(sqHttpCallback);
    }

    public static void cancelAuth(String str, String str2, SqHttpCallback<JSONObject> sqHttpCallback) {
        SqRequest.of(UrlConstant.URL_QRCODE_CANCEL_AUTH).addParam("os", IMUrl.OS).addParam("token", str).addParam("code", str2).addParamsTransformer(new CommonParamsV1()).signV3().post(sqHttpCallback);
    }
}
