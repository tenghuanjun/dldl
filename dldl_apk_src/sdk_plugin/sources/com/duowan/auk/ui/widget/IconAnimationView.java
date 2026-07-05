package com.duowan.auk.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.mtp.utils.MathUtils;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class IconAnimationView extends SurfaceView implements SurfaceHolder.Callback {
    private static final int ALPHA = 5;
    public static final int FULL_UPDATE = 1;
    public static final int HIGH_PERFORMANCE = 1;
    public static final int HIGH_QUALITY = 2;
    public static final int Minimal_Update = 2;
    private static final int ROTATION = 2;
    private static final int SCALE_X = 3;
    private static final int SCALE_Y = 4;
    private static int TIME_IN_FRAME = 17;
    private static final int X = 0;
    private static final int Y = 1;
    private int mAnimationModel;
    private LinkedList<IconAnimation> mAnimations;
    private Paint mClearPaint;
    private RectF mDirtyRF;
    private RectF mLastDirtyRF;
    private RectF mLastLastDirtyRF;
    private Paint mPaint;
    private SurfaceHolder mSurfaceHolder;
    private AnimationThread mThread;
    private int mUpdateModel;

    public interface OnAnimationListener {
        void onAnimationEnd(IconAnimation iconAnimation);
    }

    protected void frameDrew(IconAnimation iconAnimation, IconFrameHolds iconFrameHolds) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    public IconAnimationView(Context context) {
        super(context);
        this.mUpdateModel = 2;
        this.mAnimationModel = 2;
        this.mDirtyRF = new RectF();
        this.mLastDirtyRF = new RectF();
        this.mLastLastDirtyRF = new RectF();
        this.mAnimations = new LinkedList<>();
        init();
    }

    public IconAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mUpdateModel = 2;
        this.mAnimationModel = 2;
        this.mDirtyRF = new RectF();
        this.mLastDirtyRF = new RectF();
        this.mLastLastDirtyRF = new RectF();
        this.mAnimations = new LinkedList<>();
        init();
    }

    public IconAnimation createAnimation(Bitmap bitmap) {
        return new IconAnimation(bitmap);
    }

    public void endAllAnimation() {
        AnimationThread animationThread = this.mThread;
        if (animationThread != null) {
            animationThread.mRunning.set(false);
        }
        this.mAnimations.clear();
    }

    public void setUpdateModel(int i) {
        this.mUpdateModel = i;
    }

    public void setAnimationModel(int i) {
        this.mAnimationModel = i;
        if (1 == i) {
            TIME_IN_FRAME = 34;
        } else {
            TIME_IN_FRAME = 17;
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        AnimationThread animationThread = this.mThread;
        if (animationThread == null) {
            clearCanvas();
        } else {
            animationThread.mRunning.set(false);
            this.mThread = null;
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        endAllAnimation();
    }

    protected synchronized void clearAnimations() {
        this.mAnimations.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean isFinished() {
        return this.mAnimations.size() == 0;
    }

    private synchronized void addAnimation(IconAnimation iconAnimation) {
        this.mAnimations.add(iconAnimation);
    }

    private synchronized LinkedList<IconAnimation> popAllAnimaiton() {
        LinkedList<IconAnimation> linkedList;
        linkedList = this.mAnimations;
        this.mAnimations = new LinkedList<>();
        return linkedList;
    }

    private synchronized void addAnimations(LinkedList<IconAnimation> linkedList) {
        this.mAnimations.addAll(linkedList);
    }

    private void init() {
        setZOrderOnTop(true);
        SurfaceHolder holder = getHolder();
        this.mSurfaceHolder = holder;
        holder.addCallback(this);
        this.mSurfaceHolder.setFormat(-3);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        Paint paint2 = new Paint();
        this.mClearPaint = paint2;
        paint2.setColor(0);
        this.mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void drawAnimations(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        clearDirty(canvas);
        LinkedList<IconAnimation> linkedList = new LinkedList<>();
        LinkedList<IconAnimation> linkedListPopAllAnimaiton = popAllAnimaiton();
        while (!linkedListPopAllAnimaiton.isEmpty()) {
            IconAnimation iconAnimationPop = linkedListPopAllAnimaiton.pop();
            if (iconAnimationPop.target != null && iconAnimationPop.animationHolds.size() != 0) {
                IconFrameHolds currentFrame = getCurrentFrame(iconAnimationPop);
                drawFrame(canvas, currentFrame);
                frameDrew(iconAnimationPop, currentFrame);
                long j = iconAnimationPop.frameCount;
                if (j == -1) {
                    linkedList.add(iconAnimationPop);
                } else {
                    iconAnimationPop.frameIndex++;
                    if (iconAnimationPop.frameIndex <= j) {
                        linkedList.add(iconAnimationPop);
                    } else if (iconAnimationPop.listener != null) {
                        iconAnimationPop.listener.onAnimationEnd(iconAnimationPop);
                    }
                }
            }
        }
        if (linkedList.size() != 0) {
            addAnimations(linkedList);
        }
    }

    protected synchronized boolean isThreadStopped() {
        if (this.mThread == null) {
            return true;
        }
        return !this.mThread.mRunning.get();
    }

    private void clearDirty(Canvas canvas) {
        if (1 == this.mUpdateModel) {
            canvas.drawRect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), this.mClearPaint);
            return;
        }
        canvas.drawRect(MathUtils.min(this.mDirtyRF.left, this.mLastDirtyRF.left, this.mLastLastDirtyRF.left), MathUtils.min(this.mDirtyRF.top, this.mLastDirtyRF.top, this.mLastLastDirtyRF.top), MathUtils.max(this.mDirtyRF.right, this.mLastDirtyRF.right, this.mLastLastDirtyRF.right), MathUtils.max(this.mDirtyRF.bottom, this.mLastDirtyRF.bottom, this.mLastLastDirtyRF.bottom), this.mClearPaint);
        this.mLastLastDirtyRF.set(this.mLastDirtyRF);
        this.mLastDirtyRF.set(this.mDirtyRF);
        this.mDirtyRF.left = canvas.getWidth();
        this.mDirtyRF.top = canvas.getHeight();
        this.mDirtyRF.right = 0.0f;
        this.mDirtyRF.bottom = 0.0f;
    }

    private void drawFrame(Canvas canvas, IconFrameHolds iconFrameHolds) {
        Bitmap bitmap = iconFrameHolds.bitmap;
        float f = iconFrameHolds.x;
        float f2 = iconFrameHolds.y;
        float f3 = iconFrameHolds.rotation;
        float width = bitmap.getWidth() * iconFrameHolds.scaleX;
        float height = bitmap.getHeight() * iconFrameHolds.scaleY;
        float f4 = (width / 2.0f) + f;
        float f5 = (height / 2.0f) + f2;
        float f6 = f3 % 360.0f;
        if (f6 != 0.0f) {
            canvas.rotate(f3, f4, f5);
        }
        this.mPaint.setAlpha((int) (iconFrameHolds.alpha * 255.0f));
        float f7 = width + f;
        float f8 = height + f2;
        canvas.drawBitmap(bitmap, (Rect) null, new RectF(f, f2, f7, f8), this.mPaint);
        if (f6 != 0.0f) {
            canvas.rotate(360.0f - f3, f4, f5);
        }
        if (2 == this.mUpdateModel) {
            calcDirtyRect(f, f2, f7, f8);
        }
    }

    private void calcDirtyRect(float f, float f2, float f3, float f4) {
        RectF rectF = this.mDirtyRF;
        rectF.left = Math.min(f, rectF.left);
        RectF rectF2 = this.mDirtyRF;
        rectF2.top = Math.min(f2, rectF2.top);
        RectF rectF3 = this.mDirtyRF;
        rectF3.right = Math.max(f3, rectF3.right);
        RectF rectF4 = this.mDirtyRF;
        rectF4.bottom = Math.max(f4, rectF4.bottom);
        double dSqrt = Math.sqrt(((this.mDirtyRF.width() / 2.0f) * this.mDirtyRF.height()) / 2.0f);
        RectF rectF5 = this.mDirtyRF;
        rectF5.left = (float) (((double) rectF5.left) - dSqrt);
        RectF rectF6 = this.mDirtyRF;
        rectF6.top = (float) (((double) rectF6.top) - dSqrt);
        RectF rectF7 = this.mDirtyRF;
        rectF7.right = (float) (((double) rectF7.right) + dSqrt);
        RectF rectF8 = this.mDirtyRF;
        rectF8.bottom = (float) (((double) rectF8.bottom) + dSqrt);
    }

    private IconFrameHolds getCurrentFrame(IconAnimation iconAnimation) {
        IconFrameHolds iconFrameHolds = new IconFrameHolds();
        iconFrameHolds.bitmap = iconAnimation.target;
        SparseArray sparseArray = iconAnimation.animationHolds;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            int iKeyAt = sparseArray.keyAt(i);
            setFrameHolds(iconFrameHolds, iKeyAt, getCurrentValue((float[]) sparseArray.get(iKeyAt), iconAnimation.frameIndex, iconAnimation.frameCount));
        }
        return iconFrameHolds;
    }

    private float getCurrentValue(float[] fArr, long j, long j2) {
        if (fArr.length == 1) {
            return fArr[0];
        }
        float f = fArr[0];
        return -1 != j2 ? f + (((fArr[1] - fArr[0]) * j) / j2) : f;
    }

    private void setFrameHolds(IconFrameHolds iconFrameHolds, int i, float f) {
        if (i == 0) {
            iconFrameHolds.x = f;
            return;
        }
        if (i == 1) {
            iconFrameHolds.y = f;
            return;
        }
        if (i == 2) {
            iconFrameHolds.rotation = f;
            return;
        }
        if (i == 3) {
            iconFrameHolds.scaleX = f;
        } else if (i == 4) {
            iconFrameHolds.scaleY = f;
        } else {
            if (i != 5) {
                return;
            }
            iconFrameHolds.alpha = f;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAnimation(IconAnimation iconAnimation) {
        addAnimation(iconAnimation);
        AnimationThread animationThread = this.mThread;
        if (animationThread == null || !animationThread.mRunning.get()) {
            this.mThread = null;
            AnimationThread animationThread2 = new AnimationThread();
            this.mThread = animationThread2;
            animationThread2.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void endAnimation(IconAnimation iconAnimation) {
        endAllAnimation();
    }

    public class IconAnimation {
        private static final long DEFAULT_FRAME_COUNT = 40;
        public static final long FOREVER = -1;
        private static final long FOREVER_FRAME_COUNT = -1;
        private OnAnimationListener listener;
        public Bitmap target;
        public long frameIndex = 0;
        public long frameCount = DEFAULT_FRAME_COUNT;
        public long duration = 0;
        private SparseArray<float[]> animationHolds = new SparseArray<>();

        protected IconAnimation(Bitmap bitmap) {
            this.target = bitmap;
        }

        public IconAnimation x(float... fArr) {
            this.animationHolds.put(0, fArr);
            return this;
        }

        public IconAnimation y(float... fArr) {
            this.animationHolds.put(1, fArr);
            return this;
        }

        public float[] getY() {
            return this.animationHolds.get(1);
        }

        public IconAnimation rotation(float... fArr) {
            this.animationHolds.put(2, fArr);
            return this;
        }

        public IconAnimation scaleX(float... fArr) {
            this.animationHolds.put(3, fArr);
            return this;
        }

        public IconAnimation scaleY(float... fArr) {
            this.animationHolds.put(4, fArr);
            return this;
        }

        public IconAnimation alpha(float... fArr) {
            this.animationHolds.put(5, fArr);
            return this;
        }

        public IconAnimation duration(long j) {
            this.duration = j;
            if (j != -1) {
                this.frameCount = j / ((long) IconAnimationView.TIME_IN_FRAME);
            } else {
                this.frameCount = -1L;
            }
            return this;
        }

        public IconAnimation setListener(OnAnimationListener onAnimationListener) {
            this.listener = onAnimationListener;
            return this;
        }

        public void start() {
            IconAnimationView.this.startAnimation(this);
        }

        public void start(long j) {
            if (0 == j) {
                start();
            } else {
                ArkValue.gMainHandler.postDelayed(new Runnable() { // from class: com.duowan.auk.ui.widget.IconAnimationView.IconAnimation.1
                    @Override // java.lang.Runnable
                    public void run() {
                        IconAnimation.this.start();
                    }
                }, j);
            }
        }

        public void end() {
            IconAnimationView.this.endAnimation(this);
            OnAnimationListener onAnimationListener = this.listener;
            if (onAnimationListener != null) {
                onAnimationListener.onAnimationEnd(this);
            }
        }
    }

    class AnimationThread extends Thread {
        private AtomicBoolean mRunning;

        public AnimationThread() {
            super("Animation Thread");
            this.mRunning = new AtomicBoolean(true);
            if (1 == IconAnimationView.this.mAnimationModel) {
                setPriority(4);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            while (this.mRunning.get() && !IconAnimationView.this.isFinished()) {
                Canvas canvasLockCanvas = null;
                try {
                    try {
                        canvasLockCanvas = IconAnimationView.this.mSurfaceHolder.lockCanvas();
                        if (canvasLockCanvas != null) {
                            IconAnimationView.this.drawAnimations(canvasLockCanvas);
                        }
                    } finally {
                        if (canvasLockCanvas != null) {
                            try {
                                IconAnimationView.this.mSurfaceHolder.unlockCanvasAndPost(canvasLockCanvas);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e2) {
                    L.error(this, e2);
                    if (canvasLockCanvas != null) {
                        IconAnimationView.this.mSurfaceHolder.unlockCanvasAndPost(canvasLockCanvas);
                    }
                }
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                if (((int) (jCurrentTimeMillis2 - jCurrentTimeMillis)) < IconAnimationView.TIME_IN_FRAME) {
                    try {
                        sleep(IconAnimationView.TIME_IN_FRAME - r5);
                    } catch (InterruptedException e3) {
                        L.error(this, e3);
                    }
                    jCurrentTimeMillis += (long) IconAnimationView.TIME_IN_FRAME;
                } else {
                    jCurrentTimeMillis = jCurrentTimeMillis2;
                }
            }
            IconAnimationView.this.clearCanvas();
            this.mRunning.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCanvas() {
        Canvas canvasLockCanvas = null;
        try {
            try {
                try {
                    canvasLockCanvas = this.mSurfaceHolder.lockCanvas();
                    if (canvasLockCanvas != null) {
                        canvasLockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    }
                } catch (Exception e) {
                    L.error(this, e);
                    if (canvasLockCanvas == null) {
                        return;
                    } else {
                        this.mSurfaceHolder.unlockCanvasAndPost(canvasLockCanvas);
                    }
                }
                if (canvasLockCanvas != null) {
                    this.mSurfaceHolder.unlockCanvasAndPost(canvasLockCanvas);
                }
            } catch (Throwable th) {
                if (canvasLockCanvas != null) {
                    try {
                        this.mSurfaceHolder.unlockCanvasAndPost(canvasLockCanvas);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    protected class IconFrameHolds {
        public Bitmap bitmap;
        public float x = 0.0f;
        public float y = 0.0f;
        public float rotation = 0.0f;
        public float scaleX = 1.0f;
        public float scaleY = 1.0f;
        public float alpha = 1.0f;

        protected IconFrameHolds() {
        }
    }
}
