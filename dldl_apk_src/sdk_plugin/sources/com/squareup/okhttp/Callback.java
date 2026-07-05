package com.squareup.okhttp;

import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface Callback {
    void onFailure(Request request, IOException iOException);

    void onResponse(Response response) throws IOException;
}
