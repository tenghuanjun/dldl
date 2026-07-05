package com.sy37sdk.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.SparseArray;
import com.sqwan.common.util.LogUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class SettingHelper {
    public static final int SETTING_REQUEST_CODE_FLOAT = 2313;
    private static SettingHelper mInstance;
    private Activity mContext;
    private SparseArray<SettingCallback> mSettingCallbacks = new SparseArray<>();

    public interface SettingCallback {
        void onSettingResult(int i, Intent intent);
    }

    private SettingHelper(Activity activity) {
        this.mContext = activity;
    }

    public static synchronized SettingHelper getInstance(Activity activity) {
        if (mInstance == null) {
            mInstance = new SettingHelper(activity);
        }
        return mInstance;
    }

    public void requestFloatWindowSetting(SettingCallback settingCallback) {
        this.mSettingCallbacks.put(SETTING_REQUEST_CODE_FLOAT, settingCallback);
        Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION");
        intent.setData(Uri.parse("package:" + this.mContext.getPackageName()));
        this.mContext.startActivityForResult(intent, SETTING_REQUEST_CODE_FLOAT);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.d("onActivityResult: requestCode = " + i + ", resultCode = " + i2);
        SettingCallback settingCallback = this.mSettingCallbacks.get(i);
        if (settingCallback != null) {
            settingCallback.onSettingResult(i2, intent);
        }
    }
}
