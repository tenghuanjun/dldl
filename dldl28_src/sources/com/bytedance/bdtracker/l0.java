package com.bytedance.bdtracker;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.view.InputDeviceCompat;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final class l0 extends m0 {
    public final Paint b;

    public l0(Drawable drawable) {
        super(drawable);
        Paint paint = new Paint();
        this.b = paint;
        paint.setColor(InputDeviceCompat.SOURCE_ANY);
        paint.setStrokeWidth(8.0f);
        paint.setStyle(Paint.Style.STROKE);
    }

    public final void a(int i) {
        this.b.setColor(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        Drawable drawable = this.f291a;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        canvas.drawRect(new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), this.b);
    }
}
