package layaair.game.browser;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class ae implements Runnable {
    private /* synthetic */ boolean a;
    private /* synthetic */ b b;

    ae(b bVar, boolean z) {
        this.b = bVar;
        this.a = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.b.b = this.a;
    }
}
