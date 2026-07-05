package com.sy37sdk.account.view.base.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.sq.tools.utils.ResourceUtils;
import com.sqwan.common.util.DensityUtil;
import com.sqwan.common.util.DisplayUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ScannerView extends View {
    private Rect frame;
    private Bitmap mScanline;
    private final int mScreenW;
    private int mSlideTop;
    private final Rect mTextRect;
    private final int maskColor;
    private final Paint maskPaint;
    private final Paint textPaint;

    public ScannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTextRect = new Rect();
        this.maskColor = Color.parseColor("#60000000");
        Paint paint = new Paint();
        this.maskPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.textPaint = paint2;
        paint2.setColor(Color.parseColor("#FFFFFF"));
        this.textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        this.textPaint.setTextSize(DensityUtil.dip2px(getContext(), 13.0f));
        this.mScreenW = DisplayUtil.getScreenWidth(context);
    }

    public void setFraming(Rect rect) {
        this.frame = rect;
        this.mSlideTop = rect.top;
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), ResourceUtils.getDrawableIdByName(getContext(), "sy37_scan_line"));
        this.mScanline = Bitmap.createScaledBitmap(bitmapDecodeResource, rect.width(), DensityUtil.dip2px(getContext(), 7.0f), false);
        bitmapDecodeResource.recycle();
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.frame == null) {
            return;
        }
        int width = getWidth();
        int height = getHeight();
        this.maskPaint.setColor(this.maskColor);
        float f = width;
        canvas.drawRect(0.0f, 0.0f, f, this.frame.top, this.maskPaint);
        canvas.drawRect(0.0f, this.frame.top, this.frame.left, this.frame.bottom + 1, this.maskPaint);
        canvas.drawRect(this.frame.right + 1, this.frame.top, f, this.frame.bottom + 1, this.maskPaint);
        canvas.drawRect(0.0f, this.frame.bottom + 1, f, height, this.maskPaint);
        this.textPaint.getTextBounds("将二维码放入框内，即可自动扫描", 0, 15, this.mTextRect);
        canvas.drawText("将二维码放入框内，即可自动扫描", this.mScreenW / 2.0f, this.frame.bottom + this.mTextRect.height() + DensityUtil.dip2px(getContext(), 26.0f), this.textPaint);
        int i = this.mSlideTop + 3;
        this.mSlideTop = i;
        if (i >= this.frame.bottom - 20) {
            this.mSlideTop = this.frame.top;
        }
        canvas.drawBitmap(this.mScanline, this.frame.left, this.mSlideTop, (Paint) null);
        postInvalidateDelayed(14L, this.frame.left, this.frame.top, this.frame.right, this.frame.bottom);
    }
}
