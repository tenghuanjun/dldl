package com.unicom.online.account.kernel;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class o extends Exception {
    private final int a;
    private final String b;

    public o(j jVar) {
        super(jVar.A);
        this.a = Integer.parseInt(jVar.z);
        this.b = jVar.A;
    }

    public o(j jVar, Exception exc) {
        super(jVar.A);
        this.a = Integer.parseInt(jVar.z);
        this.b = jVar.A + " case by : " + exc.getMessage();
    }
}
