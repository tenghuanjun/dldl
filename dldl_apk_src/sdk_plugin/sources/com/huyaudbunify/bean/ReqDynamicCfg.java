package com.huyaudbunify.bean;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ReqDynamicCfg {
    Map<String, String> mpParams = new HashMap();
    long uid;

    public long getUid() {
        return this.uid;
    }

    public void setUid(long j) {
        this.uid = j;
    }

    public Map<String, String> getMpParams() {
        return this.mpParams;
    }

    public void setMpParams(Map<String, String> map) {
        this.mpParams = map;
    }
}
