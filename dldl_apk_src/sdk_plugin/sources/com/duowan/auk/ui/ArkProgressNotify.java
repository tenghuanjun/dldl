package com.duowan.auk.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import com.duowan.auk.ArkValue;
import com.huya.component.base.framework.R;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ArkProgressNotify {
    private int mNotifyId;
    private long mCurValue = 0;
    private long mMaxValue = 0;
    private Notification.Builder mBuilder = null;
    private NotificationManager mManager = null;
    private HandlerThread mProgressThread = null;
    private Handler mProgressHandler = null;
    private Runnable mProgressRunnable = null;
    private AtomicBoolean mIsProgressed = new AtomicBoolean(true);

    public ArkProgressNotify(int i) {
        this.mNotifyId = 0;
        this.mNotifyId = i;
        init();
    }

    public void setRes(int i, int i2, int i3) {
        setRes(i, ArkValue.gContext.getString(i3), i3);
    }

    public void setRes(int i, String str, int i2) {
        if (-1 != i) {
            this.mBuilder.setSmallIcon(i);
        }
        this.mBuilder.setContentTitle(str);
        if (-1 != i2) {
            this.mBuilder.setContentText(ArkValue.gContext.getResources().getText(i2));
        }
        this.mBuilder.setLargeIcon(BitmapFactory.decodeResource(ArkValue.gContext.getResources(), R.drawable.icon_download));
        this.mManager.notify(this.mNotifyId, this.mBuilder.build());
    }

    public void setValue(long j, long j2) {
        this.mCurValue = j;
        this.mMaxValue = j2;
        if (this.mIsProgressed.get()) {
            this.mProgressHandler.postDelayed(this.mProgressRunnable, 300L);
            this.mIsProgressed.set(false);
        }
    }

    public void cancel() {
        Handler handler;
        Runnable runnable = this.mProgressRunnable;
        if (runnable != null && (handler = this.mProgressHandler) != null) {
            handler.removeCallbacks(runnable);
        }
        HandlerThread handlerThread = this.mProgressThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mProgressThread = null;
            this.mProgressHandler = null;
        }
        this.mManager.cancel(this.mNotifyId);
        this.mIsProgressed.set(true);
        this.mMaxValue = 0L;
        this.mCurValue = 0L;
    }

    public void start() {
        cancel();
        HandlerThread handlerThread = new HandlerThread("ProgressThread");
        this.mProgressThread = handlerThread;
        handlerThread.start();
        this.mProgressHandler = new Handler(this.mProgressThread.getLooper());
        this.mIsProgressed.set(true);
    }

    private void init() {
        this.mManager = (NotificationManager) ArkValue.gContext.getSystemService("notification");
        Notification.Builder builder = new Notification.Builder(ArkValue.gContext);
        this.mBuilder = builder;
        builder.setAutoCancel(false);
        this.mBuilder.setOngoing(true);
        HandlerThread handlerThread = new HandlerThread("ProgressThread");
        this.mProgressThread = handlerThread;
        handlerThread.start();
        this.mProgressHandler = new Handler(this.mProgressThread.getLooper());
        this.mProgressRunnable = new Runnable() { // from class: com.duowan.auk.ui.ArkProgressNotify.1
            @Override // java.lang.Runnable
            public void run() {
                ArkProgressNotify.this.mIsProgressed.set(true);
                ArkProgressNotify.this.updateNotify();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNotify() {
        if (this.mMaxValue <= 0) {
            return;
        }
        this.mBuilder.setProgress(100, (int) Math.ceil((this.mCurValue * 100.0f) / r0), false);
        this.mManager.notify(this.mNotifyId, this.mBuilder.build());
    }
}
