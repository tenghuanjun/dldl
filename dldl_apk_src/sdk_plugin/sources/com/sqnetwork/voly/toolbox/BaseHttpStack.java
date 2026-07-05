package com.sqnetwork.voly.toolbox;

import com.sqnetwork.voly.AuthFailureError;
import com.sqnetwork.voly.Request;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseHttpStack {
    public abstract HttpResponse executeRequest(Request<?> request, Map<String, String> additionalHeaders) throws AuthFailureError, IOException;
}
