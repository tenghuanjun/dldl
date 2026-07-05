package com.sq.webview.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Md5Utils {
    public static String getMD5(String path) {
        try {
            DigestInputStream digestInputStream = new DigestInputStream(new FileInputStream(new File(path)), MessageDigest.getInstance("MD5"));
            while (digestInputStream.read(new byte[262144]) > 0) {
            }
            byte[] bArrDigest = digestInputStream.getMessageDigest().digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(String.format("%02X", Byte.valueOf(b)));
            }
            return sb.toString().toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
