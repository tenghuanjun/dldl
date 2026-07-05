package com.duowan.live.common;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class EmojiUtils {
    public static boolean isEmojiCharacter(int i) {
        return (i >= 9728 && i <= 10175) || i == 12349 || i == 8265 || i == 8252 || (i >= 8192 && i <= 8207) || ((i >= 8232 && i <= 8239) || i == 8287 || ((i >= 8293 && i <= 8303) || ((i >= 8448 && i <= 8527) || ((i >= 8960 && i <= 9215) || ((i >= 11008 && i <= 11263) || ((i >= 10496 && i <= 10623) || ((i >= 12800 && i <= 13055) || ((i >= 55296 && i <= 57343) || ((i >= 57344 && i <= 63743) || ((i >= 65024 && i <= 65039) || i >= 65536))))))))));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String unicode2Emoji(java.lang.String r6) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L9
            java.lang.String r6 = ""
            return r6
        L9:
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            int r1 = r6.length()
            r2 = 0
        L13:
            if (r2 >= r1) goto L62
            char r3 = r6.charAt(r2)
            r4 = 92
            if (r3 != r4) goto L58
            int r3 = r1 + (-5)
            if (r2 >= r3) goto L50
            int r3 = r2 + 1
            char r4 = r6.charAt(r3)
            r5 = 117(0x75, float:1.64E-43)
            if (r4 == r5) goto L33
            char r3 = r6.charAt(r3)
            r4 = 85
            if (r3 != r4) goto L50
        L33:
            int r3 = r2 + 2
            int r4 = r2 + 6
            java.lang.String r3 = r6.substring(r3, r4)     // Catch: java.lang.NumberFormatException -> L48
            r4 = 16
            int r3 = java.lang.Integer.parseInt(r3, r4)     // Catch: java.lang.NumberFormatException -> L48
            char r3 = (char) r3     // Catch: java.lang.NumberFormatException -> L48
            r0.append(r3)     // Catch: java.lang.NumberFormatException -> L48
            int r2 = r2 + 5
            goto L5f
        L48:
            char r3 = r6.charAt(r2)
            r0.append(r3)
            goto L5f
        L50:
            char r3 = r6.charAt(r2)
            r0.append(r3)
            goto L5f
        L58:
            char r3 = r6.charAt(r2)
            r0.append(r3)
        L5f:
            int r2 = r2 + 1
            goto L13
        L62:
            java.lang.String r6 = r0.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.live.common.EmojiUtils.unicode2Emoji(java.lang.String):java.lang.String");
    }

    public static String emoji2Unicode(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (isEmojiCharacter(str.codePointAt(i))) {
                stringBuffer.append("\\u");
                stringBuffer.append(Integer.toHexString(cCharAt));
            } else {
                stringBuffer.append(cCharAt);
            }
        }
        return stringBuffer.toString();
    }
}
