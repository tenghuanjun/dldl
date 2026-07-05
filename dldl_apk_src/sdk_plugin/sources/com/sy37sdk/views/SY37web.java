package com.sy37sdk.views;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.plugin.standard.RealBaseActivity;
import com.sq.tool.logger.SQLog;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.webview.SQCommonJsInterface;
import com.sy37sdk.account.AccountTools;
import com.sy37sdk.account.UserInfo;
import com.sy37sdk.account.trackaction.UserNameEmptyTrackAction;
import com.sy37sdk.core.SQResultListener;
import com.sy37sdk.core.SQwan;
import com.sy37sdk.core.SQwanManager;
import com.sy37sdk.utils.AppUtils;
import com.sy37sdk.utils.Util;
import com.sy37sdk.utils.ViewController;
import com.sy37sdk.utils.ZipString;
import com.sy37sdk.views.webview.AsyncHandler;
import com.sy37sdk.views.webview.WebviewUtils;
import com.sy37sdk.widget.ProgressDialog;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.io.PrintStream;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Deprecated
public class SY37web extends RealBaseActivity {
    private static final int FILE_CHOOSER_RESULT_CODE = 10000;
    private static final String MAILTO = "mailto:";
    private static final int MOBILEQQ_TYPE = 2;
    private static final String QQ = "qq:";
    private static final String TEL = "tel:";
    public static final String WEB_SHOW_TITLE = "showTitle";
    public static final String WEB_TITLE = "title";
    public static final String WEB_URL = "url";
    private static final String WEIXIN = "weixin:";
    private static final int WEIXIN_TYPE = 3;
    private String currentUrl;
    private View errorNetView;
    private RelativeLayout header;
    private ImageButton ibClose;
    private View mIvErrorViewBack;
    private Runnable runnable;
    private boolean showTitle;
    private String title;
    private TextView tvTitle;
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private ProgressDialog waitDialog;
    private WebView webView;
    private long LOAD_TIMEOUT = OAIDHelper.TIMEOUT;
    private final int RETRY_LOAD_COUNTS = 3;
    Handler showWebHandler = new Handler() { // from class: com.sy37sdk.views.SY37web.3
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            if (SY37web.this.currentUrl == null || TextUtils.isEmpty(SY37web.this.currentUrl)) {
                ViewController.showToast(SY37web.this.getContext(), "主人，网址是空的，即将为您关闭..");
                SY37web.this.closeHandler.sendEmptyMessageDelayed(0, 1000L);
            } else {
                if (Util.isNetworkConnected(SY37web.this.getApplicationContext())) {
                    if (SY37web.this.webView != null) {
                        SY37web.this.webView.loadUrl(SY37web.this.currentUrl);
                        return;
                    }
                    return;
                }
                SY37web.this.loadErrorHtml();
            }
        }
    };
    Handler closeHandler = new Handler() { // from class: com.sy37sdk.views.SY37web.4
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            SY37web.this.finish();
        }
    };
    Handler showErrorWebHandler = new Handler() { // from class: com.sy37sdk.views.SY37web.5
        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            SY37web.this.loadErrorHtml();
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(Util.getIdByName("sy37_web", "layout", getContext().getPackageName(), getContext()));
        this.currentUrl = getIntent().getExtras().getString("url");
        this.title = getIntent().getExtras().getString("title");
        this.showTitle = getIntent().getExtras().getBoolean("showTitle");
        getWindow().setSoftInputMode(19);
        this.header = (RelativeLayout) findViewById(Util.getIdByName("header", SqTrackCommonKey.id, getContext().getPackageName(), getContext()));
        this.tvTitle = (TextView) findViewById(Util.getIdByName("title", SqTrackCommonKey.id, getPackageName(), getContext()));
        this.ibClose = (ImageButton) findViewById(Util.getIdByName("togame", SqTrackCommonKey.id, getPackageName(), getContext()));
        this.errorNetView = findViewById(Util.getIdByName("sy37_m_net_error_view", SqTrackCommonKey.id, getPackageName(), getContext()));
        View viewFindViewById = findViewById(Util.getIdByName("sy37_iv_back", SqTrackCommonKey.id, getPackageName(), getContext()));
        this.mIvErrorViewBack = viewFindViewById;
        if (this.showTitle) {
            viewFindViewById.setVisibility(8);
            this.header.setVisibility(0);
            this.tvTitle.setText(this.title);
            this.ibClose.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.views.SY37web.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SY37web.this.finish();
                }
            });
        } else {
            viewFindViewById.setVisibility(0);
            this.mIvErrorViewBack.setOnClickListener(new View.OnClickListener() { // from class: com.sy37sdk.views.SY37web.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SY37web.this.finish();
                }
            });
            this.header.setVisibility(8);
        }
        WebView webView = (WebView) findViewById(Util.getIdByName("webView", SqTrackCommonKey.id, getPackageName(), getContext()));
        this.webView = webView;
        webView.setOnFocusChangeListener(new myOnFocusChangeListener());
        this.webView.setDownloadListener(new MyWebViewDownLoadListener());
        this.webView.setWebViewClient(new myWebViewClient());
        this.webView.setWebChromeClient(new myWebChromeClient());
        this.webView.addJavascriptInterface(new JsObj(getContext()), SQCommonJsInterface.INTERFACE_NAME);
        this.showWebHandler.sendEmptyMessageDelayed(0, 200L);
        SqTrackActionManager2.getInstance().trackDeprecatedCall(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWaitDialog(Context context) {
        if (context != null) {
            if (this.waitDialog == null) {
                ProgressDialog progressDialog = new ProgressDialog(context);
                this.waitDialog = progressDialog;
                progressDialog.setCanceledOnTouchOutside(false);
            }
            ProgressDialog progressDialog2 = this.waitDialog;
            if (progressDialog2 == null || progressDialog2.isShowing() || isFinishing()) {
                return;
            }
            this.waitDialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideWaitDialog() {
        ProgressDialog progressDialog = this.waitDialog;
        if (progressDialog == null || !progressDialog.isShowing() || isFinishing()) {
            return;
        }
        this.waitDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWaitDialog(String str) {
        ProgressDialog progressDialog = this.waitDialog;
        if (progressDialog == null || !progressDialog.isShowing() || isFinishing()) {
            return;
        }
        this.waitDialog.setMessage(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadErrorHtml() {
        this.errorNetView.setVisibility(0);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return true;
        }
        System.out.println("--onBackPressed,canGoBack:" + this.webView.canGoBack());
        if (this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }
        finish();
        return true;
    }

    public class JsObj {
        private Context context;

        public JsObj(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void enRefresh() {
            LogUtil.i("wap 调用enRefresh");
            ((Activity) this.context).runOnUiThread(new Runnable() { // from class: com.sy37sdk.views.SY37web.JsObj.1
                @Override // java.lang.Runnable
                public void run() {
                    SY37web.this.showWebHandler.sendEmptyMessageDelayed(0, 200L);
                }
            });
        }

        @JavascriptInterface
        public void enClose() {
            LogUtil.i("wap 调用enClose");
            ((Activity) this.context).runOnUiThread(new Runnable() { // from class: com.sy37sdk.views.SY37web.JsObj.2
                @Override // java.lang.Runnable
                public void run() {
                    ((Activity) JsObj.this.context).finish();
                }
            });
        }

        @JavascriptInterface
        public void modifyPass() {
            LogUtil.i("wap 调用 modifyPass");
            Util.setPassword(this.context, ZipString.json2ZipString(""));
            UserInfo userInfo = new UserInfo();
            userInfo.setUname(Util.getUsername(this.context));
            userInfo.setUpwd(Util.getPassword(this.context));
            UserNameEmptyTrackAction.report(UserNameEmptyTrackAction.ActionType.modifyPass, userInfo.getUname(), userInfo.toString());
            AccountTools.setAccountToFile(this.context, userInfo);
            enLogin();
        }

        @JavascriptInterface
        public void enLogin() {
            LogUtil.i("wap 调用 enLogin");
            final Context context = SQwanManager.sqContext;
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.sy37sdk.views.SY37web.JsObj.3
                @Override // java.lang.Runnable
                public void run() {
                    if (SQwanManager.back2GameLoginListener != null) {
                        LogUtil.w("回到游戏登录界面的监听|不为空，现在回到游戏的登录界面");
                        SQwanManager.back2GameLoginListener.onSuccess(new Bundle());
                        return;
                    }
                    LogUtil.w("回到游戏登录界面的监听|为空，使用切换账号的逻辑");
                    if (SQwanManager.switchAccountListener == null) {
                        ViewController.showToast(context, "切换账号错误，请联系客服【10003】");
                        return;
                    }
                    if (Util.isSkipSQChangeAccountLogin(context)) {
                        SQwanManager.switchAccountListener.onSuccess(new Bundle());
                    } else if (SQwan.isSQLoginSuccess) {
                        SQwan.getInstance().showLoginView(new SQResultListener() { // from class: com.sy37sdk.views.SY37web.JsObj.3.1
                            @Override // com.sy37sdk.core.SQResultListener
                            public void onSuccess(Bundle bundle) {
                                bundle.putString("gid", Util.getGameID(context));
                                bundle.putString("pid", Util.getPaternerID(context));
                                SQwanManager.switchAccountListener.onSuccess(bundle);
                            }

                            @Override // com.sy37sdk.core.SQResultListener
                            public void onFailture(int i, String str) {
                                SQwanManager.switchAccountListener.onFailture(i, str);
                            }
                        });
                    } else {
                        ViewController.showToast(context, "您还未登录!");
                    }
                }
            });
        }

        @JavascriptInterface
        public void enPay() {
            SQLog.w("已经废弃, 无法调起支付");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shareTo(int i, String str) {
        String str2;
        Intent intent = new Intent("android.intent.action.SEND");
        if (i == 2) {
            Util.copyString2System(getContext(), str, "已复制QQ号码到剪贴版");
            intent.setAction("android.intent.action.SENDTO");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("imto://qq"));
            str2 = "已复制QQ号码到剪贴版";
        } else if (i != 3) {
            str2 = "";
        } else {
            intent.setAction("android.intent.action.SENDTO");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse(str));
            str2 = "已复制微信号码到剪贴版";
        }
        try {
            getContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            if (3 == i) {
                ViewController.showToast(getContext(), "请安装微信，" + str2);
                return;
            }
            ViewController.showToast(getContext(), "请安装移动QQ" + str2);
        }
    }

    private class MyWebViewDownLoadListener implements DownloadListener {
        private MyWebViewDownLoadListener() {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            AppUtils.toUri(SY37web.this.getContext(), str);
        }
    }

    public class myOnFocusChangeListener implements View.OnFocusChangeListener {
        public myOnFocusChangeListener() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                try {
                    Field declaredField = WebView.class.getDeclaredField("mDefaultScale");
                    declaredField.setAccessible(true);
                    declaredField.setFloat(SY37web.this.webView, 1.0f);
                } catch (Exception unused) {
                }
            }
        }
    }

    public class myWebViewClient extends WebViewClient {
        public myWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(final WebView webView, final String str, Bitmap bitmap) {
            System.out.println("37web onPageStarted");
            SY37web.this.currentUrl = str;
            SY37web.this.removeTimeoutCheckingRunnable();
            SY37web.this.runnable = new Runnable() { // from class: com.sy37sdk.views.SY37web.myWebViewClient.1
                @Override // java.lang.Runnable
                public void run() {
                    System.out.println("37web timeout..");
                    myWebViewClient.this.onReceivedError(webView, -8, "网络超时，请稍后再试.", str);
                }
            };
            AsyncHandler.postDelayed(SY37web.this.runnable, SY37web.this.LOAD_TIMEOUT);
            WebviewUtils.setUrlLoadTime(SY37web.this.getApplicationContext(), str, System.currentTimeMillis());
            SY37web sY37web = SY37web.this;
            sY37web.showWaitDialog(sY37web.getContext());
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            System.out.println("37web onPageFinished");
            SY37web.this.removeTimeoutCheckingRunnable();
            if (WebviewUtils.isLoadOneTime(SY37web.this.getApplicationContext(), str, SY37web.this.LOAD_TIMEOUT)) {
                LogUtil.i("当前页面:超时前加载完成");
                WebviewUtils.setUrlLoadCount(SY37web.this.getApplicationContext(), str, 1);
            } else {
                LogUtil.i("当前页面:超时后加载完成");
            }
            SY37web.this.hideWaitDialog();
            String title = webView.getTitle();
            if (TextUtils.isEmpty(title) || SY37web.this.tvTitle == null) {
                return;
            }
            SY37web.this.tvTitle.setText(title);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            System.out.println("37web onReceivedError");
            SY37web.this.removeTimeoutCheckingRunnable();
            if (-8 == i) {
                int urlLoadCount = WebviewUtils.getUrlLoadCount(SY37web.this.getApplicationContext(), str2);
                if (urlLoadCount < 3) {
                    PrintStream printStream = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append("超时处理，准备加载第");
                    int i2 = urlLoadCount + 1;
                    sb.append(i2);
                    sb.append("次");
                    printStream.println(sb.toString());
                    WebviewUtils.setUrlLoadCount(SY37web.this.getApplicationContext(), str2, i2);
                    SY37web.this.showWebHandler.sendEmptyMessageAtTime(1, 500L);
                    return;
                }
                SY37web.this.showErrorWebHandler.sendEmptyMessageDelayed(1, 200L);
                return;
            }
            SY37web.this.showErrorWebHandler.sendEmptyMessageDelayed(1, 200L);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            System.out.println("37web onReceivedSslError");
            sslErrorHandler.proceed();
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            System.out.println("37web shouldOverrideUrlLoading");
            if (str.startsWith(SY37web.TEL)) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.DIAL");
                intent.setData(Uri.parse(str));
                SY37web.this.startActivity(intent);
                return true;
            }
            if (str.startsWith(SY37web.QQ)) {
                SY37web.this.shareTo(2, str.substring(3));
                return true;
            }
            if (str.startsWith(SY37web.MAILTO)) {
                SY37web.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            }
            if (str.startsWith(SY37web.WEIXIN)) {
                SY37web.this.shareTo(3, str);
                return true;
            }
            webView.loadUrl(str);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeTimeoutCheckingRunnable() {
        Runnable runnable = this.runnable;
        if (runnable != null) {
            AsyncHandler.removeCallbacks(runnable);
            this.runnable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openImageChooserActivity() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Image Chooser"), 10000);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10000) {
            if (this.uploadMessage == null && this.uploadMessageAboveL == null) {
                return;
            }
            Uri data = (intent == null || i2 != -1) ? null : intent.getData();
            if (this.uploadMessageAboveL != null) {
                onActivityResultAboveL(i, i2, intent);
                return;
            }
            ValueCallback<Uri> valueCallback = this.uploadMessage;
            if (valueCallback != null) {
                valueCallback.onReceiveValue(data);
                this.uploadMessage = null;
            }
        }
    }

    private void onActivityResultAboveL(int i, int i2, Intent intent) {
        Uri[] uriArr;
        if (i != 10000 || this.uploadMessageAboveL == null) {
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
        this.uploadMessageAboveL.onReceiveValue(uriArr);
        this.uploadMessageAboveL = null;
    }

    public void onDestroy() {
        LogUtil.i("37Web被销毁，先关闭加载进度框");
        hideWaitDialog();
        WebviewUtils.clearWebviewPrefs(getApplicationContext());
        WebView webView = this.webView;
        if (webView != null) {
            webView.clearHistory();
            ((ViewGroup) this.webView.getParent()).removeView(this.webView);
            this.webView.destroy();
            this.webView = null;
        }
        AsyncHandler.removeCallbacks(this.runnable);
        this.showWebHandler.removeCallbacksAndMessages(null);
        this.showErrorWebHandler.removeCallbacksAndMessages(null);
        this.closeHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    public class myWebChromeClient extends WebChromeClient {
        public myWebChromeClient() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            SY37web.this.updateWaitDialog("加载中.." + i + "%");
            super.onProgressChanged(webView, i);
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback) {
            SY37web.this.uploadMessage = valueCallback;
            SY37web.this.openImageChooserActivity();
        }

        public void openFileChooser(ValueCallback valueCallback, String str) {
            SY37web.this.uploadMessage = valueCallback;
            SY37web.this.openImageChooserActivity();
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
            SY37web.this.uploadMessage = valueCallback;
            SY37web.this.openImageChooserActivity();
        }

        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            SY37web.this.uploadMessageAboveL = valueCallback;
            SY37web.this.openImageChooserActivity();
            return true;
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (SY37web.this.tvTitle != null) {
                SY37web.this.tvTitle.setText(str);
            }
        }
    }
}
