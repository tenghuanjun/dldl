package layaair.game.browser;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class z implements View.OnKeyListener {
    private /* synthetic */ b a;

    z(b bVar) {
        this.a = bVar;
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 0) {
            return false;
        }
        Log.i("LayaBox", "onKey = " + i);
        this.a.f(false);
        return true;
    }
}
