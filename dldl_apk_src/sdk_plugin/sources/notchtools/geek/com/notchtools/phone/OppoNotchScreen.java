package notchtools.geek.com.notchtools.phone;

import android.app.Activity;
import android.view.Window;
import notchtools.geek.com.notchtools.core.AbsNotchScreenSupport;
import notchtools.geek.com.notchtools.core.OnNotchCallBack;
import notchtools.geek.com.notchtools.helper.NotchStatusBarUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class OppoNotchScreen extends AbsNotchScreenSupport {
    private static final String TAG = OppoNotchScreen.class.getSimpleName();

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public boolean isNotchScreen(Window window) {
        if (window == null) {
            return false;
        }
        return window.getContext().getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public int getNotchHeight(Window window) {
        if (isNotchScreen(window)) {
            return NotchStatusBarUtils.getStatusBarHeight(window.getContext());
        }
        return 0;
    }

    @Override // notchtools.geek.com.notchtools.core.AbsNotchScreenSupport, notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenDontUseStatus(Activity activity, OnNotchCallBack onNotchCallBack) {
        super.fullScreenDontUseStatus(activity, onNotchCallBack);
        if (isNotchScreen(activity.getWindow())) {
            NotchStatusBarUtils.showFakeNotchView(activity.getWindow());
        }
    }

    @Override // notchtools.geek.com.notchtools.core.AbsNotchScreenSupport, notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenDontUseStatusForPortrait(Activity activity, OnNotchCallBack onNotchCallBack) {
        fullScreenDontUseStatus(activity, onNotchCallBack);
    }

    @Override // notchtools.geek.com.notchtools.core.AbsNotchScreenSupport, notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenDontUseStatusForLandscape(Activity activity, OnNotchCallBack onNotchCallBack) {
        super.fullScreenDontUseStatusForLandscape(activity, onNotchCallBack);
    }

    @Override // notchtools.geek.com.notchtools.core.AbsNotchScreenSupport, notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenUseStatus(Activity activity, OnNotchCallBack onNotchCallBack) {
        super.fullScreenUseStatus(activity, onNotchCallBack);
    }
}
