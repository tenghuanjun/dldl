package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class UserStatusInfo {

    @SerializedName("Status")
    public int status;

    @SerializedName("UserID")
    public String userID;

    public boolean isOnline() {
        return this.status == 0;
    }
}
