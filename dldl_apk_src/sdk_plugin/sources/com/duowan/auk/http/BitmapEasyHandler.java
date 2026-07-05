package com.duowan.auk.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.duowan.auk.http.HttpClient;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class BitmapEasyHandler implements HttpClient.HttpHandler {
    public abstract void onFailure();

    public abstract void onSuccess(Bitmap bitmap);

    @Override // com.duowan.auk.http.HttpClient.HttpHandler
    public void onSuccess(int i, Map<String, List<String>> map, byte[] bArr) {
        onSuccess(bArr == null ? null : BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
    }

    @Override // com.duowan.auk.http.HttpClient.HttpHandler
    public void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Exception exc) {
        onFailure();
    }
}
