package com.youme.imsdk.internal;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class BlockUserInfo {

    @SerializedName("Block")
    private Integer block;

    @SerializedName("UserID")
    private String userID;

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public boolean getBlock() {
        return this.block.intValue() == 1;
    }

    public void setBlock(boolean z) {
        this.block = Integer.valueOf(!z ? 0 : 1);
    }
}
