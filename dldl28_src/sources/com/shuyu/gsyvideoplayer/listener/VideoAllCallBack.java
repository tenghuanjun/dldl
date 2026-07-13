package com.shuyu.gsyvideoplayer.listener;

/* JADX INFO: loaded from: classes3.dex */
public interface VideoAllCallBack {
    void onAutoComplete(String str, Object... objArr);

    void onClickBlank(String str, Object... objArr);

    void onClickBlankFullscreen(String str, Object... objArr);

    void onClickResume(String str, Object... objArr);

    void onClickResumeFullscreen(String str, Object... objArr);

    void onClickSeekbar(String str, Object... objArr);

    void onClickSeekbarFullscreen(String str, Object... objArr);

    void onClickStartError(String str, Object... objArr);

    void onClickStartIcon(String str, Object... objArr);

    void onClickStartThumb(String str, Object... objArr);

    void onClickStop(String str, Object... objArr);

    void onClickStopFullscreen(String str, Object... objArr);

    void onComplete(String str, Object... objArr);

    void onEnterFullscreen(String str, Object... objArr);

    void onEnterSmallWidget(String str, Object... objArr);

    void onPlayError(String str, Object... objArr);

    void onPrepared(String str, Object... objArr);

    void onQuitFullscreen(String str, Object... objArr);

    void onQuitSmallWidget(String str, Object... objArr);

    void onStartPrepared(String str, Object... objArr);

    void onTouchScreenSeekLight(String str, Object... objArr);

    void onTouchScreenSeekPosition(String str, Object... objArr);

    void onTouchScreenSeekVolume(String str, Object... objArr);
}
