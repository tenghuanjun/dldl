package com.sy37sdk.order.nat.trade;

import com.sy37sdk.order.nat.IPayView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface ITradeView extends IPayView<ITradePresenter> {
    void selectPayWay(int i);

    void setWxPay(boolean z);

    void showAllPayWay(ArrayList<NativePayWay> arrayList);

    void showCoupon(String str);

    void showCouponNone();

    void showCoupons();

    void showOriginalNonePrice();

    void showOriginalPrice(String str);

    void showPayWay(List<NativePayWay> list, boolean z);

    void showProductName(String str);

    void showProductPrice(String str);

    void startLabor();
}
