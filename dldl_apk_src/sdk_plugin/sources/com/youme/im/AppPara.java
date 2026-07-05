package com.youme.im;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class AppPara {
    private static String atest(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append((char) (str.charAt(i) ^ 18));
        }
        return sb.toString();
    }

    public static void initPara(Context context) {
        boolean z;
        String str;
        String[] strArr;
        try {
            String packageName = context.getPackageName();
            if (packageName != null) {
                NativeEngine.setPackageName(packageName);
            }
        } catch (Exception unused) {
        }
        try {
            NativeEngine.setModel(Build.MODEL);
            NativeEngine.setBrand(Build.BRAND);
            NativeEngine.setCPUArch(Build.CPU_ABI);
            NativeEngine.setCPUChip(Build.HARDWARE);
        } catch (Exception unused2) {
        }
        try {
            strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
        } catch (Exception unused3) {
        }
        if (strArr != null) {
            for (String str2 : strArr) {
                if (str2.equals("android.permission.READ_PHONE_STATE")) {
                    z = true;
                    break;
                }
            }
            z = false;
        } else {
            z = false;
        }
        if (z) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                str = (String) telephonyManager.getClass().getDeclaredMethod(atest("uwfVwd{qw[v"), new Class[0]).invoke(telephonyManager, new Object[0]);
            } catch (Exception unused4) {
                str = "";
            }
        } else {
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("YoumeCommon", 0);
            String string = sharedPreferences.getString("uuid", "");
            if (TextUtils.isEmpty(string)) {
                string = UUID.randomUUID().toString();
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putString("uuid", string);
                editorEdit.commit();
            }
            str = string;
        }
        NativeEngine.setDeviceIMEI(str != null ? str : "");
        try {
            String str3 = Build.VERSION.RELEASE;
            if (str3 != null) {
                NativeEngine.setSysVersion(str3);
            }
        } catch (Exception unused5) {
        }
        try {
            String string2 = context.getFilesDir().toString();
            if (string2 != null) {
                NativeEngine.setDocumentPath(string2);
            }
        } catch (Exception unused6) {
        }
        try {
            String string3 = context.getCacheDir().toString();
            if (string3 != null) {
                NativeEngine.setCachePath(string3);
            }
        } catch (Exception unused7) {
        }
    }
}
