package com.igexin.push.c.c;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class j extends c {
    public byte a;
    public Object b;

    @Override // com.igexin.push.c.c.c
    public final void a(byte[] bArr) {
    }

    @Override // com.igexin.push.c.c.c
    public final byte[] b() {
        byte b = this.a;
        byte[] bytes = (b == 1 || b == 2 || (b != 3 && (b == 4 || b == 5))) ? ((String) this.b).getBytes() : null;
        if (bytes == null) {
            return null;
        }
        byte[] bArr = new byte[bytes.length + 2];
        bArr[0] = this.a;
        bArr[1] = (byte) bytes.length;
        System.arraycopy(bytes, 0, bArr, 2, bytes.length);
        return bArr;
    }
}
