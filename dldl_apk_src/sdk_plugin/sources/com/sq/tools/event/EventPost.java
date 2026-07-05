package com.sq.tools.event;

import android.content.Context;
import com.sq.tools.Logger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EventPost {
    protected boolean sendToServer(Context context, EventRequest eventRequest, EventCollection eventCollection, String str) {
        Logger.error(Logger.tag(EventsTracker.TAG), "默认不支持sendToServer, 请自己实现请求方法", new Object[0]);
        return true;
    }
}
