package com.google.zxing.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.google.zxing.R;
import com.google.zxing.ResultPoint;
import com.google.zxing.camera.CameraManager;
import java.util.Collection;
import java.util.HashSet;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class ViewfinderView extends View {
    private static final long ANIMATION_DELAY = 10;
    private static final int CORNER_RECT_HEIGHT = 50;
    private static final int CORNER_RECT_WIDTH = 10;
    private static final int OPAQUE = 255;
    private static final int SCANNER_LINE_HEIGHT = 10;
    private static final int SCANNER_LINE_MOVE_DISTANCE = 5;
    private final int cornerColor;
    private final int frameColor;
    private final String labelText;
    private final int labelTextColor;
    private final float labelTextMarginTop;
    private final float labelTextSize;
    private final int laserColor;
    private Collection<ResultPoint> lastPossibleResultPoints;
    private final int maskColor;
    private final Paint paint;
    private Collection<ResultPoint> possibleResultPoints;
    private Bitmap resultBitmap;
    private final int resultColor;
    private final int resultPointColor;
    private int scannerAlpha;
    private static final int[] SCANNER_ALPHA = {0, 64, 128, 192, 255, 192, 128, 64};
    public static int scannerStart = 0;
    public static int scannerEnd = 0;

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ViewfinderView);
        this.laserColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_laser_color, 65280);
        this.cornerColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_corner_color, 65280);
        this.frameColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_frame_color, 16777215);
        this.resultPointColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_result_point_color, -1056964864);
        this.maskColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_mask_color, 1610612736);
        this.resultColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_result_color, -1342177280);
        this.labelTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.ViewfinderView_label_text_color, -1862270977);
        this.labelText = typedArrayObtainStyledAttributes.getString(R.styleable.ViewfinderView_label_text);
        this.labelTextSize = typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_label_text_size, 36.0f);
        this.labelTextMarginTop = typedArrayObtainStyledAttributes.getDimension(R.styleable.ViewfinderView_label_text_margin_top, 0.0f);
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.scannerAlpha = 0;
        this.possibleResultPoints = new HashSet(5);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Rect framingRect = CameraManager.get().getFramingRect();
        if (framingRect == null) {
            return;
        }
        if (scannerStart == 0 || scannerEnd == 0) {
            scannerStart = framingRect.top;
            scannerEnd = framingRect.bottom;
        }
        drawExterior(canvas, framingRect, canvas.getWidth(), canvas.getHeight());
        if (this.resultBitmap != null) {
            this.paint.setAlpha(255);
            canvas.drawBitmap(this.resultBitmap, framingRect.left, framingRect.top, this.paint);
            return;
        }
        drawCorner(canvas, framingRect);
        drawTextInfo(canvas, framingRect);
        drawLaserScanner(canvas, framingRect);
        Collection<ResultPoint> collection = this.possibleResultPoints;
        Collection<ResultPoint> collection2 = this.lastPossibleResultPoints;
        if (collection.isEmpty()) {
            this.lastPossibleResultPoints = null;
        } else {
            this.possibleResultPoints = new HashSet(5);
            this.lastPossibleResultPoints = collection;
            this.paint.setAlpha(255);
            this.paint.setColor(this.resultPointColor);
            for (ResultPoint resultPoint : collection) {
                canvas.drawCircle(framingRect.left + resultPoint.getX(), framingRect.top + resultPoint.getY(), 6.0f, this.paint);
            }
        }
        if (collection2 != null) {
            this.paint.setAlpha(TTDownloadField.CALL_DOWNLOAD_MODEL_SET_EXTRA_VALUE);
            this.paint.setColor(this.resultPointColor);
            for (ResultPoint resultPoint2 : collection2) {
                canvas.drawCircle(framingRect.left + resultPoint2.getX(), framingRect.top + resultPoint2.getY(), 3.0f, this.paint);
            }
        }
        postInvalidateDelayed(ANIMATION_DELAY, framingRect.left, framingRect.top, framingRect.right, framingRect.bottom);
    }

    private void drawTextInfo(Canvas canvas, Rect rect) {
        this.paint.setColor(this.labelTextColor);
        this.paint.setTextSize(this.labelTextSize);
        this.paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(this.labelText, rect.left + (rect.width() / 2), rect.bottom + 75.0f + this.labelTextMarginTop, this.paint);
    }

    private void drawCorner(Canvas canvas, Rect rect) {
        this.paint.setColor(this.cornerColor);
        canvas.drawRect(rect.left, rect.top, rect.left + 10, rect.top + 50, this.paint);
        canvas.drawRect(rect.left, rect.top, rect.left + 50, rect.top + 10, this.paint);
        canvas.drawRect(rect.right - 10, rect.top, rect.right, rect.top + 50, this.paint);
        canvas.drawRect(rect.right - 50, rect.top, rect.right, rect.top + 10, this.paint);
        canvas.drawRect(rect.left, rect.bottom - 10, rect.left + 50, rect.bottom, this.paint);
        canvas.drawRect(rect.left, rect.bottom - 50, rect.left + 10, rect.bottom, this.paint);
        canvas.drawRect(rect.right - 10, rect.bottom - 50, rect.right, rect.bottom, this.paint);
        canvas.drawRect(rect.right - 50, rect.bottom - 10, rect.right, rect.bottom, this.paint);
    }

    private void drawLaserScanner(Canvas canvas, Rect rect) {
        this.paint.setColor(this.laserColor);
        LinearGradient linearGradient = new LinearGradient(rect.left, scannerStart, rect.left, scannerStart + 10, shadeColor(this.laserColor), this.laserColor, Shader.TileMode.MIRROR);
        float fWidth = rect.left + (rect.width() / 2);
        float f = scannerStart + 5;
        int i = this.laserColor;
        RadialGradient radialGradient = new RadialGradient(fWidth, f, 360.0f, i, shadeColor(i), Shader.TileMode.MIRROR);
        new SweepGradient(rect.left + (rect.width() / 2), scannerStart + 10, shadeColor(this.laserColor), this.laserColor);
        new ComposeShader(radialGradient, linearGradient, PorterDuff.Mode.ADD);
        this.paint.setShader(radialGradient);
        if (scannerStart <= scannerEnd) {
            canvas.drawOval(new RectF(rect.left + 20, scannerStart, rect.right - 20, scannerStart + 10), this.paint);
            scannerStart += 5;
        } else {
            scannerStart = rect.top;
        }
        this.paint.setShader(null);
    }

    public int shadeColor(int i) {
        return Integer.valueOf("20" + Integer.toHexString(i).substring(2), 16).intValue();
    }

    private void drawFrame(Canvas canvas, Rect rect) {
        this.paint.setColor(this.frameColor);
        canvas.drawRect(rect.left, rect.top, rect.right + 1, rect.top + 2, this.paint);
        canvas.drawRect(rect.left, rect.top + 2, rect.left + 2, rect.bottom - 1, this.paint);
        canvas.drawRect(rect.right - 1, rect.top, rect.right + 1, rect.bottom - 1, this.paint);
        canvas.drawRect(rect.left, rect.bottom - 1, rect.right + 1, rect.bottom + 1, this.paint);
    }

    private void drawExterior(Canvas canvas, Rect rect, int i, int i2) {
        this.paint.setColor(this.resultBitmap != null ? this.resultColor : this.maskColor);
        float f = i;
        canvas.drawRect(0.0f, 0.0f, f, rect.top, this.paint);
        canvas.drawRect(0.0f, rect.top, rect.left, rect.bottom + 1, this.paint);
        canvas.drawRect(rect.right + 1, rect.top, f, rect.bottom + 1, this.paint);
        canvas.drawRect(0.0f, rect.bottom + 1, f, i2, this.paint);
    }

    public void drawViewfinder() {
        this.resultBitmap = null;
        invalidate();
    }

    public void drawResultBitmap(Bitmap bitmap) {
        this.resultBitmap = bitmap;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint resultPoint) {
        this.possibleResultPoints.add(resultPoint);
    }
}
