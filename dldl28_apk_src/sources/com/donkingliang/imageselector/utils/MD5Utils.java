package com.donkingliang.imageselector.utils;

import com.tencent.connect.common.Constants;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes3.dex */
public class MD5Utils {
    private static final String[] HEX_DIGITS = {"0", "1", "2", "3", Constants.VIA_TO_TYPE_QZONE, "5", Constants.VIA_SHARE_TYPE_INFO, "7", Constants.VIA_SHARE_TYPE_PUBLISHVIDEO, Constants.VIA_SHARE_TYPE_MINI_PROGRAM, "a", "b", "c", "d", "e", "f"};

    public static String md5(String str) {
        try {
            return toHex(MessageDigest.getInstance("MD5").digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String toHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String[] strArr = HEX_DIGITS;
            sb.append(strArr[(bArr[i] >> 4) & 15]);
            sb.append(strArr[bArr[i] & 15]);
        }
        return sb.toString();
    }
}
