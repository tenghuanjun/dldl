package com.sq.tool.sqtools.utils;

import android.os.Process;
import java.io.BufferedReader;
import java.io.FileReader;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DebugUtils {
    public static String checkTracerPid() {
        String strTrim = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/status"));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.contains("TracerPid")) {
                    strTrim = line.substring(line.indexOf(":") + 1, line.length()).trim();
                    break;
                }
            }
            bufferedReader.close();
        } catch (Exception unused) {
        }
        return strTrim;
    }

    public static boolean hasTracerPid() {
        return !"0".equals(checkTracerPid());
    }
}
