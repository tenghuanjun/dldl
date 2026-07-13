package com.shuyu.gsyvideoplayer.video;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.shuyu.gsyvideoplayer.model.GSYVideoModel;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import moe.codeest.enviews.ENDownloadView;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ListGSYVideoPlayer extends StandardGSYVideoPlayer {
    protected int mPlayPosition;
    protected List<GSYVideoModel> mUriList;

    public ListGSYVideoPlayer(Context context, Boolean bool) {
        super(context, bool);
        this.mUriList = new ArrayList();
    }

    public ListGSYVideoPlayer(Context context) {
        super(context);
        this.mUriList = new ArrayList();
    }

    public ListGSYVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mUriList = new ArrayList();
    }

    public boolean setUp(List<GSYVideoModel> list, boolean z, int i) {
        return setUp(list, z, i, (File) null, new HashMap());
    }

    public boolean setUp(List<GSYVideoModel> list, boolean z, int i, File file) {
        return setUp(list, z, i, file, new HashMap());
    }

    public boolean setUp(List<GSYVideoModel> list, boolean z, int i, File file, Map<String, String> map) {
        return setUp(list, z, i, file, map, true);
    }

    protected boolean setUp(List<GSYVideoModel> list, boolean z, int i, File file, Map<String, String> map, boolean z2) {
        this.mUriList = list;
        this.mPlayPosition = i;
        this.mMapHeadData = map;
        GSYVideoModel gSYVideoModel = list.get(i);
        boolean up = setUp(gSYVideoModel.getUrl(), z, file, gSYVideoModel.getTitle(), z2);
        if (!TextUtils.isEmpty(gSYVideoModel.getTitle()) && this.mTitleTextView != null) {
            this.mTitleTextView.setText(gSYVideoModel.getTitle());
        }
        return up;
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer
    protected void cloneParams(GSYBaseVideoPlayer gSYBaseVideoPlayer, GSYBaseVideoPlayer gSYBaseVideoPlayer2) {
        super.cloneParams(gSYBaseVideoPlayer, gSYBaseVideoPlayer2);
        ListGSYVideoPlayer listGSYVideoPlayer = (ListGSYVideoPlayer) gSYBaseVideoPlayer;
        ListGSYVideoPlayer listGSYVideoPlayer2 = (ListGSYVideoPlayer) gSYBaseVideoPlayer2;
        listGSYVideoPlayer2.mPlayPosition = listGSYVideoPlayer.mPlayPosition;
        listGSYVideoPlayer2.mUriList = listGSYVideoPlayer.mUriList;
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer
    public GSYBaseVideoPlayer startWindowFullscreen(Context context, boolean z, boolean z2) {
        GSYBaseVideoPlayer gSYBaseVideoPlayerStartWindowFullscreen = super.startWindowFullscreen(context, z, z2);
        if (gSYBaseVideoPlayerStartWindowFullscreen != null) {
            ListGSYVideoPlayer listGSYVideoPlayer = (ListGSYVideoPlayer) gSYBaseVideoPlayerStartWindowFullscreen;
            GSYVideoModel gSYVideoModel = this.mUriList.get(this.mPlayPosition);
            if (!TextUtils.isEmpty(gSYVideoModel.getTitle()) && this.mTitleTextView != null) {
                listGSYVideoPlayer.mTitleTextView.setText(gSYVideoModel.getTitle());
            }
        }
        return gSYBaseVideoPlayerStartWindowFullscreen;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer
    protected void resolveNormalVideoShow(View view, ViewGroup viewGroup, GSYVideoPlayer gSYVideoPlayer) {
        if (gSYVideoPlayer != null) {
            GSYVideoModel gSYVideoModel = this.mUriList.get(this.mPlayPosition);
            if (!TextUtils.isEmpty(gSYVideoModel.getTitle()) && this.mTitleTextView != null) {
                this.mTitleTextView.setText(gSYVideoModel.getTitle());
            }
        }
        super.resolveNormalVideoShow(view, viewGroup, gSYVideoPlayer);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView, com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onCompletion() {
        releaseNetWorkState();
        if (this.mPlayPosition < this.mUriList.size()) {
            return;
        }
        super.onCompletion();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView, com.shuyu.gsyvideoplayer.video.base.GSYVideoView, com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onAutoCompletion() {
        if (playNext()) {
            return;
        }
        super.onAutoCompletion();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    protected void prepareVideo() {
        super.prepareVideo();
        if (!this.mHadPlay || this.mPlayPosition >= this.mUriList.size()) {
            return;
        }
        setViewShowState(this.mLoadingProgressBar, 0);
        if (this.mLoadingProgressBar instanceof ENDownloadView) {
            this.mLoadingProgressBar.start();
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView, com.shuyu.gsyvideoplayer.video.base.GSYVideoView, com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onPrepared() {
        super.onPrepared();
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void changeUiToNormal() {
        super.changeUiToNormal();
        if (!this.mHadPlay || this.mPlayPosition >= this.mUriList.size()) {
            return;
        }
        setViewShowState(this.mThumbImageViewLayout, 8);
        setViewShowState(this.mTopContainer, 4);
        setViewShowState(this.mBottomContainer, 4);
        setViewShowState(this.mStartButton, 8);
        setViewShowState(this.mLoadingProgressBar, 0);
        setViewShowState(this.mBottomProgressBar, 4);
        setViewShowState(this.mLockScreen, 8);
        if (this.mLoadingProgressBar instanceof ENDownloadView) {
            this.mLoadingProgressBar.start();
        }
    }

    public boolean playNext() {
        if (this.mPlayPosition >= this.mUriList.size() - 1) {
            return false;
        }
        int i = this.mPlayPosition + 1;
        this.mPlayPosition = i;
        GSYVideoModel gSYVideoModel = this.mUriList.get(i);
        this.mSaveChangeViewTIme = 0L;
        setUp(this.mUriList, this.mCache, this.mPlayPosition, null, this.mMapHeadData, false);
        if (!TextUtils.isEmpty(gSYVideoModel.getTitle()) && this.mTitleTextView != null) {
            this.mTitleTextView.setText(gSYVideoModel.getTitle());
        }
        startPlayLogic();
        return true;
    }
}
