package com.sqwan.msdk.api.popup;

import android.content.Context;
import com.sq.tool.network.SqHttpCallback;
import com.sqwan.common.dialog.pop.BasePopupDialogManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PayPopupDialogManager extends BasePopupDialogManager {
    private static PayPopupDialogManager instance;
    private String dmoney;
    private String doid;
    private String moid;
    private String operateType;

    @Override // com.sqwan.common.dialog.pop.BasePopupDialogManager
    public String getDesc() {
        return "支付后";
    }

    private PayPopupDialogManager() {
    }

    public static PayPopupDialogManager getInstance() {
        if (instance == null) {
            synchronized (PayPopupDialogManager.class) {
                if (instance == null) {
                    instance = new PayPopupDialogManager();
                }
            }
        }
        return instance;
    }

    public void handlePopup(Context context, String str, String str2, String str3, String str4) {
        this.doid = str;
        this.moid = str2;
        this.dmoney = str3;
        this.operateType = str4;
        super.handlePopup(context);
    }

    @Override // com.sqwan.common.dialog.pop.BasePopupDialogManager
    public void requestPopup() {
        PopupDialogHttpUtil.requestPayPopup(this.moid, this.doid, this.dmoney, this.operateType, new SqHttpCallback.SimpleSqHttpCallback<String>() { // from class: com.sqwan.msdk.api.popup.PayPopupDialogManager.1
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str) {
                PayPopupDialogManager.this.setPopupData(str);
                PayPopupDialogManager.this.showPopupDialog(null);
            }
        });
    }
}
