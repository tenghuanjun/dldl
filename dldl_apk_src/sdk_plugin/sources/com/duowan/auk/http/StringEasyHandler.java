package com.duowan.auk.http;

import com.duowan.auk.http.HttpClient;
import com.duowan.auk.util.L;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class StringEasyHandler implements HttpClient.HttpHandler {
    public abstract void onFailure();

    public abstract void onSuccess(String str);

    @Override // com.duowan.auk.http.HttpClient.HttpHandler
    public void onSuccess(int i, Map<String, List<String>> map, byte[] bArr) {
        onSuccess(bArr == null ? "" : new String(bArr));
    }

    @Override // com.duowan.auk.http.HttpClient.HttpHandler
    public void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Exception exc) {
        onFailure();
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(i);
        objArr[1] = map;
        objArr[2] = bArr == null ? "" : new String(bArr);
        objArr[3] = exc;
        L.error(this, "http failure: %d %s %s %s", objArr);
    }
}
