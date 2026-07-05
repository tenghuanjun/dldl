package com.sy37sdk.account.view;

import com.sqwan.common.mvp.IView;
import com.sy37sdk.account.QrCodeInfo;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface IRegSuccessDialog extends IView {

    public interface EnterGameListener {
        void cancel();

        void enterGame();
    }

    void setEnterGameListener(EnterGameListener enterGameListener);

    void setQrCodeMessage(QrCodeInfo qrCodeInfo);
}
