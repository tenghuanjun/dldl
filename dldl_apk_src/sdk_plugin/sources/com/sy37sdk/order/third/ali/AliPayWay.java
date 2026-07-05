package com.sy37sdk.order.third.ali;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.alipay.sdk.app.PayTask;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.JsonMap;
import com.sqwan.order.base.PayWay;
import com.sy37sdk.order.PayOrderModel;
import com.sy37sdk.order.third.CheckOrderManager;
import com.sy37sdk.order.third.IPayWay;
import com.sy37sdk.order.third.ThirdPayManager;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AliPayWay implements IPayWay {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ERROR_INVALID_RESULT = 204;
    public static final int ERROR_ORDER_ID = 202;
    public static final int ERROR_PARAM = 201;
    public static final int ERROR_TRADE = 203;
    private static final String HTTP_PAY_WAY = "alipay";
    static final Executor WORKER = Executors.newSingleThreadExecutor();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    public String getSimpleName() {
        return "Ali";
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
    public void onResume(Activity activity) {
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void onStart(Activity activity) {
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void onStop(Activity activity) {
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public void pay(final Activity activity, final PayOrderModel payOrderModel, Bundle bundle, IPayWay.PayWayCallback payWayCallback) {
        SQLog.d("【Pay Way】[" + getSimpleName() + "]支付:\n" + payOrderModel);
        SQLog.d("【Pay Way】[" + getSimpleName() + "]extra: " + bundle);
        final IPayWay.PayWayCallback payWayCallbackWrap = IPayWay.UIPayWayCallback.wrap(payWayCallback);
        if (bundle == null) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]无额外参数, 无法支付");
            payWayCallbackWrap.onFailed(getName(), payOrderModel, 201, "无参数");
            return;
        }
        final String string = bundle.getString("third_order_id");
        if (TextUtils.isEmpty(string)) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]无uuid, 无法支付");
            payWayCallbackWrap.onFailed(getName(), payOrderModel, 202, "订单号异常");
            return;
        }
        SQLog.d("【Pay Way】[" + getSimpleName() + "]uuid=" + string);
        final String string2 = bundle.getString(ThirdPayManager.EXTRA_TRADE_INFO);
        if (TextUtils.isEmpty(string2)) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]无tradeInfo, 无法支付");
            if (payWayCallback != null) {
                payWayCallbackWrap.onFailed(getName(), payOrderModel, 203, "支付参数异常");
                return;
            }
            return;
        }
        WORKER.execute(new Runnable() { // from class: com.sy37sdk.order.third.ali.-$$Lambda$AliPayWay$g7wadoOsiEqkO6E9Rk-K_gYq1Yo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$pay$1$AliPayWay(string2, activity, string, payOrderModel, payWayCallbackWrap);
            }
        });
    }

    public /* synthetic */ void lambda$pay$1$AliPayWay(String str, Activity activity, final String str2, final PayOrderModel payOrderModel, final IPayWay.PayWayCallback payWayCallback) {
        SQLog.d("【Pay Way】[" + getSimpleName() + "]调起支付宝, trade=" + str);
        final Map mapPayV2 = new PayTask(activity).payV2(str, true);
        SQLog.d("【Pay Way】[" + getSimpleName() + "]支付回调结果: " + mapPayV2 + ", uuid=" + str2);
        this.mHandler.post(new Runnable() { // from class: com.sy37sdk.order.third.ali.-$$Lambda$AliPayWay$7LqFDXcLCg3_qUsB6aFe8iZ5zt8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$null$0$AliPayWay(payOrderModel, str2, mapPayV2, payWayCallback);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$AliPayWay(PayOrderModel payOrderModel, String str, Map map, IPayWay.PayWayCallback payWayCallback) {
        handlePayResult(payOrderModel, str, new AliPayResult(map), payWayCallback);
    }

    private void handlePayResult(PayOrderModel payOrderModel, String str, AliPayResult aliPayResult, final IPayWay.PayWayCallback payWayCallback) {
        if (!aliPayResult.isValid()) {
            SQLog.e("【Pay Way】[" + getSimpleName() + "]结果解析异常, 支付失败: " + aliPayResult);
            JsonMap jsonMap = new JsonMap();
            jsonMap.put(SqTrackKey.order_id, payOrderModel.getMoid());
            jsonMap.put("third_order_id", str);
            jsonMap.put("result_map", String.valueOf(aliPayResult.raw));
            BuglessAction.reportCatchException(new IllegalStateException("支付宝支付结果解析异常"), jsonMap.toString(), BuglessAction.PAY_ERROR);
            payWayCallback.onFailed(getName(), payOrderModel, 204, "支付结果解析异常");
            return;
        }
        if (aliPayResult.isSuccess()) {
            SQLog.i("【Pay Way】[" + getSimpleName() + "]支付成功, 检查支付状态");
            CheckOrderManager.getInstance().check(payOrderModel, str, "alipay", new CheckOrderManager.CheckOrderCallback() { // from class: com.sy37sdk.order.third.ali.AliPayWay.1
                @Override // com.sy37sdk.order.third.CheckOrderManager.CheckOrderCallback
                public void onSuccess(PayOrderModel payOrderModel2, String str2) {
                    payWayCallback.onSuccess(AliPayWay.this.getName(), payOrderModel2);
                }

                @Override // com.sy37sdk.order.third.CheckOrderManager.CheckOrderCallback
                public void onFailure(PayOrderModel payOrderModel2, String str2, int i, String str3) {
                    SQLog.w("【Pay Way】[" + AliPayWay.this.getSimpleName() + "]支付成功, 查单失败, 返回失败, uuid=" + str2);
                    JsonMap jsonMap2 = new JsonMap();
                    jsonMap2.put(SqTrackKey.order_id, payOrderModel2.getMoid());
                    jsonMap2.put("third_order_id", str2);
                    jsonMap2.put("code", Integer.valueOf(i));
                    jsonMap2.put("msg", str3);
                    BuglessAction.reportCatchException(new IllegalStateException("支付宝支付成功, 查单失败"), jsonMap2.toString(), BuglessAction.PAY_ERROR);
                    payWayCallback.onFailed(AliPayWay.this.getName(), payOrderModel2, i, str3);
                }
            });
            return;
        }
        if (aliPayResult.isCancel()) {
            SQLog.w("【Pay Way】[" + getSimpleName() + "]用户取消支付");
            payWayCallback.onCancel(getName(), payOrderModel);
            return;
        }
        SQLog.e("【Pay Way】[" + getSimpleName() + "]支付失败: " + aliPayResult);
        payWayCallback.onFailed(getName(), payOrderModel, aliPayResult.getErrorCode(), aliPayResult.getErrorMsg());
    }

    @Override // com.sy37sdk.order.third.IPayWay
    public PayWay getName() {
        return PayWay.ALI;
    }

    static class AliPayResult {
        static final String CODE_CANCEL = "6001";
        static final String CODE_SUCCESS = "9000";
        final String memo;
        final Map<String, String> raw;
        final String resultJsonStr;
        final String resultStatus;

        AliPayResult(Map<String, String> map) {
            this.raw = map;
            if (map != null) {
                this.resultStatus = map.get("resultStatus");
                this.resultJsonStr = map.get("result");
                this.memo = map.get("memo");
            } else {
                this.resultStatus = null;
                this.resultJsonStr = null;
                this.memo = null;
            }
        }

        boolean isValid() {
            return !TextUtils.isEmpty(this.resultStatus);
        }

        boolean isSuccess() {
            return "9000".equals(this.resultStatus);
        }

        boolean isCancel() {
            return "6001".equals(this.resultStatus);
        }

        int getErrorCode() {
            try {
                return Integer.parseInt(this.resultStatus);
            } catch (Exception unused) {
                return -1;
            }
        }

        String getErrorMsg() {
            String str = null;
            if (!TextUtils.isEmpty(this.resultJsonStr)) {
                try {
                    JSONObject jSONObjectOptJSONObject = new JSONObject(this.resultJsonStr).optJSONObject("alipay_trade_app_pay_response");
                    if (jSONObjectOptJSONObject != null) {
                        String strOptString = jSONObjectOptJSONObject.optString("msg");
                        String strOptString2 = jSONObjectOptJSONObject.optString("sub_msg");
                        if (!TextUtils.isEmpty(strOptString)) {
                            if (TextUtils.isEmpty(strOptString2)) {
                                str = strOptString;
                            } else {
                                str = strOptString + "/" + strOptString2;
                            }
                        }
                    }
                } catch (Exception unused) {
                }
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            if (TextUtils.isEmpty(this.memo)) {
                return TextUtils.isEmpty(this.resultStatus) ? "unknown" : this.resultStatus;
            }
            return this.memo;
        }

        public String toString() {
            return "resultStatus=" + this.resultStatus + ", resultJson=" + this.resultJsonStr + ", memo=" + this.memo;
        }
    }
}
