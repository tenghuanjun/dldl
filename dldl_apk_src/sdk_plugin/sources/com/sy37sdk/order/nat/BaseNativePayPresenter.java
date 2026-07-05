package com.sy37sdk.order.nat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.sqwan.common.mvp.IPageSwitcher;
import com.sy37sdk.order.nat.IBasePayView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class BaseNativePayPresenter<K extends IBasePayView> extends BasePayPresenter<K> {
    protected INativePayListener mPayListener;
    private IPageSwitcher pageSwitcher;

    public abstract View getView();

    @Override // com.sy37sdk.order.nat.BasePayPresenter
    public String payChannel() {
        return "2";
    }

    public BaseNativePayPresenter(Context context, K k) {
        super(context, k);
    }

    public void setPageSwitcher(IPageSwitcher iPageSwitcher) {
        this.pageSwitcher = iPageSwitcher;
    }

    public void setPayListener(INativePayListener iNativePayListener) {
        this.mPayListener = iNativePayListener;
    }

    public void onSwitch(int i) {
        IPageSwitcher iPageSwitcher = this.pageSwitcher;
        if (iPageSwitcher != null) {
            iPageSwitcher.onSwitch(i);
        }
    }

    public void onSwitched(int i, int i2, Bundle bundle) {
        ((IPageSwitchView) this.mView).onSwitched(i, i2, bundle);
    }

    public void onBackPressed() {
        ((IPageSwitchView) this.mView).onBackPressed();
    }

    public void onPayViewWindowFocusChanged(boolean z) {
        ((IPageSwitchView) this.mView).onPayViewWindowFocusChanged(z);
    }
}
