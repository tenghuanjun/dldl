package com.huya.hysignal.wrapper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RegistResultInfo {
    public static final int PUSH_REGISTER_FAIL = 1;
    public static final int PUSH_REGISTER_SUCCESS = 0;
    String error;
    String groupId;
    int status;

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public RegistResultInfo(int i) {
        this.groupId = "";
        this.status = 0;
        this.error = "";
        this.status = i;
    }

    public RegistResultInfo(String str, int i, String str2) {
        this.groupId = "";
        this.status = 0;
        this.error = "";
        this.groupId = str;
        this.status = i;
        this.error = str2;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String str) {
        this.error = str;
    }
}
