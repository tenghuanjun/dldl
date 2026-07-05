package com.sy37sdk.order.nat.coupon;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sy37sdk.order.nat.BasePayView;
import com.sy37sdk.order.nat.INativePayDialog;
import com.sy37sdk.order.nat.PayBundleKey;
import com.sy37sdk.order.nat.bean.Coupon;
import com.sy37sdk.order.nat.coupon.CouponAdapter;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CouponView extends BasePayView implements ICouponView {
    private CouponAdapter couponAdapter;
    private ICouponPresenter couponPresenter;
    private List<Coupon> coupons;
    private ImageView ivNotUseCoupon;
    private ListView lvCoupon;
    private Context mContext;
    private View notUseCoupon;
    private View rlNotUseCouponContainer;
    private Coupon selectCoupon;
    private int selectPos;
    private boolean useCoupon;

    @Override // com.sy37sdk.order.nat.BasePayView
    public String getLayoutResName() {
        return "sysq_pay_dialog_coupon";
    }

    @Override // com.sy37sdk.order.nat.BasePayView, com.sqwan.common.mvp.ILoadView
    public void hideLoading() {
    }

    @Override // com.sy37sdk.order.nat.BasePayView, com.sqwan.common.mvp.ILoadView
    public void showLoading() {
    }

    public CouponView(Context context, INativePayDialog iNativePayDialog) {
        super(context);
        this.selectPos = 0;
        this.useCoupon = true;
        this.mContext = context;
        this.nativePayDialog = iNativePayDialog;
    }

    @Override // com.sy37sdk.order.nat.BasePayView
    public String getTitle() {
        return SqResUtils.getStringByName(this.mContext, "sysq_pay_trade_coupon");
    }

    @Override // com.sy37sdk.order.nat.BasePayView
    public void initView() {
        setLeftResDrawable("sysq_ic_pay_back");
        this.rlNotUseCouponContainer = getViewByName(this.rootView, "rl_not_use_coupon_container");
        this.notUseCoupon = getViewByName(this.rootView, "not_use_coupon");
        this.ivNotUseCoupon = (ImageView) getViewByName(this.rootView, "iv_not_use_coupon");
        ListView listView = (ListView) getViewByName(this.rootView, "lv_coupon");
        this.lvCoupon = listView;
        listView.setFocusable(false);
        this.lvCoupon.setVerticalScrollBarEnabled(false);
        CouponAdapter couponAdapter = new CouponAdapter(this.mContext);
        this.couponAdapter = couponAdapter;
        this.lvCoupon.setAdapter((ListAdapter) couponAdapter);
    }

    @Override // com.sy37sdk.order.nat.BasePayView
    public void initEvent() {
        this.rlNotUseCouponContainer.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.order.nat.coupon.CouponView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CouponView.this.ivNotUseCoupon.setImageResource(SqResUtils.getDrawableId(CouponView.this.mContext, "sysq_ic_pay_selected"));
                CouponView.this.selectCoupon(-1);
                CouponView.this.back();
            }
        });
        this.couponAdapter.setSelectListener(new CouponAdapter.SelectListener() { // from class: com.sy37sdk.order.nat.coupon.CouponView.2
            @Override // com.sy37sdk.order.nat.coupon.CouponAdapter.SelectListener
            public void onSelect(int i) {
                CouponView.this.selectCoupon(i);
                CouponView.this.back();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectCoupon(int i) {
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= this.coupons.size()) {
                break;
            }
            Coupon coupon = this.coupons.get(i2);
            if (i2 != i) {
                z = false;
            }
            coupon.setSelect(z);
            i2++;
        }
        this.selectPos = i;
        if (i == -1) {
            this.selectCoupon = null;
            this.useCoupon = false;
        } else {
            this.selectCoupon = this.coupons.get(i);
            this.useCoupon = true;
        }
        this.couponAdapter.setDatas(this.coupons);
    }

    @Override // com.sy37sdk.order.nat.BasePayView, com.sy37sdk.order.nat.IPageSwitchView
    public void onSwitched(int i, int i2, Bundle bundle) {
        Context context;
        String str;
        super.onSwitched(i, i2, bundle);
        LogUtil.i("onSwitched CouponView " + i + "   " + i2);
        ImageView imageView = this.ivNotUseCoupon;
        if (this.useCoupon) {
            context = this.mContext;
            str = "sysq_ic_pay_select";
        } else {
            context = this.mContext;
            str = "sysq_ic_pay_selected";
        }
        imageView.setImageResource(SqResUtils.getDrawableId(context, str));
        if (bundle != null) {
            this.coupons = bundle.getParcelableArrayList(PayBundleKey.KEY_COUPON);
            selectCoupon(this.selectPos);
        }
    }

    @Override // com.sy37sdk.order.nat.BasePayView
    protected void leftViewClicked() {
        back();
    }

    @Override // com.sy37sdk.order.nat.IPayView
    public void setPresenter(ICouponPresenter iCouponPresenter) {
        this.couponPresenter = iCouponPresenter;
    }

    @Override // com.sy37sdk.order.nat.BasePayView, com.sy37sdk.order.nat.IPageSwitchView
    public void onBackPressed() {
        back();
    }

    @Override // com.sy37sdk.order.nat.coupon.ICouponView
    public void back() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(PayBundleKey.KEY_USE_COUPON, this.useCoupon);
        if (this.useCoupon) {
            bundle.putParcelable(PayBundleKey.KEY_SELECT_COUPON, this.selectCoupon);
        }
        this.nativePayDialog.onSwitch(0, bundle);
    }
}
