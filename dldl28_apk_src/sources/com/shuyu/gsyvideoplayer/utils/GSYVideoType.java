package com.shuyu.gsyvideoplayer.utils;

/* JADX INFO: loaded from: classes3.dex */
public class GSYVideoType {
    public static final int GLSURFACE = 2;
    private static boolean MEDIA_CODEC_FLAG = false;
    public static final int SCREEN_MATCH_FULL = -4;
    public static final int SCREEN_TYPE_16_9 = 1;
    public static final int SCREEN_TYPE_18_9 = 6;
    public static final int SCREEN_TYPE_4_3 = 2;
    public static final int SCREEN_TYPE_CUSTOM = -5;
    public static final int SCREEN_TYPE_DEFAULT = 0;
    public static final int SCREEN_TYPE_FULL = 4;
    public static final int SUFRACE = 1;
    public static final int TEXTURE = 0;
    private static int TYPE = 0;
    private static int sRenderType = 0;
    private static float sScreenScaleRatio = 0.0f;
    private static boolean sTextureMediaPlay = false;

    public static void enableMediaCodec() {
        MEDIA_CODEC_FLAG = true;
    }

    public static void disableMediaCodec() {
        MEDIA_CODEC_FLAG = false;
    }

    public static void enableMediaCodecTexture() {
        sTextureMediaPlay = true;
    }

    public static void disableMediaCodecTexture() {
        sTextureMediaPlay = false;
    }

    public static boolean isMediaCodec() {
        return MEDIA_CODEC_FLAG;
    }

    public static boolean isMediaCodecTexture() {
        return sTextureMediaPlay;
    }

    public static int getShowType() {
        return TYPE;
    }

    public static void setShowType(int i) {
        TYPE = i;
    }

    public static int getRenderType() {
        return sRenderType;
    }

    public static void setRenderType(int i) {
        sRenderType = i;
    }

    public static float getScreenScaleRatio() {
        return sScreenScaleRatio;
    }

    public static void setScreenScaleRatio(float f) {
        sScreenScaleRatio = f;
    }
}
