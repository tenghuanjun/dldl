package com.huya.berry.sdklive.liveTool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.util.L;
import com.huya.berry.sdklive.event.FloatWinInterface;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FloatWindowHelper {
    private static final String TAG = "FloatWindowHelper";
    private static long mLiveRx;
    private static boolean mLiveToolServiceStarted;
    private static long mLiveTx;
    private static ServiceConnection protoconn = new ServiceConnection() { // from class: com.huya.berry.sdklive.liveTool.FloatWindowHelper.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            boolean unused = FloatWindowHelper.mLiveToolServiceStarted = false;
            L.error(FloatWindowHelper.TAG, "stopMonitor->onServiceDisconnected:");
        }
    };

    public static boolean isLiveToolSerrviceStarted() {
        return mLiveToolServiceStarted;
    }

    public static void startLiveToolService(Context context, Bundle bundle) {
        if (mLiveToolServiceStarted) {
            L.info(TAG, "tool service has already started.");
            return;
        }
        intLiveTxRx();
        Intent intent = new Intent(context, (Class<?>) LiveToolService.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        mLiveToolServiceStarted = context.bindService(intent, protoconn, 1);
        L.info(TAG, "startLiveToolService..." + mLiveToolServiceStarted);
    }

    public static void stopLiveToolService(Context context) {
        L.info(TAG, "stopLiveToolService...%s", Boolean.valueOf(mLiveToolServiceStarted));
        if (mLiveToolServiceStarted) {
            mLiveToolServiceStarted = false;
            stopLiveTxRx();
            context.unbindService(protoconn);
        }
    }

    private static void intLiveTxRx() {
        int iMyUid = Process.myUid();
        mLiveTx = TrafficStats.getUidTxBytes(iMyUid);
        mLiveRx = TrafficStats.getUidRxBytes(iMyUid);
    }

    public static void stopLiveTxRx() {
        L.info(TAG, "this live traffic: UID:%d tx:%fMb rx:%fMb", Integer.valueOf(Process.myUid()), Double.valueOf(((TrafficStats.getUidTxBytes(r0) - mLiveTx) / 1024.0d) / 1024.0d), Double.valueOf(((TrafficStats.getUidRxBytes(r0) - mLiveRx) / 1024.0d) / 1024.0d));
    }

    public static void onStartLive() {
        ArkUtils.send(new FloatWinInterface.OnStartLive());
    }

    public static void onEndLive() {
        ArkUtils.send(new FloatWinInterface.OnEndLive());
    }
}
