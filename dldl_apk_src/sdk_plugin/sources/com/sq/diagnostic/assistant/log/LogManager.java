package com.sq.diagnostic.assistant.log;

import android.content.Context;
import com.sq.diagnostic.assistant.http.entity.UploadLogRequest;
import com.sq.diagnostic.assistant.log.impl.LogManagerImpl;
import com.sq.diagnostic.assistant.log.utils.ExtraUtil;
import com.sq.diagnostic.assistant.other.LogConfig;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LogManager {
    private static volatile LogManager sInstance;
    private LogManagerImpl mLogManagerImpl;

    public static LogManager getInstance() {
        if (sInstance == null) {
            synchronized (LogManager.class) {
                if (sInstance == null) {
                    sInstance = new LogManager();
                }
            }
        }
        return sInstance;
    }

    private LogManager() {
    }

    public boolean init(Context context, LogConfig logConfig) {
        if (context == null) {
            return false;
        }
        try {
            LogManagerImpl logManagerImpl = new LogManagerImpl();
            this.mLogManagerImpl = logManagerImpl;
            logManagerImpl.initConfig(context, logConfig);
            this.mLogManagerImpl.initLog();
            this.mLogManagerImpl.initCustomCrash();
            if (!ExtraUtil.isMainProcess(context)) {
                return true;
            }
            this.mLogManagerImpl.clearExpireFiles();
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public boolean uploadLog(UploadLogRequest uploadLogRequest, ILogCallBack<Void> iLogCallBack) {
        LogManagerImpl logManagerImpl = this.mLogManagerImpl;
        if (logManagerImpl == null) {
            return false;
        }
        logManagerImpl.uploadLog(uploadLogRequest, iLogCallBack);
        return true;
    }
}
