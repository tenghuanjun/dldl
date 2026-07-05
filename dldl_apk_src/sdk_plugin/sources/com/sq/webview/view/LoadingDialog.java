package com.sq.webview.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.sq.webview.R;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LoadingDialog extends Dialog {
    private Context mActivity;
    private ProgressBar mProgressBar;

    public LoadingDialog(Context context) {
        this(context, R.style.Dialog);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mActivity = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.sy37_web_view_loading_dialog, (ViewGroup) null);
        ProgressBar progressBar = (ProgressBar) viewInflate.findViewById(R.id.sy37_progressBar);
        this.mProgressBar = progressBar;
        progressBar.setVisibility(0);
        setCancelable(true);
        setContentView(viewInflate);
    }

    @Override // android.app.Dialog
    public void show() {
        Context context = this.mActivity;
        if ((context instanceof Activity) && (((Activity) context).isDestroyed() || ((Activity) this.mActivity).isFinishing())) {
            return;
        }
        super.show();
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            progressBar.setVisibility(0);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        ProgressBar progressBar;
        if (this.mActivity == null || (progressBar = this.mProgressBar) == null) {
            return;
        }
        progressBar.setVisibility(8);
        Context context = this.mActivity;
        if ((context instanceof Activity) && (((Activity) context).isDestroyed() || ((Activity) this.mActivity).isFinishing())) {
            return;
        }
        super.dismiss();
    }
}
