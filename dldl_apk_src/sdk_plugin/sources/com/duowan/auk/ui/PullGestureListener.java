package com.duowan.auk.ui;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PullGestureListener extends GestureDetector.SimpleOnGestureListener {
    private ViewConfiguration mConfiguration;
    private final GestureDetector mDetector;
    private float mDownX;
    private float mDownY;
    private final HeadViewGestureListener mHeadViewListener;
    private float mMoveX;
    private float mMoveY;
    private MOVEDIRECTION mMoveDirection = MOVEDIRECTION.IDLE;
    private int upLeft = 0;
    private final int IDLE = 0;
    private final int UPDOWN = 1;
    private final int LEFTRIGHT = 2;
    private boolean isScrolling = false;
    private VelocityTracker mTracker = VelocityTracker.obtain();

    public interface HeadViewGestureListener {
        boolean onDoubleTap(MotionEvent motionEvent);

        boolean onFling(float f, float f2);

        boolean onScroll(float f, float f2, float f3);

        boolean onSingleUp(MotionEvent motionEvent);

        boolean onUp(MotionEvent motionEvent);
    }

    public enum MOVEDIRECTION {
        IDLE,
        UP,
        DOWN
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    public PullGestureListener(Context context, HeadViewGestureListener headViewGestureListener) {
        this.mHeadViewListener = headViewGestureListener;
        this.mDetector = new GestureDetector(context, this);
        this.mConfiguration = ViewConfiguration.get(context);
    }

    public void registerGuesture(View view) {
        if (view == null) {
            L.error(this, "regiesterGuesture view is null");
        } else {
            view.setOnTouchListener(new View.OnTouchListener() { // from class: com.duowan.auk.ui.PullGestureListener.1
                /* JADX WARN: Removed duplicated region for block: B:28:0x00a3  */
                @Override // android.view.View.OnTouchListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public boolean onTouch(android.view.View r7, android.view.MotionEvent r8) {
                    /*
                        Method dump skipped, instruction units count: 244
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.duowan.auk.ui.PullGestureListener.AnonymousClass1.onTouch(android.view.View, android.view.MotionEvent):boolean");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean afterScroll(MotionEvent motionEvent) {
        if (!this.isScrolling) {
            return false;
        }
        this.mTracker.computeCurrentVelocity(1000);
        float yVelocity = this.mTracker.getYVelocity();
        if (Math.abs(yVelocity) < this.mConfiguration.getScaledMinimumFlingVelocity()) {
            L.debug("GuestureHelp", "scroll up velocity: " + yVelocity + " miniFlingVelocity: " + this.mConfiguration.getScaledMinimumFlingVelocity());
            HeadViewGestureListener headViewGestureListener = this.mHeadViewListener;
            if (headViewGestureListener != null) {
                return headViewGestureListener.onUp(motionEvent);
            }
            return false;
        }
        L.debug("GuestureHelp", "fling velocity: " + yVelocity);
        return false;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        L.debug(this, "onFling velocityX:" + f + " velocityY: " + f2);
        HeadViewGestureListener headViewGestureListener = this.mHeadViewListener;
        if (headViewGestureListener != null && this.upLeft == 1) {
            return headViewGestureListener.onFling(f, f2);
        }
        return super.onFling(motionEvent, motionEvent2, f, f2);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.isScrolling = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeMoveDirection(float f) {
        if (f > 0.0f) {
            this.mMoveDirection = MOVEDIRECTION.DOWN;
        } else {
            this.mMoveDirection = MOVEDIRECTION.UP;
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        L.debug(this, "onSingleTapConfirmed e: " + motionEvent.toString());
        HeadViewGestureListener headViewGestureListener = this.mHeadViewListener;
        if (headViewGestureListener != null) {
            return headViewGestureListener.onSingleUp(motionEvent);
        }
        return super.onSingleTapConfirmed(motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        HeadViewGestureListener headViewGestureListener = this.mHeadViewListener;
        if (headViewGestureListener != null) {
            return headViewGestureListener.onDoubleTap(motionEvent);
        }
        return super.onDoubleTap(motionEvent);
    }

    private float distanceX(MotionEvent motionEvent, MotionEvent motionEvent2) {
        return motionEvent2.getRawX() - motionEvent.getRawX();
    }

    private float distanceY(MotionEvent motionEvent, MotionEvent motionEvent2) {
        return motionEvent2.getRawY() - motionEvent.getRawY();
    }

    private boolean isAboveDown() {
        L.debug(this, "above down make,downY: " + this.mDownY + " moveY: " + this.mMoveY);
        return this.mDownY - this.mMoveY > 0.0f;
    }

    public MOVEDIRECTION getmMoveDirection() {
        return this.mMoveDirection;
    }

    public double distance2UnitInterval(float f, int i) {
        if (i <= 0) {
            return 1.0d;
        }
        float f2 = i;
        if (Math.abs(f) >= f2 && f > 0.0f) {
            return 1.0d;
        }
        if (Math.abs(f) < f2 || f >= 0.0f) {
            return (((double) f) * 1.0d) / ((double) i);
        }
        return -1.0d;
    }

    public void printAction(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            L.debug(this, "action_down");
            return;
        }
        if (action == 1) {
            L.debug(this, "action_up");
        } else if (action == 2) {
            L.debug(this, "action_move");
        } else {
            if (action != 3) {
                return;
            }
            L.debug(this, "action_cancel");
        }
    }

    public void printEventXY(MotionEvent motionEvent, String str) {
        L.error(this, str + " X: " + motionEvent.getX() + " Y: " + motionEvent.getY() + " rawX: " + motionEvent.getRawX() + " rawY: " + motionEvent.getRawY());
    }
}
