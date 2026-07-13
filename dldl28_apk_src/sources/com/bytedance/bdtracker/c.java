package com.bytedance.bdtracker;

import com.bytedance.bdtracker.b;

/* JADX INFO: loaded from: classes2.dex */
public final class c implements b.c {

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f222a;

        public a(c cVar, d dVar) {
            this.f222a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            d dVar = this.f222a;
            i1 i1Var = dVar.b() ? null : dVar.q.e;
            if (i1Var == null || i1Var.q || i1Var.f.getBoolean("enter_background_not_send", false)) {
                return;
            }
            this.f222a.flush();
        }
    }

    @Override // com.bytedance.bdtracker.b.c
    public void a(d dVar) {
        w.f333a.execute(new a(this, dVar));
    }
}
