package com.sqwan.msdk.api.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.route.FunctionRouter;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.JsonMap;
import com.sqwan.common.util.SqAtyRef;
import com.sqwan.common.util.SqRequestCallBack;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.ZipUtil;
import com.sqwan.msdk.BaseSQwanCore;
import com.sqwan.msdk.api.MRequestCallBack;
import com.sqwan.msdk.api.MRequestManager;
import com.sqwan.msdk.api.MultiSDKUtils;
import com.sqwan.msdk.api.SQResultListener;
import com.sqwan.msdk.api.popup.PayPopupDialogManager;
import com.sqwan.msdk.api.sdk.pay.PayReporter;
import com.sqwan.order.base.IPay;
import com.sqwan.order.base.PayContext;
import com.sqwan.order.base.PayInfoModel;
import com.sqwan.order.base.SqPayError;
import com.sy37sdk.account.auth.AuthConfigCache;
import com.sy37sdk.order.OrderTrackManager;
import com.sy37sdk.order.SQPay;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SqPayManager {
    public static final String EXTRA_PDATA = "extra_pdata";
    private static final String TAG = "【Pay Manager】";
    private static volatile SqPayManager sInstance;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private Platform mPlatform;
    private IPay mPlatformPay;
    private SQPay mSQPay;

    private interface CreateOrderCallback {
        void onFailed(PayContext payContext, int i, String str);

        void onSuccess(PayContext payContext, OrderConfig orderConfig);
    }

    public static SqPayManager getInstance() {
        if (sInstance == null) {
            synchronized (SqPayManager.class) {
                if (sInstance == null) {
                    sInstance = new SqPayManager();
                }
            }
        }
        return sInstance;
    }

    private SqPayManager() {
    }

    @Deprecated
    public void setPlatform(Platform platform) {
        SQLog.w("【Pay Manager】(兼容方式)设置平台支付实例: " + platform);
        this.mPlatform = platform;
    }

    public void setSQPay(SQPay sQPay) {
        SQLog.d("【Pay Manager】设置37支付实例: " + sQPay);
        this.mSQPay = sQPay;
    }

    public void setPlatformPay(IPay iPay) {
        SQLog.d("【Pay Manager】设置平台支付实例: " + iPay);
        this.mPlatformPay = iPay;
    }

    public void pay(final Activity activity, final PayContext payContext, final PayInfoModel payInfoModel, final Bundle bundle, final IPay.PayCallback payCallback) {
        SQLog.d("【Pay Manager】调起支付: \n" + payInfoModel);
        final IPay.PayCallback payCallbackWrap = IPay.UIPayCallback.wrap(new IPay.PayCallback() { // from class: com.sqwan.msdk.api.sdk.SqPayManager.1
            @Override // com.sqwan.order.base.IPay.PayCallback
            public void onSuccess(PayContext payContext2) {
                SQLog.i("【Pay Manager】支付成功, moid=" + payContext2.getMoid());
                SqPayManager.this.showPayResultTip("支付成功");
                PayReporter.reportPaySuccess(payContext2);
                IPay.PayCallback payCallback2 = payCallback;
                if (payCallback2 != null) {
                    payCallback2.onSuccess(payContext2);
                }
                OrderTrackManager.reportToMedia(payInfoModel, payContext.getMoid(), true);
                SQLog.d("【Pay Manager】支付成功, 检查弹窗");
                SqPayManager.this.checkAfterPayPopup(activity, payContext2, 1);
            }

            @Override // com.sqwan.order.base.IPay.PayCallback
            public void onCancel(PayContext payContext2) {
                SQLog.w("【Pay Manager】取消支付, moid=" + payContext2.getMoid());
                SqPayManager.this.showPayResultTip("取消支付");
                PayReporter.reportPayCancel(payContext2);
                IPay.PayCallback payCallback2 = payCallback;
                if (payCallback2 != null) {
                    payCallback2.onCancel(payContext2);
                }
                OrderTrackManager.reportToMedia(payInfoModel, payContext.getMoid(), false);
                SQLog.d("【Pay Manager】支付取消, 检查弹窗");
                SqPayManager.this.checkAfterPayPopup(activity, payContext2, 3);
            }

            @Override // com.sqwan.order.base.IPay.PayCallback
            public void onFailed(PayContext payContext2, SqPayError sqPayError) {
                SQLog.w("【Pay Manager】支付失败, moid=" + payContext2.getMoid() + ", " + sqPayError);
                SqPayManager.this.showPayFailTip(sqPayError);
                PayReporter.reportPayFail(payContext2, sqPayError);
                IPay.PayCallback payCallback2 = payCallback;
                if (payCallback2 != null) {
                    payCallback2.onFailed(payContext2, sqPayError);
                }
                OrderTrackManager.reportToMedia(payInfoModel, payContext.getMoid(), false);
                SQLog.d("【Pay Manager】支付失败, 检查弹窗");
                SqPayManager.this.checkAfterPayPopup(activity, payContext2, 2);
            }
        });
        createOrder(activity, payContext, payInfoModel, bundle, new CreateOrderCallback() { // from class: com.sqwan.msdk.api.sdk.SqPayManager.2
            @Override // com.sqwan.msdk.api.sdk.SqPayManager.CreateOrderCallback
            public void onSuccess(final PayContext payContext2, OrderConfig orderConfig) {
                PayReporter.reportPayInvoke(payContext2, payInfoModel);
                if (orderConfig.disablePay) {
                    SQLog.w("【Pay Manager】下单禁用支付");
                    payCallbackWrap.onFailed(payContext2, new SqPayError(SqPayError.ERROR_SDK_DISABLE_PAY, "未开启支付功能"));
                    return;
                }
                if (orderConfig.cutTo37) {
                    SQLog.w("【Pay Manager】下单转移到37支付");
                    if (SqPayManager.this.mSQPay != null) {
                        SqPayManager.this.mSQPay.pay(activity, payContext2, payInfoModel, bundle, false, payCallbackWrap);
                        return;
                    } else {
                        SQLog.e("【Pay Manager】无法调用37支付");
                        payCallbackWrap.onFailed(payContext2, new SqPayError(SqPayError.ERROR_SDK_UNSUPPORTED, "无法支付", -1, "无法调用37支付"));
                        return;
                    }
                }
                SQLog.d("【Pay Manager】走平台支付");
                if (SqPayManager.this.mPlatformPay != null) {
                    SqPayManager.this.mPlatformPay.pay(activity, payContext2, payInfoModel, bundle, payCallbackWrap);
                } else if (SqPayManager.this.mPlatform != null) {
                    SQLog.w("【Pay Manager】使用兼容方式调用平台支付");
                    SqPayManager.this.mPlatform.payPlatform(activity, payInfoModel.getOrderId(), payInfoModel.getProductName(), payInfoModel.getCurrencyName(), payInfoModel.getServerId(), payInfoModel.getServerName(), payInfoModel.getExtend(), payInfoModel.getRoleId(), payInfoModel.getRoleName(), payInfoModel.getRoleLevel(), payInfoModel.getMoney(), payInfoModel.getRadio(), payContext2.getMoid(), payContext2.getRawOrderData(), new SQResultListener() { // from class: com.sqwan.msdk.api.sdk.SqPayManager.2.1
                        public void onSuccess(Bundle bundle2) {
                            SQLog.i("【Pay Manager】(兼容方式)平台支付成功");
                            payCallbackWrap.onSuccess(payContext2);
                        }

                        public void onFailture(int i, String str) {
                            SQLog.w("【Pay Manager】(兼容方式)平台支付失败, code=" + i + ", msg=" + str);
                            if (i == 205) {
                                payCallbackWrap.onCancel(payContext2);
                            } else {
                                payCallbackWrap.onFailed(payContext2, new SqPayError(SqPayError.ERROR_THIRD_PURCHASE, "平台支付失败", i, str));
                            }
                        }
                    });
                } else {
                    SQLog.e("【Pay Manager】无法调用平台支付");
                    payCallbackWrap.onFailed(payContext2, new SqPayError(SqPayError.ERROR_SDK_UNSUPPORTED, "无法支付", -2, "无法调用平台支付"));
                }
            }

            @Override // com.sqwan.msdk.api.sdk.SqPayManager.CreateOrderCallback
            public void onFailed(PayContext payContext2, int i, String str) {
                payCallbackWrap.onFailed(payContext2, new SqPayError(SqPayError.ERROR_HTTP_CREATE_ORDER, "下单失败", i, str));
            }
        });
    }

    private void createOrder(Activity activity, final PayContext payContext, final PayInfoModel payInfoModel, final Bundle bundle, final CreateOrderCallback createOrderCallback) {
        final Context applicationContext = activity.getApplicationContext();
        String string = bundle != null ? bundle.getString(EXTRA_PDATA, "") : "";
        SQLog.d("【Pay Manager】请求下单接口, pdata=" + string);
        new MRequestManager(activity).orderRequest(payInfoModel, string, new SqRequestCallBack() { // from class: com.sqwan.msdk.api.sdk.SqPayManager.3
            @Override // com.sqwan.common.util.SqRequestCallBack, com.sqwan.common.util.BaseRequestCallBack
            public void onRequestSuccess(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    int i = jSONObject.getInt("state");
                    if (i == 1) {
                        String string2 = jSONObject.getString(FunctionRouter.KEY_DATA);
                        SQLog.i("【Pay Manager】下单成功, " + string2);
                        JSONObject jSONObject2 = new JSONObject(string2);
                        SqPayManager.this.updateAuthConfig(jSONObject2);
                        payContext.setRawOrderData(string2);
                        payContext.setOrderDataJson(jSONObject2);
                        String string3 = jSONObject2.getString(SqConstants.MOID);
                        SQLog.i("【Pay Manager】moid=" + string3);
                        if (!TextUtils.isEmpty(string3)) {
                            payContext.setMoid(string3);
                            createOrderCallback.onSuccess(payContext, new OrderConfig(applicationContext, jSONObject2));
                        } else {
                            SQLog.e("【Pay Manager】下单接口成功, 获取moid失败");
                            createOrderCallback.onFailed(payContext, -2, "支付异常");
                        }
                    } else {
                        String strOptString = jSONObject.optString("msg");
                        SQLog.e("【Pay Manager】下单失败, state=" + i + ", msg=" + strOptString);
                        createOrderCallback.onFailed(payContext, i, strOptString);
                    }
                } catch (Exception e) {
                    SQLog.e("【Pay Manager】下单结果解析异常", e);
                    BuglessAction.reportCatchException(e, str, 4);
                    createOrderCallback.onFailed(payContext, -1, "支付异常");
                }
            }

            @Override // com.sqwan.common.util.SqRequestCallBack, com.sqwan.common.util.BaseRequestCallBack
            public void onRequestError(int i, String str) {
                SQLog.e("【Pay Manager】下单接口异常, " + str);
                JsonMap jsonMap = new JsonMap();
                jsonMap.put(SqTrackKey.cp_order_id, payInfoModel.getOrderId());
                Bundle bundle2 = bundle;
                jsonMap.put(SqConstants.PDATA, bundle2 != null ? bundle2.getString(SqPayManager.EXTRA_PDATA, "") : "");
                jsonMap.put("code", Integer.valueOf(i));
                jsonMap.put("msg", str);
                BuglessAction.reportCatchException(new Exception(), jsonMap.toString(), 4);
                createOrderCallback.onFailed(payContext, i, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAuthConfig(JSONObject jSONObject) {
        String strOptString = jSONObject.optString("auth");
        if (!TextUtils.isEmpty(strOptString)) {
            SQLog.i("【Pay Manager】刷新实名认证信息: " + strOptString);
            AuthConfigCache.saveAuthConfig(strOptString);
            return;
        }
        SQLog.w("【Pay Manager】清空实名认证信息");
        AuthConfigCache.clearAuthConfig();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAfterPayPopup(Activity activity, PayContext payContext, int i) {
        String strOptString;
        String moid = payContext.getMoid();
        JSONObject orderDataJson = payContext.getOrderDataJson();
        if (orderDataJson != null) {
            z = orderDataJson.optInt("pon", 0) == 1;
            strOptString = orderDataJson.optString("psapi");
        } else {
            strOptString = null;
        }
        if (z && !TextUtils.isEmpty(moid) && !TextUtils.isEmpty(strOptString)) {
            SQLog.i("【Pay Manager】显示支付公告框, moid=" + moid + ", url=" + strOptString);
            showNoticeAfterPay(activity, strOptString, moid, 1);
        } else {
            SQLog.d("【Pay Manager】无需显示支付公告框, moid=" + moid);
        }
        PayInfoModel payInfo = payContext.getPayInfo();
        if (payInfo == null || TextUtils.isEmpty(moid)) {
            return;
        }
        SQLog.i("【Pay Manager】检查支付后弹窗, moid=" + moid);
        PayPopupDialogManager.getInstance().handlePopup(activity, payInfo.getOrderId(), moid, String.valueOf(payInfo.getMoney()), String.valueOf(i));
    }

    private static class OrderConfig {
        boolean cutTo37;
        boolean disablePay;

        public OrderConfig(Context context, JSONObject jSONObject) {
            String strOptString = jSONObject.optString("sdata");
            if (TextUtils.isEmpty(strOptString)) {
                return;
            }
            String strSqUnZip = ZipUtil.sqUnZip(context, strOptString);
            SQLog.d("【Pay Manager】sdata=" + strSqUnZip);
            try {
                int iOptInt = new JSONObject(strSqUnZip).optInt("code", 0);
                if (iOptInt == -1) {
                    this.disablePay = true;
                    this.cutTo37 = false;
                } else if (iOptInt == 1) {
                    this.disablePay = false;
                    this.cutTo37 = true;
                }
            } catch (JSONException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void showPayFailTip(com.sqwan.order.base.SqPayError r7) {
        /*
            r6 = this;
            int r0 = r7.code
            int r1 = r7.originCode
            int r2 = r7.getModule()
            r3 = 2
            if (r2 != r3) goto L1b
            if (r1 <= 0) goto L1b
            java.lang.String r2 = r7.originMsg
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L1b
            java.lang.String r7 = r7.originMsg
            r6.showPayResultTip(r7)
            return
        L1b:
            int r2 = com.sqwan.order.base.SqPayError.ERROR_SDK_INLINE
            java.lang.String r3 = "支付失败，请重新下单"
            java.lang.String r4 = "支付异常，请重试或联系客服"
            java.lang.String r5 = "支付异常，请联系客服"
            if (r0 != r2) goto L2b
            r2 = -1
            if (r1 != r2) goto L2b
        L28:
            r3 = r5
            goto L87
        L2b:
            int r2 = com.sqwan.order.base.SqPayError.ERROR_SDK_ADDITION
            if (r0 != r2) goto L40
            java.lang.String r2 = r7.originMsg
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L3d
            java.lang.String r7 = r7.originMsg
            r6.showPayResultTip(r7)
            return
        L3d:
            java.lang.String r3 = "实名信息不允许支付"
            goto L87
        L40:
            int r7 = com.sqwan.order.base.SqPayError.ERROR_SDK_NATIVE
            if (r0 != r7) goto L45
            goto L28
        L45:
            int r7 = com.sqwan.order.base.SqPayError.ERROR_THIRD_PURCHASE
            if (r0 != r7) goto L86
            r7 = 100
            if (r1 != r7) goto L50
            java.lang.String r3 = "请先安装微信再进行支付"
            goto L87
        L50:
            r7 = 105(0x69, float:1.47E-43)
            if (r1 != r7) goto L55
            goto L28
        L55:
            r7 = 106(0x6a, float:1.49E-43)
            if (r1 != r7) goto L5c
            java.lang.String r3 = "跳转微信失败，请重试或联系客服"
            goto L87
        L5c:
            r7 = 204(0xcc, float:2.86E-43)
            if (r1 != r7) goto L62
        L60:
            r3 = r4
            goto L87
        L62:
            r7 = 300(0x12c, float:4.2E-43)
            if (r1 != r7) goto L69
            java.lang.String r3 = "请先安装抖音再进行支付"
            goto L87
        L69:
            r7 = 305(0x131, float:4.27E-43)
            if (r1 != r7) goto L6e
            goto L60
        L6e:
            r7 = 306(0x132, float:4.29E-43)
            if (r1 != r7) goto L75
            java.lang.String r3 = "跳转抖音失败，请重试或联系客服"
            goto L87
        L75:
            r7 = 400(0x190, float:5.6E-43)
            if (r1 != r7) goto L7a
            goto L87
        L7a:
            r7 = 403(0x193, float:5.65E-43)
            if (r1 != r7) goto L81
            java.lang.String r3 = "跳转云闪付失败，请重试或联系客服"
            goto L87
        L81:
            r7 = 404(0x194, float:5.66E-43)
            if (r1 != r7) goto L86
            goto L87
        L86:
            r3 = 0
        L87:
            boolean r7 = android.text.TextUtils.isEmpty(r3)
            if (r7 == 0) goto L8f
            java.lang.String r3 = "功能异常，请重试或联系客服"
        L8f:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r3)
            java.lang.String r2 = "【"
            r7.append(r2)
            r7.append(r0)
            java.lang.String r0 = "/"
            r7.append(r0)
            r7.append(r1)
            java.lang.String r0 = "】"
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            r6.showPayResultTip(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.msdk.api.sdk.SqPayManager.showPayFailTip(com.sqwan.order.base.SqPayError):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPayResultTip(String str) {
        Activity activity;
        SQLog.d("【Pay Manager】支付结果提示语: " + str);
        if (TextUtils.isEmpty(str) || (activity = SqAtyRef.getInstance().getActivity()) == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        ToastUtil.showToast(activity, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoticeAfterPay(final Context context, final String str, final String str2, final int i) {
        if (TextUtils.isEmpty(str)) {
            SQLog.w("【Pay Manager】公告链接为空, moid=" + str2);
            return;
        }
        int i2 = i == 1 ? 5000 : 10000;
        final MRequestManager mRequestManager = new MRequestManager(context);
        this.mHandler.postDelayed(new Runnable() { // from class: com.sqwan.msdk.api.sdk.-$$Lambda$SqPayManager$mzJ6h4ZU71Jo1HCf-hFNxnS7-C4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$showNoticeAfterPay$0$SqPayManager(mRequestManager, str, str2, context, i);
            }
        }, i2);
    }

    public /* synthetic */ void lambda$showNoticeAfterPay$0$SqPayManager(MRequestManager mRequestManager, final String str, final String str2, final Context context, final int i) {
        mRequestManager.payQueryRequst(str, str2, new MRequestCallBack() { // from class: com.sqwan.msdk.api.sdk.SqPayManager.4
            @Override // com.sqwan.msdk.api.MRequestCallBack
            public void onRequestSuccess(String str3) {
                try {
                    String strOptString = new JSONObject(str3).getJSONObject(FunctionRouter.KEY_DATA).optString(BaseSQwanCore.LOGIN_KEY_NURL);
                    if (!TextUtils.isEmpty(strOptString)) {
                        SQLog.i(SqPayManager.TAG + str2 + " 显示公告: " + strOptString);
                        MultiSDKUtils.showNoticeDialog(context, "", strOptString);
                    } else {
                        SQLog.d(SqPayManager.TAG + str2 + " 无需显示公告");
                    }
                } catch (JSONException unused) {
                    SQLog.w(SqPayManager.TAG + str2 + " 查询订单解析失败, 不弹窗");
                }
            }

            @Override // com.sqwan.msdk.api.MRequestCallBack
            public void onRequestError(String str3) {
                if (i < 5) {
                    SQLog.w(SqPayManager.TAG + str2 + " 查询订单失败, " + str3 + ", 重试: " + i);
                    SqPayManager.this.showNoticeAfterPay(context, str, str2, i + 1);
                    return;
                }
                SQLog.w(SqPayManager.TAG + str2 + " 查询订单失败, " + str3 + ", 不弹窗");
            }
        });
    }
}
