package com.donkingliang.imageselector.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import kotlin.KotlinVersion;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ClipImageView extends AppCompatImageView {
    private int CURR_MODE;
    private final int MODE_DRAG;
    private final int MODE_NONE;
    private final int MODE_POINTER_UP;
    private final int MODE_ZOOM;
    private boolean isCutImage;
    private int mBitmapHeight;
    private int mBitmapWidth;
    private float mCircleCenterX;
    private float mCircleCenterY;
    private float mCircleX;
    private float mCircleY;
    private PointF mDownPoint;
    private Paint mFrontGroundPaint;
    private float mLastDistance;
    private Matrix mMatrix;
    private PointF mMiddlePoint;
    private float mRatio;
    private int mTargetHeight;
    private int mTargetWidth;
    private Matrix mTempMatrix;
    private Xfermode mXfermode;
    private Rect r;
    private RectF rf;

    public ClipImageView(Context context) {
        super(context);
        this.MODE_NONE = 0;
        this.MODE_DRAG = 1;
        this.MODE_ZOOM = 2;
        this.MODE_POINTER_UP = 3;
        this.CURR_MODE = 0;
        this.mFrontGroundPaint = new Paint();
        this.mRatio = 1.0f;
        setRadius();
    }

    public ClipImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.MODE_NONE = 0;
        this.MODE_DRAG = 1;
        this.MODE_ZOOM = 2;
        this.MODE_POINTER_UP = 3;
        this.CURR_MODE = 0;
        this.mFrontGroundPaint = new Paint();
        this.mRatio = 1.0f;
        setRadius();
    }

    public void setBitmapData(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        this.mBitmapHeight = bitmap.getHeight();
        this.mBitmapWidth = bitmap.getWidth();
        setImageBitmap(bitmap);
        init();
    }

    private void init() {
        this.mDownPoint = new PointF();
        this.mMiddlePoint = new PointF();
        this.mMatrix = new Matrix();
        this.mTempMatrix = new Matrix();
        this.mFrontGroundPaint.setColor(Color.parseColor("#ac000000"));
        this.mFrontGroundPaint.setAntiAlias(true);
        this.mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        setScaleType(ImageView.ScaleType.MATRIX);
        post(new Runnable() { // from class: com.donkingliang.imageselector.view.ClipImageView.1
            @Override // java.lang.Runnable
            public void run() {
                ClipImageView.this.center();
            }
        });
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        setRadius();
    }

    private void setRadius() {
        int screenWidth = getScreenWidth(getContext());
        this.mTargetWidth = screenWidth;
        this.mTargetHeight = (int) (screenWidth * this.mRatio);
        this.mCircleCenterX = getWidth() / 2;
        float height = getHeight() / 2;
        this.mCircleCenterY = height;
        this.mCircleX = this.mCircleCenterX - (this.mTargetWidth / 2);
        this.mCircleY = height - (this.mTargetHeight / 2);
    }

    public void setRatio(float f) {
        if (this.mRatio != f) {
            this.mRatio = f;
            setRadius();
            invalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isCutImage) {
            return;
        }
        RectF rectF = this.rf;
        if (rectF == null || rectF.isEmpty()) {
            this.r = new Rect(0, 0, getWidth(), getHeight());
            this.rf = new RectF(this.r);
        }
        int iSaveLayer = canvas.saveLayer(this.rf, null, 31);
        canvas.drawRect(this.r, this.mFrontGroundPaint);
        this.mFrontGroundPaint.setXfermode(this.mXfermode);
        float f = this.mCircleCenterX;
        int i = this.mTargetWidth;
        float f2 = this.mCircleCenterY;
        int i2 = this.mTargetHeight;
        canvas.drawRect(f - (i / 2), f2 - (i2 / 2), f + (i / 2), f2 + (i2 / 2), this.mFrontGroundPaint);
        canvas.restoreToCount(iSaveLayer);
        this.mFrontGroundPaint.setXfermode(null);
    }

    public Bitmap clipImage() {
        this.isCutImage = true;
        Paint paint = new Paint();
        setDrawingCacheEnabled(true);
        Bitmap drawingCache = getDrawingCache();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.mTargetWidth, this.mTargetHeight, Bitmap.Config.ARGB_8888);
        new Canvas(bitmapCreateBitmap).drawBitmap(drawingCache, (Rect) null, new RectF(((-drawingCache.getWidth()) / 2) + (this.mTargetWidth / 2), ((-getHeight()) / 2) + (this.mTargetHeight / 2), (drawingCache.getWidth() / 2) + (this.mTargetWidth / 2), (getHeight() / 2) + (this.mTargetHeight / 2)), paint);
        setDrawingCacheEnabled(false);
        drawingCache.recycle();
        this.isCutImage = false;
        return bitmapCreateBitmap;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Matrix matrix = this.mMatrix;
        if (matrix == null) {
            return super.onTouchEvent(motionEvent);
        }
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float f = fArr[2];
        float f2 = fArr[5];
        float f3 = (this.mBitmapWidth * fArr[0]) + f;
        float f4 = (this.mBitmapHeight * fArr[4]) + f2;
        int action = motionEvent.getAction() & KotlinVersion.MAX_COMPONENT_VALUE;
        if (action == 0) {
            this.CURR_MODE = 1;
            this.mDownPoint.set(motionEvent.getX(), motionEvent.getY());
        } else if (action == 1) {
            this.CURR_MODE = 0;
        } else if (action == 2) {
            int i = this.CURR_MODE;
            if (i != 1 && i != 3) {
                float distance = getDistance(motionEvent);
                if (distance > 10.0f) {
                    float f5 = distance / this.mLastDistance;
                    if (f >= this.mCircleX) {
                        this.mMiddlePoint.x = 0.0f;
                    }
                    if (f3 <= this.mCircleX + this.mTargetWidth) {
                        this.mMiddlePoint.x = f3;
                    }
                    if (f2 >= this.mCircleY) {
                        this.mMiddlePoint.y = 0.0f;
                    }
                    if (f4 <= this.mCircleY + this.mTargetHeight) {
                        this.mMiddlePoint.y = f4;
                    }
                    this.mTempMatrix.set(this.mMatrix);
                    this.mTempMatrix.postScale(f5, f5, this.mMiddlePoint.x, this.mMiddlePoint.y);
                    float[] fArr2 = new float[9];
                    this.mTempMatrix.getValues(fArr2);
                    float f6 = fArr2[2];
                    float f7 = fArr2[5];
                    float f8 = (this.mBitmapWidth * fArr2[0]) + f6;
                    float f9 = (this.mBitmapHeight * fArr2[4]) + f7;
                    float f10 = this.mCircleX;
                    if (f6 <= f10 && f8 >= f10 + this.mTargetWidth) {
                        float f11 = this.mCircleY;
                        if (f7 <= f11 && f9 >= f11 + this.mTargetHeight) {
                            this.mMatrix.postScale(f5, f5, this.mMiddlePoint.x, this.mMiddlePoint.y);
                            this.mLastDistance = getDistance(motionEvent);
                        }
                    }
                    return true;
                }
            } else if (i == 1) {
                float x = motionEvent.getX() - this.mDownPoint.x;
                float y = motionEvent.getY() - this.mDownPoint.y;
                float f12 = f + x;
                float f13 = this.mCircleX;
                if (f12 > f13) {
                    x = 0.0f;
                }
                if (f3 + x < f13 + this.mTargetWidth) {
                    x = 0.0f;
                }
                float f14 = f2 + y;
                float f15 = this.mCircleY;
                if (f14 > f15) {
                    y = 0.0f;
                }
                this.mMatrix.postTranslate(x, f4 + y >= f15 + ((float) this.mTargetHeight) ? y : 0.0f);
                this.mDownPoint.set(motionEvent.getX(), motionEvent.getY());
            } else {
                this.CURR_MODE = 1;
                this.mDownPoint.set(motionEvent.getX(), motionEvent.getY());
            }
        } else if (action != 5) {
            if (action == 6) {
                this.CURR_MODE = 3;
            }
        } else if (getDistance(motionEvent) > 10.0f) {
            this.CURR_MODE = 2;
            midPoint(this.mMiddlePoint, motionEvent);
            this.mLastDistance = getDistance(motionEvent);
        }
        setImageMatrix(this.mMatrix);
        return true;
    }

    private float getDistance(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    private void midPoint(PointF pointF, MotionEvent motionEvent) {
        pointF.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
    }

    protected void center() {
        float f = this.mBitmapHeight;
        float f2 = this.mBitmapWidth;
        float fMax = Math.max(this.mTargetWidth / f2, this.mTargetHeight / f);
        this.mMatrix.postScale(fMax, fMax);
        this.mMatrix.postTranslate((-((f2 * fMax) - getWidth())) / 2.0f, (-((f * fMax) - getHeight())) / 2.0f);
        setImageMatrix(this.mMatrix);
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
