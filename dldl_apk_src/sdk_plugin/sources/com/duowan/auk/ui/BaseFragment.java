package com.duowan.auk.ui;

import android.app.Fragment;
import com.huya.live.common.api.BaseApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BaseFragment extends Fragment {
    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        BaseApi.getSignalCenterApi().register(this);
    }

    @Override // android.app.Fragment
    public void onPause() {
        BaseApi.getSignalCenterApi().unregister(this);
        super.onPause();
    }
}
