package com.sqwan.msdk.api;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PurchaseReportBean {
    private boolean isSuccess;
    private int mCount;
    private int mPrice;
    private String mProductType = "be_null";
    private String mProductName = "be_null";
    private String mProductId = "be_null";
    private String mChannel = "be_null";
    private String mCurrency = "be_null";
    private String mOrderId = "be_null";

    public String getProductType() {
        return this.mProductType;
    }

    public PurchaseReportBean setProductType(String str) {
        this.mProductType = str;
        return this;
    }

    public String getProductName() {
        return this.mProductName;
    }

    public PurchaseReportBean setProductName(String str) {
        this.mProductName = str;
        return this;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public PurchaseReportBean setProductId(String str) {
        this.mProductId = str;
        return this;
    }

    public int getCount() {
        return this.mCount;
    }

    public PurchaseReportBean setCount(int i) {
        this.mCount = i;
        return this;
    }

    public String getChannel() {
        return this.mChannel;
    }

    public PurchaseReportBean setChannel(String str) {
        this.mChannel = str;
        return this;
    }

    public String getCurrency() {
        return this.mCurrency;
    }

    public PurchaseReportBean setCurrency(String str) {
        this.mCurrency = str;
        return this;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public PurchaseReportBean setSuccess(boolean z) {
        this.isSuccess = z;
        return this;
    }

    public int getPrice() {
        return this.mPrice;
    }

    public PurchaseReportBean setPrice(int i) {
        this.mPrice = i;
        return this;
    }

    public String getOrderId() {
        return this.mOrderId;
    }

    public PurchaseReportBean setOrderId(String str) {
        this.mOrderId = str;
        return this;
    }

    public String toString() {
        return "PurchaseReportBean{mProductType='" + this.mProductType + "', mProductName='" + this.mProductName + "', mProductId='" + this.mProductId + "', mCount=" + this.mCount + ", mChannel='" + this.mChannel + "', mCurrency='" + this.mCurrency + "', isSuccess=" + this.isSuccess + ", mPrice=" + this.mPrice + ", mOrderId='" + this.mOrderId + "'}";
    }
}
