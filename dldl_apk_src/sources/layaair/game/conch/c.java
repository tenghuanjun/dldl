package layaair.game.conch;

import android.view.MotionEvent;
import android.view.View;
import layaair.game.browser.ConchJNI;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class c implements View.OnGenericMotionListener {
    c(LayaConch5 layaConch5) {
    }

    @Override // android.view.View.OnGenericMotionListener
    public final boolean onGenericMotion(View view, MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 16777232) == 16777232 && motionEvent.getAction() == 2) {
            ConchJNI.handleJoystickEvent(motionEvent.getAxisValue(0), motionEvent.getAxisValue(1), motionEvent.getAxisValue(11), motionEvent.getAxisValue(14), motionEvent.getAxisValue(17), motionEvent.getAxisValue(18));
        }
        return false;
    }
}
