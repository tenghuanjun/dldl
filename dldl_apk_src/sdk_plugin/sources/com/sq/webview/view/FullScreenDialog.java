package com.sq.webview.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import com.sq.webview.util.StatusBarUtil;
import com.sq.webview.util.WebResUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FullScreenDialog extends Dialog {
    private Context mContext;

    public FullScreenDialog(Context context) {
        super(context, WebResUtil.getStyleId(context, "FullScreenDialogStyle"));
        this.mContext = context;
    }

    public FullScreenDialog(Context context, int themeResId) {
        super(context, WebResUtil.getStyleId(context, "FullScreenDialogStyle"));
        this.mContext = context;
    }

    public FullScreenDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // android.app.Dialog
    public void show() {
        Context context = this.mContext;
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return;
        }
        StatusBarUtil.hideSystemUI(getWindow());
        super.show();
    }
}
