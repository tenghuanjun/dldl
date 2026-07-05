package com.huya.berry.sdklive.liveTool;

import android.content.res.Configuration;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface IToolView {
    LiveAlertView addLiveAlertView();

    void createViews();

    void dismissProgress();

    void expandMenu();

    void halfHideTool();

    void onConfigurationChanged(Configuration configuration);

    void onDestroy();

    void resetMsgContainer();

    void setIsStarted(boolean z);

    void setMicban(boolean z);

    void setMsgTip(boolean z, boolean z2, String str);

    void setOnlineUser(int i);

    void showMsgContainer(boolean z);

    void showProgress(String str, boolean z);

    void showStopLiveAlert();

    void showTool(boolean z, boolean z2);

    void updateNewsNum(int i);
}
