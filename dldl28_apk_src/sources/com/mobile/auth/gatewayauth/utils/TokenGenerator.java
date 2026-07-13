package com.mobile.auth.gatewayauth.utils;

import android.content.Context;
import android.text.TextUtils;
import com.ali.security.MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.manager.SystemManager;
import com.mobile.auth.gatewayauth.manager.VendorSdkInfoManager;
import com.nirvana.tools.core.CryptUtil;
import com.nirvana.tools.core.ExecutorManager;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes3.dex */
public class TokenGenerator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.mobile.auth.p.a f705a;
    private SystemManager b;
    private VendorSdkInfoManager c;

    static {
        MinosSecurityLoad_1287811a0a9c21a2587d12b51b1a9eb7.SLoad("pns-2.13.4-LogOnlineStandardCuumRelease_alijtca_plus");
    }

    public TokenGenerator(com.mobile.auth.p.a aVar, SystemManager systemManager, VendorSdkInfoManager vendorSdkInfoManager) {
        this.f705a = aVar;
        this.b = systemManager;
        this.c = vendorSdkInfoManager;
    }

    private native String assembleCustomizeToken(Context context, String str, String str2, String str3, String str4, String str5, String str6);

    private native String generateCsrf(String str);

    public String a(Context context, String str, String str2, String str3, boolean z, String str4, String str5, String str6, boolean z2, String str7) {
        try {
            return assembleToken(context, null, null, null, str, str2, str3, z, str4, str5, str6, z2, str7);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public String assembleToken(Context context, String str, String str2, String str3, String str4, String str5, String str6, boolean z, String str7, String str8, String str9, boolean z2, String str10) {
        String str11;
        String strEncode = null;
        String strGenerateCsrf = "";
        if (z) {
            try {
                strGenerateCsrf = generateCsrf(str5);
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return strEncode;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return strEncode;
                }
            }
        }
        String str12 = strGenerateCsrf;
        if (z2) {
            String strG = k.g(context, "rpk");
            z = TextUtils.isEmpty(strG) ? false : true;
            str11 = strG;
        } else {
            str11 = null;
        }
        try {
            strEncode = !z ? CryptUtil.Base64.encode(EncryptUtils.encryptToken(context, str, str2, str3, this.c.c(), str4, str5, str6, str12, true, str10).getBytes("UTF-8")) : assembleCustomizeToken(context, str4, str5, str7, str8, str9, str11);
            return strEncode;
        } catch (UnsupportedEncodingException e) {
            this.f705a.e(ExecutorManager.getErrorInfoFromException(e));
            return strEncode;
        }
    }
}
