package com.sy37sdk.order.third.union;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.JsonMap;
import com.sqwan.order.base.PayWay;
import com.sy37sdk.order.PayOrderModel;
import com.sy37sdk.order.third.IPayWay;
import com.sy37sdk.order.web.PayWebPage;
import com.taptap.sdk.kit.internal.p000const.TrackAction;
import com.unionpay.UPPayAssistEx;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class UnionPayWay implements IPayWay {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ERROR_GO_PAY_PAGE = 403;
    public static final int ERROR_INVALID_RESULT = 404;
    public static final int ERROR_ORDER_ID = 402;
    public static final int ERROR_PARAM = 401;
    public static final int ERROR_PAY = 400;
    private UnionChatOrder mCurrentOrder;

    /* JADX INFO: Access modifiers changed from: private */
    public String getSimpleName() {
        return "Union";
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void init(Context context) {
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void onPause(Activity activity) {
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void onResume(Activity activity) {
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void onStart(Activity activity) {
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void onStop(Activity activity) {
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        SQLog.v("【Pay Way】[" + getSimpleName() + "]onActivityResult, requestCode=" + i + ", data=" + intent);
        UnionChatOrder unionChatOrder = this.mCurrentOrder;
        if (unionChatOrder == null || intent == null || i != 10) {
            return;
        }
        PayOrderModel payOrderModel = unionChatOrder.order;
        InternalCallback internalCallback = unionChatOrder.callback;
        SQLog.d("【Pay Way】[" + getSimpleName() + "]onActivityResult, 支付回调, " + intent);
        SQLog.d("【Pay Way】[" + getSimpleName() + "]当前订单moid=" + payOrderModel.getMoid());
        String stringExtra = intent.getStringExtra(PayWebPage.BUNDLE_RESULT_PAY);
        String stringExtra2 = intent.getStringExtra("result_data");
        SQLog.d("【Pay Way】[" + getSimpleName() + "]支付结果, result=" + stringExtra + ", data=" + stringExtra2);
        if ("success".equalsIgnoreCase(stringExtra)) {
            SQLog.i("【Pay Way】[" + getSimpleName() + "]支付成功");
            internalCallback.onSuccess(getName(), payOrderModel);
            return;
        }
        if (TrackAction.FAIL.equalsIgnoreCase(stringExtra)) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]支付失败: " + stringExtra);
            internalCallback.onFailed(getName(), payOrderModel, 400, "支付失败");
            return;
        }
        if ("cancel".equalsIgnoreCase(stringExtra)) {
            SQLog.w("【Pay Way】[" + getSimpleName() + "]用户取消支付");
            internalCallback.onCancel(getName(), payOrderModel);
            return;
        }
        SQLog.e("【Pay Way】[" + getSimpleName() + "]支付失败: " + stringExtra);
        JsonMap jsonMap = new JsonMap();
        jsonMap.put(SqTrackKey.order_id, payOrderModel.getMoid());
        jsonMap.put("third_order_id", unionChatOrder.tn);
        jsonMap.put(PayWebPage.BUNDLE_RESULT_PAY, stringExtra);
        jsonMap.put("result_data", stringExtra2);
        BuglessAction.reportCatchException(new IllegalStateException("云闪付支付结果异常"), jsonMap.toString(), BuglessAction.PAY_ERROR);
        internalCallback.onFailed(getName(), payOrderModel, ERROR_INVALID_RESULT, "未知支付结果");
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
            SQLog.e("【Pay Way】[" + getSimpleName() + "]无tn, 无法支付");
            internalCallbackWrapCallback.onFailed(getName(), payOrderModel, ERROR_ORDER_ID, "订单号异常");
            return;
        }
        SQLog.d("【Pay Way】[" + getSimpleName() + "]tn=" + string);
        UnionChatOrder unionChatOrder = this.mCurrentOrder;
        if (unionChatOrder != null) {
            SQLog.w("【Pay Way】[" + getSimpleName() + "]覆盖订单: " + unionChatOrder.order.getMoid());
        }
        this.mCurrentOrder = new UnionChatOrder(string, payOrderModel, internalCallbackWrapCallback);
        try {
            SQLog.d("【Pay Way】[" + getSimpleName() + "]调起云闪付, tn=" + string);
            UPPayAssistEx.startPay(activity, (String) null, (String) null, string, "00");
        } catch (Exception e) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]跳转云闪付支付界面失败", e);
            BuglessAction.reportCatchException(e, "跳转云闪付支付界面失败", 103);
            internalCallbackWrapCallback.onFailed(getName(), payOrderModel, ERROR_GO_PAY_PAGE, "无法跳转云闪付");
        }
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public PayWay getName() {
        return PayWay.UNION;
    }

    private static class UnionChatOrder {
        InternalCallback callback;
        final PayOrderModel order;
        final String tn;

        private UnionChatOrder(String str, PayOrderModel payOrderModel, InternalCallback internalCallback) {
            this.tn = str;
            this.order = payOrderModel;
            this.callback = internalCallback;
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
            UnionChatOrder unionChatOrder = UnionPayWay.this.mCurrentOrder;
            if (unionChatOrder != null) {
                SQLog.d("【Pay Way】[" + UnionPayWay.this.getSimpleName() + "]" + unionChatOrder.order.getMoid() + "订单回调(" + str + ")");
            }
            UnionPayWay.this.mCurrentOrder = null;
        }
    }
}
