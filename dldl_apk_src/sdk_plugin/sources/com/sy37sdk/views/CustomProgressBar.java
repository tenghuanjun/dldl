package com.sy37sdk.views;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.sy37sdk.utils.Util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class CustomProgressBar extends ImageView {
    private Activity activity;
    private final int duration;
    private AnimationDrawable loadingAnimationDrawable;
    private Bitmap loadingBitmap;

    public CustomProgressBar(Activity activity) {
        super(activity);
        this.duration = 100;
        this.activity = activity;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.loadingBitmap = BitmapFactory.decodeResource(getResources(), Util.getIdByName("sy37_loading", "drawable", activity.getPackageName(), activity));
        this.loadingAnimationDrawable = new AnimationDrawable();
        for (int i = 0; i <= 360; i += 30) {
            this.loadingAnimationDrawable.addFrame(adjustDrawableRotation(i), 100);
        }
        setImageDrawable(this.loadingAnimationDrawable);
        this.loadingAnimationDrawable.setOneShot(false);
        this.loadingAnimationDrawable.start();
    }

    private Drawable adjustDrawableRotation(int i) {
        PaintFlagsDrawFilter paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, 3);
        Matrix matrix = new Matrix();
        matrix.setRotate(i, this.loadingBitmap.getWidth() / 2.0f, this.loadingBitmap.getHeight() / 2.0f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.loadingBitmap.getHeight(), this.loadingBitmap.getWidth(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.setDrawFilter(paintFlagsDrawFilter);
        canvas.drawBitmap(this.loadingBitmap, matrix, paint);
        return new BitmapDrawable(getResources(), bitmapCreateBitmap);
    }
}
