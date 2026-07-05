package com.getui.gtc.base.http;

import java.io.IOException;
import java.net.HttpURLConnection;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface Interceptor {

    public interface Chain {
        HttpURLConnection connection();

        Response proceed(Request request) throws IOException;

        Request request();
    }

    Response intercept(Chain chain) throws IOException;
}
