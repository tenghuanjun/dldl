package com.sqwan.msdk.api.popup;

import android.content.Context;
import com.sq.tool.network.SqHttpCallback;
import com.sqwan.common.dialog.pop.BasePopupDialogManager;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SubmitRolePopupDialogManager extends BasePopupDialogManager {
    private static SubmitRolePopupDialogManager instance;
    private HashMap<String, String> infos;

    @Override // com.sqwan.common.dialog.pop.BasePopupDialogManager
    public String getDesc() {
        return "进服";
    }

    private SubmitRolePopupDialogManager() {
    }

    public static SubmitRolePopupDialogManager getInstance() {
        if (instance == null) {
            synchronized (SubmitRolePopupDialogManager.class) {
                if (instance == null) {
                    instance = new SubmitRolePopupDialogManager();
                }
            }
        }
        return instance;
    }

    public void handlePopup(Context context, HashMap<String, String> map) {
        this.infos = map;
        super.handlePopup(context);
    }

    @Override // com.sqwan.common.dialog.pop.BasePopupDialogManager
    public void requestPopup() {
        PopupDialogHttpUtil.requestSubmitRolePopup(this.infos, new SqHttpCallback.SimpleSqHttpCallback<String>() { // from class: com.sqwan.msdk.api.popup.SubmitRolePopupDialogManager.1
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str) {
                SubmitRolePopupDialogManager.this.setPopupData(str);
                SubmitRolePopupDialogManager.this.showPopupDialog(null);
            }
        });
    }
}
