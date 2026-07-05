package com.sy37sdk.order.nat.trade;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.util.Base64;
import com.sqwan.common.util.EncryptUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SDKError;
import com.sqwan.common.util.SpUtils;
import com.sqwan.common.webview.SQWeb;
import com.sqwan.common.webview.SQWebView;
import com.sqwan.common.webview.UrlWebHook;
import com.sy37sdk.order.nat.BaseNativePayPresenter;
import com.sy37sdk.order.nat.bean.Coupon;
import com.sy37sdk.order.nat.bean.Order;
import com.sy37sdk.order.nat.trade.OrderData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class TradePayPresenter extends BaseNativePayPresenter<ITradeView> implements ITradePresenter {
    private static final int DEFAULT_SHOW_PAY_WAY_NUMBER = 2;
    private static final String LAST_SUCCESS_PAY_WAY = "LAST_SUCCESS_PAY_WAY";
    ArrayList<Coupon> mCoupons;
    Order mOrder;
    ArrayList<NativePayWay> nativePayWays;
    OrderData orderLogic;
    private String pway;
    private Coupon selectCoupon;
    String wxOrderId;

    @Override // com.sy37sdk.order.nat.BasePayPresenter
    public int getBuglessPayErrorActionType() {
        return 106;
    }

    @Override // com.sy37sdk.order.nat.trade.ITradePresenter
    public void selectPayWay(int i) {
    }

    public TradePayPresenter(Context context, ITradeView iTradeView) {
        super(context, iTradeView);
        iTradeView.setPresenter(this);
        this.orderLogic = OrderData.getInstance(context);
    }

    @Override // com.sy37sdk.order.nat.BasePayPresenter, com.sy37sdk.order.nat.IBasePayPresenter
    public void setOrder(Order order) {
        this.mOrder = order;
        super.setOrder(order);
    }

    @Override // com.sqwan.common.mvp.BasePresenter, com.sqwan.common.mvp.IPresenter
    public void initData() {
        super.initData();
        showView();
        initPayWay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<NativePayWay> defaultPayWays() {
        ArrayList<NativePayWay> arrayList = new ArrayList<>();
        arrayList.add(defaultNativePayWay(NativePayWay.PWAY_KEY_WECHAT));
        arrayList.add(defaultNativePayWay("alipay"));
        return arrayList;
    }

    private NativePayWay defaultNativePayWay(String str) {
        NativePayWay nativePayWay = new NativePayWay();
        nativePayWay.setKey(str);
        nativePayWay.setOpen("1");
        nativePayWay.setWayChinese(PayJsonParser.getWayChineseByKey(str));
        nativePayWay.setWay(PayJsonParser.getWayByKey(str));
        nativePayWay.setPromote("");
        nativePayWay.setResId(PayJsonParser.getResByKey(str));
        nativePayWay.setPWayTip("");
        return nativePayWay;
    }

    private void showView() {
        if (this.mView == 0 || this.mOrder == null) {
            return;
        }
        ((ITradeView) this.mView).showProductName(this.mOrder.getDpt());
        ((ITradeView) this.mView).showProductPrice(this.mOrder.getMoney() + "");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sy37sdk.order.nat.BaseNativePayPresenter
    public View getView() {
        return (View) this.mView;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003f  */
    @Override // com.sy37sdk.order.nat.trade.ITradePresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void pay(java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 366
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy37sdk.order.nat.trade.TradePayPresenter.pay(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void walletPay() {
        this.orderLogic.walletPay(this.mOrder, new OrderData.PayCallback<Boolean>() { // from class: com.sy37sdk.order.nat.trade.TradePayPresenter.2
            @Override // com.sy37sdk.order.nat.trade.OrderData.PayCallback
            public void onSuccess(Boolean bool) {
                TradePayPresenter.this.reportPay(true);
                if (TradePayPresenter.this.mView != null) {
                    ((ITradeView) TradePayPresenter.this.mView).showToast("支付成功！");
                    ((ITradeView) TradePayPresenter.this.mView).paySuccess();
                }
            }

            @Override // com.sy37sdk.order.nat.trade.OrderData.PayCallback
            public void onFailure(int i, String str) {
                LogUtil.e("支付失败code:" + i + ",msg:" + str);
                TradePayPresenter.this.reportPay(false);
                BuglessAction.reportCatchException(new Exception(), "检查到 " + TradePayPresenter.this.pway + " 订单支付失败，code = " + SDKError.PAY_FAIL.code + "，msg = " + SDKError.PAY_FAIL.message, TradePayPresenter.this.getBuglessPayErrorActionType());
                if (TradePayPresenter.this.mView != null) {
                    ((ITradeView) TradePayPresenter.this.mView).showToast(str);
                }
            }
        });
    }

    private void otherPay(String str) {
        boolean zEquals = str.equals(NativePayWay.PAY_WAY_HUABEI);
        if (zEquals) {
            str = "alipay";
        }
        final String str2 = str;
        Coupon coupon = this.selectCoupon;
        this.orderLogic.otherPay(this.mOrder.getMoney(), str2, this.mOrder.getMoid(), zEquals, coupon != null ? coupon.getCode() : "", new OrderData.PayCallback<PayOrder>() { // from class: com.sy37sdk.order.nat.trade.TradePayPresenter.3
            @Override // com.sy37sdk.order.nat.trade.OrderData.PayCallback
            public void onSuccess(PayOrder payOrder) {
                LogUtil.i("下单成功：" + payOrder.toString());
                if (str2.equals("alipay")) {
                    String str3 = new String(Base64.decode(payOrder.getTrade()));
                    TradePayPresenter.this.setAliOrderId(payOrder.getUuid());
                    LogUtil.i("alipay:" + str3);
                    TradePayPresenter.this.toAlipay(str3, true);
                    return;
                }
                if (str2.equals(NativePayWay.PAY_WAY_WECHAT)) {
                    String mwebUrl = payOrder.getMwebUrl();
                    TradePayPresenter.this.wxOrderId = payOrder.getUuid();
                    TradePayPresenter tradePayPresenter = TradePayPresenter.this;
                    tradePayPresenter.setWxOrderId(tradePayPresenter.wxOrderId);
                    LogUtil.i("wechat-->url:" + mwebUrl + ", uuid:" + TradePayPresenter.this.wxOrderId);
                    TradePayPresenter.this.toWxPayByWebView(mwebUrl, payOrder.getWxReferer());
                }
            }

            @Override // com.sy37sdk.order.nat.trade.OrderData.PayCallback
            public void onFailure(int i, String str3) {
                LogUtil.i("下单失败code:" + i + ", msg: " + str3);
                if (TradePayPresenter.this.mView != null) {
                    ((ITradeView) TradePayPresenter.this.mView).setWxPay(false);
                    ((ITradeView) TradePayPresenter.this.mView).showToast(str3);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toWxPayByWebView(String str, String str2) {
        HashMap map = new HashMap();
        map.put("referer", EncryptUtil.decrypt(str2));
        SQWebView sQWebView = new SQWebView(this.context);
        SQWeb.getInstance().bind(sQWebView).disableLocalH5().replace(new TradeUrlWebHook(), UrlWebHook.class);
        sQWebView.loadUrl(str, map);
    }

    @Override // com.sy37sdk.order.nat.trade.ITradePresenter
    public ArrayList<Coupon> getCoupons() {
        return this.mCoupons;
    }

    @Override // com.sy37sdk.order.nat.trade.ITradePresenter
    public void selectCoupon(Coupon coupon) {
        if (coupon != null) {
            setCoupon(coupon);
        }
    }

    @Override // com.sy37sdk.order.nat.trade.ITradePresenter
    public void selectNoneCoupon() {
        this.selectCoupon = null;
        if (this.mView != 0) {
            ((ITradeView) this.mView).showCouponNone();
            ((ITradeView) this.mView).showOriginalNonePrice();
            ((ITradeView) this.mView).showProductPrice(this.mOrder.getMoney() + "");
        }
    }

    @Override // com.sy37sdk.order.nat.trade.ITradePresenter
    public void showAllPayWay() {
        if (this.mView != 0) {
            ((ITradeView) this.mView).showAllPayWay(this.nativePayWays);
        }
    }

    @Override // com.sy37sdk.order.nat.trade.ITradePresenter
    public void paySuccess() {
        if (this.mPayListener != null) {
            this.mPayListener.onSuccess();
        }
        saveSuccessPayWay(this.pway);
    }

    @Override // com.sy37sdk.order.nat.trade.ITradePresenter
    public void onFailure(int i, String str) {
        if (this.mPayListener != null) {
            this.mPayListener.onFailure(i, str);
        }
    }

    private void initPayWay() {
        if (this.mView != 0) {
            ((ITradeView) this.mView).showLoading();
        }
        this.orderLogic.getAvailableWays(this.mOrder.getMoid(), new OrderData.PayCallback<String>() { // from class: com.sy37sdk.order.nat.trade.TradePayPresenter.4
            @Override // com.sy37sdk.order.nat.trade.OrderData.PayCallback
            public void onSuccess(String str) {
                if (TradePayPresenter.this.mView != null) {
                    ((ITradeView) TradePayPresenter.this.mView).hideLoading();
                }
                TradePayPresenter.this.nativePayWays = PayJsonParser.parsePWay(str);
                TradePayPresenter.this.initWalletPayWay();
            }

            @Override // com.sy37sdk.order.nat.trade.OrderData.PayCallback
            public void onFailure(int i, String str) {
                if (TradePayPresenter.this.mView != null) {
                    ((ITradeView) TradePayPresenter.this.mView).hideLoading();
                }
                LogUtil.i("初始化支付方式失败,展示默认的支付方式 code:" + i + ", msg:" + str);
                TradePayPresenter tradePayPresenter = TradePayPresenter.this;
                tradePayPresenter.filterShowPayWay(tradePayPresenter.defaultPayWays());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initWalletPayWay() {
        ArrayList<NativePayWay> arrayList = this.nativePayWays;
        if (arrayList == null || arrayList.isEmpty()) {
            filterShowPayWay(defaultPayWays());
            return;
        }
        boolean z = false;
        Iterator<NativePayWay> it = this.nativePayWays.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().getKey().equals(NativePayWay.PWAY_KEY_WALLET)) {
                z = true;
                break;
            }
        }
        if (z) {
            this.orderLogic.getWalletBalance(this.mOrder.getMoid(), new OrderData.PayCallback<Wallet>() { // from class: com.sy37sdk.order.nat.trade.TradePayPresenter.5
                @Override // com.sy37sdk.order.nat.trade.OrderData.PayCallback
                public void onSuccess(Wallet wallet) {
                    if (wallet.getUb() < TradePayPresenter.this.mOrder.getMoney()) {
                        TradePayPresenter.this.excludeWalletPayWay();
                    } else {
                        TradePayPresenter.this.addWalletInfo(wallet);
                        TradePayPresenter.this.payWayLoaded();
                    }
                }

                @Override // com.sy37sdk.order.nat.trade.OrderData.PayCallback
                public void onFailure(int i, String str) {
                    TradePayPresenter.this.excludeWalletPayWay();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addWalletInfo(Wallet wallet) {
        ArrayList<NativePayWay> arrayList = this.nativePayWays;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        for (NativePayWay nativePayWay : this.nativePayWays) {
            if (nativePayWay.getKey().equals(NativePayWay.PWAY_KEY_WALLET)) {
                nativePayWay.setExtra("余额: " + wallet.getUb());
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void excludeWalletPayWay() {
        ArrayList<NativePayWay> arrayList = this.nativePayWays;
        if (arrayList == null || arrayList.isEmpty()) {
            filterShowPayWay(defaultPayWays());
            return;
        }
        ArrayList<NativePayWay> arrayList2 = new ArrayList<>();
        for (NativePayWay nativePayWay : this.nativePayWays) {
            if (!nativePayWay.getKey().equals(NativePayWay.PWAY_KEY_WALLET)) {
                arrayList2.add(nativePayWay);
            }
        }
        this.nativePayWays = arrayList2;
        payWayLoaded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void payWayLoaded() {
        filterShowPayWay(this.nativePayWays);
    }

    private void initCoupon() {
        this.orderLogic.getCoupons(this.mOrder.getMoid(), this.mOrder.getMoney(), this.mOrder.getDsid(), new OrderData.PayCallback<List<Coupon>>() { // from class: com.sy37sdk.order.nat.trade.TradePayPresenter.6
            @Override // com.sy37sdk.order.nat.trade.OrderData.PayCallback
            public void onSuccess(List<Coupon> list) {
                if (list == null || list.isEmpty()) {
                    return;
                }
                TradePayPresenter.this.mCoupons = new ArrayList<>();
                for (Coupon coupon : list) {
                    if (coupon.isAvailable()) {
                        TradePayPresenter.this.mCoupons.add(coupon);
                    }
                }
                if (TradePayPresenter.this.mCoupons.isEmpty() || TradePayPresenter.this.mView == null) {
                    return;
                }
                ((ITradeView) TradePayPresenter.this.mView).showCoupons();
                TradePayPresenter.this.setCoupon(TradePayPresenter.this.mCoupons.get(0));
            }

            @Override // com.sy37sdk.order.nat.trade.OrderData.PayCallback
            public void onFailure(int i, String str) {
                LogUtil.i("初始化代金券失败 code:" + i + ", msg:" + str);
            }
        });
    }

    public void setCoupon(Coupon coupon) {
        this.selectCoupon = coupon;
        if (this.mView != 0) {
            float money = this.mOrder.getMoney();
            float amount = money - coupon.getAmount();
            ((ITradeView) this.mView).showCoupon(coupon.getAmount() + "");
            ((ITradeView) this.mView).showOriginalPrice(money + "");
            ((ITradeView) this.mView).showProductPrice(amount + "");
        }
    }

    private void saveSuccessPayWay(String str) {
        SpUtils.get(this.context).put(LAST_SUCCESS_PAY_WAY, str);
    }

    private String lastPayWay() {
        return SpUtils.get(this.context).getString(LAST_SUCCESS_PAY_WAY, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.util.ArrayList, java.util.ArrayList<com.sy37sdk.order.nat.trade.NativePayWay>] */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.util.List] */
    public void filterShowPayWay(ArrayList<NativePayWay> arrayList) {
        int i;
        if (this.mView == null || arrayList == 0 || arrayList.size() == 0) {
            return;
        }
        String strLastPayWay = lastPayWay();
        if (TextUtils.isEmpty(strLastPayWay)) {
            i = 0;
        } else {
            i = 0;
            while (i < arrayList.size()) {
                if (((NativePayWay) arrayList.get(i)).getWay().equalsIgnoreCase(strLastPayWay)) {
                    break;
                } else {
                    i++;
                }
            }
            i = 0;
        }
        boolean z = i < 2 && arrayList.size() > 2;
        if (z) {
            arrayList = arrayList.subList(0, 2);
        }
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != 0 && arrayList.size() > 0) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (i == i2) {
                    arrayList2.add(0, arrayList.get(i2));
                } else {
                    arrayList2.add(arrayList.get(i2));
                }
            }
        }
        ((ITradeView) this.mView).showPayWay(arrayList2, z);
        ((ITradeView) this.mView).selectPayWay(0);
    }

    private class TradeUrlWebHook extends UrlWebHook {
        private TradeUrlWebHook() {
        }

        @Override // com.sqwan.common.webview.UrlWebHook, com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.startsWith("weixin://wap/pay")) {
                SQLog.i("shouldOverrideUrlLoading: 匹配到微信支付协议");
                TradePayPresenter.this.toWxPay(str);
                return true;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }
}
