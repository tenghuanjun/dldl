package com.nirvana.tools.logger.utils;

import android.content.Context;
import android.text.TextUtils;
import com.nirvana.tools.core.EncryptUtils;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import java.security.MessageDigest;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class LocalDeviceUtil {
    public static final String AUTH_DEVICEID_FILE = "AUTH_DEVICEINFO";
    public static final String AUTH_DEVICEID_UUID = "AUTH_DEVICEID_UUID";
    public static final String AUTH_UMAAID_UUID = "AUTH_UMAAID_UUID";
    private static final String IV_PASS = "0000000000000000";
    private static String mDeviceId = "";

    private static String MD5(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] bArrDigest = messageDigest.digest();
            char[] cArr2 = new char[bArrDigest.length << 1];
            int i = 0;
            for (byte b : bArrDigest) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (Exception unused) {
            return null;
        }
    }

    private static String createDeviceId(Context context) {
        try {
            String string = UUID.randomUUID().toString();
            return TextUtils.isEmpty(string) ? DownloadSettingKeys.BugFix.DEFAULT : EncryptUtils.encrypt(string + DownloadSettingKeys.BugFix.DEFAULT, "0000000000000000");
        } catch (Throwable th) {
            th.printStackTrace();
            return DownloadSettingKeys.BugFix.DEFAULT;
        }
    }

    private static String createUmaaId(Context context) {
        try {
            String string = UUID.randomUUID().toString();
            return TextUtils.isEmpty(string) ? DownloadSettingKeys.BugFix.DEFAULT : EncryptUtils.encrypt(string + DownloadSettingKeys.BugFix.DEFAULT, "0000000000000000");
        } catch (Throwable th) {
            th.printStackTrace();
            return DownloadSettingKeys.BugFix.DEFAULT;
        }
    }

    public static String getDeviceId(Context context) {
        if (TextUtils.isEmpty(mDeviceId)) {
            String saveDeviceId = getSaveDeviceId(context);
            mDeviceId = saveDeviceId;
            if (TextUtils.isEmpty(saveDeviceId)) {
                String strCreateDeviceId = createDeviceId(context);
                mDeviceId = strCreateDeviceId;
                saveDeviceId(context, strCreateDeviceId);
            }
        }
        return mDeviceId;
    }

    private static String getSaveDeviceId(Context context) {
        return (String) UTSharedPreferencesHelper.get(context, AUTH_DEVICEID_FILE, AUTH_DEVICEID_UUID, "");
    }

    private static String getSaveUmaaId(Context context) {
        return (String) UTSharedPreferencesHelper.get(context, AUTH_DEVICEID_FILE, AUTH_UMAAID_UUID, "");
    }

    public static String getUmaaId(Context context) {
        if (TextUtils.isEmpty(mDeviceId)) {
            String saveUmaaId = getSaveUmaaId(context);
            mDeviceId = saveUmaaId;
            if (TextUtils.isEmpty(saveUmaaId)) {
                String strCreateUmaaId = createUmaaId(context);
                mDeviceId = strCreateUmaaId;
                saveUmaaId(context, strCreateUmaaId);
            }
        }
        return mDeviceId;
    }

    private static void saveDeviceId(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return;
        }
        UTSharedPreferencesHelper.put(context, AUTH_DEVICEID_FILE, AUTH_DEVICEID_UUID, str);
    }

    private static void saveUmaaId(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return;
        }
        UTSharedPreferencesHelper.put(context, AUTH_DEVICEID_FILE, AUTH_UMAAID_UUID, str);
    }
}
