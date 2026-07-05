package notchtools.geek.com.notchtools.phone;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.view.Window;
import com.sqwan.msdk.api.IMUrl;
import java.lang.reflect.Method;
import notchtools.geek.com.notchtools.core.AbsNotchScreenSupport;
import notchtools.geek.com.notchtools.core.OnNotchCallBack;
import notchtools.geek.com.notchtools.helper.NotchStatusBarUtils;
import notchtools.geek.com.notchtools.helper.SystemProperties;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class MiuiNotchScreen extends AbsNotchScreenSupport {
    private static final String TAG = MiuiNotchScreen.class.getSimpleName();

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public boolean isNotchScreen(Window window) {
        return "1".equals(SystemProperties.getInstance().get("ro.miui.notch"));
    }

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public int getNotchHeight(Window window) {
        if (!isNotchScreen(window) || window == null) {
            return 0;
        }
        Context context = window.getContext();
        if (isHideNotch(window.getContext())) {
            return NotchStatusBarUtils.getStatusBarHeight(context);
        }
        return getRealNotchHeight(context);
    }

    private int getRealNotchHeight(Context context) {
        int identifier = context.getResources().getIdentifier("notch_height", "dimen", IMUrl.OS);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return NotchStatusBarUtils.getStatusBarHeight(context);
    }

    @Override // notchtools.geek.com.notchtools.core.AbsNotchScreenSupport, notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenDontUseStatus(Activity activity, OnNotchCallBack onNotchCallBack) {
        fullScreenUseStatus(activity, onNotchCallBack);
        if (isNotchScreen(activity.getWindow())) {
            NotchStatusBarUtils.showFakeNotchView(activity.getWindow());
        }
    }

    @Override // notchtools.geek.com.notchtools.core.AbsNotchScreenSupport, notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenUseStatus(Activity activity, OnNotchCallBack onNotchCallBack) {
        super.fullScreenUseStatus(activity, onNotchCallBack);
        if (Build.VERSION.SDK_INT < 26 || !isNotchScreen(activity.getWindow())) {
            return;
        }
        try {
            Method method = Window.class.getMethod("addExtraFlags", Integer.TYPE);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            method.invoke(activity.getWindow(), 1792);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isHideNotch(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "force_black", 0) == 1;
    }
}
