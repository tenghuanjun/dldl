package com.sqwan.msdk.api.popup;

import android.content.Context;
import android.text.TextUtils;
import com.sq.tool.network.SqHttpCallback;
import com.sqwan.common.dialog.pop.BasePopupDialogManager;
import com.sqwan.common.dialog.pop.PopupDialogBean;
import com.sqwan.common.util.ActivityLifeCycleUtils;
import com.sqwan.common.util.LogUtil;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class BeforePayPopupDialogManager extends BasePopupDialogManager {
    private static BeforePayPopupDialogManager instance;
    private String dmoney;
    private String doid;
    private String dpt;
    private String drlevel;
    private String moid;

    @Override // com.sqwan.common.dialog.pop.BasePopupDialogManager
    public String getDesc() {
        return "支付前弹窗";
    }

    public void handlePopup(Context context, String str, String str2, String str3, String str4, String str5) {
        this.doid = str;
        this.moid = str2;
        this.dmoney = str3;
        this.drlevel = str4;
        this.dpt = str5;
        super.handlePopup(context);
    }

    public static BeforePayPopupDialogManager getInstance() {
        if (instance == null) {
            instance = new BeforePayPopupDialogManager();
        }
        return instance;
    }

    @Override // com.sqwan.common.dialog.pop.BasePopupDialogManager
    public void requestPopup() {
        PopupDialogHttpUtil.requestOrderPopups(this.moid, this.doid, this.dmoney, this.drlevel, this.dpt, new SqHttpCallback.SimpleSqHttpCallback<String>() { // from class: com.sqwan.msdk.api.popup.BeforePayPopupDialogManager.1
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str) {
                LogUtil.w("requestOrderPopups onSuccess " + str);
                BeforePayPopupDialogManager.this.setPopupData(str);
                BeforePayPopupDialogManager.this.showPopupDialog(ActivityLifeCycleUtils.getInstance().getResumedActivity(), null);
            }
        });
    }

    @Override // com.sqwan.common.dialog.pop.BasePopupDialogManager
    public void setPopupData(String str) {
        try {
            if (this.popupDialogBeans == null) {
                this.popupDialogBeans = new ArrayList();
            }
            this.popupDialogBeans.clear();
            PopupDialogBean popupDialogBeanDecodeFromJson = PopupDialogBean.decodeFromJson(new JSONObject(str));
            if (TextUtils.isEmpty(popupDialogBeanDecodeFromJson.getUrl())) {
                return;
            }
            this.popupDialogBeans.add(popupDialogBeanDecodeFromJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
