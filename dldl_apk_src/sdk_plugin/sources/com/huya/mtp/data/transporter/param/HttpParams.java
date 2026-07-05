package com.huya.mtp.data.transporter.param;

import com.huya.mtp.http.Request;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface HttpParams extends Params {
    int getBackoffMultiplier();

    byte[] getBody();

    String getBodyContentType();

    String getCacheKey();

    String getCgi();

    Map<String, String> getHeaders();

    int getMaxRetryTimes();

    int getMethod();

    Map<String, String> getParams();

    Request.Priority getPriority();

    String getReportId();

    int getTimeout();

    int getTimeoutIncrement();

    String getUrl();

    void updateCurrentRetryTimes(int i);
}
