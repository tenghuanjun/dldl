package notchtools.geek.com.notchtools.helper;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.WindowManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ScreenOrientationHelper {
    public static final int ORIENTATION_TYPE_0 = 0;
    public static final int ORIENTATION_TYPE_180 = 180;
    public static final int ORIENTATION_TYPE_270 = 270;
    public static final int ORIENTATION_TYPE_90 = 90;
    public static int currentType;
    private static OrientationEventListener mOrientationEventListener;
    private static ScreenOrientationChangeListener mScreenOrientationChangeListener;

    interface ScreenOrientationChangeListener {
        void onChange(int i);
    }

    public static void init(final Context context, ScreenOrientationChangeListener screenOrientationChangeListener) {
        mScreenOrientationChangeListener = screenOrientationChangeListener;
        mOrientationEventListener = new OrientationEventListener(context) { // from class: notchtools.geek.com.notchtools.helper.ScreenOrientationHelper.1
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i) {
                if (ScreenOrientationHelper.mScreenOrientationChangeListener == null) {
                    return;
                }
                if (i > 340 || i < 20) {
                    if (ScreenOrientationHelper.currentType != 0 && ScreenOrientationHelper.getScreenRotation(context) == 0) {
                        ScreenOrientationHelper.mScreenOrientationChangeListener.onChange(0);
                        ScreenOrientationHelper.currentType = 0;
                        return;
                    }
                    return;
                }
                if (i > 70 && i < 110) {
                    if (ScreenOrientationHelper.currentType != 90 && ScreenOrientationHelper.getScreenRotation(context) == 3) {
                        ScreenOrientationHelper.mScreenOrientationChangeListener.onChange(90);
                        ScreenOrientationHelper.currentType = 90;
                        return;
                    }
                    return;
                }
                if (i > 160 && i < 200) {
                    if (ScreenOrientationHelper.currentType != 180 && ScreenOrientationHelper.getScreenRotation(context) == 2) {
                        ScreenOrientationHelper.mScreenOrientationChangeListener.onChange(180);
                        ScreenOrientationHelper.currentType = 180;
                        return;
                    }
                    return;
                }
                if (i <= 250 || i >= 290 || ScreenOrientationHelper.currentType == 270 || ScreenOrientationHelper.getScreenRotation(context) != 1) {
                    return;
                }
                ScreenOrientationHelper.mScreenOrientationChangeListener.onChange(270);
                ScreenOrientationHelper.currentType = 270;
            }
        };
        register();
    }

    public static int getOrientationType(Context context) {
        if (currentType == 0) {
            int screenRotation = getScreenRotation(context);
            if (screenRotation == 0) {
                currentType = 0;
            } else if (screenRotation == 1) {
                currentType = 270;
            } else if (screenRotation == 2) {
                currentType = 180;
            } else if (screenRotation == 3) {
                currentType = 90;
            }
        }
        return currentType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getScreenRotation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            return windowManager.getDefaultDisplay().getRotation();
        }
        return 0;
    }

    public static void register() {
        OrientationEventListener orientationEventListener = mOrientationEventListener;
        if (orientationEventListener != null) {
            orientationEventListener.enable();
        }
    }

    public static void unRegister() {
        OrientationEventListener orientationEventListener = mOrientationEventListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
    }
}
