package com.sqwan.common.user;

import com.sqwan.common.util.ZipString;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class UserInfo {
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PWD = "pwd";
    private static final String KEY_REFRESH_TOKEN = "refresh_token";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_TYPE = "type";
    private static final String KEY_UID = "uid";
    private static final String KEY_UNAME = "uname";
    public String refreshToken;
    public final String token;
    public final LoginType type;
    public final String uid;
    public String uname;

    JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid", this.uid);
            jSONObject.put("token", this.token);
            jSONObject.put("type", this.type.code);
            jSONObject.put("refresh_token", this.refreshToken);
            jSONObject.put("uname", this.uname);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    String toJsonString() {
        return toJson().toString();
    }

    public String toString() {
        return "User(" + this.type.name + "): uid=" + this.uid + ", uname=" + this.uname;
    }

    static UserInfo fromJson(String str) {
        UserInfo userInfoFromJson_;
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            LoginType loginType = LoginType.get(new JSONObject(str).getInt("type"));
            if (!LoginType.ACCOUNT.equals(loginType)) {
                if (!LoginType.PHONE.equals(loginType)) {
                    if (!LoginType.WECHAT.equals(loginType)) {
                        return null;
                    }
                    userInfoFromJson_ = WechatUserInfo.fromJson_(str);
                } else {
                    userInfoFromJson_ = PhoneUserInfo.fromJson_(str);
                }
            } else {
                userInfoFromJson_ = AccountUserInfo.fromJson_(str);
            }
            return userInfoFromJson_;
        } catch (Exception unused) {
            return null;
        }
    }

    public UserInfo(LoginType loginType, String str, String str2) {
        this.type = loginType;
        this.uid = str;
        this.token = str2;
    }

    public String getUid() {
        return this.uid;
    }

    public String getToken() {
        return this.token;
    }

    public String getUname() {
        return this.uname;
    }

    public static class AccountUserInfo extends UserInfo {
        public final String pwd;

        public AccountUserInfo(String str, String str2, String str3, String str4) {
            super(LoginType.ACCOUNT, str, str2);
            this.uname = str3;
            this.pwd = str4;
        }

        @Override // com.sqwan.common.user.UserInfo
        JSONObject toJson() {
            JSONObject json = super.toJson();
            try {
                json.put(UserInfo.KEY_PWD, ZipString.json2ZipString(this.pwd));
            } catch (Exception unused) {
            }
            return json;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static UserInfo fromJson_(String str) {
            if (str == null || str.isEmpty()) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                AccountUserInfo accountUserInfo = new AccountUserInfo(jSONObject.getString("uid"), jSONObject.getString("token"), jSONObject.getString("uname"), ZipString.zipString2Json(jSONObject.getString(UserInfo.KEY_PWD)));
                try {
                    accountUserInfo.refreshToken = jSONObject.optString("refresh_token");
                } catch (Exception unused) {
                }
                return accountUserInfo;
            } catch (Exception unused2) {
                return null;
            }
        }
    }

    public static class PhoneUserInfo extends UserInfo {
        public final String phone;
        public String pwd;

        public PhoneUserInfo(String str, String str2, String str3) {
            super(LoginType.PHONE, str, str2);
            this.phone = str3;
        }

        @Override // com.sqwan.common.user.UserInfo
        JSONObject toJson() {
            JSONObject json = super.toJson();
            try {
                json.put("phone", this.phone);
                json.put(UserInfo.KEY_PWD, ZipString.json2ZipString(this.pwd));
            } catch (Exception unused) {
            }
            return json;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static UserInfo fromJson_(String str) {
            if (str == null || str.isEmpty()) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                PhoneUserInfo phoneUserInfo = new PhoneUserInfo(jSONObject.getString("uid"), jSONObject.getString("token"), jSONObject.getString("phone"));
                try {
                    phoneUserInfo.pwd = ZipString.zipString2Json(jSONObject.optString(UserInfo.KEY_PWD));
                    phoneUserInfo.refreshToken = jSONObject.optString("refresh_token");
                    phoneUserInfo.uname = jSONObject.optString("uname");
                } catch (Exception unused) {
                }
                return phoneUserInfo;
            } catch (Exception unused2) {
                return null;
            }
        }

        @Override // com.sqwan.common.user.UserInfo
        public String toString() {
            return "User(" + this.type.name + "): uid=" + this.uid + ", phone=" + this.phone + ", uname=" + this.uname;
        }
    }

    public static class WechatUserInfo extends UserInfo {
        public String pwd;

        public WechatUserInfo(String str, String str2) {
            super(LoginType.WECHAT, str, str2);
        }

        @Override // com.sqwan.common.user.UserInfo
        JSONObject toJson() {
            JSONObject json = super.toJson();
            try {
                json.put(UserInfo.KEY_PWD, ZipString.json2ZipString(this.pwd));
            } catch (Exception unused) {
            }
            return json;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static UserInfo fromJson_(String str) {
            if (str == null || str.isEmpty()) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                WechatUserInfo wechatUserInfo = new WechatUserInfo(jSONObject.getString("uid"), jSONObject.getString("token"));
                try {
                    wechatUserInfo.uname = jSONObject.optString("uname");
                    wechatUserInfo.pwd = ZipString.zipString2Json(jSONObject.optString(UserInfo.KEY_PWD));
                    wechatUserInfo.refreshToken = jSONObject.optString("refresh_token");
                } catch (Exception unused) {
                }
                return wechatUserInfo;
            } catch (Exception unused2) {
                return null;
            }
        }
    }

    public static String getPwd(UserInfo userInfo) {
        String str;
        if (userInfo == null) {
            return "";
        }
        if (userInfo instanceof AccountUserInfo) {
            str = ((AccountUserInfo) userInfo).pwd;
        } else if (userInfo instanceof PhoneUserInfo) {
            str = ((PhoneUserInfo) userInfo).pwd;
        } else {
            str = userInfo instanceof WechatUserInfo ? ((WechatUserInfo) userInfo).pwd : "";
        }
        return str == null ? "" : str;
    }
}
