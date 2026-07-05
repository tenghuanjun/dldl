package com.huya.berry.sdklivelist.api;

import android.app.Activity;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface ISdkLiveListService {
    void hideLiveListFragment();

    void showAlertWindowDialogFragment(Activity activity);

    void showLiveListFragment(Activity activity, LiveListListener liveListListener, boolean z);
}
