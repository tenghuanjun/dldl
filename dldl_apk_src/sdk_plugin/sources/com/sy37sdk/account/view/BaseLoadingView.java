package com.sy37sdk.account.view;

import android.content.Context;
import com.sqwan.common.mvp.ILoadView;
import com.sqwan.common.view.BaseView;
import com.sy37sdk.account.view.uifast.ILoginDialog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class BaseLoadingView extends BaseView implements ILoadView {
    protected ILoginDialog loginDialog;

    public BaseLoadingView(Context context) {
        super(context);
    }

    @Override // com.sqwan.common.mvp.ILoadView
    public void showLoading() {
        ILoginDialog iLoginDialog = this.loginDialog;
        if (iLoginDialog != null) {
            iLoginDialog.showLoading();
        }
    }

    @Override // com.sqwan.common.mvp.ILoadView
    public void hideLoading() {
        ILoginDialog iLoginDialog = this.loginDialog;
        if (iLoginDialog != null) {
            iLoginDialog.hideLoading();
        }
    }
}
