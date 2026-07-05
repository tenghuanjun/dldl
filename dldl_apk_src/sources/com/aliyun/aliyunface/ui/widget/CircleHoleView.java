package com.aliyun.aliyunface.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class CircleHoleView extends ImageView {
    private static final Xfermode SXFERMODE = new PorterDuffXfermode(PorterDuff.Mode.XOR);
    private int floodColor;
    public float heightAttr;
    private boolean holeHCenter;
    private boolean holeVCenter;
    public float leftAttr;
    protected Context mContext;
    private Bitmap mMaskBitmap;
    private Paint mPaint;
    private WeakReference<Bitmap> mWeakBitmap;
    public float topAttr;
    public float widthAttr;

    public CircleHoleView(Context context) {
        super(context);
        this.leftAttr = -1.0f;
        this.topAttr = -1.0f;
        this.widthAttr = -1.0f;
        this.heightAttr = -1.0f;
        this.holeHCenter = false;
        this.holeVCenter = false;
        this.floodColor = -1;
        sharedConstructor();
    }

    public CircleHoleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.leftAttr = -1.0f;
        this.topAttr = -1.0f;
        this.widthAttr = -1.0f;
        this.heightAttr = -1.0f;
        this.holeHCenter = false;
        this.holeVCenter = false;
        this.floodColor = -1;
        initHoleAttrs(context, attributeSet);
        sharedConstructor();
    }

    public CircleHoleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.leftAttr = -1.0f;
        this.topAttr = -1.0f;
        this.widthAttr = -1.0f;
        this.heightAttr = -1.0f;
        this.holeHCenter = false;
        this.holeVCenter = false;
        this.floodColor = -1;
        initHoleAttrs(context, attributeSet);
        sharedConstructor();
    }

    private void initHoleAttrs(Context context, AttributeSet attributeSet) {
        this.leftAttr = 0.0f;
        this.topAttr = context.getResources().getDimension(context.getResources().getIdentifier("toyger_circle_tips_margin_top", "dimen", context.getPackageName()));
        this.widthAttr = context.getResources().getDimension(context.getResources().getIdentifier("toyger_circle_surfaceview_width", "dimen", context.getPackageName()));
        this.heightAttr = context.getResources().getDimension(context.getResources().getIdentifier("toyger_circle_surfaceview_width", "dimen", context.getPackageName()));
    }

    private void sharedConstructor() {
        this.mPaint = new Paint(1);
    }

    public void changeBackColor(int i) {
        this.floodColor = i;
        invalidate();
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
        Log.e("xxonDraw", "onDraw Come...");
        if (!isInEditMode()) {
            int iSaveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), (Paint) null, 31);
            try {
                Bitmap bitmapCreateBitmap = this.mWeakBitmap != null ? this.mWeakBitmap.get() : null;
                if ((bitmapCreateBitmap == null || bitmapCreateBitmap.isRecycled()) && (drawable = getDrawable()) != null) {
                    try {
                        bitmapCreateBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas2 = new Canvas(bitmapCreateBitmap);
                        drawable.setBounds(0, 0, getWidth(), getHeight());
                        drawable.draw(canvas2);
                        if (this.mMaskBitmap == null || this.mMaskBitmap.isRecycled()) {
                            this.mMaskBitmap = createMask1();
                        }
                        this.mPaint.reset();
                        this.mPaint.setFilterBitmap(false);
                        this.mPaint.setXfermode(SXFERMODE);
                        canvas2.drawColor(this.floodColor);
                        canvas2.drawBitmap(this.mMaskBitmap, 0.0f, 0.0f, this.mPaint);
                        this.mWeakBitmap = new WeakReference<>(bitmapCreateBitmap);
                    } catch (OutOfMemoryError unused) {
                        System.gc();
                        return;
                    }
                }
                if (bitmapCreateBitmap != null) {
                    this.mPaint.setXfermode((Xfermode) null);
                    canvas.drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, this.mPaint);
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
        Log.e("xxDraw", "createMask1");
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(1);
        paint.setAntiAlias(true);
        paint.setColor(-1);
        float width = getWidth();
        float height = getHeight();
        float f = this.leftAttr;
        float f2 = 0.0f;
        if (f <= -1.0f) {
            f = 0.0f;
        }
        if (this.holeHCenter) {
            f = (width / 2.0f) - (this.widthAttr / 2.0f);
            if (f < 0.0f) {
                f = 0.0f;
            }
        }
        float f3 = (height - width) / 2.0f;
        float f4 = this.topAttr;
        if (f4 > -1.0f) {
            f3 = f4;
        }
        if (this.holeVCenter) {
            float f5 = (height / 2.0f) - (this.heightAttr / 2.0f);
            if (f5 >= 0.0f) {
                f2 = f5;
            }
        } else {
            f2 = f3;
        }
        float f6 = this.widthAttr;
        float f7 = f6 > -1.0f ? f6 + f : width;
        float f8 = width + f2;
        float f9 = this.heightAttr;
        if (f9 > -1.0f) {
            f8 = f2 + f9;
        }
        canvas.drawOval(new RectF(f, f2, f7, f8), paint);
        return bitmapCreateBitmap;
    }
}
