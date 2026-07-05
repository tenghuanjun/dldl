package com.bytedance.pangle.a;

import com.bytedance.pangle.d.e;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    final CountDownLatch a;
    Throwable b;

    /* JADX INFO: renamed from: com.bytedance.pangle.a.a$a, reason: collision with other inner class name */
    public interface InterfaceC0014a {
        void a();
    }

    private a(InterfaceC0014a[] interfaceC0014aArr) {
        this.a = new CountDownLatch(interfaceC0014aArr.length);
        for (final InterfaceC0014a interfaceC0014a : interfaceC0014aArr) {
            e.a(new Runnable() { // from class: com.bytedance.pangle.a.a.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        interfaceC0014a.a();
                    } catch (Throwable th) {
                        a.this.b = th;
                    }
                    a.this.a.countDown();
                }
            });
        }
    }

    public static void a(InterfaceC0014a... interfaceC0014aArr) throws Throwable {
        a aVar = new a(interfaceC0014aArr);
        try {
            aVar.a.await();
            Throwable th = aVar.b;
            if (th != null) {
                throw th;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
