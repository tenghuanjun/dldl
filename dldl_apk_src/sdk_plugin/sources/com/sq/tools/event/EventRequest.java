package com.sq.tools.event;

import android.content.Context;
import com.sq.tools.network.ContentType;
import com.sq.tools.network.request.RequestTools;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class EventRequest extends RequestTools {
    protected abstract void fresh(Context context);

    protected EventRequest(Context context) {
        this.contentType = ContentType.JSON;
        fresh(context);
    }
}
