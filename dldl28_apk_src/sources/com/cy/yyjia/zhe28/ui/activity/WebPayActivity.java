package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityWebPayBinding;
import com.cy.yyjia.zhe28.util.NetUtil;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: WebPayActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u0017H\u0014R\u001d\u0010\u0004\u001a\u0004\u0018\u00010\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\n\u001a\u0004\u0018\u00010\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0013\u001a\u0004\u0018\u00010\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\t\u001a\u0004\b\u0014\u0010\u0007¨\u0006\u001a"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/WebPayActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityWebPayBinding;", "()V", "otherScheme", "", "getOtherScheme", "()Ljava/lang/String;", "otherScheme$delegate", "Lkotlin/Lazy;", "payData", "getPayData", "payData$delegate", "toPay", "", "getToPay", "()Z", "setToPay", "(Z)V", "url", "getUrl", "url$delegate", "finish", "", "init", "onResume", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class WebPayActivity extends BaseActivity<ActivityWebPayBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: otherScheme$delegate, reason: from kotlin metadata */
    private final Lazy otherScheme;

    /* JADX INFO: renamed from: payData$delegate, reason: from kotlin metadata */
    private final Lazy payData;
    private boolean toPay;

    /* JADX INFO: renamed from: url$delegate, reason: from kotlin metadata */
    private final Lazy url;

    public WebPayActivity() {
        super(R.layout.activity_web_pay, 0, 2, null);
        this.url = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebPayActivity$url$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getIntent().getStringExtra("url");
            }
        });
        this.payData = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebPayActivity$payData$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getIntent().getStringExtra("payData");
            }
        });
        this.otherScheme = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.activity.WebPayActivity$otherScheme$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getIntent().getStringExtra("otherScheme");
            }
        });
    }

    public final String getUrl() {
        return (String) this.url.getValue();
    }

    public final String getPayData() {
        return (String) this.payData.getValue();
    }

    public final String getOtherScheme() {
        return (String) this.otherScheme.getValue();
    }

    public final boolean getToPay() {
        return this.toPay;
    }

    public final void setToPay(boolean z) {
        this.toPay = z;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setTitle("支付");
        getMBinding().wv.getSettings().setJavaScriptEnabled(true);
        getMBinding().wv.getSettings().setLoadsImagesAutomatically(true);
        getMBinding().wv.getSettings().setCacheMode(2);
        getMBinding().wv.getSettings().setDomStorageEnabled(true);
        getMBinding().wv.setWebViewClient(new WebViewClient() { // from class: com.cy.yyjia.zhe28.ui.activity.WebPayActivity.init.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebPayActivity.this.log("url" + url);
                Intrinsics.checkNotNull(url);
                if (StringsKt.startsWith$default(url, "http:", false, 2, (Object) null) || StringsKt.startsWith$default(url, "https:", false, 2, (Object) null)) {
                    WebPayActivity.this.setToPay(true);
                    return false;
                }
                try {
                    WebPayActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    WebPayActivity.this.setToPay(true);
                } catch (Exception e) {
                    WebPayActivity webPayActivity = WebPayActivity.this;
                    String localizedMessage = e.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    webPayActivity.log(localizedMessage);
                }
                return true;
            }
        });
        String url = getUrl();
        if (url != null) {
            log(url);
            HashMap map = new HashMap();
            map.put("Referer", NetUtil.BASE_URL3);
            getMBinding().wv.loadUrl(url, map);
        }
        if (getPayData() != null) {
            WebView webView = getMBinding().wv;
            String payData = getPayData();
            Intrinsics.checkNotNull(payData);
            webView.loadData(payData, "", "");
        }
        String otherScheme = getOtherScheme();
        if (otherScheme != null) {
            log(otherScheme);
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(otherScheme)));
                this.toPay = true;
            } catch (Exception e) {
                String localizedMessage = e.getLocalizedMessage();
                Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                log(localizedMessage);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.toPay) {
            finish();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        getMBinding().wv.loadUrl("about:blank");
        log("关闭");
    }
}
