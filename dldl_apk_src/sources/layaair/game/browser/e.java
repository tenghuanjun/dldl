package layaair.game.browser;

import android.util.DisplayMetrics;
import android.view.WindowManager;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class e implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ int b;
    private /* synthetic */ int c;
    private /* synthetic */ int d;
    private /* synthetic */ int e;
    private /* synthetic */ int f;
    private /* synthetic */ ExportJavaFunction g;

    e(ExportJavaFunction exportJavaFunction, String str, int i, int i2, int i3, int i4, int i5) {
        this.g = exportJavaFunction;
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ao aoVar = this.g.m_pEngine.getWebView().a;
        aoVar.e = this.a;
        aoVar.a = this.b;
        aoVar.b = this.c;
        aoVar.c = this.d;
        aoVar.d = this.e;
        aoVar.f = Boolean.valueOf(this.f != 0);
        WindowManager windowManager = (WindowManager) this.g.m_pEngine.getGameContext().getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        if (aoVar.c == 0) {
            aoVar.c = displayMetrics.widthPixels;
        }
        if (aoVar.d == 0) {
            aoVar.d = displayMetrics.heightPixels;
        }
        am webView = this.g.m_pEngine.getWebView();
        if (webView.a != null) {
            webView.a(webView.a.e, webView.a.a, webView.a.b, webView.a.c, webView.a.d);
        }
    }
}
