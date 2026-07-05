package com.sq.diagnostic.assistant.log.impl;

import android.content.Context;
import com.sq.diagnostic.assistant.DiagnosticAssistant;
import com.sq.diagnostic.assistant.http.entity.UploadLogRequest;
import com.sq.diagnostic.assistant.log.ExtraPathConfig;
import com.sq.diagnostic.assistant.log.ILogCallBack;
import com.sq.diagnostic.assistant.log.SQLogUtils;
import com.sq.diagnostic.assistant.log.crash.CrashHelper;
import com.sq.diagnostic.assistant.log.strategy.DeleteLogZipStrategy;
import com.sq.diagnostic.assistant.log.strategy.PackageUploadLogStrategy;
import com.sq.diagnostic.assistant.log.utils.ExtraUtil;
import com.sq.diagnostic.assistant.other.LogConfig;
import com.sq.tool.logger.DiskLogAdapter;
import com.sq.tool.logger.SQLog;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LogManagerImpl {
    private static final String TAG = "LogManagerImpl";
    private String mCachePath;
    private LogConfig mLogConfig;

    public void initConfig(Context context, LogConfig logConfig) {
        this.mCachePath = new File(context.getFilesDir(), "log").toString();
        this.mLogConfig = logConfig;
    }

    public void initLog() {
        SQLog.addLogAdapter(new DiskLogAdapter(this.mLogConfig.logSavePath) { // from class: com.sq.diagnostic.assistant.log.impl.LogManagerImpl.1
            @Override // com.sq.tool.logger.DiskLogAdapter, com.sq.tool.logger.LogAdapter
            public boolean isLoggable(int i, String str) {
                return DiagnosticAssistant.getInstance().isInitSuccess();
            }
        });
    }

    public void initCustomCrash() {
        CrashHelper.getInstance().init();
    }

    public void clearExpireFiles() {
        List<ExtraPathConfig> list = this.mLogConfig.extraPathConfigs;
        ExtraUtil.clearExpiredFiles(this.mCachePath, this.mLogConfig.logMaxCacheDays);
        ExtraUtil.clearExpiredFiles(this.mLogConfig.logSavePath, this.mLogConfig.logMaxCacheDays);
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            ExtraPathConfig extraPathConfig = list.get(i);
            if (extraPathConfig != null) {
                ExtraUtil.clearExpiredFiles(extraPathConfig.path, extraPathConfig.cacheDays);
            }
        }
    }

    public void uploadLog(UploadLogRequest uploadLogRequest, ILogCallBack<?> iLogCallBack) {
        if (uploadLogRequest == null) {
            return;
        }
        performUpload(uploadLogRequest, iLogCallBack);
    }

    private void performUpload(UploadLogRequest uploadLogRequest, ILogCallBack<?> iLogCallBack) {
        if (!ExtraUtil.validDate(uploadLogRequest.logStartDate) || !ExtraUtil.validDate(uploadLogRequest.logEndDate)) {
            if (iLogCallBack != null) {
                iLogCallBack.onFailed(10001, ErrorStats.ERROR_MSG_LOG_INVALID_DATE);
            }
            SQLogUtils.w(TAG, "date format error");
        } else {
            DeleteLogZipStrategy deleteLogZipStrategy = new DeleteLogZipStrategy(uploadLogRequest.logStartDate, uploadLogRequest.logEndDate, this.mLogConfig.zipLogSavePath);
            deleteLogZipStrategy.setNextHandler(new PackageUploadLogStrategy(uploadLogRequest.logStartDate, uploadLogRequest.logEndDate, this.mLogConfig.zipLogSavePath, this.mLogConfig.logSavePath, this.mCachePath, getExtraPaths()));
            deleteLogZipStrategy.performProcess(uploadLogRequest, iLogCallBack);
        }
    }

    private List<String> getExtraPaths() {
        List<ExtraPathConfig> list = this.mLogConfig.extraPathConfigs;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            ExtraPathConfig extraPathConfig = list.get(i);
            if (extraPathConfig != null) {
                arrayList.add(extraPathConfig.path);
            }
        }
        return arrayList;
    }
}
