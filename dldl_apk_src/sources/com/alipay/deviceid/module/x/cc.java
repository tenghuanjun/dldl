package com.alipay.deviceid.module.x;

import android.os.Process;
import java.util.LinkedList;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class cc {
    private static cc a = new cc();
    private Thread b = null;
    private LinkedList<Runnable> c = new LinkedList<>();

    public static cc a() {
        return a;
    }

    static /* synthetic */ Thread b(cc ccVar) {
        ccVar.b = null;
        return null;
    }

    public final synchronized void a(Runnable runnable) {
        this.c.add(runnable);
        if (this.b == null) {
            Thread thread = new Thread(new Runnable() { // from class: com.alipay.deviceid.module.x.cc.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        Process.setThreadPriority(0);
                        while (!cc.this.c.isEmpty()) {
                            Runnable runnable2 = (Runnable) cc.this.c.pollFirst();
                            if (runnable2 != null) {
                                runnable2.run();
                            }
                        }
                    } catch (Exception unused) {
                    } catch (Throwable th) {
                        cc.b(cc.this);
                        throw th;
                    }
                    cc.b(cc.this);
                }
            });
            this.b = thread;
            thread.start();
        }
    }
}
