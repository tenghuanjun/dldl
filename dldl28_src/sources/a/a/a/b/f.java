package a.a.a.b;

/* JADX INFO: compiled from: TurboSDK.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e f75a;

    public f(e eVar) {
        this.f75a = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        e eVar = this.f75a;
        if (eVar.a()) {
            eVar.h.postDelayed(new g(eVar), 10000L);
        } else {
            eVar.d();
        }
    }
}
