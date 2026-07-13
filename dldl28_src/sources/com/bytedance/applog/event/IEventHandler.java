package com.bytedance.applog.event;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface IEventHandler {
    int acceptType();

    EventPolicy onReceive(int i, String str, JSONObject jSONObject);
}
