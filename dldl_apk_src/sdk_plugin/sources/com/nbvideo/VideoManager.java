package com.nbvideo;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VideoManager {
    private static VideoManager manager;
    private NBVideo video;

    public static VideoManager getInstance() {
        if (manager == null) {
            manager = new VideoManager();
        }
        return manager;
    }

    public void setCurrPlayVideo(NBVideo nBVideo) {
        this.video = nBVideo;
    }

    public NBVideo getCurrPlayVideo() {
        return this.video;
    }

    public void release() {
        NBVideo nBVideo = this.video;
        if (nBVideo != null) {
            nBVideo.releasePlayer();
            this.video = null;
        }
    }
}
