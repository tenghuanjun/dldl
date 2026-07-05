package com.sy37sdk.account.util;

import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountRegType {

    public static class RegType {
        public static final String ACCOUNT_TYPE_ACCOUNT = "1";
        public static final String ACCOUNT_TYPE_PHONE = "2";
        public static final String ACCOUNT_TYPE_WECHAT = "3";
    }

    public static class RegWay {
        public static final String REG_ACCOUNT = "1";
        public static final String REG_FAST = "4";
        public static final String REG_ONE_KEY = "3";
        public static final String REG_OTHER = "7";
        public static final String REG_PHONE_CODE = "2";
        public static final String REG_QQ = "6";
        public static final String REG_UNKNOWN = "8";
        public static final String REG_WECHAT = "5";
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static String parseRegWay(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        byte b = -1;
        int iHashCode = str.hashCode();
        if (iHashCode != 56) {
            switch (iHashCode) {
                case 49:
                    if (str.equals("1")) {
                        b = 0;
                    }
                    break;
                case 50:
                    if (str.equals("2")) {
                        b = 1;
                    }
                    break;
                case 51:
                    if (str.equals("3")) {
                        b = 2;
                    }
                    break;
            }
        } else if (str.equals("8")) {
            b = 3;
        }
        return b != 0 ? b != 1 ? b != 2 ? b != 3 ? "4" : "5" : "3" : "2" : "1";
    }

    public static String parseLoginType(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        byte b = -1;
        int iHashCode = str.hashCode();
        if (iHashCode != 50) {
            if (iHashCode == 51 && str.equals("3")) {
                b = 1;
            }
        } else if (str.equals("2")) {
            b = 0;
        }
        return (b == 0 || b == 1) ? "2" : "1";
    }

    public static String parseRegType(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        byte b = -1;
        int iHashCode = str.hashCode();
        if (iHashCode != 50) {
            if (iHashCode != 51) {
                if (iHashCode == 53 && str.equals("5")) {
                    b = 2;
                }
            } else if (str.equals("3")) {
                b = 1;
            }
        } else if (str.equals("2")) {
            b = 0;
        }
        return (b == 0 || b == 1) ? "2" : b != 2 ? "1" : "3";
    }

    public static boolean isPhoneRegType(String str) {
        return parseRegType(str).equals("2");
    }
}
