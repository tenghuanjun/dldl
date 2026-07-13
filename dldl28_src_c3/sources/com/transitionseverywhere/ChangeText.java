package com.transitionseverywhere;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.transition.Transition;
import androidx.transition.TransitionListenerAdapter;
import androidx.transition.TransitionValues;
import java.util.Map;
import kotlin.KotlinVersion;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ChangeText extends Transition {
    public static final int CHANGE_BEHAVIOR_IN = 2;
    public static final int CHANGE_BEHAVIOR_KEEP = 0;
    public static final int CHANGE_BEHAVIOR_OUT = 1;
    public static final int CHANGE_BEHAVIOR_OUT_IN = 3;
    private static final String PROPNAME_TEXT_COLOR = "android:textchange:textColor";
    private int mChangeBehavior = 0;
    private static final String PROPNAME_TEXT = "android:textchange:text";
    private static final String PROPNAME_TEXT_SELECTION_START = "android:textchange:textSelectionStart";
    private static final String PROPNAME_TEXT_SELECTION_END = "android:textchange:textSelectionEnd";
    private static final String[] sTransitionProperties = {PROPNAME_TEXT, PROPNAME_TEXT_SELECTION_START, PROPNAME_TEXT_SELECTION_END};

    public ChangeText setChangeBehavior(int i) {
        if (i >= 0 && i <= 3) {
            this.mChangeBehavior = i;
        }
        return this;
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public int getChangeBehavior() {
        return this.mChangeBehavior;
    }

    private void captureValues(TransitionValues transitionValues) {
        if (transitionValues.view instanceof TextView) {
            TextView textView = (TextView) transitionValues.view;
            transitionValues.values.put(PROPNAME_TEXT, textView.getText());
            if (textView instanceof EditText) {
                transitionValues.values.put(PROPNAME_TEXT_SELECTION_START, Integer.valueOf(textView.getSelectionStart()));
                transitionValues.values.put(PROPNAME_TEXT_SELECTION_END, Integer.valueOf(textView.getSelectionEnd()));
            }
            if (this.mChangeBehavior > 0) {
                transitionValues.values.put(PROPNAME_TEXT_COLOR, Integer.valueOf(textView.getCurrentTextColor()));
            }
        }
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int iIntValue;
        int i;
        int iIntValue2;
        int i2;
        int i3;
        CharSequence charSequence;
        TextView textView;
        int i4;
        char c;
        int i5;
        ValueAnimator valueAnimator;
        ValueAnimator valueAnimatorOfInt;
        final TextView textView2;
        Animator animator;
        final int i6;
        if (transitionValues == null || transitionValues2 == null || !(transitionValues.view instanceof TextView) || !(transitionValues2.view instanceof TextView)) {
            return null;
        }
        final TextView textView3 = (TextView) transitionValues2.view;
        Map map = transitionValues.values;
        Map map2 = transitionValues2.values;
        String str = map.get(PROPNAME_TEXT) != null ? (CharSequence) map.get(PROPNAME_TEXT) : "";
        String str2 = map2.get(PROPNAME_TEXT) != null ? (CharSequence) map2.get(PROPNAME_TEXT) : "";
        boolean z = textView3 instanceof EditText;
        if (z) {
            int iIntValue3 = map.get(PROPNAME_TEXT_SELECTION_START) != null ? ((Integer) map.get(PROPNAME_TEXT_SELECTION_START)).intValue() : -1;
            iIntValue = map.get(PROPNAME_TEXT_SELECTION_END) != null ? ((Integer) map.get(PROPNAME_TEXT_SELECTION_END)).intValue() : iIntValue3;
            int iIntValue4 = map2.get(PROPNAME_TEXT_SELECTION_START) != null ? ((Integer) map2.get(PROPNAME_TEXT_SELECTION_START)).intValue() : -1;
            i = iIntValue4;
            iIntValue2 = map2.get(PROPNAME_TEXT_SELECTION_END) != null ? ((Integer) map2.get(PROPNAME_TEXT_SELECTION_END)).intValue() : iIntValue4;
            i2 = iIntValue3;
        } else {
            iIntValue = -1;
            i = -1;
            iIntValue2 = -1;
            i2 = -1;
        }
        if (str.equals(str2)) {
            return null;
        }
        if (this.mChangeBehavior != 2) {
            textView3.setText(str);
            if (z) {
                setSelection((EditText) textView3, i2, iIntValue);
            }
        }
        if (this.mChangeBehavior == 0) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            final CharSequence charSequence2 = str;
            textView2 = textView3;
            final CharSequence charSequence3 = str2;
            i3 = i2;
            final int i7 = i;
            final int i8 = iIntValue2;
            valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.transitionseverywhere.ChangeText.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator2) {
                    if (charSequence2.equals(textView2.getText())) {
                        textView2.setText(charSequence3);
                        TextView textView4 = textView2;
                        if (textView4 instanceof EditText) {
                            ChangeText.this.setSelection((EditText) textView4, i7, i8);
                        }
                    }
                }
            });
            i5 = iIntValue;
            charSequence = str;
            i6 = 0;
            animator = valueAnimatorOfFloat;
        } else {
            i3 = i2;
            final int iIntValue5 = ((Integer) map.get(PROPNAME_TEXT_COLOR)).intValue();
            final int iIntValue6 = ((Integer) map2.get(PROPNAME_TEXT_COLOR)).intValue();
            int i9 = this.mChangeBehavior;
            if (i9 == 3 || i9 == 1) {
                ValueAnimator valueAnimatorOfInt2 = ValueAnimator.ofInt(Color.alpha(iIntValue5), 0);
                valueAnimatorOfInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.transitionseverywhere.ChangeText.2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                        int iIntValue7 = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                        TextView textView4 = textView3;
                        int i10 = iIntValue5;
                        textView4.setTextColor((iIntValue7 << 24) | (16711680 & i10) | (65280 & i10) | (i10 & KotlinVersion.MAX_COMPONENT_VALUE));
                    }
                });
                final CharSequence charSequence4 = str;
                charSequence = str;
                textView = textView3;
                i4 = 3;
                final CharSequence charSequence5 = str2;
                c = 1;
                final int i10 = i;
                final int i11 = iIntValue2;
                i5 = iIntValue;
                valueAnimatorOfInt2.addListener(new AnimatorListenerAdapter() { // from class: com.transitionseverywhere.ChangeText.3
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator2) {
                        if (charSequence4.equals(textView3.getText())) {
                            textView3.setText(charSequence5);
                            TextView textView4 = textView3;
                            if (textView4 instanceof EditText) {
                                ChangeText.this.setSelection((EditText) textView4, i10, i11);
                            }
                        }
                        textView3.setTextColor(iIntValue6);
                    }
                });
                valueAnimator = valueAnimatorOfInt2;
            } else {
                i5 = iIntValue;
                textView = textView3;
                charSequence = str;
                valueAnimator = null;
                i4 = 3;
                c = 1;
            }
            int i12 = this.mChangeBehavior;
            if (i12 == i4 || i12 == 2) {
                valueAnimatorOfInt = ValueAnimator.ofInt(0, Color.alpha(iIntValue6));
                textView2 = textView;
                valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.transitionseverywhere.ChangeText.4
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                        textView2.setTextColor((((Integer) valueAnimator2.getAnimatedValue()).intValue() << 24) | (Color.red(iIntValue6) << 16) | (Color.green(iIntValue6) << 8) | Color.blue(iIntValue6));
                    }
                });
                valueAnimatorOfInt.addListener(new AnimatorListenerAdapter() { // from class: com.transitionseverywhere.ChangeText.5
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationCancel(Animator animator2) {
                        textView2.setTextColor(iIntValue6);
                    }
                });
            } else {
                textView2 = textView;
                valueAnimatorOfInt = null;
            }
            if (valueAnimator == null || valueAnimatorOfInt == null) {
                animator = valueAnimator != null ? valueAnimator : valueAnimatorOfInt;
            } else {
                AnimatorSet animatorSet = new AnimatorSet();
                Animator[] animatorArr = new Animator[2];
                animatorArr[0] = valueAnimator;
                animatorArr[c] = valueAnimatorOfInt;
                animatorSet.playSequentially(animatorArr);
                animator = animatorSet;
            }
            i6 = iIntValue6;
        }
        final TextView textView4 = textView2;
        final CharSequence charSequence6 = str2;
        final int i13 = i;
        final int i14 = iIntValue2;
        final CharSequence charSequence7 = charSequence;
        final int i15 = i3;
        final int i16 = i5;
        addListener(new TransitionListenerAdapter() { // from class: com.transitionseverywhere.ChangeText.6
            int mPausedColor = 0;

            public void onTransitionPause(Transition transition) {
                if (ChangeText.this.mChangeBehavior != 2) {
                    textView4.setText(charSequence6);
                    TextView textView5 = textView4;
                    if (textView5 instanceof EditText) {
                        ChangeText.this.setSelection((EditText) textView5, i13, i14);
                    }
                }
                if (ChangeText.this.mChangeBehavior > 0) {
                    this.mPausedColor = textView4.getCurrentTextColor();
                    textView4.setTextColor(i6);
                }
            }

            public void onTransitionResume(Transition transition) {
                if (ChangeText.this.mChangeBehavior != 2) {
                    textView4.setText(charSequence7);
                    TextView textView5 = textView4;
                    if (textView5 instanceof EditText) {
                        ChangeText.this.setSelection((EditText) textView5, i15, i16);
                    }
                }
                if (ChangeText.this.mChangeBehavior > 0) {
                    textView4.setTextColor(this.mPausedColor);
                }
            }

            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
            }
        });
        return animator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelection(EditText editText, int i, int i2) {
        if (i < 0 || i2 < 0) {
            return;
        }
        editText.setSelection(i, i2);
    }
}
