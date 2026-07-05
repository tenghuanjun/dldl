package com.sy37sdk.account.floatview;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ScreenOrientationHelper {
    public static final int ORIENTATION_TYPE_0 = 0;
    public static final int ORIENTATION_TYPE_180 = 180;
    public static final int ORIENTATION_TYPE_270 = 270;
    public static final int ORIENTATION_TYPE_90 = 90;
    public static int currentType;
    public static List<ScreenOrientationChangeListener> listeners = new ArrayList();
    private static OrientationEventListener mOrientationEventListener;

    interface ScreenOrientationChangeListener {
        void onChange(int i);
    }

    public static void initOrAdd(final Context context, ScreenOrientationChangeListener screenOrientationChangeListener) {
        listeners.add(screenOrientationChangeListener);
        if (mOrientationEventListener == null) {
            mOrientationEventListener = new OrientationEventListener(context) { // from class: com.sy37sdk.account.floatview.ScreenOrientationHelper.1
                @Override // android.view.OrientationEventListener
                public void onOrientationChanged(int i) {
                    if (i > 340 || i < 20) {
                        if (ScreenOrientationHelper.currentType != 0 && ScreenOrientationHelper.getScreenRotation(context) == 0) {
                            Iterator<ScreenOrientationChangeListener> it = ScreenOrientationHelper.listeners.iterator();
                            while (it.hasNext()) {
                                it.next().onChange(0);
                            }
                            ScreenOrientationHelper.currentType = 0;
                            return;
                        }
                        return;
                    }
                    if (i > 70 && i < 110) {
                        if (ScreenOrientationHelper.currentType != 90 && ScreenOrientationHelper.getScreenRotation(context) == 3) {
                            Iterator<ScreenOrientationChangeListener> it2 = ScreenOrientationHelper.listeners.iterator();
                            while (it2.hasNext()) {
                                it2.next().onChange(90);
                            }
                            ScreenOrientationHelper.currentType = 90;
                            return;
                        }
                        return;
                    }
                    if (i > 160 && i < 200) {
                        if (ScreenOrientationHelper.currentType != 180 && ScreenOrientationHelper.getScreenRotation(context) == 2) {
                            Iterator<ScreenOrientationChangeListener> it3 = ScreenOrientationHelper.listeners.iterator();
                            while (it3.hasNext()) {
                                it3.next().onChange(180);
                            }
                            ScreenOrientationHelper.currentType = 180;
                            return;
                        }
                        return;
                    }
                    if (i <= 250 || i >= 290 || ScreenOrientationHelper.currentType == 270 || ScreenOrientationHelper.getScreenRotation(context) != 1) {
                        return;
                    }
                    Iterator<ScreenOrientationChangeListener> it4 = ScreenOrientationHelper.listeners.iterator();
                    while (it4.hasNext()) {
                        it4.next().onChange(270);
                    }
                    ScreenOrientationHelper.currentType = 270;
                }
            };
            register();
        }
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
