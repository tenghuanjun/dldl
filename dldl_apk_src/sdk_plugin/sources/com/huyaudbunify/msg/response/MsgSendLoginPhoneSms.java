package com.huyaudbunify.msg.response;

import com.huyaudbunify.bean.ResLoginMobileSendSms;
import com.huyaudbunify.core.AuthEvent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgSendLoginPhoneSms {
    ResLoginMobileSendSms retData;
    AuthEvent.SendSmsEvent sendSmsEvent;

    public ResLoginMobileSendSms getRetData() {
        return this.retData;
    }

    public AuthEvent.SendSmsEvent getSendSmsEvent() {
        return this.sendSmsEvent;
    }

    public void setRetData(ResLoginMobileSendSms resLoginMobileSendSms) {
        this.retData = resLoginMobileSendSms;
    }

    public void setSendSmsEvent(AuthEvent.SendSmsEvent sendSmsEvent) {
        this.sendSmsEvent = sendSmsEvent;
    }

    public MsgSendLoginPhoneSms(ResLoginMobileSendSms resLoginMobileSendSms, AuthEvent.SendSmsEvent sendSmsEvent) {
        this.retData = resLoginMobileSendSms;
        this.sendSmsEvent = sendSmsEvent;
    }
}
