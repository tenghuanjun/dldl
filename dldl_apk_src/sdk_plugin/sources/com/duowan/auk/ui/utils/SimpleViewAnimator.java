package com.duowan.auk.ui.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SimpleViewAnimator {
    private static final int ALPHA = 512;
    private static final String ALPHA_NAME = "alpha";
    public static final int BOTTOM = 4;
    public static final int LEFT = 2;
    public static final int RIGHT = 8;
    private static final int ROTATION = 16;
    private static final String ROTATION_NAME = "rotation";
    private static final int ROTATION_X = 32;
    private static final String ROTATION_X_NAME = "rotationX";
    private static final int ROTATION_Y = 64;
    private static final String ROTATION_Y_NAME = "rotationY";
    private static final int SCALE_X = 4;
    private static final String SCALE_X_NAME = "scaleX";
    private static final int SCALE_Y = 8;
    private static final String SCALE_Y_NAME = "scaleY";
    public static final int TOP = 1;
    private static final int TRANSLATION_X = 1;
    private static final String TRANSLATION_X_NAME = "translationX";
    private static final int TRANSLATION_Y = 2;
    private static final String TRANSLATION_Y_NAME = "translationY";
    private static final int X = 128;
    private static final String X_NAME = "x";
    private static final int Y = 256;
    private static final String Y_NAME = "y";
    private long mDuration;
    private TimeInterpolator mInterpolator;
    private final View mView;
    private boolean mDurationSet = false;
    private long mStartDelay = 0;
    private boolean mStartDelaySet = false;
    private boolean mInterpolatorSet = false;
    private Animator.AnimatorListener mAnimaorListener = null;
    private boolean mIsAppear = false;
    private boolean mIsDisappear = false;
    private ViewAnimatorListener mViewAnimatorListener = new ViewAnimatorListener();
    private ArrayList<PropertyValuesHolder> mPendingPropertyValuesHolders = new ArrayList<>();

    private SimpleViewAnimator(View view) {
        this.mView = view;
    }

    public static SimpleViewAnimator create(View view) {
        return new SimpleViewAnimator(view);
    }

    public SimpleViewAnimator expandAppear() {
        scaleX(0.0f, 1.0f);
        scaleY(0.0f, 1.0f);
        appear(true);
        return this;
    }

    public SimpleViewAnimator shrinkDisappear() {
        scaleX(0.0f);
        scaleY(0.0f);
        return this;
    }

    public SimpleViewAnimator translationAppear(int i) {
        if (i == 1) {
            translationY(-this.mView.getHeight(), 0.0f);
        } else if (i == 2) {
            translationX(-this.mView.getWidth(), 0.0f);
        } else if (i == 4) {
            translationY(this.mView.getHeight(), 0.0f);
        } else if (i == 8) {
            translationX(this.mView.getWidth(), 0.0f);
        }
        appear(true);
        return this;
    }

    public SimpleViewAnimator filpAppear(int i) {
        if (i == 1) {
            rotationX(-90.0f, 0.0f);
            translationY(((-this.mView.getHeight()) * 1) / 2, 0.0f);
        } else if (i == 2) {
            rotationY(90.0f, 0.0f);
            translationX(((-this.mView.getWidth()) * 1) / 2, 0.0f);
        } else if (i == 4) {
            rotationX(90.0f, 0.0f);
            translationY((this.mView.getHeight() * 1) / 2, 0.0f);
        } else if (i == 8) {
            rotationY(-90.0f, 0.0f);
            translationX((this.mView.getWidth() * 1) / 2, 0.0f);
        }
        appear(true);
        return this;
    }

    public SimpleViewAnimator shakeX(float f, int i) {
        float[] fArr = new float[i + 1];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = 1;
            if (i2 % 2 != 1) {
                i3 = -1;
            }
            fArr[i2] = i3 * this.mView.getHeight() * f;
        }
        fArr[i] = 0.0f;
        translationX(fArr);
        return this;
    }

    public SimpleViewAnimator shakeY(float f, int i) {
        float[] fArr = new float[i + 1];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = 1;
            if (i2 % 2 != 1) {
                i3 = -1;
            }
            fArr[i2] = i3 * this.mView.getWidth() * f;
        }
        fArr[i] = 0.0f;
        translationY(fArr);
        return this;
    }

    public SimpleViewAnimator alphaBy(float... fArr) {
        alpha(getAbsoluteValues(512, fArr));
        return this;
    }

    public SimpleViewAnimator alpha(float... fArr) {
        addPropertyValues("alpha", fArr);
        return this;
    }

    public SimpleViewAnimator rotationBy(float... fArr) {
        rotation(getAbsoluteValues(16, fArr));
        return this;
    }

    public SimpleViewAnimator rotation(float... fArr) {
        addPropertyValues(ROTATION_NAME, fArr);
        return this;
    }

    public SimpleViewAnimator rotationXBy(float... fArr) {
        rotationX(getAbsoluteValues(32, fArr));
        return this;
    }

    public SimpleViewAnimator rotationX(float... fArr) {
        addPropertyValues(ROTATION_X_NAME, fArr);
        return this;
    }

    public SimpleViewAnimator rotationYBy(float... fArr) {
        rotationY(getAbsoluteValues(64, fArr));
        return this;
    }

    public SimpleViewAnimator rotationY(float... fArr) {
        addPropertyValues(ROTATION_Y_NAME, fArr);
        return this;
    }

    public SimpleViewAnimator scaleXBy(float... fArr) {
        scaleX(getAbsoluteValues(4, fArr));
        return this;
    }

    public SimpleViewAnimator scaleX(float... fArr) {
        addPropertyValues(SCALE_X_NAME, fArr);
        return this;
    }

    public SimpleViewAnimator scaleYBy(float... fArr) {
        scaleY(getAbsoluteValues(8, fArr));
        return this;
    }

    public SimpleViewAnimator scaleY(float... fArr) {
        addPropertyValues(SCALE_Y_NAME, fArr);
        return this;
    }

    public SimpleViewAnimator translationXBy(float... fArr) {
        translationX(getAbsoluteValues(1, fArr));
        return this;
    }

    public SimpleViewAnimator translationX(float... fArr) {
        addPropertyValues(TRANSLATION_X_NAME, fArr);
        return this;
    }

    public SimpleViewAnimator translationYBy(float... fArr) {
        translationY(getAbsoluteValues(2, fArr));
        return this;
    }

    public SimpleViewAnimator translationY(float... fArr) {
        addPropertyValues(TRANSLATION_Y_NAME, fArr);
        return this;
    }

    public SimpleViewAnimator xBy(float... fArr) {
        x(getAbsoluteValues(128, fArr));
        return this;
    }

    public SimpleViewAnimator x(float... fArr) {
        addPropertyValues(X_NAME, fArr);
        return this;
    }

    public SimpleViewAnimator yBy(float... fArr) {
        y(getAbsoluteValues(256, fArr));
        return this;
    }

    public SimpleViewAnimator y(float... fArr) {
        addPropertyValues(Y_NAME, fArr);
        return this;
    }

    public SimpleViewAnimator setDuration(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.mDurationSet = true;
        this.mDuration = j;
        return this;
    }

    public long getDuration() {
        if (this.mDurationSet) {
            return this.mDuration;
        }
        return new ObjectAnimator().getDuration();
    }

    public long getStartDelay() {
        if (this.mStartDelaySet) {
            return this.mStartDelay;
        }
        return new ObjectAnimator().getStartDelay();
    }

    public SimpleViewAnimator setStartDelay(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.mStartDelaySet = true;
        this.mStartDelay = j;
        return this;
    }

    public SimpleViewAnimator setInterpolator(TimeInterpolator timeInterpolator) {
        this.mInterpolatorSet = true;
        this.mInterpolator = timeInterpolator;
        return this;
    }

    public SimpleViewAnimator setListener(Animator.AnimatorListener animatorListener) {
        this.mAnimaorListener = animatorListener;
        return this;
    }

    public SimpleViewAnimator appear(boolean z) {
        this.mIsAppear = z;
        return this;
    }

    public SimpleViewAnimator disappear(boolean z) {
        this.mIsDisappear = z;
        return this;
    }

    public void start() {
        startAnimator();
    }

    private void startAnimator() {
        View view = this.mView;
        ArrayList<PropertyValuesHolder> arrayList = this.mPendingPropertyValuesHolders;
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, (PropertyValuesHolder[]) arrayList.toArray(new PropertyValuesHolder[arrayList.size()]));
        objectAnimatorOfPropertyValuesHolder.addListener(this.mViewAnimatorListener);
        if (this.mStartDelaySet) {
            objectAnimatorOfPropertyValuesHolder.setStartDelay(this.mStartDelay);
        }
        if (this.mDurationSet) {
            objectAnimatorOfPropertyValuesHolder.setDuration(this.mDuration);
        }
        if (this.mInterpolatorSet) {
            objectAnimatorOfPropertyValuesHolder.setInterpolator(this.mInterpolator);
        }
        this.mView.clearAnimation();
        objectAnimatorOfPropertyValuesHolder.start();
    }

    private float[] getAbsoluteValues(int i, float... fArr) {
        int length = fArr.length;
        float[] fArr2 = new float[length];
        for (int i2 = 0; i2 < length; i2++) {
            fArr2[i2] = fArr[i2] + getValue(i);
        }
        return fArr2;
    }

    private void addPropertyValues(String str, float... fArr) {
        this.mPendingPropertyValuesHolders.add(PropertyValuesHolder.ofFloat(str, fArr));
    }

    private float getValue(int i) {
        if (i == 1) {
            return this.mView.getTranslationX();
        }
        if (i == 2) {
            return this.mView.getTranslationY();
        }
        if (i == 4) {
            return this.mView.getScaleX();
        }
        if (i == 8) {
            return this.mView.getScaleY();
        }
        if (i == 16) {
            return this.mView.getRotation();
        }
        if (i == 32) {
            return this.mView.getRotationX();
        }
        if (i == 64) {
            return this.mView.getRotationY();
        }
        if (i == 128) {
            return this.mView.getX();
        }
        if (i == 256) {
            return this.mView.getY();
        }
        if (i != 512) {
            return 0.0f;
        }
        return this.mView.getAlpha();
    }

    private class ViewAnimatorListener implements Animator.AnimatorListener {
        private ViewAnimatorListener() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (SimpleViewAnimator.this.mIsAppear) {
                SimpleViewAnimator.this.mView.setVisibility(0);
            }
            if (SimpleViewAnimator.this.mAnimaorListener != null) {
                SimpleViewAnimator.this.mAnimaorListener.onAnimationStart(animator);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (SimpleViewAnimator.this.mAnimaorListener != null) {
                SimpleViewAnimator.this.mAnimaorListener.onAnimationCancel(animator);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
            if (SimpleViewAnimator.this.mAnimaorListener != null) {
                SimpleViewAnimator.this.mAnimaorListener.onAnimationRepeat(animator);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (SimpleViewAnimator.this.mIsDisappear) {
                SimpleViewAnimator.this.mView.setVisibility(4);
            }
            if (SimpleViewAnimator.this.mAnimaorListener != null) {
                SimpleViewAnimator.this.mAnimaorListener.onAnimationEnd(animator);
            }
        }
    }
}
