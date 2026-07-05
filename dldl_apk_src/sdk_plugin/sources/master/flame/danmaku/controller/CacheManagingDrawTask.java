package master.flame.danmaku.controller;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import master.flame.danmaku.controller.IDrawTask;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.ICacheManager;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.IDrawingCache;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.DanmakuFactory;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.DrawingCache;
import master.flame.danmaku.danmaku.model.android.DrawingCachePoolManager;
import master.flame.danmaku.danmaku.model.objectpool.Pool;
import master.flame.danmaku.danmaku.model.objectpool.Pools;
import master.flame.danmaku.danmaku.renderer.IRenderer;
import master.flame.danmaku.danmaku.util.DanmakuUtils;
import master.flame.danmaku.danmaku.util.SystemClock;
import tv.cjump.jni.NativeBitmapFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class CacheManagingDrawTask extends DrawTask {
    private static final int MAX_CACHE_SCREEN_SIZE = 3;
    private CacheManager mCacheManager;
    private DanmakuTimer mCacheTimer;
    private final Object mDrawingNotify;
    private int mMaxCacheSize;
    private int mRemaininCacheCount;

    public CacheManagingDrawTask(DanmakuTimer danmakuTimer, DanmakuContext danmakuContext, IDrawTask.TaskListener taskListener) {
        super(danmakuTimer, danmakuContext, taskListener);
        this.mMaxCacheSize = 2;
        this.mDrawingNotify = new Object();
        NativeBitmapFactory.loadLibs();
        int iMax = (int) Math.max(4194304.0f, Runtime.getRuntime().maxMemory() * danmakuContext.cachingPolicy.maxCachePoolSizeFactorPercentage);
        this.mMaxCacheSize = iMax;
        this.mCacheManager = new CacheManager(iMax, 3);
        this.mRenderer.setCacheManager(this.mCacheManager);
    }

    @Override // master.flame.danmaku.controller.DrawTask
    protected void initTimer(DanmakuTimer danmakuTimer) {
        this.mTimer = danmakuTimer;
        DanmakuTimer danmakuTimer2 = new DanmakuTimer();
        this.mCacheTimer = danmakuTimer2;
        danmakuTimer2.update(danmakuTimer.currMillisecond);
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void addDanmaku(BaseDanmaku baseDanmaku) {
        super.addDanmaku(baseDanmaku);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager == null) {
            return;
        }
        cacheManager.addDanmaku(baseDanmaku);
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z) {
        super.invalidateDanmaku(baseDanmaku, z);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager == null) {
            return;
        }
        cacheManager.invalidateDanmaku(baseDanmaku, z);
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void removeAllDanmakus(boolean z) {
        super.removeAllDanmakus(z);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            cacheManager.requestClearAll();
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask
    protected void onDanmakuRemoved(BaseDanmaku baseDanmaku) {
        super.onDanmakuRemoved(baseDanmaku);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            int i = this.mRemaininCacheCount + 1;
            this.mRemaininCacheCount = i;
            if (i > 5) {
                cacheManager.requestClearTimeout();
                this.mRemaininCacheCount = 0;
                return;
            }
            return;
        }
        IDrawingCache<?> drawingCache = baseDanmaku.getDrawingCache();
        if (drawingCache != null) {
            if (drawingCache.hasReferences()) {
                drawingCache.decreaseReference();
            } else {
                drawingCache.destroy();
            }
            baseDanmaku.cache = null;
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public IRenderer.RenderingState draw(AbsDisplayer absDisplayer) {
        IRenderer.RenderingState renderingStateDraw = super.draw(absDisplayer);
        synchronized (this.mDrawingNotify) {
            this.mDrawingNotify.notify();
        }
        if (renderingStateDraw != null && this.mCacheManager != null && renderingStateDraw.totalDanmakuCount - renderingStateDraw.lastTotalDanmakuCount < -20) {
            this.mCacheManager.requestClearTimeout();
            this.mCacheManager.requestBuild(-this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
        }
        return renderingStateDraw;
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void seek(long j) {
        super.seek(j);
        if (this.mCacheManager == null) {
            start();
        }
        this.mCacheManager.seek(j);
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void start() {
        super.start();
        NativeBitmapFactory.loadLibs();
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager == null) {
            CacheManager cacheManager2 = new CacheManager(this.mMaxCacheSize, 3);
            this.mCacheManager = cacheManager2;
            cacheManager2.begin();
            this.mRenderer.setCacheManager(this.mCacheManager);
            return;
        }
        cacheManager.resume();
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void quit() {
        super.quit();
        reset();
        this.mRenderer.setCacheManager(null);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            cacheManager.end();
            this.mCacheManager = null;
        }
        NativeBitmapFactory.releaseLibs();
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void prepare() {
        if (this.mParser == null) {
            return;
        }
        loadDanmakus(this.mParser);
        this.mCacheManager.begin();
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void onPlayStateChanged(int i) {
        super.onPlayStateChanged(i);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            cacheManager.onPlayStateChanged(i);
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask, master.flame.danmaku.controller.IDrawTask
    public void requestSync(long j, long j2, long j3) {
        super.requestSync(j, j2, j3);
        CacheManager cacheManager = this.mCacheManager;
        if (cacheManager != null) {
            cacheManager.seek(j2);
        }
    }

    public class CacheManager implements ICacheManager {
        public static final byte RESULT_FAILED = 1;
        public static final byte RESULT_FAILED_OVERSIZE = 2;
        public static final byte RESULT_SUCCESS = 0;
        private static final String TAG = "CacheManager";
        Pool<DrawingCache> mCachePool;
        DrawingCachePoolManager mCachePoolManager;
        Danmakus mCaches = new Danmakus();
        private boolean mEndFlag;
        private CacheHandler mHandler;
        private int mMaxSize;
        private int mRealSize;
        private int mScreenSize;
        public HandlerThread mThread;

        public CacheManager(int i, int i2) {
            DrawingCachePoolManager drawingCachePoolManager = new DrawingCachePoolManager();
            this.mCachePoolManager = drawingCachePoolManager;
            this.mCachePool = Pools.finitePool(drawingCachePoolManager, 800);
            this.mScreenSize = 3;
            this.mEndFlag = false;
            this.mRealSize = 0;
            this.mMaxSize = i;
            this.mScreenSize = i2;
        }

        public void seek(long j) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.requestCancelCaching();
            this.mHandler.removeMessages(3);
            this.mHandler.obtainMessage(5, Long.valueOf(j)).sendToTarget();
        }

        @Override // master.flame.danmaku.danmaku.model.ICacheManager
        public void addDanmaku(BaseDanmaku baseDanmaku) {
            if (this.mHandler != null) {
                if (baseDanmaku.isLive && baseDanmaku.forceBuildCacheInSameThread) {
                    if (baseDanmaku.isTimeOut()) {
                        return;
                    }
                    this.mHandler.createCache(baseDanmaku);
                    return;
                }
                this.mHandler.obtainMessage(2, baseDanmaku).sendToTarget();
            }
        }

        public void invalidateDanmaku(BaseDanmaku baseDanmaku, boolean z) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.requestCancelCaching();
                this.mHandler.obtainMessage(17, baseDanmaku).sendToTarget();
                this.mHandler.sendEmptyMessage(18);
                requestBuild(0L);
            }
        }

        public void begin() {
            this.mEndFlag = false;
            if (this.mThread == null) {
                HandlerThread handlerThread = new HandlerThread("DFM Cache-Building Thread");
                this.mThread = handlerThread;
                handlerThread.start();
            }
            if (this.mHandler == null) {
                this.mHandler = new CacheHandler(this.mThread.getLooper());
            }
            this.mHandler.begin();
        }

        public void end() {
            this.mEndFlag = true;
            synchronized (CacheManagingDrawTask.this.mDrawingNotify) {
                CacheManagingDrawTask.this.mDrawingNotify.notifyAll();
            }
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.removeCallbacksAndMessages(null);
                this.mHandler.pause();
                this.mHandler = null;
            }
            HandlerThread handlerThread = this.mThread;
            if (handlerThread != null) {
                try {
                    handlerThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.mThread.quit();
                this.mThread = null;
            }
        }

        public void resume() {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.resume();
            } else {
                begin();
            }
        }

        public void onPlayStateChanged(int i) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.onPlayStateChanged(i == 1);
            }
        }

        public float getPoolPercent() {
            int i = this.mMaxSize;
            if (i == 0) {
                return 0.0f;
            }
            return this.mRealSize / i;
        }

        public boolean isPoolFull() {
            return this.mRealSize + 5120 >= this.mMaxSize;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void evictAll() {
            Danmakus danmakus = this.mCaches;
            if (danmakus != null) {
                danmakus.forEach(new IDanmakus.DefaultConsumer<BaseDanmaku>() { // from class: master.flame.danmaku.controller.CacheManagingDrawTask.CacheManager.1
                    @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
                    public int accept(BaseDanmaku baseDanmaku) {
                        CacheManager.this.entryRemoved(true, baseDanmaku, null);
                        return 0;
                    }
                });
                this.mCaches.clear();
            }
            this.mRealSize = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void evictAllNotInScreen() {
            Danmakus danmakus = this.mCaches;
            if (danmakus != null) {
                danmakus.forEach(new IDanmakus.DefaultConsumer<BaseDanmaku>() { // from class: master.flame.danmaku.controller.CacheManagingDrawTask.CacheManager.2
                    @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
                    public int accept(BaseDanmaku baseDanmaku) {
                        if (!baseDanmaku.isOutside()) {
                            return 0;
                        }
                        CacheManager.this.entryRemoved(true, baseDanmaku, null);
                        return 2;
                    }
                });
            }
        }

        protected void entryRemoved(boolean z, BaseDanmaku baseDanmaku, BaseDanmaku baseDanmaku2) {
            IDrawingCache<?> drawingCache = baseDanmaku.getDrawingCache();
            if (drawingCache != null) {
                long jClearCache = clearCache(baseDanmaku);
                if (baseDanmaku.isTimeOut()) {
                    CacheManagingDrawTask.this.mContext.getDisplayer().getCacheStuffer().releaseResource(baseDanmaku);
                }
                if (jClearCache <= 0) {
                    return;
                }
                this.mRealSize = (int) (((long) this.mRealSize) - jClearCache);
                this.mCachePool.release((DrawingCache) drawingCache);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long clearCache(BaseDanmaku baseDanmaku) {
            IDrawingCache<?> iDrawingCache = baseDanmaku.cache;
            if (iDrawingCache == null) {
                return 0L;
            }
            if (iDrawingCache.hasReferences()) {
                iDrawingCache.decreaseReference();
                baseDanmaku.cache = null;
                return 0L;
            }
            long jSizeOf = sizeOf(baseDanmaku);
            iDrawingCache.destroy();
            baseDanmaku.cache = null;
            return jSizeOf;
        }

        protected int sizeOf(BaseDanmaku baseDanmaku) {
            if (baseDanmaku.cache == null || baseDanmaku.cache.hasReferences()) {
                return 0;
            }
            return baseDanmaku.cache.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCachePool() {
            while (true) {
                DrawingCache drawingCache = (DrawingCache) this.mCachePool.acquire();
                if (drawingCache == null) {
                    return;
                } else {
                    drawingCache.destroy();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean push(BaseDanmaku baseDanmaku, int i, boolean z) {
            if (i > 0) {
                clearTimeOutAndFilteredCaches(i, z);
            }
            this.mCaches.addItem(baseDanmaku);
            this.mRealSize += i;
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimeOutCaches() {
            this.mCaches.forEach(new IDanmakus.DefaultConsumer<BaseDanmaku>() { // from class: master.flame.danmaku.controller.CacheManagingDrawTask.CacheManager.3
                @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
                public int accept(BaseDanmaku baseDanmaku) {
                    if (!baseDanmaku.isTimeOut()) {
                        return 1;
                    }
                    IDrawingCache<?> iDrawingCache = baseDanmaku.cache;
                    if (CacheManagingDrawTask.this.mContext.cachingPolicy.periodOfRecycle == -1 && iDrawingCache != null && !iDrawingCache.hasReferences() && iDrawingCache.size() / CacheManagingDrawTask.this.mMaxCacheSize < CacheManagingDrawTask.this.mContext.cachingPolicy.forceRecyleThreshold) {
                        return 0;
                    }
                    if (!CacheManager.this.mEndFlag) {
                        synchronized (CacheManagingDrawTask.this.mDrawingNotify) {
                            try {
                                try {
                                    CacheManagingDrawTask.this.mDrawingNotify.wait(30L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    return 1;
                                }
                            } finally {
                            }
                        }
                    }
                    CacheManager.this.entryRemoved(false, baseDanmaku, null);
                    return 2;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public BaseDanmaku findReusableCache(final BaseDanmaku baseDanmaku, final boolean z, final int i) {
            final int slopPixel = (!z ? CacheManagingDrawTask.this.mDisp.getSlopPixel() * 2 : 0) + CacheManagingDrawTask.this.mContext.cachingPolicy.reusableOffsetPixel;
            IDanmakus.Consumer<BaseDanmaku, BaseDanmaku> consumer = new IDanmakus.Consumer<BaseDanmaku, BaseDanmaku>() { // from class: master.flame.danmaku.controller.CacheManagingDrawTask.CacheManager.4
                int count = 0;
                BaseDanmaku mResult;

                @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
                public BaseDanmaku result() {
                    return this.mResult;
                }

                @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
                public int accept(BaseDanmaku baseDanmaku2) {
                    int i2 = this.count;
                    this.count = i2 + 1;
                    if (i2 >= i) {
                        return 1;
                    }
                    IDrawingCache<?> drawingCache = baseDanmaku2.getDrawingCache();
                    if (drawingCache != null && drawingCache.get() != null) {
                        if (baseDanmaku2.paintWidth == baseDanmaku.paintWidth && baseDanmaku2.paintHeight == baseDanmaku.paintHeight && baseDanmaku2.underlineColor == baseDanmaku.underlineColor && baseDanmaku2.borderColor == baseDanmaku.borderColor && baseDanmaku2.textColor == baseDanmaku.textColor && baseDanmaku2.text.equals(baseDanmaku.text) && baseDanmaku2.tag == baseDanmaku.tag) {
                            this.mResult = baseDanmaku2;
                            return 1;
                        }
                        if (z) {
                            return 0;
                        }
                        if (!baseDanmaku2.isTimeOut()) {
                            return 1;
                        }
                        if (drawingCache.hasReferences()) {
                            return 0;
                        }
                        float fWidth = drawingCache.width() - baseDanmaku.paintWidth;
                        float fHeight = drawingCache.height() - baseDanmaku.paintHeight;
                        if (fWidth >= 0.0f) {
                            int i3 = slopPixel;
                            if (fWidth <= i3 && fHeight >= 0.0f && fHeight <= i3) {
                                this.mResult = baseDanmaku2;
                                return 1;
                            }
                        }
                    }
                    return 0;
                }
            };
            this.mCaches.forEach(consumer);
            return consumer.result();
        }

        public class CacheHandler extends Handler {
            public static final int ADD_DANMAKU = 2;
            public static final int BUILD_CACHES = 3;
            public static final int CLEAR_ALL_CACHES = 7;
            public static final int CLEAR_OUTSIDE_CACHES = 8;
            public static final int CLEAR_OUTSIDE_CACHES_AND_RESET = 9;
            public static final int CLEAR_TIMEOUT_CACHES = 4;
            public static final int DISABLE_CANCEL_FLAG = 18;
            public static final int DISPATCH_ACTIONS = 16;
            private static final int PREPARE = 1;
            public static final int QUIT = 6;
            public static final int REBUILD_CACHE = 17;
            public static final int SEEK = 5;
            private boolean mCancelFlag;
            private boolean mIsPlayerPause;
            private boolean mPause;
            private boolean mSeekedFlag;

            public CacheHandler(Looper looper) {
                super(looper);
            }

            public void requestCancelCaching() {
                this.mCancelFlag = true;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                switch (i) {
                    case 1:
                        CacheManager.this.evictAllNotInScreen();
                        for (int i2 = 0; i2 < 300; i2++) {
                            CacheManager.this.mCachePool.release(new DrawingCache());
                        }
                        break;
                    case 2:
                        addDanmakuAndBuildCache((BaseDanmaku) message.obj);
                        return;
                    case 3:
                        removeMessages(3);
                        boolean z = !(CacheManagingDrawTask.this.mTaskListener == null || CacheManagingDrawTask.this.mReadyState) || this.mSeekedFlag;
                        prepareCaches(z);
                        if (z) {
                            this.mSeekedFlag = false;
                        }
                        if (CacheManagingDrawTask.this.mTaskListener == null || CacheManagingDrawTask.this.mReadyState) {
                            return;
                        }
                        CacheManagingDrawTask.this.mTaskListener.ready();
                        CacheManagingDrawTask.this.mReadyState = true;
                        return;
                    case 4:
                        CacheManager.this.clearTimeOutCaches();
                        return;
                    case 5:
                        Long l = (Long) message.obj;
                        if (l != null) {
                            long jLongValue = l.longValue();
                            long j = CacheManagingDrawTask.this.mCacheTimer.currMillisecond;
                            CacheManagingDrawTask.this.mCacheTimer.update(jLongValue);
                            this.mSeekedFlag = true;
                            long firstCacheTime = CacheManager.this.getFirstCacheTime();
                            if (jLongValue > j || firstCacheTime - jLongValue > CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) {
                                CacheManager.this.evictAllNotInScreen();
                            } else {
                                CacheManager.this.clearTimeOutCaches();
                            }
                            prepareCaches(true);
                            resume();
                            return;
                        }
                        return;
                    case 6:
                        removeCallbacksAndMessages(null);
                        this.mPause = true;
                        CacheManager.this.evictAll();
                        CacheManager.this.clearCachePool();
                        getLooper().quit();
                        return;
                    case 7:
                        CacheManager.this.evictAll();
                        CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond - CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
                        this.mSeekedFlag = true;
                        return;
                    case 8:
                        CacheManager.this.evictAllNotInScreen();
                        CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
                        return;
                    case 9:
                        CacheManager.this.evictAllNotInScreen();
                        CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
                        CacheManagingDrawTask.this.requestClear();
                        return;
                    default:
                        switch (i) {
                            case 17:
                                BaseDanmaku baseDanmaku = (BaseDanmaku) message.obj;
                                if (baseDanmaku != null) {
                                    IDrawingCache<?> drawingCache = baseDanmaku.getDrawingCache();
                                    if (!((baseDanmaku.requestFlags & 1) != 0) && drawingCache != null && drawingCache.get() != null && !drawingCache.hasReferences()) {
                                        baseDanmaku.cache = DanmakuUtils.buildDanmakuDrawingCache(baseDanmaku, CacheManagingDrawTask.this.mDisp, (DrawingCache) baseDanmaku.cache, CacheManagingDrawTask.this.mContext.cachingPolicy.bitsPerPixelOfCache);
                                        CacheManager.this.push(baseDanmaku, 0, true);
                                    } else if (baseDanmaku.isLive) {
                                        CacheManager.this.clearCache(baseDanmaku);
                                        createCache(baseDanmaku);
                                    } else {
                                        if (drawingCache != null && drawingCache.hasReferences()) {
                                            drawingCache.destroy();
                                        }
                                        CacheManager.this.entryRemoved(true, baseDanmaku, null);
                                        addDanmakuAndBuildCache(baseDanmaku);
                                    }
                                }
                                break;
                            case 18:
                                this.mCancelFlag = false;
                                break;
                        }
                        return;
                }
                long jDispatchAction = dispatchAction();
                if (jDispatchAction <= 0) {
                    jDispatchAction = CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION / 2;
                }
                sendEmptyMessageDelayed(16, jDispatchAction);
            }

            private long dispatchAction() {
                if (CacheManagingDrawTask.this.mCacheTimer.currMillisecond <= CacheManagingDrawTask.this.mTimer.currMillisecond - CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) {
                    if (CacheManagingDrawTask.this.mContext.cachingPolicy.periodOfRecycle != -1) {
                        CacheManager.this.evictAllNotInScreen();
                    }
                    CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
                    sendEmptyMessage(3);
                    return 0L;
                }
                float poolPercent = CacheManager.this.getPoolPercent();
                BaseDanmaku baseDanmakuFirst = CacheManager.this.mCaches.first();
                long actualTime = baseDanmakuFirst != null ? baseDanmakuFirst.getActualTime() - CacheManagingDrawTask.this.mTimer.currMillisecond : 0L;
                long j = CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION * 2;
                if (poolPercent < 0.6f && actualTime > CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) {
                    CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
                    removeMessages(3);
                    sendEmptyMessage(3);
                    return 0L;
                }
                if (poolPercent > 0.4f && actualTime < (-j)) {
                    removeMessages(4);
                    sendEmptyMessage(4);
                    return 0L;
                }
                if (poolPercent >= 0.9f) {
                    return 0L;
                }
                long j2 = CacheManagingDrawTask.this.mCacheTimer.currMillisecond - CacheManagingDrawTask.this.mTimer.currMillisecond;
                if (baseDanmakuFirst != null && baseDanmakuFirst.isTimeOut() && j2 < (-CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION)) {
                    CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond);
                    sendEmptyMessage(8);
                    sendEmptyMessage(3);
                    return 0L;
                }
                if (j2 > j) {
                    return 0L;
                }
                removeMessages(3);
                sendEmptyMessage(3);
                return 0L;
            }

            private void releaseDanmakuCache(BaseDanmaku baseDanmaku, DrawingCache drawingCache) {
                if (drawingCache == null) {
                    drawingCache = (DrawingCache) baseDanmaku.cache;
                }
                baseDanmaku.cache = null;
                if (drawingCache == null) {
                    return;
                }
                drawingCache.destroy();
                CacheManager.this.mCachePool.release(drawingCache);
            }

            private void preMeasure() {
                IDanmakus iDanmakusSubnew;
                try {
                    long j = CacheManagingDrawTask.this.mTimer.currMillisecond;
                    iDanmakusSubnew = CacheManagingDrawTask.this.danmakuList.subnew(j - CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION, (CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION * 2) + j);
                } catch (Exception unused) {
                    iDanmakusSubnew = null;
                }
                if (iDanmakusSubnew == null || iDanmakusSubnew.isEmpty()) {
                    return;
                }
                iDanmakusSubnew.forEach(new IDanmakus.DefaultConsumer<BaseDanmaku>() { // from class: master.flame.danmaku.controller.CacheManagingDrawTask.CacheManager.CacheHandler.1
                    @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
                    public int accept(BaseDanmaku baseDanmaku) {
                        if (CacheHandler.this.mPause || CacheHandler.this.mCancelFlag) {
                            return 1;
                        }
                        if (!baseDanmaku.hasPassedFilter()) {
                            CacheManagingDrawTask.this.mContext.mDanmakuFilters.filter(baseDanmaku, 0, 0, null, true, CacheManagingDrawTask.this.mContext);
                        }
                        if (baseDanmaku.isFiltered()) {
                            return 0;
                        }
                        if (!baseDanmaku.isMeasured()) {
                            baseDanmaku.measure(CacheManagingDrawTask.this.mDisp, true);
                        }
                        if (!baseDanmaku.isPrepared()) {
                            baseDanmaku.prepare(CacheManagingDrawTask.this.mDisp, true);
                        }
                        return 0;
                    }
                });
            }

            private long prepareCaches(final boolean z) {
                IDanmakus iDanmakusSubnew;
                boolean z2;
                preMeasure();
                final long j = CacheManagingDrawTask.this.mCacheTimer.currMillisecond - 30;
                long j2 = j + (CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION * ((long) CacheManager.this.mScreenSize));
                if (j2 < CacheManagingDrawTask.this.mTimer.currMillisecond) {
                    return 0L;
                }
                final long jUptimeMillis = SystemClock.uptimeMillis();
                IDanmakus iDanmakus = null;
                int i = 0;
                boolean z3 = false;
                while (true) {
                    try {
                        boolean z4 = z3;
                        iDanmakusSubnew = CacheManagingDrawTask.this.danmakuList.subnew(j, j2);
                        z2 = z4;
                    } catch (Exception unused) {
                        SystemClock.sleep(10L);
                        iDanmakusSubnew = iDanmakus;
                        z2 = true;
                    }
                    i++;
                    if (i >= 3 || iDanmakusSubnew != null || !z2) {
                        break;
                    }
                    IDanmakus iDanmakus2 = iDanmakusSubnew;
                    z3 = z2;
                    iDanmakus = iDanmakus2;
                }
                if (iDanmakusSubnew == null) {
                    CacheManagingDrawTask.this.mCacheTimer.update(j2);
                    return 0L;
                }
                BaseDanmaku baseDanmakuFirst = iDanmakusSubnew.first();
                final BaseDanmaku baseDanmakuLast = iDanmakusSubnew.last();
                if (baseDanmakuFirst == null || baseDanmakuLast == null) {
                    CacheManagingDrawTask.this.mCacheTimer.update(j2);
                    return 0L;
                }
                long actualTime = baseDanmakuFirst.getActualTime() - CacheManagingDrawTask.this.mTimer.currMillisecond;
                final long jMin = z ? 0L : Math.min(100L, actualTime < 0 ? 30L : ((actualTime * 10) / CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION) + 30);
                final int size = iDanmakusSubnew.size();
                iDanmakusSubnew.forEach(new IDanmakus.DefaultConsumer<BaseDanmaku>() { // from class: master.flame.danmaku.controller.CacheManagingDrawTask.CacheManager.CacheHandler.2
                    int orderInScreen = 0;
                    int currScreenIndex = 0;

                    @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
                    public int accept(BaseDanmaku baseDanmaku) {
                        if (CacheHandler.this.mPause || CacheHandler.this.mCancelFlag || baseDanmakuLast.getActualTime() < CacheManagingDrawTask.this.mTimer.currMillisecond) {
                            return 1;
                        }
                        IDrawingCache<?> drawingCache = baseDanmaku.getDrawingCache();
                        if (drawingCache != null && drawingCache.get() != null) {
                            return 0;
                        }
                        if (!z && (baseDanmaku.isTimeOut() || !baseDanmaku.isOutside())) {
                            return 0;
                        }
                        if (!baseDanmaku.hasPassedFilter()) {
                            CacheManagingDrawTask.this.mContext.mDanmakuFilters.filter(baseDanmaku, this.orderInScreen, size, null, true, CacheManagingDrawTask.this.mContext);
                        }
                        if (baseDanmaku.priority == 0 && baseDanmaku.isFiltered()) {
                            return 0;
                        }
                        if (baseDanmaku.getType() == 1) {
                            int actualTime2 = (int) ((baseDanmaku.getActualTime() - j) / CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
                            if (this.currScreenIndex == actualTime2) {
                                this.orderInScreen++;
                            } else {
                                this.orderInScreen = 0;
                                this.currScreenIndex = actualTime2;
                            }
                        }
                        if (!z && !CacheHandler.this.mIsPlayerPause) {
                            try {
                                synchronized (CacheManagingDrawTask.this.mDrawingNotify) {
                                    CacheManagingDrawTask.this.mDrawingNotify.wait(jMin);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                return 1;
                            }
                        }
                        CacheHandler.this.buildCache(baseDanmaku, false);
                        if (!z) {
                            long jUptimeMillis2 = SystemClock.uptimeMillis() - jUptimeMillis;
                            DanmakuFactory danmakuFactory = CacheManagingDrawTask.this.mContext.mDanmakuFactory;
                            if (jUptimeMillis2 >= ((long) CacheManager.this.mScreenSize) * DanmakuFactory.COMMON_DANMAKU_DURATION) {
                                return 1;
                            }
                        }
                        return 0;
                    }
                });
                long jUptimeMillis2 = SystemClock.uptimeMillis() - jUptimeMillis;
                CacheManagingDrawTask.this.mCacheTimer.update(j2);
                return jUptimeMillis2;
            }

            public boolean createCache(BaseDanmaku baseDanmaku) {
                DrawingCache drawingCacheBuildDanmakuDrawingCache;
                if (!baseDanmaku.isMeasured()) {
                    baseDanmaku.measure(CacheManagingDrawTask.this.mDisp, true);
                }
                try {
                    drawingCacheBuildDanmakuDrawingCache = (DrawingCache) CacheManager.this.mCachePool.acquire();
                } catch (Exception unused) {
                    drawingCacheBuildDanmakuDrawingCache = null;
                } catch (OutOfMemoryError unused2) {
                    drawingCacheBuildDanmakuDrawingCache = null;
                }
                try {
                    drawingCacheBuildDanmakuDrawingCache = DanmakuUtils.buildDanmakuDrawingCache(baseDanmaku, CacheManagingDrawTask.this.mDisp, drawingCacheBuildDanmakuDrawingCache, CacheManagingDrawTask.this.mContext.cachingPolicy.bitsPerPixelOfCache);
                    baseDanmaku.cache = drawingCacheBuildDanmakuDrawingCache;
                    return true;
                } catch (Exception unused3) {
                    if (drawingCacheBuildDanmakuDrawingCache != null) {
                        CacheManager.this.mCachePool.release(drawingCacheBuildDanmakuDrawingCache);
                    }
                    baseDanmaku.cache = null;
                    return false;
                } catch (OutOfMemoryError unused4) {
                    if (drawingCacheBuildDanmakuDrawingCache != null) {
                        CacheManager.this.mCachePool.release(drawingCacheBuildDanmakuDrawingCache);
                    }
                    baseDanmaku.cache = null;
                    return false;
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public byte buildCache(BaseDanmaku baseDanmaku, boolean z) {
                if (!baseDanmaku.isMeasured()) {
                    baseDanmaku.measure(CacheManagingDrawTask.this.mDisp, true);
                }
                DrawingCache drawingCache = null;
                try {
                    BaseDanmaku baseDanmakuFindReusableCache = CacheManager.this.findReusableCache(baseDanmaku, true, CacheManagingDrawTask.this.mContext.cachingPolicy.maxTimesOfStrictReusableFinds);
                    DrawingCache drawingCache2 = baseDanmakuFindReusableCache != null ? (DrawingCache) baseDanmakuFindReusableCache.cache : null;
                    try {
                        if (drawingCache2 == null) {
                            BaseDanmaku baseDanmakuFindReusableCache2 = CacheManager.this.findReusableCache(baseDanmaku, false, CacheManagingDrawTask.this.mContext.cachingPolicy.maxTimesOfReusableFinds);
                            if (baseDanmakuFindReusableCache2 != null) {
                                drawingCache2 = (DrawingCache) baseDanmakuFindReusableCache2.cache;
                            }
                            if (drawingCache2 != null) {
                                baseDanmakuFindReusableCache2.cache = null;
                                baseDanmaku.cache = DanmakuUtils.buildDanmakuDrawingCache(baseDanmaku, CacheManagingDrawTask.this.mDisp, drawingCache2, CacheManagingDrawTask.this.mContext.cachingPolicy.bitsPerPixelOfCache);
                                CacheManagingDrawTask.this.mCacheManager.push(baseDanmaku, 0, z);
                                return (byte) 0;
                            }
                            int cacheSize = DanmakuUtils.getCacheSize((int) baseDanmaku.paintWidth, (int) baseDanmaku.paintHeight, CacheManagingDrawTask.this.mContext.cachingPolicy.bitsPerPixelOfCache / 8);
                            if (cacheSize * 2 > CacheManagingDrawTask.this.mMaxCacheSize) {
                                return (byte) 1;
                            }
                            if (!z && CacheManager.this.mRealSize + cacheSize > CacheManager.this.mMaxSize) {
                                CacheManagingDrawTask.this.mCacheManager.clearTimeOutAndFilteredCaches(cacheSize, false);
                                return (byte) 1;
                            }
                            DrawingCache drawingCacheBuildDanmakuDrawingCache = DanmakuUtils.buildDanmakuDrawingCache(baseDanmaku, CacheManagingDrawTask.this.mDisp, (DrawingCache) CacheManager.this.mCachePool.acquire(), CacheManagingDrawTask.this.mContext.cachingPolicy.bitsPerPixelOfCache);
                            baseDanmaku.cache = drawingCacheBuildDanmakuDrawingCache;
                            boolean zPush = CacheManagingDrawTask.this.mCacheManager.push(baseDanmaku, CacheManager.this.sizeOf(baseDanmaku), z);
                            if (!zPush) {
                                releaseDanmakuCache(baseDanmaku, drawingCacheBuildDanmakuDrawingCache);
                            }
                            return !zPush ? (byte) 1 : (byte) 0;
                        }
                        drawingCache2.increaseReference();
                        baseDanmaku.cache = drawingCache2;
                        CacheManagingDrawTask.this.mCacheManager.push(baseDanmaku, 0, z);
                        return (byte) 0;
                    } catch (Exception unused) {
                        drawingCache = drawingCache2;
                        releaseDanmakuCache(baseDanmaku, drawingCache);
                        return (byte) 1;
                    } catch (OutOfMemoryError unused2) {
                        drawingCache = drawingCache2;
                        releaseDanmakuCache(baseDanmaku, drawingCache);
                        return (byte) 1;
                    }
                } catch (Exception unused3) {
                } catch (OutOfMemoryError unused4) {
                }
            }

            private final void addDanmakuAndBuildCache(BaseDanmaku baseDanmaku) {
                if (baseDanmaku.isTimeOut()) {
                    return;
                }
                if (baseDanmaku.getActualTime() <= CacheManagingDrawTask.this.mCacheTimer.currMillisecond + CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION || baseDanmaku.isLive) {
                    if (baseDanmaku.priority == 0 && baseDanmaku.isFiltered()) {
                        return;
                    }
                    IDrawingCache<?> drawingCache = baseDanmaku.getDrawingCache();
                    if (drawingCache == null || drawingCache.get() == null) {
                        buildCache(baseDanmaku, true);
                    }
                }
            }

            public void begin() {
                sendEmptyMessage(1);
                sendEmptyMessageDelayed(4, CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
            }

            public void pause() {
                this.mPause = true;
                sendEmptyMessage(6);
            }

            public void resume() {
                sendEmptyMessage(18);
                this.mPause = false;
                removeMessages(16);
                sendEmptyMessage(16);
                sendEmptyMessageDelayed(4, CacheManagingDrawTask.this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
            }

            public boolean isPause() {
                return this.mPause;
            }

            public void requestBuildCacheAndDraw(long j) {
                removeMessages(3);
                this.mSeekedFlag = true;
                sendEmptyMessage(18);
                CacheManagingDrawTask.this.mCacheTimer.update(CacheManagingDrawTask.this.mTimer.currMillisecond + j);
                sendEmptyMessage(3);
            }

            public void onPlayStateChanged(boolean z) {
                this.mIsPlayerPause = !z;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTimeOutAndFilteredCaches(final int i, final boolean z) {
            this.mCaches.forEach(new IDanmakus.DefaultConsumer<BaseDanmaku>() { // from class: master.flame.danmaku.controller.CacheManagingDrawTask.CacheManager.5
                @Override // master.flame.danmaku.danmaku.model.IDanmakus.Consumer
                public int accept(BaseDanmaku baseDanmaku) {
                    if (CacheManager.this.mEndFlag || CacheManager.this.mRealSize + i <= CacheManager.this.mMaxSize) {
                        return 1;
                    }
                    if (!baseDanmaku.isTimeOut() && !baseDanmaku.isFiltered()) {
                        return z ? 1 : 0;
                    }
                    CacheManager.this.entryRemoved(false, baseDanmaku, null);
                    return 2;
                }
            });
        }

        public long getFirstCacheTime() {
            BaseDanmaku baseDanmakuFirst;
            Danmakus danmakus = this.mCaches;
            if (danmakus == null || danmakus.size() <= 0 || (baseDanmakuFirst = this.mCaches.first()) == null) {
                return 0L;
            }
            return baseDanmakuFirst.getActualTime();
        }

        public void requestBuild(long j) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler != null) {
                cacheHandler.requestBuildCacheAndDraw(j);
            }
        }

        public void requestClearAll() {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.removeMessages(3);
            this.mHandler.removeMessages(18);
            this.mHandler.requestCancelCaching();
            this.mHandler.removeMessages(7);
            this.mHandler.sendEmptyMessage(7);
        }

        public void requestClearUnused() {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.removeMessages(9);
            this.mHandler.sendEmptyMessage(9);
        }

        public void requestClearTimeout() {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.removeMessages(4);
            this.mHandler.sendEmptyMessage(4);
        }

        public void post(Runnable runnable) {
            CacheHandler cacheHandler = this.mHandler;
            if (cacheHandler == null) {
                return;
            }
            cacheHandler.post(runnable);
        }
    }

    @Override // master.flame.danmaku.controller.DrawTask
    public boolean onDanmakuConfigChanged(DanmakuContext danmakuContext, DanmakuContext.DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        CacheManager cacheManager;
        CacheManager cacheManager2;
        if (!super.handleOnDanmakuConfigChanged(danmakuContext, danmakuConfigTag, objArr)) {
            if (DanmakuContext.DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(danmakuConfigTag)) {
                this.mDisp.resetSlopPixel(this.mContext.scaleTextSize);
                requestClear();
            } else if (danmakuConfigTag.isVisibilityRelatedTag()) {
                if (objArr != null && objArr.length > 0 && objArr[0] != null && ((!(objArr[0] instanceof Boolean) || ((Boolean) objArr[0]).booleanValue()) && (cacheManager2 = this.mCacheManager) != null)) {
                    cacheManager2.requestBuild(0L);
                }
                requestClear();
            } else if (DanmakuContext.DanmakuConfigTag.TRANSPARENCY.equals(danmakuConfigTag) || DanmakuContext.DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag) || DanmakuContext.DanmakuConfigTag.DANMAKU_STYLE.equals(danmakuConfigTag)) {
                if (DanmakuContext.DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag)) {
                    this.mDisp.resetSlopPixel(this.mContext.scaleTextSize);
                }
                CacheManager cacheManager3 = this.mCacheManager;
                if (cacheManager3 != null) {
                    cacheManager3.requestClearAll();
                    this.mCacheManager.requestBuild(-this.mContext.mDanmakuFactory.MAX_DANMAKU_DURATION);
                }
            } else {
                CacheManager cacheManager4 = this.mCacheManager;
                if (cacheManager4 != null) {
                    cacheManager4.requestClearUnused();
                    this.mCacheManager.requestBuild(0L);
                }
            }
        }
        if (this.mTaskListener == null || (cacheManager = this.mCacheManager) == null) {
            return true;
        }
        cacheManager.post(new Runnable() { // from class: master.flame.danmaku.controller.CacheManagingDrawTask.1
            @Override // java.lang.Runnable
            public void run() {
                CacheManagingDrawTask.this.mTaskListener.onDanmakuConfigChanged();
            }
        });
        return true;
    }
}
