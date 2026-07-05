package com.aliyun.aliyunface.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class iOSLoadingView extends View {
    private static final String TAG = iOSLoadingView.class.getSimpleName();
    private String[] color;
    private int heigheRect;
    private int height;
    private int pos;
    private Rect rect;
    private Paint rectPaint;
    private int width;
    private int widthRect;

    public iOSLoadingView(Context context) {
        this(context, null);
    }

    public iOSLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public iOSLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.pos = 0;
        this.color = new String[]{"#bbbbbb", "#aaaaaa", "#999999", "#888888", "#777777", "#666666"};
        init();
    }

    private void init() {
        this.rectPaint = new Paint(1);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE || mode2 == Integer.MIN_VALUE) {
            this.width = 200;
        } else {
            this.width = View.MeasureSpec.getSize(i);
            int size = View.MeasureSpec.getSize(i2);
            this.height = size;
            this.width = Math.min(this.width, size);
        }
        int i3 = this.width;
        int i4 = i3 / 12;
        this.widthRect = i4;
        this.heigheRect = i4 * 4;
        setMeasuredDimension(i3, i3);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.rect == null) {
            int i = this.width;
            int i2 = this.widthRect;
            this.rect = new Rect((i - i2) / 2, 0, (i + i2) / 2, this.heigheRect);
        }
        for (int i3 = 0; i3 < 12; i3++) {
            int i4 = this.pos;
            if (i3 - i4 >= 5) {
                this.rectPaint.setColor(Color.parseColor(this.color[5]));
            } else if (i3 - i4 >= 0 && i3 - i4 < 5) {
                this.rectPaint.setColor(Color.parseColor(this.color[i3 - i4]));
            } else {
                int i5 = this.pos;
                if (i3 - i5 >= -7 && i3 - i5 < 0) {
                    this.rectPaint.setColor(Color.parseColor(this.color[5]));
                } else {
                    int i6 = this.pos;
                    if (i3 - i6 >= -11 && i3 - i6 < -7) {
                        this.rectPaint.setColor(Color.parseColor(this.color[(i3 + 12) - i6]));
                    }
                }
            }
            canvas.drawRect(this.rect, this.rectPaint);
            int i7 = this.width;
            canvas.rotate(30.0f, i7 / 2, i7 / 2);
        }
        int i8 = this.pos + 1;
        this.pos = i8;
        if (i8 > 11) {
            this.pos = 0;
        }
        postInvalidateDelayed(100L);
    }
}
