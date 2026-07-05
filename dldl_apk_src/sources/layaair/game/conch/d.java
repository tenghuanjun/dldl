package layaair.game.conch;

import android.content.DialogInterface;
import layaair.game.browser.ConchJNI;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class d implements DialogInterface.OnClickListener {
    private /* synthetic */ int a;
    private /* synthetic */ LayaConch5 b;

    d(LayaConch5 layaConch5, int i) {
        this.b = layaConch5;
        this.a = i;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        int i2 = this.a;
        if (i2 == 1) {
            ConchJNI.alertCallback();
        } else if (i2 == 2) {
            this.b.game_plugin_exitGame();
        }
    }
}
