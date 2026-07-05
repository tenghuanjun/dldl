package com.sqwan.liveshow;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.sqwan.base.BaseEnginHandler;
import com.sqwan.common.mod.CommonConfigs;
import com.sqwan.common.mod.liveshow.BaseBean;
import com.sqwan.common.util.ActivityLifeCycleUtils;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.util.ToastUtil;
import com.sqwan.common.util.task.Task;
import com.sqwan.liveshow.common.LiveShowParamsKey;
import com.sqwan.liveshow.error.LiveshowResult;
import com.sqwan.msdk.api.SQResultListener;
import com.sy37sdk.account.AccountCache;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LiveshowBaseManager extends BaseEnginHandler {
    protected WeakReference<Activity> activityWeakReferenceLiveShow;
    protected SQResultListener destroyCallback;
    private ActivityLifeCycleUtils.AppVisibilityCallback innerAppVisibilityCallback;
    protected SQResultListener joinRoomListener;
    protected SQResultListener leaveRoomListener;
    private ActivityLifeCycleUtils.AppVisibilityCallback visibilityCallback;
    protected SQResultListener voiceChangeCallback;
    public boolean isResume = true;
    public boolean isResumeLast = true;
    protected JoinRoomStatu joinRoomStatu = JoinRoomStatu.unjoined;

    public enum JoinRoomStatu {
        joined,
        unjoined,
        joining,
        liveshowlist
    }

    public void resetData() {
    }

    public boolean isJoined() {
        return this.joinRoomStatu == JoinRoomStatu.joined;
    }

    public Activity getLiveshowActivity() {
        WeakReference<Activity> weakReference = this.activityWeakReferenceLiveShow;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public ActivityLifeCycleUtils.AppVisibilityCallback getVisibilityCallback() {
        return this.visibilityCallback;
    }

    public void setVisibilityCallback(ActivityLifeCycleUtils.AppVisibilityCallback appVisibilityCallback) {
        this.visibilityCallback = appVisibilityCallback;
    }

    public void initLiveshowActivity(Context context) {
        if (context != null && (context instanceof Activity) && this.activityWeakReferenceLiveShow == null) {
            this.activityWeakReferenceLiveShow = new WeakReference<>((Activity) context);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void callbackInvokeSuccess(SQResultListener sQResultListener, Bundle bundle, LiveshowResult liveshowResult) {
        callbackInvoke(true, sQResultListener, bundle, liveshowResult);
    }

    protected void callbackInvokeFail(SQResultListener sQResultListener, LiveshowResult liveshowResult) {
        callbackInvoke(false, sQResultListener, null, liveshowResult);
    }

    protected void callbackInvoke(boolean z, SQResultListener sQResultListener, Bundle bundle, LiveshowResult liveshowResult) {
        if (!z) {
            if (liveshowResult != null) {
                LogUtil.e(this.TAG, "callbackInvoke fail " + liveshowResult.toString());
                if (sQResultListener != null) {
                    sQResultListener.onFailture(liveshowResult.getError(), liveshowResult.getMsg());
                    return;
                }
                return;
            }
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        LogUtil.i(this.TAG, "callbackInvoke success bundle " + bundle.toString());
        if (liveshowResult != null) {
            LogUtil.i(this.TAG, "callbackInvoke success " + liveshowResult.toString());
        }
        if (sQResultListener != null) {
            sQResultListener.onSuccess(bundle);
        }
    }

    public String getUsernick() {
        BaseBean baseUserInfo = CommonConfigs.getInstance().getBaseUserInfo();
        return baseUserInfo != null ? baseUserInfo.roleName : "";
    }

    public String getUserId() {
        return AccountCache.getUserid(this.context);
    }

    public String getUserName() {
        return AccountCache.getUsername(this.context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void handleLeaveRoomSuccessCallback() {
        callbackInvokeSuccess(this.leaveRoomListener, null, LiveshowResult.success_leaveRoom);
        callbackInvokeSuccess(this.destroyCallback, null, LiveshowResult.success_destroyRoom);
        channelChangeInvokeCallback(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void channelChangeInvokeCallback(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(LiveShowParamsKey.isResume, z);
        callbackInvokeSuccess(this.voiceChangeCallback, bundle, LiveshowResult.success_voiceChange);
    }

    protected void handleRepeatClickLiveshowIcon() {
        Task.post(new Runnable() { // from class: com.sqwan.liveshow.LiveshowBaseManager.1
            @Override // java.lang.Runnable
            public void run() {
                ToastUtil.showToast("请关闭当前直播间后再进入");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initActivityLifeCycle() {
        this.isResume = true;
        if (this.innerAppVisibilityCallback == null) {
            this.innerAppVisibilityCallback = new ActivityLifeCycleUtils.AppVisibilityCallbackAdapter() { // from class: com.sqwan.liveshow.LiveshowBaseManager.2
                @Override // com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallbackAdapter, com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallback
                public void onBackground() {
                    if (LiveshowBaseManager.this.isResume) {
                        LiveshowBaseManager.this.isResumeLast = true;
                        LiveshowBaseManager.this.onBackground();
                    }
                }

                @Override // com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallbackAdapter, com.sqwan.common.util.ActivityLifeCycleUtils.AppVisibilityCallback
                public void onForeground() {
                    if (LiveshowBaseManager.this.isResumeLast) {
                        LiveshowBaseManager.this.isResumeLast = false;
                        LiveshowBaseManager.this.onForeground();
                    }
                }

                @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                    LiveshowBaseManager.this.onActivityStopped(activity);
                }

                @Override // com.sqwan.common.util.ActivityLifecycleAdapter, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                    LiveshowBaseManager.this.onActivityStarted(activity);
                }
            };
        }
        ActivityLifeCycleUtils.getInstance().registerActivityListener(this.innerAppVisibilityCallback);
    }

    protected void uninitActivityLifeCycle() {
        if (this.innerAppVisibilityCallback != null) {
            ActivityLifeCycleUtils.getInstance().unRegisterActivityListener(this.innerAppVisibilityCallback);
        }
    }

    protected void onBackground() {
        ActivityLifeCycleUtils.AppVisibilityCallback appVisibilityCallback = this.visibilityCallback;
        if (appVisibilityCallback != null) {
            appVisibilityCallback.onBackground();
        }
        LogUtil.i(this.TAG, "onBackground");
    }

    protected void onForeground() {
        ActivityLifeCycleUtils.AppVisibilityCallback appVisibilityCallback = this.visibilityCallback;
        if (appVisibilityCallback != null) {
            appVisibilityCallback.onForeground();
        }
        LogUtil.i(this.TAG, "onForeground");
    }

    protected void onActivityStopped(Activity activity) {
        LogUtil.i(this.TAG, "onActivityStopped");
        ActivityLifeCycleUtils.AppVisibilityCallback appVisibilityCallback = this.visibilityCallback;
        if (appVisibilityCallback != null) {
            appVisibilityCallback.onActivityStopped(activity);
        }
    }

    protected void onActivityStarted(Activity activity) {
        LogUtil.i(this.TAG, "onActivityStarted");
        ActivityLifeCycleUtils.AppVisibilityCallback appVisibilityCallback = this.visibilityCallback;
        if (appVisibilityCallback != null) {
            appVisibilityCallback.onActivityStarted(activity);
        }
    }
}
