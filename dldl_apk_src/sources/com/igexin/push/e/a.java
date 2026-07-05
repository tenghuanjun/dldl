package com.igexin.push.e;

import android.os.Message;
import com.igexin.push.core.d;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a implements com.igexin.push.e.b.c {
    private static final long b = 360000;
    private long a = 0;

    @Override // com.igexin.push.e.b.c
    public final void a() {
        Message messageObtain = Message.obtain();
        messageObtain.what = com.igexin.push.core.b.S;
        d.a.a.a(messageObtain);
    }

    @Override // com.igexin.push.e.b.c
    public final void a(long j) {
        this.a = j;
    }

    @Override // com.igexin.push.e.b.c
    public final boolean b() {
        return System.currentTimeMillis() - this.a > b;
    }
}
