package master.flame.danmaku.controller;

import master.flame.danmaku.controller.IDrawTask;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.renderer.IRenderer;
import master.flame.danmaku.danmaku.renderer.android.DanmakuRenderer;
import master.flame.danmaku.danmaku.util.SystemClock;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class DrawTask implements IDrawTask {
    protected boolean clearRetainerFlag;
    protected IDanmakus danmakuList;
    protected final DanmakuContext mContext;
    protected final AbsDisplayer mDisp;
    private boolean mIsHidden;
    private long mLastBeginMills;
    private BaseDanmaku mLastDanmaku;
    private long mLastEndMills;
    protected BaseDanmakuParser mParser;
    protected int mPlayState;
    protected boolean mReadyState;
    final IRenderer mRenderer;
    private boolean mRequestRender;
    private IDanmakus mRunningDanmakus;
    IDrawTask.TaskListener mTaskListener;
    DanmakuTimer mTimer;
    private IDanmakus danmakus = new Danmakus(4);
    private long mStartRenderTime = 0;
    private final IRenderer.RenderingState mRenderingState = new IRenderer.RenderingState();
    private Danmakus mLiveDanmakus = new Danmakus(4);
    private DanmakuContext.ConfigChangedCallback mConfigChangedCallback = new DanmakuContext.ConfigChangedCallback() { // from class: master.flame.danmaku.controller.DrawTask.1
        @Override // master.flame.danmaku.danmaku.model.android.DanmakuContext.ConfigChangedCallback
        public boolean onDanmakuConfigChanged(DanmakuContext danmakuContext, DanmakuContext.DanmakuConfigTag danmakuConfigTag, Object... objArr) {
            return DrawTask.this.onDanmakuConfigChanged(danmakuContext, danmakuConfigTag, objArr);
        }
    };

    protected void onDanmakuRemoved(BaseDanmaku baseDanmaku) {
    }

    public DrawTask(DanmakuTimer danmakuTimer, DanmakuContext danmakuContext, IDrawTask.TaskListener taskListener) {
        if (danmakuContext == null) {
            throw new IllegalArgumentException("context is null");
        }
        this.mContext = danmakuContext;
        this.mDisp = danmakuContext.getDisplayer();
        this.mTaskListener = taskListener;
        DanmakuRenderer danmakuRenderer = new DanmakuRenderer(danmakuContext);
        this.mRenderer = danmakuRenderer;
        danmakuRenderer.setOnDanmakuShownListener(new IRenderer.OnDanmakuShownListener() { // from class: master.flame.danmaku.controller.DrawTask.2
            @Override // master.flame.danmaku.danmaku.renderer.IRenderer.OnDanmakuShownListener
            public void onDanmakuShown(BaseDanmaku baseDanmaku) {
                if (DrawTask.this.mTaskListener != null) {
                    DrawTask.this.mTaskListener.onDanmakuShown(baseDanmaku);
                }
            }
        });
        this.mRenderer.setVerifierEnabled(this.mContext.isPreventOverlappingEnabled() || this.mContext.isMaxLinesLimited());
        initTimer(danmakuTimer);
        Boolean boolValueOf = Boolean.valueOf(this.mContext.isDuplicateMergingEnabled());
        if (boolValueOf != null) {
            if (boolValueOf.booleanValue()) {
                this.mContext.mDanmakuFilters.registerFilter(DanmakuFilters.TAG_DUPLICATE_FILTER);
            } else {
                this.mContext.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_DUPLICATE_FILTER);
            }
        }
    }

    protected void initTimer(DanmakuTimer danmakuTimer) {
        this.mTimer = danmakuTimer;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public synchronized void addDanmaku(BaseDanmaku baseDanmaku) {
        boolean zAddItem;
        boolean zAddItem2;
        if (this.danmakuList == null) {
            return;
        }
        if (baseDanmaku.isLive) {
            this.mLiveDanmakus.addItem(baseDanmaku);
            removeUnusedLiveDanmakusIn(10);
        }
        baseDanmaku.index = this.danmakuList.size();
        boolean z = true;
        if (this.mLastBeginMills <= baseDanmaku.getActualTime() && baseDanmaku.getActualTime() <= this.mLastEndMills) {
            synchronized (this.danmakus) {
                zAddItem2 = this.danmakus.addItem(baseDanmaku);
            }
            z = zAddItem2;
        } else if (baseDanmaku.isLive) {
            z = false;
        }
        synchronized (this.danmakuList) {
            zAddItem = this.danmakuList.addItem(baseDanmaku);
        }
        if (!z || !zAddItem) {
            this.mLastEndMills = 0L;
            this.mLastBeginMills = 0L;
        }
        if (zAddItem && this.mTaskListener != null) {
            this.mTaskListener.onDanmakuAdd(baseDanmaku);
        }
        if (this.mLastDanmaku == null || (baseDanmaku != null && this.mLastDanmaku != null && baseDanmaku.getActualTime() > this.mLastDanmaku.getActualTime())) {
            this.mLastDanmaku = baseDanmaku;
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z) {
        this.mContext.getDisplayer().getCacheStuffer().clearCache(baseDanmaku);
        baseDanmaku.requestFlags |= 2;
        if (z) {
            baseDanmaku.paintWidth = -1.0f;
            baseDanmaku.paintHeight = -1.0f;
            baseDanmaku.requestFlags |= 1;
            baseDanmaku.measureResetFlag++;
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public synchronized void removeAllDanmakus(boolean z) {
        if (this.danmakuList != null && !this.danmakuList.isEmpty()) {
            synchronized (this.danmakuList) {
                if (!z) {
                    IDanmakus iDanmakusSubnew = this.danmakuList.subnew((this.mTimer.currMillisecond - this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) - 100, this.mTimer.currMillisecond + this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
                    if (iDanmakusSubnew != null) {
                        this.danmakus = iDanmakusSubnew;
                    }
                }
                this.danmakuList.clear();
            }
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public synchronized void removeAllLiveDanmakus() {
        if (this.danmakus != null && !this.danmakus.isEmpty()) {
            synchronized (this.danmakus) {
                this.danmakus.forEachSync(new IDanmakus.DefaultConsumer<BaseDanmaku>() { // from class: master.flame.danmaku.controller.DrawTask.3
                    @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
                    public int accept(BaseDanmaku baseDanmaku) {
                        if (!baseDanmaku.isLive) {
                            return 0;
                        }
                        DrawTask.this.onDanmakuRemoved(baseDanmaku);
                        return 2;
                    }
                });
            }
        }
    }

    protected synchronized void removeUnusedLiveDanmakusIn(final int i) {
        if (this.danmakuList != null && !this.danmakuList.isEmpty() && !this.mLiveDanmakus.isEmpty()) {
            this.mLiveDanmakus.forEachSync(new IDanmakus.DefaultConsumer<BaseDanmaku>() { // from class: master.flame.danmaku.controller.DrawTask.4
                long startTime = SystemClock.uptimeMillis();

                @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
                public int accept(BaseDanmaku baseDanmaku) {
                    boolean zIsTimeOut = baseDanmaku.isTimeOut();
                    if (SystemClock.uptimeMillis() - this.startTime > i || !zIsTimeOut) {
                        return 1;
                    }
                    DrawTask.this.danmakuList.removeItem(baseDanmaku);
                    DrawTask.this.onDanmakuRemoved(baseDanmaku);
                    return 2;
                }
            });
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public IDanmakus getVisibleDanmakusOnTime(long j) {
        IDanmakus iDanmakusSubnew;
        long j2 = (j - this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) - 100;
        long j3 = j + this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION;
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i >= 3) {
                iDanmakusSubnew = null;
                break;
            }
            try {
                iDanmakusSubnew = this.danmakuList.subnew(j2, j3);
                break;
            } catch (Exception unused) {
                i = i2;
            }
        }
        final Danmakus danmakus = new Danmakus();
        if (iDanmakusSubnew != null && !iDanmakusSubnew.isEmpty()) {
            iDanmakusSubnew.forEachSync(new IDanmakus.DefaultConsumer<BaseDanmaku>() { // from class: master.flame.danmaku.controller.DrawTask.5
                @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
                public int accept(BaseDanmaku baseDanmaku) {
                    if (!baseDanmaku.isShown() || baseDanmaku.isOutside()) {
                        return 0;
                    }
                    danmakus.addItem(baseDanmaku);
                    return 0;
                }
            });
        }
        return danmakus;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public synchronized IRenderer.RenderingState draw(AbsDisplayer absDisplayer) {
        return drawDanmakus(absDisplayer, this.mTimer);
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void reset() {
        if (this.danmakus != null) {
            this.danmakus = new Danmakus();
        }
        IRenderer iRenderer = this.mRenderer;
        if (iRenderer != null) {
            iRenderer.clear();
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void seek(long j) {
        BaseDanmaku baseDanmakuLast;
        reset();
        this.mContext.mGlobalFlagValues.updateVisibleFlag();
        this.mContext.mGlobalFlagValues.updateFirstShownFlag();
        this.mContext.mGlobalFlagValues.updateSyncOffsetTimeFlag();
        this.mContext.mGlobalFlagValues.updatePrepareFlag();
        this.mRunningDanmakus = new Danmakus(4);
        if (j < 1000) {
            j = 0;
        }
        this.mStartRenderTime = j;
        this.mRenderingState.reset();
        this.mRenderingState.endTime = this.mStartRenderTime;
        this.mLastEndMills = 0L;
        this.mLastBeginMills = 0L;
        IDanmakus iDanmakus = this.danmakuList;
        if (iDanmakus == null || (baseDanmakuLast = iDanmakus.last()) == null || baseDanmakuLast.isTimeOut()) {
            return;
        }
        this.mLastDanmaku = baseDanmakuLast;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void clearDanmakusOnScreen(long j) {
        reset();
        this.mContext.mGlobalFlagValues.updateVisibleFlag();
        this.mContext.mGlobalFlagValues.updateFirstShownFlag();
        this.mStartRenderTime = j;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void start() {
        this.mContext.registerConfigChangedCallback(this.mConfigChangedCallback);
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void quit() {
        this.mContext.unregisterAllConfigChangedCallbacks();
        IRenderer iRenderer = this.mRenderer;
        if (iRenderer != null) {
            iRenderer.release();
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void prepare() {
        BaseDanmakuParser baseDanmakuParser = this.mParser;
        if (baseDanmakuParser == null) {
            return;
        }
        loadDanmakus(baseDanmakuParser);
        this.mLastEndMills = 0L;
        this.mLastBeginMills = 0L;
        IDrawTask.TaskListener taskListener = this.mTaskListener;
        if (taskListener != null) {
            taskListener.ready();
            this.mReadyState = true;
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void onPlayStateChanged(int i) {
        this.mPlayState = i;
    }

    protected void loadDanmakus(BaseDanmakuParser baseDanmakuParser) {
        this.danmakuList = baseDanmakuParser.setConfig(this.mContext).setDisplayer(this.mDisp).setTimer(this.mTimer).setListener(new BaseDanmakuParser.Listener() { // from class: master.flame.danmaku.controller.DrawTask.6
            @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser.Listener
            public void onDanmakuAdd(BaseDanmaku baseDanmaku) {
                if (DrawTask.this.mTaskListener != null) {
                    DrawTask.this.mTaskListener.onDanmakuAdd(baseDanmaku);
                }
            }
        }).getDanmakus();
        this.mContext.mGlobalFlagValues.resetAll();
        IDanmakus iDanmakus = this.danmakuList;
        if (iDanmakus != null) {
            this.mLastDanmaku = iDanmakus.last();
        }
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void setParser(BaseDanmakuParser baseDanmakuParser) {
        this.mParser = baseDanmakuParser;
        this.mReadyState = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected master.flame.danmaku.danmaku.renderer.IRenderer.RenderingState drawDanmakus(master.flame.danmaku.danmaku.model.AbsDisplayer r22, master.flame.danmaku.danmaku.model.DanmakuTimer r23) {
        /*
            Method dump skipped, instruction units count: 214
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.controller.DrawTask.drawDanmakus(master.flame.danmaku.danmaku.model.AbsDisplayer, master.flame.danmaku.danmaku.model.DanmakuTimer):master.flame.danmaku.danmaku.renderer.IRenderer$RenderingState");
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void requestClear() {
        this.mLastEndMills = 0L;
        this.mLastBeginMills = 0L;
        this.mIsHidden = false;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void requestClearRetainer() {
        this.clearRetainerFlag = true;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void requestSync(long j, long j2, final long j3) {
        IDanmakus iDanmakusObtainRunningDanmakus = this.mRenderingState.obtainRunningDanmakus();
        this.mRunningDanmakus = iDanmakusObtainRunningDanmakus;
        iDanmakusObtainRunningDanmakus.forEachSync(new IDanmakus.DefaultConsumer<BaseDanmaku>() { // from class: master.flame.danmaku.controller.DrawTask.7
            @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
            public int accept(BaseDanmaku baseDanmaku) {
                if (baseDanmaku.isOutside()) {
                    return 2;
                }
                baseDanmaku.setTimeOffset(j3 + baseDanmaku.timeOffset);
                return baseDanmaku.timeOffset == 0 ? 2 : 0;
            }
        });
        this.mStartRenderTime = j2;
    }

    public boolean onDanmakuConfigChanged(DanmakuContext danmakuContext, DanmakuContext.DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        boolean zHandleOnDanmakuConfigChanged = handleOnDanmakuConfigChanged(danmakuContext, danmakuConfigTag, objArr);
        IDrawTask.TaskListener taskListener = this.mTaskListener;
        if (taskListener != null) {
            taskListener.onDanmakuConfigChanged();
        }
        return zHandleOnDanmakuConfigChanged;
    }

    protected boolean handleOnDanmakuConfigChanged(DanmakuContext danmakuContext, DanmakuContext.DanmakuConfigTag danmakuConfigTag, Object[] objArr) {
        Boolean bool;
        if (danmakuConfigTag == null || DanmakuContext.DanmakuConfigTag.MAXIMUM_NUMS_IN_SCREEN.equals(danmakuConfigTag)) {
            return true;
        }
        if (DanmakuContext.DanmakuConfigTag.DUPLICATE_MERGING_ENABLED.equals(danmakuConfigTag)) {
            Boolean bool2 = (Boolean) objArr[0];
            if (bool2 != null) {
                if (bool2.booleanValue()) {
                    this.mContext.mDanmakuFilters.registerFilter(DanmakuFilters.TAG_DUPLICATE_FILTER);
                    return true;
                }
                this.mContext.mDanmakuFilters.unregisterFilter(DanmakuFilters.TAG_DUPLICATE_FILTER);
                return true;
            }
        } else if (DanmakuContext.DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag) || DanmakuContext.DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(danmakuConfigTag) || DanmakuContext.DanmakuConfigTag.DANMAKU_MARGIN.equals(danmakuConfigTag)) {
            requestClearRetainer();
        } else {
            if (DanmakuContext.DanmakuConfigTag.MAXIMUN_LINES.equals(danmakuConfigTag) || DanmakuContext.DanmakuConfigTag.OVERLAPPING_ENABLE.equals(danmakuConfigTag)) {
                IRenderer iRenderer = this.mRenderer;
                if (iRenderer == null) {
                    return true;
                }
                iRenderer.setVerifierEnabled(this.mContext.isPreventOverlappingEnabled() || this.mContext.isMaxLinesLimited());
                return true;
            }
            if (DanmakuContext.DanmakuConfigTag.ALIGN_BOTTOM.equals(danmakuConfigTag) && (bool = (Boolean) objArr[0]) != null) {
                IRenderer iRenderer2 = this.mRenderer;
                if (iRenderer2 == null) {
                    return true;
                }
                iRenderer2.alignBottom(bool.booleanValue());
                return true;
            }
        }
        return false;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void requestHide() {
        this.mIsHidden = true;
    }

    @Override // master.flame.danmaku.controller.IDrawTask
    public void requestRender() {
        this.mRequestRender = true;
    }

    private void beginTracing(IRenderer.RenderingState renderingState, IDanmakus iDanmakus, IDanmakus iDanmakus2) {
        renderingState.reset();
        renderingState.timer.update(SystemClock.uptimeMillis());
        renderingState.indexInScreen = 0;
        renderingState.totalSizeInScreen = (iDanmakus != null ? iDanmakus.size() : 0) + (iDanmakus2 != null ? iDanmakus2.size() : 0);
    }

    private void endTracing(IRenderer.RenderingState renderingState) {
        renderingState.nothingRendered = renderingState.totalDanmakuCount == 0;
        if (renderingState.nothingRendered) {
            renderingState.beginTime = -1L;
        }
        BaseDanmaku baseDanmaku = renderingState.lastDanmaku;
        renderingState.lastDanmaku = null;
        renderingState.endTime = baseDanmaku != null ? baseDanmaku.getActualTime() : -1L;
        renderingState.consumingTime = renderingState.timer.update(SystemClock.uptimeMillis());
    }
}
