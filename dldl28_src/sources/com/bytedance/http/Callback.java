package com.bytedance.http;

/* JADX INFO: loaded from: classes2.dex */
public interface Callback {
    void onDiagnosis(Call call, HttpResponse httpResponse);

    void onResponse(Call call, HttpResponse httpResponse);
}
