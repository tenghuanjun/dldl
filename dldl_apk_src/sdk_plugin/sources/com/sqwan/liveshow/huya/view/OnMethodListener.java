package com.sqwan.liveshow.huya.view;

import android.view.View;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface OnMethodListener {
    void addVideoFragmentContainer(View view, View view2);

    void loadSuccess();

    void loading();

    void networkError();

    void setAnchorInfo(String str, String str2, String str3);

    void setOnBehaviorListener(OnBehaviorListener onBehaviorListener);

    void switchFullLiveContainer();

    void switchSmallLiveContainer();
}
