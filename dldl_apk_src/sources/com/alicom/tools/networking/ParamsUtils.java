package com.alicom.tools.networking;

import android.util.Base64;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class ParamsUtils {
    private static final String CHARSET_UTF8 = "utf-8";
    private static final String SIGN_METHOD_HMAC = "hmac";
    private static final String SIGN_METHOD_MD5 = "md5";

    private static String byte2hex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString.toUpperCase());
        }
        return sb.toString();
    }

    private static byte[] encryptHMAC(String str, String str2) throws IOException {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("utf-8"), "HmacMD5");
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            mac.init(secretKeySpec);
            return mac.doFinal(str.getBytes("utf-8"));
        } catch (GeneralSecurityException e) {
            throw new IOException(e.toString());
        }
    }

    private static byte[] encryptMD5(String str) throws IOException {
        return encryptMD5(str.getBytes("utf-8"));
    }

    private static byte[] encryptMD5(byte[] bArr) throws IOException {
        try {
            return MessageDigest.getInstance("MD5").digest(bArr);
        } catch (GeneralSecurityException e) {
            throw new IOException(e.toString());
        }
    }

    static List<Field> getAllDeclaredFields(Class cls) {
        Class superclass;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(cls.getDeclaredFields()));
        if (!cls.getName().equals(Request.class.getName()) && (superclass = cls.getSuperclass()) != null && !superclass.getName().equals(Object.class.getName())) {
            arrayList.addAll(getAllDeclaredFields(superclass));
        }
        return arrayList;
    }

    private static boolean isNotEmpty(String str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String sign(StringBuilder sb, String str) {
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(new SecretKeySpec(str.getBytes("utf-8"), "HmacSHA1"));
            return Base64.encodeToString(mac.doFinal(sb.toString().getBytes("utf-8")), 2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2.toString());
        } catch (NoSuchAlgorithmException e3) {
            throw new IllegalArgumentException(e3.toString());
        }
    }

    public static String signTopRequest(Map<String, String> map, String str, String str2) throws IOException {
        byte[] bArrEncryptMD5;
        String[] strArr = (String[]) map.keySet().toArray(new String[0]);
        Arrays.sort(strArr);
        StringBuilder sb = new StringBuilder();
        if ("md5".equals(str2)) {
            sb.append(str);
        }
        for (String str3 : strArr) {
            String str4 = map.get(str3);
            if (isNotEmpty(str3) && isNotEmpty(str4)) {
                sb.append(str3);
                sb.append(str4);
            }
        }
        if (SIGN_METHOD_HMAC.equals(str2)) {
            bArrEncryptMD5 = encryptHMAC(sb.toString(), str);
        } else {
            sb.append(str);
            bArrEncryptMD5 = encryptMD5(sb.toString());
        }
        return byte2hex(bArrEncryptMD5);
    }

    public static String specialUrlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (Exception unused) {
            return "";
        }
    }
}
