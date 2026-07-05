package com.sy37sdk.account.util;

import android.content.Context;
import android.text.TextUtils;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.AccountTools;
import com.sy37sdk.account.UserInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountUtil {
    private static boolean isCanFastBack;
    private static boolean isToFastLogin;
    private static boolean isToWeiChatLogin;
    private static boolean notSupportFast;

    public static List<UserInfo> getAllUserInfo(Context context) {
        return AccountTools.getAccountFromFile(context);
    }

    public static List<UserInfo> getUserInfoFilterByAccount(Context context) {
        List<UserInfo> accountFromFile = AccountTools.getAccountFromFile(context);
        ArrayList arrayList = new ArrayList();
        if (accountFromFile != null && accountFromFile.size() > 0) {
            for (UserInfo userInfo : accountFromFile) {
                if (TextUtils.isEmpty(userInfo.getMobile())) {
                    arrayList.add(userInfo);
                }
            }
        }
        return arrayList;
    }

    public static UserInfo getLastUserInfo(Context context) {
        List<UserInfo> allUserInfo;
        UserInfo userInfo = AccountCache.getUserInfo(context);
        return ((userInfo == null || TextUtils.isEmpty(userInfo.getUname())) && (allUserInfo = getAllUserInfo(context)) != null && allUserInfo.size() > 0) ? allUserInfo.get(allUserInfo.size() - 1) : userInfo;
    }

    public static UserInfo getLastAccountUserInfo(Context context) {
        UserInfo userInfo = AccountCache.getUserInfo(context);
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getUname()) && TextUtils.isEmpty(userInfo.getMobile())) {
            return userInfo;
        }
        List<UserInfo> userInfoFilterByAccount = getUserInfoFilterByAccount(context);
        if (userInfoFilterByAccount == null || userInfoFilterByAccount.size() <= 0) {
            return null;
        }
        for (int size = userInfoFilterByAccount.size() - 1; size >= 0; size--) {
            UserInfo userInfo2 = userInfoFilterByAccount.get(size);
            if (userInfo2 != null && !TextUtils.isEmpty(userInfo2.getUname()) && TextUtils.isEmpty(userInfo2.getMobile())) {
                return userInfo2;
            }
        }
        return null;
    }

    public static UserInfo findUserByUname(Context context, String str) {
        List<UserInfo> allUserInfo = getAllUserInfo(context);
        if (TextUtils.isEmpty(str) || allUserInfo == null || allUserInfo.size() <= 0) {
            return null;
        }
        for (UserInfo userInfo : allUserInfo) {
            if (userInfo != null && !TextUtils.isEmpty(userInfo.getUname()) && str.toLowerCase().equals(userInfo.getUname().toLowerCase())) {
                return userInfo;
            }
        }
        return null;
    }

    public static UserInfo findUserByPhone(Context context, String str) {
        List<UserInfo> allUserInfo = getAllUserInfo(context);
        if (TextUtils.isEmpty(str) || allUserInfo == null || allUserInfo.size() <= 0) {
            return null;
        }
        for (UserInfo userInfo : allUserInfo) {
            if (userInfo != null && !TextUtils.isEmpty(userInfo.getLoginType()) && userInfo.getLoginType().equals("2") && userInfo.getMobile().equals(str)) {
                return userInfo;
            }
        }
        return null;
    }

    public static boolean checkToFastLogin() {
        return isToFastLogin;
    }

    public static void setToFastLogin(boolean z) {
        isToFastLogin = z;
    }

    public static boolean checkCanFastBack() {
        return isCanFastBack;
    }

    public static void setCanFastBack(boolean z) {
        isCanFastBack = z;
    }

    public static boolean checkNotSupportFast() {
        return notSupportFast;
    }

    public static void setNotSupportFast(boolean z) {
        notSupportFast = z;
    }

    public static boolean checkToWeiChatLogin() {
        return isToWeiChatLogin;
    }

    public static void setToWeiChatLogin(boolean z) {
        isToWeiChatLogin = z;
    }
}
