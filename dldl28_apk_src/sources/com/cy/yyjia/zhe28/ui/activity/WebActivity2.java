package com.cy.yyjia.zhe28.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityWeb2Binding;
import com.cy.yyjia.zhe28.domain.GameEventDetailBean;
import com.cy.yyjia.zhe28.domain.LoginChangeBean;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.ObjectInterface;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.mobile.auth.gatewayauth.Constant;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: WebActivity2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010#\u001a\u00020$H\u0016J\"\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u00052\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\b\u0010*\u001a\u00020$H\u0016J\b\u0010+\u001a\u00020$H\u0014J\u0010\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020.H\u0007J\b\u0010/\u001a\u00020$H\u0002J\b\u00100\u001a\u00020$H\u0014J\u001c\u00101\u001a\u00020$2\u0014\u00102\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001a\u0018\u00010\u0013J\u0016\u00103\u001a\u00020$2\u000e\u00104\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013J\b\u00105\u001a\u00020$H\u0002J\b\u00106\u001a\u00020$H\u0002J\b\u00107\u001a\u00020$H\u0002J\u000e\u00108\u001a\u00020$2\u0006\u0010\"\u001a\u00020\u001eR\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u000e\u0010\n\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R(\u0010\u0019\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001a\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0018R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u0011\u001a\u0004\b \u0010\u0007R\u0010\u0010\"\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/WebActivity2;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityWeb2Binding;", "()V", "FILECHOOSER_RESULTCODE", "", "getFILECHOOSER_RESULTCODE", "()I", "FILECHOOSER_RESULTCODE_FOR_ANDROID_5", "getFILECHOOSER_RESULTCODE_FOR_ANDROID_5", "REQ_CHOOSE", "VIDEO_REQUEST", "js", "Lcom/cy/yyjia/zhe28/util/ObjectInterface;", "getJs", "()Lcom/cy/yyjia/zhe28/util/ObjectInterface;", "js$delegate", "Lkotlin/Lazy;", "mUploadMessage", "Landroid/webkit/ValueCallback;", "Landroid/net/Uri;", "getMUploadMessage", "()Landroid/webkit/ValueCallback;", "setMUploadMessage", "(Landroid/webkit/ValueCallback;)V", "mUploadMessageForAndroid5", "", "getMUploadMessageForAndroid5", "setMUploadMessageForAndroid5", "module", "", "newsId", "getNewsId", "newsId$delegate", "url", "init", "", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "resultCode", "intent", "Landroid/content/Intent;", "onBackPressed", "onDestroy", "onLogin", "result", "Lcom/cy/yyjia/zhe28/domain/LoginChangeBean;", "onReceiveValue", "onResume", "onenFileChooseImpleForAndroid", "filePathCallback", "openFileChooserImpl", "uploadMsg", "recordVideo", "selectImage", "selectVideo", "setCookie", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class WebActivity2 extends BaseActivity<ActivityWeb2Binding> {
    public static final int $stable = 8;
    private final int FILECHOOSER_RESULTCODE;
    private final int FILECHOOSER_RESULTCODE_FOR_ANDROID_5;
    private final int REQ_CHOOSE;
    private final int VIDEO_REQUEST;

    /* JADX INFO: renamed from: js$delegate, reason: from kotlin metadata */
    private final Lazy js;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessageForAndroid5;
    private String module;

    /* JADX INFO: renamed from: newsId$delegate, reason: from kotlin metadata */
    private final Lazy newsId;
    private String url;

    public WebActivity2() {
        super(R.layout.activity_web2, 0, 2, null);
        this.FILECHOOSER_RESULTCODE = 1;
        this.FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;
        this.REQ_CHOOSE = 4672;
        this.VIDEO_REQUEST = 120;
        this.url = "";
        this.module = "";
        this.newsId = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity2$newsId$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("newsId", 0));
            }
        });
        this.js = LazyKt.lazy(new Function0<ObjectInterface>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity2$js$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ObjectInterface invoke() {
                WebActivity2 webActivity2 = this.this$0;
                WebActivity2 webActivity22 = webActivity2;
                WebView wv = WebActivity2.access$getMBinding(webActivity2).wv;
                Intrinsics.checkNotNullExpressionValue(wv, "wv");
                return new ObjectInterface(webActivity22, wv);
            }
        });
    }

    public static final /* synthetic */ ActivityWeb2Binding access$getMBinding(WebActivity2 webActivity2) {
        return webActivity2.getMBinding();
    }

    public final int getFILECHOOSER_RESULTCODE() {
        return this.FILECHOOSER_RESULTCODE;
    }

    public final int getFILECHOOSER_RESULTCODE_FOR_ANDROID_5() {
        return this.FILECHOOSER_RESULTCODE_FOR_ANDROID_5;
    }

    private final int getNewsId() {
        return ((Number) this.newsId.getValue()).intValue();
    }

    public final ObjectInterface getJs() {
        return (ObjectInterface) this.js.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        EventBus.getDefault().register(this);
        String stringExtra = getIntent().getStringExtra("color");
        if (stringExtra != null) {
            getMBinding().navigation.setBackgroundColor(Color.parseColor(stringExtra));
            immersionBar(stringExtra, true);
        }
        getMBinding().navigation.setBackClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity2$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebActivity2.init$lambda$1(this.f$0, view);
            }
        });
        this.url = getIntent().getStringExtra("url");
        this.module = getIntent().getStringExtra("module");
        getMBinding().setTitle(getIntent().getStringExtra("name"));
        getMBinding().wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getMBinding().wv.getSettings().setJavaScriptEnabled(true);
        getMBinding().wv.getSettings().setLoadsImagesAutomatically(true);
        getMBinding().wv.getSettings().setCacheMode(2);
        getMBinding().wv.getSettings().setDomStorageEnabled(true);
        getMBinding().wv.getSettings().setUserAgentString(getMBinding().wv.getSettings().getUserAgentString() + "/androidbox;");
        String userAgentString = getMBinding().wv.getSettings().getUserAgentString();
        Intrinsics.checkNotNullExpressionValue(userAgentString, "getUserAgentString(...)");
        log(userAgentString);
        log(com.cy.yyjia.zhe28.util.Constant.INSTANCE.getJS_NAME());
        getMBinding().wv.addJavascriptInterface(getJs(), com.cy.yyjia.zhe28.util.Constant.INSTANCE.getJS_NAME());
        getMBinding().wv.clearCache(true);
        getMBinding().wv.setWebViewClient(new WebViewClient() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity2.init.3
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebActivity2.this.log("url: " + url);
                Intrinsics.checkNotNull(url);
                if (StringsKt.startsWith$default(url, "https://www.28zhe.com/dist/game-detail?id=", false, 2, (Object) null)) {
                    List listSplit$default = StringsKt.split$default((CharSequence) url, new String[]{"game-detail?id="}, false, 0, 6, (Object) null);
                    if (listSplit$default.size() <= 1) {
                        return false;
                    }
                    Util.gotoGame(WebActivity2.this, Integer.parseInt((String) listSplit$default.get(1)));
                    return true;
                }
                if (StringsKt.startsWith$default(url, "http:", false, 2, (Object) null) || StringsKt.startsWith$default(url, "https:", false, 2, (Object) null)) {
                    return false;
                }
                try {
                    WebActivity2.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                } catch (Exception e) {
                    WebActivity2 webActivity2 = WebActivity2.this;
                    String localizedMessage = e.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    webActivity2.log(localizedMessage);
                }
                return true;
            }
        });
        getMBinding().wv.setWebChromeClient(new WebChromeClient() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity2.init.4
            @Override // android.webkit.WebChromeClient
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                Intrinsics.checkNotNullParameter(message, "message");
                Intrinsics.checkNotNullParameter(result, "result");
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("提示").setMessage(message).setPositiveButton("确定", (DialogInterface.OnClickListener) null);
                builder.setCancelable(false);
                builder.setIcon(R.mipmap.ic_launcher);
                builder.create().show();
                result.confirm();
                return true;
            }

            public static /* synthetic */ void openFileChooser$default(AnonymousClass4 anonymousClass4, ValueCallback valueCallback, String str, int i, Object obj) {
                if ((i & 2) != 0) {
                    str = "image/*";
                }
                anonymousClass4.openFileChooser(valueCallback, str);
            }

            public final void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                Intrinsics.checkNotNullParameter(acceptType, "acceptType");
                if (!Intrinsics.areEqual(acceptType, "image/*")) {
                    WebActivity2.this.onReceiveValue();
                    return;
                }
                if (WebActivity2.this.getMUploadMessage() != null) {
                    ValueCallback<Uri> mUploadMessage = WebActivity2.this.getMUploadMessage();
                    Intrinsics.checkNotNull(mUploadMessage);
                    mUploadMessage.onReceiveValue(null);
                } else {
                    WebActivity2.this.setMUploadMessage(uploadMsg);
                    WebActivity2.this.selectImage();
                }
            }

            public final void openFileChooser(ValueCallback<Uri> uploadMsg) {
                WebActivity2.this.openFileChooserImpl(uploadMsg);
            }

            public final void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                WebActivity2.this.openFileChooserImpl(uploadMsg);
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x0059  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x009d  */
            /* JADX WARN: Removed duplicated region for block: B:41:0x00e1  */
            @Override // android.webkit.WebChromeClient
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public boolean onShowFileChooser(android.webkit.WebView r6, android.webkit.ValueCallback<android.net.Uri[]> r7, android.webkit.WebChromeClient.FileChooserParams r8) {
                /*
                    Method dump skipped, instruction units count: 231
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.ui.activity.WebActivity2.AnonymousClass4.onShowFileChooser(android.webkit.WebView, android.webkit.ValueCallback, android.webkit.WebChromeClient$FileChooserParams):boolean");
            }
        });
        String str = this.url;
        if (str != null) {
            setCookie(str);
            log(str);
        }
        String str2 = this.module;
        if (str2 != null) {
            Repository.INSTANCE.getWebNotice(str2, new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity2$init$6$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str3) {
                    invoke2(str3);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String data) {
                    Intrinsics.checkNotNullParameter(data, "data");
                    WebActivity2.access$getMBinding(this.this$0).wv.loadData(data, "", "");
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity2$init$6$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    WebActivity2 webActivity2 = this.this$0;
                    String localizedMessage = e.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    webActivity2.toast(localizedMessage);
                }
            });
        }
        if (getNewsId() > 0) {
            Repository.INSTANCE.getGameEventDetail(getNewsId(), new Function1<GameEventDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity2.init.7
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(GameEventDetailBean gameEventDetailBean) {
                    invoke2(gameEventDetailBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(GameEventDetailBean it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    WebActivity2.access$getMBinding(WebActivity2.this).wv.loadData(it.getMessage(), "", "");
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity2.init.8
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    WebActivity2.this.netFail(it);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(WebActivity2 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        log("cookie: " + CookieManager.getInstance().getCookie(NetUtil.BASE_URL3));
    }

    public final void setCookie(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        getMBinding().wv.loadUrl(url);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getMBinding().wv.canGoBack()) {
            getMBinding().wv.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public final void onLogin(LoginChangeBean result) {
        Intrinsics.checkNotNullParameter(result, "result");
        String str = this.url;
        if (str != null) {
            getMBinding().wv.reload();
            setCookie(str);
            log(str);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public final ValueCallback<Uri> getMUploadMessage() {
        return this.mUploadMessage;
    }

    public final void setMUploadMessage(ValueCallback<Uri> valueCallback) {
        this.mUploadMessage = valueCallback;
    }

    public final void openFileChooserImpl(ValueCallback<Uri> uploadMsg) {
        this.mUploadMessage = uploadMsg;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        Intent intentCreateChooser = Intent.createChooser(intent, "File Chooser");
        Intrinsics.checkNotNullExpressionValue(intentCreateChooser, "createChooser(...)");
        startActivityForResult(intentCreateChooser, this.FILECHOOSER_RESULTCODE);
    }

    public final ValueCallback<Uri[]> getMUploadMessageForAndroid5() {
        return this.mUploadMessageForAndroid5;
    }

    public final void setMUploadMessageForAndroid5(ValueCallback<Uri[]> valueCallback) {
        this.mUploadMessageForAndroid5 = valueCallback;
    }

    public final void onenFileChooseImpleForAndroid(ValueCallback<Uri[]> filePathCallback) {
        this.mUploadMessageForAndroid5 = filePathCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        Intent intent2 = new Intent("android.intent.action.CHOOSER");
        intent2.putExtra("android.intent.extra.INTENT", intent);
        intent2.putExtra("android.intent.extra.TITLE", "Image Chooser");
        startActivityForResult(intent2, this.FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.PICK");
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, this.REQ_CHOOSE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void selectVideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction("android.intent.action.PICK");
        intent.setData(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, this.REQ_CHOOSE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onReceiveValue() {
        ValueCallback<Uri[]> valueCallback = this.mUploadMessageForAndroid5;
        if (valueCallback != null) {
            Intrinsics.checkNotNull(valueCallback);
            valueCallback.onReceiveValue(null);
            this.mUploadMessageForAndroid5 = null;
        }
    }

    private final void recordVideo() {
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        intent.putExtra("android.intent.extra.videoQuality", 1);
        intent.putExtra("android.intent.extra.durationLimit", 10);
        startActivityForResult(intent, this.VIDEO_REQUEST);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Uri data;
        Uri data2;
        Uri data3;
        Uri data4;
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == this.FILECHOOSER_RESULTCODE) {
            if (this.mUploadMessage == null) {
                return;
            }
            if (resultCode != -1) {
                data4 = null;
            } else {
                Intrinsics.checkNotNull(intent);
                data4 = intent.getData();
            }
            ValueCallback<Uri> valueCallback = this.mUploadMessage;
            Intrinsics.checkNotNull(valueCallback);
            valueCallback.onReceiveValue(data4);
            this.mUploadMessage = null;
            return;
        }
        if (requestCode == this.FILECHOOSER_RESULTCODE_FOR_ANDROID_5) {
            if (this.mUploadMessageForAndroid5 == null) {
                return;
            }
            if (resultCode != -1) {
                data3 = null;
            } else {
                Intrinsics.checkNotNull(intent);
                data3 = intent.getData();
            }
            if (data3 != null) {
                ValueCallback<Uri[]> valueCallback2 = this.mUploadMessageForAndroid5;
                Intrinsics.checkNotNull(valueCallback2);
                valueCallback2.onReceiveValue(new Uri[]{data3});
            } else {
                ValueCallback<Uri[]> valueCallback3 = this.mUploadMessageForAndroid5;
                Intrinsics.checkNotNull(valueCallback3);
                valueCallback3.onReceiveValue(new Uri[0]);
            }
            this.mUploadMessageForAndroid5 = null;
            return;
        }
        if (requestCode == this.REQ_CHOOSE) {
            if (this.mUploadMessage == null && this.mUploadMessageForAndroid5 == null) {
                return;
            }
            if (resultCode != -1) {
                data2 = null;
            } else {
                Intrinsics.checkNotNull(intent);
                data2 = intent.getData();
            }
            ValueCallback<Uri[]> valueCallback4 = this.mUploadMessageForAndroid5;
            if (valueCallback4 != null) {
                if (resultCode == -1) {
                    Intrinsics.checkNotNull(valueCallback4);
                    Intrinsics.checkNotNull(data2);
                    valueCallback4.onReceiveValue(new Uri[]{data2});
                } else {
                    Intrinsics.checkNotNull(valueCallback4);
                    valueCallback4.onReceiveValue(new Uri[0]);
                }
                this.mUploadMessageForAndroid5 = null;
                return;
            }
            ValueCallback<Uri> valueCallback5 = this.mUploadMessage;
            if (valueCallback5 != null) {
                if (resultCode == -1) {
                    Intrinsics.checkNotNull(valueCallback5);
                    valueCallback5.onReceiveValue(data2);
                } else {
                    Intrinsics.checkNotNull(valueCallback5);
                    valueCallback5.onReceiveValue(Uri.EMPTY);
                }
                this.mUploadMessage = null;
                return;
            }
            return;
        }
        if (requestCode == this.VIDEO_REQUEST) {
            if (this.mUploadMessage == null && this.mUploadMessageForAndroid5 == null) {
                return;
            }
            if (resultCode != -1) {
                data = null;
            } else {
                Intrinsics.checkNotNull(intent);
                data = intent.getData();
            }
            ValueCallback<Uri[]> valueCallback6 = this.mUploadMessageForAndroid5;
            if (valueCallback6 != null) {
                if (resultCode == -1) {
                    Intrinsics.checkNotNull(valueCallback6);
                    Intrinsics.checkNotNull(data);
                    valueCallback6.onReceiveValue(new Uri[]{data});
                } else {
                    Intrinsics.checkNotNull(valueCallback6);
                    valueCallback6.onReceiveValue(new Uri[0]);
                }
                this.mUploadMessageForAndroid5 = null;
                return;
            }
            ValueCallback<Uri> valueCallback7 = this.mUploadMessage;
            if (valueCallback7 != null) {
                if (resultCode == -1) {
                    Intrinsics.checkNotNull(valueCallback7);
                    valueCallback7.onReceiveValue(data);
                } else {
                    Intrinsics.checkNotNull(valueCallback7);
                    valueCallback7.onReceiveValue(Uri.EMPTY);
                }
                this.mUploadMessage = null;
            }
        }
    }
}
