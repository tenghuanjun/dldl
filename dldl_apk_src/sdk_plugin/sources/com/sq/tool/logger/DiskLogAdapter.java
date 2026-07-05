package com.sq.tool.logger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DiskLogAdapter implements LogAdapter {
    private final FormatStrategy formatStrategy;

    @Override // com.sq.tool.logger.LogAdapter
    public boolean isLoggable(int priority, String tag) {
        return true;
    }

    public DiskLogAdapter(String logSaveFilePath) {
        this.formatStrategy = TxtFormatStrategy.newBuilder().saveFilePath(logSaveFilePath).build();
    }

    public DiskLogAdapter(FormatStrategy formatStrategy) {
        this.formatStrategy = (FormatStrategy) Utils.checkNotNull(formatStrategy);
    }

    @Override // com.sq.tool.logger.LogAdapter
    public void log(int priority, String tag, String message) {
        this.formatStrategy.log(priority, tag, message);
    }
}
