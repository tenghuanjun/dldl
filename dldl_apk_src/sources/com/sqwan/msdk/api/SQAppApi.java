package com.sqwan.msdk.api;

import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface SQAppApi {
    void attachBaseContext(Context context);

    Context getApplicationContext();

    void insertAppContext(Application application);

    void onCreate();

    void setMediaReporter(SQMediaReportInterface sQMediaReportInterface);

    void setReporter(SQReportInterface sQReportInterface);
}
