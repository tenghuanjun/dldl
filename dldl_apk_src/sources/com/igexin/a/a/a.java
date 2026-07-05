package com.igexin.a.a;

import com.igexin.a.a.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a extends c.a {
    public a(f fVar, c.b bVar, long j, int i) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(bVar.d ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = j + ((long) (i * 8));
        this.d = fVar.b(byteBufferAllocate, j2);
        this.e = fVar.b(byteBufferAllocate, j2 + 4);
    }
}
