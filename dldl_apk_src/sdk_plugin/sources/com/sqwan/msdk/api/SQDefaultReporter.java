package com.sqwan.msdk.api;

import android.content.Context;
import com.sqwan.common.util.LogUtil;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQDefaultReporter implements SQReportInterface {
    public void init(Context context) {
        LogUtil.i("sq default reporter --> init");
    }

    public void afterPermission(Context context) {
        LogUtil.i("sq default reporter --> afterPermission");
    }

    public void eventRegister(RegisterReportBean registerReportBean) {
        LogUtil.i("sq default reporter --> eventRegister type: " + registerReportBean.getType() + ", success" + registerReportBean.isSuccess());
    }

    public void eventPurchase(PurchaseReportBean purchaseReportBean) {
        LogUtil.i("sq default reporter --> eventPurchase orderId: " + purchaseReportBean.getOrderId() + ", success" + purchaseReportBean.isSuccess());
    }

    public void eventCpPay(String str) {
        LogUtil.i("sq default reporter --> eventCpPay json : " + str);
    }

    public void report(String str, Map<String, String> map) {
        LogUtil.i("sq default reporter --> report event: " + str);
    }
}
