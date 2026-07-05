package com.igexin.a.a;

import com.igexin.a.a.c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d extends c.b {
    private final f m;

    public d(boolean z, f fVar) throws IOException {
        this.d = z;
        this.m = fVar;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.e = fVar.c(byteBufferAllocate, 16L);
        this.f = fVar.b(byteBufferAllocate, 28L);
        this.g = fVar.b(byteBufferAllocate, 32L);
        this.h = fVar.c(byteBufferAllocate, 42L);
        this.i = fVar.c(byteBufferAllocate, 44L);
        this.j = fVar.c(byteBufferAllocate, 46L);
        this.k = fVar.c(byteBufferAllocate, 48L);
        this.l = fVar.c(byteBufferAllocate, 50L);
    }

    @Override // com.igexin.a.a.c.b
    public final c.a a(long j, int i) throws IOException {
        return new a(this.m, this, j, i);
    }

    @Override // com.igexin.a.a.c.b
    public final c.AbstractC0053c a(long j) throws IOException {
        return new g(this.m, this, j);
    }

    @Override // com.igexin.a.a.c.b
    public final c.d a() throws IOException {
        return new i(this.m, this);
    }
}
