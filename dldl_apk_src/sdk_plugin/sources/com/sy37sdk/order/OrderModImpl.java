package com.sy37sdk.order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sq.eventbus.core.EventBus;
import com.sq.sdk.tool.util.EncodeUtil;
import com.sq.sdk.tool.util.SqDeviceUtil;
import com.sq.tool.logger.SQLog;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.base.SqError;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.eventbus.OnActivityResultEvent;
import com.sqwan.common.eventbus.SActiveEvent;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mod.order.IOrderMod;
import com.sqwan.common.route.FunctionRouter;
import com.sqwan.common.route.FunctionRouterManager;
import com.sqwan.common.track.SqTrackUtil;
import com.sqwan.common.util.Base64;
import com.sqwan.common.util.DeviceUtils;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.common.util.ZipUtil;
import com.sqwan.order.base.IPay;
import com.sqwan.order.base.PayContext;
import com.sqwan.order.base.PayExtraInfo;
import com.sqwan.order.base.PayInfoModel;
import com.sqwan.order.base.PayWay;
import com.sqwan.order.base.SqPayError;
import com.sy37sdk.order.eventbus.EventBusIndex;
import com.sy37sdk.order.nat.INativePayListener;
import com.sy37sdk.order.nat.NativePayDialog;
import com.sy37sdk.order.nat.bean.Order;
import com.sy37sdk.order.third.ali.AliPay;
import com.sy37sdk.order.web.PayWebPage;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class OrderModImpl implements IOrderMod {
    private static final String TAG = "【Pay Mod】";
    private final AliPay mAliPay = new AliPay();
    private final Context mContext;
    private PayPack mCurrentPay;

    public OrderModImpl(Context context) {
        this.mContext = context;
        EventBus.getDefault().addIndex(new EventBusIndex());
        EventBus.getDefault().register(this);
        registerPayFuncToRouter();
    }

    private void registerPayFuncToRouter() {
        FunctionRouterManager.getInstance().register(new FunctionRouter() { // from class: com.sy37sdk.order.-$$Lambda$OrderModImpl$Ho5lF-CHENKUEFAUFQ088hhKx84
            @Override // com.sqwan.common.route.FunctionRouter
            /* JADX INFO: renamed from: call */
            public final void lambda$call$0$FunctionRouterManager(Activity activity, String str, Bundle bundle) {
                this.f$0.lambda$registerPayFuncToRouter$0$OrderModImpl(activity, str, bundle);
            }
        });
    }

    public /* synthetic */ void lambda$registerPayFuncToRouter$0$OrderModImpl(Activity activity, String str, Bundle bundle) {
        String strOptString;
        String str2;
        if (FunctionRouter.Func.FUNC_UNIVERSAL_ALI_PAY.equals(str)) {
            if (bundle != null) {
                try {
                    JSONObject jSONObject = new JSONObject(bundle.getString(FunctionRouter.KEY_DATA));
                    strOptString = jSONObject.optString("uuid");
                    try {
                        str2 = new String(Base64.decode(jSONObject.optString("trade")));
                    } catch (Exception unused) {
                        str2 = null;
                    }
                } catch (Exception unused2) {
                    strOptString = null;
                }
            } else {
                strOptString = null;
                str2 = null;
            }
            this.mAliPay.pay(activity, strOptString, str2, null);
        }
    }

    @Override // com.sqwan.common.mod.order.IOrderMod
    public void pay(Activity activity, final PayContext payContext, PayInfoModel payInfoModel, Bundle bundle, IPay.PayCallback payCallback) {
        SQLog.d("【Pay Mod】支付\n" + payInfoModel);
        PayConfig payConfig = new PayConfig(activity, payContext.getOrderDataJson());
        SQLog.d("【Pay Mod】支付配置: " + payConfig);
        final IPay.PayCallback payCallbackWrap = IPay.UIPayCallback.wrap(payCallback);
        this.mCurrentPay = new PayPack(payContext, payInfoModel, payConfig, payCallbackWrap);
        if (payConfig.isAppPay) {
            SQLog.d("【Pay Mod】走原生支付");
            payContext.setPayChannel("2");
            final Order orderConvertToOrder = convertToOrder(payInfoModel);
            orderConvertToOrder.setMoid(payContext.getMoid());
            new NativePayDialog(activity, orderConvertToOrder, new INativePayListener() { // from class: com.sy37sdk.order.OrderModImpl.1
                @Override // com.sy37sdk.order.nat.INativePayListener
                public void onSuccess() {
                    SQLog.i("【Pay Mod】原生支付成功");
                    payContext.setPayExtraInfo(new PayExtraInfo(orderConvertToOrder.getPayAmount(), orderConvertToOrder.getIsVouchers(), orderConvertToOrder.getVouchersId(), "1.0"));
                    payCallbackWrap.onSuccess(payContext);
                }

                @Override // com.sy37sdk.order.nat.INativePayListener
                public void onFailure(int i, String str) {
                    payContext.setPayExtraInfo(new PayExtraInfo(orderConvertToOrder.getPayAmount(), orderConvertToOrder.getIsVouchers(), orderConvertToOrder.getVouchersId(), "1.0"));
                    if (i == 205) {
                        SQLog.w("【Pay Mod】原生支付回调取消支付, code=" + i + ", msg=" + str);
                        payCallbackWrap.onCancel(payContext);
                        return;
                    }
                    SQLog.e("【Pay Mod】原生支付失败, code=" + i + ", msg=" + str);
                    payCallbackWrap.onFailed(payContext, new SqPayError(SqPayError.ERROR_SDK_NATIVE, str, i));
                }
            }).show();
            return;
        }
        SQLog.d("【Pay Mod】走网页支付");
        payContext.setPayChannel("1");
        try {
            String strGeneratePayUrl = generatePayUrl(payContext, payInfoModel, payConfig);
            SQLog.d("【Pay Mod】支付url: " + strGeneratePayUrl);
            PayWebPage.startForResult(activity, payContext.getSession(), strGeneratePayUrl, payInfoModel, payContext.getMoid());
        } catch (Exception e) {
            SQLog.e("【Pay Mod】支付页跳转失败", e);
            BuglessAction.reportCatchException(e, "支付页跳转失败", 102);
            payCallbackWrap.onFailed(payContext, new SqPayError(SqPayError.ERROR_SDK_INLINE, "支付异常", -1));
        }
    }

    public void onActivityResult(OnActivityResultEvent onActivityResultEvent) {
        SqPayError sqPayError;
        int requestCode = onActivityResultEvent.getRequestCode();
        int resultCode = onActivityResultEvent.getResultCode();
        if (requestCode == 100311 && resultCode == 0) {
            SQLog.d("【Pay Mod】onActivityResult");
            Intent intent = onActivityResultEvent.getIntent();
            if (intent == null) {
                SQLog.w("【Pay Mod】无intent, 忽略");
                return;
            }
            PayPack payPack = this.mCurrentPay;
            if (payPack == null) {
                SQLog.w("【Pay Mod】当前无支付, 忽略");
                return;
            }
            SQLog.d("【Pay Mod】当前支付信息: " + payPack);
            if (payPack.payConfig.isAppPay) {
                SQLog.w("【Pay Mod】当前是原生支付, 忽略");
                return;
            }
            PayWay payWay = PayWay.get(intent.getIntExtra(PayWebPage.BUNDLE_RESULT_PAY_WAY_INT, PayWay.UNKNOWN.type));
            String stringExtra = intent.getStringExtra("pay_amount");
            String stringExtra2 = intent.getStringExtra(PayWebPage.BUNDLE_RESULT_PAY_VOUCHERS_ID);
            String stringExtra3 = intent.getStringExtra(PayWebPage.BUNDLE_RESULT_PAY_IS_VOUCHERS);
            String stringExtra4 = intent.getStringExtra("pay_version");
            payPack.payContext.setPayWay(payWay);
            payPack.payContext.setPayExtraInfo(new PayExtraInfo(stringExtra, stringExtra3, stringExtra2, stringExtra4));
            int intExtra = intent.getIntExtra(PayWebPage.BUNDLE_RESULT_PAY, 0);
            if (intExtra == 1) {
                SQLog.i("【Pay Mod】网页支付成功");
                if (payPack.callback != null) {
                    payPack.callback.onSuccess(payPack.payContext);
                    return;
                }
                return;
            }
            if (intExtra == 2) {
                String stringExtra5 = intent.getStringExtra(PayWebPage.BUNDLE_KEY_RESULT_CANCEL_WAY);
                if (TextUtils.isEmpty(stringExtra5)) {
                    stringExtra5 = "unknown";
                }
                payPack.payContext.setCancelWay(stringExtra5);
                SQLog.w("【Pay Mod】网页支付回调取消支付");
                if (payPack.callback != null) {
                    payPack.callback.onCancel(payPack.payContext);
                    return;
                }
                return;
            }
            SqError sqError = (SqError) intent.getParcelableExtra(PayWebPage.BUNDLE_KEY_RESULT_PAY_ERROR);
            if (sqError == null) {
                sqPayError = new SqPayError(SqPayError.ERROR_SDK_INLINE, "支付异常", 0);
            } else {
                sqPayError = new SqPayError(sqError);
            }
            SQLog.e("【Pay Mod】网页支付失败, " + sqError);
            if (payPack.callback != null) {
                payPack.callback.onFailed(payPack.payContext, sqPayError);
            }
        }
    }

    public void onSActiveEvent(SActiveEvent sActiveEvent) {
        SQLog.d("【Pay Mod】onSActiveEvent");
        String data = sActiveEvent.getData();
        if (TextUtils.isEmpty(data)) {
            return;
        }
        try {
            String string = new JSONObject(data).getJSONObject("u").getString("pay");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            SQLog.i("【Pay Mod】更新网页支付链接: " + string);
            OrderUrl.H5_PAY = string;
        } catch (JSONException unused) {
            SQLog.w("【Pay Mod】获取网页支付链接失败, 使用默认的网页支付链接");
        }
    }

    private String generatePayUrl(PayContext payContext, PayInfoModel payInfoModel, PayConfig payConfig) {
        String str;
        String uname;
        String token;
        String uid;
        if (!TextUtils.isEmpty(payConfig.payUrl)) {
            str = payConfig.payUrl;
        } else {
            SQLog.w("【Pay Mod】使用默认H5: " + OrderUrl.H5_PAY);
            str = OrderUrl.H5_PAY;
        }
        IAccountMod iAccountMod = (IAccountMod) ModHelper.get(IAccountMod.class);
        if (iAccountMod != null) {
            token = iAccountMod.getToken();
            uid = iAccountMod.getUid();
            uname = iAccountMod.getUname();
        } else {
            uname = "";
            token = uname;
            uid = token;
        }
        String moid = payContext.getMoid() == null ? "" : payContext.getMoid();
        Bundle bundleAddCommonParamsOfPay = OrderRequestManager.addCommonParamsOfPay(this.mContext, new Bundle(), uid == null ? "" : uid, moid);
        bundleAddCommonParamsOfPay.putString(SqConstants.DOID, payInfoModel.getOrderId());
        bundleAddCommonParamsOfPay.putString("dpt", payInfoModel.getProductName());
        bundleAddCommonParamsOfPay.putString("dcn", payInfoModel.getCurrencyName());
        bundleAddCommonParamsOfPay.putString(SqConstants.DSID, payInfoModel.getServerId());
        bundleAddCommonParamsOfPay.putString(SqConstants.DEXT, payInfoModel.getExtend());
        bundleAddCommonParamsOfPay.putString(SqConstants.DRNAME, payInfoModel.getRoleName());
        bundleAddCommonParamsOfPay.putString(SqConstants.DRID, payInfoModel.getRoleId());
        bundleAddCommonParamsOfPay.putString(SqConstants.DRLEVEL, "" + payInfoModel.getRoleLevel());
        bundleAddCommonParamsOfPay.putString(SqConstants.DMONEY, "" + payInfoModel.getMoney());
        bundleAddCommonParamsOfPay.putString(SqConstants.DRADIO, "" + payInfoModel.getRadio());
        bundleAddCommonParamsOfPay.putString(SqConstants.MOID, moid);
        if (token == null) {
            token = "";
        }
        bundleAddCommonParamsOfPay.putString("token", token);
        if (uid == null) {
            uid = "";
        }
        bundleAddCommonParamsOfPay.putString("uid", uid);
        if (uname == null) {
            uname = "";
        }
        bundleAddCommonParamsOfPay.putString("uname", uname);
        bundleAddCommonParamsOfPay.putString("ig", "1");
        bundleAddCommonParamsOfPay.putString("os", "1");
        bundleAddCommonParamsOfPay.putString(SqTrackCommonKey.os_desc, DeviceUtils.getOs());
        bundleAddCommonParamsOfPay.putString("code", String.valueOf(payConfig.payCode));
        bundleAddCommonParamsOfPay.putString("sversion", VersionUtil.sdkVersion);
        bundleAddCommonParamsOfPay.putString("version", SqDeviceUtil.getVersionName(this.mContext));
        bundleAddCommonParamsOfPay.putString("haswx", (SqTrackUtil.checkAppInstalled(this.mContext, "com.tencent.mm") ? 1 : 0) + "");
        bundleAddCommonParamsOfPay.putString(SqConstants.PAYWAY, payConfig.payWay);
        bundleAddCommonParamsOfPay.putString("gwversion", "4.6.7");
        bundleAddCommonParamsOfPay.putString(SqConstants.HOST_SDK_VERSION, VersionUtil.getOriginalVersion());
        bundleAddCommonParamsOfPay.putString("payVersion", "1.0.0");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&" : "?");
        sb.append(EncodeUtil.encodeUrl(bundleAddCommonParamsOfPay));
        return sb.toString();
    }

    private static class PayConfig {
        boolean isAppPay;
        int payCode;
        String payUrl;
        String payWay;

        public PayConfig(Context context, JSONObject jSONObject) {
            this.isAppPay = false;
            this.payCode = 0;
            this.payUrl = "";
            this.payWay = "";
            if (jSONObject == null) {
                return;
            }
            this.isAppPay = jSONObject.optInt("appPay", 0) == 1;
            this.payWay = jSONObject.optString(SqConstants.PAYWAY, "");
            if (jSONObject.has("sdata")) {
                String strOptString = jSONObject.optString("sdata");
                if (TextUtils.isEmpty(strOptString)) {
                    return;
                }
                String strSqUnZip = ZipUtil.sqUnZip(context, strOptString);
                SQLog.d("【Pay Mod】sdata=" + strSqUnZip);
                try {
                    JSONObject jSONObject2 = new JSONObject(strSqUnZip);
                    this.payCode = jSONObject2.optInt("code", 0);
                    this.payUrl = jSONObject2.optString("pay", "");
                } catch (JSONException unused) {
                }
            }
        }

        public String toString() {
            return "isAppPay=" + this.isAppPay + ", payCode=" + this.payCode + ", payUrl=" + this.payUrl + ", payWay=" + this.payWay;
        }
    }

    private static class PayPack {
        final IPay.PayCallback callback;
        final PayConfig payConfig;
        final PayContext payContext;
        final PayInfoModel payInfo;

        PayPack(PayContext payContext, PayInfoModel payInfoModel, PayConfig payConfig, IPay.PayCallback payCallback) {
            this.payContext = payContext;
            this.payInfo = payInfoModel;
            this.payConfig = payConfig;
            this.callback = payCallback;
        }

        public String toString() {
            String str = "[" + this.payContext.getSession() + "]";
            if (this.payInfo != null) {
                str = str + "order=" + this.payInfo.getOrderId() + ", ";
            }
            return str + "moid=" + this.payContext.getMoid() + ", callback=" + this.callback;
        }
    }

    private Order convertToOrder(PayInfoModel payInfoModel) {
        Order order = new Order();
        order.setDoid(payInfoModel.getOrderId());
        order.setDsid(payInfoModel.getServerId());
        order.setMoney(payInfoModel.getMoney());
        order.setDext(payInfoModel.getExtend());
        order.setDrid(String.valueOf(payInfoModel.getRoleId()));
        order.setDrname(payInfoModel.getRoleName());
        order.setDcn(payInfoModel.getCurrencyName());
        order.setDrlevel(payInfoModel.getRoleLevel());
        order.setDradio(payInfoModel.getRadio());
        order.setDpt(payInfoModel.getProductName());
        return order;
    }
}
