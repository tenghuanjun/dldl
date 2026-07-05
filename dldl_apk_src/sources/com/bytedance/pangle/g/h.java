package com.bytedance.pangle.g;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class h implements k {
    private final ByteBuffer a;

    h(ByteBuffer byteBuffer) {
        this.a = byteBuffer.slice();
    }

    @Override // com.bytedance.pangle.g.k
    public final long a() {
        return this.a.capacity();
    }

    @Override // com.bytedance.pangle.g.k
    public final void a(j jVar, long j, int i) {
        ByteBuffer byteBufferSlice;
        synchronized (this.a) {
            this.a.position(0);
            int i2 = (int) j;
            this.a.limit(i + i2);
            this.a.position(i2);
            byteBufferSlice = this.a.slice();
        }
        jVar.a(byteBufferSlice);
    }
}
