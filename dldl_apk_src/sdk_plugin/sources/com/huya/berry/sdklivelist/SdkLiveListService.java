package com.huya.berry.sdklivelist;

import android.app.Activity;
import com.duowan.auk.util.L;
import com.huya.berry.sdklivelist.api.ISdkLiveListService;
import com.huya.berry.sdklivelist.api.LiveListListener;
import com.huya.live.service.AbsService;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SdkLiveListService extends AbsService implements ISdkLiveListService {
    public static final String TAG = "SdkLiveListService";
    private LiveListFragment mLiveListFragment;

    @Override // com.huya.berry.sdklivelist.api.ISdkLiveListService
    public void showLiveListFragment(Activity activity, LiveListListener liveListListener, boolean z) {
        if (activity == null) {
            L.error(TAG, "Activity is null");
            if (liveListListener != null) {
                liveListListener.onActivityError();
                return;
            }
            return;
        }
        if (activity.isDestroyed() || activity.isFinishing()) {
            L.error(TAG, "Activity has been destroyed");
            if (liveListListener != null) {
                liveListListener.onActivityError();
                return;
            }
            return;
        }
        if (activity.getFragmentManager() == null || activity.getFragmentManager().isDestroyed()) {
            L.error(TAG, "FragmentManager has been destroyed");
            if (liveListListener != null) {
                liveListListener.onActivityError();
                return;
            }
            return;
        }
        if (this.mLiveListFragment == null) {
            this.mLiveListFragment = LiveListFragment.getInstance(activity.getFragmentManager(), z);
        }
        if (this.mLiveListFragment.isAdded()) {
            return;
        }
        this.mLiveListFragment.setListener(liveListListener);
        this.mLiveListFragment.show(activity.getFragmentManager(), LiveListFragment.TAG);
    }

    @Override // com.huya.berry.sdklivelist.api.ISdkLiveListService
    public void hideLiveListFragment() {
        LiveListFragment liveListFragment = this.mLiveListFragment;
        if (liveListFragment == null) {
            L.error(TAG, "mLiveListFragment == null");
            return;
        }
        liveListFragment.dismissAllowingStateLoss();
        this.mLiveListFragment.setListener(null);
        this.mLiveListFragment = null;
    }

    @Override // com.huya.berry.sdklivelist.api.ISdkLiveListService
    public void showAlertWindowDialogFragment(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        AlertWindowDialogFragment.getInstance(activity.getFragmentManager()).show(activity.getFragmentManager(), AlertWindowDialogFragment.TAG);
    }
}
