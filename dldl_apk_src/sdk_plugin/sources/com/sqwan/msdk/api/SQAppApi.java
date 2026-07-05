package com.sqwan.msdk.api;

import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public interface SQAppApi {
    void attachBaseContext(Context context);

    Context getApplicationContext();

    void insertAppContext(Application application);

    void onCreate();

    void setMediaReporter(SQMediaReportInterface sQMediaReportInterface);

    void setReporter(SQReportInterface sQReportInterface);
}
