package com.duowan.kiwi.barrage.stencil;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import com.duowan.ark.util.pack.Unpack;
import com.duowan.kiwi.barrage.config.BarrageLog;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class StencilPainter {
    private static final int BLUR_RADIUS = 25;
    private static final String TAG = "StencilPainter";
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private ArrayList<float[]> mCoors;
    private int mInnerColor;
    private Paint mInnerPaint;
    private float mOutHeight;
    private float mOutWidth;
    private int mOuterColor;
    private Paint mOuterPaint;
    private Path mPath;
    private Paint mPathPaint;
    private BlurMaskFilter mNormalBlurMaskFilter = new BlurMaskFilter(25.0f, BlurMaskFilter.Blur.NORMAL);
    private BlurMaskFilter mSolidBlurMaskFilter = new BlurMaskFilter(25.0f, BlurMaskFilter.Blur.SOLID);
    private Rect mRectTemp = null;

    StencilPainter(int i, int i2) {
        this.mOutWidth = i;
        this.mOutHeight = i2;
        this.mBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        initPainter();
    }

    void initPainter() {
        this.mOuterColor = Color.parseColor("#00000000");
        this.mInnerColor = Color.parseColor("#FFFFFFFF");
        Paint paint = new Paint();
        this.mInnerPaint = paint;
        paint.setColor(this.mInnerColor);
        this.mInnerPaint.setStrokeWidth(1.0f);
        this.mInnerPaint.setStyle(Paint.Style.FILL);
        this.mInnerPaint.setMaskFilter(this.mSolidBlurMaskFilter);
        this.mPath = new Path();
        Canvas canvas = new Canvas();
        this.mCanvas = canvas;
        canvas.setBitmap(this.mBitmap);
    }

    synchronized void setRect(Rect rect) {
        this.mRectTemp = rect;
    }

    synchronized boolean hasRectStencil() {
        return this.mRectTemp != null;
    }

    Bitmap drawRectStencil() {
        if (this.mRectTemp == null) {
            return null;
        }
        synchronized (this) {
            if (this.mRectTemp == null) {
                return null;
            }
            this.mBitmap.eraseColor(0);
            this.mCanvas.drawRect(this.mRectTemp, this.mInnerPaint);
            return this.mBitmap;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    android.graphics.Bitmap drawStencil(byte[] r10) {
        /*
            Method dump skipped, instruction units count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.kiwi.barrage.stencil.StencilPainter.drawStencil(byte[]):android.graphics.Bitmap");
    }

    private ArrayList<float[]> realParseSeiData(byte[] bArr) {
        Unpack unpack;
        int i;
        Unpack unpack2 = new Unpack(bArr);
        int iPopInt = unpack2.popInt();
        ArrayList<float[]> arrayList = new ArrayList<>();
        int i2 = 0;
        int i3 = 0;
        while (i3 < iPopInt) {
            int iPopInt2 = unpack2.popInt();
            if (iPopInt2 > 33) {
                char[] cArr = new char[4];
                cArr[i2] = (char) unpack2.popUint8().toInt();
                cArr[1] = (char) unpack2.popUint8().toInt();
                cArr[2] = (char) unpack2.popUint8().toInt();
                cArr[3] = (char) unpack2.popUint8().toInt();
                String str = new String(cArr);
                if (!"HUYA".equals(str)) {
                    Object[] objArr = new Object[1];
                    objArr[i2] = str;
                    BarrageLog.error(TAG, "prefix is not HUYA: %s", objArr);
                    return null;
                }
                unpack2.popUint8().toInt();
                unpack2.popUint64();
                unpack2.popUint8();
                unpack2.popUint64();
                unpack2.popUint64();
                unpack2.popUint8();
                if (unpack2.popUint8().toInt() != 1) {
                    return null;
                }
                boolean z = unpack2.popUint8().toInt() == 1;
                if (!z) {
                    Object[] objArr2 = new Object[1];
                    objArr2[i2] = Boolean.valueOf(z);
                    BarrageLog.debug(TAG, "has changed: %b", objArr2);
                    return null;
                }
                int i4 = unpack2.popUint16().toInt();
                if (i4 <= 0) {
                    Object[] objArr3 = new Object[1];
                    objArr3[i2] = Integer.valueOf(i4);
                    BarrageLog.error(TAG, "parse data error originalWidth == %d", objArr3);
                    return null;
                }
                int i5 = unpack2.popUint16().toInt();
                if (i5 <= 0) {
                    Object[] objArr4 = new Object[1];
                    objArr4[i2] = Integer.valueOf(i5);
                    BarrageLog.error(TAG, "parse data error originalHeight == %d", objArr4);
                    return null;
                }
                float f = unpack2.popUint32().toInt() / 1000000.0f;
                float f2 = unpack2.popUint32().toInt() / 1000000.0f;
                int i6 = unpack2.popUint8().toInt();
                int i7 = 0;
                while (i7 < i6) {
                    int i8 = unpack2.popUint8().toInt() * 2;
                    float[] fArr = new float[i8];
                    while (i2 < i8) {
                        fArr[i2] = (unpack2.popUint8().toInt() * f) / i4;
                        fArr[i2 + 1] = (unpack2.popUint8().toInt() * f2) / i5;
                        i2 += 2;
                        iPopInt = iPopInt;
                        unpack2 = unpack2;
                    }
                    arrayList.add(fArr);
                    i7++;
                    i2 = 0;
                }
                unpack = unpack2;
                i = iPopInt;
                i2 = 0;
                BarrageLog.debug(TAG, "seiDataCount: %s, xFactor: %f, yFactor: %f, polyCount: %d", Integer.valueOf(iPopInt2), Float.valueOf(f), Float.valueOf(f2), Integer.valueOf(i6));
            } else {
                if (i3 + 1 >= iPopInt) {
                    Object[] objArr5 = new Object[1];
                    objArr5[i2] = Integer.valueOf(iPopInt2);
                    BarrageLog.error(TAG, "error seiDataCount = %d", objArr5);
                    return null;
                }
                for (int i9 = 0; i9 < iPopInt2; i9++) {
                    unpack2.popUint8();
                }
                unpack = unpack2;
                i = iPopInt;
            }
            i3++;
            iPopInt = i;
            unpack2 = unpack;
        }
        return arrayList;
    }
}
