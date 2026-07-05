package com.taptap.sdk.kit.internal.http;

import android.os.Build;
import com.huya.mtp.hyns.report.NSPushReporter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

/* JADX INFO: compiled from: TapHttpUtil.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bJ \u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/taptap/sdk/kit/internal/http/TapHttpUtil;", "", "()V", "HMAC_SHA1", "", "HMAC_SHA256", "getRandomString", NSPushReporter.NS_PUSH_LENGTH_KEY, "", "secret", "token", "content", "algorithm", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapHttpUtil {
    public static final String HMAC_SHA1 = "HmacSHA1";
    public static final String HMAC_SHA256 = "HmacSHA256";
    public static final TapHttpUtil INSTANCE = new TapHttpUtil();

    private TapHttpUtil() {
    }

    public static /* synthetic */ String secret$default(TapHttpUtil tapHttpUtil, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = HMAC_SHA256;
        }
        return tapHttpUtil.secret(str, str2, str3);
    }

    public final String secret(String token, String content, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Mac mac = Mac.getInstance(algorithm);
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = token.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        mac.init(new SecretKeySpec(bytes, HMAC_SHA256));
        Charset UTF_82 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_82, "UTF_8");
        byte[] bytes2 = content.getBytes(UTF_82);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        byte[] bArrDoFinal = mac.doFinal(bytes2);
        if (Build.VERSION.SDK_INT >= 26) {
            String strEncodeToString = Base64.getEncoder().encodeToString(bArrDoFinal);
            Intrinsics.checkNotNullExpressionValue(strEncodeToString, "{\n            java.util.…signatureBytes)\n        }");
            return strEncodeToString;
        }
        String strEncodeToString2 = android.util.Base64.encodeToString(bArrDoFinal, 2);
        Intrinsics.checkNotNullExpressionValue(strEncodeToString2, "{\n            android.ut…Base64.NO_WRAP)\n        }");
        return strEncodeToString2;
    }

    public final String getRandomString(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(Random.INSTANCE.nextInt(36)));
        }
        String string = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }
}
