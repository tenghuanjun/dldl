package com.ss.android.socialbase.appdownloader;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.downloader.IReserveWifiStatusListener;
import com.ss.android.socialbase.downloader.impls.RetryScheduler;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class RetryJobSchedulerService extends JobService {
    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        DownloadComponentManager.setAppContext(this);
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        if (jobParameters == null) {
            return false;
        }
        int jobId = jobParameters.getJobId();
        Logger.i("RetrySchedulerService", "onStartJob, id = " + jobId);
        RetryScheduler.getInstance().doSchedulerRetry(jobId);
        return false;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int iOnStartCommand = super.onStartCommand(intent, i, i2);
        if (DownloadComponentManager.notAutoRebootService()) {
            return 2;
        }
        return iOnStartCommand;
    }

    static void a(DownloadInfo downloadInfo, long j, boolean z, int i) {
        Context appContext;
        long j2;
        IReserveWifiStatusListener reserveWifiStatusListener;
        IReserveWifiStatusListener reserveWifiStatusListener2;
        if (downloadInfo == null || j <= 0 || (appContext = DownloadComponentManager.getAppContext()) == null) {
            return;
        }
        int i2 = 2;
        if (downloadInfo.isPauseReserveOnWifi() && (reserveWifiStatusListener2 = Downloader.getInstance(DownloadComponentManager.getAppContext()).getReserveWifiStatusListener()) != null) {
            reserveWifiStatusListener2.onStatusChanged(downloadInfo, 2, 3);
        }
        try {
            JobScheduler jobScheduler = (JobScheduler) appContext.getSystemService("jobscheduler");
            if (jobScheduler == null) {
                return;
            }
            try {
                jobScheduler.cancel(downloadInfo.getId());
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (i == 0 || (z && i != 2)) {
                j = 1000;
                j2 = 0;
            } else {
                j2 = 60000 + j;
            }
            JobInfo.Builder minimumLatency = new JobInfo.Builder(downloadInfo.getId(), new ComponentName(appContext.getPackageName(), RetryJobSchedulerService.class.getName())).setMinimumLatency(j);
            if (!z) {
                i2 = 1;
            }
            JobInfo.Builder requiresDeviceIdle = minimumLatency.setRequiredNetworkType(i2).setRequiresCharging(false).setRequiresDeviceIdle(false);
            if (j2 > 0) {
                requiresDeviceIdle.setOverrideDeadline(j2);
            }
            int iSchedule = jobScheduler.schedule(requiresDeviceIdle.build());
            if (iSchedule > 0 && downloadInfo.isPauseReserveOnWifi() && (reserveWifiStatusListener = Downloader.getInstance(DownloadComponentManager.getAppContext()).getReserveWifiStatusListener()) != null) {
                reserveWifiStatusListener.onStatusChanged(downloadInfo, 3, 3);
            }
            if (iSchedule <= 0) {
                Logger.w("RetrySchedulerService", "schedule err errCode = " + iSchedule);
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    static void a(int i) {
        Context appContext = DownloadComponentManager.getAppContext();
        if (appContext == null) {
            return;
        }
        try {
            JobScheduler jobScheduler = (JobScheduler) appContext.getSystemService("jobscheduler");
            if (jobScheduler == null) {
                return;
            }
            jobScheduler.cancel(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
