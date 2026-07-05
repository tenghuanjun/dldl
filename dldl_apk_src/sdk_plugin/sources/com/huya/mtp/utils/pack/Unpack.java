package com.huya.mtp.utils.pack;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Unpack {
    private ByteBuffer buffer;

    public Unpack(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public Unpack(byte[] bArr, int i, int i2) {
        reload(bArr, i, i2);
    }

    public void reload(byte[] bArr, int i, int i2) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, i, i2);
        this.buffer = byteBufferWrap;
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
    }

    public int size() {
        return this.buffer.remaining();
    }

    public Uint32 popUint32() {
        return new Uint32(this.buffer.getInt());
    }

    public int popInt() {
        return this.buffer.getInt();
    }

    public Uint8 popUint8() {
        return new Uint8(this.buffer.get());
    }

    public Uint16 popUint16() {
        return new Uint16((int) this.buffer.getShort());
    }

    public Uint64 popUint64() {
        return new Uint64(this.buffer.getLong());
    }

    public boolean popBoolean() {
        return this.buffer.get() == 1;
    }

    public byte[] popBytes() {
        byte[] bArr = new byte[this.buffer.getShort()];
        this.buffer.get(bArr);
        return bArr;
    }

    public String popString() {
        try {
            return new String(popBytes(), "utf-8");
        } catch (UnsupportedEncodingException unused) {
            throw new UnpackException();
        }
    }

    public String popString(String str) {
        try {
            return new String(popBytes(), str);
        } catch (UnsupportedEncodingException unused) {
            throw new UnpackException();
        }
    }

    public String popString32() {
        try {
            byte[] bArr = new byte[this.buffer.getInt()];
            this.buffer.get(bArr);
            return new String(bArr, "utf-8");
        } catch (UnsupportedEncodingException unused) {
            throw new UnpackException();
        }
    }

    public byte[] popBytes32() {
        byte[] bArr = new byte[this.buffer.getInt()];
        this.buffer.get(bArr);
        return bArr;
    }

    public String toString() {
        return "Pack [buffer=" + bufferString() + "]";
    }

    private String bufferString() {
        byte[] bArrRemainBuffer = remainBuffer();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArrRemainBuffer) {
            stringBuffer.append(Integer.toHexString(b & 255).toUpperCase());
            stringBuffer.append(" ");
        }
        return stringBuffer.toString();
    }

    public byte[] remainBuffer() {
        byte[] bArr = new byte[this.buffer.remaining()];
        int iPosition = this.buffer.position();
        this.buffer.get(bArr);
        this.buffer.position(iPosition);
        return bArr;
    }
}
