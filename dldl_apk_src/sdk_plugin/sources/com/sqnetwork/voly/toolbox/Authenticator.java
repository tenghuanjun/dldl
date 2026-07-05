package com.sqnetwork.voly.toolbox;

import com.sqnetwork.voly.AuthFailureError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface Authenticator {
    String getAuthToken() throws AuthFailureError;

    void invalidateAuthToken(String authToken);
}
