package com.huya.berry.webview;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.duowan.HUYA.ChangeLiveInfoReq;
import com.duowan.HUYA.ChangeLiveInfoRsp;
import com.duowan.HUYA.LiveAnnouncementSettingReq;
import com.duowan.HUYA.LiveAnnouncementSettingRsp;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.asignal.SignalCenter;
import com.duowan.auk.asignal.notify.PropertySet;
import com.duowan.auk.http.v2.wup.WupError;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.duowan.live.common.webview.common.BaseWebView;
import com.duowan.live.common.webview.jssdk.JsCodeHolder;
import com.duowan.live.one.module.report.Report;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import com.duowan.live.one.util.ThreadPoolUtil;
import com.huya.berry.client.HuyaBerry;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.certicate.CerticateHelper;
import com.huya.berry.gamesdk.certicate.CertificateCallback;
import com.huya.berry.gamesdk.module.ICommonWup;
import com.huya.berry.gamesdk.report.SdkReportConst;
import com.huya.berry.gamesdk.utils.AppUtils;
import com.huya.berry.gamesdk.utils.CommonUtil;
import com.huya.berry.gamesdk.utils.PermissionTool;
import com.huya.berry.gamesdk.utils.PreferenceUtil;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.widgets.CommonTopBar;
import com.huya.berry.gamesdk.wup.WupHelper;
import com.huya.component.login.LoginProperties;
import com.huya.component.login.api.LoginApi;
import com.huya.component.login.api.TokenInfo;
import com.huya.component.user.UserProperties;
import com.huya.live.channelinfo.impl.wup.IChannelInfoWup;
import com.huya.live.common.api.BaseApi;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.hyns.NS;
import com.huya.mtp.hyns.stat.NSStatUtil;
import com.huya.mtp.utils.ResourceUtils;
import com.huya.statistics.core.StatisticsContent;
import com.hysdkproxy.LoginProxy;
import com.sqwan.bugless.core.Constant;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.route.FunctionRouter;
import com.sqwan.common.track.SqTrackNetKey;
import com.sqwan.liveshow.huya.SqR;
import com.sy37sdk.share.UrlConstant;
import com.taptap.sdk.db.constant.Common;
import com.tencent.bugly.Bugly;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class WebViewActivity extends Activity {
    private static final String BIND_AND = "&";
    private static final String BIND_SEPARATOR = "?";
    public static final int CHOOSE_AVATAR = 6;
    public static final int CHOOSE_IDENTITY = 6;
    public static final int CHOOSE_ONLINE_SERVICE_REQUST = 11;
    public static final int CHOOSE_RECORD_VIDEO = 13;
    private static final String KIWI_INIT_JS_SDK = "_kiwi_init_sdk.js";
    private static final String LOAD_KW_BRIDGE = "kwbridge://huya.com/load_KwBridge.js";
    private static final int STATE_NO_REQUEST = 100;
    private static final int STATE_REQUEST_ING = 101;
    private static final int STATE_RESPONSE_NO = 103;
    private static final int STATE_RESPONSE_YES = 102;
    private static final String TAG = "WebViewActivityTAG";
    private static final String TAG_JAVASCRIPT = "javascript:";
    public static final String WEBVIEW_INFO = "webview_info";
    private BaseWebView mBindWeb;
    private String mFromInvokeParam;
    private boolean mIsJumpQQAuth;
    private JsBrdige mJsBrdige;
    private CommonTopBar mTitleBar;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessageAboveL;
    private int mWebImageOpenType;
    private WebViewInfo mWebViewInfo;
    private final String QQGROUP_API = "mqqopensdkapi://bizAgent/qm/qr";
    private final int QQGROUP_API_LEN = 30;
    private int mModifyTitleState = 100;
    private int mModifyAnnouncementState = 100;
    private Map<String, String> mCallbackMap = null;
    private String mBaseUrl = "";
    private String mLoadUrl = "";
    private boolean mIsJumpAlipayCertification = false;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT == 26 && isTranslucentOrFloating()) {
            L.info(TAG, "8.0 can not Translucent:");
        } else {
            setRequestedOrientation(CommonUtil.isScreenLandScape() ? 6 : 7);
        }
        setContentView(ResourceUtil.getLayoutResIDByName(SqR.layout.hyberry_activity_webview));
        SignalCenter.register(this);
        BaseWebView baseWebView = new BaseWebView(this);
        this.mBindWeb = baseWebView;
        baseWebView.setDownloadListener(new DownloadListener() { // from class: com.huya.berry.webview.WebViewActivity.1
            @Override // android.webkit.DownloadListener
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                try {
                    WebViewActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                    ArkToast.show(ResourceUtil.getStringResIDByName("hyberry_no_web_browser_found"));
                }
            }
        });
        this.mBindWeb.setWebViewClient(new WebViewClient() { // from class: com.huya.berry.webview.WebViewActivity.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                L.info(WebViewActivity.TAG, "shouldOverrideUrlLoading:%s", str);
                if ("kiwi://DOMContentLoaded".equals(str)) {
                    return true;
                }
                if (str.startsWith("kiwi://") && WebViewActivity.this.mBindWeb != null && WebViewActivity.this.mBindWeb.getJsSdkManage() != null) {
                    WebViewActivity.this.mBindWeb.getJsSdkManage().handlerRequest(str);
                    return true;
                }
                if (!URLUtil.isValidUrl(str) && WebViewActivity.this.isJoinQQGroup(str)) {
                    return WebViewActivity.this.joinQQGroup(str);
                }
                return false;
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                L.info(WebViewActivity.TAG, "onPageFinished:%s", str);
                if (WebViewActivity.this.mBindWeb != null) {
                    WebViewActivity.this.mBindWeb.loadUrl("javascript:var myScript= document.createElement(\"script\");myScript.type = \"text/javascript\";myScript.src=\"_kiwi_init_sdk.js\";document.body.appendChild(myScript);void(0);");
                }
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                if (sslErrorHandler != null) {
                    L.error(WebViewActivity.TAG, "continue proceed with ssl error");
                    sslErrorHandler.proceed();
                }
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
                Object[] objArr = new Object[1];
                objArr[0] = TextUtils.isEmpty(str) ? AbstractJsonLexerKt.NULL : str;
                L.debug(WebViewActivity.TAG, "shouldInterceptRequest, url: %s", objArr);
                if (str == null || TextUtils.isEmpty(str)) {
                    return null;
                }
                return filterRequest(str);
            }

            @Override // android.webkit.WebViewClient
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                if (webResourceRequest == null || webResourceRequest.getUrl() == null) {
                    return null;
                }
                return shouldInterceptRequest(webView, webResourceRequest.getUrl().toString());
            }

            private WebResourceResponse filterRequest(String str) {
                if (!str.contains(WebViewActivity.KIWI_INIT_JS_SDK) && !str.contains(WebViewActivity.LOAD_KW_BRIDGE)) {
                    return null;
                }
                L.info(WebViewActivity.TAG, "[JsSDK]load JsSdk, _kiwi_init_sdk.js");
                return new WebResourceResponse("text/javascript", "UTF-8", JsCodeHolder.getJsCode());
            }
        });
        this.mBindWeb.setWebChromeClient(new XHSWebChromeClient());
        this.mBindWeb.setOnKeyListener(new View.OnKeyListener() { // from class: com.huya.berry.webview.WebViewActivity.3
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == 4 && keyEvent.getAction() == 1) {
                    return WebViewActivity.this.onBackClick();
                }
                return false;
            }
        });
        CommonTopBar commonTopBar = (CommonTopBar) findViewById(ResourceUtil.getIdResIDByName("title_bar"));
        this.mTitleBar = commonTopBar;
        commonTopBar.showLogo(false);
        this.mTitleBar.setTopBarListener(new CommonTopBar.TopBarListener() { // from class: com.huya.berry.webview.WebViewActivity.4
            @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
            public void onClickAvatar() {
            }

            @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
            public void onClickBack() {
                WebViewActivity.this.onBackClick();
            }

            @Override // com.huya.berry.gamesdk.widgets.CommonTopBar.TopBarListener
            public void onClickClose() {
                WebViewActivity.this.onBack();
            }
        });
        ((FrameLayout) findViewById(ResourceUtil.getIdResIDByName(SqR.id.fl_webview))).addView((View) this.mBindWeb, (ViewGroup.LayoutParams) new FrameLayout.LayoutParams(-1, -1));
        JsBrdige jsBrdige = new JsBrdige();
        this.mJsBrdige = jsBrdige;
        this.mBindWeb.addJavascriptInterface(jsBrdige, "AndroidJSInterfaceV2");
        initData(getIntent());
    }

    private boolean isTranslucentOrFloating() {
        Exception e;
        boolean zBooleanValue;
        Method method;
        try {
            TypedArray typedArrayObtainStyledAttributes = obtainStyledAttributes((int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null));
            method = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            method.setAccessible(true);
            zBooleanValue = ((Boolean) method.invoke(null, typedArrayObtainStyledAttributes)).booleanValue();
        } catch (Exception e2) {
            e = e2;
            zBooleanValue = false;
        }
        try {
            method.setAccessible(false);
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
        }
        return zBooleanValue;
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        L.info(TAG, "onNewIntent");
        initData(intent);
    }

    private void initData(Intent intent) {
        if (intent == null) {
            ArkToast.show("webview info error");
            finish();
            return;
        }
        WebViewInfo webViewInfo = (WebViewInfo) intent.getParcelableExtra(WEBVIEW_INFO);
        this.mWebViewInfo = webViewInfo;
        if (webViewInfo == null) {
            this.mWebViewInfo = new WebViewInfo();
        }
        String str = this.mWebViewInfo.mUrl;
        this.mBaseUrl = str;
        if (str == null) {
            this.mBaseUrl = "";
        }
        L.info(TAG, "mBaseUrl:" + this.mBaseUrl);
        LinearLayout linearLayout = (LinearLayout) findViewById(ResourceUtil.getIdResIDByName(SqR.id.ll_main));
        if (this.mWebViewInfo.width > 0.0f && this.mWebViewInfo.height > 0.0f) {
            ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
            layoutParams.width = (int) this.mWebViewInfo.width;
            layoutParams.height = (int) this.mWebViewInfo.height;
            linearLayout.setLayoutParams(layoutParams);
        }
        this.mTitleBar.setTitle(this.mWebViewInfo.mTitle);
        boolean z = this.mWebViewInfo.isBindLogin;
        boolean z2 = this.mWebViewInfo.isBusiurlBindLogin;
        String str2 = this.mWebViewInfo.mJumpUrl;
        String businessUrl = z2 ? WebViewHelper.getBusinessUrl(this.mBaseUrl) : this.mBaseUrl;
        if (z) {
            businessUrl = WebViewHelper.getLoginUrl(businessUrl, str2);
        }
        this.mLoadUrl = businessUrl;
        L.info(TAG, "mLoadUrl:" + this.mLoadUrl);
        this.mBindWeb.loadUrl(this.mLoadUrl);
        processSchmeAction(intent.getData());
    }

    private void processSchmeAction(Uri uri) {
        if (uri == null) {
            return;
        }
        L.info(TAG, "scheme:" + uri);
        if (uri.getHost().contains("certification")) {
            CerticateHelper.getCertificateParams(new CerticateHelper.GetCertificateParams(uri.getQueryParameter("zmxyAppId"), uri.getQueryParameter(FeedBackConstants.KEY_FB_APPID), uri.getQueryParameter("appOrderId"), uri.getQueryParameter("sourceType"), uri.getQueryParameter(SqTrackNetKey.params), uri.getQueryParameter(SqConstants.SIGN)));
        }
    }

    @IASlot(executorID = 1)
    public void onGetCertificateParams(CertificateCallback.GetCertificateParams getCertificateParams) {
        if (getCertificateParams == null) {
            return;
        }
        String creditBindUrl = CerticateHelper.getCreditBindUrl(getCertificateParams.result, "", "");
        L.info(TAG, "url:" + creditBindUrl);
        WebviewApi.openWebview(ArkValue.gContext, "实名认证", creditBindUrl, true);
    }

    public void onChangeLiveAnnouncement(boolean z, String str) {
        if (z) {
            this.mModifyAnnouncementState = 102;
            handleSaveUserInfoCallback("");
        } else {
            this.mModifyAnnouncementState = 103;
            handleSaveUserInfoCallback(str);
        }
    }

    public void onModifyTitleSuccess(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mModifyTitleState = 102;
            handleSaveUserInfoCallback("");
        } else {
            this.mModifyTitleState = 103;
            handleSaveUserInfoCallback(str);
        }
    }

    private synchronized void handleSaveUserInfoCallback(String str) {
        if (this.mModifyTitleState != 101 && this.mModifyAnnouncementState != 101) {
            if ((this.mModifyTitleState == 100 || this.mModifyTitleState == 102) && (this.mModifyAnnouncementState == 100 || this.mModifyAnnouncementState == 102)) {
                saveUserInfoCallback(true, "保存成功");
            } else {
                saveUserInfoCallback(false, str);
            }
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        if (this.mIsJumpAlipayCertification) {
            this.mIsJumpAlipayCertification = false;
            this.mBindWeb.loadUrl(CerticateHelper.getCreditBindUrl(0, "", ""));
        }
        if (this.mIsJumpQQAuth) {
            L.info(TAG, "reload" + this.mLoadUrl);
            finish();
            WebviewApi.qqAuthorization(this, this.mBaseUrl);
        }
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onBackClick() {
        L.info(TAG, "onBackClick:" + this.mBindWeb.canGoBack());
        if (this.mBindWeb.canGoBack()) {
            WebBackForwardList webBackForwardListCopyBackForwardList = this.mBindWeb.copyBackForwardList();
            if (webBackForwardListCopyBackForwardList.getCurrentIndex() > 0) {
                int currentIndex = webBackForwardListCopyBackForwardList.getCurrentIndex() - 1;
                String url = currentIndex >= 0 ? webBackForwardListCopyBackForwardList.getItemAtIndex(currentIndex).getUrl() : null;
                if (url == null) {
                    onBack();
                    return true;
                }
                if (url.contains("lgn/jump/authentication.do")) {
                    this.mBindWeb.goBack();
                    onBackClick();
                    return true;
                }
                this.mBindWeb.goBack();
                return true;
            }
            onBack();
        } else {
            onBack();
        }
        return true;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        onBack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBack() {
        if (WebviewApi.getCallback() != null) {
            WebviewApi.getCallback().closeLoading();
        }
        finish();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        destroyWebView();
        SdkProperties.isCertificate.set(false);
        SignalCenter.unregister(this);
        super.onDestroy();
    }

    private void destroyWebView() {
        if (this.mBindWeb != null) {
            CookieManager.getInstance().removeAllCookies(new ValueCallback<Boolean>() { // from class: com.huya.berry.webview.WebViewActivity.5
                @Override // android.webkit.ValueCallback
                public void onReceiveValue(Boolean bool) {
                }
            });
            this.mBindWeb.loadUrl("about:blank");
            this.mBindWeb.onDestroy();
            this.mBindWeb = null;
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        L.info(TAG, "onActivityResult:requestCode" + i + ",resultCode:" + i2);
        if (i == 6) {
            L.error(TAG, "onActivityResult:resultCode:" + i2);
            onOldUrlIdentity(intent);
            return;
        }
        if (i == 11 || i == 13) {
            if (this.mUploadMessage != null) {
                this.mUploadMessage.onReceiveValue((intent == null || i2 != -1) ? null : intent.getData());
                this.mUploadMessage = null;
            } else if (this.mUploadMessageAboveL != null) {
                onActivityResultAboveL(i, i2, intent);
            }
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            onBack();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void onActivityResultAboveL(int i, int i2, Intent intent) {
        Uri[] uriArr;
        if (this.mUploadMessageAboveL == null) {
            return;
        }
        if (i2 != -1 || intent == null) {
            uriArr = null;
        } else {
            String dataString = intent.getDataString();
            ClipData clipData = intent.getClipData();
            if (clipData != null) {
                uriArr = new Uri[clipData.getItemCount()];
                for (int i3 = 0; i3 < clipData.getItemCount(); i3++) {
                    uriArr[i3] = clipData.getItemAt(i3).getUri();
                }
            } else {
                uriArr = null;
            }
            if (dataString != null) {
                uriArr = new Uri[]{Uri.parse(dataString)};
            }
        }
        this.mUploadMessageAboveL.onReceiveValue(uriArr);
        this.mUploadMessageAboveL = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openCameraOrAlbum(int i) {
        int i2 = ImagePickerActivity.PICTYPE_CAMERA;
        if (i == 1) {
            int i3 = ImagePickerActivity.PICTYPE_CAMERA;
        } else if (i == 2) {
            int i4 = ImagePickerActivity.PICTYPE_ALBUM;
        }
        this.mWebImageOpenType = i;
        this.mFromInvokeParam = null;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Image Chooser"), 6);
    }

    private void onOldUrlIdentity(final Intent intent) {
        ThreadPoolUtil.executorAsync(new Runnable() { // from class: com.huya.berry.webview.WebViewActivity.6
            @Override // java.lang.Runnable
            public void run() {
                int i;
                final String str;
                Intent intent2 = intent;
                String strEncodeToString = "";
                if (intent2 == null || intent2.getData() == null) {
                    i = 2;
                } else {
                    Uri data = intent.getData();
                    L.info(WebViewActivity.TAG, "bitmap.uri:" + data.toString());
                    try {
                        Bitmap image = AppUtils.getImage(data);
                        if (image != null) {
                            L.info(WebViewActivity.TAG, "bitmap.getWidth:" + image.getWidth() + ";" + image.getHeight() + ";getByteCount:" + image.getByteCount());
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            int i2 = 100;
                            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                            while (((double) byteArrayOutputStream.toByteArray().length) / 1024.0d > 1024.0d) {
                                L.info(WebViewActivity.TAG, "压缩: " + (((double) byteArrayOutputStream.toByteArray().length) / 1024.0d) + ";options:" + i2);
                                byteArrayOutputStream.reset();
                                image.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream);
                                i2 += -10;
                            }
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            strEncodeToString = Base64.encodeToString(byteArray, 2);
                            L.info(WebViewActivity.TAG, "byteArray.length:%d encodedBitmap.length %d", Integer.valueOf(byteArray.length), Integer.valueOf(strEncodeToString.length()));
                        }
                        i = 1;
                    } catch (FileNotFoundException e) {
                        ArkToast.show(ResourceUtil.getStringResIDByName(SqR.string.hyberry_not_found_picture));
                        e.printStackTrace();
                        return;
                    }
                }
                if (TextUtils.isEmpty(strEncodeToString)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    JSONArray jSONArray = new JSONArray();
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("thumbnail", strEncodeToString);
                    jSONObject2.put("localFileName", System.currentTimeMillis());
                    jSONArray.put(0, jSONObject2);
                    jSONObject.put("code", i);
                    jSONObject.put(FunctionRouter.KEY_DATA, jSONArray);
                    if (!TextUtils.isEmpty(WebViewActivity.this.mFromInvokeParam)) {
                        jSONObject.put("fromInvokeParam", WebViewActivity.this.mFromInvokeParam);
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                if (WebViewActivity.this.mFromInvokeParam != null) {
                    str = String.format(Locale.CHINA, "javascript:unifiedResultToWeb('%s','%s')", "mobile.selPic", jSONObject.toString());
                } else {
                    str = String.format(Locale.CHINA, "javascript:unifiedResultToWeb(%d,'%s')", Integer.valueOf(WebViewActivity.this.mWebImageOpenType), jSONObject.toString());
                }
                ArkValue.gMainHandler.post(new Runnable() { // from class: com.huya.berry.webview.WebViewActivity.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (WebViewActivity.this.mBindWeb == null) {
                            return;
                        }
                        L.error(WebViewActivity.TAG, "jscall:" + str);
                        WebViewActivity.this.mBindWeb.loadUrl(str);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishCerticate(JSONObject jSONObject) {
        L.info(TAG, "finishCerticate:" + jSONObject);
        if (jSONObject == null) {
            L.error(TAG, "认证错误");
        } else {
            ArkUtils.send(new CertificateCallback.CertificateFinish(jSONObject.optInt(HuyaBerry.BerryEvent.BERRYEVENT_RESULTCODE), jSONObject.optString("message")));
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishBind(JSONObject jSONObject) {
        L.info(TAG, "finishBind:" + jSONObject);
        if (jSONObject == null || jSONObject.optString(FunctionRouter.KEY_DATA) == null) {
            ArkToast.show("绑定数据错误");
            return;
        }
        if (WebviewApi.getCallback() != null) {
            WebviewApi.getCallback().bindFinish(jSONObject.optString(FunctionRouter.KEY_DATA));
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportEvent(JSONObject jSONObject) {
        L.info(TAG, "reportEvent:" + jSONObject);
        if (jSONObject == null) {
            L.error(TAG, "上报错误");
        } else {
            Report.event(jSONObject.optString(StatisticsContent.EVENT_id));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSDKView(JSONObject jSONObject) {
        L.info(TAG, "showSDKView:" + jSONObject);
        if (jSONObject == null || jSONObject.optString("nativePageName") == null) {
            ArkToast.show("展示页面参数错误");
            return;
        }
        String strOptString = jSONObject.optString("nativePageName");
        if ("login".equals(strOptString)) {
            showLoginFragment();
        } else if ("modifyNickname".equals(strOptString)) {
            showModifyNicknameFragment();
        }
    }

    public void showLoginFragment() {
        if (WebviewApi.getCallback() != null) {
            WebviewApi.getCallback().showLogin();
            finish();
        }
    }

    public void showModifyNicknameFragment() {
        if (WebviewApi.getCallback() != null) {
            WebviewApi.getCallback().showModifyNickname();
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserInfo() {
        L.info(TAG, "getUserInfo...");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nick", UserProperties.nickName.get());
            jSONObject.put("avatar", UserProperties.avatarUrl.get());
            jSONObject.put("subAnchorTotal", UserProperties.subscribesCount.get());
            jSONObject.put(HuyaBerry.BerryEvent.BERRYEVENT_ROOMID, UserProperties.roomId.get());
            jSONObject.put("roomUrl", UserProperties.liveUrl.get());
            jSONObject.put("liveTitle", SdkProperties.liveTitle.get());
            jSONObject.put("liveNotice", SdkProperties.liveAnnouncement.get());
            TokenInfo defaultToken = LoginApi.getDefaultToken();
            jSONObject.put("token", defaultToken.getToken());
            jSONObject.put(FeedBackConstants.KEY_FB_TICKETTYPE, defaultToken.getTokenType());
            jSONObject.put("uid", LoginProperties.uid.get());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsCallWeb("getUserInfo", jSONObject.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getGameRoleId() {
        L.info(TAG, "getGameRoleId...");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("gameRoleId", SdkProperties.gameAccountID.get());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsCallWeb("getGameRoleId", jSONObject.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void copy(JSONObject jSONObject) {
        L.info(TAG, "copy...   ");
        if (jSONObject == null) {
            copyCallback(false);
            return;
        }
        String string = null;
        try {
            string = jSONObject.getString("text");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(string)) {
            copyCallback(false);
        } else {
            CommonUtil.copyToClipboard(string);
            copyCallback(true);
        }
    }

    private void copyCallback(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (z) {
                jSONObject.put("status", 1);
                jSONObject.put("msg", "复制成功");
            } else {
                jSONObject.put("status", 0);
                jSONObject.put("msg", "复制失败");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsCallWeb("copy", jSONObject.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveUserInfo(JSONObject jSONObject) {
        String string;
        String string2;
        if (jSONObject == null) {
            return;
        }
        String string3 = null;
        try {
            string = jSONObject.getString("avatar");
            try {
                string2 = jSONObject.getString("liveTitle");
                try {
                    string3 = jSONObject.getString("liveNotice");
                } catch (JSONException e) {
                    e = e;
                    e.printStackTrace();
                }
            } catch (JSONException e2) {
                e = e2;
                string2 = null;
            }
        } catch (JSONException e3) {
            e = e3;
            string = null;
            string2 = null;
        }
        L.info(TAG, "saveUserInfo...   avatar:" + string + ",liveTitle:" + string2 + ",liveNotice:" + string3);
        if (!UserProperties.avatarUrl.get().equals(string)) {
            UserProperties.avatarUrl.set(string);
        }
        if (!SdkProperties.liveTitle.get().equals(string2)) {
            if (SdkProperties.isLiving.get().booleanValue()) {
                this.mModifyTitleState = 101;
                changeLiveTitle(string2);
            } else {
                SdkProperties.liveTitle.set(string2);
                PreferenceUtil.setLiveTitle(string2);
            }
        }
        if (!SdkProperties.liveAnnouncement.get().equals(string3)) {
            this.mModifyAnnouncementState = 101;
            ((ICommonWup) NS.get(ICommonWup.class)).setMyLiveAnnouncement(new LiveAnnouncementSettingReq(BaseApi.getUserId(), string3)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<LiveAnnouncementSettingRsp>() { // from class: com.huya.berry.webview.WebViewActivity.7
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(LiveAnnouncementSettingRsp liveAnnouncementSettingRsp) {
                    if (WebViewActivity.this.isFinishing()) {
                        return;
                    }
                    L.info(WebViewActivity.TAG, "onLiveAnnouncementSetting " + liveAnnouncementSettingRsp);
                    WebViewActivity.this.onChangeLiveAnnouncement(liveAnnouncementSettingRsp != null, liveAnnouncementSettingRsp != null ? liveAnnouncementSettingRsp.sMessage : "");
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    if (WebViewActivity.this.isFinishing()) {
                        return;
                    }
                    WebViewActivity.this.onChangeLiveAnnouncement(false, "");
                }
            });
        }
        if (this.mModifyTitleState == 100 && this.mModifyAnnouncementState == 100) {
            saveUserInfoCallback(true, "保存成功");
        }
    }

    private void changeLiveTitle(String str) {
        ChangeLiveInfoReq changeLiveInfoReq = new ChangeLiveInfoReq();
        changeLiveInfoReq.tId = BaseApi.getUserId();
        changeLiveInfoReq.sLiveDesc = str;
        ((IChannelInfoWup) NS.get(IChannelInfoWup.class)).changeLiveInfo(changeLiveInfoReq).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<ChangeLiveInfoRsp>() { // from class: com.huya.berry.webview.WebViewActivity.8
            @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
            public void onNext(ChangeLiveInfoRsp changeLiveInfoRsp) {
                L.info(WebViewActivity.TAG, "changeLiveInfo, resp=" + changeLiveInfoRsp.toString());
                WebViewActivity.this.onModifyTitleSuccess("");
            }

            @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
            public void onError(Throwable th) {
                super.onError(th);
                Throwable throwable = NSStatUtil.parseThrowable((DataException) th);
                if (throwable instanceof WupError) {
                    WebViewActivity.this.onModifyTitleSuccess(((ChangeLiveInfoRsp) ((WupError) throwable).mResponse).sMessage);
                } else {
                    WebViewActivity.this.onModifyTitleSuccess(th.getMessage());
                }
            }
        });
    }

    private void saveUserInfoCallback(final boolean z, final String str) {
        runOnUiThread(new Runnable() { // from class: com.huya.berry.webview.WebViewActivity.9
            @Override // java.lang.Runnable
            public void run() {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("status", z ? 1 : 0);
                    jSONObject.put("msg", str);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                WebViewActivity.this.jsCallWeb("saveUserInfo", jSONObject.toString());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getLivingStatus() {
        L.info(TAG, "getLivingStatus...   ");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("livingStatus", SdkProperties.isLiving.get().booleanValue() ? 1 : 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsCallWeb("getLivingStatus", jSONObject.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logout() {
        L.info(TAG, "logout...   ");
        JSONObject jSONObject = new JSONObject();
        if (SdkProperties.isLiving.get().booleanValue()) {
            try {
                jSONObject.put("status", -1);
                jSONObject.put("msg", "直播中不能退出登录");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsCallWeb("logout", jSONObject.toString());
            return;
        }
        if (WebviewApi.getCallback() != null) {
            WebviewApi.getCallback().logout();
        }
        try {
            jSONObject.put("status", 200);
            jSONObject.put("msg", "");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        jsCallWeb("logout", jSONObject.toString());
        finish();
    }

    @IASlot(executorID = 1, mark = {LoginProperties.MarkLoginState})
    public void loginCallback(PropertySet<LoginProperties.LoginState> propertySet) {
        if (LoginProperties.loginState.get() == LoginProperties.LoginState.NoLogin) {
            L.info(TAG, "logout success:");
            logoutCallback(true);
        }
    }

    private void logoutCallback(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", z ? 200 : -1);
            jSONObject.put("msg", z ? "登出成功" : "登出失败");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsCallWeb("logout", jSONObject.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jsCallWeb(String str, String str2) {
        L.info(TAG, "jsCallWeb=>funcName:%s , param:%s", str, str2);
        String str3 = String.format("javascript:unifiedResultToWeb('%s',%s)", str, str2);
        BaseWebView baseWebView = this.mBindWeb;
        if (baseWebView != null) {
            baseWebView.loadUrl(str3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBindPhoneResult() {
        String str;
        L.info(TAG, "onBindPhoneResult...");
        if (this.mLoadUrl.contains(BIND_SEPARATOR)) {
            str = this.mLoadUrl + BIND_AND + "fromBindMobile=1";
        } else {
            str = this.mLoadUrl + BIND_SEPARATOR + "fromBindMobile=1";
        }
        this.mBindWeb.loadUrl(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGetComnParam() {
        L.info(TAG, "onGetComnParam...");
        String metaValue = ResourceUtils.getMetaValue(this, "HY_APPID", "");
        String metaValue2 = ResourceUtils.getMetaValue(this, "HY_APPKEY", "");
        String version = WupHelper.getVersion();
        String strMd5 = AppUtils.md5(metaValue + "" + version + "" + metaValue2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("returnCode", 0);
            jSONObject.put("passport", LoginProperties.passport.get());
            jSONObject.put("uid", LoginProperties.uid.get());
            jSONObject.put("uri", 0);
            jSONObject.put("version", version);
            jSONObject.put("context", UUID.randomUUID());
            jSONObject.put(FeedBackConstants.KEY_FB_APPID, metaValue);
            jSONObject.put("lcid", "2052");
            jSONObject.put("byPass", LoginProxy.getInstance().getHyUdbByPass());
            jSONObject.put(Common.Predefined.SUB_TIMESTAMP, System.currentTimeMillis());
            jSONObject.put("terminalType", 1);
            jSONObject.put("appSign", strMd5);
            jSONObject.put(Constant.DEVICE_ID, "");
            jSONObject.put("wupData", LoginProxy.getInstance().getH5Info());
            jSONObject.put("wupDataEx", LoginProxy.getInstance().getH5InfoEx());
            jSONObject.put("client_ua", "adr_game_sdk");
            jSONObject.put("client_scheme", CerticateHelper.getSchmeName());
            jSONObject.put("deviceData", Build.BRAND + "," + Build.MODEL + "," + Build.VERSION.RELEASE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsCallWeb("getComnParam", jSONObject.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGetLoginedToken() {
        L.info(TAG, "onGetLoginedToken...");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("returnCode", 0);
            TokenInfo defaultToken = LoginApi.getDefaultToken();
            jSONObject.put("etokType", defaultToken.getTokenType());
            jSONObject.put("sToken", defaultToken.getToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsCallWeb("getLoginedToken", jSONObject.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getH5Info() {
        L.info(TAG, "onGetBindMobileParam...");
        jsCallWeb("getH5Info", LoginProxy.getInstance().getH5InfoEx());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGoForwardClicik() {
        if (this.mBindWeb.canGoForward()) {
            this.mBindWeb.goForward();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onzmCerticate(final String str) {
        runOnUiThread(new Runnable() { // from class: com.huya.berry.webview.WebViewActivity.10
            @Override // java.lang.Runnable
            public void run() {
                String str2 = String.format("javascript:%s()", "initZmCerticateCallback");
                L.info(WebViewActivity.TAG, String.format("onzmCerticate, %s", str2));
                WebViewActivity.this.mBindWeb.loadUrl(str2);
                L.info(WebViewActivity.TAG, String.format("onzmCerticate, params %s", str));
                if (CommonUtil.hasAlipay()) {
                    try {
                        WebViewActivity.this.doVerify(((JSONObject) new JSONTokener(str).nextValue()).getString("certifyUrl"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addToCallbackMap(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (this.mCallbackMap == null) {
            this.mCallbackMap = new HashMap();
        }
        this.mCallbackMap.put(str, str2);
    }

    private String getCallbackName(String str) {
        Map<String, String> map = this.mCallbackMap;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openImageChooserActivity() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Image Chooser"), 11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGoHome() {
        WebBackForwardList webBackForwardListCopyBackForwardList;
        if (!this.mBindWeb.canGoBack() || (webBackForwardListCopyBackForwardList = this.mBindWeb.copyBackForwardList()) == null) {
            return;
        }
        this.mBindWeb.goBackOrForward(-webBackForwardListCopyBackForwardList.getCurrentIndex());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGoLive() {
        finish();
    }

    public boolean isJoinQQGroup(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        int i = this.QQGROUP_API_LEN;
        return length > i && str.substring(0, i).equalsIgnoreCase("mqqopensdkapi://bizAgent/qm/qr");
    }

    public boolean joinQQGroup(String str) {
        return runOtherAppUri(str, false, "请安装QQ或升级到更高版本");
    }

    public boolean runOtherAppUri(String str) {
        return runOtherAppUri(str, false, null);
    }

    public boolean runOtherAppUri(String str, boolean z, String str2) {
        Intent intent = new Intent();
        try {
            this.mIsJumpQQAuth = true;
            intent.setData(Uri.parse(str));
            if (z) {
                intent.addFlags(268435456);
            }
            startActivity(intent);
            return true;
        } catch (Exception unused) {
            this.mIsJumpQQAuth = false;
            if (!TextUtils.isEmpty(str2)) {
                ArkToast.show(str2);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doVerify(String str) {
        L.info(TAG, "doVerify " + str);
        if (CommonUtil.hasAlipay()) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("alipays://platformapi/startapp?appId=20000067&url=" + URLEncoder.encode(str)));
                startActivity(intent);
                this.mIsJumpAlipayCertification = true;
                Report.event(SdkReportConst.PV_CERT_OPENALIPAY);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ArkToast.show("请先安装支付宝");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordVideo() {
        if (!PermissionTool.checkCameraPermission(1280, 720, !SdkProperties.isLandscape.get().booleanValue(), SdkProperties.isLandscape.get().booleanValue())) {
            ArkToast.show("无摄像头权限，请在系统权限设置中开启游戏的相机权限");
            CommonTopBar commonTopBar = this.mTitleBar;
            if (commonTopBar != null) {
                commonTopBar.postDelayed(new Runnable() { // from class: com.huya.berry.webview.WebViewActivity.11
                    @Override // java.lang.Runnable
                    public void run() {
                        if (WebViewActivity.this.isFinishing()) {
                            return;
                        }
                        WebViewActivity.this.finish();
                    }
                }, 2000L);
                return;
            }
            return;
        }
        ArkToast.show("请用手机前置摄像头录制正脸竖屏视频");
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        intent.putExtra("android.intent.extras.LENS_FACING_FRONT", 1);
        intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
        intent.putExtra("camerasensortype", 2);
        intent.putExtra("android.intent.extra.videoQuality", 1);
        intent.putExtra("android.intent.extra.durationLimit", 10);
        startActivityForResult(intent, 13);
    }

    private final class JsBrdige {
        private JsBrdige() {
        }

        @JavascriptInterface
        public void invoke(final String str, final String str2, final String str3, String str4) {
            WebViewActivity.this.addToCallbackMap(str2, str4);
            L.error(WebViewActivity.TAG, "funcname %s,params %s", str2, str3);
            WebViewActivity.this.runOnUiThread(new Runnable() { // from class: com.huya.berry.webview.WebViewActivity.JsBrdige.1
                @Override // java.lang.Runnable
                public void run() {
                    JSONObject jSONObject;
                    try {
                        jSONObject = new JSONObject(str3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        jSONObject = null;
                    }
                    if (str.equals("ui")) {
                        if (str2.equals("openCameraOrAlbumCommon")) {
                            if (jSONObject == null) {
                                return;
                            }
                            try {
                                WebViewActivity.this.openCameraOrAlbum(jSONObject.getInt("type"));
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                        } else if (str2.equals("popViewController")) {
                            WebViewActivity.this.onBack();
                        } else if (str2.equals(UrlConstant.KEY_SHARE)) {
                            if (jSONObject == null) {
                                return;
                            }
                            try {
                                String string = jSONObject.getString("shareUrl");
                                if (TextUtils.isEmpty(string)) {
                                    return;
                                } else {
                                    ((ClipboardManager) ArkValue.gContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("simple text", string));
                                }
                            } catch (JSONException e3) {
                                e3.printStackTrace();
                            }
                        } else if (str2.equals("onBindPhoneResult")) {
                            L.info(WebViewActivity.TAG, "onBindPhoneResult:%s", str3);
                            if (jSONObject != null) {
                                try {
                                    if (jSONObject.getInt("code") == 0) {
                                        WebViewActivity.this.onBindPhoneResult();
                                    }
                                } catch (JSONException e4) {
                                    e4.printStackTrace();
                                }
                            }
                        }
                    }
                    if (str2.equals("mobile.selPic")) {
                        if (WebViewActivity.this.isFinishing()) {
                            return;
                        }
                        WebViewActivity.this.mFromInvokeParam = "";
                        if (jSONObject != null) {
                            try {
                                if (jSONObject.has("fromInvokeParam")) {
                                    String string2 = jSONObject.getString("fromInvokeParam");
                                    WebViewActivity.this.mWebImageOpenType = 0;
                                    WebViewActivity.this.mFromInvokeParam = string2;
                                }
                                if (jSONObject.has(Common.Predefined.SUB_WIDTH)) {
                                    jSONObject.getInt(Common.Predefined.SUB_WIDTH);
                                }
                                if (jSONObject.has("height")) {
                                    jSONObject.getInt("height");
                                }
                            } catch (JSONException e5) {
                                e5.printStackTrace();
                            }
                        }
                        Intent intent = new Intent("android.intent.action.GET_CONTENT");
                        intent.addCategory("android.intent.category.OPENABLE");
                        intent.setType("image/*");
                        WebViewActivity.this.startActivityForResult(Intent.createChooser(intent, "Image Chooser"), 6);
                        return;
                    }
                    if (str2.equals("zmCerticate")) {
                        WebViewActivity.this.onzmCerticate(str3);
                        return;
                    }
                    if (str2.equals("ApplicationForm")) {
                        WebViewActivity.this.finish();
                        return;
                    }
                    if (str2.equals("jumpPage")) {
                        if (jSONObject == null) {
                            return;
                        }
                        try {
                            int i = jSONObject.getInt("index");
                            if (i == 0) {
                                L.info(WebViewActivity.TAG, "关闭");
                                WebViewActivity.this.onBack();
                            } else if (i == 1) {
                                L.info(WebViewActivity.TAG, "下一个页面");
                                WebViewActivity.this.onGoForwardClicik();
                            } else if (i == -1) {
                                L.info(WebViewActivity.TAG, "前一个页面");
                                WebViewActivity.this.onBackClick();
                            } else if (i == 2) {
                                L.info(WebViewActivity.TAG, "回到首页");
                                WebViewActivity.this.onGoHome();
                            } else if (i == 3) {
                                L.info(WebViewActivity.TAG, "回到开播设置页");
                                WebViewActivity.this.onGoLive();
                            }
                            return;
                        } catch (JSONException e6) {
                            e6.printStackTrace();
                            return;
                        }
                    }
                    if (str2.equals("HYUDBMSDKClose")) {
                        L.info(WebViewActivity.TAG, "关闭");
                        WebViewActivity.this.onBack();
                        return;
                    }
                    if (str2.equals("hasAlipay")) {
                        Object[] objArr = new Object[2];
                        objArr[0] = "hasAlipay";
                        objArr[1] = CommonUtil.hasAlipay() ? "true" : Bugly.SDK_IS_DEV;
                        WebViewActivity.this.mBindWeb.loadUrl(String.format("javascript:unifiedResultToWeb('%s',%s)", objArr));
                        return;
                    }
                    if (str2.equals("getComnParam")) {
                        WebViewActivity.this.onGetComnParam();
                        return;
                    }
                    if (str2.equals("getLoginedToken")) {
                        WebViewActivity.this.onGetLoginedToken();
                        return;
                    }
                    if (str2.equals("getH5Info")) {
                        WebViewActivity.this.getH5Info();
                        return;
                    }
                    if (str2.equals("getUserInfo")) {
                        WebViewActivity.this.getUserInfo();
                        return;
                    }
                    if (str2.equals("getGameRoleId")) {
                        WebViewActivity.this.getGameRoleId();
                        return;
                    }
                    if (str2.equals("copy")) {
                        WebViewActivity.this.copy(jSONObject);
                        return;
                    }
                    if (str2.equals("saveUserInfo")) {
                        WebViewActivity.this.saveUserInfo(jSONObject);
                        return;
                    }
                    if (str2.equals("getLivingStatus")) {
                        WebViewActivity.this.getLivingStatus();
                        return;
                    }
                    if (str2.equals("logout")) {
                        WebViewActivity.this.logout();
                        return;
                    }
                    if (str2.equals("finishCerticate")) {
                        WebViewActivity.this.finishCerticate(jSONObject);
                        return;
                    }
                    if (str2.equals("finishBind")) {
                        WebViewActivity.this.finishBind(jSONObject);
                        return;
                    }
                    if (str2.equals("closeWebView")) {
                        WebViewActivity.this.finish();
                    } else if (str2.equals("reportEvent")) {
                        WebViewActivity.this.reportEvent(jSONObject);
                    } else if (str2.equals("showSDKView")) {
                        WebViewActivity.this.showSDKView(jSONObject);
                    }
                }
            });
        }
    }

    public class XHSWebChromeClient extends WebChromeClient {
        public XHSWebChromeClient() {
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
            WebViewActivity.this.mUploadMessage = valueCallback;
            WebViewActivity.this.openImageChooserActivity();
        }

        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (fileChooserParams.getAcceptTypes() == null || fileChooserParams.getAcceptTypes().length == 0) {
                return false;
            }
            if (WebViewActivity.this.mUploadMessageAboveL != null) {
                WebViewActivity.this.mUploadMessageAboveL.onReceiveValue(null);
            }
            L.info(WebViewActivity.TAG, "file chooser params：" + fileChooserParams.getAcceptTypes()[0]);
            WebViewActivity.this.mUploadMessageAboveL = valueCallback;
            if (fileChooserParams.getAcceptTypes()[0].contains("video")) {
                WebViewActivity.this.recordVideo();
                return true;
            }
            if (!fileChooserParams.getAcceptTypes()[0].contains("image")) {
                return true;
            }
            WebViewActivity.this.openImageChooserActivity();
            return true;
        }
    }
}
