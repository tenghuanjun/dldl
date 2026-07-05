package com.sq.tools.report.event;

import android.content.Context;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface IEventReporter {

    public interface DynamicSuperPropertiesTracker {
        Map<String, Object> getDynamicSuperProperties();
    }

    void flush();

    void init(Context context);

    void report(String event, Map<String, Object> properties);

    void setAccountId(String uid);

    void setDynamicSuperPropertiesTracker(DynamicSuperPropertiesTracker tracker);

    void setSuperProperties(Map<String, Object> properties);

    void setUserConsent(Map<String, Boolean> consent);
}
