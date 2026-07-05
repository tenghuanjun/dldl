package com.bytedance.pangle.g;

import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class e {
    public static boolean a(String str, String str2) throws Throwable {
        o oVarA;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (com.bytedance.pangle.util.i.a()) {
                oVarA = d.a(str);
            } else {
                oVarA = a.a(str);
            }
            Signature[] signatureArr = oVarA != null ? oVarA.b : null;
            byte[] bArrDecode = Base64.decode(Zeus.getPlugin(str2).mSignature, 0);
            if (bArrDecode == null || bArrDecode.length == 0) {
                ZeusLogger.w(ZeusLogger.TAG_INSTALL, "ApkSignatureVerify get hostSignature error : ".concat(String.valueOf(str)));
                return false;
            }
            int length = 0;
            for (Signature signature : signatureArr) {
                length += signature.toByteArray().length;
            }
            byte[] bArr = new byte[length];
            int length2 = 0;
            for (Signature signature2 : signatureArr) {
                System.arraycopy(signature2.toByteArray(), 0, bArr, length2, signature2.toByteArray().length);
                length2 += signature2.toByteArray().length;
            }
            boolean zA = o.a(bArr, bArrDecode);
            if (!zA) {
                ZeusLogger.w(ZeusLogger.TAG_INSTALL, "ApkSignatureVerify verify plugin signature error : ".concat(String.valueOf(str)));
            }
            return zA;
        } catch (q e) {
            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "ApkSignatureVerify verify plugin signature error : ".concat(String.valueOf(str)), e);
            return false;
        }
    }
}
