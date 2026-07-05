package com.bytedance.pangle.res.a;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class i extends FilterInputStream implements DataInput {
    private static int a(byte b, byte b2, byte b3, byte b4) {
        return (b << 24) | ((b2 & UByte.MAX_VALUE) << 16) | ((b3 & UByte.MAX_VALUE) << 8) | (b4 & UByte.MAX_VALUE);
    }

    public i(e eVar) {
        super(eVar);
    }

    @Override // java.io.DataInput
    public final String readLine() {
        throw new UnsupportedOperationException("readLine is not supported");
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr, int i, int i2) throws IOException {
        d.a(this, bArr, i, i2);
    }

    @Override // java.io.DataInput
    public final int skipBytes(int i) {
        return (int) this.in.skip(i);
    }

    @Override // java.io.DataInput
    public final int readUnsignedByte() throws IOException {
        int i = this.in.read();
        if (i >= 0) {
            return i;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final int readUnsignedShort() throws IOException {
        return a((byte) 0, (byte) 0, b(), b());
    }

    @Override // java.io.DataInput
    public final int readInt() throws IOException {
        byte b = b();
        byte b2 = b();
        return a(b(), b(), b2, b);
    }

    @Override // java.io.DataInput
    public final long readLong() throws IOException {
        byte b = b();
        byte b2 = b();
        byte b3 = b();
        byte b4 = b();
        byte b5 = b();
        byte b6 = b();
        return ((((long) b()) & 255) << 56) | ((((long) b()) & 255) << 48) | ((((long) b6) & 255) << 40) | ((((long) b5) & 255) << 32) | ((((long) b4) & 255) << 24) | ((((long) b3) & 255) << 16) | ((((long) b2) & 255) << 8) | (((long) b) & 255);
    }

    @Override // java.io.DataInput
    public final float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    @Override // java.io.DataInput
    public final double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    @Override // java.io.DataInput
    public final String readUTF() {
        return new DataInputStream(this.in).readUTF();
    }

    @Override // java.io.DataInput
    public final short readShort() {
        return (short) readUnsignedShort();
    }

    @Override // java.io.DataInput
    public final char readChar() {
        return (char) readUnsignedShort();
    }

    @Override // java.io.DataInput
    public final byte readByte() {
        return (byte) readUnsignedByte();
    }

    @Override // java.io.DataInput
    public final boolean readBoolean() {
        return readUnsignedByte() != 0;
    }

    private byte b() throws IOException {
        int i = this.in.read();
        if (-1 != i) {
            return (byte) i;
        }
        throw new EOFException();
    }

    public final e a() {
        return (e) this.in;
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr) throws IOException {
        d.a(this, bArr, 0, bArr.length);
    }
}
