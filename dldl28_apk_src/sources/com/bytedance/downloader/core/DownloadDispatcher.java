package com.bytedance.downloader.core;

import com.bytedance.dns.DnsResolver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadDispatcher {
    private static final String DOWNLOAD_SDK_VERSION = "1.7.7";
    private static final int KEEP_ALIVE_TIME = 10;
    private final IDownloadCallback mCallback;
    private final DownloadConfig mConfig;
    private final DnsResolver mDnsResolver;
    private final ExecutorService mExecutor;
    private final p mRunningTaskPool;
    private final t mTaskCallback = new f(this);
    private final a mTaskQueue;
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() << 2;
    private static final int MAXIMUM_POOL_SIZE = Runtime.getRuntime().availableProcessors() << 3;

    /* JADX INFO: Access modifiers changed from: private */
    final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List f350a = new ArrayList();
        private final s b;
        private j c;

        public a(boolean z, s sVar) {
            this.c = null;
            this.b = sVar;
            if (z) {
                j jVar = new j(DownloadDispatcher.this.mConfig.cacheDirectory(), DownloadDispatcher.this.mConfig.useFileStreamWriteFile());
                this.c = jVar;
                Iterator it = jVar.a().iterator();
                while (it.hasNext()) {
                    this.f350a.add(new m(DownloadDispatcher.this.mDnsResolver, DownloadDispatcher.this.mExecutor, DownloadDispatcher.this.mConfig, (g) it.next(), DownloadDispatcher.this.mTaskCallback));
                }
            }
        }

        private void e(DownloadInfo downloadInfo) {
            g gVar = new g(downloadInfo);
            j jVar = this.c;
            if (jVar != null) {
                jVar.a(gVar);
            }
            this.f350a.add(new m(DownloadDispatcher.this.mDnsResolver, DownloadDispatcher.this.mExecutor, DownloadDispatcher.this.mConfig, gVar, DownloadDispatcher.this.mTaskCallback));
            s sVar = this.b;
            if (sVar != null) {
                sVar.a();
            }
        }

        public final m a(int i) {
            return (m) this.f350a.get(i);
        }

        public final List a() {
            return this.f350a;
        }

        public final void a(DownloadInfo downloadInfo) {
            m mVarD = d(downloadInfo);
            if (mVarD != null) {
                if (!DownloadDispatcher.this.mConfig.isOverwriteExistTask()) {
                    if (DownloadDispatcher.this.mCallback != null) {
                        DownloadDispatcher.this.mCallback.onDownloadFailed(mVarD.d(), -10, "1");
                        return;
                    }
                    return;
                }
                DownloadState downloadStateB = mVarD.c().b();
                if (downloadStateB != DownloadState.DownloadFailed && downloadStateB != DownloadState.Downloaded && downloadStateB != DownloadState.VerifyFailed && downloadStateB != DownloadState.Cancelled) {
                    if (DownloadDispatcher.this.mCallback != null) {
                        DownloadDispatcher.this.mCallback.onDownloadFailed(mVarD.d(), -10, "0");
                        return;
                    }
                    return;
                } else {
                    j jVar = this.c;
                    if (jVar != null) {
                        jVar.b(mVarD.c());
                    }
                    this.f350a.remove(mVarD);
                }
            }
            e(downloadInfo);
        }

        public final int b() {
            return this.f350a.size();
        }

        public final void b(DownloadInfo downloadInfo) {
            m mVarD = d(downloadInfo);
            if (mVarD != null) {
                j jVar = this.c;
                if (jVar != null) {
                    jVar.b(mVarD.c());
                }
                this.f350a.remove(mVarD);
                s sVar = this.b;
                if (sVar != null) {
                    sVar.a(mVarD);
                }
            }
        }

        public final void c(DownloadInfo downloadInfo) {
            j jVar;
            m mVarD = d(downloadInfo);
            if (mVarD == null || (jVar = this.c) == null) {
                return;
            }
            jVar.c(mVarD.c());
        }

        public final m d(DownloadInfo downloadInfo) {
            for (int i = 0; i < this.f350a.size(); i++) {
                String string = ((m) this.f350a.get(i)).c() != null ? ((m) this.f350a.get(i)).c().toString() : "";
                String string2 = downloadInfo.toString();
                if (string != null && string.equals(string2)) {
                    return (m) this.f350a.get(i);
                }
            }
            return null;
        }
    }

    public DownloadDispatcher(DownloadConfig downloadConfig, IDownloadCallback iDownloadCallback) {
        this.mCallback = iDownloadCallback;
        this.mConfig = downloadConfig;
        this.mRunningTaskPool = new p(downloadConfig.getMaxTask());
        this.mExecutor = downloadConfig.executor() == null ? new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 10L, TimeUnit.MILLISECONDS, new SynchronousQueue(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy()) : downloadConfig.executor();
        this.mDnsResolver = new DnsResolver(this.mExecutor, downloadConfig.getCacheExpiredTime(), downloadConfig.dnsSelectStrategy(), downloadConfig.logger());
        for (String str : downloadConfig.dnsResolver().keySet()) {
            this.mDnsResolver.addHttpCloud(str, new ArrayList((Set) this.mConfig.dnsResolver().get(str)));
        }
        a aVar = new a(this.mConfig.isSupportBreakpointResume(), new e(this));
        this.mTaskQueue = aVar;
        if (aVar.b() > 0) {
            doNextTask();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doNextTask() {
        synchronized (this.mRunningTaskPool) {
            if (this.mRunningTaskPool.b() > this.mRunningTaskPool.a()) {
                int iB = this.mTaskQueue.b();
                for (int i = 0; i < iB; i++) {
                    m mVarA = this.mTaskQueue.a(i);
                    if (mVarA.c().b() == DownloadState.NotStart && this.mRunningTaskPool.a(mVarA)) {
                        mVarA.a();
                    }
                }
            }
        }
    }

    public void addTask(DownloadRequest downloadRequest) {
        synchronized (this) {
            this.mTaskQueue.a(downloadRequest);
        }
    }

    public List allTasks() {
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator it = this.mTaskQueue.a().iterator();
            while (it.hasNext()) {
                arrayList.add(((m) it.next()).d());
            }
        }
        return arrayList;
    }

    public boolean existTask(DownloadRequest downloadRequest) {
        List listAllTasks = allTasks();
        synchronized (this) {
            Iterator it = listAllTasks.iterator();
            while (it.hasNext()) {
                if (((DownloadResponse) it.next()).equals(downloadRequest)) {
                    return true;
                }
            }
            return false;
        }
    }

    public String getSDKVersion() {
        return DOWNLOAD_SDK_VERSION;
    }

    public void pauseTask(DownloadRequest downloadRequest) {
        synchronized (this) {
            m mVarD = this.mTaskQueue.d(downloadRequest);
            if (mVarD != null) {
                mVarD.b();
            }
        }
    }

    public void removeTask(DownloadRequest downloadRequest) {
        synchronized (this) {
            this.mTaskQueue.b(downloadRequest);
        }
    }

    public void resumeTask(DownloadRequest downloadRequest) {
        synchronized (this) {
            m mVarD = this.mTaskQueue.d(downloadRequest);
            if (mVarD != null) {
                g gVarC = mVarD.c();
                if (gVarC.b() == DownloadState.Cancelled || gVarC.b() == DownloadState.DownloadFailed || gVarC.b() == DownloadState.VerifyFailed) {
                    mVarD.c().a(DownloadState.NotStart);
                    this.mTaskCallback.e(mVarD);
                    doNextTask();
                }
            }
        }
    }
}
