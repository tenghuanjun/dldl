package com.aliyun.aliyunface.config;

import com.alibaba.fastjson.JSON;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class Protocol {
    public String content;
    public ProtocolContent protocolContent;
    public String sign;

    public void parse(String str) {
        ProtocolContent protocolContent = (ProtocolContent) JSON.parseObject(str, ProtocolContent.class);
        this.protocolContent = protocolContent;
        if (protocolContent != null) {
            protocolContent.parse(protocolContent.androidcfg);
        }
    }

    public boolean isValid() {
        return this.protocolContent.isValid();
    }
}
