package com.huyaudbunify.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResLoginPhoneSms {
    ResponseHeander header;
    LoginData loginData;
    List<StrategyDetail> loginStrategy = new ArrayList();

    public List<StrategyDetail> getLoginStrategy() {
        return this.loginStrategy;
    }

    public void setLoginStrategy(List<StrategyDetail> list) {
        this.loginStrategy = list;
    }

    public LoginData getLoginData() {
        return this.loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public ResponseHeander getHeader() {
        return this.header;
    }

    public void setHeader(ResponseHeander responseHeander) {
        this.header = responseHeander;
    }
}
