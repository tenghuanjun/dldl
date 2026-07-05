package layaair.game.wrapper;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import java.util.HashMap;
import layaair.game.conch.ILayaEventListener;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public interface ILayaLibWrapper {
    void _enableOnLayout(boolean z);

    void initEngine(Activity activity);

    void onActivityResult(int i, int i2, Intent intent);

    void onDestroy();

    void onNewIntent(Intent intent);

    void onPause();

    void onRestart();

    void onResume();

    void onStop();

    void setGameUrl(String str);

    void setInterceptKey(boolean z);

    void setLayaEventListener(ILayaEventListener iLayaEventListener);

    void setLoadingView(View view);

    void setLocalizable(boolean z);

    void setOptions(HashMap map);

    void setResolution(int i, int i2);

    void startGame();
}
