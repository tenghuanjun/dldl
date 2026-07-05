package com.duowan.kiwi.barrage.config;

import android.os.Build;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class GLBarrageAdapter {
    private static volatile boolean sHasClean;
    private static volatile boolean sIsBarrageRenderOn;
    private static boolean sIsOpenGLBarrageProblemSystem;
    private static volatile boolean sNeedCleanOnPase;

    public static boolean isOpenGLBarrageProblemSystem() {
        return sIsOpenGLBarrageProblemSystem;
    }

    static {
        if (Build.VERSION.SDK_INT >= 26) {
            sIsOpenGLBarrageProblemSystem = true;
        } else {
            String str = Build.BOARD;
            if (str != null) {
                String lowerCase = str.toLowerCase();
                if (lowerCase.equals("m5 note") || lowerCase.equals("m1 e") || lowerCase.equals("m3 max") || lowerCase.toLowerCase().equals("m3 note")) {
                    sIsOpenGLBarrageProblemSystem = true;
                }
            }
        }
        sNeedCleanOnPase = false;
        sIsBarrageRenderOn = false;
        sHasClean = true;
    }

    public static void pause() {
        BarrageLog.error("wolf", "enter pause------");
        if (sIsOpenGLBarrageProblemSystem && isRenderOn()) {
            sNeedCleanOnPase = true;
            sHasClean = false;
        }
    }

    public static void resume() {
        if (sIsOpenGLBarrageProblemSystem) {
            sNeedCleanOnPase = false;
            sHasClean = true;
        }
    }

    public static boolean needClear() {
        return sNeedCleanOnPase;
    }

    public static boolean hasClean() {
        return sHasClean;
    }

    public static void setHasClean() {
        sHasClean = true;
    }

    public static void setRenderOn(boolean z) {
        sIsBarrageRenderOn = z;
    }

    private static boolean isRenderOn() {
        return sIsBarrageRenderOn;
    }
}
