package com.sqwan.common.mvp;

import android.content.Context;
import android.view.View;
import com.sqwan.common.mod.account.ILoginListener;
import com.sqwan.common.mvp.IView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseViewPagerPresenter<K extends IView> extends BasePresenter<K> {
    protected ILoginListener mLoginListener;
    private IPageSwitcher mPageSwitcher;

    public abstract View getView();

    public BaseViewPagerPresenter(Context context, K k) {
        super(context, k);
    }

    public void setAccountPageSwitcher(IPageSwitcher iPageSwitcher) {
        this.mPageSwitcher = iPageSwitcher;
    }

    public void setLoginListener(ILoginListener iLoginListener) {
        this.mLoginListener = iLoginListener;
    }

    public void onSwitch(int i) {
        IPageSwitcher iPageSwitcher = this.mPageSwitcher;
        if (iPageSwitcher != null) {
            iPageSwitcher.onSwitch(i);
        }
    }
}
