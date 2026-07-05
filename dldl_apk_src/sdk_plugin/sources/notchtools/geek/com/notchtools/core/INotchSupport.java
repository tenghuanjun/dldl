package notchtools.geek.com.notchtools.core;

import android.app.Activity;
import android.view.Window;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public interface INotchSupport {
    void fullScreenDontUseStatus(Activity activity, OnNotchCallBack onNotchCallBack);

    void fullScreenDontUseStatusForLandscape(Activity activity, OnNotchCallBack onNotchCallBack);

    void fullScreenDontUseStatusForPortrait(Activity activity, OnNotchCallBack onNotchCallBack);

    void fullScreenUseStatus(Activity activity, OnNotchCallBack onNotchCallBack);

    int getNotchHeight(Window window);

    int getStatusHeight(Window window);

    boolean ignoreNotchScreen(Window window, int i, int i2);

    boolean isNotchScreen(Window window);

    void translucentStatusBar(Activity activity);

    void translucentStatusBar(Activity activity, OnNotchCallBack onNotchCallBack);
}
