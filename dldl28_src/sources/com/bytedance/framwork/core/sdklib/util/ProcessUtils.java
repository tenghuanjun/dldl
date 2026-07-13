package com.bytedance.framwork.core.sdklib.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes2.dex */
public class ProcessUtils {
    private static String sCurProcessName;

    public static String getCurProcessName(Context context) {
        int iMyPid;
        String str = sCurProcessName;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            iMyPid = Process.myPid();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == iMyPid) {
                String str2 = runningAppProcessInfo.processName;
                sCurProcessName = str2;
                return str2;
            }
            String curProcessNameFromProc = getCurProcessNameFromProc();
            sCurProcessName = curProcessNameFromProc;
            return curProcessNameFromProc;
        }
        String curProcessNameFromProc2 = getCurProcessNameFromProc();
        sCurProcessName = curProcessNameFromProc2;
        return curProcessNameFromProc2;
    }

    private static String getCurProcessNameFromProc() {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/cmdline"), "iso-8859-1"));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    int i = bufferedReader.read();
                    if (i <= 0) {
                        break;
                    }
                    sb.append((char) i);
                }
                String string = sb.toString();
                try {
                    bufferedReader.close();
                } catch (Exception unused) {
                }
                return string;
            } catch (Throwable unused2) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception unused3) {
                    }
                }
                return null;
            }
        } catch (Throwable unused4) {
            bufferedReader = null;
        }
    }
}
