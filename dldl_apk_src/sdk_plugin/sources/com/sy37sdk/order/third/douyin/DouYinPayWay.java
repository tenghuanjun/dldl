package com.sy37sdk.order.third.douyin;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class DouYinPayWay implements IWebPayWay, CheckablePayWay {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ERROR_GO_PAY_PAGE = 306;
    public static final int ERROR_NO_ORDER = 303;
    public static final int ERROR_ORDER_ID = 302;
    public static final int ERROR_ORDER_NOT_MATCH = 304;
    public static final int ERROR_PARAM = 301;
    public static final int ERROR_UNSUPPORTED = 300;
    public static final int ERROR_URL = 305;
    private static final String HTTP_PAY_WAY = "douyin_h5";
    private DouYinOrder mCurrentOrder;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final List<String> mSchemePrefix;
    private final WebView mWebView;

    /* JADX INFO: Access modifiers changed from: private */
    public String getSimpleName() {
        return "DouYin";
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

    public DouYinPayWay(WebView webView) {
        ArrayList arrayList = new ArrayList();
        this.mSchemePrefix = arrayList;
        this.mWebView = webView;
        arrayList.add("ttcjpay://dypay/cashier");
        this.mSchemePrefix.add("dypay1128://dypay/cashier");
        this.mSchemePrefix.add("ttcjpay://dypay/awemelite");
        this.mSchemePrefix.add("dypay2329://dypay/cashier");
        this.mSchemePrefix.add("dypay8663://dypay/cashier");
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
            internalCallbackWrapCallback.onFailed(getName(), payOrderModel, ERROR_PARAM, "无参数");
            return;
        }
        String string = bundle.getString("third_order_id");
        if (TextUtils.isEmpty(string)) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]无uuid, 无法支付");
            internalCallbackWrapCallback.onFailed(getName(), payOrderModel, ERROR_ORDER_ID, "订单号异常");
            return;
        }
        DouYinOrder douYinOrder = this.mCurrentOrder;
        if (douYinOrder != null) {
            SQLog.w("【Pay Way】[" + getSimpleName() + "]覆盖订单: " + douYinOrder.order.getMoid());
            JsonMap jsonMap = new JsonMap();
            jsonMap.put("old_moid", douYinOrder.order.getMoid());
            jsonMap.put("old_uuid", douYinOrder.uuid);
            jsonMap.put("new_moid", payOrderModel.getMoid());
            jsonMap.put("new_uuid", string);
            BuglessAction.reportCatchException(new IllegalStateException("抖音覆盖订单"), jsonMap.toString(), BuglessAction.PAY_ERROR);
        }
        this.mCurrentOrder = new DouYinOrder(string, payOrderModel, internalCallbackWrapCallback);
        String string2 = bundle.getString("referer");
        final String string3 = bundle.getString(ThirdPayManager.EXTRA_PAY_URL);
        if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
            SQLog.d("【Pay Way】[" + getSimpleName() + "]重定向到支付链接: " + string3 + ", refer=" + string2);
            final HashMap map = new HashMap();
            map.put("referer", string2);
            this.mHandler.post(new Runnable() { // from class: com.sy37sdk.order.third.douyin.-$$Lambda$DouYinPayWay$aXpEjIjGJjASQ8xKlRLa-Vnlag8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$pay$0$DouYinPayWay(string3, map);
                }
            });
            return;
        }
        SQLog.e("【Pay Way】[" + getSimpleName() + "]无有效参数, 无法支付");
        internalCallbackWrapCallback.onFailed(getName(), payOrderModel, ERROR_PARAM, "参数异常");
    }

    public /* synthetic */ void lambda$pay$0$DouYinPayWay(String str, Map map) {
        this.mWebView.loadUrl(str, map);
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public PayWay getName() {
        return PayWay.DOU_YIN_H5;
    }

    @Override // com.sy37sdk.order.third.IWebPayWay
    public boolean interceptorWithUrl(Activity activity, PayOrderModel payOrderModel, String str, Bundle bundle, IPayWay.PayWayCallback payWayCallback) {
        if (str == null || !isMatchDouYinPaySchemePrefix(str)) {
            return false;
        }
        InternalCallback internalCallbackWrapCallback = wrapCallback(payWayCallback);
        SQLog.i("【Pay Way】[" + getSimpleName() + "]匹配到抖音支付协议: " + str);
        DouYinOrder douYinOrder = this.mCurrentOrder;
        if (douYinOrder == null) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]无前置订单, 直接支付失败");
            internalCallbackWrapCallback.onFailed(getName(), payOrderModel, ERROR_NO_ORDER, "无订单");
            return true;
        }
        if (!douYinOrder.match(payOrderModel)) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]订单对应不上, 当前:" + douYinOrder.order.getMoid() + ", 实际:" + payOrderModel.getMoid() + ", 直接支付失败");
            JsonMap jsonMap = new JsonMap();
            jsonMap.put("expect_order_id", douYinOrder.order.getMoid());
            jsonMap.put("exact_order_id", payOrderModel.getMoid());
            BuglessAction.reportCatchException(new IllegalStateException("抖音订单不对应"), jsonMap.toString(), BuglessAction.PAY_ERROR);
            internalCallbackWrapCallback.onFailed(getName(), payOrderModel, ERROR_ORDER_NOT_MATCH, "订单异常");
            return true;
        }
        douYinOrder.callback = internalCallbackWrapCallback;
        payByUrl(activity, payOrderModel, str, internalCallbackWrapCallback);
        return true;
    }

    private void payByUrl(Activity activity, PayOrderModel payOrderModel, String str, IPayWay.PayWayCallback payWayCallback) {
        if (!isMatchDouYinPaySchemePrefix(str)) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]非抖音url, 无法支付: " + str);
            BuglessAction.reportCatchException(new IllegalStateException("异常的抖音url"), str, BuglessAction.PAY_ERROR);
            payWayCallback.onFailed(getName(), payOrderModel, ERROR_URL, "scheme异常");
            return;
        }
        Intent intentCreateIntent = createIntent(activity, str);
        if (intentCreateIntent == null) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]未安装抖音, 无法支付");
            payWayCallback.onFailed(getName(), payOrderModel, 300, "未安装");
            return;
        }
        SQLog.d("【Pay Way】[" + getSimpleName() + "]跳转支付界面");
        try {
            activity.startActivity(intentCreateIntent);
        } catch (Exception e) {
            BuglessAction.reportCatchException(e, "跳转抖音支付界面失败", 103);
            SQLog.w("【Pay Way】[" + getSimpleName() + "]跳转抖音支付界面失败, 无法支付");
            payWayCallback.onFailed(getName(), payOrderModel, ERROR_GO_PAY_PAGE, "无法跳转抖音");
        }
    }

    private void checkOrder(boolean z) {
        DouYinOrder douYinOrder = this.mCurrentOrder;
        if (douYinOrder == null) {
            return;
        }
        String str = douYinOrder.uuid;
        final PayOrderModel payOrderModel = douYinOrder.order;
        final InternalCallback internalCallback = douYinOrder.callback;
        SQLog.d("【Pay Way】[" + getSimpleName() + "]检查订单, moid=" + payOrderModel.getMoid() + ", uuid=" + str);
        CheckOrderManager.getInstance().check(payOrderModel, str, HTTP_PAY_WAY, z, new CheckOrderManager.CheckOrderCallback() { // from class: com.sy37sdk.order.third.douyin.DouYinPayWay.1
            @Override // com.sy37sdk.order.third.CheckOrderManager.CheckOrderCallback
            public void onSuccess(PayOrderModel payOrderModel2, String str2) {
                SQLog.i("【Pay Way】[" + DouYinPayWay.this.getSimpleName() + "]订单支付成功, uuid=" + str2);
                internalCallback.onSuccess(DouYinPayWay.this.getName(), payOrderModel);
            }

            @Override // com.sy37sdk.order.third.CheckOrderManager.CheckOrderCallback
            public void onFailure(PayOrderModel payOrderModel2, String str2, int i, String str3) {
                SQLog.w("【Pay Way】[" + DouYinPayWay.this.getSimpleName() + "]订单未支付成功, code=" + i + ", msg=" + str3 + ", uuid=" + str2);
                if (i == -333) {
                    internalCallback.onCancel(DouYinPayWay.this.getName(), payOrderModel);
                } else {
                    internalCallback.onFailed(DouYinPayWay.this.getName(), payOrderModel, i, str3);
                }
            }
        });
    }

    @Override // com.sy37sdk.order.third.CheckablePayWay
    public void check() {
        checkOrder(false);
    }

    private static class DouYinOrder {
        InternalCallback callback;
        final PayOrderModel order;
        final String uuid;

        private DouYinOrder(String str, PayOrderModel payOrderModel, InternalCallback internalCallback) {
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
            DouYinOrder douYinOrder = DouYinPayWay.this.mCurrentOrder;
            if (douYinOrder != null) {
                SQLog.d("【Pay Way】[" + DouYinPayWay.this.getSimpleName() + "]" + douYinOrder.order.getMoid() + "订单回调(" + str + ")");
            }
            DouYinPayWay.this.mCurrentOrder = null;
        }
    }

    private Intent createIntent(Context context, String str) {
        String strReplace;
        try {
            Uri.parse(str);
            Iterator<String> it = this.mSchemePrefix.iterator();
            while (true) {
                if (!it.hasNext()) {
                    strReplace = null;
                    break;
                }
                String next = it.next();
                if (str.startsWith(next)) {
                    strReplace = str.replace(next, "");
                    SQLog.d("【Pay Way】[" + getSimpleName() + "]匹配到" + next + ", 提取路径和参数: " + strReplace);
                    break;
                }
            }
            if (TextUtils.isEmpty(strReplace)) {
                SQLog.w("【Pay Way】[" + getSimpleName() + "]非抖音url: " + str);
                BuglessAction.reportCatchException(new IllegalStateException("异常的抖音url"), str, BuglessAction.PAY_ERROR);
                return null;
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            if (canHandleUrl(context, str)) {
                SQLog.d("【Pay Way】[" + getSimpleName() + "]使用原始url跳转: " + str);
                intent.setData(Uri.parse(str));
                return intent;
            }
            SQLog.w("【Pay Way】[" + getSimpleName() + "]原始url无法跳转, 尝试匹配其他scheme");
            Iterator<String> it2 = this.mSchemePrefix.iterator();
            while (it2.hasNext()) {
                String str2 = it2.next() + strReplace;
                SQLog.d("【Pay Way】[" + getSimpleName() + "]尝试: " + str2);
                intent.setData(Uri.parse(str2));
                if (canHandleIntent(context, intent)) {
                    SQLog.i("【Pay Way】[" + getSimpleName() + "]匹配: " + str2);
                    return intent;
                }
            }
            SQLog.w("【Pay Way】[" + getSimpleName() + "]无应用可以处理跳转" + str);
            return null;
        } catch (Exception unused) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]url解析异常, 无法支付: " + str);
            BuglessAction.reportCatchException(new IllegalStateException("异常的抖音url"), str, BuglessAction.PAY_ERROR);
            return null;
        }
    }

    private boolean isMatchDouYinPaySchemePrefix(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Iterator<String> it = this.mSchemePrefix.iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }

    private static boolean canHandleUrl(Context context, String str) {
        try {
            Uri uri = Uri.parse(str);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(uri);
            return canHandleIntent(context, intent);
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean canHandleIntent(Context context, Intent intent) {
        return !context.getPackageManager().queryIntentActivities(intent, 65536).isEmpty();
    }
}
