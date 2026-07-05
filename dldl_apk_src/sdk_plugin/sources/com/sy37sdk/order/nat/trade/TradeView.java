package com.sy37sdk.order.nat.trade;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.web.SY37web;
import com.sqwan.msdk.config.ConfigManager;
import com.sy37sdk.order.nat.BasePayView;
import com.sy37sdk.order.nat.INativePayDialog;
import com.sy37sdk.order.nat.PayBundleKey;
import com.sy37sdk.order.nat.bean.Coupon;
import com.sy37sdk.order.nat.trade.PayWayAdapter;
import com.sy37sdk.order.view.PayConfirmDialog;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class TradeView extends BasePayView implements ITradeView {
    private View allPayWay;
    private View couponLayout;
    private boolean isWxPayClick;
    private ListView lvPayWay;
    private Context mContext;
    private PayWayAdapter payWayAdapter;
    private int selectPayWayPos;
    private ITradePresenter tradePresenter;
    private TextView tvCoupon;
    private TextView tvOriginalPrice;
    private TextView tvPay;
    private TextView tvPrice;
    private TextView tvProductName;

    public TradeView(Context context, INativePayDialog iNativePayDialog) {
        super(context);
        this.mContext = context;
        this.nativePayDialog = iNativePayDialog;
    }

    @Override // com.sy37sdk.order.nat.IPayView
    public void setPresenter(ITradePresenter iTradePresenter) {
        this.tradePresenter = iTradePresenter;
    }

    @Override // com.sy37sdk.order.nat.BasePayView, com.sy37sdk.order.nat.IPageSwitchView
    public void onSwitched(int i, int i2, Bundle bundle) {
        LogUtil.i("onSwitched TradeView " + i + "   " + i2);
        if (bundle == null) {
            return;
        }
        if (bundle.getBoolean(PayBundleKey.KEY_USE_COUPON)) {
            this.tradePresenter.selectCoupon((Coupon) bundle.getParcelable(PayBundleKey.KEY_SELECT_COUPON));
        } else {
            this.tradePresenter.selectNoneCoupon();
        }
    }

    @Override // com.sy37sdk.order.nat.BasePayView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.tradePresenter.initData();
    }

    @Override // com.sy37sdk.order.nat.BasePayView
    public String getTitle() {
        return SqResUtils.getStringByName(this.mContext, "sysq_pay_cashier");
    }

    @Override // com.sy37sdk.order.nat.BasePayView
    public void initView() {
        this.tvPay = (TextView) getViewByName(this.rootView, "tv_pay_sure");
        this.couponLayout = getViewByName(this.rootView, "coupon_layout");
        this.allPayWay = getViewByName(this.rootView, "pay_way_more_layout");
        this.tvProductName = (TextView) getViewByName(this.rootView, "product_name");
        this.tvPrice = (TextView) getViewByName(this.rootView, "tv_price");
        TextView textView = (TextView) getViewByName(this.rootView, "tv_original_price");
        this.tvOriginalPrice = textView;
        textView.setPaintFlags(17);
        this.tvCoupon = (TextView) getViewByName(this.rootView, "tv_cupon");
        ListView listView = (ListView) getViewByName(this.rootView, "lv_pay_way");
        this.lvPayWay = listView;
        listView.setCacheColorHint(-1);
        this.lvPayWay.setDivider(null);
        this.lvPayWay.setFocusable(false);
        this.lvPayWay.setVerticalScrollBarEnabled(false);
        PayWayAdapter payWayAdapter = new PayWayAdapter(this.mContext);
        this.payWayAdapter = payWayAdapter;
        payWayAdapter.setIsShowDivider(!isLandscape());
        this.lvPayWay.setAdapter((ListAdapter) this.payWayAdapter);
        setLeftVisible(!ConfigManager.getInstance(getContext()).isSplashSDK());
    }

    @Override // com.sy37sdk.order.nat.BasePayView
    public void initEvent() {
        this.couponLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.order.nat.trade.TradeView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TradeView.this.tradePresenter.getCoupons() == null || TradeView.this.tradePresenter.getCoupons().size() <= 0) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(PayBundleKey.KEY_COUPON, TradeView.this.tradePresenter.getCoupons());
                TradeView.this.nativePayDialog.onSwitch(1, bundle);
            }
        });
        this.allPayWay.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.order.nat.trade.TradeView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TradeView.this.tradePresenter.showAllPayWay();
            }
        });
        this.payWayAdapter.setSelectListener(new PayWayAdapter.SelectListener() { // from class: com.sy37sdk.order.nat.trade.TradeView.3
            @Override // com.sy37sdk.order.nat.trade.PayWayAdapter.SelectListener
            public void select(int i) {
                TradeView.this.refreshPayWays(i);
            }
        });
        this.tvPay.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.order.nat.trade.TradeView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String way = TradeView.this.getSelectPayWay() != null ? TradeView.this.getSelectPayWay().getWay() : NativePayWay.PAY_WAY_WECHAT;
                if (way.equals(NativePayWay.PAY_WAY_WECHAT) && !TradeView.this.isWxPayClick) {
                    TradeView.this.isWxPayClick = true;
                }
                TradeView.this.tradePresenter.pay(way);
            }
        });
    }

    public NativePayWay getSelectPayWay() {
        List<NativePayWay> datas = this.payWayAdapter.getDatas();
        if (datas == null || datas.size() <= 0) {
            return null;
        }
        return datas.get(this.selectPayWayPos);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshPayWays(int i) {
        List<NativePayWay> datas = this.payWayAdapter.getDatas();
        if (datas == null || datas.size() <= 0) {
            return;
        }
        int i2 = 0;
        while (i2 < datas.size()) {
            datas.get(i2).setSelected(i2 == i);
            i2++;
        }
        this.payWayAdapter.setDatas(datas);
        this.selectPayWayPos = i;
    }

    @Override // com.sy37sdk.order.nat.BasePayView
    public String getLayoutResName() {
        return isLandscape() ? "sysq_pay_dialog_landscape" : "sysq_pay_dialog_portrait";
    }

    @Override // com.sy37sdk.order.nat.trade.ITradeView
    public void showProductName(String str) {
        TextView textView = this.tvProductName;
        if (textView != null) {
            textView.setText(str);
        }
    }

    @Override // com.sy37sdk.order.nat.trade.ITradeView
    public void showProductPrice(String str) {
        TextView textView = this.tvPrice;
        if (textView != null) {
            textView.setText("￥" + str);
        }
        TextView textView2 = this.tvPay;
        if (textView2 != null) {
            textView2.setText("确认支付 ￥" + str);
        }
    }

    @Override // com.sy37sdk.order.nat.trade.ITradeView
    public void showOriginalPrice(String str) {
        TextView textView = this.tvOriginalPrice;
        if (textView != null) {
            textView.setText("￥" + str);
            this.tvOriginalPrice.setVisibility(0);
        }
    }

    @Override // com.sy37sdk.order.nat.trade.ITradeView
    public void showOriginalNonePrice() {
        TextView textView = this.tvOriginalPrice;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    @Override // com.sy37sdk.order.nat.trade.ITradeView
    public void showCoupon(String str) {
        TextView textView = this.tvCoupon;
        if (textView != null) {
            textView.setText("-￥" + str);
            this.tvCoupon.setTextColor(Color.parseColor("#F54B4B"));
        }
    }

    @Override // com.sy37sdk.order.nat.trade.ITradeView
    public void showCouponNone() {
        TextView textView = this.tvCoupon;
        if (textView != null) {
            textView.setText("不使用代金券");
            this.tvCoupon.setTextColor(Color.parseColor("#999999"));
        }
    }

    @Override // com.sy37sdk.order.nat.trade.ITradeView
    public void showPayWay(List<NativePayWay> list, boolean z) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.allPayWay.setVisibility(z ? 0 : 8);
        this.payWayAdapter.setDatas(list);
        refreshPayWays(0);
    }

    @Override // com.sy37sdk.order.nat.trade.ITradeView
    public void showAllPayWay(ArrayList<NativePayWay> arrayList) {
        int iIndexOf;
        NativePayWay nativePayWay = this.payWayAdapter.getDatas().get(this.selectPayWayPos);
        if (nativePayWay != null && (iIndexOf = arrayList.indexOf(nativePayWay)) != -1) {
            this.selectPayWayPos = iIndexOf;
        }
        this.allPayWay.setVisibility(8);
        this.payWayAdapter.setDatas(arrayList);
    }

    @Override // com.sy37sdk.order.nat.trade.ITradeView
    public void selectPayWay(int i) {
        refreshPayWays(i);
        this.lvPayWay.setSelection(i);
        this.lvPayWay.smoothScrollToPosition(i);
    }

    @Override // com.sy37sdk.order.nat.trade.ITradeView
    public void showCoupons() {
        this.couponLayout.setVisibility(0);
    }

    @Override // com.sy37sdk.order.nat.BasePayView, com.sy37sdk.order.nat.IPageSwitchView
    public void onBackPressed() {
        LogUtil.i("TradeView onBackPressed ");
        new PayConfirmDialog.Builder(this.mContext).setTitle(SqResUtils.getStringByName(this.mContext, "sysq_pay_cancel_tip")).setCancelable(false).setNegativeButton(SqResUtils.getStringByName(this.mContext, "sysq_pay_cancel_sure"), new View.OnClickListener() { // from class: com.sy37sdk.order.nat.trade.TradeView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TradeView.this.nativePayDialog != null) {
                    TradeView.this.nativePayDialog.closeAccountDialog(true);
                }
            }
        }).setPositiveButton(SqResUtils.getStringByName(this.mContext, "sysq_pay_cancel_continue"), null).show();
    }

    @Override // com.sy37sdk.order.nat.BasePayView
    protected void leftViewClicked() {
        showPayConfirmDialog(null, null, null);
    }

    @Override // com.sy37sdk.order.nat.trade.ITradeView
    public void startLabor() {
        if (this.nativePayDialog != null) {
            Bundle bundle = new Bundle();
            bundle.putString(PayBundleKey.KEY_LABOR_CONTENT, getSelectPayWay() != null ? getSelectPayWay().getPWayTip() : "");
            this.nativePayDialog.onSwitch(2, bundle);
        }
    }

    @Override // com.sy37sdk.order.nat.trade.ITradeView
    public void setWxPay(boolean z) {
        this.isWxPayClick = z;
    }

    @Override // com.sy37sdk.order.nat.BasePayView, com.sy37sdk.order.nat.IPageSwitchView
    public void onPayViewWindowFocusChanged(boolean z) {
        if (z && this.isWxPayClick) {
            this.tradePresenter.checkWxPay();
        }
    }

    @Override // com.sy37sdk.order.nat.BasePayView, com.sy37sdk.order.nat.IBasePayView
    public void paySuccess() {
        showToast("支付成功！");
        if (this.isWxPayClick) {
            this.isWxPayClick = false;
        }
        this.tradePresenter.paySuccess();
        if (this.nativePayDialog != null) {
            this.nativePayDialog.closeAccountDialog(false);
        }
    }

    @Override // com.sy37sdk.order.nat.BasePayView, com.sy37sdk.order.nat.IBasePayView
    public void onFailure(int i, String str) {
        if (this.isWxPayClick) {
            this.isWxPayClick = false;
        }
        this.tradePresenter.onFailure(i, str);
        View viewInflate = LayoutInflater.from(this.mContext).inflate(SqResUtils.getLayoutId(this.mContext, "sysq_pay_dialog_head_failure"), (ViewGroup) null);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sy37sdk.order.nat.trade.TradeView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TradeView.this.nativePayDialog != null) {
                    TradeView.this.nativePayDialog.closeAccountDialog(false);
                }
            }
        };
        if (ConfigManager.getInstance(getContext()).isSplashSDK()) {
            ToastUtil.showToast(str);
            if (this.nativePayDialog != null) {
                this.nativePayDialog.closeAccountDialog(false);
                return;
            }
            return;
        }
        showPayConfirmDialog(viewInflate, onClickListener, onClickListener);
    }

    private void showPayConfirmDialog(View view, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        final String str = ConfigManager.getInstance(getContext()).isSplashSDK() ? "玩心俱乐部" : "37手游俱乐部";
        new PayConfirmDialog.Builder(this.mContext).setHeadView(view).setTitle("支付问题联系客服\n关注公众号【" + str + "】").setCancelable(false).setNegativeButton("取消", new View.OnClickListener() { // from class: com.sy37sdk.order.nat.trade.TradeView.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                View.OnClickListener onClickListener3 = onClickListener;
                if (onClickListener3 != null) {
                    onClickListener3.onClick(view2);
                }
            }
        }).setPositiveButton("复制公众号", new View.OnClickListener() { // from class: com.sy37sdk.order.nat.trade.TradeView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SY37web.copyString2System(TradeView.this.mContext, str, "复制成功");
                View.OnClickListener onClickListener3 = onClickListener2;
                if (onClickListener3 != null) {
                    onClickListener3.onClick(view2);
                }
            }
        }).show();
    }
}
