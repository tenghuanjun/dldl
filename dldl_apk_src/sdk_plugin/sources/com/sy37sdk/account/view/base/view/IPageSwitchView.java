package com.sy37sdk.account.view.base.view;

import android.os.Bundle;
import com.sqwan.common.mvp.ILoadView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IPageSwitchView extends ILoadView {
    void onBackPressed();

    void onSwitched(int i, int i2, Bundle bundle);
}
