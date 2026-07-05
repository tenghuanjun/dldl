package com.sqwan.common.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class TimeTickMonitor {
    private Context mContext;
    private OnTimeTickListener mTimeTickListener;
    BroadcastReceiver mTimeTickReceiver;
    private int mCurrentTimeTick = 1;
    private int mCallBackFrequency = 1;

    public interface OnTimeTickListener {
        void onTimeTick();
    }

    static /* synthetic */ int access$108(TimeTickMonitor timeTickMonitor) {
        int i = timeTickMonitor.mCurrentTimeTick;
        timeTickMonitor.mCurrentTimeTick = i + 1;
        return i;
    }

    public TimeTickMonitor(Context context, OnTimeTickListener onTimeTickListener) {
        this.mContext = context.getApplicationContext();
        this.mTimeTickListener = onTimeTickListener;
    }

    public void register() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.sqwan.common.util.TimeTickMonitor.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (TimeTickMonitor.this.mTimeTickListener != null) {
                    if (TimeTickMonitor.this.mCurrentTimeTick >= TimeTickMonitor.this.mCallBackFrequency) {
                        TimeTickMonitor.this.mTimeTickListener.onTimeTick();
                        TimeTickMonitor.this.mCurrentTimeTick = 1;
                    } else {
                        TimeTickMonitor.access$108(TimeTickMonitor.this);
                    }
                }
            }
        };
        this.mTimeTickReceiver = broadcastReceiver;
        this.mContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    public void setTimeTickFrequency(int i) {
        this.mCallBackFrequency = i;
    }

    public void unregister() {
        BroadcastReceiver broadcastReceiver = this.mTimeTickReceiver;
        if (broadcastReceiver != null) {
            this.mContext.unregisterReceiver(broadcastReceiver);
            this.mTimeTickReceiver = null;
        }
    }
}
