package com.sy37sdk.order.nat;

import com.sqwan.common.mvp.ILoadView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IBasePayView extends ILoadView {
    void onFailure(int i, String str);

    void paySuccess();

    void showToast(String str);
}
