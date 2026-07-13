package com.tencent.open.a;

import java.io.IOException;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
class d implements g {
    private Response a;
    private String b = null;
    private int c;
    private int d;
    private int e;

    d(Response response, int i) {
        this.a = response;
        this.d = i;
        this.c = response.code();
        ResponseBody responseBodyBody = this.a.body();
        if (responseBodyBody != null) {
            this.e = (int) responseBodyBody.contentLength();
        } else {
            this.e = 0;
        }
    }

    @Override // com.tencent.open.a.g
    public String a() throws IOException {
        if (this.b == null) {
            ResponseBody responseBodyBody = this.a.body();
            if (responseBodyBody != null) {
                this.b = responseBodyBody.string();
            }
            if (this.b == null) {
                this.b = "";
            }
        }
        return this.b;
    }

    @Override // com.tencent.open.a.g
    public int b() {
        return this.e;
    }

    @Override // com.tencent.open.a.g
    public int c() {
        return this.d;
    }

    @Override // com.tencent.open.a.g
    public int d() {
        return this.c;
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + hashCode() + this.b + this.c + this.d + this.e;
    }
}
