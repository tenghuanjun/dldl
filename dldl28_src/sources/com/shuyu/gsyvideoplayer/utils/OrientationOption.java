package com.shuyu.gsyvideoplayer.utils;

import com.volcengine.cloudcore.common.mode.KeyBoardKey;

/* JADX INFO: loaded from: classes3.dex */
public class OrientationOption {
    private int mNormalLandAngleStart = KeyBoardKey.KeyboardKeyIcoClear;
    private int mNormalLandAngleEnd = 310;
    private int mNormalPortraitAngleStart = 30;
    private int mNormalPortraitAngleEnd = 330;
    private int mReverseLandAngleStart = 30;
    private int mReverseLandAngleEnd = 95;

    public int getNormalLandAngleStart() {
        return this.mNormalLandAngleStart;
    }

    public void setNormalLandAngleStart(int i) {
        this.mNormalLandAngleStart = i;
    }

    public int getNormalLandAngleEnd() {
        return this.mNormalLandAngleEnd;
    }

    public void setNormalLandAngleEnd(int i) {
        this.mNormalLandAngleEnd = i;
    }

    public int getNormalPortraitAngleStart() {
        return this.mNormalPortraitAngleStart;
    }

    public void setNormalPortraitAngleStart(int i) {
        this.mNormalPortraitAngleStart = i;
    }

    public int getNormalPortraitAngleEnd() {
        return this.mNormalPortraitAngleEnd;
    }

    public void setNormalPortraitAngleEnd(int i) {
        this.mNormalPortraitAngleEnd = i;
    }

    public int getReverseLandAngleStart() {
        return this.mReverseLandAngleStart;
    }

    public void setReverseLandAngleStart(int i) {
        this.mReverseLandAngleStart = i;
    }

    public int getReverseLandAngleEnd() {
        return this.mReverseLandAngleEnd;
    }

    public void setReverseLandAngleEnd(int i) {
        this.mReverseLandAngleEnd = i;
    }
}
