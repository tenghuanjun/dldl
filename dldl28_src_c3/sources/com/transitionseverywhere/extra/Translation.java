package com.transitionseverywhere.extra;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import androidx.transition.TransitionValues;
import com.transitionseverywhere.utils.TransitionUtils;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class Translation extends Transition {
    private static final String TRANSLATION_X = "Translation:translationX";
    private static final String TRANSLATION_Y = "Translation:translationY";
    private static final String[] sTransitionProperties = {TRANSLATION_X, TRANSLATION_Y};
    private static final Property<View, PointF> TRANSLATION_PROPERTY = new Property<View, PointF>(PointF.class, "translation") { // from class: com.transitionseverywhere.extra.Translation.1
        @Override // android.util.Property
        public void set(View view, PointF pointF) {
            view.setTranslationX(pointF.x);
            view.setTranslationY(pointF.y);
        }

        @Override // android.util.Property
        public PointF get(View view) {
            return new PointF(view.getTranslationX(), view.getTranslationY());
        }
    };

    public Translation() {
    }

    public Translation(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    private void captureValues(TransitionValues transitionValues) {
        transitionValues.values.put(TRANSLATION_X, Float.valueOf(transitionValues.view.getTranslationX()));
        transitionValues.values.put(TRANSLATION_Y, Float.valueOf(transitionValues.view.getTranslationY()));
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        float fFloatValue = ((Float) transitionValues.values.get(TRANSLATION_X)).floatValue();
        float fFloatValue2 = ((Float) transitionValues.values.get(TRANSLATION_Y)).floatValue();
        float fFloatValue3 = ((Float) transitionValues2.values.get(TRANSLATION_X)).floatValue();
        float fFloatValue4 = ((Float) transitionValues2.values.get(TRANSLATION_Y)).floatValue();
        transitionValues2.view.setTranslationX(fFloatValue);
        transitionValues2.view.setTranslationY(fFloatValue2);
        Property<View, PointF> property = TRANSLATION_PROPERTY;
        if (property != null) {
            return ObjectAnimator.ofObject(transitionValues2.view, (Property<View, V>) property, (TypeConverter) null, getPathMotion().getPath(fFloatValue, fFloatValue2, fFloatValue3, fFloatValue4));
        }
        return TransitionUtils.mergeAnimators(fFloatValue == fFloatValue3 ? null : ObjectAnimator.ofFloat(transitionValues2.view, (Property<View, Float>) View.TRANSLATION_X, fFloatValue, fFloatValue3), fFloatValue2 != fFloatValue4 ? ObjectAnimator.ofFloat(transitionValues2.view, (Property<View, Float>) View.TRANSLATION_Y, fFloatValue2, fFloatValue4) : null);
    }
}
