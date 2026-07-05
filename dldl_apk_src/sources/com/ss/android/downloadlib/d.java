package com.ss.android.downloadlib;

import android.content.SharedPreferences;
import android.util.SparseArray;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.downloader.IDownloadCache;
import com.ss.android.socialbase.downloader.impls.DefaultDownloadCache;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.thread.DefaultThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class d {
    private ExecutorService a;
    private ExecutorService b;
    private ScheduledExecutorService c;
    private ExecutorService d;

    private static class a {
        private static d a = new d();
    }

    public static d a() {
        return a.a;
    }

    private d() {
    }

    public void a(Runnable runnable) {
        a(runnable, false);
    }

    public void b(Runnable runnable) {
        b(runnable, false);
    }

    public void a(Runnable runnable, boolean z) {
        if (runnable == null) {
            return;
        }
        if (z && !m.b()) {
            runnable.run();
        } else {
            b().execute(runnable);
        }
    }

    public void c(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        e().execute(runnable);
    }

    public void b(Runnable runnable, boolean z) {
        if (runnable == null) {
            return;
        }
        if (z && !m.b()) {
            runnable.run();
        } else {
            c().execute(runnable);
        }
    }

    public void a(Runnable runnable, long j) {
        try {
            d().schedule(runnable, j, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public ExecutorService b() {
        if (this.a == null) {
            synchronized (d.class) {
                if (this.a == null) {
                    this.a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 30L, TimeUnit.SECONDS, new SynchronousQueue(), new DefaultThreadFactory(i.class.getName() + "-CPUThreadPool"));
                }
            }
        }
        return this.a;
    }

    public ExecutorService c() {
        if (this.b == null) {
            synchronized (d.class) {
                if (this.b == null) {
                    this.b = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 30L, TimeUnit.SECONDS, new SynchronousQueue(), new DefaultThreadFactory(i.class.getName() + "-IOThreadPool"));
                }
            }
        }
        return this.b;
    }

    public ScheduledExecutorService d() {
        if (this.c == null) {
            synchronized (d.class) {
                if (this.c == null) {
                    this.c = new ScheduledThreadPoolExecutor(0, new DefaultThreadFactory(i.class.getName() + "-ScheduledThreadPool"));
                }
            }
        }
        return this.c;
    }

    public ExecutorService e() {
        if (this.d == null) {
            synchronized (d.class) {
                if (this.d == null) {
                    this.d = new ThreadPoolExecutor(5, Integer.MAX_VALUE, 30L, TimeUnit.SECONDS, new SynchronousQueue(), new DefaultThreadFactory(i.class.getName() + "-InstallFinishCheckCPUThreadPool"));
                }
            }
        }
        return this.d;
    }

    public void f() {
        a(new Runnable() { // from class: com.ss.android.downloadlib.d.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (d.class) {
                    try {
                        String[] strArr = {"sp_ad_download_event", "sp_download_finish_cache", "sp_delay_operation_info", "sp_ttdownloader_md5", "sp_name_installed_app", "misc_config", "sp_ad_install_back_dialog", "sp_ttdownloader_clean", "sp_order_download", "sp_a_b_c", DownloadConstants.SP_ANTI_HIJACK_CONFIG, DownloadConstants.SP_DOWNLOAD_INFO, "sp_appdownloader"};
                        for (int i = 0; i < 13; i++) {
                            SharedPreferences sharedPreferences = k.a().getSharedPreferences(strArr[i], 0);
                            if (sharedPreferences != null) {
                                sharedPreferences.edit().clear().apply();
                            }
                        }
                        IDownloadCache downloadCache = DownloadComponentManager.getDownloadCache();
                        if (!(downloadCache instanceof DefaultDownloadCache)) {
                            return;
                        }
                        SparseArray<DownloadInfo> downloadInfoMap = ((DefaultDownloadCache) downloadCache).getDownloadCache().getDownloadInfoMap();
                        for (int size = downloadInfoMap.size() - 1; size >= 0; size--) {
                            DownloadInfo downloadInfo = downloadInfoMap.get(downloadInfoMap.keyAt(size));
                            if (downloadInfo != null) {
                                Downloader.getInstance(k.a()).clearDownloadData(downloadInfo.getId());
                            }
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
        });
    }
}
