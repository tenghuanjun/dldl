package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityWeb2Binding;
import com.cy.yyjia.zhe28.domain.GameEventDetailBean;
import com.cy.yyjia.zhe28.domain.LoginChangeBean;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.ObjectInterface;
import com.cy.yyjia.zhe28.util.PathUtils;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.mobile.auth.gatewayauth.Constant;
import java.io.File;
import java.io.IOException;
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

/* JADX INFO: compiled from: WebActivity3.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020 H\u0016J\"\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u00052\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\b\u0010+\u001a\u00020 H\u0016J\b\u0010,\u001a\u00020 H\u0014J\u0010\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020/H\u0007J\b\u00100\u001a\u00020 H\u0002J\b\u00101\u001a\u00020 H\u0014J\b\u00102\u001a\u00020 H\u0002J\b\u00103\u001a\u00020 H\u0002J\u0016\u00104\u001a\u00020 2\u000e\u00105\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016J\b\u00106\u001a\u00020 H\u0002J\b\u00107\u001a\u00020 H\u0002J\b\u00108\u001a\u00020 H\u0002J\u000e\u00109\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0019\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u0014\u001a\u0004\b\u001c\u0010\u0007R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/WebActivity3;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityWeb2Binding;", "()V", "FILECHOOSER_RESULTCODE", "", "getFILECHOOSER_RESULTCODE", "()I", "PATH", "", "kotlin.jvm.PlatformType", "REQ_CAMERA", "REQ_CHOOSE", "VIDEO_REQUEST", "imageName", "js", "Lcom/cy/yyjia/zhe28/util/ObjectInterface;", "getJs", "()Lcom/cy/yyjia/zhe28/util/ObjectInterface;", "js$delegate", "Lkotlin/Lazy;", "mUploadMessage", "Landroid/webkit/ValueCallback;", "Landroid/net/Uri;", "mUploadMessageForAndroid5", "", "module", "newsId", "getNewsId", "newsId$delegate", "url", "handleFile", "", "file", "Ljava/io/File;", "hasSDcard", "", "init", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "resultCode", "intent", "Landroid/content/Intent;", "onBackPressed", "onDestroy", "onLogin", "result", "Lcom/cy/yyjia/zhe28/domain/LoginChangeBean;", "onReceiveValue", "onResume", "openAlbum", "openCamera", "openFileChooserImpl", "uploadMsg", "openFileInput", "recordVideo", "selectImage", "setCookie", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class WebActivity3 extends BaseActivity<ActivityWeb2Binding> {
    public static final int $stable = 8;
    private final int FILECHOOSER_RESULTCODE;
    private final String PATH;
    private final int REQ_CAMERA;
    private final int REQ_CHOOSE;
    private final int VIDEO_REQUEST;
    private String imageName;

    /* JADX INFO: renamed from: js$delegate, reason: from kotlin metadata */
    private final Lazy js;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessageForAndroid5;
    private String module;

    /* JADX INFO: renamed from: newsId$delegate, reason: from kotlin metadata */
    private final Lazy newsId;
    private String url;

    public WebActivity3() {
        super(R.layout.activity_web2, 0, 2, null);
        this.REQ_CAMERA = 1;
        this.REQ_CHOOSE = 1 + 1;
        this.FILECHOOSER_RESULTCODE = 1222;
        this.VIDEO_REQUEST = 120;
        this.PATH = Environment.DIRECTORY_DCIM;
        this.url = "";
        this.module = "";
        this.newsId = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity3$newsId$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("newsId", 0));
            }
        });
        this.js = LazyKt.lazy(new Function0<ObjectInterface>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity3$js$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ObjectInterface invoke() {
                WebActivity3 webActivity3 = this.this$0;
                WebActivity3 webActivity32 = webActivity3;
                WebView wv = WebActivity3.access$getMBinding(webActivity3).wv;
                Intrinsics.checkNotNullExpressionValue(wv, "wv");
                return new ObjectInterface(webActivity32, wv);
            }
        });
    }

    public static final /* synthetic */ ActivityWeb2Binding access$getMBinding(WebActivity3 webActivity3) {
        return webActivity3.getMBinding();
    }

    public final int getFILECHOOSER_RESULTCODE() {
        return this.FILECHOOSER_RESULTCODE;
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
        getMBinding().navigation.setBackClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity3$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebActivity3.init$lambda$1(this.f$0, view);
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
        getMBinding().wv.setWebViewClient(new WebViewClient() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity3.init.3
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebActivity3.this.log("url: " + url);
                Intrinsics.checkNotNull(url);
                if (StringsKt.startsWith$default(url, "http:", false, 2, (Object) null) || StringsKt.startsWith$default(url, "https:", false, 2, (Object) null)) {
                    return false;
                }
                try {
                    WebActivity3.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    return true;
                } catch (Exception e) {
                    WebActivity3 webActivity3 = WebActivity3.this;
                    String localizedMessage = e.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    webActivity3.log(localizedMessage);
                    return true;
                }
            }
        });
        getMBinding().wv.setWebChromeClient(new WebChromeClient() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity3.init.4
            public static /* synthetic */ void openFileChooser$default(AnonymousClass4 anonymousClass4, ValueCallback valueCallback, String str, int i, Object obj) {
                if ((i & 2) != 0) {
                    str = "image/*";
                }
                anonymousClass4.openFileChooser(valueCallback, str);
            }

            public final void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                Intrinsics.checkNotNullParameter(acceptType, "acceptType");
                if (Intrinsics.areEqual(acceptType, "image/*")) {
                    if (WebActivity3.this.mUploadMessage != null) {
                        ValueCallback valueCallback = WebActivity3.this.mUploadMessage;
                        Intrinsics.checkNotNull(valueCallback);
                        valueCallback.onReceiveValue(null);
                        return;
                    } else {
                        WebActivity3.this.mUploadMessage = uploadMsg;
                        WebActivity3.this.selectImage();
                        return;
                    }
                }
                WebActivity3.this.onReceiveValue();
            }

            public final void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                Intrinsics.checkNotNullParameter(acceptType, "acceptType");
                openFileChooser(uploadMsg, acceptType);
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
                throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.ui.activity.WebActivity3.AnonymousClass4.onShowFileChooser(android.webkit.WebView, android.webkit.ValueCallback, android.webkit.WebChromeClient$FileChooserParams):boolean");
            }
        });
        String str = this.url;
        if (str != null) {
            setCookie(str);
            log(str);
        }
        String str2 = this.module;
        if (str2 != null) {
            Repository.INSTANCE.getWebNotice(str2, new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity3$init$6$1
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
                    WebActivity3.access$getMBinding(this.this$0).wv.loadData(data, "", "");
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity3$init$6$2
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
                    WebActivity3 webActivity3 = this.this$0;
                    String localizedMessage = e.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    webActivity3.toast(localizedMessage);
                }
            });
        }
        if (getNewsId() > 0) {
            Repository.INSTANCE.getGameEventDetail(getNewsId(), new Function1<GameEventDetailBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity3.init.7
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
                    WebActivity3.access$getMBinding(WebActivity3.this).wv.loadData(it.getMessage(), "", "");
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity3.init.8
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
                    WebActivity3.this.netFail(it);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(WebActivity3 this$0, View view) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void openFileInput() {
        this.imageName = String.valueOf(System.currentTimeMillis());
        String PATH = this.PATH;
        Intrinsics.checkNotNullExpressionValue(PATH, "PATH");
        File file = new File(PATH);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri uriFromFile = Uri.fromFile(file);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", uriFromFile);
        Intent intent2 = new Intent("android.intent.action.GET_CONTENT");
        intent2.setType("*/*");
        Intent intentCreateChooser = Intent.createChooser(intent2, "选择操作");
        intentCreateChooser.putExtra("android.intent.extra.INITIAL_INTENTS", new Parcelable[]{intent});
        Intrinsics.checkNotNull(intentCreateChooser);
        startActivityForResult(intentCreateChooser, this.REQ_CHOOSE);
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordVideo() {
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        intent.putExtra("android.intent.extra.videoQuality", 1);
        intent.putExtra("android.intent.extra.durationLimit", 10);
        startActivityForResult(intent, this.VIDEO_REQUEST);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void selectImage() {
        openAlbum();
    }

    private final void openAlbum() {
        if (hasSDcard()) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction("android.intent.action.PICK");
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, this.REQ_CHOOSE);
        }
    }

    private final void openCamera() {
        if (hasSDcard()) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            this.imageName = System.currentTimeMillis() + ".png";
            File file = new File(this.PATH, this.imageName);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    log(e.toString());
                }
            }
            intent.putExtra("output", Util.getFileUri(this, file));
            startActivityForResult(intent, this.REQ_CAMERA);
        }
    }

    private final boolean hasSDcard() {
        boolean zAreEqual = Intrinsics.areEqual(Environment.getExternalStorageState(), "mounted");
        if (!zAreEqual) {
            Toast.makeText(this, "请插入手机存储卡再使用本功能", 0).show();
            onReceiveValue();
        }
        return zAreEqual;
    }

    private final void handleFile(File file) {
        if (file.isFile()) {
            if (this.mUploadMessageForAndroid5 == null) {
                return;
            }
            Uri[] uriArr = {Uri.fromFile(file)};
            ValueCallback<Uri[]> valueCallback = this.mUploadMessageForAndroid5;
            Intrinsics.checkNotNull(valueCallback);
            valueCallback.onReceiveValue(uriArr);
            this.mUploadMessageForAndroid5 = null;
            return;
        }
        onReceiveValue();
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

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode != -1) {
            onReceiveValue();
            return;
        }
        if (requestCode == this.REQ_CAMERA) {
            handleFile(new File(this.PATH, this.imageName));
            return;
        }
        if (requestCode == this.REQ_CHOOSE) {
            Intrinsics.checkNotNull(intent);
            String path = PathUtils.getPath(this, intent.getData());
            Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
            handleFile(new File(path));
            return;
        }
        if (requestCode == this.VIDEO_REQUEST) {
            if (this.mUploadMessage == null && this.mUploadMessageForAndroid5 == null) {
                return;
            }
            Uri data = (intent == null || resultCode != -1) ? null : intent.getData();
            ValueCallback<Uri[]> valueCallback = this.mUploadMessageForAndroid5;
            if (valueCallback != null) {
                if (resultCode == -1) {
                    Intrinsics.checkNotNull(valueCallback);
                    Intrinsics.checkNotNull(data);
                    valueCallback.onReceiveValue(new Uri[]{data});
                } else {
                    Intrinsics.checkNotNull(valueCallback);
                    valueCallback.onReceiveValue(new Uri[0]);
                }
                this.mUploadMessageForAndroid5 = null;
                return;
            }
            ValueCallback<Uri> valueCallback2 = this.mUploadMessage;
            if (valueCallback2 != null) {
                if (resultCode == -1) {
                    Intrinsics.checkNotNull(valueCallback2);
                    valueCallback2.onReceiveValue(data);
                } else {
                    Intrinsics.checkNotNull(valueCallback2);
                    valueCallback2.onReceiveValue(Uri.EMPTY);
                }
                this.mUploadMessage = null;
            }
        }
    }
}
