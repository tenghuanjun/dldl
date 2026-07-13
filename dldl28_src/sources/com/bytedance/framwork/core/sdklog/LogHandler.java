package com.bytedance.framwork.core.sdklog;

import android.content.Context;
import android.text.TextUtils;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LogHandler {
    protected IConfig mConfig;
    private long mLastStopTime;
    private String mLastSuccessChannel;
    protected LogQueue mLogQueue;
    private IResponseConfig mResponseConfig;
    private long mStopMoreChannelInterval;
    protected String mType;

    public static abstract class BaseConfig implements IConfig {
        @Override // com.bytedance.framwork.core.sdklog.LogHandler.IConfig
        public long getLogExpireTime() {
            return 604800000L;
        }

        @Override // com.bytedance.framwork.core.sdklog.LogHandler.IConfig
        public int getMaxRetryCount() {
            return 4;
        }

        @Override // com.bytedance.framwork.core.sdklog.LogHandler.IConfig
        public long getRetryInterval() {
            return 15000L;
        }
    }

    public interface IConfig {
        List<String> getChannels();

        long getLogExpireTime();

        String getLogType();

        int getMaxRetryCount();

        long getRetryInterval();
    }

    public interface IResponseConfig {
        boolean getMoreChannelSwitch();

        boolean getRemoveSwitch();

        int getStatusCode();

        long getStopInterval();

        long getStopMoreChannelInterval();
    }

    public LogHandler(Context context, IConfig iConfig) {
        this.mConfig = iConfig;
        if (iConfig == null) {
            throw new IllegalArgumentException("config is null.");
        }
        String logType = iConfig.getLogType();
        this.mType = logType;
        if (TextUtils.isEmpty(logType)) {
            throw new IllegalArgumentException("type is empty.");
        }
        LogQueue logQueue = LogQueue.getInstance(context);
        this.mLogQueue = logQueue;
        logQueue.registerLogHandler(this.mType, this);
    }

    public LogHandler(Context context, IConfig iConfig, IResponseConfig iResponseConfig) {
        this.mConfig = iConfig;
        this.mResponseConfig = iResponseConfig;
        if (iConfig == null) {
            throw new IllegalArgumentException("config is null.");
        }
        if (iResponseConfig == null) {
            throw new IllegalArgumentException("responseConfig is null");
        }
        String logType = iConfig.getLogType();
        this.mType = logType;
        if (TextUtils.isEmpty(logType)) {
            throw new IllegalArgumentException("type is empty.");
        }
        LogQueue logQueue = LogQueue.getInstance(context);
        this.mLogQueue = logQueue;
        logQueue.registerLogHandler(this.mType, this);
    }

    public boolean enqueue(String str) {
        return enqueue(LogLib.safeGetBytes(str));
    }

    public boolean enqueue(byte[] bArr) {
        return this.mLogQueue.enqueue(this.mType, bArr);
    }

    IConfig getConfig() {
        return this.mConfig;
    }

    public long getLastStopTime() {
        return this.mLastStopTime;
    }

    String getLastSuccessChannel() {
        return this.mLastSuccessChannel;
    }

    IResponseConfig getResponseConfig() {
        return this.mResponseConfig;
    }

    public long getStopMoreChannelInterval() {
        return this.mStopMoreChannelInterval;
    }

    String getType() {
        return this.mType;
    }

    protected void onLogSent(byte[] bArr, boolean z) {
    }

    protected abstract boolean send(String str, byte[] bArr);

    public void setLastStopTime(long j) {
        this.mLastStopTime = j;
    }

    void setLastSuccessChannel(String str) {
        this.mLastSuccessChannel = str;
    }

    public void setStopMoreChannelInterval(long j) {
        this.mStopMoreChannelInterval = j;
    }

    public void updateConfig(IConfig iConfig) {
        if (iConfig == null) {
            return;
        }
        this.mConfig = iConfig;
    }
}
