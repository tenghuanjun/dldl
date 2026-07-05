package com.huya.berry.sdkplayer.floats.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ScreenObserver {
    private static final String TAG = ScreenObserver.class.getSimpleName();
    private Context mContext;
    private boolean mRegistered = false;
    private ScreenBroadcastReceiver mScreenReceiver = new ScreenBroadcastReceiver();
    private ScreenStateListener mScreenStateListener;

    public interface ScreenStateListener {
        void onScreenOff();

        void onScreenOn();

        void onUserPresent();
    }

    public ScreenObserver(Context context) {
        this.mContext = context;
    }

    public void startObserver(ScreenStateListener screenStateListener) {
        this.mScreenStateListener = screenStateListener;
        registerListener();
        getScreenState();
    }

    public void shutdownObserver() {
        unregisterListener();
    }

    private void getScreenState() {
        Context context = this.mContext;
        if (context == null) {
            return;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager != null && powerManager.isScreenOn()) {
            ScreenStateListener screenStateListener = this.mScreenStateListener;
            if (screenStateListener != null) {
                screenStateListener.onScreenOn();
                return;
            }
            return;
        }
        ScreenStateListener screenStateListener2 = this.mScreenStateListener;
        if (screenStateListener2 != null) {
            screenStateListener2.onScreenOff();
        }
    }

    private void registerListener() {
        if (this.mContext != null) {
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                intentFilter.addAction("android.intent.action.USER_PRESENT");
                this.mContext.registerReceiver(this.mScreenReceiver, intentFilter);
                this.mRegistered = true;
            } catch (Exception unused) {
                L.error(TAG, "registerListener failed");
                this.mRegistered = false;
            }
        }
    }

    private void unregisterListener() {
        Context context = this.mContext;
        if (context == null || !this.mRegistered) {
            return;
        }
        try {
            context.unregisterReceiver(this.mScreenReceiver);
            this.mRegistered = false;
        } catch (Exception unused) {
            L.error(TAG, "unregisterListener failed");
        }
    }

    private class ScreenBroadcastReceiver extends BroadcastReceiver {
        private String action;

        private ScreenBroadcastReceiver() {
            this.action = null;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            this.action = action;
            if ("android.intent.action.SCREEN_ON".equals(action)) {
                ScreenObserver.this.mScreenStateListener.onScreenOn();
            } else if ("android.intent.action.SCREEN_OFF".equals(this.action)) {
                ScreenObserver.this.mScreenStateListener.onScreenOff();
            } else if ("android.intent.action.USER_PRESENT".equals(this.action)) {
                ScreenObserver.this.mScreenStateListener.onUserPresent();
            }
        }
    }
}
