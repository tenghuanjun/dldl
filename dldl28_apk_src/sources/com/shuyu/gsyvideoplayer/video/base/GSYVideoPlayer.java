package com.shuyu.gsyvideoplayer.video.base;

import android.content.Context;
import android.util.AttributeSet;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

/* JADX INFO: loaded from: classes3.dex */
public abstract class GSYVideoPlayer extends GSYBaseVideoPlayer {
    public GSYVideoPlayer(Context context, Boolean bool) {
        super(context, bool);
    }

    public GSYVideoPlayer(Context context) {
        super(context);
    }

    public GSYVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GSYVideoPlayer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    public GSYVideoViewBridge getGSYVideoManager() {
        GSYVideoManager.instance().initContext(getContext().getApplicationContext());
        return GSYVideoManager.instance();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    protected boolean backFromFull(Context context) {
        return GSYVideoManager.backFromWindowFull(context);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    protected void releaseVideos() {
        GSYVideoManager.releaseAllVideos();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer
    protected int getFullId() {
        return GSYVideoManager.FULLSCREEN_ID;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer
    protected int getSmallId() {
        return GSYVideoManager.SMALL_ID;
    }
}
