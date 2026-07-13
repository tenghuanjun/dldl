package com.bytedance.http;

import com.bytedance.http.b.c;
import com.bytedance.http.b.d;
import com.bytedance.http.b.g;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class Scheduler {
    private static final int MAX_REQUESTS = 16;
    private final ExecutorService mExecutorService;
    private final Deque mReadyAsyncCalls = new ArrayDeque();
    private final Deque mRunningAsyncCalls = new ArrayDeque();

    public Scheduler(ExecutorService executorService) {
        if (executorService == null) {
            this.mExecutorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), g.a("Dispatcher", false));
        } else {
            this.mExecutorService = executorService;
        }
    }

    private void promoteCalls() {
        if (this.mRunningAsyncCalls.size() < 16 && !this.mReadyAsyncCalls.isEmpty()) {
            try {
                ExecutorService executorService = this.mExecutorService;
                if (executorService instanceof ThreadPoolExecutor) {
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
                    if (threadPoolExecutor.getActiveCount() >= threadPoolExecutor.getMaximumPoolSize()) {
                        return;
                    }
                }
                Iterator it = this.mReadyAsyncCalls.iterator();
                while (it.hasNext()) {
                    d dVar = (d) it.next();
                    it.remove();
                    this.mRunningAsyncCalls.add(dVar);
                    this.mExecutorService.submit(dVar);
                    if (this.mRunningAsyncCalls.size() >= 16) {
                        return;
                    }
                }
            } catch (RejectedExecutionException e) {
                c.c(e.getMessage());
            }
        }
    }

    public synchronized void cancelAll() {
        Iterator it = this.mReadyAsyncCalls.iterator();
        while (it.hasNext()) {
            ((d) it.next()).b().cancel();
        }
        Iterator it2 = this.mRunningAsyncCalls.iterator();
        while (it2.hasNext()) {
            ((d) it2.next()).b().cancel();
        }
    }

    public synchronized void enqueue(d dVar) {
        if (dVar != null) {
            if (this.mRunningAsyncCalls.size() < 16) {
                this.mRunningAsyncCalls.add(dVar);
                this.mExecutorService.execute(dVar);
                return;
            }
            this.mReadyAsyncCalls.add(dVar);
        }
    }

    public synchronized void finished(d dVar) {
        if (!this.mRunningAsyncCalls.remove(dVar)) {
            throw new AssertionError("AsyncCall wasn't running!");
        }
        promoteCalls();
    }
}
