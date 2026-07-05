package layaair.game.browser;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class ac implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ b b;

    ac(b bVar, int i) {
        this.b = bVar;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.b.l();
        this.b.m();
        int length = this.b.a.getText().length();
        int i = this.a;
        if (i < 0) {
            this.b.a.setSelection(0);
        } else if (i > length) {
            this.b.a.setSelection(length);
        } else {
            this.b.a.setSelection(this.a);
        }
    }
}
