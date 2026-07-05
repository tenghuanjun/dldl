package layaair.game.utility;

import android.media.MediaPlayer;
import layaair.game.browser.ConchJNI;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
final class a implements MediaPlayer.OnCompletionListener {
    a() {
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
        ConchJNI.audioMusicPlayEnd();
    }
}
