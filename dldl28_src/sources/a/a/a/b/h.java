package a.a.a.b;

import a.a.a.d.c;

/* JADX INFO: compiled from: TurboSDK.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements a.a.a.e.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e f77a;

    public h(e eVar) {
        this.f77a = eVar;
    }

    @Override // a.a.a.e.e
    public void a(int i, String str) {
    }

    @Override // a.a.a.e.e
    public void a(a.a.a.d.c cVar) {
        c.b bVar;
        a.a.a.g.b.c("TurboSDK", "request global success");
        if (cVar == null || (bVar = cVar.c) == null) {
            return;
        }
        String str = bVar.f103a;
        e.k = str;
        d.a(this.f77a.c, "ks_global_id", str);
    }
}
