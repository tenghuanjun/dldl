package com.bytedance.http.b;

import com.bytedance.http.Call;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f391a;

    protected d(String str) {
        this.f391a = str;
    }

    protected abstract void a();

    public abstract Call b();

    @Override // java.lang.Runnable
    public void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f391a);
        try {
            a();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
