package com.sy37sdk.order.nat.coupon;

import android.content.Context;
import android.view.View;
import com.sy37sdk.order.nat.BaseNativePayPresenter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CouponPresenter extends BaseNativePayPresenter<ICouponView> implements ICouponPresenter {
    @Override // com.sy37sdk.order.nat.BasePayPresenter
    public int getBuglessPayErrorActionType() {
        return 106;
    }

    @Override // com.sy37sdk.order.nat.coupon.ICouponPresenter
    public void select() {
    }

    public CouponPresenter(Context context, ICouponView iCouponView) {
        super(context, iCouponView);
    }

    @Override // com.sqwan.common.mvp.BasePresenter, com.sqwan.common.mvp.IPresenter
    public void initData() {
        super.initData();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sy37sdk.order.nat.BaseNativePayPresenter
    public View getView() {
        return (View) this.mView;
    }
}
