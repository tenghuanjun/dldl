package com.huya.force.export.surface;

import android.hardware.Camera;
import android.view.Surface;
import com.huya.force.common.VideoFrameData;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class ISurface {
    protected Listener mListener;
    protected float[] mTransform = null;
    protected boolean mValid = true;

    public interface Listener {
        void makePreviewCurrent();

        void onFrameAvailable(VideoFrameData videoFrameData);
    }

    public enum SurfaceType {
        SURFACE_TEXTURE
    }

    public abstract Class getOutputClass();

    public abstract Surface getSurface();

    public abstract Object getSurfaceObject();

    public abstract void setCamera1Preview(Camera camera);

    public abstract void start(SurfaceConfig surfaceConfig);

    public abstract void stop();

    public abstract void updateSize(int i, int i2);

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void setTransform(float[] fArr) {
        this.mTransform = fArr;
    }

    public void setValid(boolean z) {
        this.mValid = z;
    }
}
