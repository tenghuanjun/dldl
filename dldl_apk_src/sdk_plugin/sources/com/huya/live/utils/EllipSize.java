package com.huya.live.utils;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import com.duowan.auk.ArkValue;
import com.huya.mtp.utils.FP;
import java.io.IOException;
import java.io.StringReader;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EllipSize {
    private static final String ELLIPSIS = "...";
    public static final int MAX_LOVE_BEANS_NICK_NAME_COUNT = 7;
    public static final int MAX_NICK_NAME_COUNT = 9;

    public static String ellipsize(String str, int i) {
        return ellipsize(str, i, 14.0f);
    }

    public static String ellipsize(String str, int i, float f) {
        if (str == null) {
            return "";
        }
        TextPaint textPaint = new TextPaint();
        float fApplyDimension = TypedValue.applyDimension(2, f, ArkValue.gContext.getResources().getDisplayMetrics());
        textPaint.setTextSize(fApplyDimension);
        textPaint.setTypeface(Typeface.create((String) null, 1));
        CharSequence charSequenceEllipsize = TextUtils.ellipsize(str, textPaint, fApplyDimension * i, TextUtils.TruncateAt.END);
        if (charSequenceEllipsize != null) {
            return charSequenceEllipsize.toString();
        }
        return null;
    }

    public static String getText(String str, int i) {
        if (str.length() <= i) {
            return str;
        }
        return str.substring(0, i) + ELLIPSIS;
    }

    public static String getNumStr(String str, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            i2 = str.charAt(i3) < 128 ? i2 + 1 : i2 + 2;
            if (i2 == i) {
                if (i3 == str.length() - 1) {
                    return str;
                }
                return str.substring(0, i3 + 1) + ELLIPSIS;
            }
            if (i2 > i) {
                String strSubstring = str.substring(0, i3 + 1);
                if (i3 == str.length() - 1) {
                    return strSubstring;
                }
                return strSubstring + ELLIPSIS;
            }
        }
        return str;
    }

    public static int getStringLength(String str) {
        int i = 0;
        if (FP.empty(str)) {
            return 0;
        }
        StringReader stringReader = new StringReader(str);
        int i2 = 0;
        while (true) {
            try {
                int i3 = stringReader.read();
                if (i3 == -1) {
                    break;
                }
                if (i3 < 256) {
                    i++;
                } else {
                    i2++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (i / 2) + (i2 / 2);
    }

    public static String limitStringLength(String str, int i) {
        return limitStringLength(str, i, ELLIPSIS);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
    
        r0.read();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String limitStringLength(java.lang.String r5, int r6, java.lang.String r7) {
        /*
            boolean r0 = com.huya.mtp.utils.FP.empty(r5)
            if (r0 == 0) goto L7
            return r5
        L7:
            java.io.StringReader r0 = new java.io.StringReader
            r0.<init>(r5)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r1 = 0
            r2 = 0
        L13:
            int r3 = r0.read()     // Catch: java.io.IOException -> L34
            r4 = -1
            if (r3 == r4) goto L38
            r4 = 256(0x100, float:3.59E-43)
            if (r3 >= r4) goto L21
            int r2 = r2 + 1
            goto L23
        L21:
            int r1 = r1 + 1
        L23:
            char r3 = (char) r3     // Catch: java.io.IOException -> L34
            java.lang.Character r3 = java.lang.Character.valueOf(r3)     // Catch: java.io.IOException -> L34
            r5.add(r3)     // Catch: java.io.IOException -> L34
            int r3 = r1 << 1
            int r3 = r3 + r2
            if (r3 < r6) goto L13
            r0.read()     // Catch: java.io.IOException -> L34
            goto L38
        L34:
            r6 = move-exception
            r6.printStackTrace()
        L38:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.Object[] r5 = r5.toArray()
            java.lang.String r0 = ""
            java.lang.String r5 = displayArray(r5, r0)
            r6.append(r5)
            r6.append(r7)
            java.lang.String r5 = r6.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.live.utils.EllipSize.limitStringLength(java.lang.String, int, java.lang.String):java.lang.String");
    }

    public static String displayArray(Object[] objArr, String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < objArr.length) {
            sb.append(objArr[i]);
            i++;
            if (i < objArr.length) {
                sb.append(str);
            }
        }
        return sb.toString();
    }
}
