package com.sqwan.common.util;

import java.util.Random;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RandomUtils {
    public static String randomData(int i) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        Random random2 = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            int iNextInt = random.nextInt(3);
            if (iNextInt == 0) {
                sb.append(random2.nextInt(10));
            } else if (iNextInt == 1) {
                sb.append((char) (random2.nextInt(26) + 65));
            } else if (iNextInt == 2) {
                sb.append((char) (random2.nextInt(26) + 97));
            }
        }
        return sb.toString();
    }
}
