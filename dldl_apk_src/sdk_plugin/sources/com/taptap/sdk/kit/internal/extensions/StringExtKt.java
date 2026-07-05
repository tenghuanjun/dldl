package com.taptap.sdk.kit.internal.extensions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StringExt.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0002\u001a\u0006\u0010\u0002\u001a\u00020\u0001¨\u0006\u0003"}, d2 = {"generateRandomString", "", "generateSessionId", "tap-common_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class StringExtKt {
    public static final String generateSessionId() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd", Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        sb.append(simpleDateFormat.format(new Date()));
        sb.append(generateRandomString());
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sessionId.append(generat…andomString()).toString()");
        return string;
    }

    private static final String generateRandomString() {
        char[] charArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
        StringBuilder sb = new StringBuilder();
        int length = charArray.length;
        for (int i = 0; i < 12; i++) {
            sb.append(charArray[(int) Math.floor(Math.random() * ((double) length))]);
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "stringBuilder.toString()");
        return string;
    }
}
