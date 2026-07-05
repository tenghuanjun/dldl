package com.sy37sdk.order.third.ali;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.alipay.sdk.app.PayTask;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sy37sdk.order.third.ali.AliPayWay;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AliPay {
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public interface AliPayCallback {
        void onCancel();

        void onFailure(int i, String str);

        void onSuccess();
    }

    public void pay(final Activity activity, final String str, final String str2, final AliPayCallback aliPayCallback) {
        SQLog.i("调起阿里支付: " + str + ", " + str2);
        reportInvoke(str);
        if (TextUtils.isEmpty(str2)) {
            SQLog.e("阿里支付参数异常, 支付失败");
            reportFail(str, -1, "支付参数异常");
            if (aliPayCallback != null) {
                aliPayCallback.onFailure(-1, "支付参数异常");
                return;
            }
            return;
        }
        AliPayWay.WORKER.execute(new Runnable() { // from class: com.sy37sdk.order.third.ali.-$$Lambda$AliPay$fc8gti-8VDQjh3VN2wBwfptROVw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$pay$1$AliPay(activity, str2, str, aliPayCallback);
            }
        });
    }

    public /* synthetic */ void lambda$pay$1$AliPay(Activity activity, String str, final String str2, final AliPayCallback aliPayCallback) {
        final Map mapPayV2 = new PayTask(activity).payV2(str, true);
        SQLog.d("阿里支付回调结果: " + mapPayV2);
        this.mHandler.post(new Runnable() { // from class: com.sy37sdk.order.third.ali.-$$Lambda$AliPay$vVC5K7ofZAxknzRZhCx0_pjJ1tU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$null$0$AliPay(str2, mapPayV2, aliPayCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handlePayResult, reason: merged with bridge method [inline-methods] */
    public void lambda$null$0$AliPay(String str, Map<String, String> map, AliPayCallback aliPayCallback) {
        AliPayWay.AliPayResult aliPayResult = new AliPayWay.AliPayResult(map);
        if (!aliPayResult.isValid()) {
            SQLog.e("阿里支付结果解析异常, 支付失败: " + map);
            reportFail(str, -1, "支付结果解析异常");
            if (aliPayCallback != null) {
                aliPayCallback.onFailure(-1, "支付结果解析异常");
                return;
            }
            return;
        }
        if (aliPayResult.isSuccess()) {
            SQLog.i("阿里支付成功");
            reportSuccess(str);
            if (aliPayCallback != null) {
                aliPayCallback.onSuccess();
                return;
            }
            return;
        }
        if (aliPayResult.isCancel()) {
            SQLog.w("用户取消阿里支付");
            reportCancel(str);
            if (aliPayCallback != null) {
                aliPayCallback.onCancel();
                return;
            }
            return;
        }
        SQLog.e("阿里支付失败: " + map);
        int errorCode = aliPayResult.getErrorCode();
        String errorMsg = aliPayResult.getErrorMsg();
        reportFail(str, errorCode, errorMsg);
        if (aliPayCallback != null) {
            aliPayCallback.onFailure(errorCode, errorMsg);
        }
    }

    private void reportInvoke(String str) {
        HashMap<String, String> map = new HashMap<>();
        map.put(SqTrackKey.order_id, str);
        SqTrackActionManager2.getInstance().trackAction("get_android_sdk_to_alipay", "请求从安卓SDK调起支付宝", map);
    }

    private void reportSuccess(String str) {
        reportResult(str, "成功", null, null);
    }

    private void reportCancel(String str) {
        reportResult(str, "取消", null, null);
    }

    private void reportFail(String str, int i, String str2) {
        reportResult(str, "失败", String.valueOf(i), str2);
    }

    private void reportResult(String str, String str2, String str3, String str4) {
        HashMap<String, String> map = new HashMap<>();
        map.put(SqTrackKey.order_id, str);
        map.put("result", str2);
        if (str3 != null) {
            map.put(SqTrackKey.fail_code, str3);
        }
        if (str4 != null) {
            map.put(SqTrackKey.reason_fail, str4);
        }
        SqTrackActionManager2.getInstance().trackAction("android_sdk_to_alipay_result", "支付结果", map);
    }
}
