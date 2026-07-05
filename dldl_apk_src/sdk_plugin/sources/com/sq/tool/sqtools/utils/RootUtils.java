package com.sq.tool.sqtools.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RootUtils {
    public static boolean isRoot() {
        return isSUExistFiles();
    }

    public static boolean isSUExistFiles() {
        String[] strArr = {"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/system/app/Superuser.apk"};
        for (int i = 0; i < 9; i++) {
            String str = strArr[i];
            if (new File(str).exists() && isExecutable(str)) {
                return true;
            }
        }
        return false;
    }

    public static String getSuFiles() {
        String[] strArr = {"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/system/app/Superuser.apk"};
        for (int i = 0; i < 9; i++) {
            String str = strArr[i];
            if (new File(str).exists() && isExecutable(str)) {
                return str;
            }
        }
        return null;
    }

    private static boolean isExecutable(String str) {
        Process processExec = null;
        try {
            processExec = Runtime.getRuntime().exec("ls -l " + str);
            String line = new BufferedReader(new InputStreamReader(processExec.getInputStream())).readLine();
            if (line != null && line.length() >= 4) {
                char cCharAt = line.charAt(3);
                if (cCharAt == 's' || cCharAt == 'x') {
                    if (processExec != null) {
                        processExec.destroy();
                    }
                    return true;
                }
            }
            if (processExec == null) {
                return false;
            }
        } catch (IOException unused) {
            if (processExec == null) {
                return false;
            }
        } catch (Throwable th) {
            if (processExec != null) {
                processExec.destroy();
            }
            throw th;
        }
        processExec.destroy();
        return false;
    }

    public static String getRoSecureProp() {
        return SystemPropertiesUtil.getProperty("ro.secure");
    }

    public static String getRoDebugProp() {
        return SystemPropertiesUtil.getProperty("ro.debuggable");
    }
}
