package com.sy37sdk.account.auth;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.webview.SQWebViewDialog;
import com.sy37sdk.account.AccountCache;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AuthDialog extends SQWebViewDialog {
    private boolean isFocus;
    private boolean isFocusAuthCdFinish;
    private CloseListener mCloseListener;

    public interface CloseListener {
        void onClose(String str, String str2);
    }

    public void setFocusAuthCdFinish(boolean z) {
        this.isFocusAuthCdFinish = z;
    }

    public boolean isFocusAuthCdFinish() {
        return this.isFocusAuthCdFinish;
    }

    public AuthDialog(Context context) {
        super(context);
        this.isFocusAuthCdFinish = false;
        this.isFocus = false;
    }

    public AuthDialog(Context context, int i) {
        super(context, i);
        this.isFocusAuthCdFinish = false;
        this.isFocus = false;
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog, com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(-1, -1);
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog
    protected void timeOut() {
        super.timeOut();
        if (this.isFocus) {
            return;
        }
        dismiss();
    }

    public void setFocus(boolean z) {
        setCancelable(!z);
        this.isFocus = z;
    }

    public void setCloseListener(CloseListener closeListener) {
        this.mCloseListener = closeListener;
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
        if (this.isFocus) {
            return;
        }
        this.mCloseListener.onClose("", "");
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog
    protected void jsClose(String str, String str2) {
        super.jsClose(str, str2);
        CloseListener closeListener = this.mCloseListener;
        if (closeListener != null) {
            closeListener.onClose(str, str2);
        }
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog, com.sqwan.common.dialog.FullScreenDialog, android.app.Dialog
    public void show() {
        setFocusAuthCdFinish(false);
        loadUrl();
        super.show();
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog
    protected void jsEnLogin() {
        super.jsEnLogin();
        SqTrackActionManager2.getInstance().trackBtn(SqTrackBtn.SqTrackBtnId.changeAccount, SqTrackBtn.SqTrackBtnExt.CHANGE_ACCOUNT_AUTH);
        HashMap map = new HashMap();
        map.put(SqTrackKey.logout_type, SqTrackBtn.SqTrackBtnExt.CHANGE_ACCOUNT_AUTH);
        map.put("login_type", AccountCache.getLoginType(getContext()));
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.LOGOUT_SUCC, map);
    }

    @Override // com.sqwan.common.webview.SQWebViewDialog
    protected void jsCertificate(String str, String str2) {
        super.jsCertificate(str, str2);
        HashMap map = new HashMap();
        map.put(SqTrackKey.fail_code, str);
        map.put(SqTrackKey.reason_fail, str2);
        map.put(SqTrackKey.certification_url, this.mUrl);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.certification_fail, map);
    }
}
