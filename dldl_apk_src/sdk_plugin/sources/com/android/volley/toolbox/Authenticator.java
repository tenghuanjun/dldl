package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public interface Authenticator {
    String getAuthToken() throws AuthFailureError;

    void invalidateAuthToken(String str);
}
