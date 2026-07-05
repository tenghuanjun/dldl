package com.sq.diagnostic.assistant.other;

import android.content.Context;
import com.sq.diagnostic.assistant.log.ExtraPathConfig;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LogConfig {
    private static final String FILE_SQ_LOG_FOLDER_NAME = "sqLog";
    private static final String FILE_ZIP_FOLDER_NAME = "zipLog";
    public List<ExtraPathConfig> extraPathConfigs;
    public String logSavePath;
    public String zipLogSavePath;
    public boolean logPrintEnable = true;
    public int logMaxCacheDays = 7;

    public LogConfig(Context context) {
        this.logSavePath = getDefaultSaveDirectory(context, FILE_SQ_LOG_FOLDER_NAME).getPath();
        this.zipLogSavePath = getDefaultSaveDirectory(context, FILE_ZIP_FOLDER_NAME).getPath();
    }

    private File getDefaultSaveDirectory(Context context, String str) {
        File externalFilesDir = context.getExternalFilesDir(str);
        if (externalFilesDir == null) {
            externalFilesDir = new File(context.getFilesDir(), str);
        }
        if (externalFilesDir.exists() && !externalFilesDir.isDirectory()) {
            externalFilesDir.delete();
        }
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        return externalFilesDir;
    }
}
