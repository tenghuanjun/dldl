package layaair.game.browser;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class r implements Runnable {
    private /* synthetic */ b a;

    r(b bVar) {
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.g();
        this.a.a.setSelection(this.a.a.getText().length());
    }
}
