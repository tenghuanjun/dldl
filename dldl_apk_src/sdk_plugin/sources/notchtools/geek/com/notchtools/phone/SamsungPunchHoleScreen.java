package notchtools.geek.com.notchtools.phone;

import android.app.Activity;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import com.sqwan.msdk.api.IMUrl;
import java.lang.reflect.Field;
import notchtools.geek.com.notchtools.core.AbsNotchScreenSupport;
import notchtools.geek.com.notchtools.core.OnNotchCallBack;
import notchtools.geek.com.notchtools.helper.NotchStatusBarUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class SamsungPunchHoleScreen extends AbsNotchScreenSupport {
    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public boolean isNotchScreen(Window window) {
        if (window == null) {
            return false;
        }
        try {
            Resources resources = window.getContext().getResources();
            int identifier = resources.getIdentifier("config_mainBuiltInDisplayCutout", "string", IMUrl.OS);
            String string = identifier > 0 ? resources.getString(identifier) : null;
            if (string != null) {
                return !TextUtils.isEmpty(string);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
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
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            try {
                Field field = attributes.getClass().getField("layoutInDisplayCutoutMode");
                field.setAccessible(true);
                field.setInt(attributes, 1);
                window.setAttributes(attributes);
            } catch (Exception e) {
                e.printStackTrace();
            }
            NotchStatusBarUtils.showFakeNotchView(activity.getWindow());
        }
    }

    @Override // notchtools.geek.com.notchtools.core.AbsNotchScreenSupport, notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenDontUseStatusForPortrait(Activity activity, OnNotchCallBack onNotchCallBack) {
        fullScreenDontUseStatus(activity, onNotchCallBack);
    }

    @Override // notchtools.geek.com.notchtools.core.AbsNotchScreenSupport, notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenUseStatus(Activity activity, OnNotchCallBack onNotchCallBack) {
        super.fullScreenUseStatus(activity, onNotchCallBack);
        if (isNotchScreen(activity.getWindow())) {
            Window window = activity.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            try {
                Field field = attributes.getClass().getField("layoutInDisplayCutoutMode");
                field.setAccessible(true);
                field.setInt(attributes, 1);
                window.setAttributes(attributes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
