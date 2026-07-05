package com.nirvana.tools.logger.executor;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
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
