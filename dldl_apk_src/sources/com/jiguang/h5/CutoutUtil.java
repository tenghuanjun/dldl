package com.jiguang.h5;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.google.gson.JsonObject;
import com.jiguang.main.MainActivity;
import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class CutoutUtil {
    private static final String DISPLAY_NOTCH_STATUS = "display_notch_status";
    private static final int NOTCH_IN_SCREEN_HUAWEI_MARK = 65536;
    private static final int NOTCH_IN_SCREEN_VOIO_MARK = 32;
    private static final int NOTCH_IN_SCREEN_XIAOMI_DEFULT = 256;
    private static final int NOTCH_IN_SCREEN_XIAOMI_MARK = 512;
    private static final int NOTCH_TYPE_AndroidP = 99;
    private static final int NOTCH_TYPE_HUIWEI = 1;
    private static final int NOTCH_TYPE_LIANXIANG = 6;
    private static final int NOTCH_TYPE_MEIZU = 7;
    private static final int NOTCH_TYPE_OPPO = 2;
    private static final int NOTCH_TYPE_SANXING = 5;
    private static final int NOTCH_TYPE_VIVO = 3;
    private static final int NOTCH_TYPE_XIAOMI = 4;
    private static final int NOTCH_TYPE_YIJIA = 8;
    private static final int ROUNDED_IN_SCREEN_VOIO_MARK = 8;
    private static int sAllowDisplayToCutout = -1;

    private static boolean hasCutout_Yijia(Context context) {
        return false;
    }

    private static void setDisplayCutout_Vivo(Window window, boolean z) {
        if (window == null) {
        }
    }

    private static int getIsNotchSwitchOpen(Context context) {
        int i = sAllowDisplayToCutout;
        if (i == 1) {
            return Settings.Secure.getInt(context.getContentResolver(), DISPLAY_NOTCH_STATUS, 0);
        }
        if (i == 4) {
            return Settings.Global.getInt(context.getContentResolver(), "force_black", 0);
        }
        if (i == 7) {
            return Settings.Global.getInt(context.getContentResolver(), "mz_fringe_hide", 0);
        }
        return 2;
    }

    private static boolean useDisplayNotchStatus() {
        int i = sAllowDisplayToCutout;
        return i == 1 || i == 4;
    }

    @SuppressLint({"PrivateApi"})
    private static boolean checkDeviceHasNavigationBar() {
        Resources resources = MainActivity.ma.getResources();
        int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        boolean z = identifier > 0 ? resources.getBoolean(identifier) : false;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class).invoke(cls, "qemu.hw.mainkeys");
            if ("1".equals(str)) {
                return false;
            }
            if ("0".equals(str)) {
                return true;
            }
            return z;
        } catch (Exception e) {
            Log.d("test----", "e=" + e);
            return z;
        }
    }

    public static void setDislay2Cutout() {
        allowDisplayToCutout();
        if (sAllowDisplayToCutout <= 0) {
            return;
        }
        MainActivity mainActivity = MainActivity.ma;
        Window window = mainActivity.getWindow();
        int isNotchSwitchOpen = getIsNotchSwitchOpen(mainActivity);
        boolean z = isNotchSwitchOpen == 0 || (isNotchSwitchOpen == 2 && !useDisplayNotchStatus());
        Point screenRealSize = getScreenRealSize();
        int i = screenRealSize.x > screenRealSize.y ? screenRealSize.x : screenRealSize.y;
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("notchStatus", Integer.valueOf(isNotchSwitchOpen));
        jsonObject.addProperty("realHeightSize", Integer.valueOf(i));
        SdkMgr.Call("androidRealHeight", jsonObject);
        if (Build.VERSION.SDK_INT >= 28) {
            int i2 = sAllowDisplayToCutout;
            int i3 = DownloadErrorCode.ERROR_SAVE_PATH_EMPTY;
            if (i2 == 1) {
                View decorView = window.getDecorView();
                if (!z) {
                    i3 = 0;
                }
                decorView.setSystemUiVisibility(i3);
            } else if (!checkDeviceHasNavigationBar()) {
                View decorView2 = window.getDecorView();
                if (!z) {
                    i3 = 0;
                }
                decorView2.setSystemUiVisibility(i3);
            } else {
                window.setNavigationBarColor(0);
                if (z) {
                    window.getDecorView().setSystemUiVisibility(5894);
                } else {
                    window.getDecorView().setSystemUiVisibility(4866);
                }
            }
            if (z) {
                window.addFlags(67108864);
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.layoutInDisplayCutoutMode = 1;
                window.setAttributes(attributes);
                return;
            }
            return;
        }
        int i4 = sAllowDisplayToCutout;
        if (i4 == 1) {
            setDisplayCutout_Huawei(window, z);
        } else {
            if (i4 == 2 || i4 == 3 || i4 != 4) {
                return;
            }
            setDisplayCutout_Xiaomi(window, z);
        }
    }

    private static void setDisplayCutout_Huawei(Window window, boolean z) {
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        try {
            Class<?> cls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            cls.getMethod(!z ? "clearHwFlags" : "addHwFlags", Integer.TYPE).invoke(cls.getConstructor(WindowManager.LayoutParams.class).newInstance(attributes), 65536);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            Log.e("test", "hw add notch screen flag api error");
        } catch (Exception unused2) {
            Log.e("test", "other Exception");
        }
    }

    private static void setDisplayCutout_Xiaomi(Window window, boolean z) {
        if (window == null) {
            return;
        }
        try {
            Window.class.getMethod(z ? "addExtraFlags" : "clearExtraFlags", Integer.TYPE).invoke(window, 768);
        } catch (Exception unused) {
            Log.e("test", "addExtraFlags not found.");
        }
    }

    private static void allowDisplayToCutout() {
        MainActivity mainActivity = MainActivity.ma;
        if (sAllowDisplayToCutout != -1) {
            return;
        }
        hasCutout_AndroidP();
        if (hasCutout_Huawei(mainActivity)) {
            sAllowDisplayToCutout = 1;
            return;
        }
        if (hasCutout_OPPO(mainActivity)) {
            sAllowDisplayToCutout = 2;
            return;
        }
        if (hasCutout_VIVO(mainActivity)) {
            sAllowDisplayToCutout = 3;
            return;
        }
        if (hasCutout_XIAOMI(mainActivity)) {
            sAllowDisplayToCutout = 4;
            return;
        }
        if (hasCutout_SanXing(mainActivity)) {
            sAllowDisplayToCutout = 5;
            return;
        }
        if (hasCutout_Lianxiang(mainActivity)) {
            sAllowDisplayToCutout = 6;
            return;
        }
        if (hasCutout_Meizu(mainActivity)) {
            sAllowDisplayToCutout = 7;
        } else if (hasCutout_Yijia(mainActivity)) {
            sAllowDisplayToCutout = 8;
        } else {
            sAllowDisplayToCutout = 0;
        }
    }

    private static boolean hasCutout_Huawei(Context context) {
        if (!Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
            return false;
        }
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
            if (clsLoadClass != null) {
                return ((Boolean) clsLoadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(clsLoadClass, new Object[0])).booleanValue();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean hasCutout_OPPO(Context context) {
        if (Build.MANUFACTURER.equalsIgnoreCase("oppo")) {
            return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
        }
        return false;
    }

    private static boolean hasCutout_VIVO(Context context) {
        if (!Build.MANUFACTURER.equalsIgnoreCase("vivo")) {
            return false;
        }
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.util.FtFeature");
            if (clsLoadClass == null) {
                return false;
            }
            Method method = clsLoadClass.getMethod("isFeatureSupport", Integer.TYPE);
            if (!((Boolean) method.invoke(clsLoadClass, 32)).booleanValue()) {
                if (!((Boolean) method.invoke(clsLoadClass, 8)).booleanValue()) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean hasCutout_XIAOMI(Context context) {
        if (!Build.MANUFACTURER.equalsIgnoreCase("xiaomi")) {
            return false;
        }
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return ((Integer) clsLoadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(clsLoadClass, "ro.miui.notch", 0)).intValue() == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean hasCutout_SanXing(Context context) {
        try {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_mainBuiltInDisplayCutout", "string", "android");
            String string = identifier > 0 ? resources.getString(identifier) : null;
            if (string != null) {
                return !TextUtils.isEmpty(string);
            }
            return false;
        } catch (Exception unused) {
            Log.e("test---", "getFeature Exception");
            return false;
        }
    }

    private static boolean hasCutout_Meizu(Context context) {
        try {
            return ((Boolean) Class.forName("flyme.config.FlymeFeature").getDeclaredField("IS_FRINGE_DEVICE").get(null)).booleanValue();
        } catch (Exception e) {
            Log.e("test---", "isSupportNotch:\n" + e.toString());
            return false;
        }
    }

    private static boolean hasCutout_Lianxiang(Context context) {
        int identifier = context.getResources().getIdentifier("config_screen_has_notch", "bool", "android");
        if (identifier > 0) {
            return context.getResources().getBoolean(identifier);
        }
        return false;
    }

    @TargetApi(28)
    private static void hasCutout_AndroidP() {
        if (Build.VERSION.SDK_INT < 28) {
            return;
        }
        final View decorView = MainActivity.ma.getWindow().getDecorView();
        decorView.post(new Runnable() { // from class: com.jiguang.h5.CutoutUtil.1
            @Override // java.lang.Runnable
            public void run() {
                List<Rect> boundingRects;
                DisplayCutout displayCutout = decorView.getRootWindowInsets().getDisplayCutout();
                if (displayCutout == null || (boundingRects = displayCutout.getBoundingRects()) == null || boundingRects.size() == 0 || CutoutUtil.sAllowDisplayToCutout > 0) {
                    return;
                }
                int unused = CutoutUtil.sAllowDisplayToCutout = 99;
                CutoutUtil.setDislay2Cutout();
            }
        });
    }

    private static Point getScreenRealSize() {
        Point point;
        Exception e;
        try {
            point = new Point();
        } catch (Exception e2) {
            point = null;
            e = e2;
        }
        try {
            ((WindowManager) MainActivity.ma.getSystemService("window")).getDefaultDisplay().getRealSize(point);
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
        }
        return point;
    }
}
