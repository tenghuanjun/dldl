package com.sy37sdk.order.nat.trade;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class NativePayWay {
    private static final int CLOSE = 0;
    private static final int OPEN = 1;
    public static final String PAY_WAY_ALIPAY = "alipay";
    public static final String PAY_WAY_HUABEI = "hbpay";
    public static final String PAY_WAY_LABOR = "labor";
    public static final String PAY_WAY_WALLET = "wtpay";
    public static final String PAY_WAY_WECHAT = "wxpay";
    public static final String PWAY_KEY_ALIPAY = "alipay";
    public static final String PWAY_KEY_HUABEI = "huabei";
    public static final String PWAY_KEY_WALLET = "wallet";
    public static final String PWAY_KEY_WECHAT = "wechat";
    private String extra;
    private boolean isSelected;
    private String key;
    private String open;
    private String pWayTip;
    private String promote;
    private String resId;
    private String way;
    private String wayChinese;

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getResId() {
        return this.resId;
    }

    public void setResId(String str) {
        this.resId = str;
    }

    public String getPWayTip() {
        return this.pWayTip;
    }

    public void setPWayTip(String str) {
        this.pWayTip = str;
    }

    public String getPromote() {
        return this.promote;
    }

    public void setPromote(String str) {
        this.promote = str;
    }

    public String getWayChinese() {
        return this.wayChinese;
    }

    public void setWayChinese(String str) {
        this.wayChinese = str;
    }

    public String getWay() {
        return this.way;
    }

    public void setWay(String str) {
        this.way = str;
    }

    public String getOpen() {
        return this.open;
    }

    public void setOpen(String str) {
        this.open = str;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public String toString() {
        return "NativePayWay{way='" + this.wayChinese + "', open='" + this.open + '\'' + AbstractJsonLexerKt.END_OBJ;
    }

    public boolean equals(Object obj) {
        if (obj instanceof NativePayWay) {
            return this.key.equals(((NativePayWay) obj).getKey());
        }
        return super.equals(obj);
    }
}
