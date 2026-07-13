package com.shuyu.gsyvideoplayer.utils;

import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public final class MeasureHelper {
    private int mCurrentAspectRatio = 0;
    private int mMeasuredHeight;
    private int mMeasuredWidth;
    private final MeasureFormVideoParamsListener mParamsListener;
    private int mVideoHeight;
    private int mVideoRotationDegree;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private int mVideoWidth;
    private WeakReference<View> mWeakView;

    public interface MeasureFormVideoParamsListener {
        int getCurrentVideoHeight();

        int getCurrentVideoWidth();

        int getVideoSarDen();

        int getVideoSarNum();
    }

    public MeasureHelper(View view, MeasureFormVideoParamsListener measureFormVideoParamsListener) {
        this.mParamsListener = measureFormVideoParamsListener;
        this.mWeakView = new WeakReference<>(view);
    }

    public View getView() {
        WeakReference<View> weakReference = this.mWeakView;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public void setVideoSize(int i, int i2) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
    }

    public void setVideoSampleAspectRatio(int i, int i2) {
        this.mVideoSarNum = i;
        this.mVideoSarDen = i2;
    }

    public void setVideoRotation(int i) {
        this.mVideoRotationDegree = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void doMeasure(int r14, int r15) {
        /*
            Method dump skipped, instruction units count: 338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shuyu.gsyvideoplayer.utils.MeasureHelper.doMeasure(int, int):void");
    }

    public void prepareMeasure(int i, int i2, int i3) {
        MeasureFormVideoParamsListener measureFormVideoParamsListener = this.mParamsListener;
        if (measureFormVideoParamsListener != null) {
            try {
                int currentVideoWidth = measureFormVideoParamsListener.getCurrentVideoWidth();
                int currentVideoHeight = this.mParamsListener.getCurrentVideoHeight();
                Debuger.printfLog("videoWidth: " + currentVideoWidth + " videoHeight: " + currentVideoHeight);
                int videoSarNum = this.mParamsListener.getVideoSarNum();
                int videoSarDen = this.mParamsListener.getVideoSarDen();
                if (currentVideoWidth > 0 && currentVideoHeight > 0) {
                    setVideoSampleAspectRatio(videoSarNum, videoSarDen);
                    setVideoSize(currentVideoWidth, currentVideoHeight);
                }
                setVideoRotation(i3);
                doMeasure(i, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public void setAspectRatio(int i) {
        this.mCurrentAspectRatio = i;
    }
}
