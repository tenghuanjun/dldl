package com.huya.berry.sdkplayer.floats.view;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.util.AttributeSet;
import android.widget.VideoView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyberryVideoView extends VideoView {
    private int mVideoHeight;
    private int mVideoWidth;
    private int videoRealH;
    private int videoRealW;

    public HyberryVideoView(Context context) {
        super(context.getApplicationContext());
        this.mVideoWidth = 480;
        this.mVideoHeight = 480;
        this.videoRealW = 1;
        this.videoRealH = 1;
    }

    public HyberryVideoView(Context context, AttributeSet attributeSet) {
        super(context.getApplicationContext(), attributeSet);
        this.mVideoWidth = 480;
        this.mVideoHeight = 480;
        this.videoRealW = 1;
        this.videoRealH = 1;
    }

    public HyberryVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context.getApplicationContext(), attributeSet, i);
        this.mVideoWidth = 480;
        this.mVideoHeight = 480;
        this.videoRealW = 1;
        this.videoRealH = 1;
    }

    public HyberryVideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context.getApplicationContext(), attributeSet, i, i2);
        this.mVideoWidth = 480;
        this.mVideoHeight = 480;
        this.videoRealW = 1;
        this.videoRealH = 1;
    }

    @Override // android.widget.VideoView
    public void setVideoPath(String str) {
        super.setVideoPath(str);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        String strExtractMetadata = mediaMetadataRetriever.extractMetadata(19);
        String strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(18);
        try {
            this.videoRealH = Integer.parseInt(strExtractMetadata);
            this.videoRealW = Integer.parseInt(strExtractMetadata2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
    }
}
