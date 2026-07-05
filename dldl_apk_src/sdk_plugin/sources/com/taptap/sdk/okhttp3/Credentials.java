package com.taptap.sdk.okhttp3;

import com.taptap.sdk.okhttp3.internal.Util;
import com.taptap.sdk.okio.ByteString;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public final class Credentials {
    private Credentials() {
    }

    public static String basic(String str, String str2) {
        return basic(str, str2, Util.ISO_8859_1);
    }

    public static String basic(String str, String str2, Charset charset) {
        return "Basic " + ByteString.encodeString(str + ":" + str2, charset).base64();
    }
}
