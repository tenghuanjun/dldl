package com.huyaudbunify.bean.email;

import com.huyaudbunify.bean.ResponseHeander;
import com.huyaudbunify.bean.StrategyDetail;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HuyaBindLoginEmailRsp {
    ResponseHeander header;
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

    public String toString() {
        return "HuyaBindLoginEmailRsp{header=" + this.header + ", strategys=" + this.strategys + AbstractJsonLexerKt.END_OBJ;
    }
}
