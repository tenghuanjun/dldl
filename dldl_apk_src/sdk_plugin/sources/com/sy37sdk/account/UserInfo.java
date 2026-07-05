package com.sy37sdk.account;

import android.text.TextUtils;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.user.LoginType;
import com.sqwan.common.user.UserInfo;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ZipString;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UserInfo {
    public static boolean wechatLoginFail;
    private String actionType;
    private String alias;
    private String loginType;
    private String loginWay;
    private String mobile;
    private String refreshToken;
    private String ticket;
    private String token;
    private String uid;
    private String uname;
    private String upwd;

    private static String getStrWithoutNull(String str) {
        return str == null ? "" : str;
    }

    public String getActionType() {
        return this.actionType;
    }

    public void setActionType(String str) {
        this.actionType = str;
    }

    public String getLoginType() {
        return this.loginType;
    }

    public void setLoginType(String str) {
        this.loginType = str;
    }

    public String getLoginWay() {
        return this.loginWay;
    }

    public void setLoginWay(String str) {
        this.loginWay = str;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String str) {
        this.refreshToken = str;
    }

    public String getUname() {
        return this.uname;
    }

    public void setUname(String str) {
        this.uname = str;
    }

    public String getUpwd() {
        return ZipString.zipString2Json(this.upwd);
    }

    public void setUpwd(String str) {
        this.upwd = str;
    }

    public String getTicket() {
        return this.ticket;
    }

    public void setTicket(String str) {
        this.ticket = str;
    }

    public String toString() {
        return "【UserInfo】uname:" + this.uname + ", uid:" + this.uid + ", alias:" + this.alias + ", upwd:" + this.upwd + ", token:" + this.token + ", refreshToken:" + this.refreshToken + ", actionType:" + this.actionType + ", loginType:" + this.loginType + ", loginWay:" + this.loginWay + ", mobile:" + this.mobile;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String str) {
        this.alias = str;
    }

    public boolean isPhoneLoginType() {
        return !TextUtils.isEmpty(this.loginType) && this.loginType.equals("2");
    }

    public static UserInfo decodeFromJson(JSONObject jSONObject) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUname(jSONObject.optString("uname"));
        userInfo.setUpwd(jSONObject.optString(SqConstants.UPWD));
        userInfo.setAlias(jSONObject.optString("alias"));
        userInfo.setToken(jSONObject.optString("token"));
        userInfo.setRefreshToken(jSONObject.optString(SqConstants.REFRESH_TOKEN));
        userInfo.setMobile(jSONObject.optString("mobile"));
        userInfo.setUid(jSONObject.optString("uid"));
        userInfo.setLoginType(jSONObject.optString("login_type"));
        userInfo.setLoginWay(jSONObject.optString("login_way"));
        userInfo.setActionType(jSONObject.optString("action_type"));
        userInfo.setTicket(jSONObject.optString("ticket"));
        return userInfo;
    }

    public static JSONObject encodeToJson(UserInfo userInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uname", getStrWithoutNull(userInfo.uname));
            jSONObject.put(SqConstants.UPWD, getStrWithoutNull(userInfo.upwd));
            jSONObject.put("alias", getStrWithoutNull(userInfo.alias));
            jSONObject.put("token", getStrWithoutNull(userInfo.token));
            jSONObject.put(SqConstants.REFRESH_TOKEN, getStrWithoutNull(userInfo.refreshToken));
            jSONObject.put("mobile", getStrWithoutNull(userInfo.mobile));
            jSONObject.put("uid", getStrWithoutNull(userInfo.uid));
            jSONObject.put("action_type", getStrWithoutNull(userInfo.actionType));
            jSONObject.put("login_type", getStrWithoutNull(userInfo.loginType));
            jSONObject.put("login_way", getStrWithoutNull(userInfo.loginWay));
            jSONObject.put("ticket", getStrWithoutNull(userInfo.ticket));
        } catch (Exception e) {
            LogUtil.e("UserInfo", "encode user info to json error!");
            e.printStackTrace();
        }
        return jSONObject;
    }

    public com.sqwan.common.user.UserInfo convert() {
        com.sqwan.common.user.UserInfo accountUserInfo;
        String uname = getUname();
        String upwd = getUpwd();
        String token = getToken();
        String refreshToken = getRefreshToken();
        String uid = getUid();
        LoginType loginType = LoginType.get(Integer.parseInt(getLoginType()));
        if (loginType == LoginType.ACCOUNT) {
            accountUserInfo = new UserInfo.AccountUserInfo(uid, token, uname, upwd);
        } else if (loginType == LoginType.PHONE) {
            UserInfo.PhoneUserInfo phoneUserInfo = new UserInfo.PhoneUserInfo(uid, token, getMobile());
            phoneUserInfo.pwd = upwd;
            accountUserInfo = phoneUserInfo;
        } else if (loginType == LoginType.WECHAT) {
            UserInfo.WechatUserInfo wechatUserInfo = new UserInfo.WechatUserInfo(uid, token);
            wechatUserInfo.pwd = upwd;
            accountUserInfo = wechatUserInfo;
        } else {
            BuglessAction.reportCatchException(new IllegalStateException("用户缓存异常"), encodeToJson(this).toString(), 999);
            return null;
        }
        accountUserInfo.uname = uname;
        accountUserInfo.refreshToken = refreshToken;
        return accountUserInfo;
    }
}
