package com.volcengine.cloudphone.base;

import android.opengl.EGLContext;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class VeVideoFrame {
    public ByteBuffer buffer;
    public EGLContext eglContext14 = null;
    public ByteBuffer extentData;
    public int format;
    public int height;
    public int rotation;
    public int stride;
    public int textureId;
    public long timestamp;
    public float[] transform;
    public int width;

    @Retention(RetentionPolicy.CLASS)
    public @interface PixelFormat {
        public static final int I420 = 1;
        public static final int NV12 = 2;
        public static final int TEXTURE_OES = 11;
    }

    @Retention(RetentionPolicy.CLASS)
    public @interface VideoRotation {
        public static final int VIDEO_ROTATION_0 = 0;
        public static final int VIDEO_ROTATION_180 = 180;
        public static final int VIDEO_ROTATION_270 = 270;
        public static final int VIDEO_ROTATION_90 = 90;
    }
}
