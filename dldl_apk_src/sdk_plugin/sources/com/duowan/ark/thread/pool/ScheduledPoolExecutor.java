package com.duowan.ark.thread.pool;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class ScheduledPoolExecutor implements ScheduledExecutor {
    private boolean[] mBusy;
    private int mCount;
    private ThreadFactory mFactory;
    private int mIndex = 0;
    private int mPriority;
    private String mType;
    private WorkThread[] mWorks;

    ScheduledPoolExecutor(String str, int i, int i2, ThreadFactory threadFactory) {
        this.mType = str;
        this.mPriority = i;
        this.mCount = i2;
        this.mFactory = threadFactory;
        init();
    }

    private void init() {
        int i = this.mCount;
        this.mBusy = new boolean[i];
        this.mWorks = new WorkThread[i];
        for (int i2 = 0; i2 < this.mCount; i2++) {
            this.mBusy[i2] = false;
            this.mWorks[i2] = this.mFactory.newThread(this.mType + "children " + i2, this.mPriority);
        }
    }

    @Override // com.duowan.ark.thread.pool.ScheduledExecutor
    public void execute(Runnable runnable, long j) {
        realExecute(runnable, j);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        realExecute(runnable, 0L);
    }

    public void realExecute(Runnable runnable, long j) {
        synchronized (this) {
            int workIndex = getWorkIndex();
            this.mWorks[workIndex].post(new RunnableDelegate(runnable, workIndex), j);
        }
    }

    private int getWorkIndex() {
        int i = 0;
        while (true) {
            int i2 = this.mCount;
            if (i < i2) {
                if (this.mBusy[i]) {
                    return i;
                }
                i++;
            } else {
                int i3 = this.mIndex + 1;
                this.mIndex = i3;
                return i3 % i2;
            }
        }
    }

    class RunnableDelegate implements Runnable {
        private Runnable mTask;
        private int mWorkIndex;

        public RunnableDelegate(Runnable runnable, int i) {
            this.mTask = runnable;
            this.mWorkIndex = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (ScheduledPoolExecutor.this) {
                ScheduledPoolExecutor.this.mBusy[this.mWorkIndex] = true;
            }
            this.mTask.run();
            synchronized (ScheduledPoolExecutor.this) {
                ScheduledPoolExecutor.this.mBusy[this.mWorkIndex] = false;
            }
        }
    }
}
