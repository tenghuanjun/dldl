package layaair.game.browser;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class g implements Runnable {
    private /* synthetic */ String a;

    g(ExportJavaFunction exportJavaFunction, String str) {
        this.a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ExportJavaFunction exportJavaFunctionGetInstance = ExportJavaFunction.GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance.m_pEngine.showMessage(this.a);
        }
    }
}
