package com.sy37sdk.account.view.base.view;

import android.content.Context;
import com.sqwan.common.mvp.BasePresenter;
import com.sqwan.common.mvp.IView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class IAccountPresenter<V extends IView> extends BasePresenter<V> {
    public IAccountPresenter(Context context, V v) {
        super(context, v);
    }
}
