package com.igexin.b.a.b.a.a;

import android.text.TextUtils;
import com.igexin.b.a.b.a.a.a;
import java.util.concurrent.locks.Lock;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class g extends a {
    private static final String P = "GS-W";
    public static final int a = -2036;
    com.igexin.b.a.b.a.a.a.c j;
    protected com.igexin.b.a.b.d k;
    protected i l;

    public g(i iVar, com.igexin.b.a.b.d dVar) {
        super(-2036, dVar);
        this.k = dVar;
        this.l = iVar;
    }

    private void a(com.igexin.b.a.b.a.a.a.c cVar) {
        this.j = cVar;
    }

    @Override // com.igexin.b.a.b.f, com.igexin.b.a.d.f, com.igexin.b.a.d.a.a
    public final void a() {
        super.a();
        com.igexin.b.a.c.a.a("GS-W|wt dispose", new Object[0]);
        if (this.j != null) {
            if (this.g != a.EnumC0055a.b) {
                this.j.a();
            } else if (!TextUtils.isEmpty(this.h)) {
                this.j.a(new Exception(this.h));
            }
        }
        this.j = null;
    }

    @Override // com.igexin.b.a.d.f, com.igexin.b.a.d.a.f
    public final void b_() throws Exception {
        Lock lock;
        super.b_();
        Thread threadCurrentThread = Thread.currentThread();
        com.igexin.b.a.c.a.a("GS-W|" + threadCurrentThread + " running", new Object[0]);
        d dVarA = d.a();
        while (this.i && !threadCurrentThread.isInterrupted() && !this.f) {
            try {
                try {
                    dVarA.f.lock();
                    if (dVarA.j.isEmpty() && this.i) {
                        dVarA.g.await();
                    }
                    f fVarPoll = dVarA.j.poll();
                    if (fVarPoll != null && this.i && this.l != null && this.i) {
                        this.g = a.EnumC0055a.a;
                        if (this.j != null && this.i) {
                            this.j.a(fVarPoll);
                        }
                        if (fVarPoll.d != null) {
                            i iVar = this.l;
                            byte[] bArr = (byte[]) this.k.a(null, fVarPoll.d);
                            iVar.a.write(bArr, 0, bArr.length);
                            iVar.a.flush();
                        }
                        if (fVarPoll.d != null) {
                            com.igexin.b.a.c.a.a("GS-W|" + fVarPoll.d.getClass().getName() + " -- send success", new Object[0]);
                        }
                    }
                    lock = dVarA.f;
                } catch (Throwable th) {
                    this.i = false;
                    if (this.g != a.EnumC0055a.c) {
                        this.g = a.EnumC0055a.b;
                        this.h = th.toString();
                    }
                    try {
                        lock = dVarA.f;
                    } catch (Exception unused) {
                    }
                }
                lock.unlock();
            } catch (Throwable th2) {
                try {
                    dVarA.f.unlock();
                } catch (Exception unused2) {
                }
                throw th2;
            }
        }
        this.f = true;
        com.igexin.b.a.c.a.a("GS-W|finish ~~~~~~", new Object[0]);
    }

    @Override // com.igexin.b.a.d.a.e
    public final int c() {
        return -2036;
    }

    @Override // com.igexin.b.a.b.a.a.a
    public final void c_() {
        boolean z = this.i;
        boolean z2 = this.f;
        this.i = false;
        this.g = a.EnumC0055a.c;
        d dVarA = d.a();
        try {
            try {
                if (!this.f) {
                    dVarA.f.lock();
                    dVarA.g.signalAll();
                }
                try {
                    dVarA.f.unlock();
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
            }
        } catch (Exception unused3) {
            dVarA.f.unlock();
        } catch (Throwable th) {
            try {
                dVarA.f.unlock();
            } catch (Exception unused4) {
            }
            throw th;
        }
    }
}
