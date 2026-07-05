package com.huya.berry.endlive;

import android.app.Activity;
import com.duowan.auk.util.L;
import com.huya.berry.endlive.api.IEndLiveService;
import com.huya.berry.endlive.data.EndLiveData;
import com.huya.berry.endlive.event.EndLiveFragmentListener;
import com.huya.live.service.AbsService;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class EndLiveService extends AbsService implements IEndLiveService {
    public static final String TAG = "EndLiveService";
    private EndLiveFragment mEndLiveFragment;

    @Override // com.huya.berry.endlive.api.IEndLiveService
    public void showEndLiveFragment(Activity activity, EndLiveFragmentListener endLiveFragmentListener) {
        if (activity == null) {
            L.error(TAG, "Activity is null");
            return;
        }
        EndLiveFragment endLiveFragment = EndLiveFragment.getInstance(activity.getFragmentManager());
        this.mEndLiveFragment = endLiveFragment;
        endLiveFragment.setListener(endLiveFragmentListener);
        this.mEndLiveFragment.show(activity.getFragmentManager());
    }

    @Override // com.huya.berry.endlive.api.IEndLiveService
    public void setEndLiveData(EndLiveData endLiveData) {
        EndLiveFragment endLiveFragment = this.mEndLiveFragment;
        if (endLiveFragment == null) {
            return;
        }
        endLiveFragment.setEndLiveData(endLiveData);
    }

    @Override // com.huya.berry.endlive.api.IEndLiveService
    public void hideEndLiveFragment() {
        EndLiveFragment endLiveFragment = this.mEndLiveFragment;
        if (endLiveFragment == null) {
            return;
        }
        endLiveFragment.dismissAllowingStateLoss();
    }
}
