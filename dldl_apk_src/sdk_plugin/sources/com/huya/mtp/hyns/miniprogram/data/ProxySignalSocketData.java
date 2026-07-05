package com.huya.mtp.hyns.miniprogram.data;

import com.huya.mtp.hyns.api.ISocketPacket;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ProxySignalSocketData implements ISocketPacket {
    public byte[] data;

    @Override // com.huya.mtp.hyns.api.ISocketPacket
    public int length() {
        return this.data.length;
    }

    @Override // com.huya.mtp.hyns.api.ISocketPacket
    public ByteBuffer getByteBuffer() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length());
        byteBufferAllocate.put(this.data);
        return byteBufferAllocate;
    }

    @Override // com.huya.mtp.hyns.api.ISocketPacket
    public void readFrom(ByteBuffer byteBuffer) {
        this.data = byteBuffer.array();
    }
}
