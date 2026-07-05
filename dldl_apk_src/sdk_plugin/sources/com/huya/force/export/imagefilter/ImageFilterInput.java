package com.huya.force.export.imagefilter;

import android.content.Context;
import android.os.Handler;
import com.huya.force.export.imagefilter.BaseImageFilter;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ImageFilterInput {
    BaseImageFilter.Type mBeautyType;
    Handler mEglHandler;
    int mEncodeHeight;
    int mEncodeWidth;
    int mPreviewHeight;
    int mPreviewWidth;
    List<Watermark> mWatermarks;
    WeakReference<Context> mWeakContext;

    public ImageFilterInput(Context context, int i, int i2, int i3, int i4, BaseImageFilter.Type type, List<Watermark> list) {
        this(context, i, i2, i3, i4, type, list, null);
    }

    public ImageFilterInput(Context context, int i, int i2, int i3, int i4, BaseImageFilter.Type type, List<Watermark> list, Handler handler) {
        this.mWeakContext = new WeakReference<>(context);
        this.mPreviewWidth = i;
        this.mPreviewHeight = i2;
        this.mEncodeWidth = i3;
        this.mEncodeHeight = i4;
        this.mBeautyType = type;
        this.mWatermarks = list;
        this.mEglHandler = handler;
    }

    public Context getContext() {
        return this.mWeakContext.get();
    }

    public int getPreviewWidth() {
        return this.mPreviewWidth;
    }

    public int getPreviewHeight() {
        return this.mPreviewHeight;
    }

    public int getEncodeWidth() {
        return this.mEncodeWidth;
    }

    public int getEncodeHeight() {
        return this.mEncodeHeight;
    }

    public BaseImageFilter.Type getBeautyType() {
        return this.mBeautyType;
    }

    public void setBeautyType(BaseImageFilter.Type type) {
        this.mBeautyType = type;
    }

    public List<Watermark> getWatermark() {
        return this.mWatermarks;
    }

    public void setWatermark(List<Watermark> list) {
        this.mWatermarks = list;
    }

    public void setEglHandler(Handler handler) {
        this.mEglHandler = handler;
    }

    public Handler getEglHandler() {
        return this.mEglHandler;
    }
}
