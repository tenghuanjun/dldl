package com.huya.mtp.utils.pack;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Sender extends Header {
    private static final int HEADER_SIZE = 10;
    private int bodySize;
    private Pack pack;

    public int headerSize() {
        return 10;
    }

    public Sender(long j, Marshallable marshallable) {
        this(new Uint32(j), marshallable);
    }

    public Sender(int i, Marshallable marshallable) {
        this(new Uint32(i), marshallable);
    }

    public Sender(Uint32 uint32, Marshallable marshallable) {
        this.pack = new Pack();
        setUri(uint32);
        this.pack.getBuffer().position(headerSize());
        marshallable.marshall(this.pack);
        this.bodySize = this.pack.size() - headerSize();
    }

    public Sender(int i, byte[] bArr) {
        this.pack = new Pack();
        setUri(new Uint32(i));
        this.pack.getBuffer().position(headerSize());
        this.pack.setRawData(bArr);
        this.bodySize = this.pack.size() - headerSize();
    }

    public void endPack() {
        this.pack.replaceUint32(4, this.uri);
        this.pack.replaceUint16(8, this.resCode);
        this.pack.replaceUint32(0, new Uint32(headerSize() + bodySize()));
        this.pack.getBuffer().position(headerSize() + bodySize());
        this.pack.getBuffer().flip();
    }

    public Pack getPack() {
        return this.pack;
    }

    public int bodySize() {
        return this.bodySize;
    }

    public ByteBuffer getBuffer() {
        return getPack().getBuffer();
    }
}
