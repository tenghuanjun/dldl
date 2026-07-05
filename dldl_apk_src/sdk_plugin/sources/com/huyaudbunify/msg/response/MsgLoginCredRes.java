package com.huyaudbunify.msg.response;

import com.huyaudbunify.bean.ResLoginCred;
import com.huyaudbunify.core.AuthEvent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgLoginCredRes {
    AuthEvent.LoginEvent loginEvent;
    ResLoginCred retData;

    public AuthEvent.LoginEvent getLoginEvent() {
        return this.loginEvent;
    }

    public void setLoginEvent(AuthEvent.LoginEvent loginEvent) {
        this.loginEvent = loginEvent;
    }

    public MsgLoginCredRes(AuthEvent.LoginEvent loginEvent, ResLoginCred resLoginCred) {
        this.loginEvent = loginEvent;
        this.retData = resLoginCred;
    }

    public ResLoginCred getRetData() {
        return this.retData;
    }

    public void setRetData(ResLoginCred resLoginCred) {
        this.retData = resLoginCred;
    }
}
