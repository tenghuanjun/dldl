package com.sqwan.msdk.api;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class RegisterReportBean {
    private boolean isSuccess;
    private String mType;

    public RegisterReportBean(String str, boolean z) {
        this.mType = str;
        this.isSuccess = z;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean z) {
        this.isSuccess = z;
    }
}
