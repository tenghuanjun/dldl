package com.huya.live.common.api;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BaseCallback {
    protected Status mCallBackStatus;

    public enum Status {
        SUCCESS,
        ERROR,
        NONE
    }

    public BaseCallback(Status status) {
        this.mCallBackStatus = Status.SUCCESS;
        this.mCallBackStatus = status;
    }

    public Status getStatus() {
        return this.mCallBackStatus;
    }
}
