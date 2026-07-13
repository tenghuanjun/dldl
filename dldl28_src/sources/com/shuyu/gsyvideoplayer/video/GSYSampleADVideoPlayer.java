package com.shuyu.gsyvideoplayer.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.shuyu.gsyvideoplayer.R;
import com.shuyu.gsyvideoplayer.model.GSYVideoModel;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class GSYSampleADVideoPlayer extends ListGSYVideoPlayer {
    protected boolean isAdModel;
    protected boolean isFirstPrepared;
    protected TextView mADTime;
    protected View mJumpAd;
    protected ViewGroup mWidgetContainer;

    public GSYSampleADVideoPlayer(Context context, Boolean bool) {
        super(context, bool);
        this.isAdModel = false;
        this.isFirstPrepared = false;
    }

    public GSYSampleADVideoPlayer(Context context) {
        super(context);
        this.isAdModel = false;
        this.isFirstPrepared = false;
    }

    public GSYSampleADVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isAdModel = false;
        this.isFirstPrepared = false;
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    protected void init(Context context) {
        super.init(context);
        this.mJumpAd = findViewById(R.id.jump_ad);
        this.mADTime = (TextView) findViewById(R.id.ad_time);
        this.mWidgetContainer = (ViewGroup) findViewById(R.id.widget_container);
        View view = this.mJumpAd;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.shuyu.gsyvideoplayer.video.GSYSampleADVideoPlayer.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    GSYSampleADVideoPlayer.this.playNext();
                }
            });
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    public int getLayoutId() {
        return R.layout.video_layout_sample_ad;
    }

    @Override // com.shuyu.gsyvideoplayer.video.ListGSYVideoPlayer
    public boolean setUp(List<GSYVideoModel> list, boolean z, int i) {
        return setUp(list, z, i, (File) null);
    }

    @Override // com.shuyu.gsyvideoplayer.video.ListGSYVideoPlayer
    public boolean setUp(List<GSYVideoModel> list, boolean z, int i, File file) {
        return setUp(list, z, i, file, new HashMap());
    }

    @Override // com.shuyu.gsyvideoplayer.video.ListGSYVideoPlayer
    public boolean setUp(List<GSYVideoModel> list, boolean z, int i, File file, Map<String, String> map) {
        return setUp(list, z, i, file, map, true);
    }

    @Override // com.shuyu.gsyvideoplayer.video.ListGSYVideoPlayer
    protected boolean setUp(List<GSYVideoModel> list, boolean z, int i, File file, Map<String, String> map, boolean z2) {
        GSYVideoModel gSYVideoModel = list.get(i);
        if (gSYVideoModel instanceof GSYADVideoModel) {
            GSYADVideoModel gSYADVideoModel = (GSYADVideoModel) gSYVideoModel;
            if (gSYADVideoModel.isSkip() && i < list.size() - 1) {
                return setUp(list, z, i + 1, file, map, z2);
            }
            this.isAdModel = gSYADVideoModel.getType() == GSYADVideoModel.TYPE_AD;
        }
        changeAdUIState();
        return super.setUp(list, z, i, file, map, z2);
    }

    @Override // com.shuyu.gsyvideoplayer.video.ListGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView, com.shuyu.gsyvideoplayer.video.base.GSYVideoView, com.shuyu.gsyvideoplayer.listener.GSYMediaPlayerListener
    public void onPrepared() {
        super.onPrepared();
        this.isFirstPrepared = true;
        changeAdUIState();
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
    protected void updateStartImage() {
        if (this.mStartButton == null || !(this.mStartButton instanceof ImageView)) {
            return;
        }
        ImageView imageView = (ImageView) this.mStartButton;
        if (this.mCurrentState == 2) {
            imageView.setImageResource(R.drawable.video_click_pause_selector);
        } else if (this.mCurrentState == 7) {
            imageView.setImageResource(R.drawable.video_click_play_selector);
        } else {
            imageView.setImageResource(R.drawable.video_click_play_selector);
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void touchDoubleUp(MotionEvent motionEvent) {
        if (this.isAdModel) {
            return;
        }
        super.touchDoubleUp(motionEvent);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void touchSurfaceMove(float f, float f2, float f3) {
        if (this.mChangePosition && this.isAdModel) {
            return;
        }
        super.touchSurfaceMove(f, f2, f3);
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void touchSurfaceMoveFullLogic(float f, float f2) {
        if (f > this.mThreshold || f2 > this.mThreshold) {
            int screenWidth = CommonUtil.getScreenWidth(getContext());
            if (this.isAdModel && f >= this.mThreshold && Math.abs(screenWidth - this.mDownX) > this.mSeekEndOffset) {
                this.mChangePosition = true;
                this.mDownPosition = getCurrentPositionWhenPlaying();
            } else {
                super.touchSurfaceMoveFullLogic(f, f2);
            }
        }
    }

    @Override // com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void touchSurfaceUp() {
        if (this.mChangePosition && this.isAdModel) {
            return;
        }
        super.touchSurfaceUp();
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView
    protected void hideAllWidget() {
        if (this.isFirstPrepared && this.isAdModel) {
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

    @Override // com.shuyu.gsyvideoplayer.video.ListGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer
    protected void cloneParams(GSYBaseVideoPlayer gSYBaseVideoPlayer, GSYBaseVideoPlayer gSYBaseVideoPlayer2) {
        super.cloneParams(gSYBaseVideoPlayer, gSYBaseVideoPlayer2);
        GSYSampleADVideoPlayer gSYSampleADVideoPlayer = (GSYSampleADVideoPlayer) gSYBaseVideoPlayer;
        GSYSampleADVideoPlayer gSYSampleADVideoPlayer2 = (GSYSampleADVideoPlayer) gSYBaseVideoPlayer2;
        gSYSampleADVideoPlayer2.isAdModel = gSYSampleADVideoPlayer.isAdModel;
        gSYSampleADVideoPlayer2.isFirstPrepared = gSYSampleADVideoPlayer.isFirstPrepared;
        gSYSampleADVideoPlayer2.changeAdUIState();
    }

    protected void changeAdUIState() {
        View view = this.mJumpAd;
        if (view != null) {
            view.setVisibility((this.isFirstPrepared && this.isAdModel) ? 0 : 8);
        }
        TextView textView = this.mADTime;
        if (textView != null) {
            textView.setVisibility((this.isFirstPrepared && this.isAdModel) ? 0 : 8);
        }
        ViewGroup viewGroup = this.mWidgetContainer;
        if (viewGroup != null) {
            viewGroup.setVisibility((this.isFirstPrepared && this.isAdModel) ? 8 : 0);
        }
        if (this.mBottomContainer != null) {
            this.mBottomContainer.setBackgroundColor((this.isFirstPrepared && this.isAdModel) ? 0 : getContext().getResources().getColor(R.color.bottom_container_bg));
        }
        if (this.mCurrentTimeTextView != null) {
            this.mCurrentTimeTextView.setVisibility((this.isFirstPrepared && this.isAdModel) ? 4 : 0);
        }
        if (this.mTotalTimeTextView != null) {
            this.mTotalTimeTextView.setVisibility((this.isFirstPrepared && this.isAdModel) ? 4 : 0);
        }
        if (this.mProgressBar != null) {
            this.mProgressBar.setVisibility((this.isFirstPrepared && this.isAdModel) ? 4 : 0);
            this.mProgressBar.setEnabled((this.isFirstPrepared && this.isAdModel) ? false : true);
        }
    }

    public boolean setAdUp(ArrayList<GSYADVideoModel> arrayList, boolean z, int i) {
        return setUp((ArrayList) arrayList.clone(), z, i);
    }

    public boolean setAdUp(ArrayList<GSYADVideoModel> arrayList, boolean z, int i, File file) {
        return setUp((ArrayList) arrayList.clone(), z, i, file);
    }

    public boolean setAdUp(ArrayList<GSYADVideoModel> arrayList, boolean z, int i, File file, Map<String, String> map) {
        return setUp((ArrayList) arrayList.clone(), z, i, file, map);
    }

    public static class GSYADVideoModel extends GSYVideoModel {
        public static int TYPE_AD = 1;
        public static int TYPE_NORMAL;
        private boolean isSkip;
        private int mType;

        public GSYADVideoModel(String str, String str2, int i) {
            this(str, str2, i, false);
        }

        public GSYADVideoModel(String str, String str2, int i, boolean z) {
            super(str, str2);
            this.mType = i;
            this.isSkip = z;
        }

        public int getType() {
            return this.mType;
        }

        public void setType(int i) {
            this.mType = i;
        }

        public boolean isSkip() {
            return this.isSkip;
        }

        public void setSkip(boolean z) {
            this.isSkip = z;
        }
    }
}
