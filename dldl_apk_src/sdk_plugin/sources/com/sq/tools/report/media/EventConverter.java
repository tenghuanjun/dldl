package com.sq.tools.report.media;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EventConverter {
    private final Map<String, String> mEventMap = new HashMap();
    private final Map<String, String> mParamMap = new HashMap();

    public String convert(String event) {
        if (event == null) {
            return null;
        }
        return !this.mEventMap.containsKey(event) ? event : this.mEventMap.get(event);
    }

    public EventConverter map(String sqEvent, String thirdEvent) {
        this.mEventMap.put(sqEvent, thirdEvent);
        return this;
    }

    public String convertParam(String param) {
        if (param == null) {
            return null;
        }
        return !this.mParamMap.containsKey(param) ? param : this.mParamMap.get(param);
    }

    public EventConverter mapParam(String sqParam, String thirdParam) {
        this.mParamMap.put(sqParam, thirdParam);
        return this;
    }
}
