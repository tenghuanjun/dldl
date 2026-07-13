package com.lzy.okserver.task;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class PriorityRunnable extends PriorityObject<Runnable> implements Runnable {
    public PriorityRunnable(int i, Runnable runnable) {
        super(i, runnable);
    }

    @Override // java.lang.Runnable
    public void run() {
        ((Runnable) this.obj).run();
    }
}
