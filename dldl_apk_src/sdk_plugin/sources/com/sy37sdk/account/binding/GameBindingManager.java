package com.sy37sdk.account.binding;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.sqgson.Gson;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.request.CommonParamsV3;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.ActivityLifecycleAdapter;
import com.sqwan.common.util.DeviceUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.UrlConstant;
import com.sy37sdk.account.binding.view.GameBindingDialog;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class GameBindingManager {
    public static final String BINDING_PARAMS = "bindingParams";
    private static final String TAG = "GameBindingManager";
    private static volatile GameBindingManager sInstance;
    private Map<String, String> mApiMap;
    private MMOBindingCallback mCallback;
    private Context mContext;
    private GameBindingDialog mGameBindingDialog;
    private WeakReference<Activity> mWeakActivity;
    private String mBindingParams = "";
    private String mCallingPackageName = "";
    private String mDialogContent = "";
    private volatile boolean mIsLogin = false;
    private ActivityLifecycleAdapter mAppStartCallback = new ActivityLifecycleAdapter() { // from class: com.sy37sdk.account.binding.GameBindingManager.1
        @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            Intent intent;
            super.onActivityCreated(activity, bundle);
            try {
                LogUtil.d("MMOBindingUtils onActivityCreated ");
                String string = bundle != null ? bundle.getString(GameBindingManager.BINDING_PARAMS) : "";
                if (TextUtils.isEmpty(string) && (intent = activity.getIntent()) != null) {
                    string = intent.getStringExtra(GameBindingManager.BINDING_PARAMS);
                }
                GameBindingManager.this.handleBinding(activity, string);
            } catch (Exception e) {
                LogUtil.d(GameBindingManager.TAG, "onActivityCreated error = " + e.getMessage());
            }
        }
    };

    public static class GameBindingErrorType {
        public static final int TYPE_ACTIVITY_LIFECYCLE_ERROR = 1002;
        public static final int TYPE_ACTIVITY_NULL_ERROR = 1001;
        public static final int TYPE_JUMP_ERROR = 1004;
        public static final int TYPE_NETWORK_ERROR = 1003;
    }

    public enum GameBindingLoginType {
        TYPE_LOGIN,
        TYPE_CHANGE_ACCOUNT,
        TYPE_SWITCH
    }

    interface MMOBindingCallback {
        void executeCallback();
    }

    private GameBindingManager() {
    }

    public static GameBindingManager getInstance() {
        if (sInstance == null) {
            synchronized (GameBindingManager.class) {
                if (sInstance == null) {
                    sInstance = new GameBindingManager();
                }
            }
        }
        return sInstance;
    }

    public void init(Object obj) {
        if (obj != null) {
            if (obj instanceof Application) {
                Application application = (Application) obj;
                this.mContext = application.getApplicationContext();
                LogUtil.e(TAG, "registerActivityLifecycleCallbacks mAppStartCallback");
                application.registerActivityLifecycleCallbacks(this.mAppStartCallback);
                return;
            }
            LogUtil.e(TAG, "registerActivityLifecycleCallbacks mAppStartCallback error");
        }
    }

    public void handlerNewIntentEvent(Activity activity, Intent intent) {
        if (activity != null && intent != null) {
            handleBinding(activity, intent.getStringExtra(BINDING_PARAMS));
        } else {
            LogUtil.e(TAG, "activity or intent is null");
        }
    }

    public SQResultListener getBindingLoginResultListener(GameBindingLoginType gameBindingLoginType, final SQResultListener sQResultListener) {
        if (gameBindingLoginType == GameBindingLoginType.TYPE_CHANGE_ACCOUNT || gameBindingLoginType == GameBindingLoginType.TYPE_LOGIN) {
            this.mIsLogin = false;
        }
        return new SQResultListener() { // from class: com.sy37sdk.account.binding.GameBindingManager.2
            public void onSuccess(Bundle bundle) {
                GameBindingManager.this.mIsLogin = true;
                LogUtil.d(GameBindingManager.TAG, "getBindingLoginResultListener onSuccess");
                SQResultListener sQResultListener2 = sQResultListener;
                if (sQResultListener2 != null) {
                    sQResultListener2.onSuccess(bundle);
                }
                GameBindingManager.this.executeCallback();
            }

            public void onFailture(int i, String str) {
                LogUtil.d(GameBindingManager.TAG, "getBindingLoginResultListener onFailture code = " + i + " message = " + str);
                SQResultListener sQResultListener2 = sQResultListener;
                if (sQResultListener2 != null) {
                    sQResultListener2.onFailture(i, str);
                }
            }
        };
    }

    public void handleBinding(Activity activity, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                LogUtil.d(TAG, "bindingParams is null");
                return;
            }
            this.mWeakActivity = new WeakReference<>(activity);
            this.mBindingParams = str;
            LogUtil.d(TAG, "handleBinding bindingParams = " + str);
            parseBindingParams(this.mBindingParams);
            LogUtil.d(TAG, "获取到的调用包名: " + this.mCallingPackageName);
            if (this.mIsLogin) {
                showBindingDialog();
            } else {
                this.mCallback = new MMOBindingCallback() { // from class: com.sy37sdk.account.binding.GameBindingManager.3
                    @Override // com.sy37sdk.account.binding.GameBindingManager.MMOBindingCallback
                    public void executeCallback() {
                        LogUtil.d(GameBindingManager.TAG, "handlerBinding bindingParams = " + GameBindingManager.this.mBindingParams + " callingPackageName = " + GameBindingManager.this.mCallingPackageName);
                        GameBindingManager.this.showBindingDialog();
                    }
                };
            }
        } catch (Exception e) {
            LogUtil.d(TAG, "handlerBinding bindingParams error = " + e.getMessage());
        }
    }

    private void parseBindingParams(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("extParams");
            if (jSONObjectOptJSONObject != null) {
                this.mDialogContent = jSONObjectOptJSONObject.optString("dialogContent");
                this.mCallingPackageName = jSONObjectOptJSONObject.optString("callingPackageName");
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("apiParams");
            if (jSONObjectOptJSONObject2 != null) {
                this.mApiMap = (Map) new Gson().fromJson(jSONObjectOptJSONObject2.toString(), Map.class);
            }
        } catch (Exception e) {
            LogUtil.d(TAG, "parseBindingParams error = " + e.getMessage());
        }
    }

    public void executeCallback() {
        MMOBindingCallback mMOBindingCallback = this.mCallback;
        if (mMOBindingCallback != null) {
            mMOBindingCallback.executeCallback();
        }
    }

    public void setIsLogin(boolean z) {
        this.mIsLogin = z;
    }

    public void clearData() {
        this.mBindingParams = "";
        this.mCallingPackageName = "";
        this.mDialogContent = "";
        this.mCallback = null;
        this.mGameBindingDialog = null;
        this.mApiMap = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBindingDialog() {
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.SDK_GAME_BINDING_INVOKE);
        WeakReference<Activity> weakReference = this.mWeakActivity;
        if (weakReference == null || weakReference.get() == null) {
            trackBindingFail(1001, "activity is null");
            return;
        }
        Activity activity = this.mWeakActivity.get();
        if (activity.isFinishing() || activity.isDestroyed()) {
            trackBindingFail(1002, "activity lifecycle error");
            return;
        }
        GameBindingDialog gameBindingDialog = this.mGameBindingDialog;
        if (gameBindingDialog != null) {
            try {
                gameBindingDialog.dismiss();
            } catch (Exception e) {
                LogUtil.e(TAG, "dismiss dialog error = " + e.getMessage());
            }
        }
        final GameBindingDialog gameBindingDialog2 = new GameBindingDialog(activity, this.mDialogContent);
        gameBindingDialog2.setGameBindingDialogCallback(new GameBindingDialog.GameBindingDialogCallback() { // from class: com.sy37sdk.account.binding.GameBindingManager.4
            @Override // com.sy37sdk.account.binding.view.GameBindingDialog.GameBindingDialogCallback
            public void onConfirm() {
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.CONFIRM_DLYZAPP_LOGIN_AUTHORIZE);
                GameBindingManager.this.requestUserRelate(new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.binding.GameBindingManager.4.1
                    @Override // com.sq.tool.network.SqHttpCallback
                    public void onSuccess(JSONObject jSONObject) {
                        LogUtil.d(GameBindingManager.TAG, "requestUserRelate onSuccess data = " + jSONObject);
                        GameBindingManager.this.jumpToDlyzApp(GameBindingManager.this.mContext, GameBindingManager.this.mCallingPackageName, jSONObject);
                        GameBindingManager.this.clearData();
                        gameBindingDialog2.dismiss();
                    }

                    @Override // com.sq.tool.network.SqHttpCallback
                    public void onFailure(int i, String str, VolleyError volleyError) {
                        LogUtil.d(GameBindingManager.TAG, "requestUserRelate onFailure code = " + i + " errorMsg = " + str);
                        GameBindingManager.this.trackBindingFail(1003, "code = " + i + ", msg = " + str);
                        ToastUtil.showToast("网络异常，请重新确认");
                    }

                    @Override // com.sdk.sq.net.SqRequestCallback
                    public void onResponseStateError(int i, int i2, String str, String str2) {
                        LogUtil.d(GameBindingManager.TAG, "requestUserRelate onResponseStateError state = " + i2 + " msg = " + str);
                        GameBindingManager.this.trackBindingFail(1003, "code = " + i + ", msg = " + str);
                        ToastUtil.showToast("网络异常，请重新确认");
                    }
                });
            }

            @Override // com.sy37sdk.account.binding.view.GameBindingDialog.GameBindingDialogCallback
            public void onCancel() {
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.SDK_GAME_BINDING_CANCEL);
                SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.CANCEL_DLYZAPP_LOGIN_AUTHORIZE);
                GameBindingManager.this.clearData();
            }
        });
        this.mGameBindingDialog = gameBindingDialog2;
        gameBindingDialog2.show();
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.DLYZAPP_LOGIN_PUSH_SHOW);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpToDlyzApp(Context context, String str, JSONObject jSONObject) {
        try {
            LogUtil.d(TAG, "jumpToDlyzApp 开始执行跳转逻辑");
            if (context != null && !TextUtils.isEmpty(str)) {
                LogUtil.d(TAG, "context 不为空，开始获取启动Intent");
                Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
                if (launchIntentForPackage != null) {
                    LogUtil.d(TAG, "成功获取到启动Intent，准备跳转到: " + str);
                    launchIntentForPackage.addFlags(268435456);
                    launchIntentForPackage.putExtra("bindingStatus", "1");
                    launchIntentForPackage.putExtra("bindingRespData", jSONObject == null ? "" : jSONObject.toString());
                    context.startActivity(launchIntentForPackage);
                    LogUtil.d(TAG, "跳转命令已执行");
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.SDK_GAME_BINDING_SUCCESS);
                    return;
                }
                String str2 = "未找到包名对应的启动Intent: " + str + "，可能应用未安装";
                LogUtil.d(TAG, str2);
                trackBindingFail(1004, str2);
                return;
            }
            LogUtil.e(TAG, "context = null 或 packageName = null，无法跳转");
            trackBindingFail(1004, "context = null 或 packageName = null，无法跳转");
        } catch (Exception e) {
            LogUtil.e(TAG, "jumpToDlyzApp 跳转失败: " + e.getMessage());
            e.printStackTrace();
            trackBindingFail(1004, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestUserRelate(SqHttpCallback<JSONObject> sqHttpCallback) {
        HashMap map = new HashMap();
        try {
            String oaid = DeviceUtils.getOaid(this.mContext);
            String strOptString = TextUtils.isEmpty(oaid) ? "" : new JSONObject(oaid).optString("oaid");
            String token = AccountCache.getToken(this.mContext);
            if (this.mApiMap != null) {
                map.putAll(this.mApiMap);
            }
            map.put("oaid", strOptString);
            map.put("token", token);
            LogUtil.d(TAG, "oaid = " + strOptString + ", token = " + token);
        } catch (Exception e) {
            LogUtil.e(TAG, "requestUserRelate error = " + e.getMessage());
        }
        SqRequest.of(UrlConstant.URL_USER_RELATE).formParams(map).addParamsTransformer(new CommonParamsV3()).signV3().post(sqHttpCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackBindingFail(int i, String str) {
        HashMap map = new HashMap();
        map.put(SqTrackKey.fail_code, String.valueOf(i));
        map.put(SqTrackKey.reason_fail, str);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.SDK_GAME_BINDING_FAIL, map);
    }

    public boolean isGameBinding() {
        return this.mCallback != null;
    }
}
