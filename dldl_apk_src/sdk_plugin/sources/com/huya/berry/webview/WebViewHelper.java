package com.huya.berry.webview;

import android.os.Build;
import com.huya.berry.gamesdk.wup.WupHelper;
import com.huya.component.login.LoginProperties;
import com.huya.mtp.utils.StringUtils;
import com.huyaudbunify.HuyaAuth;
import com.huyaudbunify.util.HuyaUrlUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebViewHelper {
    public static final String BASE_JUMP_SERVICE = "lgn.huya.com";
    private static final String KEY_LAST_LOGIN_UID = "last_login_uid";

    public static int getByPass() {
        return 3;
    }

    public static String getH5Info() {
        return HuyaAuth.getInstance().getH5Info(LoginProperties.uid.get().longValue(), LoginProperties.passport.get(), "");
    }

    public static String getH5InfoEx() {
        return HuyaAuth.getInstance().getH5InfoEx(LoginProperties.uid.get().longValue());
    }

    public static String getDeviceData() {
        return Build.MODEL + "|" + Build.BRAND + "|" + Build.VERSION.RELEASE;
    }

    public static boolean isLogined() {
        return LoginProperties.uid.get().longValue() != 0;
    }

    public static String getBusinessUrl(String str) {
        return !isLogined() ? str : HuyaUrlUtil.getHYBusinessUrl(str, LoginProperties.uid.get().longValue(), LoginProperties.passport.get(), "5480", WupHelper.getVersion(), "adr_game_sdk");
    }

    public static String getLoginUrl(String str) {
        return getLoginUrl(str, BASE_JUMP_SERVICE);
    }

    public static String getLoginUrl(String str, String str2) {
        if (!isLogined()) {
            return str;
        }
        if (StringUtils.isNullOrEmpty(str2)) {
            str2 = BASE_JUMP_SERVICE;
        }
        String str3 = str2;
        return HuyaUrlUtil.getHyLgnJumpUrl(str3, LoginProperties.uid.get().longValue(), LoginProperties.passport.get(), "5480", str, str3);
    }
}
