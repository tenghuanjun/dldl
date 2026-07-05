package com.huyaudbunify.bean.email;

import com.huyaudbunify.bean.ResponseHeander;
import com.huyaudbunify.bean.StrategyDetail;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaEmailIsRegRes {
    ResponseHeander header;
    Boolean isRegist;
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

    public Boolean getRegist() {
        return this.isRegist;
    }

    public void setRegist(Boolean bool) {
        this.isRegist = bool;
    }
}
