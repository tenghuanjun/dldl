package com.huya.force.export.imagefilter;

import com.huya.force.common.VideoFrameData;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseImageFilter {
    protected boolean mEnableBeauty = false;
    protected boolean mEnableThinFace = false;
    protected boolean mEnableWaterMark = false;
    protected Listener mListener;

    public interface Listener {
        void onImageFilterResult(VideoFrameData videoFrameData);
    }

    public enum Type {
        NONE,
        NATURAL,
        SUMMER,
        SWEETNESS,
        FRESH,
        DAWN,
        GLAMOROUS,
        WARMSUN,
        HOLIDAY,
        FLOWER_SEA,
        SOFT_LIGHT,
        WARM_AIR
    }

    public ImageFilterInput getInput() {
        return null;
    }

    public abstract void put(VideoFrameData videoFrameData);

    public abstract void setBeautyDermabrasion(float f);

    public abstract void setBeautyWhite(float f);

    public abstract void setChinScale(float f);

    public abstract void setEyeScale(float f);

    public abstract void setFaceKeyPoints(float[] fArr);

    public abstract void setFaceScale(float f);

    public abstract void setShaveScale(float f);

    public abstract void setThinScale(float f);

    public abstract void setWatermark(List<Watermark> list);

    public abstract void start();

    public abstract void stop();

    public abstract void switchBeauty(Type type);

    public BaseImageFilter(ImageFilterInput imageFilterInput) {
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void enableBeautyFilter(boolean z) {
        this.mEnableBeauty = z;
    }

    public void enableThinFace(boolean z) {
        this.mEnableThinFace = z;
    }

    public void enableWaterMark(boolean z) {
        this.mEnableWaterMark = z;
    }

    public void enableAll() {
        this.mEnableBeauty = true;
        this.mEnableThinFace = true;
        this.mEnableWaterMark = true;
    }

    public void disableAll() {
        this.mEnableBeauty = false;
        this.mEnableThinFace = false;
        this.mEnableWaterMark = false;
    }
}
