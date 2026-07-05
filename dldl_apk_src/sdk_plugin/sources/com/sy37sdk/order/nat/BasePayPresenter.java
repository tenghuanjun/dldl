package com.sy37sdk.order.nat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebView;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tools.Logger;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.base.ActivityResultListener;
import com.sqwan.base.EventDispatcher;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.eventbus.OnActivityResultEvent;
import com.sqwan.common.mvp.BasePresenter;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.util.CheckClassUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SDKError;
import com.sqwan.order.base.PayWay;
import com.sy37sdk.order.OrderRequestManager;
import com.sy37sdk.order.nat.IBasePayView;
import com.sy37sdk.order.nat.bean.Order;
import com.sy37sdk.order.nat.pay.Apay;
import com.sy37sdk.order.web.PayWebPage;
import com.taptap.sdk.kit.internal.p000const.TrackAction;
import com.unionpay.UPPayAssistEx;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class BasePayPresenter<G extends IBasePayView> extends BasePresenter<G> implements IBasePayPresenter {
    private static final int PAY_RETRY_TIME = 60000;
    private static final String TAG = "BasePayPresenter";
    String alipayOrderId;
    protected String currentPayMethod;
    String currentPayWay;
    String douYinOrderId;
    private final List<String> douYinSchemePrefix;
    private Handler mHandler;
    Order mOrder;
    private OrderRequestManager mOrderRequestManager;
    private boolean retryPayCallbackFlag;
    String wxOrderId;

    public abstract int getBuglessPayErrorActionType();

    public abstract String payChannel();

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public void reportPay(boolean z) {
    }

    public BasePayPresenter(Context context, G g) {
        super(context, g);
        this.currentPayMethod = "";
        this.currentPayWay = PayWay.ALI.getHttpPayWay();
        this.retryPayCallbackFlag = false;
        this.douYinSchemePrefix = new ArrayList();
        this.mOrderRequestManager = new OrderRequestManager(context);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.douYinSchemePrefix.add("ttcjpay://dypay/cashier");
        this.douYinSchemePrefix.add("dypay1128://dypay/cashier");
        this.douYinSchemePrefix.add("ttcjpay://dypay/awemelite");
        this.douYinSchemePrefix.add("dypay2329://dypay/cashier");
        this.douYinSchemePrefix.add("dypay8663://dypay/cashier");
    }

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public void setOrder(Order order) {
        this.mOrder = order;
    }

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public void toWxPay(String str) {
        if (str.startsWith("weixin")) {
            LogUtil.i("start weixin pay --> " + str);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            List<ResolveInfo> listQueryIntentActivities = this.context.getPackageManager().queryIntentActivities(intent, 65536);
            if (listQueryIntentActivities != null && !listQueryIntentActivities.isEmpty()) {
                try {
                    this.context.startActivity(intent);
                    return;
                } catch (Exception e) {
                    LogUtil.e(TAG, "跳转微信支付界面失败", e);
                    BuglessAction.reportCatchException(e, "跳转微信支付界面失败", getBuglessPayErrorActionType());
                    return;
                }
            }
            if (this.mView != 0) {
                ((IBasePayView) this.mView).showToast("未安装微信");
            }
        }
    }

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public void toAlipay(String str, boolean z) {
        LogUtil.i("orderinfo:" + str + " 支付宝订单号：" + this.alipayOrderId);
        Apay.getInstance().pay((Activity) this.context, str, new Apay.AliCallback() { // from class: com.sy37sdk.order.nat.BasePayPresenter.1
            @Override // com.sy37sdk.order.nat.pay.Apay.AliCallback
            public void onSuccess() {
                LogUtil.i("支付宝支付成功");
                if (!TextUtils.isEmpty(BasePayPresenter.this.alipayOrderId)) {
                    BasePayPresenter.this.checkAlipay();
                    return;
                }
                BasePayPresenter.this.reportPay(true);
                if (BasePayPresenter.this.mView != null) {
                    ((IBasePayView) BasePayPresenter.this.mView).paySuccess();
                }
            }

            @Override // com.sy37sdk.order.nat.pay.Apay.AliCallback
            public void onFailure(int i, String str2) {
                LogUtil.i("alipay -> code:" + i + ", msg:" + str2);
                if (!TextUtils.isEmpty(BasePayPresenter.this.alipayOrderId)) {
                    BasePayPresenter.this.checkAlipay();
                    return;
                }
                BasePayPresenter.this.reportPay(false);
                BuglessAction.reportCatchException(new Exception(), "支付宝回调支付失败，code = " + i + "，msg = " + str2, BasePayPresenter.this.getBuglessPayErrorActionType());
                if (BasePayPresenter.this.mView != null) {
                    ((IBasePayView) BasePayPresenter.this.mView).onFailure(i, str2);
                }
            }
        }, true);
    }

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public void toUnionPay(String str) {
        if (!CheckClassUtils.classExist("com.unionpay.UPPayAssistEx") && !CheckClassUtils.classExist("com.unionpay.UPPayWapActivity")) {
            ((IBasePayView) this.mView).onFailure(5010, "当前版本不支持云闪付支付");
            BuglessAction.reportCatchException(new Exception(), "云闪付支付失败：当前版本不支持云闪付支付", getBuglessPayErrorActionType());
            return;
        }
        EventDispatcher.getInstance().addActivityResultListener(new ActivityResultListener() { // from class: com.sy37sdk.order.nat.BasePayPresenter.2
            @Override // com.sqwan.base.ActivityResultListener
            public void onResult(OnActivityResultEvent onActivityResultEvent) {
                String str2;
                Intent intent = onActivityResultEvent.getIntent();
                if (intent != null && onActivityResultEvent.getRequestCode() == 10) {
                    EventDispatcher.getInstance().removeActivityResultListener(this);
                    String stringExtra = intent.getStringExtra(PayWebPage.BUNDLE_RESULT_PAY);
                    String stringExtra2 = intent.getStringExtra("result_data");
                    boolean z = true;
                    int i = 0;
                    if ("success".equalsIgnoreCase(stringExtra)) {
                        str2 = "支付成功";
                    } else if (TrackAction.FAIL.equalsIgnoreCase(stringExtra)) {
                        str2 = "支付失败：resultData = " + stringExtra2;
                        z = false;
                        i = 1;
                    } else if ("cancel".equalsIgnoreCase(stringExtra)) {
                        str2 = "取消支付";
                        z = false;
                        i = 2;
                    } else {
                        str2 = "未知支付结果：payResult = " + stringExtra + ", resultData = " + stringExtra2;
                        z = false;
                        i = -1;
                    }
                    BasePayPresenter.this.reportPay(z);
                    if (!z) {
                        BuglessAction.reportCatchException(new Exception(), "云闪付回调支付失败，msg = " + str2, BasePayPresenter.this.getBuglessPayErrorActionType());
                    }
                    if (BasePayPresenter.this.mView == null) {
                        return;
                    }
                    if (z) {
                        ((IBasePayView) BasePayPresenter.this.mView).paySuccess();
                    } else {
                        ((IBasePayView) BasePayPresenter.this.mView).onFailure(i, str2);
                    }
                }
            }
        });
        try {
            UPPayAssistEx.startPay(this.context, (String) null, (String) null, str, "00");
        } catch (Exception e) {
            LogUtil.e(TAG, "跳转云闪付支付界面失败", e);
            BuglessAction.reportCatchException(e, "跳转云闪付支付界面失败", getBuglessPayErrorActionType());
        }
    }

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public void toDouYinWebPay(final WebView webView, final String str, final String str2) {
        if (TextUtils.isEmpty(str)) {
            callbackDouYinPayFailure(20011, "调起抖音支付失败，请联系客服【20011】");
        } else if (TextUtils.isEmpty(str2)) {
            callbackDouYinPayFailure(20012, "调起抖音支付失败，请联系客服【20012】");
        } else {
            webView.post(new Runnable() { // from class: com.sy37sdk.order.nat.-$$Lambda$BasePayPresenter$r93yy6wnL1LxbGIZSQvrlLunmvI
                @Override // java.lang.Runnable
                public final void run() {
                    BasePayPresenter.lambda$toDouYinWebPay$0(str2, webView, str);
                }
            });
        }
    }

    static /* synthetic */ void lambda$toDouYinWebPay$0(String str, WebView webView, String str2) {
        HashMap map = new HashMap();
        map.put("referer", str);
        webView.loadUrl(str2, map);
        HashMap map2 = new HashMap();
        map2.put("payUrl", str2);
        map2.put("referer", str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.PAY_H5_DOU_YIN, map2);
    }

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public void toDouYinPaySchemePage(String str) {
        if (!isMatchDouYinPaySchemePrefix(str)) {
            callbackDouYinPayFailure(20015, "跳转抖音支付失败，请重试或联系客服【20015】");
            return;
        }
        LogUtil.i("准备跳转到抖音支付页：" + str);
        Intent intent = new Intent("android.intent.action.VIEW");
        PackageManager packageManager = this.context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        String strReplace = null;
        Iterator<String> it = this.douYinSchemePrefix.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            if (str.startsWith(next)) {
                strReplace = str.replace(next, "");
                break;
            }
        }
        if (strReplace == null || "".equals(strReplace)) {
            intent.setData(Uri.parse(str));
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            if (listQueryIntentActivities != null && !listQueryIntentActivities.isEmpty()) {
                arrayList.add(str);
            }
        } else {
            for (String str2 : this.douYinSchemePrefix) {
                String str3 = str2 + strReplace;
                intent.setData(Uri.parse(str3));
                List<ResolveInfo> listQueryIntentActivities2 = packageManager.queryIntentActivities(intent, 65536);
                if (listQueryIntentActivities2 != null && !listQueryIntentActivities2.isEmpty()) {
                    if (str.startsWith(str2)) {
                        arrayList.add(0, str3);
                    } else {
                        arrayList.add(str3);
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            if (this.mView != 0) {
                ((IBasePayView) this.mView).showToast("未安装抖音，请安装后重新下单【20013】");
                return;
            }
            return;
        }
        try {
            intent.setData(Uri.parse((String) arrayList.get(0)));
            this.context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e(TAG, "跳转抖音支付失败，请重试或联系客服【20014】", e);
            callbackDouYinPayFailure(20014, "跳转抖音支付失败，请重试或联系客服【20014】", e);
        }
    }

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public boolean isMatchDouYinPaySchemePrefix(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Iterator<String> it = this.douYinSchemePrefix.iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public void checkWxPay() {
        checkPayStatus(this.wxOrderId, PayWay.WECHAT.getHttpPayWay());
    }

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public void checkDouYinWebPay() {
        checkPayStatus(this.douYinOrderId, PayWay.DOU_YIN_H5.getHttpPayWay());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAlipay() {
        checkPayStatus(this.alipayOrderId, this.currentPayWay);
        this.alipayOrderId = null;
    }

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public void setWxOrderId(String str) {
        this.wxOrderId = str;
    }

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public void setAliOrderId(String str) {
        this.alipayOrderId = str;
    }

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public void setDouYinOrderId(String str) {
        this.douYinOrderId = str;
    }

    @Override // com.sy37sdk.order.nat.IBasePayPresenter
    public void setCurrentPayMethod(String str) {
        this.currentPayMethod = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkPayStatus(final String str, final String str2) {
        Logger.info("查询支付状态：" + str + "  " + str2, new Object[0]);
        this.mOrderRequestManager.checkPay(str, str2, new SqHttpCallback<Void>() { // from class: com.sy37sdk.order.nat.BasePayPresenter.3
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(Void r3) {
                if (!BasePayPresenter.this.retryPayCallbackFlag) {
                    BasePayPresenter.this.reportPay(true);
                    if (BasePayPresenter.this.mView != null) {
                        ((IBasePayView) BasePayPresenter.this.mView).paySuccess();
                        return;
                    }
                    return;
                }
                LogUtil.d(str2 + "支付回调重试成功");
                LogUtil.d("补充上报支付成功");
                BasePayPresenter.this.reportPay(true);
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str3, String str4) {
                if (!BasePayPresenter.this.retryPayCallbackFlag) {
                    LogUtil.d(str2 + "查询未支付成功，且未重试过，一分钟后重试");
                    BasePayPresenter.this.mHandler.postDelayed(new Runnable() { // from class: com.sy37sdk.order.nat.BasePayPresenter.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            LogUtil.d(str2 + "支付查询发起重试");
                            BasePayPresenter.this.retryPayCallbackFlag = true;
                            BasePayPresenter.this.checkPayStatus(str, str2);
                        }
                    }, 60000L);
                    BasePayPresenter.this.reportPay(false);
                    BuglessAction.reportCatchException(new Exception(), "检查到 " + str2 + " 订单支付失败，code = " + SDKError.PAY_FAIL.code + "，msg = " + SDKError.PAY_FAIL.message, BasePayPresenter.this.getBuglessPayErrorActionType());
                    if (BasePayPresenter.this.mView != null) {
                        ((IBasePayView) BasePayPresenter.this.mView).onFailure(SDKError.PAY_FAIL.code, SDKError.PAY_FAIL.message);
                        return;
                    }
                    return;
                }
                LogUtil.d("重试" + str2 + "付结果查询还是未支付，无需处理");
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str3, VolleyError volleyError) {
                BuglessAction.reportCatchException(volleyError, "检查 " + str2 + " 订单状态失败，code = " + i + "，msg = " + str3, BasePayPresenter.this.getBuglessPayErrorActionType());
                if (BasePayPresenter.this.mView != null) {
                    ((IBasePayView) BasePayPresenter.this.mView).onFailure(i, str3);
                }
            }
        });
    }

    private void callbackDouYinPayFailure(int i, String str) {
        callbackDouYinPayFailure(i, str, new Exception(str));
    }

    private void callbackDouYinPayFailure(int i, String str, Exception exc) {
        if (this.mView != 0) {
            ((IBasePayView) this.mView).onFailure(i, str);
        }
        BuglessAction.reportCatchException(exc, "抖音支付失败：" + str, getBuglessPayErrorActionType());
    }
}
