package com.igexin.a.a;

import com.igexin.a.a.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class e extends c.b {
    private final f m;

    public e(boolean z, f fVar) throws IOException {
        this.d = z;
        this.m = fVar;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.e = fVar.c(byteBufferAllocate, 16L);
        this.f = fVar.a(byteBufferAllocate, 32L);
        this.g = fVar.a(byteBufferAllocate, 40L);
        this.h = fVar.c(byteBufferAllocate, 54L);
        this.i = fVar.c(byteBufferAllocate, 56L);
        this.j = fVar.c(byteBufferAllocate, 58L);
        this.k = fVar.c(byteBufferAllocate, 60L);
        this.l = fVar.c(byteBufferAllocate, 62L);
    }

    @Override // com.igexin.a.a.c.b
    public final c.a a(long j, int i) throws IOException {
        return new b(this.m, this, j, i);
    }

    @Override // com.igexin.a.a.c.b
    public final c.AbstractC0053c a(long j) throws IOException {
        return new h(this.m, this, j);
    }

    @Override // com.igexin.a.a.c.b
    public final c.d a() throws IOException {
        return new j(this.m, this);
    }
}
