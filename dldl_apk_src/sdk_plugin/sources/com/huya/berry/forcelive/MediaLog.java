package com.huya.berry.forcelive;

import com.duowan.auk.util.L;
import com.huya.force.log.ILog;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MediaLog implements ILog {
    @Override // com.huya.force.log.ILog
    public void info(String str, String str2) {
        L.info(str, str2);
    }

    @Override // com.huya.force.log.ILog
    public void error(String str, String str2) {
        L.error(str, str2);
    }

    @Override // com.huya.force.log.ILog
    public void debug(String str, String str2) {
        L.debug(str, str2);
    }

    @Override // com.huya.force.log.ILog
    public void warn(String str, String str2) {
        L.warn(str, str2);
    }
}
