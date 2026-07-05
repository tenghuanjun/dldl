package com.sy37sdk.order.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.plugin.standard.BaseActivity;
import com.sq.tool.logger.SQLog;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sq.webview.SimpleWebHook;
import com.sqwan.base.EventDispatcher;
import com.sqwan.common.BuglessAction;
import com.sqwan.common.dialog.LoadingDialog;
import com.sqwan.common.eventbus.OnActivityResultEvent;
import com.sqwan.common.util.Base64;
import com.sqwan.common.util.CutoutUtil;
import com.sqwan.common.util.EncryptUtil;
import com.sqwan.common.util.PermissionHelper;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.StatusBarUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.webview.SQCommonJsInterface;
import com.sqwan.common.webview.SQWeb;
import com.sqwan.common.webview.SQWebView;
import com.sqwan.common.webview.UrlWebHook;
import com.sqwan.order.base.PayInfoModel;
import com.sqwan.order.base.PayWay;
import com.sqwan.order.base.SqPayError;
import com.sy37sdk.order.PayOrderModel;
import com.sy37sdk.order.PayVersionUtil;
import com.sy37sdk.order.third.IPayWay;
import com.sy37sdk.order.third.ThirdPayManager;
import com.sy37sdk.order.view.PayConfirmDialog;
import com.tencent.bugly.Bugly;
import java.util.HashMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import notchtools.geek.com.notchtools.NotchTools;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PayWebPage extends BaseActivity {
    private static final String BUNDLE_KEY_MOID = "moid";
    private static final String BUNDLE_KEY_PAY_INFO = "pay_info";
    private static final String BUNDLE_KEY_PAY_SESSION = "pay_session";
    public static final String BUNDLE_KEY_RESULT_CANCEL_WAY = "pay_result_cancel_way";
    public static final String BUNDLE_KEY_RESULT_PAY_ERROR = "pay_result_error";
    private static final String BUNDLE_KEY_URL = "url";
    public static final String BUNDLE_RESULT_PAY = "pay_result";
    public static final String BUNDLE_RESULT_PAY_AMOUNT = "pay_amount";
    public static final String BUNDLE_RESULT_PAY_IS_VOUCHERS = "pay_is_vouchers";
    public static final String BUNDLE_RESULT_PAY_VERSION = "pay_version";
    public static final String BUNDLE_RESULT_PAY_VOUCHERS_ID = "pay_vouchers_id";
    public static final String BUNDLE_RESULT_PAY_WAY_INT = "pay_way_int";
    public static final int CODE_PAY_CANCEL = 2;
    public static final int CODE_PAY_FAILURE = 0;
    public static final int CODE_PAY_SUCCESS = 1;
    private static final String FUNCTION_NAME_DOU_YIN_WEB_PAY = "DouYinWebPay";
    private static final String FUNCTION_NAME_UNION_PAY = "UnionPay";
    private static final int PAY_WEB_LOAD_TIMEOUT = 10000;
    public static final int REQUEST_CODE = 100311;
    public static final int RESULT_PAY_CODE = 0;
    private static final String TAG = "【Pay H5】";
    private View errorLayout;
    private boolean isLoading;
    private LoadingDialog loadingDialog;
    private ThirdPayManager mThirdPayManager;
    private PayOrderModel order;
    private String url;
    private SQWebView webView;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final OrderExtraInfo orderExtraInfo = new OrderExtraInfo();
    private final IPayWay.PayWayCallback mInternalPayWayCallback = new IPayWay.PayWayCallback() { // from class: com.sy37sdk.order.web.PayWebPage.1
        @Override // com.sy37sdk.order.third.IPayWay.PayWayCallback
        public void onSuccess(PayWay payWay, PayOrderModel payOrderModel) {
            PayWebPage payWebPage = PayWebPage.this;
            payWebPage.dismiss(payWebPage.paySuccessIntent());
        }

        @Override // com.sy37sdk.order.third.IPayWay.PayWayCallback
        public void onCancel(PayWay payWay, PayOrderModel payOrderModel) {
            PayWebPage payWebPage = PayWebPage.this;
            payWebPage.dismiss(payWebPage.payCancelIntent(payWay.desc));
        }

        @Override // com.sy37sdk.order.third.IPayWay.PayWayCallback
        public void onFailed(PayWay payWay, PayOrderModel payOrderModel, int i, String str) {
            PayWebPage payWebPage = PayWebPage.this;
            payWebPage.dismiss(payWebPage.payFailIntent(new SqPayError(SqPayError.ERROR_THIRD_PURCHASE, str, i)));
        }
    };
    private final Runnable payNetErrorRunnable = new Runnable() { // from class: com.sy37sdk.order.web.PayWebPage.2
        @Override // java.lang.Runnable
        public void run() {
            if (PayWebPage.this.isLoading) {
                SQLog.e("【Pay H5】超过五秒loading。。。网络问题");
                H5PayReporter.trackPayNetError(PayWebPage.this.url, PayWebPage.this.order, PayWebPage.this.orderExtraInfo);
                PayWebPage.this.hideLoading();
                PayWebPage.this.showToast("网络不给力");
            }
        }
    };

    public static class OrderExtraInfo {
        public String payAmount = "";
        public String isVouchers = "0";
        public String vouchersId = "";
        public int payMethod = PayWay.UNKNOWN.type;
        public String payVersion = "3.1";
    }

    public static void startForResult(Activity activity, String str, String str2, PayInfoModel payInfoModel, String str3) {
        Intent intent = new Intent(activity, (Class<?>) PayWebPage.class);
        intent.putExtra("pay_session", str);
        intent.putExtra("url", str2);
        if (payInfoModel != null) {
            intent.putExtra(BUNDLE_KEY_PAY_INFO, payInfoModel.toJson());
        } else {
            intent.putExtra(BUNDLE_KEY_PAY_INFO, "");
        }
        intent.putExtra("moid", str3);
        activity.startActivityForResult(intent, REQUEST_CODE);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SQLog.d("【Pay H5】打开h5支付页");
        initSystemUI();
        setContentView(getIdByName("sysq_m_payweb_dialog", "layout"));
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            String string = extras.getString("pay_session");
            String string2 = extras.getString(BUNDLE_KEY_PAY_INFO);
            SQLog.d("【Pay H5】pay session: " + string);
            SQLog.d("【Pay H5】bundle pay info: " + string2);
            PayInfoModel payInfoModelFromJson = PayInfoModel.fromJson(string2);
            String string3 = extras.getString("moid");
            SQLog.d("【Pay H5】pay info: " + payInfoModelFromJson);
            SQLog.d("【Pay H5】moid: " + string3);
            PayOrderModel payOrderModel = new PayOrderModel(payInfoModelFromJson);
            this.order = payOrderModel;
            payOrderModel.setPaySession(string);
            this.order.setMoid(string3);
            this.url = extras.getString("url");
        }
        SQLog.d("【Pay H5】url=" + this.url);
        initView();
        ThirdPayManager thirdPayManager = new ThirdPayManager(this.webView);
        this.mThirdPayManager = thirdPayManager;
        thirdPayManager.init(getContext());
        this.webView.loadUrl(this.url);
        showWaitDialog();
    }

    private void initSystemUI() {
        if (getContext() != null && (getContext() instanceof Activity)) {
            Activity activityMyself = myself();
            activityMyself.setRequestedOrientation(1);
            activityMyself.requestWindowFeature(1);
        }
        getWindow().setSoftInputMode(19);
        getWindow().getDecorView().setSystemUiVisibility(1028);
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 1;
            getWindow().setAttributes(attributes);
        }
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.sy37sdk.order.web.-$$Lambda$PayWebPage$sFP-63xF_zEQThyzFFgnj7GYjBk
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i) {
                this.f$0.lambda$initSystemUI$0$PayWebPage(i);
            }
        });
    }

    public /* synthetic */ void lambda$initSystemUI$0$PayWebPage(int i) {
        StatusBarUtil.hideSystemUI(getWindow());
    }

    private void initView() {
        View viewFindViewById = findViewById(getIdByName("status_view", SqTrackCommonKey.id));
        FrameLayout frameLayout = (FrameLayout) findViewById(getIdByName("fl_web_container", SqTrackCommonKey.id));
        this.errorLayout = findViewById(getIdByName("ll_web_error_layout", SqTrackCommonKey.id));
        View viewFindViewById2 = findViewById(getIdByName("btn_web_error_retry", SqTrackCommonKey.id));
        if (viewFindViewById2 != null) {
            viewFindViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.order.web.-$$Lambda$PayWebPage$MWnNT7IZUKWFAvBxHgKmclgOYxY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initView$1$PayWebPage(view);
                }
            });
        }
        initWebView(getContext());
        frameLayout.addView(this.webView);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewFindViewById.getLayoutParams();
        int statusHeight = NotchTools.getFullScreenTools().getStatusHeight(getWindow());
        boolean zHasNotchScreen = true;
        if (getContext() != null && (getContext() instanceof Activity)) {
            zHasNotchScreen = CutoutUtil.hasNotchScreen(myself());
        }
        if (zHasNotchScreen) {
            layoutParams.height = statusHeight + 60;
        } else {
            layoutParams.height = 0;
        }
        viewFindViewById.setLayoutParams(layoutParams);
    }

    public /* synthetic */ void lambda$initView$1$PayWebPage(View view) {
        this.webView.reload();
    }

    private void initWebView(Context context) {
        this.webView = new SQWebView(context);
        SQWeb.getInstance().bind(this.webView).replace(new PayUrlWebHook(), UrlWebHook.class).disableLocalH5().addWebHook(new PayWebChromeClient()).addWebHook(new PayWebViewClient());
        this.webView.setBackgroundColor(-1);
        WebSettings settings = this.webView.getSettings();
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setAppCacheMaxSize(5242880L);
        settings.setAppCachePath(context.getApplicationContext().getCacheDir().getAbsolutePath());
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(2);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        this.webView.clearCache(false);
        SQWebView sQWebView = this.webView;
        sQWebView.addJavascriptInterface(new CustomJsObj(sQWebView), SQCommonJsInterface.INTERFACE_NAME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callJsPurchaseParameters(final ValueCallback<String> valueCallback) {
        this.mHandler.post(new Runnable() { // from class: com.sy37sdk.order.web.-$$Lambda$PayWebPage$qClMNJHPCkLlpEhWcgPuC4-yPas
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$callJsPurchaseParameters$3$PayWebPage(valueCallback);
            }
        });
    }

    public /* synthetic */ void lambda$callJsPurchaseParameters$3$PayWebPage(final ValueCallback valueCallback) {
        if (this.webView == null) {
            return;
        }
        SQLog.d("【Pay H5】调用JS获取支付信息");
        this.webView.evaluateJavascript("javascript:window.getPurchaseParameters('')", new ValueCallback() { // from class: com.sy37sdk.order.web.-$$Lambda$PayWebPage$WgtkVXJ2DSEeHqBf4B-rPECEIqo
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                this.f$0.lambda$null$2$PayWebPage(valueCallback, (String) obj);
            }
        });
    }

    public /* synthetic */ void lambda$null$2$PayWebPage(ValueCallback valueCallback, String str) {
        if (TextUtils.isEmpty(str) || AbstractJsonLexerKt.NULL.equalsIgnoreCase(str) || "undefined".equalsIgnoreCase(str)) {
            return;
        }
        SQLog.d("【Pay H5】支付信息onReceiveValue " + str);
        parsePurchaseParameters(str, true);
        if (valueCallback != null) {
            valueCallback.onReceiveValue(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent payFailIntent(SqPayError sqPayError) {
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_RESULT_PAY, 0);
        intent.putExtra(BUNDLE_RESULT_PAY_WAY_INT, this.orderExtraInfo.payMethod);
        intent.putExtra(BUNDLE_KEY_RESULT_PAY_ERROR, sqPayError);
        appendOrderExtra(intent);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent paySuccessIntent() {
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_RESULT_PAY, 1);
        appendOrderExtra(intent);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent payCancelIntent(String str) {
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_RESULT_PAY, 2);
        intent.putExtra(BUNDLE_KEY_RESULT_CANCEL_WAY, str);
        intent.putExtra(BUNDLE_RESULT_PAY_WAY_INT, this.orderExtraInfo.payMethod);
        appendOrderExtra(intent);
        return intent;
    }

    private void appendOrderExtra(Intent intent) {
        intent.putExtra(BUNDLE_RESULT_PAY_WAY_INT, this.orderExtraInfo.payMethod);
        intent.putExtra("pay_amount", this.orderExtraInfo.payAmount);
        intent.putExtra(BUNDLE_RESULT_PAY_VOUCHERS_ID, this.orderExtraInfo.vouchersId);
        intent.putExtra(BUNDLE_RESULT_PAY_IS_VOUCHERS, this.orderExtraInfo.isVouchers);
        intent.putExtra("pay_version", this.orderExtraInfo.payVersion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismiss(Intent intent) {
        myself().setResult(0, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToast(String str) {
        ToastUtil.showToast(getContext(), str);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            if (this.webView.canGoBack()) {
                this.webView.goBack();
                return true;
            }
            showExitPayDialog("backKey");
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showExitPayDialog(final String str) {
        SQLog.w("【Pay H5】显示退出弹窗");
        this.mThirdPayManager.checkPay(this.order);
        callJsPurchaseParameters(null);
        new PayConfirmDialog.Builder(getContext()).setTitle("交易尚未完成，继续支付？").setCancelable(false).setNegativeButton("返回", new View.OnClickListener() { // from class: com.sy37sdk.order.web.-$$Lambda$PayWebPage$KyOEFrxDpBQ_kEPPG-ZhndoL_es
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showExitPayDialog$4$PayWebPage(str, view);
            }
        }).setPositiveButton("确定", null).show();
    }

    public /* synthetic */ void lambda$showExitPayDialog$4$PayWebPage(String str, View view) {
        SQLog.w("【Pay H5】手动退出支付页, 取消支付");
        H5PayReporter.trackPayClose(this.url, this.order, this.orderExtraInfo);
        dismiss(payCancelIntent(str));
    }

    private void showWaitDialog() {
        showLoading();
        this.mHandler.postDelayed(this.payNetErrorRunnable, 10000L);
    }

    protected int getIdByName(String str, String str2) {
        return SqResUtils.getIdByName(str, str2, getContext());
    }

    private void showLoading() {
        SQLog.d("【Pay H5】显示loading");
        if (this.loadingDialog == null) {
            LoadingDialog loadingDialog = new LoadingDialog(getContext());
            this.loadingDialog = loadingDialog;
            loadingDialog.setCancelable(false);
        }
        LoadingDialog loadingDialog2 = this.loadingDialog;
        if (loadingDialog2 != null && !loadingDialog2.isShowing()) {
            this.loadingDialog.show();
        }
        this.isLoading = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideLoading() {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog != null && loadingDialog.isShowing()) {
            this.loadingDialog.dismiss();
        }
        this.isLoading = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLoadingMsg(String str) {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            return;
        }
        this.loadingDialog.setMessage(str);
    }

    public class PayWebViewClient extends SimpleWebHook {
        private boolean loadingFail;

        public PayWebViewClient() {
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.loadingFail = false;
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onPageFinished(WebView webView, final String str) {
            super.onPageFinished(webView, str);
            SQLog.v("【Pay H5】onPageFinished: " + str);
            PayWebPage.this.hideLoading();
            PayWebPage.this.callJsPurchaseParameters(new ValueCallback() { // from class: com.sy37sdk.order.web.-$$Lambda$PayWebPage$PayWebViewClient$iu-gA33J6yJzxoZs81mhSkSgY9U
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    this.f$0.lambda$onPageFinished$0$PayWebPage$PayWebViewClient(str, (String) obj);
                }
            });
            if (PayWebPage.this.errorLayout == null) {
                return;
            }
            if (this.loadingFail) {
                PayWebPage.this.errorLayout.setVisibility(0);
            } else {
                PayWebPage.this.errorLayout.setVisibility(8);
            }
        }

        public /* synthetic */ void lambda$onPageFinished$0$PayWebPage$PayWebViewClient(String str, String str2) {
            SQLog.d("【Pay H5】onReceiveValue 网页加载完成，上报进入支付页");
            H5PayReporter.trackPayInit(str, PayWebPage.this.order, PayWebPage.this.orderExtraInfo);
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onReceivedError(WebView webView, String str, int i, String str2) {
            PayWebPage.this.hideLoading();
            PayWebPage.this.showToast(str2);
            this.loadingFail = true;
        }
    }

    private class PayUrlWebHook extends UrlWebHook {
        private PayUrlWebHook() {
        }

        @Override // com.sqwan.common.webview.UrlWebHook, com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            SQLog.v("【Pay H5】shouldOverrideUrlLoading: " + str);
            if (PayWebPage.this.mThirdPayManager.interceptorWithUrl(PayWebPage.this.myself(), PayWebPage.this.order, str, null, PayWebPage.this.mInternalPayWayCallback)) {
                return true;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }
    }

    class PayWebChromeClient extends SimpleWebHook {
        PayWebChromeClient() {
        }

        @Override // com.sq.webview.SimpleWebHook, com.sq.webview.WebHook
        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            PayWebPage.this.setLoadingMsg("加载中..." + i + "%");
        }
    }

    public class CustomJsObj extends SQCommonJsInterface {
        public CustomJsObj(SQWebView sQWebView) {
            super(sQWebView);
        }

        @JavascriptInterface
        public void checkWX() {
            SQLog.d("【Pay H5】JS触发微信查单");
            PayWebPage.this.mHandler.post(new Runnable() { // from class: com.sy37sdk.order.web.-$$Lambda$PayWebPage$CustomJsObj$fXfnmEDbwh2N0tBaEy9_xyy_QRE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$checkWX$0$PayWebPage$CustomJsObj();
                }
            });
        }

        public /* synthetic */ void lambda$checkWX$0$PayWebPage$CustomJsObj() {
            PayWebPage.this.mThirdPayManager.checkPay(PayWebPage.this.order);
        }

        @JavascriptInterface
        public void closePay() {
            closePay("0");
        }

        @JavascriptInterface
        public void closePay(final String str) {
            SQLog.d("【Pay H5】JS触发关闭支付, flag=" + str);
            PayWebPage.this.mHandler.post(new Runnable() { // from class: com.sy37sdk.order.web.-$$Lambda$PayWebPage$CustomJsObj$g9r-n_KgseQZjns10y6xUAcdTCQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$closePay$1$PayWebPage$CustomJsObj(str);
                }
            });
        }

        public /* synthetic */ void lambda$closePay$1$PayWebPage$CustomJsObj(String str) {
            int i;
            try {
                i = Integer.parseInt(str);
            } catch (Exception unused) {
                i = 0;
            }
            if (i == 1) {
                SQLog.i("【Pay H5】JS返回支付成功");
                H5PayReporter.trackJsPaySuccess(PayWebPage.this.order, PayWebPage.this.orderExtraInfo);
                PayWebPage payWebPage = PayWebPage.this;
                payWebPage.dismiss(payWebPage.paySuccessIntent());
                return;
            }
            SQLog.w("【Pay H5】JS关闭支付");
            PayWebPage.this.showExitPayDialog("jsClosePay");
        }

        @JavascriptInterface
        public void enAliPay(final String str) {
            SQLog.i("【Pay H5】JS触发支付宝支付: " + str);
            PayWebPage.this.mHandler.post(new Runnable() { // from class: com.sy37sdk.order.web.-$$Lambda$PayWebPage$CustomJsObj$gweaymP4Cem36Ch3M27LSlp-Fx0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$enAliPay$2$PayWebPage$CustomJsObj(str);
                }
            });
        }

        public /* synthetic */ void lambda$enAliPay$2$PayWebPage$CustomJsObj(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("uuid");
                String str2 = new String(Base64.decode(jSONObject.optString("trade")));
                HashMap map = new HashMap();
                map.put("payUrl", str2);
                map.put("uuid", strOptString);
                H5PayReporter.trackPayAli(PayWebPage.this.order, map);
                PayWebPage.this.orderExtraInfo.payMethod = PayWay.ALI.type;
                Bundle bundle = new Bundle();
                bundle.putString("third_order_id", strOptString);
                bundle.putString(ThirdPayManager.EXTRA_TRADE_INFO, str2);
                PayWebPage.this.mThirdPayManager.pay(PayWebPage.this.myself(), PayWay.ALI, PayWebPage.this.order, bundle, PayWebPage.this.mInternalPayWayCallback);
            } catch (Exception e) {
                BuglessAction.reportCatchException(e, str, 13);
                PayWebPage payWebPage = PayWebPage.this;
                payWebPage.dismiss(payWebPage.payFailIntent(new SqPayError(SqPayError.ERROR_SDK_INLINE, "支付宝支付数据异常，请重试或联系客服", PayWay.ALI.type)));
            }
        }

        @JavascriptInterface
        public void enWX(final String str) {
            SQLog.i("【Pay H5】JS触发微信支付: " + str);
            PayWebPage.this.mHandler.post(new Runnable() { // from class: com.sy37sdk.order.web.-$$Lambda$PayWebPage$CustomJsObj$9npr1tSoFRX8J6E6W5cus2WTG0c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$enWX$3$PayWebPage$CustomJsObj(str);
                }
            });
        }

        public /* synthetic */ void lambda$enWX$3$PayWebPage$CustomJsObj(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("uuid");
                String strDecrypt = EncryptUtil.decrypt(jSONObject.optString("wx_referer"));
                String strOptString2 = jSONObject.optString("mweb_url");
                HashMap map = new HashMap();
                map.put("en_wx", str);
                map.put("uuid", strOptString);
                if (!TextUtils.isEmpty(strDecrypt) && !TextUtils.isEmpty(strOptString2)) {
                    map.put("wx_standard", "true");
                } else {
                    map.put("wx_standard", Bugly.SDK_IS_DEV);
                }
                H5PayReporter.trackPayWechat(PayWebPage.this.order, map);
                PayWebPage.this.orderExtraInfo.payMethod = PayWay.WECHAT.type;
                Bundle bundle = new Bundle();
                bundle.putString(ThirdPayManager.EXTRA_PAY_URL, strOptString2);
                bundle.putString("referer", strDecrypt);
                bundle.putString("third_order_id", strOptString);
                bundle.putString(ThirdPayManager.EXTRA_TRADE_INFO, jSONObject.optString("trade"));
                PayWebPage.this.mThirdPayManager.pay(PayWebPage.this.myself(), PayWay.WECHAT, PayWebPage.this.order, bundle, PayWebPage.this.mInternalPayWayCallback);
            } catch (Exception e) {
                BuglessAction.reportCatchException(e, str, 13);
                PayWebPage payWebPage = PayWebPage.this;
                payWebPage.dismiss(payWebPage.payFailIntent(new SqPayError(SqPayError.ERROR_SDK_INLINE, "微信支付数据异常，请重试或联系客服", PayWay.WECHAT.type)));
            }
        }

        @JavascriptInterface
        public void enUnionPay(final String str) {
            SQLog.i("【Pay H5】JS触发云闪付支付: " + str);
            PayWebPage.this.mHandler.post(new Runnable() { // from class: com.sy37sdk.order.web.-$$Lambda$PayWebPage$CustomJsObj$IyGmXkrbJfa6vOie4UCUsBhehas
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$enUnionPay$4$PayWebPage$CustomJsObj(str);
                }
            });
        }

        public /* synthetic */ void lambda$enUnionPay$4$PayWebPage$CustomJsObj(String str) {
            try {
                String strOptString = new JSONObject(str).optString("tn");
                HashMap map = new HashMap();
                map.put("tn", strOptString);
                H5PayReporter.trackPayUnion(PayWebPage.this.order, map);
                PayWebPage.this.orderExtraInfo.payMethod = PayWay.UNION.type;
                Bundle bundle = new Bundle();
                bundle.putString("third_order_id", strOptString);
                PayWebPage.this.mThirdPayManager.pay(PayWebPage.this.myself(), PayWay.UNION, PayWebPage.this.order, bundle, PayWebPage.this.mInternalPayWayCallback);
            } catch (Exception e) {
                BuglessAction.reportCatchException(e, str, 13);
                PayWebPage payWebPage = PayWebPage.this;
                payWebPage.dismiss(payWebPage.payFailIntent(new SqPayError(SqPayError.ERROR_SDK_INLINE, "云闪付支付数据异常，请重试或联系客服", PayWay.UNION.type)));
            }
        }

        @JavascriptInterface
        public void enDouYinWebPay(final String str) {
            SQLog.i("【Pay H5】JS触发抖音支付: " + str);
            PayWebPage.this.mHandler.post(new Runnable() { // from class: com.sy37sdk.order.web.-$$Lambda$PayWebPage$CustomJsObj$X5wweSn3ismH3-Zz61AjQfl5HWo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$enDouYinWebPay$5$PayWebPage$CustomJsObj(str);
                }
            });
        }

        public /* synthetic */ void lambda$enDouYinWebPay$5$PayWebPage$CustomJsObj(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("trade");
                String strOptString2 = jSONObject.optString("referer");
                String strOptString3 = jSONObject.optString("uuid");
                HashMap map = new HashMap();
                map.put("payUrl", strOptString);
                map.put("referer", strOptString2);
                map.put("uuid", strOptString3);
                H5PayReporter.trackPayDouYin(PayWebPage.this.order, map);
                PayWebPage.this.orderExtraInfo.payMethod = PayWay.DOU_YIN_H5.type;
                Bundle bundle = new Bundle();
                bundle.putString("referer", strOptString2);
                bundle.putString("third_order_id", strOptString3);
                bundle.putString(ThirdPayManager.EXTRA_PAY_URL, strOptString);
                PayWebPage.this.mThirdPayManager.pay(PayWebPage.this.myself(), PayWay.DOU_YIN_H5, PayWebPage.this.order, bundle, PayWebPage.this.mInternalPayWayCallback);
            } catch (Exception e) {
                BuglessAction.reportCatchException(e, str, 13);
                PayWebPage payWebPage = PayWebPage.this;
                payWebPage.dismiss(payWebPage.payFailIntent(new SqPayError(SqPayError.ERROR_SDK_INLINE, "抖音支付数据异常，请重试或联系客服", PayWay.DOU_YIN_H5.type)));
            }
        }

        @JavascriptInterface
        public void purchaseParameters(String str) {
            SQLog.i("【Pay H5】JS更新订单信息: " + str);
            PayWebPage.this.parsePurchaseParameters(str);
            try {
                if (TextUtils.isEmpty(new JSONObject(str).optString("payMethod"))) {
                    return;
                }
                H5PayReporter.trackPayClick(PayWebPage.this.order, PayWebPage.this.orderExtraInfo);
            } catch (Exception e) {
                SQLog.e("【Pay H5】purchaseParameters json解析异常: " + str, e);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1 */
        /* JADX WARN: Type inference failed for: r0v2 */
        /* JADX WARN: Type inference failed for: r0v3, types: [int] */
        /* JADX WARN: Type inference failed for: r0v5 */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9 */
        @Override // com.sqwan.common.webview.SQCommonJsInterface
        @JavascriptInterface
        public int checkFeatureStatus(String str) {
            int iCheckFeatureStatus = super.checkFeatureStatus(str);
            if (iCheckFeatureStatus == 1) {
                return iCheckFeatureStatus;
            }
            ?? r0 = 0;
            r0 = 0;
            try {
                String strOptString = new JSONObject(str).optString("featureName");
                if (PayWebPage.FUNCTION_NAME_UNION_PAY.equals(strOptString)) {
                    boolean zIsSupport = PayWebPage.this.mThirdPayManager.isSupport(PayWay.UNION);
                    SQLog.d("【Pay H5】JS查询当前版本是否支持云闪付功能: " + zIsSupport);
                    r0 = zIsSupport;
                } else if (PayWebPage.FUNCTION_NAME_DOU_YIN_WEB_PAY.equals(strOptString)) {
                    boolean zIsSupport2 = PayWebPage.this.mThirdPayManager.isSupport(PayWay.DOU_YIN_H5);
                    SQLog.d("【Pay H5】JS查询当前版本是否支持抖音支付功能: " + zIsSupport2);
                    r0 = zIsSupport2;
                }
            } catch (Exception e) {
                SQLog.e("【Pay H5】checkFeatureStatus json解析异常: " + str, e);
            }
            return r0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parsePurchaseParameters(String str) {
        parsePurchaseParameters(str, false);
    }

    private void parsePurchaseParameters(String str, boolean z) {
        if (z) {
            try {
                str = str.substring(1, str.length() - 1).replaceAll("\\\\", "");
            } catch (Exception e) {
                SQLog.e("【Pay H5】解析支付信息异常: " + str, e);
                BuglessAction.reportCatchException(e, str, BuglessAction.PAY_ERROR);
                return;
            }
        }
        JSONObject jSONObject = new JSONObject(str);
        String strOptString = jSONObject.optString("payAmount");
        String strOptString2 = jSONObject.optString("isVouchers");
        String strOptString3 = jSONObject.optString("vouchersId");
        String strOptString4 = jSONObject.optString("payMethod");
        String strOptString5 = jSONObject.optString("payVersion");
        PayVersionUtil.webVersion = strOptString5;
        if (TextUtils.isEmpty(strOptString4)) {
            return;
        }
        this.orderExtraInfo.payAmount = strOptString;
        this.orderExtraInfo.isVouchers = strOptString2;
        this.orderExtraInfo.vouchersId = strOptString3;
        try {
            this.orderExtraInfo.payMethod = Integer.parseInt(strOptString4);
        } catch (Exception e2) {
            BuglessAction.reportCatchException(e2, str, BuglessAction.PAY_ERROR);
        }
        this.orderExtraInfo.payVersion = strOptString5;
        SQLog.i("【Pay H5】更新订单信息: " + jSONObject);
    }

    public void onStart() {
        super.onStart();
        ThirdPayManager thirdPayManager = this.mThirdPayManager;
        if (thirdPayManager != null) {
            thirdPayManager.onStart(myself());
        }
    }

    public void onResume() {
        super.onResume();
        ThirdPayManager thirdPayManager = this.mThirdPayManager;
        if (thirdPayManager != null) {
            thirdPayManager.onResume(myself());
        }
    }

    public void onPause() {
        super.onPause();
        ThirdPayManager thirdPayManager = this.mThirdPayManager;
        if (thirdPayManager != null) {
            thirdPayManager.onPause(myself());
        }
    }

    public void onStop() {
        super.onStop();
        ThirdPayManager thirdPayManager = this.mThirdPayManager;
        if (thirdPayManager != null) {
            thirdPayManager.onStop(myself());
        }
    }

    public void onDestroy() {
        try {
            if (this.webView != null) {
                this.webView.onDestroy();
            }
            if (this.loadingDialog != null) {
                this.loadingDialog.dismiss();
            }
        } catch (Exception unused) {
        }
        super.onDestroy();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        EventDispatcher.getInstance().dispatcherActivityResultListener(new OnActivityResultEvent(i, i2, intent));
        ThirdPayManager thirdPayManager = this.mThirdPayManager;
        if (thirdPayManager != null) {
            thirdPayManager.onActivityResult(myself(), i, i2, intent);
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 1110) {
            return;
        }
        PermissionHelper.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Activity myself() {
        return (Activity) getContext();
    }
}
