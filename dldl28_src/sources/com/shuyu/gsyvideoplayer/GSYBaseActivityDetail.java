package com.shuyu.gsyvideoplayer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationOption;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;

/* JADX INFO: loaded from: classes3.dex */
public abstract class GSYBaseActivityDetail<T extends GSYBaseVideoPlayer> extends AppCompatActivity implements VideoAllCallBack {
    protected boolean isPause;
    protected boolean isPlay;
    protected OrientationUtils orientationUtils;

    public abstract void clickForFullScreen();

    public abstract boolean getDetailOrientationRotateAuto();

    public abstract GSYVideoOptionBuilder getGSYVideoOptionBuilder();

    public abstract T getGSYVideoPlayer();

    public OrientationOption getOrientationOption() {
        return null;
    }

    public boolean hideActionBarWhenFull() {
        return true;
    }

    public boolean hideStatusBarWhenFull() {
        return true;
    }

    public boolean isAutoFullWithSize() {
        return false;
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onAutoComplete(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onClickBlank(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onClickBlankFullscreen(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onClickResume(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onClickResumeFullscreen(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onClickSeekbar(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onClickSeekbarFullscreen(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onClickStartError(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onClickStartIcon(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onClickStartThumb(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onClickStop(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onClickStopFullscreen(String str, Object... objArr) {
    }

    public void onComplete(String str, Object... objArr) {
    }

    public void onEnterFullscreen(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onEnterSmallWidget(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onPlayError(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onQuitSmallWidget(String str, Object... objArr) {
    }

    public void onStartPrepared(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onTouchScreenSeekLight(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onTouchScreenSeekPosition(String str, Object... objArr) {
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onTouchScreenSeekVolume(String str, Object... objArr) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void initVideo() {
        OrientationUtils orientationUtils = new OrientationUtils(this, getGSYVideoPlayer(), getOrientationOption());
        this.orientationUtils = orientationUtils;
        orientationUtils.setEnable(false);
        if (getGSYVideoPlayer().getFullscreenButton() != null) {
            getGSYVideoPlayer().getFullscreenButton().setOnClickListener(new View.OnClickListener() { // from class: com.shuyu.gsyvideoplayer.GSYBaseActivityDetail.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    GSYBaseActivityDetail.this.showFull();
                    GSYBaseActivityDetail.this.clickForFullScreen();
                }
            });
        }
    }

    public void initVideoBuilderMode() {
        initVideo();
        getGSYVideoOptionBuilder().setVideoAllCallBack(this).build(getGSYVideoPlayer());
    }

    public void showFull() {
        if (this.orientationUtils.getIsLand() != 1) {
            this.orientationUtils.resolveByClick();
        }
        getGSYVideoPlayer().startWindowFullscreen(this, hideActionBarWhenFull(), hideStatusBarWhenFull());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        OrientationUtils orientationUtils = this.orientationUtils;
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        getGSYVideoPlayer().getCurrentPlayer().onVideoPause();
        OrientationUtils orientationUtils = this.orientationUtils;
        if (orientationUtils != null) {
            orientationUtils.setIsPause(true);
        }
        this.isPause = true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getGSYVideoPlayer().getCurrentPlayer().onVideoResume();
        OrientationUtils orientationUtils = this.orientationUtils;
        if (orientationUtils != null) {
            orientationUtils.setIsPause(false);
        }
        this.isPause = false;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.isPlay) {
            getGSYVideoPlayer().getCurrentPlayer().release();
        }
        OrientationUtils orientationUtils = this.orientationUtils;
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (!this.isPlay || this.isPause) {
            return;
        }
        getGSYVideoPlayer().onConfigurationChanged(this, configuration, this.orientationUtils, hideActionBarWhenFull(), hideStatusBarWhenFull());
    }

    public void onPrepared(String str, Object... objArr) {
        OrientationUtils orientationUtils = this.orientationUtils;
        if (orientationUtils == null) {
            throw new NullPointerException("initVideo() or initVideoBuilderMode() first");
        }
        orientationUtils.setEnable(getDetailOrientationRotateAuto() && !isAutoFullWithSize());
        this.isPlay = true;
    }

    @Override // com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onQuitFullscreen(String str, Object... objArr) {
        OrientationUtils orientationUtils = this.orientationUtils;
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
    }
}
