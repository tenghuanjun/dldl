package layaair.game.conch;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.widget.AbsoluteLayout;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public interface ILayaGameEgine {
    void _enableOnLayout(boolean z);

    AbsoluteLayout getAbsLayout();

    void onActivityResult(int i, int i2, Intent intent);

    void onCreate();

    void onDestroy();

    void onKeyEvent(String str, int i);

    void onNewIntent(Intent intent);

    void onPause();

    void onRestart();

    void onResume();

    void onStop();

    void setAppCacheDir(String str);

    void setAssetInfo(AssetManager assetManager);

    void setContext(Context context);

    void setDownloadThreadNum(int i);

    void setExpansionZipDir(String str, String str2);

    void setGameUrl(String str);

    void setInterceptKey(boolean z);

    void setIsPlugin(boolean z);

    void setJarFile(String str);

    void setLayaEventListener(ILayaEventListener iLayaEventListener);

    void setLocalizable(boolean z);

    void setResolution(int i, int i2);

    void setSoFile(String str);

    void setSoPath(String str);
}
