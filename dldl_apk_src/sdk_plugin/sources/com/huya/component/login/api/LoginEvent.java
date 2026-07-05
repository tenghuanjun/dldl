package com.huya.component.login.api;

import com.huya.component.login.LoginInfo;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoginEvent {

    public static class LogoutRest {
    }

    public static class LogOutFinished {
        public String ext;
        public Reason reason;

        public enum Reason {
            Normal,
            NoNetwork,
            KickOff,
            Ban,
            PasswdChanged
        }

        public LogOutFinished(Reason reason) {
            this(reason, "");
        }

        public LogOutFinished(Reason reason, String str) {
            this.reason = reason;
            this.ext = str;
        }
    }

    public static class LoginFail {
        public final String des;
        public int errorCode;
        public LoginInfo loginType;
        public Reason reason;

        public enum Reason {
            Cancel,
            NoNetwork,
            NullAccount,
            NullPassword,
            PasswordError,
            UserNoExist,
            TimeOut,
            ServerHasNotReceivedSms,
            VerifyFail,
            Unknown
        }

        public static Reason converToReason(int i) {
            if (Reason.Cancel.ordinal() == i) {
                return Reason.Cancel;
            }
            if (Reason.NoNetwork.ordinal() == i) {
                return Reason.NoNetwork;
            }
            if (Reason.NullAccount.ordinal() == i) {
                return Reason.NullAccount;
            }
            if (Reason.NullPassword.ordinal() == i) {
                return Reason.NullPassword;
            }
            if (Reason.PasswordError.ordinal() == i) {
                return Reason.PasswordError;
            }
            if (Reason.UserNoExist.ordinal() == i) {
                return Reason.UserNoExist;
            }
            if (Reason.TimeOut.ordinal() == i) {
                return Reason.TimeOut;
            }
            if (Reason.ServerHasNotReceivedSms.ordinal() == i) {
                return Reason.ServerHasNotReceivedSms;
            }
            if (Reason.VerifyFail.ordinal() == i) {
                return Reason.VerifyFail;
            }
            return Reason.Unknown;
        }

        public LoginFail(Reason reason, String str) {
            this.reason = reason;
            this.des = str;
            this.errorCode = 0;
        }

        public LoginFail(Reason reason, int i, String str) {
            this.reason = reason;
            this.des = str;
            this.errorCode = i;
        }

        public LoginFail(Reason reason, LoginInfo loginInfo, int i, String str) {
            this.reason = reason;
            this.des = str;
            this.errorCode = i;
            this.loginType = loginInfo;
        }
    }

    public static class LoginSuccess {
        public LoginInfo loginInfo;
        public final long uid;

        public LoginSuccess(long j) {
            this.uid = j;
        }

        public LoginSuccess(LoginInfo loginInfo, long j) {
            this.uid = j;
            this.loginInfo = loginInfo;
        }
    }
}
