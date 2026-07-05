package com.duowan.auk.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.duowan.auk.ui.PullGestureListener;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class InteractionImpl implements PullGestureListener.HeadViewGestureListener {
    protected View head_hide_fl;
    protected View head_show_fl;
    protected PullGestureListener mGestureHelp;
    private final float K = 0.6f;
    private float head_hide_height = 0.0f;
    private String TAG = getClass().getSimpleName();

    @Override // com.duowan.auk.ui.PullGestureListener.HeadViewGestureListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    public InteractionImpl(Context context, View view, View view2, View view3) {
        PullGestureListener pullGestureListener = new PullGestureListener(context, this);
        this.mGestureHelp = pullGestureListener;
        this.head_hide_fl = view2;
        this.head_show_fl = view3;
        pullGestureListener.registerGuesture(view);
    }

    private void doShowHeadAlpha(View view, float f, float f2) {
        Animator animator = (Animator) view.getTag();
        if (animator != null) {
            animator.cancel();
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "Alpha", f, f2);
        objectAnimatorOfFloat.setDuration(200L);
        objectAnimatorOfFloat.start();
        view.setTag(objectAnimatorOfFloat);
    }

    private void doHeadAnimator(View view, float f, float f2) {
        Animator animator = (Animator) view.getTag();
        if (animator != null) {
            animator.cancel();
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "Y", f, f2);
        objectAnimatorOfFloat.setDuration(200L);
        objectAnimatorOfFloat.start();
        view.setTag(objectAnimatorOfFloat);
    }

    private boolean doFling(float f, float f2) {
        L.debug(this.TAG, "doFling");
        changeHeadViewsProperty(f2 > 0.0f, this.head_hide_fl.getY(), this.head_show_fl.getAlpha());
        return true;
    }

    private void changeHeadViewsProperty(boolean z, float f, float f2) {
        if (z) {
            doHeadAnimator(this.head_hide_fl, f, 0.0f);
            doShowHeadAlpha(this.head_show_fl, f2, 0.0f);
            L.debug(this.TAG, "show hide view animation");
        } else {
            doHeadAnimator(this.head_hide_fl, f, -this.head_hide_height);
            doShowHeadAlpha(this.head_show_fl, f2, 1.0f);
            L.debug(this.TAG, "dismiss hide view animation");
        }
    }

    private boolean doUp(MotionEvent motionEvent) {
        L.debug(this.TAG, "DoUp");
        float y = this.head_hide_fl.getY();
        if (y == 0.0f) {
            this.head_show_fl.setAlpha(0.0f);
            L.debug(this.TAG, "show hide view");
            return true;
        }
        if (y == (-this.head_hide_height)) {
            this.head_show_fl.setAlpha(1.0f);
            L.debug(this.TAG, "dismiss hide view");
            return true;
        }
        changeHeadViewsProperty(y > (-this.head_hide_height) / 2.0f, y, this.head_show_fl.getAlpha());
        return true;
    }

    private void scrollHead(float f, float f2) {
        float y = this.head_hide_fl.getY() + (f2 * 0.6f);
        if (y > 0.0f) {
            y = 0.0f;
        } else {
            float f3 = this.head_hide_height;
            if (y < (-f3)) {
                y = -f3;
            }
        }
        L.debug(this.TAG, "nextY: " + y);
        this.head_hide_fl.setY(y);
        this.head_show_fl.setAlpha(Math.abs(y) / this.head_hide_height);
    }

    private boolean canScroll() {
        if (this.mGestureHelp.getmMoveDirection() == PullGestureListener.MOVEDIRECTION.DOWN) {
            if (((int) this.head_hide_fl.getY()) != 0 || this.head_hide_fl.getVisibility() != 0) {
                return true;
            }
            L.debug(this.TAG, "hide view is already visible");
            return false;
        }
        if (this.mGestureHelp.getmMoveDirection() != PullGestureListener.MOVEDIRECTION.UP || ((int) this.head_hide_fl.getY()) != (-this.head_hide_height)) {
            return true;
        }
        L.debug(this.TAG, "hide view is already gone");
        return false;
    }

    private boolean doScroll(float f, float f2, float f3) {
        L.debug(this.TAG, "doScroll");
        if (canScroll() && Math.abs(f) < Math.abs(f2)) {
            if (this.head_hide_fl.getVisibility() != 0) {
                this.head_hide_fl.setVisibility(0);
                float height = this.head_hide_fl.getHeight();
                this.head_hide_height = height;
                this.head_hide_fl.setY(-height);
            }
            scrollHead(f2, f3);
        }
        return true;
    }

    @Override // com.duowan.auk.ui.PullGestureListener.HeadViewGestureListener
    public boolean onScroll(float f, float f2, float f3) {
        return doScroll(f, f2, f3);
    }

    @Override // com.duowan.auk.ui.PullGestureListener.HeadViewGestureListener
    public boolean onFling(float f, float f2) {
        return doFling(f, f2);
    }

    @Override // com.duowan.auk.ui.PullGestureListener.HeadViewGestureListener
    public boolean onUp(MotionEvent motionEvent) {
        return doUp(motionEvent);
    }

    @Override // com.duowan.auk.ui.PullGestureListener.HeadViewGestureListener
    public boolean onSingleUp(MotionEvent motionEvent) {
        L.debug(this.TAG, "onSingleUp");
        int y = (int) this.head_hide_fl.getY();
        if (y != 0 || this.head_hide_fl.getVisibility() != 0) {
            return true;
        }
        changeHeadViewsProperty(false, y, 0.0f);
        return true;
    }
}
