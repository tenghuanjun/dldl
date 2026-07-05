package com.sqwan.common.util;

import android.app.Activity;
import android.text.TextUtils;
import com.sqwan.msdk.config.ConfigManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class EncryptUtil {
    public static String encrypt(String str) {
        String strEncodeToString = "";
        try {
            strEncodeToString = android.util.Base64.encodeToString(AESUtil.encrypt(str, getEncodeKey()), 2);
            LogUtil.i("加密后：" + strEncodeToString);
            return strEncodeToString;
        } catch (Exception e) {
            e.printStackTrace();
            return strEncodeToString;
        }
    }

    public static String decrypt(String str) {
        try {
            return AESUtil.decryptString(str, getEncodeKey());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getEncodeKey() {
        Activity activity = SQContextWrapper.getActivity();
        if (activity == null) {
            return "";
        }
        String appKey = ConfigManager.getInstance(activity).getAppKey();
        if (TextUtils.isEmpty(appKey)) {
            return "";
        }
        int length = appKey.length();
        if (length < 16) {
            StringBuilder sb = new StringBuilder(appKey);
            for (int i = 0; i < 16 - length; i++) {
                sb.append("0");
            }
            return sb.toString();
        }
        return appKey.substring(0, 16);
    }
}
