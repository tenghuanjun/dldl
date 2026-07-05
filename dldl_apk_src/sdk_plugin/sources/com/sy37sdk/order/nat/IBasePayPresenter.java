package com.sy37sdk.order.nat;

import android.webkit.WebView;
import com.sqwan.common.mvp.IPresenter;
import com.sy37sdk.order.nat.bean.Order;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IBasePayPresenter extends IPresenter {
    void checkDouYinWebPay();

    void checkWxPay();

    boolean isMatchDouYinPaySchemePrefix(String str);

    void reportPay(boolean z);

    void setAliOrderId(String str);

    void setCurrentPayMethod(String str);

    void setDouYinOrderId(String str);

    void setOrder(Order order);

    void setWxOrderId(String str);

    void toAlipay(String str, boolean z);

    void toDouYinPaySchemePage(String str);

    void toDouYinWebPay(WebView webView, String str, String str2);

    void toUnionPay(String str);

    void toWxPay(String str);
}
