package com.sqwan.common.request;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RspBeanWrapperBase {
    private String msg;
    private int state;

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public boolean isSuccess() {
        return this.state == 1;
    }
}
