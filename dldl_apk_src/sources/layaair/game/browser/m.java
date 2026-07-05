package layaair.game.browser;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class m implements Runnable {
    private /* synthetic */ ExportJavaFunction a;

    m(ExportJavaFunction exportJavaFunction) {
        this.a = exportJavaFunction;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.m_pEngine.game_plugin_exitGame();
    }
}
