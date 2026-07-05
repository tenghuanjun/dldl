package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface IDisplayer {
    public static final int DANMAKU_STYLE_DEFAULT = -1;
    public static final int DANMAKU_STYLE_NONE = 0;
    public static final int DANMAKU_STYLE_PROJECTION = 3;
    public static final int DANMAKU_STYLE_SHADOW = 1;
    public static final int DANMAKU_STYLE_STROKEN = 2;

    int draw(BaseDanmaku baseDanmaku);

    int getAllMarginTop();

    float getDensity();

    int getDensityDpi();

    int getHeight();

    int getMargin();

    int getMaximumCacheHeight();

    int getMaximumCacheWidth();

    float getScaledDensity();

    int getSlopPixel();

    float getStrokeWidth();

    int getWidth();

    boolean isHardwareAccelerated();

    void measure(BaseDanmaku baseDanmaku, boolean z);

    void prepare(BaseDanmaku baseDanmaku, boolean z);

    void recycle(BaseDanmaku baseDanmaku);

    void resetSlopPixel(float f);

    void setAllMarginTop(int i);

    void setDanmakuStyle(int i, float[] fArr);

    void setDensities(float f, int i, float f2);

    void setHardwareAccelerated(boolean z);

    void setMargin(int i);

    void setSize(int i, int i2);
}
