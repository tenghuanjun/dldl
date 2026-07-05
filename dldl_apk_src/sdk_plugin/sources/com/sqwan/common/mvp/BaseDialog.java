package com.sqwan.common.mvp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sqwan.common.dialog.FullScreenDialog;
import com.sqwan.common.dialog.LoadingDialog;
import com.sqwan.common.util.AndroidBug5497Workaround;
import com.sqwan.common.util.SqResUtils;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.msdk.config.ConfigManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BaseDialog extends FullScreenDialog implements ILoadView {
    private LoadingDialog loadingDialog;
    protected Context mContext;

    public BaseDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    protected int getIdByName(String str, String str2) {
        return SqResUtils.getIdByName(str, str2, getContext());
    }

    protected boolean isSplashSDK() {
        return ConfigManager.getInstance(getContext()).isSplashSDK();
    }

    @Override // com.sqwan.common.mvp.ILoadView
    public void showLoading() {
        if (this.loadingDialog == null) {
            LoadingDialog loadingDialog = new LoadingDialog(this.mContext);
            this.loadingDialog = loadingDialog;
            loadingDialog.setCancelable(false);
        }
        LoadingDialog loadingDialog2 = this.loadingDialog;
        if (loadingDialog2 == null || loadingDialog2.isShowing()) {
            return;
        }
        this.loadingDialog.show();
    }

    @Override // com.sqwan.common.mvp.ILoadView
    public void hideLoading() {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            return;
        }
        this.loadingDialog.dismiss();
    }

    public void setLoadingMsg(String str) {
        LoadingDialog loadingDialog = this.loadingDialog;
        if (loadingDialog == null || !loadingDialog.isShowing()) {
            return;
        }
        this.loadingDialog.setMessage(str);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        super.setContentView(view);
        if (this.mContext.getResources().getConfiguration().orientation == 1) {
            AndroidBug5497Workaround(view);
        }
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        setContentView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(i, (ViewGroup) null));
    }

    protected void toast(String str) {
        ToastUtil.showToast(getContext(), str);
    }

    protected void AndroidBug5497Workaround(View view) {
        Context context = this.mContext;
        if (context == null || view == null || !(context instanceof Activity)) {
            return;
        }
        AndroidBug5497Workaround.assistActivity((Activity) context, view);
    }
}
