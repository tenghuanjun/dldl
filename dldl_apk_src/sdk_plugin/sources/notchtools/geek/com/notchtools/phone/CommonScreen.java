package notchtools.geek.com.notchtools.phone;

import android.app.Activity;
import android.view.Window;
import notchtools.geek.com.notchtools.core.AbsNotchScreenSupport;
import notchtools.geek.com.notchtools.core.OnNotchCallBack;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class CommonScreen extends AbsNotchScreenSupport {
    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public int getNotchHeight(Window window) {
        return 0;
    }

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public boolean isNotchScreen(Window window) {
        return false;
    }

    @Override // notchtools.geek.com.notchtools.core.AbsNotchScreenSupport, notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenDontUseStatus(Activity activity, OnNotchCallBack onNotchCallBack) {
        super.fullScreenDontUseStatus(activity, onNotchCallBack);
    }

    @Override // notchtools.geek.com.notchtools.core.AbsNotchScreenSupport, notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenUseStatus(Activity activity, OnNotchCallBack onNotchCallBack) {
        super.fullScreenUseStatus(activity, onNotchCallBack);
    }
}
