package com.sy37sdk.account.age;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.dialog.BaseNormalDialog;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SQContextWrapper;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.account.AccountRequestManager;
import com.sy37sdk.account.uagree.UAgreeManager;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AppropriateAgeManager {
    private static volatile AppropriateAgeManager instance;
    private AccountRequestManager requestManager = new AccountRequestManager(SQContextWrapper.getApplicationContext());

    private AppropriateAgeManager() {
    }

    public static AppropriateAgeManager getInstance() {
        if (instance == null) {
            synchronized (UAgreeManager.class) {
                if (instance == null) {
                    instance = new AppropriateAgeManager();
                }
            }
        }
        return instance;
    }

    public void refreshConfig(final SQResultListener sQResultListener) {
        this.requestManager.appropriateAgeProtocol(new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.age.AppropriateAgeManager.1
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                LogUtil.e("refresh appropriate age protocol config fail state: " + i2 + "message: " + str);
                SQResultListener sQResultListener2 = sQResultListener;
                if (sQResultListener2 != null) {
                    AppropriateAgeManager.this.callbackAppropriateIcon(sQResultListener2);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                AppropriateAgeManager.this.initConfig(jSONObject);
                SQResultListener sQResultListener2 = sQResultListener;
                if (sQResultListener2 != null) {
                    AppropriateAgeManager.this.callbackAppropriateIcon(sQResultListener2);
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                LogUtil.e("refresh appropriate age protocol config error code: " + i + "message: " + str);
                SQResultListener sQResultListener2 = sQResultListener;
                if (sQResultListener2 != null) {
                    AppropriateAgeManager.this.callbackAppropriateIcon(sQResultListener2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackAppropriateIcon(SQResultListener sQResultListener) {
        AppropriateAge appropriateAge = AppropriateAgeCacheHelper.getAppropriateAge(SQContextWrapper.getApplicationContext());
        String appropriateIconUrl = (appropriateAge == null || !appropriateAge.isStatus() || appropriateAge.getTiming() == null || !appropriateAge.getTiming().contains("2")) ? "" : getInstance().getAppropriateIconUrl();
        Bundle bundle = new Bundle();
        bundle.putString("age_appropriate_icon", appropriateIconUrl);
        LogUtil.i("通过后端接口回调适龄提醒url：" + appropriateIconUrl);
        sQResultListener.onSuccess(bundle);
    }

    public void refreshConfig() {
        refreshConfig(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initConfig(JSONObject jSONObject) {
        LogUtil.i("int appropriate age config:" + jSONObject);
        try {
            AppropriateAgeCacheHelper.saveAppropriateAge(SQContextWrapper.getApplicationContext(), AppropriateAge.parse(jSONObject.optString("age_appropriate_config")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAppropriateAgeDialog(Context context) {
        AppropriateAge appropriateAge = AppropriateAgeCacheHelper.getAppropriateAge(SQContextWrapper.getApplicationContext());
        if (appropriateAge != null && !TextUtils.isEmpty(appropriateAge.getDesc())) {
            BaseNormalDialog baseNormalDialog = new BaseNormalDialog(context);
            baseNormalDialog.setUrl(appropriateAge.getDesc());
            baseNormalDialog.show();
        }
        SqTrackActionManager2.getInstance().trackActionCPTest(SqTrackAction2.SHOW_AGE_APPROPRIATE);
    }

    public String getAppropriateIconUrl() {
        AppropriateAge appropriateAge = AppropriateAgeCacheHelper.getAppropriateAge(SQContextWrapper.getApplicationContext());
        return (appropriateAge == null || TextUtils.isEmpty(appropriateAge.getIcon())) ? "" : appropriateAge.getIcon();
    }
}
