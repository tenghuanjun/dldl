package com.sy37sdk.account.auth;

import android.text.TextUtils;
import com.sqwan.common.util.TimeTools;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AuthConfigCache {
    public static AuthBean authBean;

    public static String getAuthUrl() {
        AuthBean authBean2 = authBean;
        return authBean2 != null ? authBean2.getUrl() : "";
    }

    public static String getPersonalDurl() {
        AuthBean authBean2 = authBean;
        return authBean2 != null ? authBean2.getDurl() : "";
    }

    public static boolean needAuth() {
        AuthBean authBean2 = authBean;
        return (authBean2 == null || !authBean2.needAuth() || TextUtils.isEmpty(authBean.getUrl())) ? false : true;
    }

    public static boolean isAuthFocus() {
        AuthBean authBean2 = authBean;
        return authBean2 != null && authBean2.isFocus();
    }

    public static boolean needAccumulateDuration() {
        AuthBean authBean2 = authBean;
        return authBean2 != null && authBean2.getNeedAccumulateDuration();
    }

    public static int getInterval() {
        AuthBean authBean2 = authBean;
        if (authBean2 != null) {
            return authBean2.getInterval();
        }
        return AuthBean.DEFAULT_INTERVAL;
    }

    public static boolean needAddiction() {
        AuthBean authBean2 = authBean;
        return authBean2 != null && authBean2.getNeedAddiction();
    }

    public static boolean allowRecharge() {
        AuthBean authBean2 = authBean;
        if (authBean2 != null) {
            return authBean2.getAllowRecharge();
        }
        return true;
    }

    public static void saveAuthConfig(String str) {
        try {
            authBean = AuthBean.parseFromJson(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void clearAuthConfig() {
        authBean = null;
    }

    public static void setIsAuth(boolean z) {
        AuthBean authBean2 = authBean;
        if (authBean2 != null) {
            authBean2.setIsAuth(z ? 1 : 0);
        }
    }

    public static boolean getIsAuth() {
        AuthBean authBean2 = authBean;
        if (authBean2 != null) {
            return authBean2.getIsAuth();
        }
        return false;
    }

    public static int getAuthLimitTime() {
        AuthBean authBean2 = authBean;
        if (authBean2 != null) {
            return authBean2.getRemainingTime() * 1000;
        }
        return 0;
    }

    public static void autoCalAuthLimitTime() {
        AuthBean authBean2 = authBean;
        if (authBean2 != null) {
            authBean.setRemainingTime(authBean.getRemainingTime() - (authBean2.getInterval() * 60));
        }
    }

    public static boolean isNeddReportOnline() {
        AuthBean authBean2 = authBean;
        if (authBean2 != null) {
            return authBean2.isNeddReportOnline();
        }
        return false;
    }

    public static void setNeedReportOnline(int i) {
        AuthBean authBean2 = authBean;
        if (authBean2 != null) {
            authBean2.setNeedReportOnline(i);
        }
    }

    public static int getAge() {
        AuthBean authBean2 = authBean;
        if (authBean2 != null) {
            return authBean2.getAge();
        }
        return -1;
    }

    public static boolean isPlayDate() {
        AuthBean authBean2 = authBean;
        return authBean2 != null && authBean2.isPlayableDate();
    }

    public static boolean isPlayTimeRange(long j) {
        String[] strArrSplit;
        AuthBean authBean2 = authBean;
        try {
            String[] strArrSplit2 = (authBean2 != null ? authBean2.getPlayableTimerange() : "").split("-");
            if (strArrSplit2 != null && strArrSplit2.length == 2) {
                String str = strArrSplit2[0];
                String str2 = strArrSplit2[1];
                String[] strArrSplit3 = str.split(":");
                if (strArrSplit3 != null && strArrSplit3.length == 2 && (strArrSplit = str2.split(":")) != null && strArrSplit.length == 2) {
                    return TimeTools.isCurrentInTimeScope(j, Integer.parseInt(strArrSplit3[0]), Integer.parseInt(strArrSplit3[1]), Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]));
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getTimeRangeLimitUrl() {
        AuthBean authBean2 = authBean;
        return authBean2 != null ? authBean2.getTimerangeLimtUrl() : "";
    }

    public static long getTimeStamp() {
        AuthBean authBean2 = authBean;
        if (authBean2 != null) {
            return authBean2.getTimestamp();
        }
        return 0L;
    }

    public static boolean isBsAuth() {
        AuthBean authBean2 = authBean;
        return authBean2 != null && authBean2.getBanshuAuthState() == 1;
    }

    public static boolean isAdult() {
        AuthBean authBean2 = authBean;
        return authBean2 != null && 18 - authBean2.getAge() <= 0;
    }
}
