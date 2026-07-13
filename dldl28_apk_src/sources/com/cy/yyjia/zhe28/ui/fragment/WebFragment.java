package com.cy.yyjia.zhe28.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.lifecycle.ViewModelProvider;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseFragment;
import com.cy.yyjia.zhe28.databinding.FragmentWebBinding;
import com.cy.yyjia.zhe28.domain.MainViewModel;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.ObjectInterface;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: WebFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\n\u001a\u0004\u0018\u00010\u000b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\t\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000f\u001a\u00020\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\t\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/WebFragment;", "Lcom/cy/yyjia/zhe28/base/BaseFragment;", "Lcom/cy/yyjia/zhe28/databinding/FragmentWebBinding;", "()V", "js", "Lcom/cy/yyjia/zhe28/util/ObjectInterface;", "getJs", "()Lcom/cy/yyjia/zhe28/util/ObjectInterface;", "js$delegate", "Lkotlin/Lazy;", "url", "", "getUrl", "()Ljava/lang/String;", "url$delegate", "vm", "Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "getVm", "()Lcom/cy/yyjia/zhe28/domain/MainViewModel;", "vm$delegate", "init", "", "onResume", "Companion", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class WebFragment extends BaseFragment<FragmentWebBinding> {

    /* JADX INFO: renamed from: js$delegate, reason: from kotlin metadata */
    private final Lazy js;

    /* JADX INFO: renamed from: url$delegate, reason: from kotlin metadata */
    private final Lazy url;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public WebFragment() {
        super(R.layout.fragment_web);
        this.vm = LazyKt.lazy(new Function0<MainViewModel>() { // from class: com.cy.yyjia.zhe28.ui.fragment.WebFragment$vm$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MainViewModel invoke() {
                return (MainViewModel) new ViewModelProvider(this.this$0.getMContext()).get(MainViewModel.class);
            }
        });
        this.js = LazyKt.lazy(new Function0<ObjectInterface>() { // from class: com.cy.yyjia.zhe28.ui.fragment.WebFragment$js$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ObjectInterface invoke() {
                BaseActivity mContext = this.this$0.getMContext();
                WebView wv = this.this$0.getMBinding().wv;
                Intrinsics.checkNotNullExpressionValue(wv, "wv");
                return new ObjectInterface(mContext, wv);
            }
        });
        this.url = LazyKt.lazy(new Function0<String>() { // from class: com.cy.yyjia.zhe28.ui.fragment.WebFragment$url$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.requireArguments().getString("url");
            }
        });
    }

    public final MainViewModel getVm() {
        return (MainViewModel) this.vm.getValue();
    }

    public final ObjectInterface getJs() {
        return (ObjectInterface) this.js.getValue();
    }

    /* JADX INFO: compiled from: WebFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/fragment/WebFragment$Companion;", "", "()V", "newInstance", "Lcom/cy/yyjia/zhe28/ui/fragment/WebFragment;", "url", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WebFragment newInstance(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            Bundle bundle = new Bundle();
            bundle.putString("url", url);
            WebFragment webFragment = new WebFragment();
            webFragment.setArguments(bundle);
            return webFragment;
        }
    }

    public final String getUrl() {
        return (String) this.url.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseFragment
    public void init() {
        getMBinding().wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getMBinding().wv.getSettings().setJavaScriptEnabled(true);
        getMBinding().wv.getSettings().setLoadsImagesAutomatically(true);
        getMBinding().wv.getSettings().setCacheMode(2);
        getMBinding().wv.getSettings().setDomStorageEnabled(true);
        getMBinding().wv.getSettings().setUserAgentString(getMBinding().wv.getSettings().getUserAgentString() + "/androidbox;");
        String userAgentString = getMBinding().wv.getSettings().getUserAgentString();
        Intrinsics.checkNotNullExpressionValue(userAgentString, "getUserAgentString(...)");
        log(userAgentString);
        log(Constant.INSTANCE.getJS_NAME());
        getMBinding().wv.addJavascriptInterface(getJs(), Constant.INSTANCE.getJS_NAME());
        getMBinding().wv.clearCache(true);
        getMBinding().wv.setWebViewClient(new WebViewClient() { // from class: com.cy.yyjia.zhe28.ui.fragment.WebFragment.init.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                WebFragment.this.log("url: " + url);
                Intrinsics.checkNotNull(url);
                if (StringsKt.startsWith$default(url, "http:", false, 2, (Object) null) || StringsKt.startsWith$default(url, "https:", false, 2, (Object) null)) {
                    return false;
                }
                try {
                    WebFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    return true;
                } catch (Exception e) {
                    WebFragment webFragment = WebFragment.this;
                    String localizedMessage = e.getLocalizedMessage();
                    Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                    webFragment.log(localizedMessage);
                    return true;
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        String url = getUrl();
        if (url != null) {
            getMBinding().wv.loadUrl(url);
        }
    }
}
