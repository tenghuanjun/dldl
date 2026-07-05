package com.duowan.live.common.framework;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.duowan.auk.ui.ArkActivity;
import com.duowan.auk.util.L;
import com.duowan.live.common.widget.LiveAlert;
import com.huya.live.common.api.BaseApi;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class BaseActivity extends ArkActivity {
    private static final String BASE_CLASS_NAME = BaseActivity.class.getName();
    protected static final int SIGNAL_REGISTER_ON_ONCREATE = 1;
    protected static final int SIGNAL_REGISTER_ON_ONRESUME = 0;
    protected static final int SIGNAL_REGISTER_ON_START = 2;
    protected LiveAlert mLiveAlert;
    protected Handler mHandler = new Handler();
    protected boolean mIsResume = false;
    private Map<Integer, IActivityResultCallback> activityResultCallbackMap = new HashMap();
    private Map<IActivityResultCallback, Object> activityResultCallbackObjectHashMap = new HashMap();
    protected int mSignalRegisterLifeCycle = 0;

    public interface IActivityResultCallback {
        void onActivityResult(Object obj, int i, int i2, Intent intent);
    }

    @Override // com.duowan.auk.ui.ArkActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mSignalRegisterLifeCycle == 1) {
            BaseApi.getSignalCenterApi().register(this);
        }
    }

    @Override // com.duowan.auk.ui.ArkActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mIsResume = true;
        if (this.mSignalRegisterLifeCycle == 0) {
            BaseApi.getSignalCenterApi().register(this);
        }
    }

    @Override // com.duowan.auk.ui.ArkActivity, android.app.Activity
    protected void onPause() {
        if (this.mSignalRegisterLifeCycle == 0) {
            BaseApi.getSignalCenterApi().unregister(this);
        }
        super.onPause();
        this.mIsResume = false;
    }

    @Override // com.duowan.auk.ui.ArkActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.mSignalRegisterLifeCycle == 2) {
            BaseApi.getSignalCenterApi().register(this);
        }
    }

    @Override // com.duowan.auk.ui.ArkActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.mSignalRegisterLifeCycle == 2) {
            BaseApi.getSignalCenterApi().unregister(this);
        }
    }

    @Override // com.duowan.auk.ui.ArkActivity, android.app.Activity
    public void onDestroy() {
        this.mHandler.removeCallbacksAndMessages(null);
        LiveAlert liveAlert = this.mLiveAlert;
        if (liveAlert != null) {
            liveAlert.dismiss();
            this.mLiveAlert = null;
        }
        if (this.mSignalRegisterLifeCycle == 1) {
            BaseApi.getSignalCenterApi().unregister(this);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        L.info(this, "lifecycle | BaseActivity | onSaveInstanceState");
    }

    protected void setSignalRegisterLifeCycle(int i) {
        this.mSignalRegisterLifeCycle = i;
    }

    public void startActivityForResult(IActivityResultCallback iActivityResultCallback, Object obj, Intent intent, int i, Bundle bundle) {
        this.activityResultCallbackMap.put(Integer.valueOf(i), iActivityResultCallback);
        this.activityResultCallbackObjectHashMap.put(iActivityResultCallback, obj);
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        IActivityResultCallback iActivityResultCallback;
        if (this.activityResultCallbackMap.containsKey(Integer.valueOf(i)) && (iActivityResultCallback = this.activityResultCallbackMap.get(Integer.valueOf(i))) != null) {
            iActivityResultCallback.onActivityResult(this.activityResultCallbackObjectHashMap.get(iActivityResultCallback), i, i2, intent);
            this.activityResultCallbackMap.remove(Integer.valueOf(i));
        } else {
            super.onActivityResult(i, i2, intent);
        }
    }
}
