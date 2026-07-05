package com.sq.track;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CacheEvent {
    private String event;
    private Map<String, String> eventParams;

    public CacheEvent(String str, Map<String, String> map) {
        this.event = str;
        this.eventParams = map;
    }

    public String getEvent() {
        return this.event;
    }

    public void setEvent(String str) {
        this.event = str;
    }

    public Map<String, String> getEventParams() {
        return this.eventParams;
    }

    public void setEventParams(HashMap<String, String> map) {
        this.eventParams = map;
    }
}
