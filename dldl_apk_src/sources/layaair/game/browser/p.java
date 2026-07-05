package layaair.game.browser;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class p implements Runnable {
    private /* synthetic */ ExportJavaFunction a;

    p(ExportJavaFunction exportJavaFunction) {
        this.a = exportJavaFunction;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ExportJavaFunction exportJavaFunctionGetInstance = ExportJavaFunction.GetInstance();
        if (exportJavaFunctionGetInstance != null) {
            exportJavaFunctionGetInstance.m_pEngine.alertJS("提示", this.a.m_sAlertMsg, this.a.m_nAlertCallbackType);
        }
    }
}
