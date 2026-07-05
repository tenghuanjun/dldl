package com.sqwan.common.user;

import com.sq.tool.logger.SQLog;
import com.sy37sdk.order.nat.trade.NativePayWay;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LoginType {
    public final int code;
    public final String name;
    public static final LoginType ACCOUNT = new LoginType(1, "account");
    public static final LoginType PHONE = new LoginType(2, "phone");
    public static final LoginType WECHAT = new LoginType(3, NativePayWay.PWAY_KEY_WECHAT);
    public static final LoginType UNKNOWN = new LoginType(-1, "unknown");
    private static final List<LoginType> VALUES = new ArrayList<LoginType>() { // from class: com.sqwan.common.user.LoginType.1
        {
            add(LoginType.ACCOUNT);
            add(LoginType.PHONE);
            add(LoginType.WECHAT);
            add(LoginType.UNKNOWN);
        }
    };

    public LoginType(int i, String str) {
        this.code = i;
        this.name = str;
    }

    public static void register(LoginType loginType) {
        if (VALUES.contains(loginType)) {
            SQLog.wt("sqsdk", "重复注册登录类型" + loginType);
            return;
        }
        SQLog.dt("sqsdk", "注册登录类型" + loginType);
        VALUES.add(loginType);
    }

    public static Collection<LoginType> values() {
        return VALUES;
    }

    public String toString() {
        return this.name;
    }

    public static LoginType get(String str) {
        if (str != null) {
            for (LoginType loginType : values()) {
                if (loginType.name.equalsIgnoreCase(str)) {
                    return loginType;
                }
            }
        }
        return UNKNOWN;
    }

    public static LoginType get(int i) {
        if (i > 0) {
            for (LoginType loginType : values()) {
                if (loginType.code == i) {
                    return loginType;
                }
            }
        }
        return UNKNOWN;
    }

    public String capitalize() {
        char cCharAt;
        char titleCase;
        String lowerCase = this.name.toLowerCase(Locale.US);
        int length = lowerCase.length();
        if (length == 0 || cCharAt == (titleCase = Character.toTitleCase((cCharAt = lowerCase.charAt(0))))) {
            return lowerCase;
        }
        char[] cArr = new char[length];
        cArr[0] = titleCase;
        lowerCase.getChars(1, length, cArr, 1);
        return String.valueOf(cArr);
    }

    public String lowerCase() {
        return this.name.toLowerCase(Locale.US);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LoginType loginType = (LoginType) obj;
        if (this.code != loginType.code) {
            return false;
        }
        return this.name.equals(loginType.name);
    }

    public int hashCode() {
        return (this.code * 31) + this.name.hashCode();
    }
}
