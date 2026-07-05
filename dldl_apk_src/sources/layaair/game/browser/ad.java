package layaair.game.browser;

import android.widget.EditText;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class ad implements Runnable {
    private /* synthetic */ boolean a;
    private /* synthetic */ b b;

    ad(b bVar, boolean z) {
        this.b = bVar;
        this.a = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        EditText editText;
        int i;
        this.b.a.setSingleLine(!this.a);
        if (this.a) {
            editText = this.b.a;
            i = 48;
        } else {
            editText = this.b.a;
            i = 16;
        }
        editText.setGravity(i);
    }
}
