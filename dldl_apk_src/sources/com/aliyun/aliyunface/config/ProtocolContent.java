package com.aliyun.aliyunface.config;

import com.alibaba.fastjson.JSON;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ProtocolContent {
    public AndroidClientConfig androidClientConfig;
    public String androidcfg;
    public String expireTime;
    public int sampleMode;
    public String token;
    public int type;

    public void parse(String str) {
        this.androidClientConfig = (AndroidClientConfig) JSON.parseObject(str, AndroidClientConfig.class);
    }

    public boolean isValid() {
        return this.androidClientConfig != null;
    }
}
