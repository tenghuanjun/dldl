package com.huya.mtp.hyns.api;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface ISocketPacket {
    ByteBuffer getByteBuffer();

    int length();

    void readFrom(ByteBuffer byteBuffer);
}
