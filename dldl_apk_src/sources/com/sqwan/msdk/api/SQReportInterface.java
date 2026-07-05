package com.sqwan.msdk.api;

import android.content.Context;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface SQReportInterface {
    void afterPermission(Context context);

    void eventCpPay(String str);

    void eventPurchase(PurchaseReportBean purchaseReportBean);

    void eventRegister(RegisterReportBean registerReportBean);

    void init(Context context);

    void report(String str, Map<String, String> map);
}
