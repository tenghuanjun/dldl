package com.shuyu.gsyvideoplayer.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.shuyu.gsyvideoplayer.R;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class NormalGSYVideoPlayer extends StandardGSYVideoPlayer {
    public NormalGSYVideoPlayer(Context context, Boolean bool) {
        super(context, bool);
    }

    public NormalGSYVideoPlayer(Context context) {
        super(context);
    }

    public NormalGSYVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    public int getLayoutId() {
        return R.layout.video_layout_normal;
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
    protected void updateStartImage() {
        if (this.mStartButton instanceof ImageView) {
            ImageView imageView = (ImageView) this.mStartButton;
            if (this.mCurrentState == 2) {
                imageView.setImageResource(R.drawable.video_click_pause_selector);
            } else if (this.mCurrentState == 7) {
                imageView.setImageResource(R.drawable.video_click_play_selector);
            } else {
                imageView.setImageResource(R.drawable.video_click_play_selector);
            }
        }
    }
}
