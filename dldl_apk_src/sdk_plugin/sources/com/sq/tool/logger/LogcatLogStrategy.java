package com.sq.tool.logger;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LogcatLogStrategy implements LogStrategy {
    static final String DEFAULT_TAG = "NO_TAG";

    @Override // com.sq.tool.logger.LogStrategy
    public void log(int priority, String tag, String message) {
        Utils.checkNotNull(message);
        if (tag == null) {
            tag = DEFAULT_TAG;
        }
        Log.println(priority, tag, message);
    }
}
