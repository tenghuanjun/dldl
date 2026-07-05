package com.huya.berry.client.customui;

import android.text.TextUtils;
import com.duowan.HUYA.ChangeLiveInfoRsp;
import com.duowan.HUYA.GetUserProfileRsp;
import com.duowan.HUYA.LiveAnnouncementSettingRsp;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.http.v2.wup.WupError;
import com.duowan.auk.signal.IASlot;
import com.duowan.live.login.LoginReportConstants;
import com.duowan.live.login.api.ILoginService;
import com.duowan.live.one.module.report.Report;
import com.huya.berry.client.LiveStream;
import com.huya.berry.client.customui.model.ErrorInfo;
import com.huya.berry.client.customui.model.OptionalResolution;
import com.huya.berry.gamesdk.SdkProperties;
import com.huya.berry.gamesdk.module.ICommonService;
import com.huya.berry.gamesdk.resolutions.LivingParams;
import com.huya.berry.gamesdk.resolutions.ResolutionOptions;
import com.huya.berry.gamesdk.utils.TaskExecutor;
import com.huya.berry.module.help.LiveHelper;
import com.huya.berry.module.live.ISdkLiveService;
import com.huya.component.login.api.LoginApi;
import com.huya.component.login.api.LoginCallback;
import com.huya.component.user.UserProperties;
import com.huya.component.user.api.IUserService;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.live.rxutils.SchedulerUtils;
import com.huya.live.service.ServiceCenter;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.hyns.stat.NSStatUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CustomUIPresenterImpl implements ICustomUIPresenter {
    private static final int FROM_LOGIN = 3;
    private static final int FROM_MODIFY_NICKNAME = 2;
    private static final int FROM_NULL = 0;
    private static final int FROM_STARTLIVE = 1;
    private int mCustomFrom = 0;
    private CustomUICallback mCustomUICallback;
    private WeakReference<LiveStream> mLiveStream;

    @Override // com.huya.berry.client.customui.ICustomUI
    public void getLiveUrl(String str, CustomUICallback customUICallback) {
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onGetAuthorInfo(CustomUICallback customUICallback) {
    }

    @Override // com.duowan.live.common.framework.IPresenter
    public void onPause() {
    }

    @Override // com.duowan.live.common.framework.IPresenter
    public void onResume() {
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void stopRtmpLive(CustomUICallback customUICallback) {
    }

    public CustomUIPresenterImpl(LiveStream liveStream) {
        this.mLiveStream = new WeakReference<>(liveStream);
    }

    @Override // com.duowan.live.common.framework.IPresenter
    public void onCreate() {
        ArkUtils.register(this);
    }

    @Override // com.duowan.live.common.framework.IPresenter
    public void onDestroy() {
        ArkUtils.unregister(this);
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void setFromStartlive() {
        this.mCustomFrom = 0;
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onStartLive(CustomUICallback customUICallback) {
        LiveStream liveStream = getLiveStream();
        if (liveStream == null) {
            return;
        }
        this.mCustomFrom = 1;
        liveStream.setIsDirect(true);
        liveStream.onStartLive();
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onShowModifyNickname(CustomUICallback customUICallback) {
        LiveStream liveStream = getLiveStream();
        if (liveStream == null) {
            return;
        }
        this.mCustomFrom = 2;
        liveStream.onShowModifyNickname();
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onModifyTitle(CustomUICallback customUICallback, String str) {
        LiveStream liveStream = getLiveStream();
        if (liveStream == null) {
            return;
        }
        if (liveStream.isNotLogin()) {
            customUICallback.onResultCallback(1, null);
        } else if (SdkProperties.isLiving.get().booleanValue()) {
            this.mCustomUICallback = customUICallback;
            changeLiveTitle(str);
        } else {
            SdkProperties.liveTitle.set(str);
            customUICallback.onResultCallback(0, null);
        }
    }

    private void changeLiveTitle(String str) {
        ISdkLiveService iSdkLiveService = (ISdkLiveService) ServiceCenter.instance().getService(ISdkLiveService.class);
        if (iSdkLiveService != null) {
            iSdkLiveService.changeLiveInfo(str).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<ChangeLiveInfoRsp>() { // from class: com.huya.berry.client.customui.CustomUIPresenterImpl.1
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(ChangeLiveInfoRsp changeLiveInfoRsp) {
                    if (CustomUIPresenterImpl.this.mCustomUICallback == null) {
                        return;
                    }
                    CustomUIPresenterImpl.this.mCustomUICallback.onResultCallback(0, null);
                    CustomUIPresenterImpl.this.mCustomUICallback = null;
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    if (CustomUIPresenterImpl.this.mCustomUICallback == null) {
                        return;
                    }
                    Throwable throwable = NSStatUtil.parseThrowable((DataException) th);
                    if (throwable instanceof WupError) {
                        CustomUIPresenterImpl.this.mCustomUICallback.onResultCallback(1, new ErrorInfo(((ChangeLiveInfoRsp) ((WupError) throwable).mResponse).sMessage));
                    } else {
                        CustomUIPresenterImpl.this.mCustomUICallback.onResultCallback(1, new ErrorInfo(th.getMessage()));
                    }
                    CustomUIPresenterImpl.this.mCustomUICallback = null;
                }
            });
        }
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onModifyAnnouncement(CustomUICallback customUICallback, String str) {
        LiveStream liveStream = getLiveStream();
        if (liveStream == null) {
            return;
        }
        if (liveStream.isNotLogin()) {
            customUICallback.onResultCallback(1, null);
        } else {
            this.mCustomUICallback = customUICallback;
            changeLiveAnnouncement(str);
        }
    }

    private void changeLiveAnnouncement(final String str) {
        ICommonService iCommonService = (ICommonService) ServiceCenter.instance().getService(ICommonService.class);
        if (iCommonService != null) {
            iCommonService.setMyLiveAnnouncement(str, LiveHelper.getUserId()).compose(SchedulerUtils.ioio()).observeOn(AndroidSchedulers.mainThread()).subscribe(new WupObserver<LiveAnnouncementSettingRsp>() { // from class: com.huya.berry.client.customui.CustomUIPresenterImpl.2
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(LiveAnnouncementSettingRsp liveAnnouncementSettingRsp) {
                    if (liveAnnouncementSettingRsp == null) {
                        return;
                    }
                    if (TextUtils.isEmpty(liveAnnouncementSettingRsp.sMessage)) {
                        SdkProperties.liveAnnouncement.set(str);
                    }
                    if (CustomUIPresenterImpl.this.mCustomUICallback == null) {
                        return;
                    }
                    CustomUIPresenterImpl.this.mCustomUICallback.onResultCallback(0, null);
                    CustomUIPresenterImpl.this.mCustomUICallback = null;
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    if (CustomUIPresenterImpl.this.mCustomUICallback == null) {
                        return;
                    }
                    Throwable throwable = NSStatUtil.parseThrowable((DataException) th);
                    if (throwable instanceof WupError) {
                        String str2 = ((ChangeLiveInfoRsp) ((WupError) throwable).mResponse).sMessage;
                        CustomUIPresenterImpl.this.mCustomUICallback.onResultCallback(1, TextUtils.isEmpty(str2) ? new ErrorInfo("请求超时") : new ErrorInfo(str2));
                    } else {
                        CustomUIPresenterImpl.this.mCustomUICallback.onResultCallback(1, TextUtils.isEmpty(th.getMessage()) ? new ErrorInfo("请求超时") : new ErrorInfo(th.getMessage()));
                    }
                    CustomUIPresenterImpl.this.mCustomUICallback = null;
                }
            });
        }
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onLogin(CustomUICallback customUICallback) {
        LiveStream liveStream = getLiveStream();
        if (liveStream == null) {
            return;
        }
        this.mCustomFrom = 3;
        liveStream.setReadyToStartLive(false);
        liveStream.onLogin();
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onLogout(CustomUICallback customUICallback) {
        if (getLiveStream() == null || getLiveStream().isNotLogin() || SdkProperties.isLiving.get().booleanValue()) {
            customUICallback.onResultCallback(1, null);
            Report.event(LoginReportConstants.STATUS_LOGOUT_FAIL);
            return;
        }
        ILoginService iLoginService = (ILoginService) ServiceCenter.instance().getService(ILoginService.class);
        if (iLoginService != null) {
            iLoginService.logout();
            UserProperties.roomId.set(0);
            customUICallback.onResultCallback(0, null);
            Report.event(LoginReportConstants.STATUS_LOGOUT_SUCCESS);
        }
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onGetResolution(CustomUICallback customUICallback) {
        LiveStream liveStream = getLiveStream();
        if (liveStream == null) {
            return;
        }
        if (liveStream.isNotLogin()) {
            customUICallback.onResultListCallback(1, null);
            return;
        }
        List<LivingParams> resolutionSettingList = ResolutionOptions.getInstance().getResolutionSettingList();
        if (resolutionSettingList == null || resolutionSettingList.size() == 0) {
            customUICallback.onResultListCallback(2, null);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (LivingParams livingParams : resolutionSettingList) {
            arrayList.add(new OptionalResolution(livingParams.getResolution(), livingParams.getTips()));
        }
        customUICallback.onResultListCallback(0, arrayList);
    }

    @Override // com.huya.berry.client.customui.ICustomUI
    public void onSetResolution(CustomUICallback customUICallback, int i) {
        LiveStream liveStream = getLiveStream();
        if (liveStream == null) {
            return;
        }
        if (liveStream.isNotLogin()) {
            customUICallback.onResultCallback(2, null);
        } else if (ResolutionOptions.getInstance().containResolution(i)) {
            SdkProperties.resolution.set(Integer.valueOf(i));
            customUICallback.onResultCallback(0, null);
        } else {
            customUICallback.onResultCallback(1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LiveStream getLiveStream() {
        WeakReference<LiveStream> weakReference = this.mLiveStream;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return this.mLiveStream.get();
    }

    private void getUserProfile() {
        IUserService iUserService = (IUserService) ServiceCenter.instance().getService(IUserService.class);
        if (iUserService != null) {
            iUserService.getUserProfile(LoginApi.getUid()).compose(SchedulerUtils.ioio()).subscribe(new WupObserver<GetUserProfileRsp>() { // from class: com.huya.berry.client.customui.CustomUIPresenterImpl.3
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(GetUserProfileRsp getUserProfileRsp) {
                    TaskExecutor.uiHandler().post(new Runnable() { // from class: com.huya.berry.client.customui.CustomUIPresenterImpl.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            LiveStream liveStream = CustomUIPresenterImpl.this.getLiveStream();
                            if (liveStream != null && CustomUIPresenterImpl.this.mCustomFrom == 2) {
                                liveStream.onShowModifyNickname();
                            }
                        }
                    });
                }
            });
        }
    }

    @IASlot(executorID = 1)
    public void onLoginFinished(LoginCallback.LoginFinished loginFinished) {
        LiveStream liveStream = getLiveStream();
        if (liveStream != null && loginFinished.success) {
            boolean z = true;
            if (!liveStream.getReadyToStartLive() && this.mCustomFrom != 1) {
                z = false;
            }
            if (z && !liveStream.isOutSideCapture()) {
                liveStream.onStartLive();
            }
            getUserProfile();
        }
    }
}
