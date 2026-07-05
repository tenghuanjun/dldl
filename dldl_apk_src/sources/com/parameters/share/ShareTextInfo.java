package com.parameters.share;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class ShareTextInfo implements IShareInfo {
    private String text;

    @Override // com.parameters.share.IShareInfo
    public int classify() {
        return 3;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }
}
