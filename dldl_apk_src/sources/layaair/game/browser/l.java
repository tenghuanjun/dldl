package layaair.game.browser;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class l implements Runnable {
    private /* synthetic */ ExportJavaFunction a;

    l(ExportJavaFunction exportJavaFunction) {
        this.a = exportJavaFunction;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.m_pEngine.getWebView().c();
    }
}
