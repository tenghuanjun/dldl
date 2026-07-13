package com.transitionseverywhere;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Property;
import android.widget.TextView;
import androidx.transition.Transition;
import androidx.transition.TransitionValues;
import com.transitionseverywhere.utils.IntProperty;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class Recolor extends Transition {
    private static final String PROPNAME_BACKGROUND = "android:recolor:background";
    private static final String PROPNAME_TEXT_COLOR = "android:recolor:textColor";
    private static final String[] sTransitionProperties = {PROPNAME_BACKGROUND, PROPNAME_TEXT_COLOR};
    public static final Property<TextView, Integer> TEXTVIEW_TEXT_COLOR = new IntProperty<TextView>() { // from class: com.transitionseverywhere.Recolor.1
        @Override // com.transitionseverywhere.utils.IntProperty
        public void setValue(TextView textView, int i) {
            textView.setTextColor(i);
        }

        @Override // com.transitionseverywhere.utils.IntProperty, android.util.Property
        public Integer get(TextView textView) {
            return 0;
        }
    }.optimize();
    public static final Property<ColorDrawable, Integer> COLORDRAWABLE_COLOR = new IntProperty<ColorDrawable>() { // from class: com.transitionseverywhere.Recolor.2
        @Override // com.transitionseverywhere.utils.IntProperty
        public void setValue(ColorDrawable colorDrawable, int i) {
            colorDrawable.setColor(i);
        }

        @Override // com.transitionseverywhere.utils.IntProperty, android.util.Property
        public Integer get(ColorDrawable colorDrawable) {
            return Integer.valueOf(colorDrawable.getColor());
        }
    }.optimize();

    public Recolor() {
    }

    public Recolor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    private void captureValues(TransitionValues transitionValues) {
        transitionValues.values.put(PROPNAME_BACKGROUND, transitionValues.view.getBackground());
        if (transitionValues.view instanceof TextView) {
            transitionValues.values.put(PROPNAME_TEXT_COLOR, Integer.valueOf(((TextView) transitionValues.view).getCurrentTextColor()));
        }
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.animation.Animator createAnimator(android.view.ViewGroup r6, androidx.transition.TransitionValues r7, androidx.transition.TransitionValues r8) {
        /*
            r5 = this;
            r6 = 0
            if (r7 == 0) goto L95
            if (r8 != 0) goto L7
            goto L95
        L7:
            android.view.View r0 = r8.view
            java.util.Map r1 = r7.values
            java.lang.String r2 = "android:recolor:background"
            java.lang.Object r1 = r1.get(r2)
            android.graphics.drawable.Drawable r1 = (android.graphics.drawable.Drawable) r1
            java.util.Map r3 = r8.values
            java.lang.Object r2 = r3.get(r2)
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
            boolean r3 = r1 instanceof android.graphics.drawable.ColorDrawable
            if (r3 == 0) goto L59
            boolean r3 = r2 instanceof android.graphics.drawable.ColorDrawable
            if (r3 == 0) goto L59
            android.graphics.drawable.ColorDrawable r1 = (android.graphics.drawable.ColorDrawable) r1
            android.graphics.drawable.ColorDrawable r2 = (android.graphics.drawable.ColorDrawable) r2
            int r3 = r1.getColor()
            int r4 = r2.getColor()
            if (r3 == r4) goto L59
            int r3 = r2.getColor()
            android.graphics.drawable.Drawable r2 = r2.mutate()
            android.graphics.drawable.ColorDrawable r2 = (android.graphics.drawable.ColorDrawable) r2
            int r4 = r1.getColor()
            r2.setColor(r4)
            android.util.Property<android.graphics.drawable.ColorDrawable, java.lang.Integer> r4 = com.transitionseverywhere.Recolor.COLORDRAWABLE_COLOR
            int r1 = r1.getColor()
            int[] r1 = new int[]{r1, r3}
            android.animation.ObjectAnimator r1 = android.animation.ObjectAnimator.ofInt(r2, r4, r1)
            android.animation.ArgbEvaluator r2 = new android.animation.ArgbEvaluator
            r2.<init>()
            r1.setEvaluator(r2)
            goto L5a
        L59:
            r1 = r6
        L5a:
            boolean r2 = r0 instanceof android.widget.TextView
            if (r2 == 0) goto L91
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.util.Map r7 = r7.values
            java.lang.String r2 = "android:recolor:textColor"
            java.lang.Object r7 = r7.get(r2)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            java.util.Map r8 = r8.values
            java.lang.Object r8 = r8.get(r2)
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            if (r7 == r8) goto L91
            r0.setTextColor(r8)
            android.util.Property<android.widget.TextView, java.lang.Integer> r6 = com.transitionseverywhere.Recolor.TEXTVIEW_TEXT_COLOR
            int[] r7 = new int[]{r7, r8}
            android.animation.ObjectAnimator r6 = android.animation.ObjectAnimator.ofInt(r0, r6, r7)
            android.animation.ArgbEvaluator r7 = new android.animation.ArgbEvaluator
            r7.<init>()
            r6.setEvaluator(r7)
        L91:
            android.animation.Animator r6 = com.transitionseverywhere.utils.TransitionUtils.mergeAnimators(r1, r6)
        L95:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.transitionseverywhere.Recolor.createAnimator(android.view.ViewGroup, androidx.transition.TransitionValues, androidx.transition.TransitionValues):android.animation.Animator");
    }
}
