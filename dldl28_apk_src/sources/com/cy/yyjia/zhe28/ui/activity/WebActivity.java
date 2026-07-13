package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityWebBinding;
import com.cy.yyjia.zhe28.domain.LoginChangeBean;
import com.cy.yyjia.zhe28.domain.MessageBean;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.NetUtil;
import com.cy.yyjia.zhe28.util.ObjectInterface;
import com.cy.yyjia.zhe28.util.Repository;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringEscapeUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: WebActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0014J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000bR\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/WebActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityWebBinding;", "()V", "messageId", "", "getMessageId", "()I", "messageId$delegate", "Lkotlin/Lazy;", "module", "", "url", "init", "", "onBackPressed", "onDestroy", "onLogin", "result", "Lcom/cy/yyjia/zhe28/domain/LoginChangeBean;", "setCookie", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class WebActivity extends BaseActivity<ActivityWebBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: messageId$delegate, reason: from kotlin metadata */
    private final Lazy messageId;
    private String module;
    private String url;

    public WebActivity() {
        super(R.layout.activity_web, 0, 2, null);
        this.url = "";
        this.module = "";
        this.messageId = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity$messageId$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("messageId", 0));
            }
        });
    }

    public static final /* synthetic */ ActivityWebBinding access$getMBinding(WebActivity webActivity) {
        return webActivity.getMBinding();
    }

    private final int getMessageId() {
        return ((Number) this.messageId.getValue()).intValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        EventBus.getDefault().register(this);
        getMBinding().navigation.setBackClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WebActivity.init$lambda$0(this.f$0, view);
            }
        });
        if (getIntent().getBooleanExtra("game", false)) {
            getMBinding().navigation.setVisibility(8);
        }
        String stringExtra = getIntent().getStringExtra("color");
        if (stringExtra != null) {
            getMBinding().navigation.setBackgroundColor(Color.parseColor(stringExtra));
            immersionBar(stringExtra, true);
        }
        this.url = getIntent().getStringExtra("url");
        this.module = getIntent().getStringExtra("module");
        getMBinding().setTitle(getIntent().getStringExtra("name"));
        getMBinding().wv.getSettings().setJavaScriptEnabled(true);
        getMBinding().wv.getSettings().setLoadsImagesAutomatically(true);
        getMBinding().wv.getSettings().setCacheMode(2);
        getMBinding().wv.getSettings().setDomStorageEnabled(true);
        WebView webView = getMBinding().wv;
        WebView wv = getMBinding().wv;
        Intrinsics.checkNotNullExpressionValue(wv, "wv");
        webView.addJavascriptInterface(new ObjectInterface(this, wv), Constant.INSTANCE.getJS_NAME());
        getMBinding().wv.clearCache(true);
        getMBinding().wv.getSettings().setUserAgentString(getMBinding().wv.getSettings().getUserAgentString() + "/androidbox;");
        String userAgentString = getMBinding().wv.getSettings().getUserAgentString();
        Intrinsics.checkNotNullExpressionValue(userAgentString, "getUserAgentString(...)");
        log(userAgentString);
        getMBinding().wv.setWebChromeClient(new WebChromeClient() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity.init.3
        });
        getMBinding().wv.setWebViewClient(new WebViewClient() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity.init.4
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebActivity.this.log("url: " + url);
                Intrinsics.checkNotNull(url);
                if (StringsKt.contains$default((CharSequence) url, (CharSequence) "&quot;", false, 2, (Object) null)) {
                    String strUnescapeHtml3 = StringEscapeUtils.unescapeHtml3(url);
                    WebActivity.this.log("url2: " + strUnescapeHtml3);
                    if (view != null) {
                        view.loadUrl(strUnescapeHtml3);
                    }
                    return true;
                }
                if (StringsKt.startsWith$default(url, "http:", false, 2, (Object) null) || StringsKt.startsWith$default(url, "https:", false, 2, (Object) null)) {
                    WebActivity.this.log("跳转");
                    return false;
                }
                try {
                    WebActivity.this.log("跳转其他协议");
                    WebActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                } catch (Exception e) {
                    WebActivity webActivity = WebActivity.this;
                    String localizedMessage = e.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    webActivity.log(localizedMessage);
                }
                return true;
            }
        });
        String str = this.url;
        if (str != null) {
            setCookie(str);
            log(str);
        }
        String str2 = this.module;
        if (str2 != null) {
            Repository.INSTANCE.getWebNotice(str2, new Function1<String, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity$init$6$1
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
                    WebActivity.access$getMBinding(this.this$0).wv.loadData(data, "", "");
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity$init$6$2
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
                    WebActivity webActivity = this.this$0;
                    String localizedMessage = e.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    webActivity.toast(localizedMessage);
                }
            });
        }
        if (getMessageId() > 0) {
            Repository.INSTANCE.getMessageDetail(getMessageId(), new Function1<MessageBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity.init.7
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MessageBean messageBean) {
                    invoke2(messageBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MessageBean it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    WebActivity.access$getMBinding(WebActivity.this).wv.loadData(it.getContent(), "", "");
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebActivity.init.8
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
                    WebActivity.this.netFail(it);
                }
            });
        }
        log("cookie: " + CookieManager.getInstance().getCookie(NetUtil.BASE_URL3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(WebActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public final void setCookie(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        HashMap map = new HashMap();
        map.put("Referer", NetUtil.BASE_URL3);
        map.put("HTTP_USER_AGENT", "icbc");
        getMBinding().wv.loadUrl(url, map);
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
            setCookie(str);
            log(str);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
