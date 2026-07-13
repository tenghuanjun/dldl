package com.bytedance.framwork.core.sdklib.net;

import android.text.TextUtils;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class MonitorLogSender {
    private static ConcurrentHashMap<String, ISendLog> mapAidSendLog = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ILogSendImpl> mapAidLogSendImpl = new ConcurrentHashMap<>();

    public static ISendLog getISendLog(String str) {
        return mapAidSendLog.get(str);
    }

    public static boolean send(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        ILogSendImpl iLogSendImpl = mapAidLogSendImpl.get(str);
        if (iLogSendImpl.logStopCollectSwitch()) {
            return false;
        }
        return iLogSendImpl.send(str2);
    }

    public static void setISendLog(String str, ISendLog iSendLog) {
        mapAidSendLog.put(str, iSendLog);
    }

    public static void setImpl(String str, ILogSendImpl iLogSendImpl) {
        mapAidLogSendImpl.put(str, iLogSendImpl);
    }
}
