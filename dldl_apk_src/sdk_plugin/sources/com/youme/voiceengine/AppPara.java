package com.youme.voiceengine;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class AppPara {
    private static String mAppVersionString = "";
    private static String mDeviceIMEIString = null;
    private static String mDocumentPathString = null;
    private static int mNetWorkType = -1;
    private static String mPackageNameString = null;
    private static String mSysNameString = "Android";
    private static String mSysVersionString = "";
    private static String mUUIDString;

    private static String atest(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append((char) (str.charAt(i) ^ 18));
        }
        return sb.toString();
    }

    public static void initPara(Context context) {
        boolean z;
        String[] strArr;
        try {
            String packageName = context.getPackageName();
            mPackageNameString = packageName;
            if (packageName != null) {
                NativeEngine.setPackageName(packageName);
            }
            NativeEngine.setModel(Build.MODEL);
            NativeEngine.setBrand(Build.BRAND);
            NativeEngine.setCPUArch(Build.CPU_ABI);
            NativeEngine.setCPUChip(Build.HARDWARE);
            try {
                strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            } catch (Throwable unused) {
            }
            if (strArr != null) {
                for (String str : strArr) {
                    if (str.equals("android.permission.READ_PHONE_STATE")) {
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
                    mDeviceIMEIString = (String) telephonyManager.getClass().getDeclaredMethod(atest("uwfVwd{qw[v"), new Class[0]).invoke(telephonyManager, new Object[0]);
                } catch (Throwable unused2) {
                }
            }
            if (mDeviceIMEIString == null) {
                mDeviceIMEIString = "";
            }
            NativeEngine.setDeviceIMEI(mDeviceIMEIString);
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences("YoumeCommon", 0);
                String string = sharedPreferences.getString("uuid", "");
                if (string.length() == 0) {
                    if (mDeviceIMEIString != null && mDeviceIMEIString.length() != 0) {
                        string = mDeviceIMEIString;
                    } else {
                        string = UUID.randomUUID().toString();
                    }
                    SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                    editorEdit.putString("uuid", string);
                    editorEdit.commit();
                }
                mUUIDString = string;
                NativeEngine.setUUID(string);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (mSysNameString != null) {
                NativeEngine.setSysName(mSysNameString);
            }
            String str2 = Build.VERSION.RELEASE;
            mSysVersionString = str2;
            if (str2 != null) {
                NativeEngine.setSysVersion(str2);
            }
            try {
                String str3 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                mAppVersionString = str3;
                if (str3 != null) {
                    NativeEngine.setVersionName(str3);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            try {
                onNetWorkChange(NetUtil.getNetworkState(context));
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            String string2 = context.getExternalFilesDir("").toString();
            mDocumentPathString = string2;
            if (string2 != null) {
                NativeEngine.setDocumentPath(string2);
            }
        } catch (Exception unused3) {
        }
    }

    public static void onNetWorkChange(int i) {
        mNetWorkType = i;
        NativeEngine.onNetWorkChanged(i);
    }

    public static void onHeadSetPlugin(int i) {
        NativeEngine.onHeadSetPlugin(i);
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static String getSysName() {
        return mSysNameString;
    }

    public static String getSysVersion() {
        return mSysVersionString;
    }

    public static String getAppVersion() {
        return mAppVersionString;
    }

    public static String getDeviceIMEI() {
        return mDeviceIMEIString;
    }

    public static String getPackageName() {
        return mPackageNameString;
    }

    public static String getUUID() {
        return mUUIDString;
    }

    public static String getDocumentPath() {
        return mDocumentPathString;
    }

    public static int getNetWorkType() {
        return mNetWorkType;
    }
}
