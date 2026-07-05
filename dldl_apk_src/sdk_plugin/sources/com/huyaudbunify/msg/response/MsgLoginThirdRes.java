package com.huyaudbunify.msg.response;

import com.huyaudbunify.bean.ResLoginThird;
import com.huyaudbunify.core.AuthEvent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgLoginThirdRes {
    AuthEvent.LoginEvent loginEvent;
    ResLoginThird retData;

    public AuthEvent.LoginEvent getLoginEvent() {
        return this.loginEvent;
    }

    public void setLoginEvent(AuthEvent.LoginEvent loginEvent) {
        this.loginEvent = loginEvent;
    }

    public MsgLoginThirdRes(AuthEvent.LoginEvent loginEvent, ResLoginThird resLoginThird) {
        this.loginEvent = loginEvent;
        this.retData = resLoginThird;
    }

    public ResLoginThird getRetData() {
        return this.retData;
    }

    public void setRetData(ResLoginThird resLoginThird) {
        this.retData = resLoginThird;
    }
}
