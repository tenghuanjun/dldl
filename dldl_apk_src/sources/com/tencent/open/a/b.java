package com.tencent.open.a;

import java.io.IOException;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class b {
    private Response a;
    private String b = null;
    private int c;
    private int d;
    private int e;

    b(Response response, int i) {
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

    public int b() {
        return this.e;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.c;
    }
}
