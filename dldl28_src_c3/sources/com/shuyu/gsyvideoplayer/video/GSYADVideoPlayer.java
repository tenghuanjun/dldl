package com.shuyu.gsyvideoplayer.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.shuyu.gsyvideoplayer.GSYVideoADManager;
import com.shuyu.gsyvideoplayer.R;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class GSYADVideoPlayer extends StandardGSYVideoPlayer {
    protected boolean isFirstPrepared;
    protected TextView mADTime;
    protected View mJumpAd;

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void touchDoubleUp(MotionEvent motionEvent) {
    }

    public GSYADVideoPlayer(Context context, Boolean bool) {
        super(context, bool);
    }

    public GSYADVideoPlayer(Context context) {
        super(context);
    }

    public GSYADVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    protected void init(Context context) {
        super.init(context);
        this.mJumpAd = findViewById(R.id.jump_ad);
        this.mADTime = (TextView) findViewById(R.id.ad_time);
        View view = this.mJumpAd;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.shuyu.gsyvideoplayer.video.GSYADVideoPlayer.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (GSYADVideoPlayer.this.getGSYVideoManager().listener() != null) {
                        GSYADVideoPlayer.this.getGSYVideoManager().listener().onAutoCompletion();
                    }
                }
            });
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    public int getLayoutId() {
        return R.layout.video_layout_ad;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    public GSYVideoViewBridge getGSYVideoManager() {
        GSYVideoADManager.instance().initContext(getContext().getApplicationContext());
        return GSYVideoADManager.instance();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    protected boolean backFromFull(Context context) {
        return GSYVideoADManager.backFromWindowFull(context);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    protected void releaseVideos() {
        GSYVideoADManager.releaseAllVideos();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer
    protected int getFullId() {
        return GSYVideoADManager.FULLSCREEN_ID;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer
    protected int getSmallId() {
        return GSYVideoADManager.SMALL_ID;
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView, com.shuyu.gsyvideoplayer.video.base.GSYVideoView, com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onPrepared() {
        super.onPrepared();
        this.isFirstPrepared = true;
        changeAdUIState();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.start) {
            if (this.mCurrentState == 7) {
                clickStartIcon();
                return;
            }
            return;
        }
        super.onClick(view);
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
    protected void updateStartImage() {
        if (this.mStartButton == null || !(this.mStartButton instanceof ImageView)) {
            return;
        }
        ImageView imageView = (ImageView) this.mStartButton;
        if (this.mCurrentState == 2) {
            imageView.setImageResource(R.drawable.empty_drawable);
        } else if (this.mCurrentState == 7) {
            imageView.setImageResource(R.drawable.video_click_error_selector);
        } else {
            imageView.setImageResource(R.drawable.empty_drawable);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void touchSurfaceMove(float f, float f2, float f3) {
        if (this.mChangePosition) {
            return;
        }
        super.touchSurfaceMove(f, f2, f3);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void touchSurfaceMoveFullLogic(float f, float f2) {
        if (f > this.mThreshold || f2 > this.mThreshold) {
            int screenWidth = CommonUtil.getScreenWidth(getContext());
            if (f >= this.mThreshold && Math.abs(screenWidth - this.mDownX) > this.mSeekEndOffset) {
                this.mChangePosition = true;
                this.mDownPosition = getCurrentPositionWhenPlaying();
            } else {
                super.touchSurfaceMoveFullLogic(f, f2);
            }
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void touchSurfaceUp() {
        if (this.mChangePosition) {
            return;
        }
        super.touchSurfaceUp();
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void hideAllWidget() {
        if (this.isFirstPrepared) {
            return;
        }
        super.hideAllWidget();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void setProgressAndTime(long j, long j2, long j3, long j4, boolean z) {
        super.setProgressAndTime(j, j2, j3, j4, z);
        TextView textView = this.mADTime;
        if (textView == null || j3 <= 0) {
            return;
        }
        textView.setText("" + ((j4 / 1000) - (j3 / 1000)));
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer
    protected void cloneParams(GSYBaseVideoPlayer gSYBaseVideoPlayer, GSYBaseVideoPlayer gSYBaseVideoPlayer2) {
        super.cloneParams(gSYBaseVideoPlayer, gSYBaseVideoPlayer2);
        GSYADVideoPlayer gSYADVideoPlayer = (GSYADVideoPlayer) gSYBaseVideoPlayer2;
        gSYADVideoPlayer.isFirstPrepared = ((GSYADVideoPlayer) gSYBaseVideoPlayer).isFirstPrepared;
        gSYADVideoPlayer.changeAdUIState();
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    public void release() {
        super.release();
        TextView textView = this.mADTime;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    protected void changeAdUIState() {
        View view = this.mJumpAd;
        if (view != null) {
            view.setVisibility(this.isFirstPrepared ? 0 : 8);
        }
        TextView textView = this.mADTime;
        if (textView != null) {
            textView.setVisibility(this.isFirstPrepared ? 0 : 8);
        }
        if (this.mBottomContainer != null) {
            this.mBottomContainer.setBackgroundColor(this.isFirstPrepared ? 0 : getContext().getResources().getColor(R.color.bottom_container_bg));
        }
        if (this.mCurrentTimeTextView != null) {
            this.mCurrentTimeTextView.setVisibility(this.isFirstPrepared ? 4 : 0);
        }
        if (this.mTotalTimeTextView != null) {
            this.mTotalTimeTextView.setVisibility(this.isFirstPrepared ? 4 : 0);
        }
        if (this.mProgressBar != null) {
            this.mProgressBar.setVisibility(this.isFirstPrepared ? 4 : 0);
            this.mProgressBar.setEnabled(!this.isFirstPrepared);
        }
    }

    public void removeFullWindowViewOnly() {
        ViewGroup viewGroup = (ViewGroup) CommonUtil.scanForActivity(getContext()).findViewById(android.R.id.content);
        View viewFindViewById = viewGroup.findViewById(getFullId());
        if (viewFindViewById != null && viewFindViewById.getParent() != null) {
            viewGroup.removeView((ViewGroup) viewFindViewById.getParent());
        }
        this.mIfCurrentIsFullscreen = false;
    }
}
