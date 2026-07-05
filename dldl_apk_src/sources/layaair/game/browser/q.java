package layaair.game.browser;

import layaair.game.conch.LayaConch5;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class q implements Runnable {
    private /* synthetic */ ExportJavaFunction a;

    q(ExportJavaFunction exportJavaFunction) {
        this.a = exportJavaFunction;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LayaConch5.GetInstance().setScreenWakeLock(this.a.m_bScreenWakeLock);
    }
}
