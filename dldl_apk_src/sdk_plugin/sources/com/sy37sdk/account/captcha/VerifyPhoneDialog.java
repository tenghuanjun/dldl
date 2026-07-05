package com.sy37sdk.account.captcha;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import com.sqwan.common.web.WebViewToolBar;
import com.sqwan.common.webview.SQWebViewDialog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class VerifyPhoneDialog extends SQWebViewDialog {
    private static final String SUCCESS_TAG = "1";
    protected VerifyListener mListener;

    public interface VerifyListener {
        void result(boolean z, String str);
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog
    protected boolean isShowWebBar() {
        return true;
    }

    public VerifyPhoneDialog(Context context) {
        super(context);
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog, com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.sy37sdk.account.captcha.VerifyPhoneDialog.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (VerifyPhoneDialog.this.mListener != null) {
                    VerifyPhoneDialog.this.mListener.result(false, "取消验证");
                }
            }
        });
        setWebToolBarClickListener(new WebViewToolBar.WebToolBarClickListener() { // from class: com.sy37sdk.account.captcha.VerifyPhoneDialog.2
            @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
            public void onClickBack() {
            }

            @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
            public void onClickForward() {
            }

            @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
            public void onClickRefresh() {
            }

            @Override // com.sqwan.common.web.WebViewToolBar.WebToolBarClickListener
            public void onClickClose() {
                if (VerifyPhoneDialog.this.mListener != null) {
                    VerifyPhoneDialog.this.mListener.result(false, "取消验证");
                }
            }
        });
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog
    protected void jsClose(String str, String str2) {
        super.jsClose(str, str2);
        this.mListener.result(!TextUtils.isEmpty(str) && str.equals("1"), str2);
    }

    public void setVerifyListener(VerifyListener verifyListener) {
        this.mListener = verifyListener;
    }
}
