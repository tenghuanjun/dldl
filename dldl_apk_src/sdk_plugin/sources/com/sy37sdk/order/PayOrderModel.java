package com.sy37sdk.order;

import com.sqwan.order.base.PayInfoModel;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PayOrderModel {
    private String mMoid;
    private final PayInfoModel mPayInfoModel;
    private String mPaySession;

    public PayOrderModel(PayInfoModel payInfoModel) {
        this.mPayInfoModel = payInfoModel;
    }

    public String getPaySession() {
        return this.mPaySession;
    }

    public void setPaySession(String str) {
        this.mPaySession = str;
    }

    public PayInfoModel getPayInfoModel() {
        return this.mPayInfoModel;
    }

    public String getMoid() {
        return this.mMoid;
    }

    public void setMoid(String str) {
        this.mMoid = str;
    }

    public String toString() {
        return this.mPayInfoModel + "\n  moid=" + getMoid();
    }
}
