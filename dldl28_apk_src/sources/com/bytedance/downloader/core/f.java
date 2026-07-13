package com.bytedance.downloader.core;

/* JADX INFO: loaded from: classes2.dex */
final class f implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ DownloadDispatcher f354a;

    f(DownloadDispatcher downloadDispatcher) {
        this.f354a = downloadDispatcher;
    }

    @Override // com.bytedance.downloader.core.t
    public final void a(m mVar) {
        this.f354a.mTaskQueue.c(mVar.c());
        if (this.f354a.mCallback != null) {
            this.f354a.mCallback.onDownloadPrepared(mVar.d());
        }
    }

    @Override // com.bytedance.downloader.core.t
    public final void a(m mVar, int i) {
        if (i % 5 == 0) {
            this.f354a.mTaskQueue.c(mVar.c());
        }
        if (this.f354a.mCallback != null) {
            this.f354a.mCallback.onDownloadProgress(mVar.d(), i);
        }
    }

    @Override // com.bytedance.downloader.core.t
    public final void a(m mVar, int i, String str) {
        this.f354a.mRunningTaskPool.b(mVar);
        this.f354a.mTaskQueue.c(mVar.c());
        if (this.f354a.mCallback != null) {
            this.f354a.mCallback.onDownloadFailed(mVar.d(), i, str);
        }
        if (this.f354a.mConfig.disappearWhenTaskIsDone()) {
            this.f354a.mTaskQueue.b(mVar.c());
        }
        this.f354a.doNextTask();
    }

    @Override // com.bytedance.downloader.core.t
    public final void a(m mVar, long j) {
        if (this.f354a.mCallback != null) {
            this.f354a.mCallback.onDownloadSpeed(mVar.d(), j);
        }
    }

    @Override // com.bytedance.downloader.core.t
    public final void b(m mVar) {
        this.f354a.mTaskQueue.c(mVar.c());
        if (this.f354a.mCallback != null) {
            this.f354a.mCallback.onDownloadStarted(mVar.d());
        }
    }

    @Override // com.bytedance.downloader.core.t
    public final void b(m mVar, int i, String str) {
        if (this.f354a.mCallback != null) {
            this.f354a.mCallback.onDownloadWarning(mVar.d(), i, str);
        }
    }

    @Override // com.bytedance.downloader.core.t
    public final void c(m mVar) {
        this.f354a.mRunningTaskPool.b(mVar);
        this.f354a.mTaskQueue.c(mVar.c());
        if (this.f354a.mCallback != null) {
            this.f354a.mCallback.onDownloadCompleted(mVar.d());
        }
        if (this.f354a.mConfig.disappearWhenTaskIsDone()) {
            this.f354a.mTaskQueue.b(mVar.c());
        }
    }

    @Override // com.bytedance.downloader.core.t
    public final void d(m mVar) {
        this.f354a.mRunningTaskPool.b(mVar);
        this.f354a.mTaskQueue.c(mVar.c());
        if (this.f354a.mCallback != null) {
            this.f354a.mCallback.onDownloadCancelled(mVar.d());
        }
        this.f354a.doNextTask();
    }

    @Override // com.bytedance.downloader.core.t
    public final void e(m mVar) {
        this.f354a.mTaskQueue.c(mVar.c());
        if (this.f354a.mCallback != null) {
            this.f354a.mCallback.onDownloadUpdated(mVar.d());
        }
    }
}
