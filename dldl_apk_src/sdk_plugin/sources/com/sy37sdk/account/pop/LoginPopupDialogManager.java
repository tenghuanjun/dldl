package com.sy37sdk.account.pop;

import android.app.Activity;
import com.sq.tool.logger.SQLog;
import com.sq.tool.network.SqHttpCallback;
import com.sqwan.common.dialog.pop.BasePopupDialogManager;
import com.sqwan.common.track.SqTrackBtn;
import com.sqwan.common.util.SQContextWrapper;
import com.sy37sdk.account.AccountCache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LoginPopupDialogManager extends BasePopupDialogManager {
    private static LoginPopupDialogManager instance;

    @Override // com.sqwan.common.dialog.pop.BasePopupDialogManager
    public String getDesc() {
        return SqTrackBtn.SqTrackBtnExt.login;
    }

    private LoginPopupDialogManager() {
    }

    public static LoginPopupDialogManager getInstance() {
        if (instance == null) {
            synchronized (LoginPopupDialogManager.class) {
                if (instance == null) {
                    instance = new LoginPopupDialogManager();
                }
            }
        }
        return instance;
    }

    @Override // com.sqwan.common.dialog.pop.BasePopupDialogManager
    public void requestPopup() {
        SQLog.d("请求登录后弹窗");
        Activity activity = SQContextWrapper.getActivity();
        AccountPopupDialogHttpUtil.requestLoginPopup(AccountCache.getToken(activity), AccountCache.getActionType(activity), new SqHttpCallback.SimpleSqHttpCallback<String>() { // from class: com.sy37sdk.account.pop.LoginPopupDialogManager.1
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str) {
                LoginPopupDialogManager.this.setPopupData(str);
                if (!LoginPopupDialogManager.this.needShowPopup()) {
                    SQLog.d("无登录后弹窗");
                } else {
                    SQLog.i("展示登录后弹窗");
                    LoginPopupDialogManager.this.showPopupDialog(null);
                }
            }
        });
    }
}
