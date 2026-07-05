package com.taptap.sdk.common.network.utils;

import android.os.Build;
import com.taptap.sdk.kit.internal.http.TapHttpUtil;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SecretUtil.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004¨\u0006\u0007"}, d2 = {"Lcom/taptap/sdk/common/network/utils/SecretUtil;", "", "()V", "secret", "", "token", "content", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class SecretUtil {
    public static final SecretUtil INSTANCE = new SecretUtil();

    private SecretUtil() {
    }

    public final String secret(String token, String content) throws NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(content, "content");
        Mac mac = Mac.getInstance(TapHttpUtil.HMAC_SHA256);
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = token.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        mac.init(new SecretKeySpec(bytes, TapHttpUtil.HMAC_SHA256));
        Charset UTF_82 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_82, "UTF_8");
        byte[] bytes2 = content.getBytes(UTF_82);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        byte[] bArrDoFinal = mac.doFinal(bytes2);
        if (Build.VERSION.SDK_INT >= 26) {
            String strEncodeToString = Base64.getEncoder().encodeToString(bArrDoFinal);
            Intrinsics.checkNotNullExpressionValue(strEncodeToString, "getEncoder().encodeToString(signatureBytes)");
            return strEncodeToString;
        }
        String strEncodeToString2 = android.util.Base64.encodeToString(bArrDoFinal, 2);
        Intrinsics.checkNotNullExpressionValue(strEncodeToString2, "encodeToString(signature…s, androidBase64.NO_WRAP)");
        return strEncodeToString2;
    }
}
