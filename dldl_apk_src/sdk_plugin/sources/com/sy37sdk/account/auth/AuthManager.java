package com.sy37sdk.account.auth;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.sq.tool.logger.SQLog;
import com.sq.tool.network.SqHttpCallback;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.common.dialog.CommonAlertDialog;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import com.sqwan.common.util.AppUtils;
import com.sqwan.common.util.DensityUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.SpanUtil;
import com.sqwan.common.util.task.Task;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.account.AccountCache;
import com.sy37sdk.account.AccountRequestManager;
import com.sy37sdk.account.auth.AuthDialog;
import com.sy37sdk.account.auth.floatview.AuthCountDownManager;
import com.sy37sdk.account.face.FaceVerifyManager;
import com.sy37sdk.account.policy.PolicyManager;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AuthManager {
    private static AuthManager sInstance;
    private Context mContext;
    private AccountRequestManager requestManager;
    final String TAG = "AuthManager";
    private int currentTimeTick = 0;
    private boolean isAuthDialogShow = false;
    private boolean isReporting = false;
    private AuthDialog authDialog = null;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.sy37sdk.account.auth.AuthManager.8
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.TIME_TICK".equals(intent.getAction())) {
                if (!((IAccountMod) ModHelper.get(IAccountMod.class)).hasSubmitRole()) {
                    LogUtil.e("not submitRole");
                    return;
                }
                if (AuthCountDownManager.getInstance().canReport()) {
                    if (AuthManager.this.currentTimeTick >= AuthConfigCache.getInterval() - 1) {
                        AuthManager.this.currentTimeTick = 0;
                        LogUtil.i("android time tick currentTimeTick");
                        AuthManager.this.reportAuth();
                        return;
                    }
                    AuthManager.access$608(AuthManager.this);
                    return;
                }
                LogUtil.e("canReport false");
            }
        }
    };

    public interface AuthCallback {
        void onFailure();

        void onShowDialog();

        void onSuccess();
    }

    public static class AuthCallbackAdapter implements AuthCallback {
        @Override // com.sy37sdk.account.auth.AuthManager.AuthCallback
        public void onFailure() {
        }

        @Override // com.sy37sdk.account.auth.AuthManager.AuthCallback
        public void onShowDialog() {
        }

        @Override // com.sy37sdk.account.auth.AuthManager.AuthCallback
        public void onSuccess() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean testForceStopReportDevDuration() throws JSONException {
        return false;
    }

    static /* synthetic */ int access$608(AuthManager authManager) {
        int i = authManager.currentTimeTick;
        authManager.currentTimeTick = i + 1;
        return i;
    }

    public static AuthManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (AuthManager.class) {
                if (sInstance == null) {
                    sInstance = new AuthManager(context);
                }
            }
        }
        return sInstance;
    }

    private AuthManager(Context context) {
        this.mContext = context;
        this.requestManager = new AccountRequestManager(context);
    }

    private void hideForceAuthDialog() {
        if (this.authDialog != null) {
            Task.post(new Runnable() { // from class: com.sy37sdk.account.auth.AuthManager.1
                @Override // java.lang.Runnable
                public void run() {
                    AuthManager.this.authDialog.dismiss();
                }
            });
        }
    }

    public void requestAuthConfig(final SQResultListener sQResultListener) {
        hideForceAuthDialog();
        AuthConfigCache.clearAuthConfig();
        getInstance(this.mContext).reset();
        this.requestManager.antiIndulge(AccountCache.getActionType(this.mContext), new SqHttpCallback<String>() { // from class: com.sy37sdk.account.auth.AuthManager.2
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(String str) {
                AuthConfigCache.saveAuthConfig(str);
                if (AuthConfigCache.getTimeStamp() == 0) {
                    LogUtil.i("处理pcheck服务端请求服务超时");
                    PolicyManager.getInstance().handlePCheckGuarantee(AuthManager.this.mContext, sQResultListener);
                } else if (AuthConfigCache.authBean == null || !AuthConfigCache.authBean.isAgeLimited) {
                    AuthManager.getInstance(AuthManager.this.mContext).checkAuth(sQResultListener);
                } else {
                    AuthManager.this.check18AgeLimit(AuthConfigCache.authBean);
                }
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                LogUtil.e("请求实名信息失败！！");
                PolicyManager.getInstance().handlePCheckGuarantee(AuthManager.this.mContext, sQResultListener);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                LogUtil.e("请求实名信息失败！！");
                PolicyManager.getInstance().handlePCheckGuarantee(AuthManager.this.mContext, sQResultListener);
            }
        });
    }

    public void checkAuth(SQResultListener sQResultListener) {
        if (AuthConfigCache.getIsAuth()) {
            SQLog.d("已实名, 判断是否需要人脸识别");
            FaceVerifyManager.getInstance(this.mContext).handleFaceVerify(sQResultListener);
            PolicyManager.getInstance().handleTimeLimit(this.mContext, AuthConfigCache.getTimeStamp() * 1000, true);
            AntiManager.getInstance(this.mContext).report();
            SQLog.d("isBsAuth=" + AuthConfigCache.isBsAuth() + ", isAdult=" + AuthConfigCache.isAdult());
            if (AuthConfigCache.isBsAuth() && AuthConfigCache.isAdult()) {
                SQLog.i("版署实名过并且成年人, 存储uid");
                PolicyManager.saveAuthAdult(this.mContext);
                return;
            } else {
                SQLog.w("不满足缓存条件");
                return;
            }
        }
        if (AuthConfigCache.needAuth()) {
            showPersonalDialog(AuthConfigCache.getAuthUrl(), AuthConfigCache.isAuthFocus(), AuthConfigCache.needAccumulateDuration(), false, null, sQResultListener);
        } else if (sQResultListener != null) {
            sQResultListener.onSuccess(new Bundle());
        }
    }

    public void showAuthDialog(AuthCallback authCallback) {
        showPersonalDialog(AuthConfigCache.getAuthUrl(), AuthConfigCache.isAuthFocus(), AuthConfigCache.needAccumulateDuration(), false, authCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startReport() {
        if (this.isReporting) {
            return;
        }
        LogUtil.i("AuthManager", "启动实名认证上报");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        this.mContext.registerReceiver(this.broadcastReceiver, intentFilter);
        this.isReporting = true;
    }

    public void stopReport() {
        LogUtil.i("AuthManager", "停止实名认证上报");
        if (this.isReporting) {
            this.mContext.unregisterReceiver(this.broadcastReceiver);
            this.isReporting = false;
        }
    }

    public void showPersonalDialog(String str, boolean z, final boolean z2, boolean z3, final AuthCallback authCallback, SQResultListener sQResultListener) {
        showAuthDialog(str, z, z3, new AuthCallback() { // from class: com.sy37sdk.account.auth.AuthManager.3
            @Override // com.sy37sdk.account.auth.AuthManager.AuthCallback
            public void onSuccess() {
                LogUtil.i("AuthManager", "auth success");
                AuthCallback authCallback2 = authCallback;
                if (authCallback2 != null) {
                    authCallback2.onSuccess();
                }
            }

            @Override // com.sy37sdk.account.auth.AuthManager.AuthCallback
            public void onFailure() {
                AuthCallback authCallback2 = authCallback;
                if (authCallback2 != null) {
                    authCallback2.onFailure();
                }
                if (z2) {
                    AuthManager.this.startReport();
                }
            }

            @Override // com.sy37sdk.account.auth.AuthManager.AuthCallback
            public void onShowDialog() {
                AuthCallback authCallback2 = authCallback;
                if (authCallback2 != null) {
                    authCallback2.onShowDialog();
                }
            }
        }, sQResultListener);
    }

    public void showPersonalDialog(String str, boolean z, final boolean z2, boolean z3, final AuthCallback authCallback) {
        showAuthDialog(str, z, z3, new AuthCallback() { // from class: com.sy37sdk.account.auth.AuthManager.4
            @Override // com.sy37sdk.account.auth.AuthManager.AuthCallback
            public void onSuccess() {
                LogUtil.i("AuthManager", "auth success");
                AuthCallback authCallback2 = authCallback;
                if (authCallback2 != null) {
                    authCallback2.onSuccess();
                }
            }

            @Override // com.sy37sdk.account.auth.AuthManager.AuthCallback
            public void onFailure() {
                AuthCallback authCallback2 = authCallback;
                if (authCallback2 != null) {
                    authCallback2.onFailure();
                }
                if (z2) {
                    AuthManager.this.startReport();
                }
            }

            @Override // com.sy37sdk.account.auth.AuthManager.AuthCallback
            public void onShowDialog() {
                AuthCallback authCallback2 = authCallback;
                if (authCallback2 != null) {
                    authCallback2.onShowDialog();
                }
            }
        });
    }

    public void showAuthDialog(final String str, final boolean z, final boolean z2, final AuthCallback authCallback, final SQResultListener sQResultListener) {
        Task.post(new Runnable() { // from class: com.sy37sdk.account.auth.AuthManager.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.i("AuthManager showAuthDialog ,focus:" + z + " isAuthDialogShow " + AuthManager.this.isAuthDialogShow);
                if (!AuthManager.this.isAuthDialogShow || (z2 && z)) {
                    if (AuthManager.this.authDialog == null) {
                        AuthManager.this.authDialog = new AuthDialog(AuthManager.this.mContext);
                    }
                    AuthManager.this.authDialog.setFocus(z);
                    AuthManager.this.authDialog.setUrl(AppUtils.constructWebUrlParam(AuthManager.this.mContext, str));
                    AuthManager.this.authDialog.setCloseListener(new AuthDialog.CloseListener() { // from class: com.sy37sdk.account.auth.AuthManager.5.1
                        @Override // com.sy37sdk.account.auth.AuthDialog.CloseListener
                        public void onClose(String str2, String str3) {
                            AuthManager.this.isAuthDialogShow = false;
                            if (str2.equals("exitGame")) {
                                LogUtil.i("退出游戏");
                                ((Activity) AuthManager.this.mContext).finish();
                                System.exit(0);
                                return;
                            }
                            boolean zEquals = str2.equals("0");
                            if (zEquals) {
                                AuthCountDownManager.getInstance().setAuthResult(AuthManager.this.mContext, true, z, AuthManager.this.authDialog);
                            }
                            if (authCallback != null) {
                                if (zEquals) {
                                    HashMap map = new HashMap();
                                    map.put(SqTrackKey.certification_url, str);
                                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.certification_succ, map);
                                    AuthConfigCache.setIsAuth(true);
                                    AuthManager.this.stopReport();
                                    authCallback.onSuccess();
                                    AuthManager.this.requestAuthConfig(sQResultListener);
                                    return;
                                }
                                authCallback.onFailure();
                            }
                        }
                    });
                    AuthManager.this.authDialog.show();
                    AuthManager.this.isAuthDialogShow = true;
                    AuthCountDownManager.getInstance().setAuthResult(AuthManager.this.mContext, false, z, AuthManager.this.authDialog);
                    HashMap map = new HashMap();
                    map.put(SqTrackKey.certification_url, str);
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.certification, map);
                }
            }
        });
    }

    public void showAuthDialog(final String str, final boolean z, final boolean z2, final AuthCallback authCallback) {
        Task.post(new Runnable() { // from class: com.sy37sdk.account.auth.AuthManager.6
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.i("AuthManager showAuthDialog ,focus:" + z + " isAuthDialogShow " + AuthManager.this.isAuthDialogShow);
                if (!AuthManager.this.isAuthDialogShow || (z2 && z)) {
                    if (AuthManager.this.authDialog == null) {
                        AuthManager.this.authDialog = new AuthDialog(AuthManager.this.mContext);
                    }
                    AuthManager.this.authDialog.setFocus(z);
                    AuthManager.this.authDialog.setUrl(AppUtils.constructWebUrlParam(AuthManager.this.mContext, str));
                    AuthManager.this.authDialog.setCloseListener(new AuthDialog.CloseListener() { // from class: com.sy37sdk.account.auth.AuthManager.6.1
                        @Override // com.sy37sdk.account.auth.AuthDialog.CloseListener
                        public void onClose(String str2, String str3) {
                            AuthManager.this.isAuthDialogShow = false;
                            if (str2.equals("exitGame")) {
                                LogUtil.i("退出游戏");
                                ((Activity) AuthManager.this.mContext).finish();
                                System.exit(0);
                                return;
                            }
                            boolean zEquals = str2.equals("0");
                            if (zEquals) {
                                AuthCountDownManager.getInstance().setAuthResult(AuthManager.this.mContext, true, z, AuthManager.this.authDialog);
                            }
                            if (authCallback != null) {
                                if (zEquals) {
                                    HashMap map = new HashMap();
                                    map.put(SqTrackKey.certification_url, str);
                                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.certification_succ, map);
                                    AuthConfigCache.setIsAuth(true);
                                    AuthManager.this.stopReport();
                                    authCallback.onSuccess();
                                    AuthManager.this.requestAuthConfig(null);
                                    return;
                                }
                                authCallback.onFailure();
                            }
                        }
                    });
                    AuthManager.this.authDialog.show();
                    AuthManager.this.isAuthDialogShow = true;
                    AuthCountDownManager.getInstance().setAuthResult(AuthManager.this.mContext, false, z, AuthManager.this.authDialog);
                    HashMap map = new HashMap();
                    map.put(SqTrackKey.certification_url, str);
                    SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.certification, map);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportAuth() {
        this.requestManager.reportDevDuration(new SqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.auth.AuthManager.7
            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                LogUtil.e("AuthManager", "上报请求失败 msg:" + str);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                if (AuthManager.this.isReporting) {
                    LogUtil.i("AuthManager", "上报一小时实名认证：" + jSONObject);
                    try {
                        if (AuthManager.this.testForceStopReportDevDuration()) {
                            return;
                        }
                        PopConfig fromJson = PopConfig.parseFromJson(jSONObject);
                        AuthConfigCache.autoCalAuthLimitTime();
                        if (fromJson.getNeedStop()) {
                            AuthManager.this.stopReport();
                        }
                        if (fromJson.getRemainingTime() == 0) {
                            AuthCountDownManager.getInstance().stopReportAuth();
                        }
                        if (TextUtils.isEmpty(fromJson.getUrl()) || !fromJson.isShow()) {
                            return;
                        }
                        AuthManager.this.showPersonalDialog(fromJson.getUrl(), fromJson.isFocus(), false, true, null);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                LogUtil.e("AuthManager", "上报一小时实名认证 error:" + str);
            }
        });
    }

    public void reset() {
        stopReport();
        this.currentTimeTick = 0;
        this.isAuthDialogShow = false;
    }

    public void check18AgeLimit(final AuthBean authBean) {
        if (authBean == null || !authBean.isAgeLimited) {
            return;
        }
        Task.post(new Runnable() { // from class: com.sy37sdk.account.auth.AuthManager.9
            @Override // java.lang.Runnable
            public void run() {
                AntiManager.getInstance(AuthManager.this.mContext).stopReport();
                AuthCountDownManager.getInstance().stopReportAuth();
                new CommonAlertDialog.Builder(AuthManager.this.mContext).setTitle("温馨提示").setMessage(authBean.ageLimitedMsg).setPositiveButton(SpanUtil.getFontString("确认", DensityUtil.dip2px(AuthManager.this.mContext, 18.0f), Color.parseColor("#FA9D05"), false), new View.OnClickListener() { // from class: com.sy37sdk.account.auth.AuthManager.9.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ((IAccountMod) ModHelper.get(IAccountMod.class)).backToGameLogin();
                    }
                }).setCancelable(false).showEx();
            }
        });
    }
}
