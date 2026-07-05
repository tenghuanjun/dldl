package com.sy37sdk.order.nat;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.mvp.BaseDialog;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.widget.BaseViewPager;
import com.sy37sdk.order.nat.PayPageSwitcher;
import com.sy37sdk.order.nat.bean.Order;
import com.sy37sdk.order.nat.coupon.CouponPresenter;
import com.sy37sdk.order.nat.coupon.CouponView;
import com.sy37sdk.order.nat.trade.TradePayPresenter;
import com.sy37sdk.order.nat.trade.TradeView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class NativePayDialog extends BaseDialog implements INativePayDialog {
    private CouponPresenter couponPresenter;
    private Bundle mBundle;
    private Context mContext;
    private int mFromIndex;
    private INativePayListener mPayListener;
    private List<BaseNativePayPresenter> mPayPagePresenterList;
    private int mToIndex;
    private Order order;
    private PayPageAdapter payPageAdapter;
    private PayPageSwitcher payPageSwitcher;
    private TradePayPresenter tradeViewPayPresenter;
    private BaseViewPager viewPager;

    public NativePayDialog(Context context, Order order, INativePayListener iNativePayListener) {
        super(context);
        this.mContext = context;
        this.mPayListener = iNativePayListener;
        this.order = order;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getIdByName("sysq_pay_dialog", "layout"));
        initView();
        Order order = this.order;
        if (order != null) {
            NatPayReporter.trackPayInit(order.getMoid(), this.order.getMoney() + "", "2", "1.0");
        }
    }

    private void initView() {
        BaseViewPager baseViewPager = (BaseViewPager) findViewById(getIdByName("pay_dialog_content", SqTrackCommonKey.id));
        this.viewPager = baseViewPager;
        baseViewPager.setPagingEnabled(false);
        this.payPageSwitcher = new PayPageSwitcher(this.viewPager);
        initPageViewList();
        PayPageAdapter payPageAdapter = new PayPageAdapter(this.mPayPagePresenterList);
        this.payPageAdapter = payPageAdapter;
        this.viewPager.setAdapter(payPageAdapter);
        this.viewPager.setOffscreenPageLimit(this.mPayPagePresenterList.size());
        this.payPageSwitcher.setPageScrollListener(new PayPageSwitcher.IPageScrollListener() { // from class: com.sy37sdk.order.nat.NativePayDialog.1
            @Override // com.sy37sdk.order.nat.PayPageSwitcher.IPageScrollListener
            public void scroll(int i, int i2, Bundle bundle) {
                NativePayDialog.this.mFromIndex = i;
                NativePayDialog.this.mToIndex = i2;
                NativePayDialog.this.mBundle = bundle;
                LogUtil.i("scroll 页面切换 from " + NativePayDialog.this.mFromIndex + " to " + NativePayDialog.this.mToIndex);
            }
        });
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sy37sdk.order.nat.NativePayDialog.2
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
                LogUtil.i("onPageScrolled " + i);
                ((BaseNativePayPresenter) NativePayDialog.this.mPayPagePresenterList.get(i)).onSwitched(NativePayDialog.this.mFromIndex, NativePayDialog.this.mToIndex, NativePayDialog.this.mBundle);
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                LogUtil.i("onPageSelected " + i);
            }
        });
    }

    private void initPageViewList() {
        this.mPayPagePresenterList = new ArrayList();
        TradePayPresenter tradePayPresenter = new TradePayPresenter(this.mContext, new TradeView(this.mContext, this));
        this.tradeViewPayPresenter = tradePayPresenter;
        tradePayPresenter.setPageSwitcher(this.payPageSwitcher);
        this.tradeViewPayPresenter.setPayListener(this.mPayListener);
        this.tradeViewPayPresenter.setOrder(this.order);
        this.mPayPagePresenterList.add(0, this.tradeViewPayPresenter);
        CouponPresenter couponPresenter = new CouponPresenter(this.mContext, new CouponView(this.mContext, this));
        this.couponPresenter = couponPresenter;
        couponPresenter.setPageSwitcher(this.payPageSwitcher);
        this.couponPresenter.setPayListener(this.mPayListener);
        this.mPayPagePresenterList.add(1, this.couponPresenter);
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        LogUtil.i("onBackPressed " + this.mToIndex);
        this.mPayPagePresenterList.get(this.mToIndex).onBackPressed();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        LogUtil.i("onWindowFocusChanged " + this.mToIndex);
        this.mPayPagePresenterList.get(this.mToIndex).onPayViewWindowFocusChanged(z);
    }

    @Override // com.sy37sdk.order.nat.INativePayDialog
    public void onSwitch(int i, Bundle bundle) {
        PayPageSwitcher payPageSwitcher = this.payPageSwitcher;
        if (payPageSwitcher != null) {
            payPageSwitcher.onSwitch(i, bundle);
        }
    }

    @Override // com.sy37sdk.order.nat.INativePayDialog
    public void closeAccountDialog(boolean z) {
        if (z) {
            NatPayReporter.trackPayClose(this.order.getMoid(), this.order.getMoney() + "", this.order.getPayAmount(), "2", this.order.getPayMethod(), "1.0", this.order.getIsVouchers(), this.order.getVouchersId());
            INativePayListener iNativePayListener = this.mPayListener;
            if (iNativePayListener != null) {
                iNativePayListener.onFailure(205, "取消支付【20005】");
            }
            TradePayPresenter tradePayPresenter = this.tradeViewPayPresenter;
            if (tradePayPresenter != null) {
                tradePayPresenter.reportPay(false);
            }
        }
        dismiss();
    }
}
