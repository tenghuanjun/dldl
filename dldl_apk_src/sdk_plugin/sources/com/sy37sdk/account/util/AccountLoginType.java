package com.sy37sdk.account.util;

import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountLoginType {

    public static class LoginType {
        public static final String ACCOUNT_TYPE_ACCOUNT = "1";
        public static final String ACCOUNT_TYPE_PHONE = "2";
        public static final String ACCOUNT_TYPE_WECHAT = "3";
    }

    public static class LoginWay {
        public static final String LOGIN_ACCOUNT = "1";
        public static final String LOGIN_AUTO = "4";
        public static final String LOGIN_FAST_TOKEN = "6";
        public static final String LOGIN_ONE_KEY = "3";
        public static final String LOGIN_OTHER = "0";
        public static final String LOGIN_PHONE_CODE = "2";
        public static final String LOGIN_PHONE_PWD = "5";
        public static final String LOGIN_QQ = "7";
        public static final String LOGIN_WECHAT = "8";
    }

    public static String parseLoginType(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        byte b = -1;
        int iHashCode = str.hashCode();
        if (iHashCode != 50) {
            if (iHashCode != 51) {
                if (iHashCode != 53) {
                    if (iHashCode == 56 && str.equals("8")) {
                        b = 3;
                    }
                } else if (str.equals("5")) {
                    b = 1;
                }
            } else if (str.equals("3")) {
                b = 2;
            }
        } else if (str.equals("2")) {
            b = 0;
        }
        return (b == 0 || b == 1 || b == 2) ? "2" : b != 3 ? "1" : "3";
    }

    public static boolean isPhoneLoginType(String str) {
        return parseLoginType(str).equals("2");
    }
}
