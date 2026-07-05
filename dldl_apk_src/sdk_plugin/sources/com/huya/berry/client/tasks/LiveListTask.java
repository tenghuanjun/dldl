package com.huya.berry.client.tasks;

import android.app.Activity;
import com.duowan.auk.util.L;
import com.huya.berry.client.ModifyNicknameFragment;
import com.huya.berry.endlive.api.IEndLiveService;
import com.huya.berry.endlive.data.EndLiveData;
import com.huya.berry.endlive.event.EndLiveFragmentListener;
import com.huya.berry.gamesdk.loading.LoadingFragment;
import com.huya.berry.modifytitle.api.IModifyTitleService;
import com.huya.berry.sdkcamera.api.ISdkCameraService;
import com.huya.berry.sdkcamera.event.CameraListener;
import com.huya.berry.sdklivelist.api.ISdkLiveListService;
import com.huya.berry.sdklivelist.api.LiveListListener;
import com.huya.live.service.ServiceCenter;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveListTask implements LiveListListener {
    private static final String TAG = "LiveStream";
    private WeakReference<Activity> mActivity;
    private EndLiveFragmentListener mEndLiveFragmentListener = new EndLiveFragmentListener() { // from class: com.huya.berry.client.tasks.LiveListTask.1
        @Override // com.huya.berry.endlive.event.EndLiveFragmentListener
        public void onExitClick() {
            LiveListTask.this.hideEndLiveFragment();
        }

        @Override // com.huya.berry.endlive.event.EndLiveFragmentListener
        public void onBackToHome() {
            LiveListTask.this.hideEndLiveFragment();
            LiveListTask.this.showLiveListFragment();
        }
    };
    private boolean mIsHasLiveList;
    private boolean mIsLoading;
    private LiveListListener mListener;
    private LoadingFragment mLoadingFragment;
    private ModifyNicknameFragment mModifyNicknameFragment;

    @Override // com.huya.berry.sdklivelist.api.LiveListListener
    public void onActivityError() {
    }

    public void setListener(LiveListListener liveListListener) {
        this.mListener = liveListListener;
    }

    public LiveListTask(Activity activity) {
        this.mActivity = new WeakReference<>(activity);
    }

    @Override // com.huya.berry.sdklivelist.api.LiveListListener
    public void onStartLive() {
        LiveListListener liveListListener = this.mListener;
        if (liveListListener != null) {
            liveListListener.onStartLive();
        }
    }

    @Override // com.huya.berry.sdklivelist.api.LiveListListener
    public void onLogin() {
        LiveListListener liveListListener = this.mListener;
        if (liveListListener != null) {
            liveListListener.onLogin();
        }
    }

    @Override // com.huya.berry.sdklivelist.api.LiveListListener
    public void onClose() {
        LiveListListener liveListListener = this.mListener;
        if (liveListListener != null) {
            liveListListener.onClose();
        }
    }

    public void start(boolean z) {
        this.mIsHasLiveList = z;
        showLiveListFragment();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLiveListFragment() {
        ISdkLiveListService iSdkLiveListService = (ISdkLiveListService) ServiceCenter.instance().getService(ISdkLiveListService.class);
        if (iSdkLiveListService == null) {
            L.error(TAG, "sdkLiveListService =" + iSdkLiveListService);
            return;
        }
        iSdkLiveListService.showLiveListFragment(getActivity(), this, this.mIsHasLiveList);
    }

    public void hideLiveListFragment() {
        ISdkLiveListService iSdkLiveListService = (ISdkLiveListService) ServiceCenter.instance().getService(ISdkLiveListService.class);
        if (iSdkLiveListService != null) {
            iSdkLiveListService.hideLiveListFragment();
        }
    }

    public void showInputLiveTitleDialog() {
        IModifyTitleService iModifyTitleService = (IModifyTitleService) ServiceCenter.instance().getService(IModifyTitleService.class);
        if (iModifyTitleService == null) {
            L.error(TAG, "modifyTitleService =" + iModifyTitleService);
            return;
        }
        iModifyTitleService.showInputLiveTitleDialog(getActivity());
    }

    public void hideInputLiveTitleDialog() {
        IModifyTitleService iModifyTitleService = (IModifyTitleService) ServiceCenter.instance().getService(IModifyTitleService.class);
        if (iModifyTitleService != null) {
            iModifyTitleService.hideInputLiveTitleDialog();
        }
    }

    public void startLoading() {
        if (getActivity() == null || this.mIsLoading) {
            L.error(TAG, "Activity is null or loading ==" + this.mIsLoading);
            onActivityError();
            return;
        }
        L.info(TAG, "start loading");
        if (this.mLoadingFragment == null) {
            this.mLoadingFragment = LoadingFragment.getInstance(getActivity().getFragmentManager());
        }
        this.mLoadingFragment.show(getActivity().getFragmentManager());
        this.mIsLoading = true;
    }

    public void closeLoading() {
        if (this.mLoadingFragment == null || !this.mIsLoading) {
            return;
        }
        L.info(TAG, "close loading");
        this.mLoadingFragment.dismiss();
        this.mIsLoading = false;
    }

    public void showModifyNicknameFragment() {
        if (getActivity() == null) {
            L.error(TAG, "Activity is null");
            onActivityError();
        } else {
            ModifyNicknameFragment intance = ModifyNicknameFragment.getIntance(getActivity().getFragmentManager());
            this.mModifyNicknameFragment = intance;
            intance.show(getActivity().getFragmentManager());
        }
    }

    public void hideModifyNicknameFragment() {
        ModifyNicknameFragment modifyNicknameFragment = this.mModifyNicknameFragment;
        if (modifyNicknameFragment == null) {
            return;
        }
        modifyNicknameFragment.dismissAllowingStateLoss();
    }

    public void showEndLiveFragment() {
        IEndLiveService iEndLiveService = (IEndLiveService) ServiceCenter.instance().getService(IEndLiveService.class);
        if (iEndLiveService == null) {
            L.error(TAG, "endLiveService =" + iEndLiveService);
            return;
        }
        iEndLiveService.showEndLiveFragment(getActivity(), this.mEndLiveFragmentListener);
    }

    public void setEndLiveData(EndLiveData endLiveData) {
        IEndLiveService iEndLiveService = (IEndLiveService) ServiceCenter.instance().getService(IEndLiveService.class);
        if (iEndLiveService != null) {
            iEndLiveService.setEndLiveData(endLiveData);
        }
    }

    public void hideEndLiveFragment() {
        IEndLiveService iEndLiveService = (IEndLiveService) ServiceCenter.instance().getService(IEndLiveService.class);
        if (iEndLiveService != null) {
            iEndLiveService.hideEndLiveFragment();
        }
    }

    public void showCameraLive(CameraListener cameraListener) {
        ISdkCameraService iSdkCameraService = (ISdkCameraService) ServiceCenter.instance().getService(ISdkCameraService.class);
        if (iSdkCameraService == null) {
            L.error(TAG, "sdkCameraService =" + iSdkCameraService);
            return;
        }
        iSdkCameraService.showCameraLive(getActivity(), cameraListener);
    }

    public Activity getActivity() {
        WeakReference<Activity> weakReference = this.mActivity;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return this.mActivity.get();
    }
}
