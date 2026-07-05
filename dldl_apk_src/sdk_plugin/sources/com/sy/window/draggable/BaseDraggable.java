package com.sy.window.draggable;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.TypedValue;
import android.view.DisplayCutout;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.sy.window.WindowX;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class BaseDraggable implements View.OnTouchListener {
    private int mCurrentViewOnScreenX;
    private int mCurrentViewOnScreenY;
    private int mCurrentWindowHeight;
    private int mCurrentWindowInvisibleHeight;
    private int mCurrentWindowInvisibleWidth;
    private int mCurrentWindowWidth;
    private View mDecorView;
    private DraggingCallback mDraggingCallback;
    private WindowX<?> mWindowX;
    private boolean mAllowMoveToScreenNotch = true;
    private final Rect mTempRect = new Rect();

    public interface DraggingCallback {

        /* JADX INFO: renamed from: com.sy.window.draggable.BaseDraggable$DraggingCallback$-CC, reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onExecuteDragging(DraggingCallback draggingCallback, WindowX windowX) {
            }

            public static void $default$onStartDragging(DraggingCallback draggingCallback, WindowX windowX) {
            }

            public static void $default$onStopDragging(DraggingCallback draggingCallback, WindowX windowX) {
            }
        }

        void onExecuteDragging(WindowX<?> windowX);

        void onStartDragging(WindowX<?> windowX);

        void onStopDragging(WindowX<?> windowX);
    }

    public void start(WindowX<?> windowX) {
        this.mWindowX = windowX;
        View decorView = windowX.getDecorView();
        this.mDecorView = decorView;
        decorView.setOnTouchListener(new View.OnTouchListener() { // from class: com.sy.window.draggable.-$$Lambda$BaseDraggable$W6_W5PMcPK7aCL2VxaTpxqjSLyU
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$start$0$BaseDraggable(view, motionEvent);
            }
        });
        this.mDecorView.post(new $$Lambda$SS7_rmdqWR9kPVZNutLLXY4AKvw(this));
    }

    public /* synthetic */ boolean lambda$start$0$BaseDraggable(View view, MotionEvent motionEvent) {
        refreshLocationCoordinate();
        return onTouch(view, motionEvent);
    }

    public WindowX<?> getWindowX() {
        return this.mWindowX;
    }

    public View getDecorView() {
        return this.mDecorView;
    }

    public void setAllowMoveToScreenNotch(boolean z) {
        this.mAllowMoveToScreenNotch = z;
    }

    public boolean isAllowMoveToScreenNotch() {
        return this.mAllowMoveToScreenNotch;
    }

    public int getWindowWidth() {
        return this.mCurrentWindowWidth;
    }

    public int getWindowHeight() {
        return this.mCurrentWindowHeight;
    }

    public int getWindowInvisibleWidth() {
        return this.mCurrentWindowInvisibleWidth;
    }

    public int getWindowInvisibleHeight() {
        return this.mCurrentWindowInvisibleHeight;
    }

    public void refreshLocationCoordinate() {
        View decorView = getDecorView();
        if (decorView == null) {
            return;
        }
        decorView.getWindowVisibleDisplayFrame(this.mTempRect);
        this.mCurrentWindowWidth = this.mTempRect.right - this.mTempRect.left;
        this.mCurrentWindowHeight = this.mTempRect.bottom - this.mTempRect.top;
        int[] iArr = new int[2];
        decorView.getLocationOnScreen(iArr);
        this.mCurrentViewOnScreenX = iArr[0];
        this.mCurrentViewOnScreenY = iArr[1];
        this.mCurrentWindowInvisibleWidth = this.mTempRect.left;
        this.mCurrentWindowInvisibleHeight = this.mTempRect.top;
    }

    public void onScreenOrientationChange() {
        final float f;
        final int width = getDecorView().getWidth();
        int height = getDecorView().getHeight();
        int i = this.mCurrentViewOnScreenX - this.mCurrentWindowInvisibleWidth;
        int i2 = this.mCurrentViewOnScreenY - this.mCurrentWindowInvisibleHeight;
        float f2 = i;
        final float f3 = 0.0f;
        if (f2 < 1.0f) {
            f = 0.0f;
        } else {
            f = ((float) Math.abs(this.mCurrentWindowWidth - (i + width))) < 1.0f ? 1.0f : (f2 + (width / 2.0f)) / this.mCurrentWindowWidth;
        }
        float f4 = i2;
        if (f4 >= 1.0f) {
            f3 = ((float) Math.abs(this.mCurrentWindowHeight - (i2 + height))) < 1.0f ? 1.0f : (f4 + (height / 2.0f)) / this.mCurrentWindowHeight;
        }
        getWindowX().postDelayed(new Runnable() { // from class: com.sy.window.draggable.-$$Lambda$BaseDraggable$tL3iw8wpndBSvqncAh39qim6JgE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onScreenOrientationChange$1$BaseDraggable(f, width, f3);
            }
        }, 100L);
    }

    public /* synthetic */ void lambda$onScreenOrientationChange$1$BaseDraggable(float f, int i, float f2) {
        getDecorView().getWindowVisibleDisplayFrame(this.mTempRect);
        this.mCurrentWindowWidth = this.mTempRect.right - this.mTempRect.left;
        this.mCurrentWindowHeight = this.mTempRect.bottom - this.mTempRect.top;
        float f3 = this.mCurrentWindowWidth * f;
        float f4 = i / 2.0f;
        updateLocation((int) (f3 - f4), (int) ((r0 * f2) - f4));
        getWindowX().post(new $$Lambda$SS7_rmdqWR9kPVZNutLLXY4AKvw(this));
    }

    public void updateLocation(float f, float f2) {
        updateLocation(f, f2, isAllowMoveToScreenNotch());
    }

    public void updateLocation(float f, float f2, boolean z) {
        updateLocation((int) f, (int) f2, z);
    }

    public void updateLocation(int i, int i2, boolean z) {
        if (z) {
            updateWindowCoordinate(i, i2);
            return;
        }
        Rect safeInsetRect = getSafeInsetRect();
        if (safeInsetRect == null) {
            updateWindowCoordinate(i, i2);
            return;
        }
        if (safeInsetRect.left > 0 && safeInsetRect.right > 0 && safeInsetRect.top > 0 && safeInsetRect.bottom > 0) {
            updateWindowCoordinate(i, i2);
            return;
        }
        int viewWidth = this.mWindowX.getViewWidth();
        int viewHeight = this.mWindowX.getViewHeight();
        int windowWidth = getWindowWidth();
        int windowHeight = getWindowHeight();
        if (i < safeInsetRect.left - getWindowInvisibleWidth()) {
            i = safeInsetRect.left - getWindowInvisibleWidth();
        } else if (i > (windowWidth - safeInsetRect.right) - viewWidth) {
            i = (windowWidth - safeInsetRect.right) - viewWidth;
        }
        if (i2 < safeInsetRect.top - getWindowInvisibleHeight()) {
            i2 = safeInsetRect.top - getWindowInvisibleHeight();
        } else if (i2 > (windowHeight - safeInsetRect.bottom) - viewHeight) {
            i2 = (windowHeight - safeInsetRect.bottom) - viewHeight;
        }
        updateWindowCoordinate(i, i2);
    }

    public void updateWindowCoordinate(int i, int i2) {
        WindowManager.LayoutParams windowParams = this.mWindowX.getWindowParams();
        if (windowParams == null) {
            return;
        }
        if (windowParams.gravity == 8388659 && windowParams.x == i && windowParams.y == i2) {
            return;
        }
        windowParams.x = i;
        windowParams.y = i2;
        windowParams.gravity = 8388659;
        this.mWindowX.update();
    }

    public Rect getSafeInsetRect() {
        Window window;
        Context context = this.mWindowX.getContext();
        if ((context instanceof Activity) && (window = ((Activity) context).getWindow()) != null) {
            return getSafeInsetRect(window);
        }
        return null;
    }

    public static Rect getSafeInsetRect(Window window) {
        if (Build.VERSION.SDK_INT >= 28) {
            View decorView = window != null ? window.getDecorView() : null;
            WindowInsets rootWindowInsets = decorView != null ? decorView.getRootWindowInsets() : null;
            DisplayCutout displayCutout = rootWindowInsets != null ? rootWindowInsets.getDisplayCutout() : null;
            if (displayCutout != null) {
                return new Rect(displayCutout.getSafeInsetLeft(), displayCutout.getSafeInsetTop(), displayCutout.getSafeInsetRight(), displayCutout.getSafeInsetBottom());
            }
        }
        return null;
    }

    protected boolean isFingerMove(float f, float f2, float f3, float f4) {
        float minTouchDistance = getMinTouchDistance();
        return Math.abs(f - f2) >= minTouchDistance || Math.abs(f3 - f4) >= minTouchDistance;
    }

    protected float getMinTouchDistance() {
        return TypedValue.applyDimension(1, 1.0f, Resources.getSystem().getDisplayMetrics());
    }

    public void setDraggingCallback(DraggingCallback draggingCallback) {
        this.mDraggingCallback = draggingCallback;
    }

    protected void dispatchStartDraggingCallback() {
        DraggingCallback draggingCallback = this.mDraggingCallback;
        if (draggingCallback == null) {
            return;
        }
        draggingCallback.onStartDragging(this.mWindowX);
    }

    protected void dispatchExecuteDraggingCallback() {
        DraggingCallback draggingCallback = this.mDraggingCallback;
        if (draggingCallback == null) {
            return;
        }
        draggingCallback.onExecuteDragging(this.mWindowX);
    }

    protected void dispatchStopDraggingCallback() {
        DraggingCallback draggingCallback = this.mDraggingCallback;
        if (draggingCallback == null) {
            return;
        }
        draggingCallback.onStopDragging(this.mWindowX);
    }
}
