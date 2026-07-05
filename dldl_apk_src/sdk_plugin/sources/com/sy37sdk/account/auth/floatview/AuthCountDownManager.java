package com.sy37sdk.account.auth.floatview;

import android.content.Context;
import com.sq.tool.network.SqHttpCallback;
import com.sqwan.common.mod.ModHelper;
import com.sqwan.common.mod.account.IAccountMod;
import com.sqwan.common.mod.account.IAuthResultListener;
import com.sqwan.common.mod.liveshow.LiveRadioEngine;
import com.sqwan.common.mod.liveshow.LiveshowEngine;
import com.sqwan.common.util.ActivityLifeCycleUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.task.Task;
import com.sy37sdk.account.AccountRequestManager;
import com.sy37sdk.account.auth.AuthConfigCache;
import com.sy37sdk.account.auth.AuthDialog;
import com.sy37sdk.account.auth.AuthManager;
import com.sy37sdk.account.auth.floatview.AuthCountDownView;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AuthCountDownManager {
    private static final AuthCountDownManager ourInstance = new AuthCountDownManager();
    private AuthCountDownView authCountDownView;
    private int limitedDuration;
    private Context mContext;
    private AccountRequestManager requestManager;
    public boolean isReportOnBackground = false;
    String TAG = getClass().getSimpleName();
    private int repeatDuration = 1000;
    private Task task = Task.create();
    private Task reportTask = Task.create();

    interface CountDownCallback {
        void onResult(boolean z, String str);
    }

    public static AuthCountDownManager getInstance() {
        return ourInstance;
    }

    public void init(Context context) {
        this.mContext = context;
        this.requestManager = new AccountRequestManager(context);
    }

    private AuthCountDownManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String formatTime(int i) {
        String str;
        int i2 = i / 1000;
        int i3 = i2 / 60;
        int i4 = (i3 / 60) % 60;
        int i5 = i3 % 60;
        int i6 = i2 % 60;
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (i4 == 0) {
            str = "";
        } else {
            str = i4 + "小时";
        }
        sb.append(str);
        if (i5 != 0) {
            str2 = i5 + "分钟";
        }
        sb.append(str2);
        sb.append(i6);
        sb.append("秒");
        String string = sb.toString();
        LogUtil.d(this.TAG, "formatTime " + string);
        if (i3 < 1) {
            i3 = i6 == 0 ? 0 : 1;
        }
        String str3 = i3 + "分钟";
        LogUtil.d(this.TAG, "formatTime " + str3);
        return str3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAuth(Context context) {
        AuthManager.getInstance(context).showAuthDialog(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAuthCdFinish(Context context) {
        AuthManager.getInstance(context).showAuthDialog(null);
    }

    public void startTask(final Context context, final boolean z, final int i) {
        Task.post(new Runnable() { // from class: com.sy37sdk.account.auth.floatview.AuthCountDownManager.1
            @Override // java.lang.Runnable
            public void run() {
                AuthCountDownManager.this.startTask(context, z, i, new CountDownCallback() { // from class: com.sy37sdk.account.auth.floatview.AuthCountDownManager.1.1
                    @Override // com.sy37sdk.account.auth.floatview.AuthCountDownManager.CountDownCallback
                    public void onResult(boolean z2, String str) {
                        if (AuthCountDownManager.this.authCountDownView != null) {
                            AuthCountDownManager.this.authCountDownView.changeTime(str);
                        }
                        if (z2) {
                            AuthCountDownManager.this.handleAuthCdFinish(context);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTask(final Context context, boolean z, int i, final CountDownCallback countDownCallback) {
        this.task.stop();
        if (this.authCountDownView == null) {
            AuthCountDownView authCountDownView = new AuthCountDownView(context);
            this.authCountDownView = authCountDownView;
            authCountDownView.initView();
            this.authCountDownView.authClickCallback = new AuthCountDownView.AuthClickCallback() { // from class: com.sy37sdk.account.auth.floatview.AuthCountDownManager.2
                @Override // com.sy37sdk.account.auth.floatview.AuthCountDownView.AuthClickCallback
                public void onClick() {
                    AuthCountDownManager.this.handleAuth(context);
                }
            };
            this.authCountDownView.show();
        }
        this.limitedDuration = i;
        if (!z) {
            countDownCallback.onResult(false, formatTime(i));
        } else {
            this.task.repeat(this.repeatDuration, new Task.TaskFunc() { // from class: com.sy37sdk.account.auth.floatview.AuthCountDownManager.3
                @Override // com.sqwan.common.util.task.Task.TaskFunc
                public Task.Result exec() {
                    if (!AuthCountDownManager.this.canReport()) {
                        LogUtil.e(AuthCountDownManager.this.TAG, "cd canReport false");
                        return Task.Result.Next;
                    }
                    AuthCountDownManager authCountDownManager = AuthCountDownManager.this;
                    String time = authCountDownManager.formatTime(authCountDownManager.limitedDuration);
                    if (AuthCountDownManager.this.limitedDuration <= 0) {
                        CountDownCallback countDownCallback2 = countDownCallback;
                        if (countDownCallback2 != null) {
                            countDownCallback2.onResult(true, time);
                        }
                        return Task.Result.Stop;
                    }
                    if (countDownCallback != null) {
                        AuthCountDownManager.this.limitedDuration -= AuthCountDownManager.this.repeatDuration;
                        countDownCallback.onResult(false, time);
                    }
                    return Task.Result.Next;
                }
            });
        }
    }

    public void release() {
        getInstance().stopAuthCdTask();
        getInstance().stopReportAuth();
    }

    public void stopAuthCdTask() {
        Task.post(new Runnable() { // from class: com.sy37sdk.account.auth.floatview.AuthCountDownManager.4
            @Override // java.lang.Runnable
            public void run() {
                AuthCountDownManager.this.limitedDuration = 0;
                AuthCountDownManager.this.task.stop();
                if (AuthCountDownManager.this.authCountDownView != null) {
                    AuthCountDownManager.this.authCountDownView.release();
                    AuthCountDownManager.this.authCountDownView = null;
                }
            }
        });
    }

    public void setAuthResult(final Context context, boolean z, boolean z2, final AuthDialog authDialog) {
        final IAuthResultListener authResultListener;
        final IAuthResultListener authResultListener2;
        LogUtil.d(this.TAG, "setAuthResult isSuccess " + z + " focus " + z2);
        boolean zHasSubmitRole = ((IAccountMod) ModHelper.get(IAccountMod.class)).hasSubmitRole();
        if (!z && z2 && zHasSubmitRole && (authResultListener2 = ((IAccountMod) ModHelper.get(IAccountMod.class)).getAuthResultListener()) != null) {
            Task.post(new Runnable() { // from class: com.sy37sdk.account.auth.floatview.AuthCountDownManager.5
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.e(AuthCountDownManager.this.TAG, "onAuthResult false");
                    AuthDialog authDialog2 = authDialog;
                    if (authDialog2 != null) {
                        authDialog2.setFocusAuthCdFinish(true);
                    }
                    ((IAccountMod) ModHelper.get(IAccountMod.class)).setSubmitRole(false);
                    AuthManager.getInstance(context).reset();
                    AuthCountDownManager.getInstance().stopAuthCdTask();
                    AuthCountDownManager.getInstance().stopReportAuth();
                    LiveshowEngine.getInstance().leaveLiveshowRoom(null, null);
                    LiveRadioEngine.getInstance().leaveLiveRadioRoom(null, null);
                    if (authResultListener2 != null) {
                        LogUtil.e(AuthCountDownManager.this.TAG, "onAuthResult false callback");
                        authResultListener2.onAuthResult(false);
                    }
                }
            });
        }
        if (z) {
            getInstance().stopAuthCdTask();
            if (!authDialog.isFocusAuthCdFinish() || (authResultListener = ((IAccountMod) ModHelper.get(IAccountMod.class)).getAuthResultListener()) == null) {
                return;
            }
            Task.post(new Runnable() { // from class: com.sy37sdk.account.auth.floatview.AuthCountDownManager.6
                @Override // java.lang.Runnable
                public void run() {
                    authResultListener.onAuthResult(true);
                    LogUtil.e(AuthCountDownManager.this.TAG, "onAuthResult true");
                }
            });
        }
    }

    public void showAuthLimiteTime(Context context) {
        boolean zHasSubmitRole = ((IAccountMod) ModHelper.get(IAccountMod.class)).hasSubmitRole();
        LogUtil.i(this.TAG, "showAuthLimiteTime toStart " + zHasSubmitRole);
        if (AuthConfigCache.getIsAuth()) {
            return;
        }
        int authLimitTime = AuthConfigCache.getAuthLimitTime();
        if (authLimitTime > 0) {
            getInstance().startTask(context, zHasSubmitRole, authLimitTime);
        } else {
            getInstance().stopAuthCdTask();
        }
    }

    public boolean canReport() {
        boolean z = this.isReportOnBackground;
        boolean z2 = z || (!z && ActivityLifeCycleUtils.getInstance().isForeground());
        LogUtil.i(this.TAG, "canReport:" + z2);
        return z2;
    }

    public void startReportAuth() {
        LogUtil.i(this.TAG, "startReportAuth");
        if (AuthConfigCache.isNeddReportOnline()) {
            this.reportTask.repeat(0L, AuthConfigCache.getInterval() * 60 * 1000, new Task.TaskFunc() { // from class: com.sy37sdk.account.auth.floatview.AuthCountDownManager.7
                @Override // com.sqwan.common.util.task.Task.TaskFunc
                public Task.Result exec() {
                    if (AuthCountDownManager.this.canReport()) {
                        AuthCountDownManager.this.reportAuth();
                        return null;
                    }
                    LogUtil.e(AuthCountDownManager.this.TAG, "reportAuth canReport false");
                    return null;
                }
            });
        }
    }

    public void stopReportAuth() {
        LogUtil.i(this.TAG, "stopReportAuth");
        this.reportTask.stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportAuth() {
        this.requestManager.reportOnlineDuration(new SqHttpCallback.SimpleSqHttpCallback<JSONObject>() { // from class: com.sy37sdk.account.auth.floatview.AuthCountDownManager.8
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject.optInt("need_stop") == 1) {
                    AuthCountDownManager.this.stopReportAuth();
                }
            }
        });
    }

    public void updateRemainingTime(int i) {
        if (this.task.isRunning()) {
            this.limitedDuration = i;
        }
    }
}
