package com.nirvana.tools.logger.utils;

import android.text.TextUtils;
import androidx.core.view.InputDeviceCompat;
import java.nio.ByteBuffer;
import kotlin.UByte;

/* JADX INFO: loaded from: classes3.dex */
public class EncodeUtil {
    public static String decode(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer(str);
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(str.length() / 2);
            int i = 0;
            while (i < str.length()) {
                int i2 = i + 1;
                int i3 = Integer.parseInt(stringBuffer.substring(i, i2), 16) << 4;
                i += 2;
                byteBufferAllocate.put((byte) (Integer.parseInt(stringBuffer.substring(i2, i), 16) | i3));
            }
            byte[] bArrArray = byteBufferAllocate.array();
            for (int i4 = 0; i4 < bArrArray.length; i4++) {
                if (i4 == bArrArray.length - 1) {
                    int length = bArrArray.length - 1;
                    bArrArray[length] = (byte) (bArrArray[length] ^ 98);
                } else {
                    byte b = bArrArray[i4];
                    byte b2 = bArrArray[i4 + 1];
                    if (b != b2) {
                        bArrArray[i4] = (byte) (b ^ b2);
                    }
                }
            }
            for (int length2 = bArrArray.length - 1; length2 >= 0; length2--) {
                if (length2 == 0) {
                    bArrArray[0] = (byte) (bArrArray[0] ^ 69);
                } else {
                    byte b3 = bArrArray[length2];
                    byte b4 = bArrArray[length2 - 1];
                    if (b3 != b4) {
                        bArrArray[length2] = (byte) (b3 ^ b4);
                    }
                }
            }
            return new String(bArrArray, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encode(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                bytes[0] = (byte) (bytes[0] ^ 69);
            } else {
                byte b = bytes[i];
                byte b2 = bytes[i - 1];
                if (b != b2) {
                    bytes[i] = (byte) (b ^ b2);
                }
            }
        }
        int i2 = length - 1;
        for (int i3 = i2; i3 >= 0; i3--) {
            if (i3 == i2) {
                bytes[i3] = (byte) (bytes[i3] ^ 98);
            } else {
                byte b3 = bytes[i3];
                byte b4 = bytes[i3 + 1];
                if (b3 != b4) {
                    bytes[i3] = (byte) (b3 ^ b4);
                }
            }
        }
        String str2 = "";
        for (byte b5 : bytes) {
            str2 = str2 + Integer.toHexString((b5 & UByte.MAX_VALUE) | InputDeviceCompat.SOURCE_ANY).substring(6);
        }
        return str2;
    }
}
