package com.bytedance.http;

/* JADX INFO: loaded from: classes2.dex */
public interface WebSocketListener extends Callback {
    void onClosed(Call call, int i, String str);

    void onClosing(Call call, int i, String str);

    void onMessage(Call call, String str);

    void onMessage(Call call, byte[] bArr);

    void onOpen(Call call);
}
