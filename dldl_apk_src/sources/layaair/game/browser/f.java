package layaair.game.browser;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class f implements Runnable {
    f(ExportJavaFunction exportJavaFunction) {
    }

    @Override // java.lang.Runnable
    public final void run() {
        ConchJNI.reloadJS();
    }
}
