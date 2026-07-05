package com.huya.mtp.utils.pack;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Request extends Header {
    private ByteBuffer buffer;
    private Unpack up;

    public Request(byte[] bArr) {
        reload(bArr);
    }

    public Request(byte[] bArr, int i, int i2) {
        reload(bArr, i, i2);
    }

    public void reload(byte[] bArr) {
        reload(bArr, 0, bArr.length);
    }

    public void reload(byte[] bArr, int i, int i2) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, i, i2);
        this.buffer = byteBufferWrap;
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        Unpack unpack = this.up;
        if (unpack == null) {
            this.up = new Unpack(bArr, i, i2);
        } else {
            unpack.reload(bArr, i, i2);
        }
    }

    public static int peeklen(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() <= 4) {
            return 0;
        }
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        int iPosition = byteBuffer.position();
        int i = byteBuffer.getInt();
        byteBuffer.position(iPosition);
        return i;
    }

    public void head() {
        this.length = this.up.popUint32();
        this.uri = this.up.popUint32();
        this.resCode = this.up.popUint16();
    }

    public Unpack getPackData() {
        return this.up;
    }
}
