package com.bytedance.http;

/* JADX INFO: loaded from: classes2.dex */
public interface WebSocket extends Call {
    void enqueue(WebSocketListener webSocketListener);

    boolean isOpen();

    int queueSize();

    boolean send(String str);

    boolean send(byte[] bArr);
}
