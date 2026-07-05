package master.flame.danmaku.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Choreographer;
import java.util.LinkedList;
import master.flame.danmaku.controller.IDrawTask;
import master.flame.danmaku.danmaku.model.AbsDanmakuSync;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.renderer.IRenderer;
import master.flame.danmaku.danmaku.util.SystemClock;
import tv.cjump.jni.DeviceUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class DrawHandler extends Handler {
    private static final int CLEAR_DANMAKUS_ON_SCREEN = 13;
    private static final int FORCE_RENDER = 14;
    private static final int HIDE_DANMAKUS = 9;
    private static final long INDEFINITE_TIME = 10000000;
    private static final int MAX_RECORD_SIZE = 500;
    private static final int NOTIFY_DISP_SIZE_CHANGED = 10;
    private static final int NOTIFY_RENDERING = 11;
    private static final int PAUSE = 7;
    public static final int PREPARE = 5;
    private static final int QUIT = 6;
    public static final int RESUME = 3;
    public static final int SEEK_POS = 4;
    private static final int SHOW_DANMAKUS = 8;
    public static final int START = 1;
    public static final int UPDATE = 2;
    private static final int UPDATE_WHEN_PAUSED = 12;
    public IDrawTask drawTask;
    private Callback mCallback;
    private DanmakuContext mContext;
    private long mCordonTime;
    private long mCordonTime2;
    private IDanmakuViewController mDanmakuView;
    private boolean mDanmakusVisible;
    private long mDesireSeekingTime;
    private AbsDisplayer mDisp;
    private LinkedList<Long> mDrawTimes;
    private FrameCallback mFrameCallback;
    private long mFrameUpdateRate;
    private boolean mIdleSleep;
    private boolean mInSeekingAction;
    private boolean mInSyncAction;
    private boolean mInWaitingState;
    private long mLastDeltaTime;
    private boolean mNonBlockModeEnable;
    private BaseDanmakuParser mParser;
    private boolean mReady;
    private long mRemainingTime;
    private final IRenderer.RenderingState mRenderingState;
    private UpdateThread mThread;
    private long mThresholdTime;
    private long mTimeBase;
    private boolean mUpdateInSeparateThread;
    private long pausedPosition;
    private boolean quitFlag;
    private DanmakuTimer timer;

    public interface Callback {
        void danmakuShown(BaseDanmaku baseDanmaku);

        void drawingFinished();

        void prepared();

        void updateTimer(DanmakuTimer danmakuTimer);
    }

    public DrawHandler(Looper looper, IDanmakuViewController iDanmakuViewController, boolean z) {
        super(looper);
        this.pausedPosition = 0L;
        this.quitFlag = true;
        this.timer = new DanmakuTimer();
        this.mDanmakusVisible = true;
        this.mRenderingState = new IRenderer.RenderingState();
        this.mDrawTimes = new LinkedList<>();
        this.mCordonTime = 30L;
        this.mCordonTime2 = 60L;
        this.mFrameUpdateRate = 16L;
        this.mIdleSleep = true ^ DeviceUtils.isProblemBoxDevice();
        bindView(iDanmakuViewController);
        if (z) {
            showDanmakus(null);
        } else {
            hideDanmakus(false);
        }
        this.mDanmakusVisible = z;
    }

    private void bindView(IDanmakuViewController iDanmakuViewController) {
        this.mDanmakuView = iDanmakuViewController;
    }

    public void setIdleSleep(boolean z) {
        this.mIdleSleep = z;
    }

    public void enableNonBlockMode(boolean z) {
        this.mNonBlockModeEnable = z;
    }

    public void setConfig(DanmakuContext danmakuContext) {
        this.mContext = danmakuContext;
    }

    public void setParser(BaseDanmakuParser baseDanmakuParser) {
        this.mParser = baseDanmakuParser;
        DanmakuTimer timer = baseDanmakuParser.getTimer();
        if (timer != null) {
            this.timer = timer;
        }
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void quit() {
        this.quitFlag = true;
        sendEmptyMessage(6);
    }

    public boolean isStop() {
        return this.quitFlag;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x018f  */
    @Override // android.os.Handler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleMessage(android.os.Message r12) {
        /*
            Method dump skipped, instruction units count: 550
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.controller.DrawHandler.handleMessage(android.os.Message):void");
    }

    private synchronized void quitUpdateThread() {
        UpdateThread updateThread = this.mThread;
        this.mThread = null;
        if (updateThread != null) {
            synchronized (this.drawTask) {
                this.drawTask.notifyAll();
            }
            updateThread.quit();
            try {
                updateThread.join(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateInCurrentThread() {
        if (this.quitFlag) {
            return;
        }
        long jSyncTimer = syncTimer(SystemClock.uptimeMillis());
        if (jSyncTimer < 0 && !this.mNonBlockModeEnable) {
            removeMessages(2);
            sendEmptyMessageDelayed(2, 60 - jSyncTimer);
            return;
        }
        long jDrawDanmakus = this.mDanmakuView.drawDanmakus();
        removeMessages(2);
        if (jDrawDanmakus > this.mCordonTime2) {
            this.timer.add(jDrawDanmakus);
            this.mDrawTimes.clear();
        }
        if (!this.mDanmakusVisible) {
            waitRendering(INDEFINITE_TIME);
            return;
        }
        if (this.mRenderingState.nothingRendered && this.mIdleSleep) {
            long j = this.mRenderingState.endTime - this.timer.currMillisecond;
            if (j > 500) {
                waitRendering(j - 10);
                return;
            }
        }
        long j2 = this.mFrameUpdateRate;
        if (jDrawDanmakus < j2) {
            sendEmptyMessageDelayed(2, j2 - jDrawDanmakus);
        } else {
            sendEmptyMessage(2);
        }
    }

    private void updateInNewThread() {
        if (this.mThread != null) {
            return;
        }
        UpdateThread updateThread = new UpdateThread("DFM Update") { // from class: master.flame.danmaku.controller.DrawHandler.2
            @Override // master.flame.danmaku.controller.UpdateThread, java.lang.Thread, java.lang.Runnable
            public void run() {
                long jUptimeMillis = SystemClock.uptimeMillis();
                while (!isQuited() && !DrawHandler.this.quitFlag) {
                    long jUptimeMillis2 = SystemClock.uptimeMillis();
                    if (DrawHandler.this.mFrameUpdateRate - (SystemClock.uptimeMillis() - jUptimeMillis) <= 1 || DrawHandler.this.mNonBlockModeEnable) {
                        long jSyncTimer = DrawHandler.this.syncTimer(jUptimeMillis2);
                        if (jSyncTimer >= 0 || DrawHandler.this.mNonBlockModeEnable) {
                            long jDrawDanmakus = DrawHandler.this.mDanmakuView.drawDanmakus();
                            if (jDrawDanmakus > DrawHandler.this.mCordonTime2) {
                                DrawHandler.this.timer.add(jDrawDanmakus);
                                DrawHandler.this.mDrawTimes.clear();
                            }
                            if (!DrawHandler.this.mDanmakusVisible) {
                                DrawHandler.this.waitRendering(DrawHandler.INDEFINITE_TIME);
                            } else if (DrawHandler.this.mRenderingState.nothingRendered && DrawHandler.this.mIdleSleep) {
                                long j = DrawHandler.this.mRenderingState.endTime - DrawHandler.this.timer.currMillisecond;
                                if (j > 500) {
                                    DrawHandler.this.notifyRendering();
                                    DrawHandler.this.waitRendering(j - 10);
                                }
                            }
                        } else {
                            SystemClock.sleep(60 - jSyncTimer);
                        }
                        jUptimeMillis = jUptimeMillis2;
                    } else {
                        SystemClock.sleep(1L);
                    }
                }
            }
        };
        this.mThread = updateThread;
        updateThread.start();
    }

    private class FrameCallback implements Choreographer.FrameCallback {
        private FrameCallback() {
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            DrawHandler.this.sendEmptyMessage(2);
        }
    }

    private void updateInChoreographer() {
        if (this.quitFlag) {
            return;
        }
        Choreographer.getInstance().postFrameCallback(this.mFrameCallback);
        if (syncTimer(SystemClock.uptimeMillis()) < 0) {
            removeMessages(2);
            return;
        }
        long jDrawDanmakus = this.mDanmakuView.drawDanmakus();
        removeMessages(2);
        if (jDrawDanmakus > this.mCordonTime2) {
            this.timer.add(jDrawDanmakus);
            this.mDrawTimes.clear();
        }
        if (!this.mDanmakusVisible) {
            waitRendering(INDEFINITE_TIME);
            return;
        }
        if (this.mRenderingState.nothingRendered && this.mIdleSleep) {
            long j = this.mRenderingState.endTime - this.timer.currMillisecond;
            if (j > 500) {
                waitRendering(j - 10);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long syncTimer(long j) {
        long jLastInterval = 0;
        if (!this.mInSeekingAction && !this.mInSyncAction) {
            this.mInSyncAction = true;
            long j2 = j - this.mTimeBase;
            if (this.mNonBlockModeEnable) {
                Callback callback = this.mCallback;
                if (callback != null) {
                    callback.updateTimer(this.timer);
                    jLastInterval = this.timer.lastInterval();
                }
            } else if (!this.mDanmakusVisible || this.mRenderingState.nothingRendered || this.mInWaitingState) {
                this.timer.update(j2);
                this.mRemainingTime = 0L;
                Callback callback2 = this.mCallback;
                if (callback2 != null) {
                    callback2.updateTimer(this.timer);
                }
            } else {
                long j3 = j2 - this.timer.currMillisecond;
                long jMax = Math.max(this.mFrameUpdateRate, getAverageRenderingTime());
                if (j3 <= 2000) {
                    long j4 = this.mRenderingState.consumingTime;
                    long j5 = this.mCordonTime;
                    if (j4 <= j5 && jMax <= j5) {
                        long j6 = this.mFrameUpdateRate;
                        long jMin = Math.min(this.mCordonTime, Math.max(j6, jMax + (j3 / j6)));
                        long j7 = this.mLastDeltaTime;
                        long j8 = jMin - j7;
                        if (j8 > 3 && j8 < 8 && j7 >= this.mFrameUpdateRate && j7 <= this.mCordonTime) {
                            jMin = j7;
                        }
                        long j9 = j3 - jMin;
                        this.mLastDeltaTime = jMin;
                        j3 = jMin;
                        jLastInterval = j9;
                    }
                }
                this.mRemainingTime = jLastInterval;
                this.timer.add(j3);
                Callback callback3 = this.mCallback;
                if (callback3 != null) {
                    callback3.updateTimer(this.timer);
                }
                jLastInterval = j3;
            }
            this.mInSyncAction = false;
        }
        return jLastInterval;
    }

    private void syncTimerIfNeeded() {
        if (this.mInWaitingState) {
            syncTimer(SystemClock.uptimeMillis());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRenderingConfigs() {
        long jMax = Math.max(33L, (long) (16 * 2.5f));
        this.mCordonTime = jMax;
        this.mCordonTime2 = (long) (jMax * 2.5f);
        long frameUpdateRate = getConfig().getFrameUpdateRate();
        this.mFrameUpdateRate = frameUpdateRate;
        this.mThresholdTime = frameUpdateRate + 3;
    }

    private void prepare(final Runnable runnable) {
        if (this.drawTask == null) {
            this.drawTask = createDrawTask(this.mDanmakuView.isDanmakuDrawingCacheEnabled(), this.timer, this.mDanmakuView.getContext(), this.mDanmakuView.getViewWidth(), this.mDanmakuView.getViewHeight(), this.mDanmakuView.isHardwareAccelerated(), new IDrawTask.TaskListener() { // from class: master.flame.danmaku.controller.DrawHandler.3
                @Override // master.flame.danmaku.controller.IDrawTask.TaskListener
                public void ready() {
                    DrawHandler.this.initRenderingConfigs();
                    runnable.run();
                }

                @Override // master.flame.danmaku.controller.IDrawTask.TaskListener
                public void onDanmakuAdd(BaseDanmaku baseDanmaku) {
                    if (baseDanmaku.isTimeOut()) {
                        return;
                    }
                    long actualTime = baseDanmaku.getActualTime() - DrawHandler.this.getCurrentTime();
                    if (actualTime < DrawHandler.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION && (DrawHandler.this.mInWaitingState || DrawHandler.this.mRenderingState.nothingRendered)) {
                        DrawHandler.this.notifyRendering();
                    } else {
                        if (actualTime <= 0 || actualTime > DrawHandler.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) {
                            return;
                        }
                        DrawHandler.this.sendEmptyMessageDelayed(11, actualTime);
                    }
                }

                @Override // master.flame.danmaku.controller.IDrawTask.TaskListener
                public void onDanmakuShown(BaseDanmaku baseDanmaku) {
                    if (DrawHandler.this.mCallback != null) {
                        DrawHandler.this.mCallback.danmakuShown(baseDanmaku);
                    }
                }

                @Override // master.flame.danmaku.controller.IDrawTask.TaskListener
                public void onDanmakusDrawingFinished() {
                    if (DrawHandler.this.mCallback != null) {
                        DrawHandler.this.mCallback.drawingFinished();
                    }
                }

                @Override // master.flame.danmaku.controller.IDrawTask.TaskListener
                public void onDanmakuConfigChanged() {
                    DrawHandler.this.redrawIfNeeded();
                }
            });
        } else {
            runnable.run();
        }
    }

    public boolean isPrepared() {
        return this.mReady;
    }

    private IDrawTask createDrawTask(boolean z, DanmakuTimer danmakuTimer, Context context, int i, int i2, boolean z2, IDrawTask.TaskListener taskListener) {
        AbsDisplayer displayer = this.mContext.getDisplayer();
        this.mDisp = displayer;
        displayer.setSize(i, i2);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.mDisp.setDensities(displayMetrics.density, displayMetrics.densityDpi, displayMetrics.scaledDensity);
        this.mDisp.resetSlopPixel(this.mContext.scaleTextSize);
        this.mDisp.setHardwareAccelerated(z2);
        IDrawTask cacheManagingDrawTask = z ? new CacheManagingDrawTask(danmakuTimer, this.mContext, taskListener) : new DrawTask(danmakuTimer, this.mContext, taskListener);
        cacheManagingDrawTask.setParser(this.mParser);
        cacheManagingDrawTask.prepare();
        obtainMessage(10, false).sendToTarget();
        return cacheManagingDrawTask;
    }

    public void seekTo(Long l) {
        this.mInSeekingAction = true;
        this.mDesireSeekingTime = l.longValue();
        removeMessages(2);
        removeMessages(3);
        removeMessages(4);
        obtainMessage(4, l).sendToTarget();
    }

    public void addDanmaku(BaseDanmaku baseDanmaku) {
        if (this.drawTask != null) {
            baseDanmaku.flags = this.mContext.mGlobalFlagValues;
            baseDanmaku.setTimer(this.timer);
            this.drawTask.addDanmaku(baseDanmaku);
            obtainMessage(11).sendToTarget();
        }
    }

    public void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z) {
        IDrawTask iDrawTask = this.drawTask;
        if (iDrawTask != null && baseDanmaku != null) {
            iDrawTask.invalidateDanmaku(baseDanmaku, z);
        }
        redrawIfNeeded();
    }

    public void resume() {
        removeMessages(7);
        sendEmptyMessage(3);
    }

    public void prepare() {
        this.mReady = false;
        if (Build.VERSION.SDK_INT < 16 && this.mContext.updateMethod == 0) {
            this.mContext.updateMethod = (byte) 2;
        }
        if (this.mContext.updateMethod == 0) {
            this.mFrameCallback = new FrameCallback();
        }
        this.mUpdateInSeparateThread = this.mContext.updateMethod == 1;
        sendEmptyMessage(5);
    }

    public void pause() {
        removeMessages(3);
        syncTimerIfNeeded();
        sendEmptyMessage(7);
    }

    public void showDanmakus(Long l) {
        if (this.mDanmakusVisible) {
            return;
        }
        this.mDanmakusVisible = true;
        removeMessages(8);
        removeMessages(9);
        obtainMessage(8, l).sendToTarget();
    }

    public long hideDanmakus(boolean z) {
        if (!this.mDanmakusVisible) {
            return this.timer.currMillisecond;
        }
        this.mDanmakusVisible = false;
        removeMessages(8);
        removeMessages(9);
        obtainMessage(9, Boolean.valueOf(z)).sendToTarget();
        return this.timer.currMillisecond;
    }

    public void forceRender() {
        removeMessages(14);
        obtainMessage(14).sendToTarget();
    }

    public boolean getVisibility() {
        return this.mDanmakusVisible;
    }

    public IRenderer.RenderingState draw(Canvas canvas) {
        AbsDanmakuSync absDanmakuSync;
        boolean zIsSyncPlayingState;
        if (this.drawTask == null) {
            return this.mRenderingState;
        }
        if (!this.mInWaitingState && (absDanmakuSync = this.mContext.danmakuSync) != null && ((zIsSyncPlayingState = absDanmakuSync.isSyncPlayingState()) || !this.quitFlag)) {
            int syncState = absDanmakuSync.getSyncState();
            if (syncState == 2) {
                long j = this.timer.currMillisecond;
                long uptimeMillis = absDanmakuSync.getUptimeMillis();
                long j2 = uptimeMillis - j;
                if (Math.abs(j2) > absDanmakuSync.getThresholdTimeMills()) {
                    if (zIsSyncPlayingState && this.quitFlag) {
                        resume();
                    }
                    this.drawTask.requestSync(j, uptimeMillis, j2);
                    this.timer.update(uptimeMillis);
                    this.mTimeBase -= j2;
                    this.mRemainingTime = 0L;
                }
            } else if (syncState == 1 && zIsSyncPlayingState && !this.quitFlag) {
                pause();
            }
        }
        this.mDisp.setExtraData(canvas);
        this.mRenderingState.set(this.drawTask.draw(this.mDisp));
        recordRenderingTime();
        return this.mRenderingState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void redrawIfNeeded() {
        if (this.quitFlag && this.mDanmakusVisible) {
            removeMessages(12);
            sendEmptyMessageDelayed(12, 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRendering() {
        if (this.mInWaitingState) {
            IDrawTask iDrawTask = this.drawTask;
            if (iDrawTask != null) {
                iDrawTask.requestClear();
            }
            if (this.mUpdateInSeparateThread) {
                synchronized (this) {
                    this.mDrawTimes.clear();
                }
                synchronized (this.drawTask) {
                    this.drawTask.notifyAll();
                }
            } else {
                this.mDrawTimes.clear();
                removeMessages(2);
                sendEmptyMessage(2);
            }
            this.mInWaitingState = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitRendering(long j) {
        if (isStop() || !isPrepared() || this.mInSeekingAction) {
            return;
        }
        this.mRenderingState.sysTime = SystemClock.uptimeMillis();
        this.mInWaitingState = true;
        if (!this.mUpdateInSeparateThread) {
            if (j == INDEFINITE_TIME) {
                removeMessages(11);
                removeMessages(2);
                return;
            } else {
                removeMessages(11);
                removeMessages(2);
                sendEmptyMessageDelayed(11, j);
                return;
            }
        }
        if (this.mThread == null) {
            return;
        }
        try {
            synchronized (this.drawTask) {
                if (j == INDEFINITE_TIME) {
                    this.drawTask.wait();
                } else {
                    this.drawTask.wait(j);
                }
                sendEmptyMessage(11);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized long getAverageRenderingTime() {
        int size = this.mDrawTimes.size();
        if (size <= 0) {
            return 0L;
        }
        Long lPeekFirst = this.mDrawTimes.peekFirst();
        Long lPeekLast = this.mDrawTimes.peekLast();
        if (lPeekFirst != null && lPeekLast != null) {
            return (lPeekLast.longValue() - lPeekFirst.longValue()) / ((long) size);
        }
        return 0L;
    }

    private synchronized void recordRenderingTime() {
        this.mDrawTimes.addLast(Long.valueOf(SystemClock.uptimeMillis()));
        if (this.mDrawTimes.size() > MAX_RECORD_SIZE) {
            this.mDrawTimes.removeFirst();
        }
    }

    public IDisplayer getDisplayer() {
        return this.mDisp;
    }

    public void notifyDispSizeChanged(int i, int i2) {
        AbsDisplayer absDisplayer = this.mDisp;
        if (absDisplayer == null) {
            return;
        }
        if (absDisplayer.getWidth() == i && this.mDisp.getHeight() == i2) {
            return;
        }
        this.mDisp.setSize(i, i2);
        obtainMessage(10, true).sendToTarget();
    }

    public void removeAllDanmakus(boolean z) {
        IDrawTask iDrawTask = this.drawTask;
        if (iDrawTask != null) {
            iDrawTask.removeAllDanmakus(z);
        }
    }

    public void removeAllLiveDanmakus() {
        IDrawTask iDrawTask = this.drawTask;
        if (iDrawTask != null) {
            iDrawTask.removeAllLiveDanmakus();
        }
    }

    public IDanmakus getCurrentVisibleDanmakus() {
        IDrawTask iDrawTask = this.drawTask;
        if (iDrawTask != null) {
            return iDrawTask.getVisibleDanmakusOnTime(getCurrentTime());
        }
        return null;
    }

    public long getCurrentTime() {
        long jUptimeMillis;
        long j;
        if (!this.mReady) {
            return 0L;
        }
        if (this.mInSeekingAction) {
            return this.mDesireSeekingTime;
        }
        if (this.quitFlag || !this.mInWaitingState) {
            jUptimeMillis = this.timer.currMillisecond;
            j = this.mRemainingTime;
        } else {
            jUptimeMillis = SystemClock.uptimeMillis();
            j = this.mTimeBase;
        }
        return jUptimeMillis - j;
    }

    public void clearDanmakusOnScreen() {
        obtainMessage(13).sendToTarget();
    }

    public DanmakuContext getConfig() {
        return this.mContext;
    }
}
