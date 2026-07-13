package com.cy.yyjia.zhe28.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class WancmsStandardPlayer extends StandardGSYVideoPlayer {
    private ImageView ivVoice;

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    public int getLayoutId() {
        return 2131558866;
    }

    public WancmsStandardPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public WancmsStandardPlayer(Context context) {
        super(context);
    }

    public WancmsStandardPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer, com.shuyu.gsyvideoplayer.video.base.GSYVideoControlView, com.shuyu.gsyvideoplayer.video.base.GSYVideoView
    protected void init(Context context) {
        super.init(context);
        ImageView imageView = (ImageView) findViewById(2131362210);
        this.ivVoice = imageView;
        imageView.setSelected(false);
        this.ivVoice.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.view.WancmsStandardPlayer$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$init$0(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0(View view) {
        this.ivVoice.setSelected(!r2.isSelected());
        setVoice(this.ivVoice.isSelected());
    }

    @Override // com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
    protected void updateStartImage() {
        if (TextUtils.isEmpty(this.mOriginUrl)) {
            this.mStartButton.setVisibility(8);
            return;
        }
        ImageView imageView = (ImageView) this.mStartButton;
        if (this.mCurrentState == 2) {
            imageView.setImageResource(2131624378);
        } else if (this.mCurrentState == 7) {
            imageView.setImageResource(2131230985);
        } else {
            imageView.setImageResource(2131624379);
        }
    }

    public void setVoice(boolean isVoice) {
        GSYVideoManager.instance().setNeedMute(!isVoice);
    }

    public boolean setUp(String url, String pic) {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setAdjustViewBounds(true);
        Glide.with(getContext()).load(pic).into(imageView);
        setThumbImageView(imageView);
        return setUp(url, true, "");
    }
}
