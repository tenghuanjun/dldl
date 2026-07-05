package com.huya.berry.endlive.api;

import android.app.Activity;
import com.huya.berry.endlive.data.EndLiveData;
import com.huya.berry.endlive.event.EndLiveFragmentListener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface IEndLiveService {
    void hideEndLiveFragment();

    void setEndLiveData(EndLiveData endLiveData);

    void showEndLiveFragment(Activity activity, EndLiveFragmentListener endLiveFragmentListener);
}
