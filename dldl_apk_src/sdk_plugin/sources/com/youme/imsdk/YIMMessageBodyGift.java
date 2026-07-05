package com.youme.imsdk;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMMessageBodyGift implements IYIMMessageBodyBase {
    private String anchor;
    private YIMExtraGifParam extParam;
    private Integer giftCount;
    private Integer giftID;

    public Integer getGiftID() {
        return this.giftID;
    }

    public void setGiftID(Integer num) {
        this.giftID = num;
    }

    public Integer getGiftCount() {
        return this.giftCount;
    }

    public void setGiftCount(Integer num) {
        this.giftCount = num;
    }

    public String getAnchor() {
        return this.anchor;
    }

    public void setAnchor(String str) {
        this.anchor = str;
    }

    public YIMExtraGifParam getExtParam() {
        return this.extParam;
    }

    public void setExtParam(YIMExtraGifParam yIMExtraGifParam) {
        this.extParam = yIMExtraGifParam;
    }
}
