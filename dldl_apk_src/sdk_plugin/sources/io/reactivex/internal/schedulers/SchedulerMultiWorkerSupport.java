package io.reactivex.internal.schedulers;

import io.reactivex.Scheduler;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface SchedulerMultiWorkerSupport {

    public interface WorkerCallback {
        void onWorker(int i, Scheduler.Worker worker);
    }

    void createWorkers(int i, WorkerCallback workerCallback);
}
