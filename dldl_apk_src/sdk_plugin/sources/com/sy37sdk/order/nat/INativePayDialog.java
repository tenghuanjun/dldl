package com.sy37sdk.order.nat;

import android.os.Bundle;
import com.sqwan.common.mvp.ILoadView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface INativePayDialog extends ILoadView {
    void closeAccountDialog(boolean z);

    void onSwitch(int i, Bundle bundle);
}
