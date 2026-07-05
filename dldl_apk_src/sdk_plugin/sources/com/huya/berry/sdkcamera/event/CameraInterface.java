package com.huya.berry.sdkcamera.event;

import android.view.SurfaceView;
import com.duowan.auk.NoProguard;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class CameraInterface implements NoProguard {

    public static class onBackPress implements NoProguard {
    }

    public static class onPause implements NoProguard {
    }

    public static class onResume implements NoProguard {
    }

    public static class onSurfaceDestroyed implements NoProguard {
    }

    public static class onSwitchCamera implements NoProguard {
    }

    public static class GetPreviewSurface implements NoProguard {
        public PreviewParams previewParams;

        public GetPreviewSurface(SurfaceView surfaceView, int i, int i2) {
            this.previewParams = new PreviewParams(surfaceView, i, i2);
        }
    }

    public static class onResumePreview {
        public PreviewParams previewParams;

        public onResumePreview(SurfaceView surfaceView, int i, int i2) {
            this.previewParams = new PreviewParams(surfaceView, i, i2);
        }
    }

    public static class PreviewParams implements NoProguard {
        public int previewHeight;
        public SurfaceView previewSurface;
        public int previewWidth;

        public PreviewParams(SurfaceView surfaceView, int i, int i2) {
            this.previewSurface = surfaceView;
            this.previewWidth = i;
            this.previewHeight = i2;
        }
    }

    public static class OnUpdatePreviewSize implements NoProguard {
        public int previewHeight;
        public int previewWidth;

        public OnUpdatePreviewSize(int i, int i2) {
            this.previewWidth = i;
            this.previewHeight = i2;
        }
    }
}
