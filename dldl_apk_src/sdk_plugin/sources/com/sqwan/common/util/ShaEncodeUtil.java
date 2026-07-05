package com.sqwan.common.util;

import com.sqwan.msdk.utils.AppSigning;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ShaEncodeUtil {
    public static String encode(String str) {
        if (str != null && str.length() != 0) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(AppSigning.SHA1);
                messageDigest.update(str.getBytes("UTF-8"));
                byte[] bArrDigest = messageDigest.digest();
                char[] cArr2 = new char[bArrDigest.length * 2];
                int i = 0;
                for (byte b : bArrDigest) {
                    int i2 = i + 1;
                    cArr2[i] = cArr[(b >>> 4) & 15];
                    i = i2 + 1;
                    cArr2[i2] = cArr[b & 15];
                }
                return new String(cArr2);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
