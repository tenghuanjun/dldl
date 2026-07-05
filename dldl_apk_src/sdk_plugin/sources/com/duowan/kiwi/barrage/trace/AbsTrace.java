package com.duowan.kiwi.barrage.trace;

import com.duowan.kiwi.barrage.render.IRenderConfig;
import com.duowan.kiwi.barrage.render.area.AnimationListenerImpl;
import com.duowan.kiwi.barrage.render.area.OnAnimationListener;
import com.duowan.kiwi.barrage.render.draw.BulletBuilder;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class AbsTrace {
    private static final int END = 1;
    public static final int INFINITE = -1;
    public static final int RESTART = 0;
    public static final int REVERSE = 1;
    private static final int START = 0;
    public int mExplosive;
    public OnAnimationListener mListener;
    public String mNickName;
    public String mText;
    public float mDuration = 0.0f;
    public float mCurrentTime = 0.0f;
    public float mBeginTime = 0.0f;
    public int mRepeated = 0;
    public int mRepeatCount = 0;
    public int mRepeatModel = 0;
    public int mTextureId = -1;
    protected boolean mUseBitmap = false;
    public final float[][] mHolds = (float[][]) Array.newInstance((Class<?>) float.class, 5, 2);
    public float[] mSpeeds = new float[5];
    public TraceFrame mCurrentFrame = new TraceFrame();
    public long mUid = 0;
    public long mCurrentTimeMillis = System.currentTimeMillis();
    public int mWidth = 0;
    public int mHeight = 0;
    public int mLineIndex = 0;
    public boolean mHasFollower = false;
    public int mTarget = 1;

    public abstract void recycle();

    public abstract AbsTrace x(float f, float f2);

    public abstract AbsTrace y(float f, float f2);

    public String toString() {
        return String.format("[%s,%f,%f]", this.mText, Float.valueOf(this.mDuration), Float.valueOf(this.mCurrentTime));
    }

    public AbsTrace() {
        float[][] fArr = this.mHolds;
        fArr[2][0] = 1.0f;
        fArr[2][1] = 1.0f;
        fArr[3][0] = 1.0f;
        fArr[3][1] = 1.0f;
        fArr[4][0] = 1.0f;
        fArr[4][1] = 1.0f;
    }

    public boolean isUseBitmap() {
        return this.mUseBitmap;
    }

    public TraceFrame getCurrentFrame() {
        return this.mCurrentFrame;
    }

    public float getDuration() {
        return this.mDuration;
    }

    public float getCurrentTime() {
        return this.mCurrentTime;
    }

    public AbsTrace alpha(float f, float f2) {
        float[][] fArr = this.mHolds;
        fArr[2][0] = f;
        fArr[2][1] = f2;
        return this;
    }

    public AbsTrace scaleX(float f, float f2) {
        float[][] fArr = this.mHolds;
        fArr[3][0] = f;
        fArr[3][1] = f2;
        return this;
    }

    public AbsTrace scaleY(float f, float f2) {
        float[][] fArr = this.mHolds;
        fArr[4][0] = f;
        fArr[4][1] = f2;
        return this;
    }

    public AbsTrace duration(float f) {
        this.mDuration = f;
        return this;
    }

    public AbsTrace beginTime(float f) {
        this.mBeginTime = f;
        return this;
    }

    public AbsTrace setListener(OnAnimationListener onAnimationListener) {
        this.mListener = onAnimationListener;
        if (onAnimationListener instanceof AnimationListenerImpl) {
            ((AnimationListenerImpl) onAnimationListener).setTarget(this);
        }
        return this;
    }

    public AbsTrace setRepeatCount(int i) {
        this.mRepeatCount = i;
        return this;
    }

    public AbsTrace setRepeatModel(int i) {
        this.mRepeatModel = i;
        return this;
    }

    public void start(IRenderConfig iRenderConfig) {
        init();
        iRenderConfig.addAnimation(this);
    }

    public void init() {
        float[][] fArr = this.mHolds;
        float[] fArr2 = fArr[0];
        float[] fArr3 = fArr[1];
        float[] fArr4 = fArr[2];
        float[] fArr5 = fArr[3];
        float[] fArr6 = fArr[4];
        float[] fArr7 = this.mSpeeds;
        float f = fArr2[1] - fArr2[0];
        float f2 = this.mDuration;
        fArr7[0] = f / f2;
        fArr7[1] = (fArr3[1] - fArr3[0]) / f2;
        fArr7[2] = (fArr4[1] - fArr4[0]) / f2;
        fArr7[3] = (fArr5[1] - fArr5[0]) / f2;
        fArr7[4] = (fArr6[1] - fArr6[0]) / f2;
        this.mCurrentFrame.mX = fArr2[0];
        this.mCurrentFrame.mY = fArr3[0];
        this.mCurrentFrame.mAlpha = fArr4[0];
        this.mCurrentFrame.mScaleX = fArr5[0];
        this.mCurrentFrame.mScaleY = fArr6[0];
        this.mCurrentTime = 0.0f;
    }

    public void stepCurrentFrame(int i, float f) {
        if (i == 0) {
            this.mCurrentFrame.mX += f;
            return;
        }
        if (i == 1) {
            this.mCurrentFrame.mY += f;
            return;
        }
        if (i == 2) {
            this.mCurrentFrame.mAlpha += f;
        } else if (i == 3) {
            this.mCurrentFrame.mScaleX += f;
        } else {
            if (i != 4) {
                return;
            }
            this.mCurrentFrame.mScaleY += f;
        }
    }

    public void setAlpha(float f) {
        this.mCurrentFrame.mAlpha = f;
    }

    protected void exploreBullet(BulletBuilder.Bullet bullet) {
        this.mUid = bullet.getUid();
        this.mNickName = bullet.getNickName();
        this.mText = bullet.getText();
        this.mExplosive = bullet.mExplosive;
        this.mWidth = bullet.getPixelsWidth();
        this.mHeight = bullet.getPixelsHeight();
        this.mCurrentTimeMillis = System.currentTimeMillis();
        this.mHasFollower = false;
    }

    public int getTarget() {
        return this.mTarget;
    }
}
