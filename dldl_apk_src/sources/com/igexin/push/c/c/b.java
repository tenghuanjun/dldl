package com.igexin.push.c.c;

import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b extends c {
    public static final int a = 25;
    public int b;
    public int c;
    public long d;
    public String e;
    public Object f;
    public Object g;
    public String h = "";
    public String i = "UTF-8";
    public String j = com.igexin.push.a.i;
    public String k = com.igexin.push.a.i;
    public String l = com.igexin.push.a.i;

    public b() {
        this.m = 25;
        this.n = (byte) 20;
    }

    private boolean d() {
        return this.c == 128;
    }

    private boolean e() {
        return this.c == 64;
    }

    private boolean f() {
        return this.c == 192;
    }

    private void g() {
        this.c = 128;
    }

    private void h() {
        this.c = 64;
    }

    private void k() {
        this.c = 192;
    }

    @Override // com.igexin.push.c.c.c, com.igexin.b.a.d.a.a
    public final void a() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.i = null;
    }

    @Override // com.igexin.push.c.c.c
    public final void a(byte[] bArr) {
        int i;
        int i2;
        this.b = com.igexin.b.a.b.g.b(bArr, 0);
        this.c = bArr[2] & 192;
        this.i = a(bArr[2]);
        this.d = com.igexin.b.a.b.g.d(bArr, 3);
        int i3 = bArr[11] & 255;
        try {
            this.e = new String(bArr, 12, i3, this.i);
        } catch (Exception unused) {
        }
        int i4 = i3 + 12;
        int i5 = 0;
        while (true) {
            i = i5 | (bArr[i4] & TTDownloadField.CALL_DOWNLOAD_MODEL_SET_EXTRA_VALUE);
            if ((bArr[i4] & ByteCompanionObject.MIN_VALUE) == 0) {
                break;
            }
            i5 = i << 7;
            i4++;
        }
        int i6 = i4 + 1;
        if (i > 0) {
            if (this.c == 192) {
                this.f = new byte[i];
                System.arraycopy(bArr, i6, this.f, 0, i);
            } else {
                try {
                    this.f = new String(bArr, i6, i, this.i);
                } catch (Exception unused2) {
                }
            }
        }
        int i7 = i6 + i;
        int i8 = 0;
        while (true) {
            i2 = i8 | (bArr[i7] & TTDownloadField.CALL_DOWNLOAD_MODEL_SET_EXTRA_VALUE);
            if ((bArr[i7] & ByteCompanionObject.MIN_VALUE) == 0) {
                break;
            }
            i8 = i2 << 7;
            i7++;
        }
        int i9 = i7 + 1;
        if (i2 > 0) {
            this.g = new byte[i2];
            System.arraycopy(bArr, i9, this.g, 0, i2);
        }
        int i10 = i9 + i2;
        if (bArr.length > i10) {
            try {
                this.h = new String(bArr, i10 + 1, bArr[i10] & UByte.MAX_VALUE, this.i);
            } catch (Exception unused3) {
            }
        }
    }

    @Override // com.igexin.push.c.c.c
    public final byte[] b() {
        byte[] bArr = null;
        try {
            byte[] bytes = this.e.getBytes(this.i);
            byte[] bytes2 = !"".equals(this.f) ? this.c == 192 ? (byte[]) this.f : ((String) this.f).getBytes(this.i) : null;
            byte[] bArr2 = this.g != null ? (byte[]) this.g : null;
            byte[] bytes3 = this.h.getBytes(this.i);
            int length = bytes2 == null ? 0 : bytes2.length;
            int length2 = bArr2 == null ? 0 : bArr2.length;
            byte[] bArrA = com.igexin.b.a.b.g.a(length);
            byte[] bArrA2 = com.igexin.b.a.b.g.a(length2);
            bArr = new byte[bytes.length + 13 + bArrA.length + length + bArrA2.length + length2 + bytes3.length];
            com.igexin.b.a.b.g.b(this.b, bArr, 0);
            bArr[2] = (byte) (this.c | a(this.i));
            com.igexin.b.a.b.g.a(this.d, bArr, 3);
            bArr[11] = (byte) bytes.length;
            int iA = com.igexin.b.a.b.g.a(bytes, 0, bArr, 12, bytes.length) + 12;
            int iA2 = iA + com.igexin.b.a.b.g.a(bArrA, 0, bArr, iA, bArrA.length);
            if (length > 0) {
                iA2 += com.igexin.b.a.b.g.a(bytes2, 0, bArr, iA2, length);
            }
            int iA3 = iA2 + com.igexin.b.a.b.g.a(bArrA2, 0, bArr, iA2, bArrA2.length);
            if (length2 > 0) {
                iA3 += com.igexin.b.a.b.g.a(bArr2, 0, bArr, iA3, length2);
            }
            bArr[iA3] = (byte) bytes3.length;
            com.igexin.b.a.b.g.a(bytes3, 0, bArr, iA3 + 1, bytes3.length);
        } catch (Exception unused) {
        }
        if (bArr != null && bArr.length >= com.igexin.push.config.d.S) {
            this.n = (byte) (this.n | ByteCompanionObject.MIN_VALUE);
        }
        return bArr;
    }
}
