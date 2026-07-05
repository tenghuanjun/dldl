package com.sq.tool.logger;

import android.os.HandlerThread;
import com.sq.tool.logger.DiskLogStrategy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TxtFormatStrategy implements FormatStrategy {
    private static final String SEPARATOR = " ";
    private final LogStrategy logStrategy;
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
    private static final String NEW_LINE = System.lineSeparator();

    private TxtFormatStrategy(Builder builder) {
        Utils.checkNotNull(builder);
        this.logStrategy = builder.logStrategy;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.sq.tool.logger.FormatStrategy
    public void log(int priority, String tag, String message) {
        Utils.checkNotNull(message);
        this.logStrategy.log(priority, tag, SIMPLE_DATE_FORMAT.format(new Date()) + SEPARATOR + Utils.logLevel(priority) + SEPARATOR + tag + SEPARATOR + message + NEW_LINE);
    }

    public static final class Builder {
        private static final int MAX_BYTES = 512000;
        LogStrategy logStrategy;
        String saveFilePath;

        private Builder() {
        }

        public Builder logStrategy(LogStrategy val) {
            this.logStrategy = val;
            return this;
        }

        public Builder saveFilePath(String saveFilePath) {
            this.saveFilePath = saveFilePath;
            return this;
        }

        public TxtFormatStrategy build() {
            if (this.saveFilePath == null) {
                throw new IllegalArgumentException("The log saving path cannot be empty");
            }
            if (this.logStrategy == null) {
                HandlerThread handlerThread = new HandlerThread("AndroidFileLogger." + this.saveFilePath);
                handlerThread.start();
                this.logStrategy = new DiskLogStrategy(new DiskLogStrategy.WriteHandler(handlerThread.getLooper(), this.saveFilePath, MAX_BYTES));
            }
            return new TxtFormatStrategy(this);
        }
    }
}
