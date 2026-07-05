package com.parameters.share;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class ShareMessage {
    private int platform;
    private IShareInfo shareMessage;
    private boolean skipPreview;

    public IShareInfo getShareMessage() {
        return this.shareMessage;
    }

    public void setShareMessage(IShareInfo iShareInfo) {
        this.shareMessage = iShareInfo;
    }

    public int getPlatform() {
        return this.platform;
    }

    public void setPlatform(int i) {
        this.platform = i;
    }

    public boolean isSkipPreview() {
        return this.skipPreview;
    }

    public void setSkipPreview(boolean z) {
        this.skipPreview = z;
    }
}
