package com.huya.mtp.utils.pack;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Pack {
    private ByteBuffer buffer;

    public Pack() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(512);
        this.buffer = byteBufferAllocate;
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
    }

    private void autoExpand(int i) {
        expand(i, true);
    }

    private void expand(int i, boolean z) {
        expand(this.buffer.position(), i, z);
    }

    private void expand(int i, int i2, boolean z) {
        int i3 = i + i2;
        int iNormalizeCapacity = z ? normalizeCapacity(i3) : i3;
        if (iNormalizeCapacity > this.buffer.capacity()) {
            capacity(iNormalizeCapacity);
        }
        if (i3 > this.buffer.limit()) {
            this.buffer.limit(i3);
        }
    }

    public void capacity(int i) {
        if (i > this.buffer.capacity()) {
            int iPosition = this.buffer.position();
            int iLimit = this.buffer.limit();
            ByteOrder byteOrderOrder = this.buffer.order();
            ByteBuffer byteBuffer = this.buffer;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
            byteBuffer.clear();
            byteBufferAllocate.put(byteBuffer);
            this.buffer = byteBufferAllocate;
            byteBufferAllocate.limit(iLimit);
            this.buffer.position(iPosition);
            this.buffer.order(byteOrderOrder);
        }
    }

    protected static int normalizeCapacity(int i) {
        if (i < 0) {
            return Integer.MAX_VALUE;
        }
        int iHighestOneBit = Integer.highestOneBit(i);
        int i2 = iHighestOneBit << (iHighestOneBit < i ? 1 : 0);
        if (i2 < 0) {
            return Integer.MAX_VALUE;
        }
        return i2;
    }

    public void replaceUint32(int i, Uint32 uint32) {
        int iPosition = this.buffer.position();
        this.buffer.position(i);
        this.buffer.putInt(uint32.toInt()).position(iPosition);
    }

    public void replaceUint16(int i, Uint16 uint16) {
        int iPosition = this.buffer.position();
        this.buffer.position(i);
        this.buffer.putShort(uint16.toShort()).position(iPosition);
    }

    public int size() {
        return this.buffer.position();
    }

    public ByteBuffer getBuffer() {
        return this.buffer;
    }

    public byte[] toBytes() {
        return this.buffer.array();
    }

    public Pack push(Uint32 uint32) {
        if (uint32 == null) {
            throw new PackException("Uint32 is null");
        }
        autoExpand(4);
        this.buffer.putInt(uint32.toInt());
        return this;
    }

    public Pack push(Uint16 uint16) {
        if (uint16 == null) {
            throw new PackException("Uint16 is null");
        }
        autoExpand(2);
        this.buffer.putShort(uint16.toShort());
        return this;
    }

    public Pack push(Uint64 uint64) {
        if (uint64 == null) {
            throw new PackException("Uint64 is null");
        }
        autoExpand(8);
        this.buffer.putLong(uint64.toLong());
        return this;
    }

    public Pack push(Uint8 uint8) {
        autoExpand(1);
        this.buffer.put(uint8.toByte());
        return this;
    }

    public Pack push(byte b) {
        autoExpand(1);
        this.buffer.put(b);
        return this;
    }

    public Pack push(boolean z) {
        autoExpand(1);
        this.buffer.put(z ? (byte) 1 : (byte) 0);
        return this;
    }

    public Pack push(byte[] bArr) {
        autoExpand(bArr.length + 2);
        push(new Uint16(bArr.length));
        this.buffer.put(bArr);
        return this;
    }

    public Pack pushString32(byte[] bArr) {
        autoExpand(bArr.length + 4);
        push(new Uint32(bArr.length));
        this.buffer.put(bArr);
        return this;
    }

    public Pack setRawData(byte[] bArr) {
        autoExpand(bArr.length);
        this.buffer.put(bArr);
        return this;
    }

    public Pack push(String str) {
        if (str == null) {
            str = "";
        }
        try {
            byte[] bytes = str.getBytes("utf-8");
            if (bytes.length > 65535) {
                throw new PackException("String too big");
            }
            return push(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new PackException(e);
        }
    }

    public Pack push(String str, String str2) {
        if (str == null) {
            str = "";
        }
        try {
            byte[] bytes = str.getBytes(str2);
            if (bytes.length > 65535) {
                throw new PackException("String too big");
            }
            return push(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new PackException(e);
        }
    }

    public String toString() {
        return "Pack [buffer=" + bufferString() + "]";
    }

    private String bufferString() {
        int iLimit = this.buffer.limit();
        byte[] bArr = new byte[iLimit];
        this.buffer.get(bArr);
        this.buffer.flip();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < iLimit; i++) {
            stringBuffer.append(Integer.toHexString(bArr[i] & 255).toUpperCase());
            stringBuffer.append(" ");
        }
        return stringBuffer.toString();
    }
}
