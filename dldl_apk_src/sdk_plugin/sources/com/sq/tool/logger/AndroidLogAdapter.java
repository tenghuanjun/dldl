package com.sq.tool.logger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AndroidLogAdapter implements LogAdapter {
    private final FormatStrategy formatStrategy;

    @Override // com.sq.tool.logger.LogAdapter
    public boolean isLoggable(int priority, String tag) {
        return true;
    }

    public AndroidLogAdapter() {
        this.formatStrategy = new SimpleFormatStrategy();
    }

    public AndroidLogAdapter(String tag) {
        this.formatStrategy = new SimpleFormatStrategy(tag);
    }

    public AndroidLogAdapter(FormatStrategy formatStrategy) {
        this.formatStrategy = (FormatStrategy) Utils.checkNotNull(formatStrategy);
    }

    @Override // com.sq.tool.logger.LogAdapter
    public void log(int priority, String tag, String message) {
        this.formatStrategy.log(priority, tag, message);
    }
}
