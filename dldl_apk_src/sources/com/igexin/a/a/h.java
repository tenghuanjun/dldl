package com.igexin.a.a;

import com.igexin.a.a.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class h extends c.AbstractC0053c {
    public h(f fVar, c.b bVar, long j) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(bVar.d ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = bVar.f + (j * ((long) bVar.h));
        this.c = fVar.b(byteBufferAllocate, j2);
        this.d = fVar.a(byteBufferAllocate, 8 + j2);
        this.e = fVar.a(byteBufferAllocate, 16 + j2);
        this.f = fVar.a(byteBufferAllocate, j2 + 40);
    }
}
