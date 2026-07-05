package com.aliyun.aliyunface.network.model;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class BisBehavLog {
    public BisBehavCommon behavCommon;
    public List<BisBehavTask> behavTask;
    public BisBehavToken behavToken;
    public BisClientInfo clientInfo;
    public Map<String, String> extAttr;

    public BisClientInfo getClientInfo() {
        return this.clientInfo;
    }

    public void setClientInfo(BisClientInfo bisClientInfo) {
        this.clientInfo = bisClientInfo;
    }

    public BisBehavToken getBehavToken() {
        return this.behavToken;
    }

    public void setBehavToken(BisBehavToken bisBehavToken) {
        this.behavToken = bisBehavToken;
    }

    public BisBehavCommon getBehavCommon() {
        return this.behavCommon;
    }

    public void setBehavCommon(BisBehavCommon bisBehavCommon) {
        this.behavCommon = bisBehavCommon;
    }

    public List<BisBehavTask> getBehavTask() {
        return this.behavTask;
    }

    public void setBehavTask(List<BisBehavTask> list) {
        this.behavTask = list;
    }

    public Map<String, String> getExtAttr() {
        return this.extAttr;
    }

    public void setExtAttr(Map<String, String> map) {
        this.extAttr = map;
    }
}
