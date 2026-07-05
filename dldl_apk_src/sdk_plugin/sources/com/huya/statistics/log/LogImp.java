package com.huya.statistics.log;

import android.util.Log;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LogImp implements IL {
    @Override // com.huya.statistics.log.IL
    public void verbose(Object obj, String str, Object... objArr) {
        Log.v(obj.toString(), str);
    }

    @Override // com.huya.statistics.log.IL
    public void debug(Object obj, String str, Object... objArr) {
        Log.d(obj.toString(), str);
    }

    @Override // com.huya.statistics.log.IL
    public void info(Object obj, String str, Object... objArr) {
        Log.i(obj.toString(), str);
    }

    @Override // com.huya.statistics.log.IL
    public void warn(Object obj, String str, Object... objArr) {
        Log.w(obj.toString(), str);
    }

    @Override // com.huya.statistics.log.IL
    public void error(Object obj, String str, Object... objArr) {
        Log.e(obj.toString(), str);
    }
}
