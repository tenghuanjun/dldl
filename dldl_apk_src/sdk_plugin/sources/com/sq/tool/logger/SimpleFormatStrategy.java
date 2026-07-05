package com.sq.tool.logger;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SimpleFormatStrategy implements FormatStrategy {
    static final String DEFAULT_TAG = "NO_TAG";
    private final String mTag;

    public SimpleFormatStrategy() {
        this(DEFAULT_TAG);
    }

    public SimpleFormatStrategy(String tag) {
        this.mTag = (tag == null || tag.isEmpty()) ? DEFAULT_TAG : tag;
    }

    @Override // com.sq.tool.logger.FormatStrategy
    public void log(int priority, String tag, String message) {
        Utils.checkNotNull(message);
        if (tag == null || tag.isEmpty()) {
            tag = this.mTag;
        }
        Log.println(priority, tag, message);
    }
}
