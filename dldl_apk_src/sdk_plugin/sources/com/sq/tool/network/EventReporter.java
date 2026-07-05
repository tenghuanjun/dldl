package com.sq.tool.network;

import android.content.Context;
import com.sq.tools.report.event.IEventReporter;
import com.sqwan.common.track.SqTrackActionManager2;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EventReporter implements IEventReporter {
    @Override // com.sq.tools.report.event.IEventReporter
    public void init(Context context) {
    }

    @Override // com.sq.tools.report.event.IEventReporter
    public void setAccountId(String str) {
    }

    @Override // com.sq.tools.report.event.IEventReporter
    public void setDynamicSuperPropertiesTracker(IEventReporter.DynamicSuperPropertiesTracker dynamicSuperPropertiesTracker) {
    }

    @Override // com.sq.tools.report.event.IEventReporter
    public void setSuperProperties(Map<String, Object> map) {
    }

    @Override // com.sq.tools.report.event.IEventReporter
    public void setUserConsent(Map<String, Boolean> map) {
    }

    @Override // com.sq.tools.report.event.IEventReporter
    public void report(String str, Map<String, Object> map) {
        SqTrackActionManager2.getInstance().trackAction(str, convertMap(map), (HashMap<String, String>) null);
    }

    private HashMap<String, String> convertMap(Map<String, Object> map) {
        HashMap<String, String> map2 = new HashMap<>();
        if (map != null && !map.isEmpty()) {
            for (String str : map.keySet()) {
                Object obj = map.get(str);
                if (obj == null) {
                    map2.put(str, null);
                } else if (obj instanceof String) {
                    map2.put(str, (String) obj);
                } else {
                    map2.put(str, String.valueOf(obj));
                }
            }
        }
        return map2;
    }

    @Override // com.sq.tools.report.event.IEventReporter
    public void flush() {
        SqTrackActionManager2.getInstance().flush();
    }
}
