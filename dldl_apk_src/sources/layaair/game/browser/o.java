package layaair.game.browser;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class o implements Runnable {
    private /* synthetic */ boolean a;

    o(ExportJavaFunction exportJavaFunction, boolean z) {
        this.a = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ExportJavaFunction.GetInstance().m_pEngine.game_showAssistantTouch(this.a);
    }
}
