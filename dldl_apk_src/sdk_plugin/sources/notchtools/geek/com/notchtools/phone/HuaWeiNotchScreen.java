package notchtools.geek.com.notchtools.phone;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import java.lang.reflect.InvocationTargetException;
import notchtools.geek.com.notchtools.core.AbsNotchScreenSupport;
import notchtools.geek.com.notchtools.core.OnNotchCallBack;
import notchtools.geek.com.notchtools.helper.NotchStatusBarUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class HuaWeiNotchScreen extends AbsNotchScreenSupport {
    private static final String DISPLAY_NOTCH_STATUS = "display_notch_status";
    public static final int FLAG_NOTCH_SUPPORT = 65536;
    private static final String TAG = HuaWeiNotchScreen.class.getSimpleName();

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public boolean isNotchScreen(Window window) {
        try {
            try {
                try {
                    Class<?> clsLoadClass = window.getContext().getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
                    return ((Boolean) clsLoadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(clsLoadClass, new Object[0])).booleanValue();
                } catch (Exception unused) {
                    Log.d(TAG, "hasNotchInScreen Exception");
                    return false;
                }
            } catch (ClassNotFoundException unused2) {
                Log.d(TAG, "hasNotchInScreen ClassNotFoundException");
                return false;
            } catch (NoSuchMethodException unused3) {
                Log.d(TAG, "hasNotchInScreen NoSuchMethodException");
                return false;
            }
        } catch (Throwable unused4) {
            return false;
        }
    }

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public int getNotchHeight(Window window) {
        if (!isNotchScreen(window)) {
            return 0;
        }
        int[] iArr = {0, 0};
        try {
            Class<?> clsLoadClass = window.getContext().getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            return ((int[]) clsLoadClass.getMethod("getNotchSize", new Class[0]).invoke(clsLoadClass, new Object[0]))[1];
        } catch (ClassNotFoundException unused) {
            return iArr[1];
        } catch (NoSuchMethodException unused2) {
            return iArr[1];
        } catch (Exception unused3) {
            return iArr[1];
        } catch (Throwable unused4) {
            return iArr[1];
        }
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
        if (isNotchScreen(activity.getWindow())) {
            setFullScreenWindowLayoutInDisplayCutout(activity.getWindow());
        }
    }

    public static void setFullScreenWindowLayoutInDisplayCutout(Window window) {
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        try {
            Class<?> cls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            cls.getMethod("addHwFlags", Integer.TYPE).invoke(cls.getConstructor(WindowManager.LayoutParams.class).newInstance(attributes), 65536);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            Log.e("test", "hw add notch screen flag api error");
        } catch (Exception unused2) {
            Log.e("test", "other Exception");
        }
    }

    public static void setNotFullScreenWindowLayoutInDisplayCutout(Window window) {
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        try {
            Class<?> cls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            cls.getMethod("clearHwFlags", Integer.TYPE).invoke(cls.getConstructor(WindowManager.LayoutParams.class).newInstance(attributes), 65536);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            Log.e("test", "hw clear notch screen flag api error");
        } catch (Exception unused2) {
            Log.e("test", "other Exception");
        }
    }

    private boolean isHideNotch(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), DISPLAY_NOTCH_STATUS, 0) == 1;
    }
}
