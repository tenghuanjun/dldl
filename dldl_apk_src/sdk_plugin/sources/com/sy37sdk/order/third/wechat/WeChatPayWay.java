package com.sy37sdk.order.third.wechat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebView;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.util.JsonMap;
import com.sqwan.order.base.PayWay;
import com.sy37sdk.order.PayOrderModel;
import com.sy37sdk.order.third.CheckOrderManager;
import com.sy37sdk.order.third.CheckablePayWay;
import com.sy37sdk.order.third.IPayWay;
import com.sy37sdk.order.third.IWebPayWay;
import com.sy37sdk.order.third.ThirdPayManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class WeChatPayWay implements IWebPayWay, CheckablePayWay {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ERROR_GO_PAY_PAGE = 106;
    public static final int ERROR_NO_ORDER = 103;
    public static final int ERROR_ORDER_ID = 102;
    public static final int ERROR_ORDER_NOT_MATCH = 104;
    public static final int ERROR_PARAM = 101;
    public static final int ERROR_UNSUPPORTED = 100;
    public static final int ERROR_URL = 105;
    private static final String HTTP_PAY_WAY = "wxpay";
    private WeChatOrder mCurrentOrder;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final WebView mWebView;

    /* JADX INFO: Access modifiers changed from: private */
    public String getSimpleName() {
        return "WeChat";
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void init(Context context) {
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void onPause(Activity activity) {
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void onStart(Activity activity) {
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void onStop(Activity activity) {
    }

    public WeChatPayWay(WebView webView) {
        this.mWebView = webView;
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void onResume(Activity activity) {
        SQLog.v("【Pay Way】[" + getSimpleName() + "]onResume, " + this.mCurrentOrder);
        checkOrder(true);
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void pay(Activity activity, PayOrderModel payOrderModel, Bundle bundle, IPayWay.PayWayCallback payWayCallback) {
        SQLog.d("【Pay Way】[" + getSimpleName() + "]支付:\n" + payOrderModel);
        SQLog.d("【Pay Way】[" + getSimpleName() + "]extra: " + bundle);
        InternalCallback internalCallbackWrapCallback = wrapCallback(payWayCallback);
        if (bundle == null) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]无额外参数, 无法支付");
            internalCallbackWrapCallback.onFailed(getName(), payOrderModel, 101, "无参数");
            return;
        }
        String string = bundle.getString("third_order_id");
        if (TextUtils.isEmpty(string)) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]无uuid, 无法支付");
            internalCallbackWrapCallback.onFailed(getName(), payOrderModel, 102, "订单号异常");
            return;
        }
        WeChatOrder weChatOrder = this.mCurrentOrder;
        if (weChatOrder != null) {
            SQLog.w("【Pay Way】[" + getSimpleName() + "]覆盖订单: " + weChatOrder.order.getMoid());
            JsonMap jsonMap = new JsonMap();
            jsonMap.put("old_moid", weChatOrder.order.getMoid());
            jsonMap.put("old_uuid", weChatOrder.uuid);
            jsonMap.put("new_moid", payOrderModel.getMoid());
            jsonMap.put("new_uuid", string);
            BuglessAction.reportCatchException(new IllegalStateException("微信覆盖订单"), jsonMap.toString(), BuglessAction.PAY_ERROR);
        }
        this.mCurrentOrder = new WeChatOrder(string, payOrderModel, internalCallbackWrapCallback);
        final String string2 = bundle.getString(ThirdPayManager.EXTRA_PAY_URL, "");
        String string3 = bundle.getString("referer");
        String string4 = bundle.getString(ThirdPayManager.EXTRA_TRADE_INFO);
        if (!TextUtils.isEmpty(string3) && !TextUtils.isEmpty(string2)) {
            SQLog.d("【Pay Way】[" + getSimpleName() + "]重定向到支付链接: " + string2 + ", refer=" + string3);
            final HashMap map = new HashMap();
            map.put("referer", string3);
            this.mHandler.post(new Runnable() { // from class: com.sy37sdk.order.third.wechat.-$$Lambda$WeChatPayWay$TP0ZLejtIhQRzBxQaCqDsYcjvuM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$pay$0$WeChatPayWay(string2, map);
                }
            });
            return;
        }
        if (!TextUtils.isEmpty(string4)) {
            String str = string4 + "&type=android";
            SQLog.w("【Pay Way】[" + getSimpleName() + "](旧)支付链接: " + str);
            payByUrl(activity, payOrderModel, str, internalCallbackWrapCallback);
            return;
        }
        SQLog.e("【Pay Way】[" + getSimpleName() + "]无有效参数, 无法支付");
        internalCallbackWrapCallback.onFailed(getName(), payOrderModel, 101, "参数异常");
    }

    public /* synthetic */ void lambda$pay$0$WeChatPayWay(String str, Map map) {
        this.mWebView.loadUrl(str, map);
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public PayWay getName() {
        return PayWay.WECHAT;
    }

    @Override // com.sy37sdk.order.third.IWebPayWay
    public boolean interceptorWithUrl(Activity activity, PayOrderModel payOrderModel, String str, Bundle bundle, IPayWay.PayWayCallback payWayCallback) {
        if (str == null || !str.startsWith("weixin://wap/pay")) {
            return false;
        }
        InternalCallback internalCallbackWrapCallback = wrapCallback(payWayCallback);
        SQLog.i("【Pay Way】[" + getSimpleName() + "]匹配到微信支付协议: " + str);
        WeChatOrder weChatOrder = this.mCurrentOrder;
        if (weChatOrder == null) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]无前置订单, 直接支付失败");
            internalCallbackWrapCallback.onFailed(getName(), payOrderModel, 103, "无订单");
            return true;
        }
        if (!weChatOrder.match(payOrderModel)) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]订单对应不上, 当前:" + weChatOrder.order.getMoid() + ", 实际:" + payOrderModel.getMoid() + ", 直接支付失败");
            JsonMap jsonMap = new JsonMap();
            jsonMap.put("expect_order_id", weChatOrder.order.getMoid());
            jsonMap.put("exact_order_id", payOrderModel.getMoid());
            BuglessAction.reportCatchException(new IllegalStateException("微信订单不对应"), jsonMap.toString(), BuglessAction.PAY_ERROR);
            internalCallbackWrapCallback.onFailed(getName(), payOrderModel, 104, "订单异常");
            return true;
        }
        weChatOrder.callback = internalCallbackWrapCallback;
        payByUrl(activity, payOrderModel, str, internalCallbackWrapCallback);
        return true;
    }

    private void payByUrl(Activity activity, PayOrderModel payOrderModel, String str, IPayWay.PayWayCallback payWayCallback) {
        if (!str.startsWith("weixin")) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]非微信url, 无法支付: " + str);
            BuglessAction.reportCatchException(new IllegalStateException("异常的微信url"), str, BuglessAction.PAY_ERROR);
            payWayCallback.onFailed(getName(), payOrderModel, 105, "scheme异常");
            return;
        }
        try {
            Uri uri = Uri.parse(str);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(uri);
            if (!activity.getPackageManager().queryIntentActivities(intent, 65536).isEmpty()) {
                SQLog.d("【Pay Way】[" + getSimpleName() + "]跳转支付界面");
                try {
                    activity.startActivity(intent);
                    return;
                } catch (Exception e) {
                    BuglessAction.reportCatchException(e, "跳转微信支付界面失败", 103);
                    SQLog.w("【Pay Way】[" + getSimpleName() + "]跳转微信支付界面失败, 无法支付");
                    payWayCallback.onFailed(getName(), payOrderModel, 106, "无法跳转微信");
                    return;
                }
            }
            SQLog.w("【Pay Way】[" + getSimpleName() + "]未安装微信, 无法支付");
            payWayCallback.onFailed(getName(), payOrderModel, 100, "未安装");
        } catch (Exception unused) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]url解析异常, 无法支付: " + str);
            BuglessAction.reportCatchException(new IllegalStateException("异常的微信url"), str, BuglessAction.PAY_ERROR);
            payWayCallback.onFailed(getName(), payOrderModel, 105, "URL异常");
        }
    }

    private void checkOrder(boolean z) {
        WeChatOrder weChatOrder = this.mCurrentOrder;
        if (weChatOrder == null) {
            return;
        }
        String str = weChatOrder.uuid;
        final PayOrderModel payOrderModel = weChatOrder.order;
        final InternalCallback internalCallback = weChatOrder.callback;
        SQLog.d("【Pay Way】[" + getSimpleName() + "]检查订单, moid=" + payOrderModel.getMoid() + ", uuid=" + str);
        CheckOrderManager.getInstance().check(payOrderModel, str, "wxpay", z, new CheckOrderManager.CheckOrderCallback() { // from class: com.sy37sdk.order.third.wechat.WeChatPayWay.1
            @Override // com.sy37sdk.order.third.CheckOrderManager.CheckOrderCallback
            public void onSuccess(PayOrderModel payOrderModel2, String str2) {
                SQLog.i("【Pay Way】[" + WeChatPayWay.this.getSimpleName() + "]订单支付成功, uuid=" + str2);
                internalCallback.onSuccess(WeChatPayWay.this.getName(), payOrderModel);
            }

            @Override // com.sy37sdk.order.third.CheckOrderManager.CheckOrderCallback
            public void onFailure(PayOrderModel payOrderModel2, String str2, int i, String str3) {
                SQLog.w("【Pay Way】[" + WeChatPayWay.this.getSimpleName() + "]订单未支付成功, code=" + i + ", msg=" + str3 + ", uuid=" + str2);
                if (i == -333) {
                    internalCallback.onCancel(WeChatPayWay.this.getName(), payOrderModel);
                } else {
                    internalCallback.onFailed(WeChatPayWay.this.getName(), payOrderModel, i, str3);
                }
            }
        });
    }

    @Override // com.sy37sdk.order.third.CheckablePayWay
    public void check() {
        checkOrder(false);
    }

    private static class WeChatOrder {
        InternalCallback callback;
        final PayOrderModel order;
        final String uuid;

        private WeChatOrder(String str, PayOrderModel payOrderModel, InternalCallback internalCallback) {
            this.uuid = str;
            this.order = payOrderModel;
            this.callback = internalCallback;
        }

        boolean match(PayOrderModel payOrderModel) {
            if (payOrderModel == null) {
                return false;
            }
            String orderId = this.order.getPayInfoModel().getOrderId();
            return orderId != null && orderId.equals(payOrderModel.getPayInfoModel().getOrderId());
        }
    }

    private InternalCallback wrapCallback(IPayWay.PayWayCallback payWayCallback) {
        if (payWayCallback instanceof InternalCallback) {
            return (InternalCallback) payWayCallback;
        }
        return new InternalCallback(payWayCallback);
    }

    private class InternalCallback extends IPayWay.UIPayWayCallback {
        public InternalCallback(IPayWay.PayWayCallback payWayCallback) {
            super(payWayCallback);
        }

        @Override // com.sy37sdk.order.third.IPayWay.UIPayWayCallback, com.sy37sdk.order.third.IPayWay.PayWayCallback
        public void onSuccess(PayWay payWay, PayOrderModel payOrderModel) {
            resetOrder("success");
            super.onSuccess(payWay, payOrderModel);
        }

        @Override // com.sy37sdk.order.third.IPayWay.UIPayWayCallback, com.sy37sdk.order.third.IPayWay.PayWayCallback
        public void onCancel(PayWay payWay, PayOrderModel payOrderModel) {
            resetOrder("cancel");
            super.onCancel(payWay, payOrderModel);
        }

        @Override // com.sy37sdk.order.third.IPayWay.UIPayWayCallback, com.sy37sdk.order.third.IPayWay.PayWayCallback
        public void onFailed(PayWay payWay, PayOrderModel payOrderModel, int i, String str) {
            resetOrder("failed");
            super.onFailed(payWay, payOrderModel, i, str);
        }

        private void resetOrder(String str) {
            WeChatOrder weChatOrder = WeChatPayWay.this.mCurrentOrder;
            if (weChatOrder != null) {
                SQLog.d("【Pay Way】[" + WeChatPayWay.this.getSimpleName() + "]" + weChatOrder.order.getMoid() + "订单回调(" + str + ")");
            }
            WeChatPayWay.this.mCurrentOrder = null;
        }
    }
}
