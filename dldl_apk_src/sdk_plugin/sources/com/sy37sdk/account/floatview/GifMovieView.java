package com.sy37sdk.account.floatview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.os.SystemClock;
import android.view.View;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class GifMovieView extends View {
    private static final int DEFAULT_MOVIEW_DURATION = 1000;
    private int mCurrentAnimationTime;
    private float mLeft;
    private int mMeasuredMovieHeight;
    private int mMeasuredMovieWidth;
    private Movie mMovie;
    private int mMovieResourceId;
    private long mMovieStart;
    private volatile boolean mPaused;
    private float mScale;
    private float mTop;
    private boolean mVisible;

    public GifMovieView(Context context) {
        super(context);
        this.mCurrentAnimationTime = 0;
        this.mPaused = false;
        this.mVisible = true;
        setLayerType(1, null);
    }

    public void setMovieResource(int i) {
        this.mMovieResourceId = i;
        this.mMovie = Movie.decodeStream(getResources().openRawResource(this.mMovieResourceId));
        requestLayout();
    }

    public void setMovieFilePath(String str) {
        this.mMovie = Movie.decodeFile(str);
        requestLayout();
    }

    public void setMovie(Movie movie) {
        this.mMovie = movie;
        requestLayout();
    }

    public Movie getMovie() {
        return this.mMovie;
    }

    public void setMovieTime(int i) {
        this.mCurrentAnimationTime = i;
        invalidate();
    }

    public void setPaused(boolean z) {
        this.mPaused = z;
        if (!z) {
            this.mMovieStart = SystemClock.uptimeMillis() - ((long) this.mCurrentAnimationTime);
        }
        invalidate();
    }

    public boolean isPaused() {
        return this.mPaused;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size;
        int size2;
        Movie movie = this.mMovie;
        if (movie != null) {
            int iWidth = movie.width();
            int iHeight = this.mMovie.height();
            float fMax = 1.0f / Math.max((View.MeasureSpec.getMode(i) == 0 || iWidth <= (size2 = View.MeasureSpec.getSize(i))) ? 1.0f : iWidth / size2, (View.MeasureSpec.getMode(i2) == 0 || iHeight <= (size = View.MeasureSpec.getSize(i2))) ? 1.0f : iHeight / size);
            this.mScale = fMax;
            int i3 = (int) (iWidth * fMax);
            this.mMeasuredMovieWidth = i3;
            int i4 = (int) (iHeight * fMax);
            this.mMeasuredMovieHeight = i4;
            setMeasuredDimension(i3, i4);
            return;
        }
        setMeasuredDimension(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mLeft = (getWidth() - this.mMeasuredMovieWidth) / 2.0f;
        this.mTop = (getHeight() - this.mMeasuredMovieHeight) / 2.0f;
        this.mVisible = getVisibility() == 0;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mMovie != null) {
            if (!this.mPaused) {
                updateAnimationTime();
                drawMovieFrame(canvas);
                invalidateView();
                return;
            }
            drawMovieFrame(canvas);
        }
    }

    private void invalidateView() {
        if (this.mVisible) {
            if (Build.VERSION.SDK_INT >= 16) {
                postInvalidateOnAnimation();
            } else {
                invalidate();
            }
        }
    }

    private void updateAnimationTime() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.mMovieStart == 0) {
            this.mMovieStart = jUptimeMillis;
        }
        int iDuration = this.mMovie.duration();
        if (iDuration == 0) {
            iDuration = 1000;
        }
        this.mCurrentAnimationTime = (int) ((jUptimeMillis - this.mMovieStart) % ((long) iDuration));
    }

    private void drawMovieFrame(Canvas canvas) {
        this.mMovie.setTime(this.mCurrentAnimationTime);
        canvas.save();
        float f = this.mScale;
        canvas.scale(f, f);
        Movie movie = this.mMovie;
        float f2 = this.mLeft;
        float f3 = this.mScale;
        movie.draw(canvas, f2 / f3, this.mTop / f3);
        canvas.restore();
    }

    @Override // android.view.View
    public void onScreenStateChanged(int i) {
        super.onScreenStateChanged(i);
        this.mVisible = i == 1;
        invalidateView();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.mVisible = i == 0;
        invalidateView();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.mVisible = i == 0;
        invalidateView();
    }
}
