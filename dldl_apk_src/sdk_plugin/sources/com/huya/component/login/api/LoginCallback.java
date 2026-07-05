package com.huya.component.login.api;

import android.graphics.Bitmap;
import com.huyaudbunify.bean.ResLoginMobileSendSms;
import com.huyaudbunify.core.AuthEvent;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoginCallback {

    public static class LoginFinished {
        public String desc;
        public boolean isLoginCred;
        public boolean success;
    }

    public static class LoginInfoSettle {
    }

    public static class LoginReqSuccess {
        public String mMobileMask;

        public LoginReqSuccess(String str) {
            this.mMobileMask = str;
        }
    }

    public static class LoginNextVerify {
        public ArrayList<AuthEvent.NextVerify> nextVerifies;

        public LoginNextVerify(ArrayList<AuthEvent.NextVerify> arrayList) {
            this.nextVerifies = arrayList;
        }
    }

    public static class LoginNextVerifyFailed {
        public String description;
        public ArrayList<AuthEvent.NextVerify> nextVerifies;

        public LoginNextVerifyFailed(String str, ArrayList<AuthEvent.NextVerify> arrayList) {
            this.description = str;
            this.nextVerifies = arrayList;
        }
    }

    public static class CheckModPwdResult {
        public AuthEvent.CheckModPwdEvent event;

        public CheckModPwdResult(AuthEvent.CheckModPwdEvent checkModPwdEvent) {
            this.event = checkModPwdEvent;
        }
    }

    public static class VerifySmsCodeResult {
        public AuthEvent.VerifySmsCodeEvent event;

        public VerifySmsCodeResult(AuthEvent.VerifySmsCodeEvent verifySmsCodeEvent) {
            this.event = verifySmsCodeEvent;
        }
    }

    public static class RegisterResult {
        public AuthEvent.RegisterEvent event;

        public RegisterResult(AuthEvent.RegisterEvent registerEvent) {
            this.event = registerEvent;
        }
    }

    public static class SmsModPwdResult {
        public AuthEvent.SmsModPwdEvent event;

        public SmsModPwdResult(AuthEvent.SmsModPwdEvent smsModPwdEvent) {
            this.event = smsModPwdEvent;
        }
    }

    public static class LoginCode {
        public List<byte[]> context;
        public Bitmap picCode;
        public byte[] picId;

        public LoginCode(Bitmap bitmap, byte[] bArr, List<byte[]> list) {
            this.picCode = bitmap;
            this.picId = bArr;
            this.context = list;
        }
    }

    public static class RefreshSmsCodeCallBack {
        public AuthEvent.SendSmsEvent event;

        public RefreshSmsCodeCallBack(AuthEvent.SendSmsEvent sendSmsEvent) {
            this.event = sendSmsEvent;
        }
    }

    public static class RefreshPicCodeCallback {
        public AuthEvent.RefreshPicEvent event;

        public RefreshPicCodeCallback(AuthEvent.RefreshPicEvent refreshPicEvent) {
            this.event = refreshPicEvent;
        }
    }

    public static class CheckUserRegisterResult {
        public AuthEvent.CheckRegisterEvent event;

        public CheckUserRegisterResult(AuthEvent.CheckRegisterEvent checkRegisterEvent) {
            this.event = checkRegisterEvent;
        }
    }

    public static class EndAnchorLink {
        public long uid;

        public EndAnchorLink(long j) {
            this.uid = j;
        }
    }

    public static class SetPresenterPCAuthInfoResult {
        public boolean isOk;

        public SetPresenterPCAuthInfoResult(boolean z) {
            this.isOk = z;
        }
    }

    public static class UidChanged {
        public long newUid;
        public long oldUid;

        public UidChanged(long j, long j2) {
            this.oldUid = 0L;
            this.newUid = 0L;
            this.oldUid = j;
            this.newUid = j2;
        }
    }

    public static class RegisterLoginResult {
        public AuthEvent.LoginEvent event;

        public RegisterLoginResult(AuthEvent.LoginEvent loginEvent) {
            this.event = loginEvent;
        }
    }

    public static class OpenMigrateUrl {
        public String url;

        public OpenMigrateUrl(String str) {
            this.url = str;
        }
    }

    public static class H5SendSms {
        public String mPhoneNume;

        public H5SendSms(String str) {
            this.mPhoneNume = str;
        }
    }

    public static class LoginTimeout {
        public String mDescription;

        public LoginTimeout(String str) {
            this.mDescription = str;
        }
    }

    public static class OnSaveThirdUserInfo {
        public final String avatar;
        public final String nick;

        public OnSaveThirdUserInfo(String str, String str2) {
            this.avatar = str;
            this.nick = str2;
        }
    }

    public static class H5LgnThirdAuth {
        public String code;

        public H5LgnThirdAuth(String str) {
            this.code = str;
        }
    }

    public static class GetVerifyCode {
        public ResLoginMobileSendSms rsp;

        public GetVerifyCode(ResLoginMobileSendSms resLoginMobileSendSms) {
            this.rsp = resLoginMobileSendSms;
        }
    }
}
