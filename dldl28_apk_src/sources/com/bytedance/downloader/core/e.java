package com.bytedance.downloader.core;

/* JADX INFO: loaded from: classes2.dex */
final class e implements s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private /* synthetic */ DownloadDispatcher f353a;

    e(DownloadDispatcher downloadDispatcher) {
        this.f353a = downloadDispatcher;
    }

    @Override // com.bytedance.downloader.core.s
    public final void a() {
        this.f353a.doNextTask();
    }

    @Override // com.bytedance.downloader.core.s
    public final void a(m mVar) {
        this.f353a.mRunningTaskPool.b(mVar);
        if (this.f353a.mCallback != null) {
            this.f353a.mCallback.onDownloadRemoved(mVar.d());
        }
        this.f353a.doNextTask();
    }
}
