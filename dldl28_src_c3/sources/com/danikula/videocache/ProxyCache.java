package com.danikula.videocache;

import java.lang.Thread;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
class ProxyCache {
    private static final int MAX_READ_SOURCE_ATTEMPTS = 1;
    private final Cache cache;
    private final Source source;
    private volatile Thread sourceReaderThread;
    private volatile boolean stopped;
    private final Object wc = new Object();
    private final Object stopLock = new Object();
    private volatile int percentsAvailable = -1;
    private final AtomicInteger readSourceErrorsCount = new AtomicInteger();

    protected void onCachePercentsAvailableChanged(int i) {
    }

    public ProxyCache(Source source, Cache cache) {
        this.source = (Source) Preconditions.checkNotNull(source);
        this.cache = (Cache) Preconditions.checkNotNull(cache);
    }

    public int read(byte[] bArr, long j, int i) throws ProxyCacheException {
        ProxyCacheUtils.assertBuffer(bArr, j, i);
        while (!this.cache.isCompleted() && this.cache.available() < ((long) i) + j && !this.stopped) {
            readSourceAsync();
            waitForSourceData();
            checkReadSourceErrorsCount();
        }
        int i2 = this.cache.read(bArr, j, i);
        if (this.cache.isCompleted() && this.percentsAvailable != 100) {
            this.percentsAvailable = 100;
            onCachePercentsAvailableChanged(100);
        }
        return i2;
    }

    private void checkReadSourceErrorsCount() throws ProxyCacheException {
        int i = this.readSourceErrorsCount.get();
        if (i < 1) {
            return;
        }
        this.readSourceErrorsCount.set(0);
        throw new ProxyCacheException("Error reading source " + i + " times");
    }

    public void shutdown() {
        synchronized (this.stopLock) {
            try {
                this.stopped = true;
                if (this.sourceReaderThread != null) {
                    this.sourceReaderThread.interrupt();
                }
                this.cache.close();
            } catch (ProxyCacheException e) {
                onError(e);
            }
        }
    }

    private synchronized void readSourceAsync() throws ProxyCacheException {
        boolean z = (this.sourceReaderThread == null || this.sourceReaderThread.getState() == Thread.State.TERMINATED) ? false : true;
        if (!this.stopped && !this.cache.isCompleted() && !z) {
            this.sourceReaderThread = new Thread(new SourceReaderRunnable(), "Source reader for " + this.source);
            this.sourceReaderThread.start();
        }
    }

    private void waitForSourceData() throws ProxyCacheException {
        synchronized (this.wc) {
            try {
                try {
                    this.wc.wait(1000L);
                } catch (InterruptedException e) {
                    throw new ProxyCacheException("Waiting source data is interrupted!", e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void notifyNewCacheDataAvailable(long j, long j2) {
        onCacheAvailable(j, j2);
        synchronized (this.wc) {
            this.wc.notifyAll();
        }
    }

    protected void onCacheAvailable(long j, long j2) {
        int i = j2 == 0 ? 100 : (int) ((j / j2) * 100.0f);
        boolean z = i != this.percentsAvailable;
        if (j2 >= 0 && z) {
            onCachePercentsAvailableChanged(i);
        }
        this.percentsAvailable = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readSource() {
        long length = -1;
        long jAvailable = 0;
        try {
            jAvailable = this.cache.available();
            this.source.open(jAvailable);
            length = this.source.length();
            byte[] bArr = new byte[8192];
            while (true) {
                int i = this.source.read(bArr);
                if (i != -1) {
                    synchronized (this.stopLock) {
                        if (isStopped()) {
                            return;
                        } else {
                            this.cache.append(bArr, i);
                        }
                    }
                    jAvailable += (long) i;
                    notifyNewCacheDataAvailable(jAvailable, length);
                } else {
                    tryComplete();
                    onSourceRead();
                    break;
                }
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    private void onSourceRead() {
        this.percentsAvailable = 100;
        onCachePercentsAvailableChanged(this.percentsAvailable);
    }

    private void tryComplete() throws ProxyCacheException {
        synchronized (this.stopLock) {
            if (!isStopped() && this.cache.available() == this.source.length()) {
                this.cache.complete();
            }
        }
    }

    private boolean isStopped() {
        return Thread.currentThread().isInterrupted() || this.stopped;
    }

    private void closeSource() {
        try {
            this.source.close();
        } catch (ProxyCacheException e) {
            onError(new ProxyCacheException("Error closing source " + this.source, e));
        }
    }

    protected final void onError(Throwable th) {
        if (th instanceof InterruptedProxyCacheException) {
            HttpProxyCacheDebuger.printfLog("ProxyCache is interrupted");
        } else {
            HttpProxyCacheDebuger.printfError("ProxyCache error", th.getMessage());
        }
    }

    private class SourceReaderRunnable implements Runnable {
        private SourceReaderRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ProxyCache.this.readSource();
        }
    }
}
