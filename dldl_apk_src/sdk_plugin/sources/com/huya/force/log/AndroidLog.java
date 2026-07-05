package com.huya.force.log;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class AndroidLog implements ILog {
    AndroidLog() {
    }

    @Override // com.huya.force.log.ILog
    public void info(String str, String str2) {
        Log.i(str, str2);
    }

    @Override // com.huya.force.log.ILog
    public void error(String str, String str2) {
        Log.e(str, str2);
    }

    @Override // com.huya.force.log.ILog
    public void debug(String str, String str2) {
        Log.d(str, str2);
    }

    @Override // com.huya.force.log.ILog
    public void warn(String str, String str2) {
        Log.w(str, str2);
    }
}
