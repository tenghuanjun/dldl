package com.huyaudbunify.msg;

import com.google.gson.Gson;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MsgBase<T> {
    protected T mMsgData = null;

    public String toString() {
        return this.mMsgData == null ? "" : new Gson().toJson(this.mMsgData);
    }

    public T getData() {
        return this.mMsgData;
    }
}
