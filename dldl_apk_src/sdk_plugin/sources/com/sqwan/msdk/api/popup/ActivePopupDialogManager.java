package com.sqwan.msdk.api.popup;

import com.sq.tool.network.SqHttpCallback;
import com.sqwan.common.dialog.pop.BasePopupDialogManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ActivePopupDialogManager extends BasePopupDialogManager {
    private static ActivePopupDialogManager instance;

    @Override // com.sqwan.common.dialog.pop.BasePopupDialogManager
    public String getDesc() {
        return "激活";
    }

    private ActivePopupDialogManager() {
    }

    public static ActivePopupDialogManager getInstance() {
        if (instance == null) {
            synchronized (ActivePopupDialogManager.class) {
                if (instance == null) {
                    instance = new ActivePopupDialogManager();
                }
            }
        }
        return instance;
    }

    @Override // com.sqwan.common.dialog.pop.BasePopupDialogManager
    public void requestPopup() {
        PopupDialogHttpUtil.requestActivePopup(new SqHttpCallback.SimpleSqHttpCallback<String>() { // from class: com.sqwan.msdk.api.popup.ActivePopupDialogManager.1
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str) {
                ActivePopupDialogManager.this.setPopupData(str);
            }
        });
    }
}
