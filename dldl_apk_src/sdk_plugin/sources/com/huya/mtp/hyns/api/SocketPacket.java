package com.huya.mtp.hyns.api;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SocketPacket implements ISocketPacket {
    public byte[] data;

    @Override // com.huya.mtp.hyns.api.ISocketPacket
    public int length() {
        byte[] bArr = this.data;
        return (bArr != null ? bArr.length : 0) + 4;
    }

    @Override // com.huya.mtp.hyns.api.ISocketPacket
    public ByteBuffer getByteBuffer() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length());
        byteBufferAllocate.putInt(length());
        byteBufferAllocate.put(this.data);
        return byteBufferAllocate;
    }

    @Override // com.huya.mtp.hyns.api.ISocketPacket
    public void readFrom(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.getInt() - 4];
        this.data = bArr;
        byteBuffer.get(bArr);
    }
}
