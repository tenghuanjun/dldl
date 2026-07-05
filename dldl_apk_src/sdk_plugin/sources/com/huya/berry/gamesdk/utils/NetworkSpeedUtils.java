package com.huya.berry.gamesdk.utils;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NetworkSpeedUtils {
    public static final int WHAT_NETWORK_SPEED = 100;
    private Context mContext;
    private Handler mHandler;
    private boolean mIsRunning;
    private boolean mIsPause = false;
    private long mLastTotalTxBytes = 0;
    private long mLastTimeStamp = 0;
    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() { // from class: com.huya.berry.gamesdk.utils.NetworkSpeedUtils.1
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (NetworkSpeedUtils.this.mIsPause) {
                return;
            }
            NetworkSpeedUtils.this.showNetSpeed();
        }
    };

    public NetworkSpeedUtils(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
    }

    public void startNetSpeed() {
        this.mLastTotalTxBytes = getTotalTxBytes();
        this.mLastTimeStamp = System.currentTimeMillis();
        this.timer.schedule(this.task, 1000L, 2000L);
        this.mIsRunning = true;
    }

    public void pauseNetSpeed() {
        this.mIsPause = true;
    }

    public void resumeNetSpeed() {
        this.mLastTotalTxBytes = getTotalTxBytes();
        this.mLastTimeStamp = System.currentTimeMillis();
        this.mIsPause = false;
    }

    public void stopNetSpeed() {
        if (this.mIsRunning) {
            this.timer.cancel();
            this.timer.purge();
            this.timer = null;
            this.mIsPause = true;
            this.mIsRunning = false;
        }
    }

    private long getTotalTxBytes() {
        if (TrafficStats.getUidTxBytes(this.mContext.getApplicationInfo().uid) == -1) {
            return 0L;
        }
        return TrafficStats.getTotalTxBytes() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNetSpeed() {
        long totalTxBytes = getTotalTxBytes();
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.mLastTimeStamp;
        if (jCurrentTimeMillis - j == 0) {
            return;
        }
        long j2 = ((totalTxBytes - this.mLastTotalTxBytes) * 1000) / (jCurrentTimeMillis - j);
        this.mLastTimeStamp = jCurrentTimeMillis;
        this.mLastTotalTxBytes = totalTxBytes;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf(j2));
        stringBuffer.append(" KB/s");
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.what = 100;
        messageObtainMessage.obj = stringBuffer.toString();
        this.mHandler.sendMessage(messageObtainMessage);
    }
}
