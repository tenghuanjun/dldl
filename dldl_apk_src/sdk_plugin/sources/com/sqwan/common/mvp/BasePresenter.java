package com.sqwan.common.mvp;

import android.content.Context;
import com.sqwan.common.mvp.IView;
import com.sqwan.common.util.LogUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BasePresenter<T extends IView> implements IPresenter {
    protected Context context;
    protected T mView;

    @Override // com.sqwan.common.mvp.IPresenter
    public void initData() {
    }

    public BasePresenter(Context context, T t) {
        this.context = context;
        this.mView = t;
    }

    @Override // com.sqwan.common.mvp.IPresenter
    public void detachView() {
        if (this.mView != null) {
            LogUtil.i(this.mView.getClass().getName() + " detach from window");
        }
        this.mView = null;
    }
}
