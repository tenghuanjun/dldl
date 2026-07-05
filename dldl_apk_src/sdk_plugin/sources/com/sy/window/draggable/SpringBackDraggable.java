package com.sy.window.draggable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import com.sy.window.WindowX;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SpringBackDraggable extends BaseDraggable {
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    private final int mOrientation;
    private SpringBackAnimCallback mSpringBackAnimCallback;
    private boolean mTouchMoving;
    private float mViewDownX;
    private float mViewDownY;

    public interface SpringBackAnimCallback {
        void onSpringBackAnimationEnd(WindowX<?> windowX, Animator animator);

        void onSpringBackAnimationStart(WindowX<?> windowX, Animator animator);
    }

    public SpringBackDraggable() {
        this(0);
    }

    public SpringBackDraggable(int i) {
        this.mOrientation = i;
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("You cannot pass in directions other than horizontal or vertical");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x000f, code lost:
    
        if (r0 != 3) goto L45;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouch(android.view.View r7, android.view.MotionEvent r8) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sy.window.draggable.SpringBackDraggable.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    protected boolean equalsWithRelativeTolerance(float f, float f2) {
        return Math.abs(f - f2) < 1.0E-5f;
    }

    protected void startHorizontalAnimation(float f, float f2, float f3) {
        startHorizontalAnimation(f, f2, f3, calculateAnimationDuration(f, f2));
    }

    public /* synthetic */ void lambda$startHorizontalAnimation$0$SpringBackDraggable(float f, ValueAnimator valueAnimator) {
        updateLocation(((Float) valueAnimator.getAnimatedValue()).floatValue(), f);
    }

    protected void startHorizontalAnimation(float f, float f2, final float f3, long j) {
        startAnimation(f, f2, j, new ValueAnimator.AnimatorUpdateListener() { // from class: com.sy.window.draggable.-$$Lambda$SpringBackDraggable$9PEepWKlx1IncAkBRODGTIAMHyU
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$startHorizontalAnimation$0$SpringBackDraggable(f3, valueAnimator);
            }
        });
    }

    protected void startVerticalAnimation(float f, float f2, float f3) {
        startVerticalAnimation(f, f2, f3, calculateAnimationDuration(f2, f3));
    }

    public /* synthetic */ void lambda$startVerticalAnimation$1$SpringBackDraggable(float f, ValueAnimator valueAnimator) {
        updateLocation(f, ((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    protected void startVerticalAnimation(final float f, float f2, float f3, long j) {
        startAnimation(f2, f3, j, new ValueAnimator.AnimatorUpdateListener() { // from class: com.sy.window.draggable.-$$Lambda$SpringBackDraggable$WfylxPmaYERXgWDWNULhsBddZB8
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$startVerticalAnimation$1$SpringBackDraggable(f, valueAnimator);
            }
        });
    }

    protected void startAnimation(float f, float f2, long j, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, f2);
        valueAnimatorOfFloat.setDuration(j);
        if (animatorUpdateListener != null) {
            valueAnimatorOfFloat.addUpdateListener(animatorUpdateListener);
        }
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.sy.window.draggable.SpringBackDraggable.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                SpringBackDraggable.this.dispatchSpringBackAnimationStartCallback(animator);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SpringBackDraggable.this.dispatchSpringBackAnimationEndCallback(animator);
            }
        });
        valueAnimatorOfFloat.start();
    }

    public long calculateAnimationDuration(float f, float f2) {
        return Math.min(Math.max((long) (Math.abs(f2 - f) / 2.0f), 200L), 800L);
    }

    public void setSpringBackAnimCallback(SpringBackAnimCallback springBackAnimCallback) {
        this.mSpringBackAnimCallback = springBackAnimCallback;
    }

    protected void dispatchSpringBackAnimationStartCallback(Animator animator) {
        SpringBackAnimCallback springBackAnimCallback = this.mSpringBackAnimCallback;
        if (springBackAnimCallback == null) {
            return;
        }
        springBackAnimCallback.onSpringBackAnimationStart(getWindowX(), animator);
    }

    protected void dispatchSpringBackAnimationEndCallback(Animator animator) {
        SpringBackAnimCallback springBackAnimCallback = this.mSpringBackAnimCallback;
        if (springBackAnimCallback == null) {
            return;
        }
        springBackAnimCallback.onSpringBackAnimationEnd(getWindowX(), animator);
    }
}
