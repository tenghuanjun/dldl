package com.sqwan.order.base;

import com.sq.sdk.tool.util.SqLogUtil;
import java.util.UUID;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PayContext {
    private static final String TAG = "【Pay Ctx】";
    private final String mAction;
    private String mCancelWay;
    private String mMoid;
    private JSONObject mOrderDataJson;
    private String mPayChannel;
    private PayExtraInfo mPayExtraInfo;
    private PayInfoModel mPayInfo;
    private String mRawOrderData;
    private PayWay mPayWay = PayWay.UNKNOWN;
    private final String mSession = UUID.randomUUID().toString();

    public PayContext(String str) {
        this.mAction = str;
    }

    public void setPayWay(PayWay payWay) {
        this.mPayWay = payWay;
    }

    public void setPayInfo(PayInfoModel payInfoModel) {
        PayInfoModel payInfoModel2 = this.mPayInfo;
        if (payInfoModel2 != null && payInfoModel2 != payInfoModel) {
            SqLogUtil.w("【Pay Ctx】重复设置PayInfo");
        }
        this.mPayInfo = payInfoModel;
    }

    public PayInfoModel getPayInfo() {
        return this.mPayInfo;
    }

    public PayWay getPayWay() {
        return this.mPayWay;
    }

    public String getAction() {
        return this.mAction;
    }

    public String getSession() {
        return this.mSession;
    }

    public String getMoid() {
        return this.mMoid;
    }

    public void setMoid(String str) {
        this.mMoid = str;
    }

    public String getRawOrderData() {
        return this.mRawOrderData;
    }

    public void setRawOrderData(String str) {
        this.mRawOrderData = str;
    }

    public JSONObject getOrderDataJson() {
        return this.mOrderDataJson;
    }

    public void setOrderDataJson(JSONObject jSONObject) {
        this.mOrderDataJson = jSONObject;
    }

    public String getPayChannel() {
        return this.mPayChannel;
    }

    public void setPayChannel(String str) {
        this.mPayChannel = str;
    }

    public String getCancelWay() {
        return this.mCancelWay;
    }

    public void setCancelWay(String str) {
        this.mCancelWay = str;
    }

    public PayExtraInfo getPayExtraInfo() {
        return this.mPayExtraInfo;
    }

    public void setPayExtraInfo(PayExtraInfo payExtraInfo) {
        this.mPayExtraInfo = payExtraInfo;
    }
}
