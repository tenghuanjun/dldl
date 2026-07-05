package com.huya.mtp.hycloudgame.base.tcpsocket.core;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
class TcpStickPackage {
    TcpStickPackage() {
    }

    public static void processBytes(byte[] bArr, int i, ByteBuffer byteBuffer, ISocketClientListener iSocketClientListener) {
        int iPosition = byteBuffer.position() > 0 ? byteBuffer.position() + i : i;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(iPosition);
        if (byteBuffer.position() > 0) {
            byteBufferAllocate.put(byteBuffer.array(), 0, byteBuffer.position());
            byteBuffer.clear();
        }
        byteBufferAllocate.put(bArr, 0, i);
        byteBufferAllocate.position(0);
        byteBufferAllocate.limit(iPosition);
        int iRemaining = 0;
        while (iRemaining < iPosition) {
            byteBufferAllocate.mark();
            if (byteBufferAllocate.remaining() > 4) {
                int i2 = byteBufferAllocate.getInt() - 4;
                byte[] bArr2 = new byte[i2];
                if (byteBufferAllocate.remaining() >= i2) {
                    byteBufferAllocate.get(bArr2, 0, i2);
                    synchronized (TcpClientCore.class) {
                        iSocketClientListener.onMessage(ByteBuffer.wrap(bArr2));
                    }
                    iRemaining = iRemaining + 4 + i2;
                } else {
                    iRemaining = iRemaining + 4 + byteBufferAllocate.remaining();
                    byteBufferAllocate.reset();
                    byteBuffer.clear();
                    byteBuffer.put(byteBufferAllocate);
                }
                byteBufferAllocate.position(iRemaining);
            } else {
                iRemaining += byteBufferAllocate.remaining();
                byteBufferAllocate.reset();
                byteBuffer.clear();
                byteBuffer.put(byteBufferAllocate);
            }
        }
    }
}
