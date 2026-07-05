package com.bytedance.pangle.res.a;

import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class g extends f {
    public g(i iVar) {
        super(iVar);
    }

    public final int[] a(int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readInt();
        }
        return iArr;
    }

    public final void a() throws IOException {
        short s = readShort();
        if (s != 8) {
            throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", (short) 8, Short.valueOf(s)));
        }
    }

    public final void b() throws IOException {
        byte b = readByte();
        if (b != 0) {
            throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", (byte) 0, Byte.valueOf(b)));
        }
    }

    public final void b(int i) throws IOException {
        int i2;
        while (true) {
            i2 = readInt();
            if (i2 != i && i2 >= 1835009) {
                break;
            } else {
                i = -1;
            }
        }
        if (i2 != 1835009) {
            throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", 1835009, Integer.valueOf(i2)));
        }
    }

    @Override // com.bytedance.pangle.res.a.f, java.io.DataInput
    public final int skipBytes(int i) {
        int i2 = 0;
        while (i2 < i) {
            int iSkipBytes = super.skipBytes(i - i2);
            if (iSkipBytes <= 0) {
                break;
            }
            i2 += iSkipBytes;
        }
        return i2;
    }
}
