package com.mobile.auth.d;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.security.MessageDigest;
import java.util.UUID;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class c {
    private static final String a = "com.mobile.auth.d.c";
    private static String b = "";

    public static String a() {
        try {
            String string = UUID.randomUUID().toString();
            try {
                string = UUID.nameUUIDFromBytes((string + System.currentTimeMillis() + Math.random()).getBytes("utf8")).toString();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return !TextUtils.isEmpty(string) ? string.replace("-", "") : string;
        } catch (Throwable th2) {
            try {
                ExceptionProcessor.processException(th2);
                return null;
            } catch (Throwable th3) {
                ExceptionProcessor.processException(th3);
                return null;
            }
        }
    }

    public static String a(Context context) {
        try {
            if (TextUtils.isEmpty(b)) {
                String strB = b(context);
                b = strB;
                if (TextUtils.isEmpty(strB)) {
                    String strC = c(context);
                    b = strC;
                    a(context, strC);
                }
            }
            return b;
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

    private static String a(String str) {
        try {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            try {
                byte[] bytes = str.getBytes();
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(bytes);
                byte[] bArrDigest = messageDigest.digest();
                char[] cArr2 = new char[bArrDigest.length * 2];
                int i = 0;
                for (byte b2 : bArrDigest) {
                    int i2 = i + 1;
                    cArr2[i] = cArr[(b2 >>> 4) & 15];
                    i += 2;
                    cArr2[i2] = cArr[b2 & 15];
                }
                return new String(cArr2);
            } catch (Exception unused) {
                return null;
            }
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

    private static void a(Context context, String str) {
        try {
            if (!TextUtils.isEmpty(str) && context != null) {
                b.a(context, "key_d_i_u", str);
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    private static String b(Context context) {
        try {
            return b.b(context, "key_d_i_u", "");
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

    private static String c(Context context) {
        try {
            String string = UUID.randomUUID().toString();
            return TextUtils.isEmpty(string) ? "default" : a(string + "default");
        } catch (Throwable th) {
            try {
                th.printStackTrace();
                return "default";
            } catch (Throwable th2) {
                try {
                    ExceptionProcessor.processException(th2);
                    return null;
                } catch (Throwable th3) {
                    ExceptionProcessor.processException(th3);
                    return null;
                }
            }
        }
    }
}
