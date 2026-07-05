package com.sy37sdk.order.nat.trade;

import com.sqwan.common.mvp.IPresenter;
import com.sy37sdk.order.nat.bean.Coupon;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface ITradePresenter extends IPresenter {
    void checkWxPay();

    ArrayList<Coupon> getCoupons();

    void onFailure(int i, String str);

    void pay(String str);

    void paySuccess();

    void selectCoupon(Coupon coupon);

    void selectNoneCoupon();

    void selectPayWay(int i);

    void showAllPayWay();
}
