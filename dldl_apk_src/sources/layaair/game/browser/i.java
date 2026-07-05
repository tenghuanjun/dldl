package layaair.game.browser;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class i implements Runnable {
    private int a;
    private int b;
    private int c;

    public i(ExportJavaFunction exportJavaFunction, int i, int i2, int i3) {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ConchJNI.onRunCmd(this.a, this.b, this.c);
    }
}
