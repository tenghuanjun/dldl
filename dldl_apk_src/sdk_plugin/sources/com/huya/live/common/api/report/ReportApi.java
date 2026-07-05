package com.huya.live.common.api.report;

import android.app.Activity;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ReportApi {
    void changeHuyaSessionId();

    void error(String str, String str2, String str3);

    void event(String str);

    void event(String str, String str2);

    void event(String str, String str2, String str3);

    void event(String str, String str2, String str3, String str4);

    void event(String str, String str2, String str3, String str4, Map<String, String> map);

    void event(String str, String str2, String str3, Map<String, String> map);

    void eventHuya(String str, String str2);

    void init(String str, String str2, String str3, String str4);

    void loginSuccess(long j);

    void pause(Activity activity);

    void reportGuid(String str);

    void resume(Activity activity);

    void startLive(long j, long j2, long j3, String str);

    void stopLive();

    void value(String str, int i);

    void value(String str, String str2, int i);
}
