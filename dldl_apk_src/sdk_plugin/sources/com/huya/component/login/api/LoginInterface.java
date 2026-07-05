package com.huya.component.login.api;

import android.text.TextUtils;
import com.huya.component.login.Account;
import com.huya.component.login.LoginInfo;
import com.huya.mtp.utils.Utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoginInterface {

    public static class LogOut {
    }

    public static class UpgradeAvalibal {
    }

    public static class UpgradeIgnore {
    }

    public static class Login {
        public LoginInfo loginInfo;

        public Login(Account account) {
            LoginInfo loginInfo = new LoginInfo();
            this.loginInfo = loginInfo;
            loginInfo.account = account.account;
            this.loginInfo.password = account.password;
            this.loginInfo.token = null;
            this.loginInfo.type = 0;
        }

        public Login(long j) {
            this(j, null, null);
        }

        public Login(long j, String str, String str2) {
            LoginInfo loginInfo = new LoginInfo();
            this.loginInfo = loginInfo;
            loginInfo.type = 2;
            this.loginInfo.uid = j;
            if (TextUtils.isEmpty(str)) {
                this.loginInfo.account = String.valueOf(j);
            } else {
                this.loginInfo.account = str;
            }
            if (TextUtils.isEmpty(str2)) {
                this.loginInfo.password = "";
            } else {
                this.loginInfo.password = Utils.getHashIfPassIsPlainText(str2);
            }
        }

        public Login(String str, String str2) {
            LoginInfo loginInfo = new LoginInfo();
            this.loginInfo = loginInfo;
            loginInfo.account = str;
            this.loginInfo.password = Utils.getHashIfPassIsPlainText(str2);
            this.loginInfo.token = null;
            this.loginInfo.type = 0;
        }

        public Login(String str, String str2, int i, String str3) {
            LoginInfo loginInfo = new LoginInfo();
            this.loginInfo = loginInfo;
            loginInfo.account = str;
            this.loginInfo.password = Utils.getHashIfPassIsPlainText(str2);
            this.loginInfo.token = str3;
            this.loginInfo.type = 0;
            this.loginInfo.strategy_type = i;
        }

        public Login(LoginInfo loginInfo) {
            this.loginInfo = loginInfo;
        }
    }

    public static class ThirdPartyLogin {
        public String oauthType;
        public String openId;
        public String thirdPartyAppId;
        public int thirdType;
        public String tokenId;

        public ThirdPartyLogin(int i, String str, String str2, String str3) {
            this(i, str, str2, str3, "1");
        }

        public ThirdPartyLogin(int i, String str, String str2, String str3, String str4) {
            this.thirdType = i;
            this.tokenId = str;
            this.thirdPartyAppId = str2;
            this.openId = str3;
            this.oauthType = str4;
        }
    }

    public static class CheckModPwd {
        public String user;

        public CheckModPwd(String str) {
            this.user = str;
        }
    }

    public static class VerifySmsCode {
        public String code;
        public String mStrMobile;
        public String user;

        public VerifySmsCode(String str, String str2, String str3) {
            this.user = str;
            this.code = str3;
            this.mStrMobile = str2;
        }
    }

    public static class QuickModPwd {
        public String password;
        public String user;

        public QuickModPwd(String str, String str2) {
            this.user = str;
            this.password = Utils.getHashIfPassIsPlainText(str2);
        }
    }

    public static class RefreshPicCode {
        public String user;

        public RefreshPicCode(String str) {
            this.user = str;
        }
    }

    public static class CheckSmsUp {
        public String password;
        public String user;

        public CheckSmsUp(String str, String str2) {
            this.user = str;
            this.password = Utils.getHashIfPassIsPlainText(str2);
        }
    }

    public static class RefreshSmsCode {
        public boolean isRegister;
        public String mobile;
        public String user;

        public RefreshSmsCode(String str, String str2, boolean z) {
            this.user = str;
            this.mobile = str2;
            this.isRegister = z;
        }
    }

    public static class CheckUserRegister {
        public String mobile;

        public CheckUserRegister(String str) {
            this.mobile = str;
        }
    }

    public static class Register {
        public String mobile;
        public String pwd;
        public String smsCode;

        public Register(String str, String str2, String str3) {
            this.mobile = str;
            this.smsCode = str2;
            this.pwd = Utils.getHashIfPassIsPlainText(str3);
        }
    }

    public static class SendLoginPhoneSms {
        public String strMobile;

        public SendLoginPhoneSms(String str) {
            this.strMobile = str;
        }
    }

    public static class LoginPhoneSms {
        public String strMobile;
        public String strSms;

        public LoginPhoneSms(String str, String str2) {
            this.strMobile = str;
            this.strSms = str2;
        }
    }

    public static class LoginMobileQuick {
        public String token;
        public int type;

        public LoginMobileQuick(int i, String str) {
            this.type = i;
            this.token = str;
        }
    }

    public static class LoginSmsCode {
        public String user;

        public LoginSmsCode(String str) {
            this.user = str;
        }
    }

    public static class AppInBackstage {
        public Boolean isInBackstage;

        public AppInBackstage(Boolean bool) {
            this.isInBackstage = false;
            this.isInBackstage = bool;
        }
    }

    public static class GetVerifyCode {
        public static String phone;

        public GetVerifyCode(String str) {
            phone = str;
        }
    }
}
