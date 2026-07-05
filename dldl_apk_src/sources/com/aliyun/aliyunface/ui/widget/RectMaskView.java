package com.aliyun.aliyunface.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.aliyun.aliyuncomm.R;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class RectMaskView extends ImageView {
    private static final Xfermode SXFERMODE = new PorterDuffXfermode(PorterDuff.Mode.XOR);
    private Bitmap mMaskBitmap;
    private Paint mRectHolePaint;
    private Paint mRectStrokePaint;
    private WeakReference<Bitmap> mWeakBitmap;
    private int rectColor;
    private boolean rectHCenter;
    private float rectHeight;
    private float rectLeft;
    private int rectRoundCx;
    private float rectTop;
    private boolean rectVCenter;
    private float rectWidth;
    private int strokeWidth;

    public RectMaskView(Context context) {
        super(context);
        this.rectLeft = -1.0f;
        this.rectTop = -1.0f;
        this.rectWidth = -1.0f;
        this.rectHeight = -1.0f;
        this.rectHCenter = false;
        this.rectVCenter = false;
        this.rectColor = -1;
        this.strokeWidth = 5;
        this.rectRoundCx = 35;
        sharedConstructor();
    }

    public RectMaskView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.rectLeft = -1.0f;
        this.rectTop = -1.0f;
        this.rectWidth = -1.0f;
        this.rectHeight = -1.0f;
        this.rectHCenter = false;
        this.rectVCenter = false;
        this.rectColor = -1;
        this.strokeWidth = 5;
        this.rectRoundCx = 35;
        initRectAttrs(context, attributeSet);
        sharedConstructor();
    }

    public RectMaskView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.rectLeft = -1.0f;
        this.rectTop = -1.0f;
        this.rectWidth = -1.0f;
        this.rectHeight = -1.0f;
        this.rectHCenter = false;
        this.rectVCenter = false;
        this.rectColor = -1;
        this.strokeWidth = 5;
        this.rectRoundCx = 35;
        initRectAttrs(context, attributeSet);
        sharedConstructor();
    }

    private void initRectAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RectMaskView);
        if (typedArrayObtainStyledAttributes != null) {
            this.rectLeft = typedArrayObtainStyledAttributes.getDimension(R.styleable.RectMaskView_rectLeft, 0.0f);
            this.rectTop = typedArrayObtainStyledAttributes.getDimension(R.styleable.RectMaskView_rectTop, 0.0f);
            this.rectWidth = typedArrayObtainStyledAttributes.getDimension(R.styleable.RectMaskView_rectWidth, 0.0f);
            this.rectHeight = typedArrayObtainStyledAttributes.getDimension(R.styleable.RectMaskView_rectHeight, 0.0f);
            this.rectHCenter = typedArrayObtainStyledAttributes.getBoolean(R.styleable.RectMaskView_rectHCenter, false);
            this.rectVCenter = typedArrayObtainStyledAttributes.getBoolean(R.styleable.RectMaskView_rectVCenter, false);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public int getStrokeWidth() {
        return this.strokeWidth;
    }

    public void setStrokeWidth(int i) {
        this.strokeWidth = i;
    }

    public int getRectRoundCx() {
        return this.rectRoundCx;
    }

    public void setRectRoundCx(int i) {
        this.rectRoundCx = i;
    }

    public void setRectColor(int i) {
        this.rectColor = i;
    }

    public int getRectColor() {
        return this.rectColor;
    }

    public void setRectLeft(int i) {
        this.rectLeft = i;
    }

    public float getRectLeft() {
        return this.rectLeft;
    }

    public void setRectTop(int i) {
        this.rectTop = i;
    }

    public float getRectTop() {
        return this.rectTop;
    }

    public void setRectWidth(int i) {
        this.rectWidth = i;
    }

    public float getRectWidth() {
        return this.rectWidth;
    }

    public void setRectHeight(int i) {
        this.rectHeight = i;
    }

    public float getRectHeigth() {
        return this.rectHeight;
    }

    private void sharedConstructor() {
        this.mRectHolePaint = new Paint(1);
        this.mRectStrokePaint = new Paint(1);
    }

    @Override // android.view.View
    public void invalidate() {
        this.mWeakBitmap = null;
        Bitmap bitmap = this.mMaskBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        super.invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable drawable;
        if (!isInEditMode()) {
            int iSaveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null, 31);
            try {
                Bitmap bitmapCreateBitmap = this.mWeakBitmap != null ? this.mWeakBitmap.get() : null;
                float f = 0.0f;
                if ((bitmapCreateBitmap == null || bitmapCreateBitmap.isRecycled()) && (drawable = getDrawable()) != null) {
                    try {
                        bitmapCreateBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas2 = new Canvas(bitmapCreateBitmap);
                        drawable.setBounds(0, 0, getWidth(), getHeight());
                        drawable.draw(canvas2);
                        if (this.mMaskBitmap == null || this.mMaskBitmap.isRecycled()) {
                            this.mMaskBitmap = createMask1();
                        }
                        this.mRectHolePaint.reset();
                        this.mRectHolePaint.setFilterBitmap(false);
                        this.mRectHolePaint.setXfermode(SXFERMODE);
                        canvas2.drawBitmap(this.mMaskBitmap, 0.0f, 0.0f, this.mRectHolePaint);
                        this.mWeakBitmap = new WeakReference<>(bitmapCreateBitmap);
                    } catch (OutOfMemoryError unused) {
                        System.gc();
                        return;
                    }
                }
                if (bitmapCreateBitmap != null) {
                    this.mRectHolePaint.setXfermode(null);
                    canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, this.mRectHolePaint);
                    if (-1 != this.rectColor) {
                        float f2 = this.rectTop;
                        if (f2 < 0.0f) {
                            f2 = 0.0f;
                        }
                        float f3 = this.rectLeft;
                        if (f3 >= 0.0f) {
                            f = f3;
                        }
                        RectF rectF = new RectF(f, f2, this.rectWidth + f, this.rectHeight + f2);
                        this.mRectStrokePaint.setColor(this.rectColor);
                        this.mRectStrokePaint.setStrokeWidth(this.strokeWidth);
                        this.mRectStrokePaint.setStyle(Paint.Style.STROKE);
                        canvas.drawRoundRect(rectF, this.rectRoundCx, this.rectRoundCx, this.mRectStrokePaint);
                    }
                    return;
                }
            } catch (Exception unused2) {
            } finally {
                canvas.restoreToCount(iSaveLayer);
            }
            return;
        }
        super.onDraw(canvas);
    }

    public Bitmap createMask1() {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(1);
        paint.setAntiAlias(true);
        paint.setColor(-1);
        float width = getWidth();
        float height = getHeight();
        float f = this.rectLeft;
        if (f <= -1.0f) {
            f = 0.0f;
        }
        if (this.rectHCenter) {
            f = (width / 2.0f) - (this.rectWidth / 2.0f);
            if (f < 0.0f) {
                f = 0.0f;
            }
            this.rectLeft = f;
        }
        float f2 = (height - width) / 2.0f;
        float f3 = this.rectTop;
        if (f3 > -1.0f) {
            f2 = f3;
        }
        if (this.rectVCenter) {
            float f4 = (height / 2.0f) - (this.rectHeight / 2.0f);
            float f5 = f4 >= 0.0f ? f4 : 0.0f;
            this.rectTop = f5;
            f2 = f5;
        }
        float f6 = this.rectWidth;
        float f7 = f6 > -1.0f ? f6 + f : width;
        float f8 = width + f2;
        float f9 = this.rectHeight;
        if (f9 > -1.0f) {
            f8 = f2 + f9;
        }
        RectF rectF = new RectF(f, f2, f7, f8);
        int i = this.rectRoundCx;
        canvas.drawRoundRect(rectF, i, i, paint);
        return bitmapCreateBitmap;
    }
}
