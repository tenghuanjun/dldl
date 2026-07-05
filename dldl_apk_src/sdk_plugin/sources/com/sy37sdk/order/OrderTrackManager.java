package com.sy37sdk.order;

import com.sq.tool.logger.SQLog;
import com.sqwan.msdk.SQReportCore;
import com.sqwan.msdk.api.PurchaseReportBean;
import com.sqwan.order.base.PayInfoModel;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class OrderTrackManager {
    public static void reportToMedia(PayInfoModel payInfoModel, String str, boolean z) {
        if (payInfoModel == null) {
            return;
        }
        PurchaseReportBean purchaseReportBean = new PurchaseReportBean();
        purchaseReportBean.setCurrency("RMB");
        purchaseReportBean.setProductName(payInfoModel.getProductName());
        purchaseReportBean.setPrice((int) payInfoModel.getMoney());
        purchaseReportBean.setOrderId(str);
        purchaseReportBean.setCount(1);
        purchaseReportBean.setSuccess(z);
        SQLog.i("支付结果上报媒体: " + purchaseReportBean);
        SQReportCore.getInstance().eventPurchase(purchaseReportBean);
    }
}
