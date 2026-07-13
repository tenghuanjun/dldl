package a.a.a.b;

import a.a.a.b.e;

/* JADX INFO: compiled from: TurboSDK.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements a.a.a.a {
    public j(e eVar) {
    }

    @Override // a.a.a.a
    public void a(int i, int i2) {
        double d = i2;
        a.a.a.d.a aVar = new a.a.a.d.a("BEHAVIOR_POSITIVE");
        aVar.b = d;
        aVar.j = i;
        e.c.INSTANCE.f74a.a(aVar);
    }

    @Override // a.a.a.a
    public void b(int i) {
        a.a.a.d.a aVar = new a.a.a.d.a("BEHAVIOR_TUNING");
        aVar.j = i;
        e.c.INSTANCE.f74a.a(aVar);
    }

    @Override // a.a.a.a
    public void a(int i) {
        a.a.a.d.a aVar = new a.a.a.d.a("BEHAVIOR_NEGATIVE");
        aVar.j = i;
        e.c.INSTANCE.f74a.a(aVar);
    }
}
