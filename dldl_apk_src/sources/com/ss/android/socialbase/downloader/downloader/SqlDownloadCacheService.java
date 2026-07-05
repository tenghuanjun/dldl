package com.ss.android.socialbase.downloader.downloader;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.ss.android.socialbase.downloader.impls.DefaultDownloadCache;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class SqlDownloadCacheService extends Service {
    private static final String TAG = SqlDownloadCacheService.class.getSimpleName();

    public static void startServiceAndBind(Context context, ServiceConnection serviceConnection) {
        if (context != null) {
            try {
                Intent intent = new Intent(context, (Class<?>) SqlDownloadCacheService.class);
                if (serviceConnection != null) {
                    context.bindService(intent, serviceConnection, 1);
                }
                context.startService(intent);
            } catch (Throwable th) {
                Log.w(TAG, "startServiceAndBind fail", th);
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        DownloadComponentManager.setAppContext(getApplicationContext());
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        ISqlDownloadCache sqlDownloadCache;
        IDownloadCache downloadCache = DownloadComponentManager.getDownloadCache();
        if (downloadCache instanceof DefaultDownloadCache) {
            sqlDownloadCache = ((DefaultDownloadCache) downloadCache).getSqlDownloadCache();
        } else {
            sqlDownloadCache = downloadCache instanceof ISqlDownloadCache ? (ISqlDownloadCache) downloadCache : null;
        }
        if (sqlDownloadCache instanceof IBinder) {
            return (IBinder) sqlDownloadCache;
        }
        return new Binder();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int iOnStartCommand = super.onStartCommand(intent, i, i2);
        if (DownloadComponentManager.notAutoRebootService()) {
            return 2;
        }
        return iOnStartCommand;
    }
}
