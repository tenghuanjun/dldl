package com.nirvana.tools.logger.executor;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public abstract class AbstractSafeRunnable implements Runnable {
    @Override // java.lang.Runnable
    public void run() {
        try {
            safeRun();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public abstract void safeRun();
}
