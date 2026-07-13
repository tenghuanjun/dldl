package com.shuyu.gsyvideoplayer;

import android.content.res.Configuration;
import android.view.View;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationOption;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.GSYADVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class GSYBaseADActivityDetail<T extends GSYBaseVideoPlayer, R extends GSYADVideoPlayer> extends GSYBaseActivityDetail<T> {
    protected OrientationUtils mADOrientationUtils;

    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail
    public void clickForFullScreen() {
    }

    public abstract GSYVideoOptionBuilder getGSYADVideoOptionBuilder();

    public abstract R getGSYADVideoPlayer();

    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail
    public OrientationOption getOrientationOption() {
        return null;
    }

    public abstract boolean isNeedAdOnStart();

    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail, com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onComplete(String str, Object... objArr) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail
    public void initVideo() {
        super.initVideo();
        OrientationUtils orientationUtils = new OrientationUtils(this, getGSYADVideoPlayer(), getOrientationOption());
        this.mADOrientationUtils = orientationUtils;
        orientationUtils.setEnable(false);
        if (getGSYADVideoPlayer().getFullscreenButton() != null) {
            getGSYADVideoPlayer().getFullscreenButton().setOnClickListener(new View.OnClickListener() { // from class: com.shuyu.gsyvideoplayer.GSYBaseADActivityDetail.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    GSYBaseADActivityDetail.this.showADFull();
                    GSYBaseADActivityDetail.this.clickForFullScreen();
                }
            });
        }
    }

    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail
    public void initVideoBuilderMode() {
        super.initVideoBuilderMode();
        getGSYADVideoOptionBuilder().setVideoAllCallBack(new GSYSampleCallBack() { // from class: com.shuyu.gsyvideoplayer.GSYBaseADActivityDetail.2
            @Override // com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack, com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
            public void onStartPrepared(String str, Object... objArr) {
                super.onStartPrepared(str, objArr);
                GSYBaseADActivityDetail.this.mADOrientationUtils.setEnable(GSYBaseADActivityDetail.this.getDetailOrientationRotateAuto());
            }

            /* JADX WARN: Type inference failed for: r1v18, types: [com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer] */
            /* JADX WARN: Type inference failed for: r1v23, types: [com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer] */
            /* JADX WARN: Type inference failed for: r1v9, types: [com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer] */
            @Override // com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack, com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
            public void onAutoComplete(String str, Object... objArr) {
                GSYBaseADActivityDetail.this.getGSYADVideoPlayer().getCurrentPlayer().release();
                GSYBaseADActivityDetail.this.getGSYADVideoPlayer().onVideoReset();
                GSYBaseADActivityDetail.this.getGSYADVideoPlayer().setVisibility(8);
                GSYBaseADActivityDetail.this.getGSYVideoPlayer().getCurrentPlayer().startAfterPrepared();
                if (GSYBaseADActivityDetail.this.getGSYADVideoPlayer().getCurrentPlayer().isIfCurrentIsFullscreen()) {
                    GSYBaseADActivityDetail.this.getGSYADVideoPlayer().removeFullWindowViewOnly();
                    if (GSYBaseADActivityDetail.this.getGSYVideoPlayer().getCurrentPlayer().isIfCurrentIsFullscreen()) {
                        return;
                    }
                    GSYBaseADActivityDetail.this.showFull();
                    GSYBaseADActivityDetail.this.getGSYVideoPlayer().setSaveBeforeFullSystemUiVisibility(GSYBaseADActivityDetail.this.getGSYADVideoPlayer().getSaveBeforeFullSystemUiVisibility());
                }
            }

            /* JADX WARN: Type inference failed for: r1v4, types: [com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer] */
            /* JADX WARN: Type inference failed for: r1v8, types: [com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer] */
            @Override // com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack, com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
            public void onQuitFullscreen(String str, Object... objArr) {
                if (GSYBaseADActivityDetail.this.mADOrientationUtils != null) {
                    GSYBaseADActivityDetail.this.mADOrientationUtils.backToProtVideo();
                }
                if (GSYBaseADActivityDetail.this.getGSYVideoPlayer().getCurrentPlayer().isIfCurrentIsFullscreen()) {
                    GSYBaseADActivityDetail.this.getGSYVideoPlayer().onBackFullscreen();
                }
            }
        }).build((StandardGSYVideoPlayer) getGSYADVideoPlayer());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail
    public void showFull() {
        if (this.orientationUtils.getIsLand() != 1) {
            this.orientationUtils.resolveByClick();
        }
        getGSYVideoPlayer().startWindowFullscreen(this, hideActionBarWhenFull(), hideStatusBarWhenFull());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail
    public void onBackPressed() {
        OrientationUtils orientationUtils = this.mADOrientationUtils;
        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }
        if (GSYVideoADManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail
    protected void onPause() {
        super.onPause();
        GSYVideoADManager.onPause();
    }

    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail
    protected void onResume() {
        super.onResume();
        GSYVideoADManager.onResume();
    }

    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoADManager.releaseAllVideos();
        OrientationUtils orientationUtils = this.mADOrientationUtils;
        if (orientationUtils != null) {
            orientationUtils.releaseListener();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail
    public void onConfigurationChanged(Configuration configuration) {
        boolean z = this.isPlay;
        if (!this.isPause && getGSYADVideoPlayer().getVisibility() == 0 && isADStarted()) {
            this.isPlay = false;
            getGSYADVideoPlayer().getCurrentPlayer().onConfigurationChanged(this, configuration, this.mADOrientationUtils, hideActionBarWhenFull(), hideStatusBarWhenFull());
        }
        super.onConfigurationChanged(configuration);
        this.isPlay = z;
    }

    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail, com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onStartPrepared(String str, Object... objArr) {
        super.onStartPrepared(str, objArr);
    }

    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail, com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onPrepared(String str, Object... objArr) {
        super.onPrepared(str, objArr);
        if (isNeedAdOnStart()) {
            startAdPlay();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.GSYBaseActivityDetail, com.shuyu.gsyvideoplayer.listener.VideoAllCallBack
    public void onEnterFullscreen(String str, Object... objArr) {
        super.onEnterFullscreen(str, objArr);
        ((GSYVideoPlayer) objArr[1]).getBackButton().setVisibility(8);
    }

    protected boolean isADStarted() {
        return (getGSYADVideoPlayer().getCurrentPlayer().getCurrentState() < 0 || getGSYADVideoPlayer().getCurrentPlayer().getCurrentState() == 0 || getGSYADVideoPlayer().getCurrentPlayer().getCurrentState() == 6) ? false : true;
    }

    public void startAdPlay() {
        getGSYADVideoPlayer().setVisibility(0);
        getGSYADVideoPlayer().startPlayLogic();
        if (getGSYVideoPlayer().getCurrentPlayer().isIfCurrentIsFullscreen()) {
            showADFull();
            getGSYADVideoPlayer().setSaveBeforeFullSystemUiVisibility(getGSYVideoPlayer().getSaveBeforeFullSystemUiVisibility());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void showADFull() {
        if (this.mADOrientationUtils.getIsLand() != 1) {
            this.mADOrientationUtils.resolveByClick();
        }
        getGSYADVideoPlayer().startWindowFullscreen(this, hideActionBarWhenFull(), hideStatusBarWhenFull());
    }
}
