package com.sy37sdk.order;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.order.IOrderMod;
import com.sqwan.msdk.api.MultiSDKUtils;
import com.sqwan.msdk.api.popup.BeforePayPopupDialogManager;
import com.sqwan.order.base.IPay;
import com.sqwan.order.base.PayContext;
import com.sqwan.order.base.PayInfoModel;
import com.sqwan.order.base.SqPayError;
import com.sy37sdk.account.auth.AuthBean;
import com.sy37sdk.account.auth.AuthManager;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQPay implements IPay {
    public static final String EXTRA_CHECK_AUTH = "check_auth";
    private static final String TAG = "【Pay 37】";

    @Override // com.sqwan.order.base.IPay
    public void init(Context context) {
    }

    public void pay(Activity activity, PayContext payContext, PayInfoModel payInfoModel, Bundle bundle, boolean z, IPay.PayCallback payCallback) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        bundle2.putBoolean(EXTRA_CHECK_AUTH, z);
        pay(activity, payContext, payInfoModel, bundle2, payCallback);
    }

    @Override // com.sqwan.order.base.IPay
    public void pay(Activity activity, PayContext payContext, PayInfoModel payInfoModel, Bundle bundle, IPay.PayCallback payCallback) {
        SQLog.d("【Pay 37】37支付, extra=" + bundle);
        boolean z = true;
        boolean z2 = bundle != null ? bundle.getBoolean(EXTRA_CHECK_AUTH, true) : true;
        IPay.PayCallback payCallbackWrap = IPay.UIPayCallback.wrap(payCallback);
        if (z2) {
            SQLog.d("【Pay 37】检查实名认证");
            AuthBean authBean = parseAuthBean(payContext);
            if ((authBean == null || !authBean.needAuth() || TextUtils.isEmpty(authBean.getUrl())) ? false : true) {
                SQLog.d("【Pay 37】需要实名认证");
                showAuthDialog(activity, authBean, payContext, payInfoModel, bundle, payCallbackWrap);
                return;
            }
            SQLog.d("【Pay 37】无需实名认证, 检查防沉迷配置");
            PayAddictionBean payAddictionBean = PayAddictionBean.parse(payContext);
            if (payAddictionBean != null && !payAddictionBean.allowRecharge) {
                z = false;
            }
            if (z) {
                SQLog.d("【Pay 37】防沉迷允许支付, 继续支付");
                payInternal(activity, payContext, payInfoModel, bundle, payCallbackWrap);
                return;
            } else {
                SQLog.e("【Pay 37】防沉迷配置限制支付, 回调支付失败");
                payCallbackWrap.onFailed(payContext, new SqPayError(SqPayError.ERROR_SDK_ADDITION, "防沉迷配置限制支付", -1, payAddictionBean.toastContent));
                return;
            }
        }
        SQLog.d("【Pay 37】无需检查实名认证, 继续支付");
        payInternal(activity, payContext, payInfoModel, bundle, payCallbackWrap);
    }

    private AuthBean parseAuthBean(PayContext payContext) {
        JSONObject orderDataJson = payContext.getOrderDataJson();
        if (orderDataJson == null) {
            return null;
        }
        try {
            JSONObject jSONObjectOptJSONObject = orderDataJson.optJSONObject("auth");
            SQLog.d("【Pay 37】auth=" + jSONObjectOptJSONObject);
            if (jSONObjectOptJSONObject != null) {
                return AuthBean.parseFromJson(jSONObjectOptJSONObject);
            }
        } catch (Exception e) {
            SQLog.w("【Pay 37】解析auth配置异常", e);
        }
        return null;
    }

    private void showAuthDialog(final Activity activity, final AuthBean authBean, final PayContext payContext, final PayInfoModel payInfoModel, final Bundle bundle, final IPay.PayCallback payCallback) {
        String url = authBean.getUrl();
        SQLog.d("【Pay 37】显示实名认证弹窗: " + url);
        AuthManager.getInstance(activity).showAuthDialog(url, authBean.isFocus(), false, new AuthManager.AuthCallbackAdapter() { // from class: com.sy37sdk.order.SQPay.1
            @Override // com.sy37sdk.account.auth.AuthManager.AuthCallbackAdapter, com.sy37sdk.account.auth.AuthManager.AuthCallback
            public void onSuccess() {
                SQLog.e("【Pay 37】实名认证成功, 需要重新下单, 回调支付失败");
                payCallback.onFailed(payContext, new SqPayError(SqPayError.ERROR_SDK_AUTH, "实名认证成功，需要重新下单", -1));
            }

            @Override // com.sy37sdk.account.auth.AuthManager.AuthCallbackAdapter, com.sy37sdk.account.auth.AuthManager.AuthCallback
            public void onFailure() {
                if (authBean.getAllowRecharge()) {
                    SQLog.w("【Pay 37】实名认证失败, 但允许支付, 继续支付");
                    SQPay.this.payInternal(activity, payContext, payInfoModel, bundle, payCallback);
                } else {
                    SQLog.e("【Pay 37】实名认证失败, 回调支付失败");
                    payCallback.onFailed(payContext, new SqPayError(SqPayError.ERROR_SDK_AUTH, "未实名认证", -2));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void payInternal(Activity activity, PayContext payContext, PayInfoModel payInfoModel, Bundle bundle, IPay.PayCallback payCallback) {
        if (!(!TextUtils.isEmpty(MultiSDKUtils.getToken(activity)))) {
            SQLog.e("【Pay 37】未登录, 回调支付失败");
            payCallback.onFailed(payContext, new SqPayError(SqPayError.ERROR_SDK_USER_ERROR, "用户信息已过期，请重新登录", -1));
            return;
        }
        if (TextUtils.isEmpty(payInfoModel.getServerId())) {
            SQLog.e("【Pay 37】区服ID为空, 回调支付失败");
            payCallback.onFailed(payContext, new SqPayError(SqPayError.ERROR_SDK_USER_ERROR, "区服ID不能为空", -2));
            return;
        }
        String moid = payContext.getMoid();
        SQLog.d("【Pay 37】moid=" + moid);
        IOrderMod iOrderMod = (IOrderMod) ModHelper.get(IOrderMod.class);
        if (iOrderMod == null) {
            SQLog.e("【Pay 37】缺少支付模块, 回调支付失败");
            payCallback.onFailed(payContext, new SqPayError(SqPayError.ERROR_SDK_UNSUPPORTED, "无法支付", -3, "缺少支付模块"));
        } else {
            iOrderMod.pay(activity, payContext, payInfoModel, bundle, payCallback);
            SQLog.d("【Pay 37】触发支付前弹窗");
            BeforePayPopupDialogManager.getInstance().handlePopup(activity, moid, payInfoModel.getOrderId(), String.valueOf(payInfoModel.getMoney()), String.valueOf(payInfoModel.getRoleLevel()), payInfoModel.getProductName());
        }
    }

    private static class PayAddictionBean {
        final boolean allowRecharge;
        final String toastContent;

        public PayAddictionBean(boolean z, String str) {
            this.toastContent = str;
            this.allowRecharge = z;
        }

        static PayAddictionBean parse(PayContext payContext) {
            JSONObject orderDataJson = payContext.getOrderDataJson();
            if (orderDataJson == null) {
                return null;
            }
            try {
                JSONObject jSONObjectOptJSONObject = orderDataJson.optJSONObject("addiction");
                SQLog.d("【Pay 37】addiction=" + jSONObjectOptJSONObject);
                if (jSONObjectOptJSONObject != null) {
                    String strOptString = jSONObjectOptJSONObject.optString("toastContent");
                    boolean z = true;
                    if (jSONObjectOptJSONObject.optInt("allowRecharge") != 1) {
                        z = false;
                    }
                    return new PayAddictionBean(z, strOptString);
                }
            } catch (Exception e) {
                SQLog.w("【Pay 37】解析addiction配置异常", e);
            }
            return null;
        }
    }
}
