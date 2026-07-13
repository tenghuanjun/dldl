package com.lzy.okserver.upload;

import com.lzy.okserver.task.PriorityBlockingQueue;
import com.lzy.okserver.task.XExecutor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class UploadThreadPool {
    private static final int KEEP_ALIVE_TIME = 1;
    private static final int MAX_IMUM_POOL_SIZE = 5;
    private static final TimeUnit UNIT = TimeUnit.HOURS;
    private int corePoolSize = 1;
    private XExecutor executor;

    public XExecutor getExecutor() {
        if (this.executor == null) {
            synchronized (UploadThreadPool.class) {
                if (this.executor == null) {
                    this.executor = new XExecutor(this.corePoolSize, 5, 1L, UNIT, new PriorityBlockingQueue(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return this.executor;
    }

    public void setCorePoolSize(int i) {
        if (i <= 0) {
            i = 1;
        }
        if (i > 5) {
            i = 5;
        }
        this.corePoolSize = i;
    }

    public void execute(Runnable runnable) {
        if (runnable != null) {
            getExecutor().execute(runnable);
        }
    }

    public void remove(Runnable runnable) {
        if (runnable != null) {
            getExecutor().remove(runnable);
        }
    }
}
