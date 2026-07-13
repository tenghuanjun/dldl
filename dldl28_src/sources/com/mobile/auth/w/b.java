package com.mobile.auth.w;

import android.util.Base64;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes3.dex */
public class b {
    public static String a(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (Exception unused) {
            return "";
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

    public static String a(StringBuilder sb, String str) {
        try {
            try {
                try {
                    try {
                        Mac mac = Mac.getInstance("HmacSHA1");
                        mac.init(new SecretKeySpec(str.getBytes("utf-8"), "HmacSHA1"));
                        return Base64.encodeToString(mac.doFinal(sb.toString().getBytes("utf-8")), 2);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                            return null;
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                            return null;
                        }
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw new IllegalArgumentException(e.toString());
                }
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                return null;
            }
        } catch (InvalidKeyException e3) {
            throw new IllegalArgumentException(e3.toString());
        }
    }

    static List<Field> a(Class cls) {
        Class superclass;
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(Arrays.asList(cls.getDeclaredFields()));
            if (!cls.getName().equals(c.class.getName()) && (superclass = cls.getSuperclass()) != null && !superclass.getName().equals(Object.class.getName())) {
                arrayList.addAll(a(superclass));
            }
            return arrayList;
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
}
