package com.sy37sdk.account.controller;

import android.content.Context;
import com.sqwan.common.mod.account.ILoginListener;
import com.sy37sdk.account.QrCodeInfo;
import com.sy37sdk.account.alifast.AccountLoginManager;
import com.sy37sdk.account.view.IRegSuccessDialog;
import com.sy37sdk.account.view.ui360.RegSuccessDialog360;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class FastVerifyController extends AbstractLoginController {
    public FastVerifyController(Context context) {
        super(context);
    }

    @Override // com.sy37sdk.account.controller.AbstractLoginController
    public void showLoginDialog(ILoginListener iLoginListener) {
        LOG.d("展示登录弹窗");
        AccountLoginManager.getInstance(this.mContext).login(iLoginListener);
    }

    @Override // com.sy37sdk.account.controller.AbstractLoginController
    public void showRegSuccessDialog(String str, String str2, QrCodeInfo qrCodeInfo, IRegSuccessDialog.EnterGameListener enterGameListener) {
        RegSuccessDialog360 regSuccessDialog360 = new RegSuccessDialog360(this.mContext, str, str2);
        regSuccessDialog360.setEnterGameListener(enterGameListener);
        regSuccessDialog360.show();
    }
}
