package master.flame.danmaku.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.DrawHelper;
import master.flame.danmaku.danmaku.model.AlphaValue;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.Duration;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.SpecialDanmaku;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.DanmakuFactory;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.util.DanmakuUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class FakeDanmakuView extends DanmakuView implements DrawHandler.Callback {
    private long mBeginTimeMills;
    private Bitmap mBufferBitmap;
    private Canvas mBufferCanvas;
    private long mEndTimeMills;
    private long mExpectBeginMills;
    private long mFrameIntervalMills;
    private int mHeight;
    private boolean mIsRelease;
    private OnFrameAvailableListener mOnFrameAvailableListener;
    private DanmakuTimer mOuterTimer;
    private int mRetryCount;
    private float mScale;
    private DanmakuTimer mTimer;
    private int mWidth;

    public interface OnFrameAvailableListener {
        void onConfig(DanmakuContext danmakuContext);

        void onFailed(int i, String str);

        void onFrameAvailable(long j, Bitmap bitmap);

        void onFramesFinished(long j);
    }

    @Override // master.flame.danmaku.controller.DrawHandler.Callback
    public void danmakuShown(BaseDanmaku baseDanmaku) {
    }

    @Override // master.flame.danmaku.controller.DrawHandler.Callback
    public void drawingFinished() {
    }

    @Override // master.flame.danmaku.ui.widget.DanmakuView, android.view.View, master.flame.danmaku.controller.IDanmakuView
    public boolean isShown() {
        return true;
    }

    @Override // master.flame.danmaku.ui.widget.DanmakuView, master.flame.danmaku.controller.IDanmakuViewController
    public boolean isViewReady() {
        return true;
    }

    @Override // master.flame.danmaku.ui.widget.DanmakuView, android.view.View
    protected void onDraw(Canvas canvas) {
    }

    @Override // master.flame.danmaku.controller.DrawHandler.Callback
    public void prepared() {
    }

    private class CustomParser extends BaseDanmakuParser {
        private final long edTime;
        private final BaseDanmakuParser mBaseParser;
        private float mDispScaleX;
        private float mDispScaleY;
        private int mViewWidth;
        private final long stTime;

        public CustomParser(BaseDanmakuParser baseDanmakuParser, long j, long j2) {
            this.mBaseParser = baseDanmakuParser;
            this.stTime = j;
            this.edTime = j2;
        }

        @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser
        protected IDanmakus parse() {
            IDanmakus danmakus;
            final Danmakus danmakus2 = new Danmakus();
            try {
                danmakus = this.mBaseParser.getDanmakus().subnew(this.stTime, this.edTime);
            } catch (Exception unused) {
                danmakus = this.mBaseParser.getDanmakus();
            }
            if (danmakus == null) {
                return danmakus2;
            }
            danmakus.forEach(new IDanmakus.Consumer<BaseDanmaku, Object>() { // from class: master.flame.danmaku.ui.widget.FakeDanmakuView.CustomParser.1
                @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
                public int accept(BaseDanmaku baseDanmaku) {
                    long time = baseDanmaku.getTime();
                    if (time < CustomParser.this.stTime) {
                        return 0;
                    }
                    if (time > CustomParser.this.edTime) {
                        return 1;
                    }
                    BaseDanmaku baseDanmakuCreateDanmaku = CustomParser.this.mContext.mDanmakuFactory.createDanmaku(baseDanmaku.getType(), CustomParser.this.mContext);
                    if (baseDanmakuCreateDanmaku != null) {
                        baseDanmakuCreateDanmaku.setTime(baseDanmaku.getTime());
                        DanmakuUtils.fillText(baseDanmakuCreateDanmaku, baseDanmaku.text);
                        baseDanmakuCreateDanmaku.textSize = baseDanmaku.textSize;
                        baseDanmakuCreateDanmaku.textColor = baseDanmaku.textColor;
                        baseDanmakuCreateDanmaku.textShadowColor = baseDanmaku.textShadowColor;
                        if (!(baseDanmaku instanceof SpecialDanmaku)) {
                            baseDanmakuCreateDanmaku.setTimer(CustomParser.this.mTimer);
                            baseDanmakuCreateDanmaku.mFilterParam = baseDanmaku.mFilterParam;
                            baseDanmakuCreateDanmaku.filterResetFlag = baseDanmaku.filterResetFlag;
                            baseDanmakuCreateDanmaku.flags = CustomParser.this.mContext.mGlobalFlagValues;
                            synchronized (danmakus2.obtainSynchronizer()) {
                                danmakus2.addItem(baseDanmakuCreateDanmaku);
                            }
                        } else {
                            SpecialDanmaku specialDanmaku = (SpecialDanmaku) baseDanmaku;
                            baseDanmakuCreateDanmaku.index = baseDanmaku.index;
                            baseDanmakuCreateDanmaku.duration = new Duration(specialDanmaku.getDuration());
                            baseDanmakuCreateDanmaku.rotationZ = specialDanmaku.rotateZ;
                            baseDanmakuCreateDanmaku.rotationY = specialDanmaku.rotationY;
                            ((SpecialDanmaku) baseDanmakuCreateDanmaku).isQuadraticEaseOut = specialDanmaku.isQuadraticEaseOut;
                            CustomParser.this.mContext.mDanmakuFactory.fillTranslationData(baseDanmakuCreateDanmaku, specialDanmaku.beginX, specialDanmaku.beginY, specialDanmaku.endX, specialDanmaku.endY, specialDanmaku.translationDuration, specialDanmaku.translationStartDelay, CustomParser.this.mDispScaleX, CustomParser.this.mDispScaleY);
                            CustomParser.this.mContext.mDanmakuFactory.fillAlphaData(baseDanmakuCreateDanmaku, specialDanmaku.beginAlpha, specialDanmaku.endAlpha, baseDanmakuCreateDanmaku.getDuration());
                            return 0;
                        }
                    }
                    return 0;
                }
            });
            return danmakus2;
        }

        @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser
        public BaseDanmakuParser setDisplayer(IDisplayer iDisplayer) {
            super.setDisplayer(iDisplayer);
            BaseDanmakuParser baseDanmakuParser = this.mBaseParser;
            if (baseDanmakuParser != null && baseDanmakuParser.getDisplayer() != null) {
                this.mDispScaleX = this.mDispWidth / this.mBaseParser.getDisplayer().getWidth();
                this.mDispScaleY = this.mDispHeight / this.mBaseParser.getDisplayer().getHeight();
                if (this.mViewWidth <= 1) {
                    this.mViewWidth = iDisplayer.getWidth();
                }
            }
            return this;
        }

        @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser
        protected float getViewportSizeFactor() {
            return (this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION * 1.1f) / ((((long) this.mViewWidth) * DanmakuFactory.COMMON_DANMAKU_DURATION) / 682.0f);
        }
    }

    public FakeDanmakuView(Context context) {
        super(context);
        this.mWidth = 0;
        this.mHeight = 0;
        this.mScale = 1.0f;
        this.mFrameIntervalMills = 16L;
        this.mRetryCount = 0;
        this.mExpectBeginMills = 0L;
    }

    public FakeDanmakuView(Context context, int i, int i2, float f) {
        super(context);
        this.mWidth = 0;
        this.mHeight = 0;
        this.mScale = 1.0f;
        this.mFrameIntervalMills = 16L;
        this.mRetryCount = 0;
        this.mExpectBeginMills = 0L;
        this.mWidth = i;
        this.mHeight = i2;
        this.mScale = f;
        initBufferCanvas(i, i2);
    }

    public void initBufferCanvas(int i, int i2) {
        this.mBufferBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        this.mBufferCanvas = new Canvas(this.mBufferBitmap);
    }

    @Override // master.flame.danmaku.ui.widget.DanmakuView, master.flame.danmaku.controller.IDanmakuViewController
    public long drawDanmakus() {
        Canvas canvas;
        Bitmap bitmapCreateScaledBitmap;
        DanmakuTimer danmakuTimer;
        if (this.mIsRelease || (canvas = this.mBufferCanvas) == null || (bitmapCreateScaledBitmap = this.mBufferBitmap) == null || bitmapCreateScaledBitmap.isRecycled()) {
            return 0L;
        }
        bitmapCreateScaledBitmap.eraseColor(0);
        if (this.mClearFlag) {
            DrawHelper.clearCanvas(canvas);
            this.mClearFlag = false;
        } else if (this.handler != null) {
            this.handler.draw(canvas);
        }
        OnFrameAvailableListener onFrameAvailableListener = this.mOnFrameAvailableListener;
        if (onFrameAvailableListener != null) {
            long j = this.mOuterTimer.currMillisecond;
            try {
                try {
                    if (j >= this.mExpectBeginMills - this.mFrameIntervalMills) {
                        boolean z = true;
                        if (this.mScale == 1.0f) {
                            z = false;
                        } else {
                            bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCreateScaledBitmap, (int) (this.mWidth * this.mScale), (int) (this.mHeight * this.mScale), true);
                        }
                        onFrameAvailableListener.onFrameAvailable(j, bitmapCreateScaledBitmap);
                        if (z) {
                            bitmapCreateScaledBitmap.recycle();
                        }
                    }
                } catch (Exception e) {
                    release();
                    onFrameAvailableListener.onFailed(101, e.getMessage());
                    if (j >= this.mEndTimeMills) {
                        release();
                        danmakuTimer = this.mTimer;
                        if (danmakuTimer != null) {
                        }
                        onFrameAvailableListener.onFramesFinished(j);
                    }
                }
                if (j >= this.mEndTimeMills) {
                    release();
                    danmakuTimer = this.mTimer;
                    if (danmakuTimer != null) {
                        danmakuTimer.update(this.mEndTimeMills);
                    }
                    onFrameAvailableListener.onFramesFinished(j);
                }
            } catch (Throwable th) {
                if (j >= this.mEndTimeMills) {
                    release();
                    DanmakuTimer danmakuTimer2 = this.mTimer;
                    if (danmakuTimer2 != null) {
                        danmakuTimer2.update(this.mEndTimeMills);
                    }
                    onFrameAvailableListener.onFramesFinished(j);
                }
                throw th;
            }
        }
        this.mRequestRender = false;
        return 2L;
    }

    @Override // master.flame.danmaku.ui.widget.DanmakuView, master.flame.danmaku.controller.IDanmakuView
    public void release() {
        this.mIsRelease = true;
        super.release();
        this.mBufferBitmap = null;
    }

    @Override // master.flame.danmaku.ui.widget.DanmakuView, master.flame.danmaku.controller.IDanmakuViewController
    public int getViewWidth() {
        return this.mWidth;
    }

    @Override // master.flame.danmaku.ui.widget.DanmakuView, master.flame.danmaku.controller.IDanmakuViewController
    public int getViewHeight() {
        return this.mHeight;
    }

    @Override // master.flame.danmaku.ui.widget.DanmakuView, master.flame.danmaku.controller.IDanmakuView
    public void prepare(BaseDanmakuParser baseDanmakuParser, DanmakuContext danmakuContext) {
        CustomParser customParser = new CustomParser(baseDanmakuParser, this.mBeginTimeMills, this.mEndTimeMills);
        try {
            DanmakuContext danmakuContext2 = (DanmakuContext) danmakuContext.clone();
            danmakuContext2.resetContext();
            danmakuContext2.transparency = AlphaValue.MAX;
            danmakuContext2.setDanmakuTransparency(danmakuContext.transparency / AlphaValue.MAX);
            danmakuContext2.mGlobalFlagValues.FILTER_RESET_FLAG = danmakuContext.mGlobalFlagValues.FILTER_RESET_FLAG;
            danmakuContext2.setDanmakuSync(null);
            danmakuContext2.unregisterAllConfigChangedCallbacks();
            danmakuContext2.mGlobalFlagValues.updateAll();
            danmakuContext = danmakuContext2;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        danmakuContext.updateMethod = (byte) 1;
        OnFrameAvailableListener onFrameAvailableListener = this.mOnFrameAvailableListener;
        if (onFrameAvailableListener != null) {
            onFrameAvailableListener.onConfig(danmakuContext);
        }
        super.prepare(customParser, danmakuContext);
        this.handler.setIdleSleep(false);
        this.handler.enableNonBlockMode(true);
    }

    public void setTimeRange(long j, long j2) {
        this.mExpectBeginMills = j;
        this.mBeginTimeMills = Math.max(0L, j - 30000);
        this.mEndTimeMills = j2;
    }

    public void setOnFrameAvailableListener(OnFrameAvailableListener onFrameAvailableListener) {
        this.mOnFrameAvailableListener = onFrameAvailableListener;
    }

    public void getFrameAtTime(final int i) {
        int i2 = this.mRetryCount;
        this.mRetryCount = i2 + 1;
        if (i2 > 5) {
            release();
            OnFrameAvailableListener onFrameAvailableListener = this.mOnFrameAvailableListener;
            if (onFrameAvailableListener != null) {
                onFrameAvailableListener.onFailed(100, "not prepared");
                return;
            }
            return;
        }
        if (!isPrepared()) {
            DrawHandler drawHandler = this.handler;
            if (drawHandler == null) {
                return;
            }
            drawHandler.postDelayed(new Runnable() { // from class: master.flame.danmaku.ui.widget.FakeDanmakuView.1
                @Override // java.lang.Runnable
                public void run() {
                    FakeDanmakuView.this.getFrameAtTime(i);
                }
            }, 1000L);
            return;
        }
        this.mFrameIntervalMills = 1000 / i;
        setCallback(this);
        long jMax = Math.max(0L, this.mExpectBeginMills - ((getConfig().mDanmakuFactory.MAX_DANMAKU_DURATION * 3) / 2));
        this.mOuterTimer = new DanmakuTimer(jMax);
        start(jMax);
    }

    @Override // master.flame.danmaku.controller.DrawHandler.Callback
    public void updateTimer(DanmakuTimer danmakuTimer) {
        this.mTimer = danmakuTimer;
        danmakuTimer.update(this.mOuterTimer.currMillisecond);
        this.mOuterTimer.add(this.mFrameIntervalMills);
        danmakuTimer.add(this.mFrameIntervalMills);
    }
}
