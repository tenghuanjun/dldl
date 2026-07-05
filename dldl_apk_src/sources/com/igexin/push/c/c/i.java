package com.igexin.push.c.c;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class i extends c {
    public static final int a = 4;
    public long b;
    public byte c;
    public int d;
    public String e;
    public List<j> f;

    public i() {
        this.m = 4;
        this.n = (byte) 20;
    }

    private static String a(byte[] bArr, int i, int i2) {
        try {
            return new String(bArr, i, i2, "UTF-8");
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // com.igexin.push.c.c.c
    public final void a(byte[] bArr) {
        this.b = com.igexin.b.a.b.g.d(bArr, 0);
        this.c = bArr[8];
        this.d = com.igexin.b.a.b.g.c(bArr, 9) & (-1);
        int i = 14;
        if (bArr.length > 13) {
            int i2 = bArr[13] & UByte.MAX_VALUE;
            if (i2 > 0) {
                this.f = new ArrayList();
                int i3 = i2 + 14;
                while (i < i3) {
                    j jVar = new j();
                    this.f.add(jVar);
                    int i4 = i + 1;
                    int i5 = bArr[i] & UByte.MAX_VALUE & 255;
                    int i6 = i4 + 1;
                    int i7 = bArr[i4] & UByte.MAX_VALUE & 255;
                    jVar.a = (byte) i5;
                    if ((i5 == 1 || i5 == 4) && i7 > 0) {
                        try {
                            jVar.b = new String(bArr, i6, i7, "UTF-8");
                        } catch (Exception unused) {
                        }
                    }
                    i = i6 + i7;
                }
            }
        } else {
            i = 13;
        }
        if (bArr.length > i) {
            this.e = a(bArr, i + 1, bArr[i] & UByte.MAX_VALUE);
        }
    }

    @Override // com.igexin.push.c.c.c
    public final byte[] b() {
        int length;
        int i;
        List<j> list = this.f;
        byte[] byteArray = null;
        if (list != null && list.size() > 0) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Iterator<j> it = this.f.iterator();
            while (it.hasNext()) {
                try {
                    byteArrayOutputStream.write(it.next().b());
                    byteArray = byteArrayOutputStream.toByteArray();
                } catch (IOException unused) {
                }
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException unused2) {
            }
        }
        if (byteArray != null) {
            length = byteArray.length;
            i = length + 1;
        } else {
            length = 0;
            i = 1;
        }
        byte[] bArr = new byte[i + 12 + this.e.getBytes().length + 1];
        com.igexin.b.a.b.g.a(this.b, bArr, 0);
        com.igexin.b.a.b.g.a(((this.c & UByte.MAX_VALUE) << 24) | this.d, bArr, 8);
        bArr[12] = (byte) length;
        int iA = length > 0 ? 13 + com.igexin.b.a.b.g.a(byteArray, 0, bArr, 13, length) : 13;
        byte[] bytes = this.e.getBytes();
        bArr[iA] = (byte) bytes.length;
        System.arraycopy(bytes, 0, bArr, iA + 1, bytes.length);
        return bArr;
    }
}
