package layaair.game.browser;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class h implements Runnable {
    private /* synthetic */ int a;
    private /* synthetic */ int b;
    private /* synthetic */ ExportJavaFunction c;

    h(ExportJavaFunction exportJavaFunction, int i, int i2) {
        this.c = exportJavaFunction;
        this.a = i;
        this.b = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.c.m_pEngine.setResolution(this.a, this.b);
    }
}
