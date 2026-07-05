package com.mobile.auth.k;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class s {
    private static String a(String[] strArr) {
        Process processStart;
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        try {
            processStart = new ProcessBuilder(strArr).start();
            bufferedReader = new BufferedReader(new InputStreamReader(processStart.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
            return sb.toString();
        }
        processStart.getInputStream().close();
        processStart.destroy();
        return sb.toString();
    }

    public static boolean a() {
        int i;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String str : new String[]{"/system/xbin/", "/system/bin/", "/system/sbin/", "/sbin/", "/vendor/bin/", "/su/bin/"}) {
            String str2 = str + "su";
            if (new File(str2).exists()) {
                String strA = a(new String[]{"ls", "-l", str2});
                f.b("cyb", "isRooted=" + strA);
                if (TextUtils.isEmpty(strA)) {
                    return false;
                }
                return strA.indexOf("root") != strA.lastIndexOf("root");
            }
            return false;
        }
        return false;
    }
}
