package com.sy37sdk.account.captcha;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import com.sqwan.common.webview.SQWebViewDialog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CaptchaDialog extends SQWebViewDialog {
    private static final String SUCCESS_TAG = "1";
    private VerifyListener mListener;

    public interface VerifyListener {
        void result(boolean z, String str);
    }

    public CaptchaDialog(Context context) {
        super(context);
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog, com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.sy37sdk.account.captcha.CaptchaDialog.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (CaptchaDialog.this.mListener != null) {
                    CaptchaDialog.this.mListener.result(false, "取消验证");
                }
            }
        });
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog
    protected void jsClose(String str, String str2) {
        super.jsClose(str, str2);
        dismiss();
        boolean z = !TextUtils.isEmpty(str) && str.equals("1");
        VerifyListener verifyListener = this.mListener;
        if (verifyListener != null) {
            verifyListener.result(z, z ? "验证成功" : "验证失败");
        }
    }

    public void setVerifyListener(VerifyListener verifyListener) {
        this.mListener = verifyListener;
    }
}
