package com.sy37sdk.account;

import android.content.Context;
import android.text.TextUtils;
import com.duowan.live.common.utils.LoginUtils;
import com.huya.component.login.LoginProperties;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.user.UserInfoManager;
import com.sqwan.common.util.JsonMap;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.common.util.SpUtils;
import com.sqwan.common.util.ZipString;
import com.sy37sdk.account.entrance.EntranceManager;
import java.util.Objects;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AccountCache {
    private static final String ACTIONTYPE = "action_type";
    private static final String AUTOISSAVE = "auto_Issave";
    private static final String AUTONAME = "auto_name";
    private static final String AUTOPASSWORD = "auto_pwd";
    private static final String AUTOSTATE = "auto_state";
    private static final String GET_VERIFY_CODE_LAST_TIME = "time_last_get_verify_code";
    public static final String LOGINED = "logined";
    private static final String LOGIN_WAY = "login_way";
    private static final String PASSWORD = "pd";
    private static final String SQ_PREFS = "sq_prefs";
    private static final String TAG = "[AccountCache]";
    private static final String TICKET = "ticket";
    private static Boolean TICKET_SUCCESS = false;
    private static final String TOKEN = "token";
    public static final String TOUCH_REPORT = "touchReport";
    private static String USERALIAS = "useralias";
    private static final String USERID = "userid";
    private static final String USERNAME = "username";
    private static final String USER_INFO = "user_info";

    public static void setVerifyCodeLastTime(Context context, long j) {
        SpUtils.get(context, SQ_PREFS).put(GET_VERIFY_CODE_LAST_TIME, j);
    }

    public static long getVerifyCodeLastTime(Context context) {
        return SpUtils.get(context, SQ_PREFS).getLong(GET_VERIFY_CODE_LAST_TIME, 0L);
    }

    public static void setUserid(Context context, String str) {
        SpUtils.get(context, SQ_PREFS).put("userid", str);
    }

    public static String getUserid(Context context) {
        String string = SpUtils.get(context, SQ_PREFS).getString("userid", "");
        com.sqwan.common.user.UserInfo currentUser = UserInfoManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            checkAndReport("uid", string, currentUser.getUid());
        }
        return string;
    }

    public static void setUsername(Context context, String str) {
        SpUtils.get(context, SQ_PREFS).put("username", str);
    }

    public static String getUsername(Context context) {
        String string = SpUtils.get(context, SQ_PREFS).getString("username", "");
        com.sqwan.common.user.UserInfo currentUser = UserInfoManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            checkAndReport("uname", string, currentUser.getUname());
        }
        return string;
    }

    public static void setPassword(Context context, String str) {
        SpUtils.get(context, SQ_PREFS).put(PASSWORD, ZipString.json2ZipString(str));
    }

    public static String getPassword(Context context) {
        String strZipString2Json = ZipString.zipString2Json(SpUtils.get(context, SQ_PREFS).getString(PASSWORD, ""));
        com.sqwan.common.user.UserInfo currentUser = UserInfoManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            checkAndReport(LoginUtils.PASSWORD, strZipString2Json, com.sqwan.common.user.UserInfo.getPwd(currentUser));
        }
        return strZipString2Json;
    }

    public static void setToken(Context context, String str) {
        SpUtils.get(context, SQ_PREFS).put("token", str);
    }

    public static String getToken(Context context) {
        String string = SpUtils.get(context, SQ_PREFS).getString("token", "");
        com.sqwan.common.user.UserInfo currentUser = UserInfoManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            checkAndReport("token", string, currentUser.getToken());
        }
        return string;
    }

    public static void setAutoName(Context context, String str) {
        SpUtils.get(context, SQ_PREFS).put(AUTONAME, str);
    }

    public static String getAutoName(Context context) {
        return SpUtils.get(context, SQ_PREFS).getString(AUTONAME, "");
    }

    public static void setAutoPassword(Context context, String str) {
        SpUtils.get(context, SQ_PREFS).put(AUTOPASSWORD, str);
    }

    public static String getAutoPassword(Context context) {
        return SpUtils.get(context, SQ_PREFS).getString(AUTOPASSWORD, "");
    }

    public static void setAutoState(Context context, String str) {
        SpUtils.get(context, SQ_PREFS).put(AUTOSTATE, str);
    }

    public static boolean getAutoState(Context context) {
        return SpUtils.get(context, SQ_PREFS).getString(AUTOSTATE, "0").equals("1");
    }

    public static void setAutoIssave(Context context, String str) {
        SpUtils.get(context, SQ_PREFS).put(AUTOISSAVE, str);
    }

    public static boolean getAutoIssave(Context context) {
        return SpUtils.get(context, SQ_PREFS).getString(AUTOISSAVE, "0").equals("1");
    }

    public static void setAccountAlias(Context context, String str) {
        SpUtils.get(context, SQ_PREFS).put(USERALIAS, str);
    }

    public static String getAccountAlias(Context context) {
        return SpUtils.get(context, SQ_PREFS).getString(USERALIAS, "");
    }

    public static void setLoginType(Context context, String str) {
        SpUtils.get(context, SQ_PREFS).put("login_way", str);
    }

    public static String getLoginType(Context context) {
        String string = SpUtils.get(context, SQ_PREFS).getString("login_way", "sq");
        com.sqwan.common.user.UserInfo currentUser = UserInfoManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            checkAndReport(LoginProperties.MarkLoginType, string, String.valueOf(currentUser.type.code));
        }
        return string;
    }

    public static void setLogined(Context context, boolean z) {
        SpUtils.get(context, SQ_PREFS).put("logined", z);
    }

    public static void setActionType(Context context, String str) {
        SpUtils.get(context, SQ_PREFS).put(ACTIONTYPE, str);
    }

    public static String getActionType(Context context) {
        String string = SpUtils.get(context, SQ_PREFS).getString(ACTIONTYPE);
        return TextUtils.isEmpty(string) ? "login" : string;
    }

    public static void setTicketState(boolean z) {
        TICKET_SUCCESS = Boolean.valueOf(z);
    }

    public static boolean getTicketState() {
        return TICKET_SUCCESS.booleanValue();
    }

    public static void setUserInfo(Context context, UserInfo userInfo) {
        if (userInfo == null) {
            setUserid(context, "");
            setPassword(context, "");
            setUsername(context, "");
            setToken(context, "");
            setAccountAlias(context, "");
            setActionType(context, "");
            setLoginType(context, "");
        } else {
            setUserid(context, userInfo.getUid());
            setPassword(context, userInfo.getUpwd());
            setUsername(context, userInfo.getUname());
            setToken(context, userInfo.getToken());
            setAccountAlias(context, userInfo.getAlias());
            setActionType(context, userInfo.getActionType());
            setLoginType(context, userInfo.getLoginType());
        }
        SpUtils.get(context, SQ_PREFS).put(USER_INFO, UserInfo.encodeToJson(userInfo).toString());
    }

    public static UserInfo getUserInfo(Context context) {
        UserInfo userInfoDecodeFromJson;
        String string = SpUtils.get(context, SQ_PREFS).getString(USER_INFO, "");
        if (TextUtils.isEmpty(string)) {
            String accountAlias = getAccountAlias(context);
            String username = getUsername(context);
            String password = getPassword(context);
            UserInfo userInfo = new UserInfo();
            userInfo.setUname(username);
            userInfo.setAlias(accountAlias);
            userInfo.setUpwd(ZipString.json2ZipString(password));
            return userInfo;
        }
        try {
            userInfoDecodeFromJson = UserInfo.decodeFromJson(new JSONObject(string));
        } catch (Exception e) {
            e.printStackTrace();
            userInfoDecodeFromJson = null;
        }
        if (userInfoDecodeFromJson == null || !TextUtils.isEmpty(userInfoDecodeFromJson.getMobile()) || EntranceManager.getInstance().isAccountLoginEntrance()) {
            return userInfoDecodeFromJson;
        }
        return null;
    }

    public static void setTouch(Context context, boolean z) {
        SpUtils.get(context, SQ_PREFS).put(TOUCH_REPORT, z);
    }

    public static boolean getTouch(Context context) {
        return SpUtils.get(context, SQ_PREFS).getBoolean(TOUCH_REPORT, false);
    }

    private static void checkAndReport(String str, String str2, String str3) {
        UserInfo userInfo;
        if (Objects.equals(str2, str3)) {
            return;
        }
        SQLog.e(TAG + str + " 值不同: old=" + str2 + ", new=" + str3);
        JsonMap jsonMap = new JsonMap();
        jsonMap.put("desc", str);
        jsonMap.put("old", str2);
        jsonMap.put("new", str3);
        Context applicationContext = SQContextWrapper.getApplicationContext();
        if (applicationContext != null && (userInfo = getUserInfo(applicationContext)) != null) {
            jsonMap.put("old_user", userInfo.toString());
        }
        com.sqwan.common.user.UserInfo currentUser = UserInfoManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            jsonMap.put("new_user", currentUser.toString());
        }
        BuglessAction.reportCatchException(new IllegalArgumentException(), "用户参数重构前后不一致", jsonMap.toString(), 999);
    }
}
