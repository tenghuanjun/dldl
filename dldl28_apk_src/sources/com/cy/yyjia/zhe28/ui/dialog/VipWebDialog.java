package com.cy.yyjia.zhe28.ui.dialog;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogVipWebBinding;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.ObjectInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringEscapeUtils;

/* JADX INFO: compiled from: VipWebDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/VipWebDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogVipWebBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "setUrl", "url", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VipWebDialog extends BaseDataBindingDialog<DialogVipWebBinding, VipWebDialog> {
    public static final int $stable = 0;

    /* JADX WARN: Illegal instructions before constructor call */
    public VipWebDialog(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        FragmentActivity fragmentActivity = activity;
        super(fragmentActivity, R.layout.dialog_vip_web);
        ((DialogVipWebBinding) this.mBinding).wv.getSettings().setJavaScriptEnabled(true);
        ((DialogVipWebBinding) this.mBinding).wv.getSettings().setLoadsImagesAutomatically(true);
        ((DialogVipWebBinding) this.mBinding).wv.getSettings().setCacheMode(2);
        ((DialogVipWebBinding) this.mBinding).wv.getSettings().setDomStorageEnabled(true);
        WebView webView = ((DialogVipWebBinding) this.mBinding).wv;
        WebView wv = ((DialogVipWebBinding) this.mBinding).wv;
        Intrinsics.checkNotNullExpressionValue(wv, "wv");
        webView.addJavascriptInterface(new ObjectInterface(fragmentActivity, wv), Constant.INSTANCE.getJS_NAME());
        ((DialogVipWebBinding) this.mBinding).wv.clearCache(true);
        ((DialogVipWebBinding) this.mBinding).wv.getSettings().setUserAgentString(((DialogVipWebBinding) this.mBinding).wv.getSettings().getUserAgentString() + "/androidbox;");
        log(((DialogVipWebBinding) this.mBinding).wv.getSettings().getUserAgentString());
        ((DialogVipWebBinding) this.mBinding).wv.setWebChromeClient(new WebChromeClient() { // from class: com.cy.yyjia.zhe28.ui.dialog.VipWebDialog.1
        });
        ((DialogVipWebBinding) this.mBinding).wv.setWebViewClient(new WebViewClient() { // from class: com.cy.yyjia.zhe28.ui.dialog.VipWebDialog.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                VipWebDialog.this.log("url: " + url);
                Intrinsics.checkNotNull(url);
                if (StringsKt.contains$default((CharSequence) url, (CharSequence) "&quot;", false, 2, (Object) null)) {
                    String strUnescapeHtml3 = StringEscapeUtils.unescapeHtml3(url);
                    VipWebDialog.this.log("url2: " + strUnescapeHtml3);
                    if (view != null) {
                        view.loadUrl(strUnescapeHtml3);
                    }
                    return true;
                }
                if (!StringsKt.startsWith$default(url, "http:", false, 2, (Object) null) && !StringsKt.startsWith$default(url, "https:", false, 2, (Object) null)) {
                    return true;
                }
                VipWebDialog.this.log("跳转");
                return false;
            }
        });
    }

    public final VipWebDialog setUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        ((DialogVipWebBinding) this.mBinding).wv.loadUrl(url);
        return this;
    }
}
