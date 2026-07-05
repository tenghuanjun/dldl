package com.huyaudbunify.bean.email;

import com.huyaudbunify.bean.LoginData;
import com.huyaudbunify.bean.ResponseHeander;
import com.huyaudbunify.bean.StrategyDetail;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaEmailRegisterRsp {
    ResponseHeander header;
    LoginData loginData;
    String sessionData;
    List<StrategyDetail> strategys = new ArrayList();

    public ResponseHeander getHeader() {
        return this.header;
    }

    public void setHeader(ResponseHeander responseHeander) {
        this.header = responseHeander;
    }

    public List<StrategyDetail> getStrategys() {
        return this.strategys;
    }

    public void setStrategys(List<StrategyDetail> list) {
        this.strategys = list;
    }

    public String getSessionData() {
        return this.sessionData;
    }

    public void setSessionData(String str) {
        this.sessionData = str;
    }

    public LoginData getLoginData() {
        return this.loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    public String toString() {
        return "HuyaEmailRegisterRsp{header=" + this.header + ", strategys=" + this.strategys + ", sessionData='" + this.sessionData + '\'' + AbstractJsonLexerKt.END_OBJ;
    }
}
