package com.parameters.share;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class DefaultShareInfo implements IShareInfo {
    private String imgId;
    private String inviteCode;

    @Override // com.parameters.share.IShareInfo
    public int classify() {
        return 0;
    }

    public String getInviteCode() {
        return this.inviteCode;
    }

    public void setInviteCode(String str) {
        this.inviteCode = str;
    }

    public String getImgId() {
        return this.imgId;
    }

    public void setImgId(String str) {
        this.imgId = str;
    }
}
