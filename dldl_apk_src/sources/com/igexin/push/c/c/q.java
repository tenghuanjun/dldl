package com.igexin.push.c.c;

import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class q extends c {
    public static final int a = 20;
    public int b;

    public q() {
        this.m = 20;
    }

    @Override // com.igexin.push.c.c.c
    public final void a(byte[] bArr) {
        if (bArr.length == 1) {
            this.b = bArr[0] & UByte.MAX_VALUE;
        }
    }

    @Override // com.igexin.push.c.c.c
    public final byte[] b() {
        return null;
    }
}
