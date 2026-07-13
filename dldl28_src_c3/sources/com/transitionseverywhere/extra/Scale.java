package com.transitionseverywhere.extra;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import androidx.transition.TransitionListenerAdapter;
import androidx.transition.TransitionValues;
import androidx.transition.Visibility;
import com.transitionseverywhere.R;
import com.transitionseverywhere.utils.TransitionUtils;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class Scale extends Visibility {
    static final String PROPNAME_SCALE_X = "scale:scaleX";
    static final String PROPNAME_SCALE_Y = "scale:scaleY";
    private float mDisappearedScale;

    public Scale() {
        this.mDisappearedScale = 0.0f;
    }

    public Scale(float f) {
        this.mDisappearedScale = 0.0f;
        setDisappearedScale(f);
    }

    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        transitionValues.values.put(PROPNAME_SCALE_X, Float.valueOf(transitionValues.view.getScaleX()));
        transitionValues.values.put(PROPNAME_SCALE_Y, Float.valueOf(transitionValues.view.getScaleY()));
    }

    public Scale setDisappearedScale(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("disappearedScale cannot be negative!");
        }
        this.mDisappearedScale = f;
        return this;
    }

    public Scale(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDisappearedScale = 0.0f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Scale);
        setDisappearedScale(typedArrayObtainStyledAttributes.getFloat(R.styleable.Scale_disappearedScale, this.mDisappearedScale));
        typedArrayObtainStyledAttributes.recycle();
    }

    private Animator createAnimation(final View view, float f, float f2, TransitionValues transitionValues) {
        final float scaleX = view.getScaleX();
        final float scaleY = view.getScaleY();
        float fFloatValue = scaleX * f;
        float f3 = scaleX * f2;
        float fFloatValue2 = f * scaleY;
        float f4 = f2 * scaleY;
        if (transitionValues != null) {
            Float f5 = (Float) transitionValues.values.get(PROPNAME_SCALE_X);
            Float f6 = (Float) transitionValues.values.get(PROPNAME_SCALE_Y);
            if (f5 != null && f5.floatValue() != scaleX) {
                fFloatValue = f5.floatValue();
            }
            if (f6 != null && f6.floatValue() != scaleY) {
                fFloatValue2 = f6.floatValue();
            }
        }
        view.setScaleX(fFloatValue);
        view.setScaleY(fFloatValue2);
        Animator animatorMergeAnimators = TransitionUtils.mergeAnimators(ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_X, fFloatValue, f3), ObjectAnimator.ofFloat(view, (Property<View, Float>) View.SCALE_Y, fFloatValue2, f4));
        addListener(new TransitionListenerAdapter() { // from class: com.transitionseverywhere.extra.Scale.1
            public void onTransitionEnd(Transition transition) {
                view.setScaleX(scaleX);
                view.setScaleY(scaleY);
                transition.removeListener(this);
            }
        });
        return animatorMergeAnimators;
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return createAnimation(view, this.mDisappearedScale, 1.0f, transitionValues);
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return createAnimation(view, 1.0f, this.mDisappearedScale, transitionValues);
    }
}
