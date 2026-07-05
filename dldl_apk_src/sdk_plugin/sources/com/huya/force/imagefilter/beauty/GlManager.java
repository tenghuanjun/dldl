package com.huya.force.imagefilter.beauty;

import android.opengl.EGLContext;
import android.opengl.EGLSurface;
import android.os.Handler;
import android.os.Looper;
import com.huya.force.gles.EglCore;
import com.huya.force.log.ForceLog;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GlManager implements Runnable {
    private static final String TAG = "GlManager";
    private EglCore mEglCore;
    private EGLSurface mEnvSurface;
    public Thread mLooperThread;
    public Handler mGlHandler = null;
    public volatile boolean mIsRuning = false;
    private final AtomicBoolean mStartLock = new AtomicBoolean(false);
    private final AtomicBoolean mQuitLock = new AtomicBoolean(false);
    private int mDefaultWidth = 10;
    private int mDefaultHeight = 10;

    public GlManager() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Thread thread = new Thread(this, TAG);
        this.mLooperThread = thread;
        thread.start();
        synchronized (this.mStartLock) {
            if (!this.mStartLock.get()) {
                try {
                    this.mStartLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        ForceLog.info(TAG, "[procedure] GlManager constructor cost:" + (System.currentTimeMillis() - jCurrentTimeMillis));
    }

    public long getThreadId() {
        return this.mLooperThread.getId();
    }

    public Handler getHandler() {
        return this.mGlHandler;
    }

    public EglCore getEglCore() {
        return this.mEglCore;
    }

    public void resetContext() {
        this.mEglCore.makeCurrent(this.mEnvSurface);
    }

    private void InitEGL() {
        EglCore eglCore = new EglCore(null, 1);
        this.mEglCore = eglCore;
        EGLSurface eGLSurfaceCreateOffscreenSurface = eglCore.createOffscreenSurface(this.mDefaultWidth, this.mDefaultHeight);
        this.mEnvSurface = eGLSurfaceCreateOffscreenSurface;
        if (eGLSurfaceCreateOffscreenSurface != null) {
            this.mEglCore.makeCurrent(eGLSurfaceCreateOffscreenSurface);
        }
        ForceLog.info(TAG, "[procedure] Texture created thread id:" + Thread.currentThread().getId());
    }

    private void deInitEGL() {
        if (this.mEnvSurface != null) {
            this.mEglCore.makeNothingCurrent();
            this.mEglCore.releaseSurface(this.mEnvSurface);
            this.mEnvSurface = null;
        }
        EglCore eglCore = this.mEglCore;
        if (eglCore != null) {
            eglCore.release();
            this.mEglCore = null;
        }
    }

    public void quit() {
        ForceLog.info(TAG, "[tracer] quit GlManager thread.");
        if (this.mQuitLock.get()) {
            return;
        }
        synchronized (this.mQuitLock) {
            if (this.mQuitLock.get()) {
                return;
            }
            this.mGlHandler.post(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GlManager.1
                @Override // java.lang.Runnable
                public void run() {
                    Looper looperMyLooper = Looper.myLooper();
                    if (looperMyLooper != null) {
                        looperMyLooper.quitSafely();
                    }
                }
            });
            try {
                this.mQuitLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        ForceLog.info(TAG, "[procedure] glManager thread begin!!!!!");
        this.mIsRuning = true;
        try {
            try {
                Looper.prepare();
                this.mGlHandler = new Handler();
                synchronized (this.mStartLock) {
                    this.mStartLock.set(true);
                    this.mStartLock.notifyAll();
                }
                InitEGL();
                Looper.loop();
                this.mIsRuning = false;
                deInitEGL();
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    ForceLog.error(TAG, "[exception] exception occur, " + th.toString());
                    this.mIsRuning = false;
                    deInitEGL();
                } catch (Throwable th2) {
                    try {
                        this.mIsRuning = false;
                        deInitEGL();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    throw th2;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        synchronized (this.mQuitLock) {
            this.mQuitLock.set(true);
            this.mQuitLock.notifyAll();
        }
        ForceLog.info(TAG, "[procedure] glManager thread exit!!!!!");
    }

    public boolean checkSameThread() {
        return Thread.currentThread().getId() == getThreadId();
    }

    public boolean post(Runnable runnable) {
        try {
            return this.mGlHandler.post(runnable);
        } catch (Throwable th) {
            ForceLog.error(TAG, "[exception] GlManager PostRunnable exeception:" + th.toString());
            return false;
        }
    }

    public boolean postDelay(Runnable runnable, long j) {
        try {
            return this.mGlHandler.postDelayed(runnable, j);
        } catch (Throwable th) {
            ForceLog.error(TAG, "[exception] GlManager PostRunnable exeception:" + th.toString());
            return false;
        }
    }

    public EGLContext getContext() {
        EglCore eglCore = this.mEglCore;
        if (eglCore != null) {
            return eglCore.getContext();
        }
        return null;
    }
}
