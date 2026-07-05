package com.huya.mtp.utils;

import android.content.Context;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.utils.egl.EglCore;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DeviceHelper {
    private static final String KEY_GUP = "key_gup";
    private static final String TAG = "DeviceHelper";
    public static final String UNKNOWN = "unknown";

    public static String getCpuInfo() {
        String[] strArrSplit;
        try {
            String line = new BufferedReader(new FileReader("/proc/cpuinfo")).readLine();
            return (line == null || (strArrSplit = line.split(":\\s+", 2)) == null || strArrSplit.length <= 1) ? "unknown" : strArrSplit[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "unknown";
        } catch (IOException e2) {
            e2.printStackTrace();
            return "unknown";
        }
    }

    public static String getGpuInfo(Context context) {
        if (Build.VERSION.SDK_INT < 17) {
            return "unknown";
        }
        String string = Config.getInstance(context).getString(KEY_GUP, "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        try {
            EglCore eglCore = new EglCore(null, 0);
            EGLSurface eGLSurfaceCreateOffscreenSurface = eglCore.createOffscreenSurface(640, 480);
            eglCore.makeCurrent(eGLSurfaceCreateOffscreenSurface);
            String strGlGetString = GLES20.glGetString(7937);
            Config.getInstance(context).setString(KEY_GUP, strGlGetString);
            eglCore.releaseSurface(eGLSurfaceCreateOffscreenSurface);
            eglCore.release();
            return strGlGetString;
        } catch (Exception e) {
            MTPApi.LOGGER.error(TAG, e);
            return "unknown";
        } catch (NoClassDefFoundError e2) {
            MTPApi.LOGGER.error(TAG, e2);
            return "unknown";
        }
    }

    public static int getScreenRotation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        int rotation = windowManager.getDefaultDisplay().getRotation();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (((rotation == 0 || rotation == 2) && i2 > i) || ((rotation == 1 || rotation == 3) && i > i2)) {
            if (rotation != 0) {
                if (rotation != 1) {
                    if (rotation != 2) {
                        if (rotation == 3) {
                            return 8;
                        }
                        MTPApi.LOGGER.error("Unknown screen orientation. Defaulting to portrait.");
                    }
                    return 9;
                }
                return 0;
            }
            return 1;
        }
        if (rotation != 0) {
            if (rotation != 1) {
                if (rotation == 2) {
                    return 8;
                }
                if (rotation != 3) {
                    MTPApi.LOGGER.error("Unknown screen orientation. Defaulting to landscape.");
                }
                return 9;
            }
            return 1;
        }
        return 0;
    }
}
