package com.huyaudbunify.bean;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ResFPSendSms {
    ResponseHeander header;
    List<StrategyDetail> loginStrategy = new ArrayList();

    public ResponseHeander getHeader() {
        return this.header;
    }

    public void setHeader(ResponseHeander responseHeander) {
        this.header = responseHeander;
    }

    public List<StrategyDetail> getLoginStrategy() {
        return this.loginStrategy;
    }

    public void setLoginStrategy(List<StrategyDetail> list) {
        this.loginStrategy = list;
    }
}
