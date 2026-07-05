package com.igexin.b.a.b.a.a;

import android.text.TextUtils;
import com.igexin.b.a.b.a.a.a;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class e extends a {
    private static final String P = "GS-R";
    public static final int a = -2035;
    private byte[] Q;
    protected h j;
    com.igexin.b.a.b.a.a.a.b k;
    protected com.igexin.b.a.b.d l;

    public e(h hVar, com.igexin.b.a.b.d dVar) {
        super(-2035, dVar);
        this.l = dVar;
        this.j = hVar;
    }

    private void a(com.igexin.b.a.b.a.a.a.b bVar) {
        this.k = bVar;
    }

    @Override // com.igexin.b.a.b.f, com.igexin.b.a.d.f, com.igexin.b.a.d.a.a
    public final void a() {
        super.a();
        com.igexin.b.a.c.a.a("GS-R|rt dispose", new Object[0]);
        if (this.k != null) {
            if (this.g != a.EnumC0055a.b) {
                this.k.a();
            } else if (!TextUtils.isEmpty(this.h)) {
                this.k.a(new Exception(this.h));
            }
        }
        if (this.Q != null) {
            this.Q = null;
        }
        this.k = null;
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void b_() throws Exception {
        super.b_();
        Thread threadCurrentThread = Thread.currentThread();
        com.igexin.b.a.c.a.a("GS-R|" + threadCurrentThread + " running", new Object[0]);
        while (this.i && !threadCurrentThread.isInterrupted() && !this.f) {
            try {
                com.igexin.b.a.d.a.e eVarB = this.l.b(null, this.j);
                if (eVarB != null) {
                    com.igexin.b.a.b.e.a().a(eVarB);
                    com.igexin.b.a.b.e.a().b();
                } else {
                    com.igexin.b.a.c.a.a("GS-R|read error data", new Object[0]);
                }
                this.g = a.EnumC0055a.a;
            } catch (Throwable th) {
                this.i = false;
                if (this.g != a.EnumC0055a.c) {
                    this.g = a.EnumC0055a.b;
                    this.h = (th.getMessage() == null || !th.getMessage().equals("read = -1, end of stream !")) ? th.toString() : "end of stream";
                }
            }
        }
        this.f = true;
        com.igexin.b.a.c.a.a("GS-R|finish ~~~~~~", new Object[0]);
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return -2035;
    }

    @Override // com.igexin.b.a.b.a.a.a
    public final void c_() {
        boolean z = this.i;
        boolean z2 = this.f;
        this.i = false;
        this.g = a.EnumC0055a.c;
    }
}
