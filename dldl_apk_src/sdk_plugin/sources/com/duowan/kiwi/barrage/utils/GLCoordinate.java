package com.duowan.kiwi.barrage.utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class GLCoordinate {
    public static final float BOTTOM = -1.0f;
    public static final float EYE_Z = -1.0f;
    public static final float FAR = 2.0f;
    private static final float GL_UNIT;
    public static final float LEFT = -1.0f;
    public static final float NEAR = 1.0f;
    public static final float NEAR_Z = 0.0f;
    public static final float RIGHT = 1.0f;
    public static final float TOP = 1.0f;
    private static float WORLD_HEIGHT;
    private static float WORLD_UNIT;
    private static float WORLD_WIDTH;

    static {
        float fAbs = Math.abs(2.0f);
        GL_UNIT = fAbs;
        WORLD_UNIT = fAbs;
        WORLD_WIDTH = 1.0f;
        WORLD_HEIGHT = 1.0f;
    }

    public static void setWorldSize(int i, int i2) {
        WORLD_WIDTH = i;
        float f = i2;
        WORLD_HEIGHT = f;
        WORLD_UNIT = f;
    }

    public static float toGLUnit(float f) {
        return (f * GL_UNIT) / WORLD_UNIT;
    }

    public static float toGLPositionX(float f) {
        float f2 = GL_UNIT;
        float f3 = WORLD_WIDTH;
        float f4 = (f2 * f3) / WORLD_HEIGHT;
        return ((f * f4) / f3) - (f4 / 2.0f);
    }

    public static float toGLPositionY(float f) {
        float f2 = GL_UNIT;
        return ((f * f2) / WORLD_UNIT) - (f2 / 2.0f);
    }

    public static float toWorldPositionX(float f) {
        float f2 = GL_UNIT;
        float f3 = WORLD_WIDTH;
        float f4 = (f2 * f3) / WORLD_HEIGHT;
        return ((f + (f4 / 2.0f)) * f3) / f4;
    }

    public static float toWorldPositionY(float f) {
        float f2 = GL_UNIT;
        return ((f + (f2 / 2.0f)) * WORLD_UNIT) / f2;
    }

    public static float outSideWorldX() {
        return toGLPositionX(WORLD_WIDTH * 2.0f);
    }

    public static float outSideWorldY() {
        return toGLPositionY(WORLD_HEIGHT * 2.0f);
    }
}
