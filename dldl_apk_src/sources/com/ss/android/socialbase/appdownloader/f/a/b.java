package com.ss.android.socialbase.appdownloader.f.a;

import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class b {
    public static final void a(d dVar, int i) throws IOException {
        int iB = dVar.b();
        if (iB == i) {
            return;
        }
        throw new IOException("Expected chunk of type 0x" + Integer.toHexString(i) + ", read 0x" + Integer.toHexString(iB) + ".");
    }
}
