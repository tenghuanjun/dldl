package com.sqwan.common.web;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.sqwan.common.util.DisplayUtil;
import com.sqwan.msdk.config.MultiConfigManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ScaleRoundAnimView extends ImageView {
    private static final String PROPERTY_RADIUS_X = "property_radius_x";
    private static final String PROPERTY_RADIUS_Y = "property_radius_y";
    private AnimFinishListener mAnimFinishListener;
    private RectF mAnimRect;
    private int mBitmapHeight;
    private int mBitmapWidth;
    private ExitAnimParams mExitAnimParams;
    private Paint mPaint;
    private int mRadiusX;
    private int mRadiusY;

    public interface AnimFinishListener {
        void onAnimFinish();
    }

    public ScaleRoundAnimView(Context context) {
        this(context, null);
    }

    public ScaleRoundAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScaleRoundAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRadiusX = 0;
        this.mRadiusY = 0;
        this.mBitmapWidth = 0;
        this.mBitmapHeight = 0;
        init();
    }

    public void setParams(ExitAnimParams exitAnimParams) {
        this.mExitAnimParams = exitAnimParams;
    }

    private void init() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setDither(true);
        this.mPaint.setAntiAlias(true);
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        this.mBitmapWidth = bitmap.getWidth();
        this.mBitmapHeight = bitmap.getHeight();
        super.setImageBitmap(getRoundedCornerBitmap(bitmap));
        this.mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        this.mAnimRect = new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight());
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(this.mAnimRect, this.mRadiusX, this.mRadiusY, this.mPaint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scale() {
        getLocationOnScreen(new int[2]);
        setPivotX((this.mExitAnimParams.x / (1.0f - (this.mExitAnimParams.width / this.mBitmapWidth))) - r1[0]);
        setPivotY(((this.mExitAnimParams.y / (1.0f - (this.mExitAnimParams.height / this.mBitmapHeight))) - r1[1]) - (MultiConfigManager.getInstance().isLandscape() ? 0 : DisplayUtil.dip2px(getContext(), 34.0f)));
        AnimatorSet animatorSet = new AnimatorSet();
        PropertyValuesHolder propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(PROPERTY_RADIUS_X, 0, this.mBitmapWidth / 2);
        PropertyValuesHolder propertyValuesHolderOfInt2 = PropertyValuesHolder.ofInt(PROPERTY_RADIUS_Y, 0, this.mBitmapHeight / 2);
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setValues(propertyValuesHolderOfInt, propertyValuesHolderOfInt2);
        valueAnimator.setDuration(300L);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sqwan.common.web.-$$Lambda$ScaleRoundAnimView$GNz4uXqjxzm6IzsXnss6Rq2nL-k
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f$0.lambda$scale$0$ScaleRoundAnimView(valueAnimator2);
            }
        });
        animatorSet.playTogether(ObjectAnimator.ofFloat(this, "scaleX", 1.0f, this.mExitAnimParams.width / this.mBitmapWidth).setDuration(300L), ObjectAnimator.ofFloat(this, "scaleY", 1.0f, this.mExitAnimParams.height / this.mBitmapHeight).setDuration(300L), valueAnimator);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.sqwan.common.web.ScaleRoundAnimView.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ScaleRoundAnimView.this.round();
            }
        });
        animatorSet.start();
    }

    public /* synthetic */ void lambda$scale$0$ScaleRoundAnimView(ValueAnimator valueAnimator) {
        this.mRadiusX = ((Integer) valueAnimator.getAnimatedValue(PROPERTY_RADIUS_X)).intValue();
        this.mRadiusY = ((Integer) valueAnimator.getAnimatedValue(PROPERTY_RADIUS_Y)).intValue();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void round() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(this, "alpha", 1.0f, 0.0f).setDuration(500L));
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.sqwan.common.web.ScaleRoundAnimView.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (ScaleRoundAnimView.this.mAnimFinishListener != null) {
                    ScaleRoundAnimView.this.mAnimFinishListener.onAnimFinish();
                }
            }
        });
        animatorSet.start();
    }

    public void start() {
        post(new Runnable() { // from class: com.sqwan.common.web.-$$Lambda$ScaleRoundAnimView$osUwgjrJUGfABHcwLbUNA7pd76k
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.scale();
            }
        });
    }

    public void setAnimFinishListener(AnimFinishListener animFinishListener) {
        this.mAnimFinishListener = animFinishListener;
    }

    private Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, 100.0f, 100.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return bitmapCreateBitmap;
    }
}
