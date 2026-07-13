package com.bytedance.framwork.core.sdklib.util;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
public class DecodeUtils {
    public static String decodeData(byte[] bArr) {
        try {
            byte[] bArrDecode = Base64.decode(bArr, 0);
            byte[] bArrDoFinal = new byte[0];
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec("yuNttCSojTyxZods".getBytes(), "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
                cipher.init(2, secretKeySpec);
                bArrDoFinal = cipher.doFinal(bArrDecode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String str = new String(bArrDoFinal);
            int iIndexOf = str.indexOf("$");
            return iIndexOf != -1 ? str.substring(0, iIndexOf) : str;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
