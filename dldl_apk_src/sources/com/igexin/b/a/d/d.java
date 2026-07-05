package com.igexin.b.a.d;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d {
    private final ConcurrentLinkedQueue<com.igexin.b.a.d.a.e> a = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<com.igexin.b.a.d.a.e> b = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<com.igexin.b.a.d.a.e> c = this.a;

    private synchronized Iterator<com.igexin.b.a.d.a.e> e() {
        return this.c.iterator();
    }

    public final synchronized void a() {
        this.c = this.a;
    }

    public final synchronized void a(com.igexin.b.a.d.a.e eVar) {
        this.c.offer(eVar);
    }

    public final synchronized void b() {
        this.c = this.b;
        this.c.addAll(this.a);
        this.a.clear();
    }

    public final synchronized boolean c() {
        return this.c.isEmpty();
    }

    public final synchronized com.igexin.b.a.d.a.e d() {
        return this.c.poll();
    }
}
