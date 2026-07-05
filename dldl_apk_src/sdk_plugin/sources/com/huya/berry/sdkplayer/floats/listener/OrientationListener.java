package com.huya.berry.sdkplayer.floats.listener;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.duowan.auk.ArkValue;
import com.huya.berry.gamesdk.utils.EasyTimer;
import com.huya.berry.gamesdk.utils.SystemUiUtils;
import com.huya.berry.sdkplayer.floats.view.FloatingWindowMgr;
import com.huya.mtp.utils.ThreadUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class OrientationListener {
    private static final String TAG = OrientationListener.class.getSimpleName();
    private EasyTimer mEasyTimer;
    private int mHeight;
    private int mPreviousHeight;
    private int mPreviousWidth;
    private int mWidth;

    private static class InnerInstance {
        private static final OrientationListener INSTANCE = new OrientationListener();

        private InnerInstance() {
        }
    }

    public static OrientationListener getInstance() {
        return InnerInstance.INSTANCE;
    }

    private OrientationListener() {
        this.mEasyTimer = null;
        this.mWidth = SystemUiUtils.getDisplayWidth();
        int displayHeight = SystemUiUtils.getDisplayHeight();
        this.mHeight = displayHeight;
        this.mPreviousWidth = this.mWidth;
        this.mPreviousHeight = displayHeight;
    }

    private void initDisplayOrientation() {
        WindowManager windowManager = (WindowManager) ArkValue.gContext.getSystemService("window");
        if (windowManager == null) {
            return;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager.getDefaultDisplay() == null) {
            return;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.mWidth = displayMetrics.widthPixels;
        this.mHeight = displayMetrics.heightPixels;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryResetScreenOrientation() {
        initDisplayOrientation();
        if (this.mPreviousWidth == this.mWidth && this.mPreviousHeight == this.mHeight) {
            return;
        }
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.huya.berry.sdkplayer.floats.listener.OrientationListener.1
            @Override // java.lang.Runnable
            public void run() {
                FloatingWindowMgr.resetFloatingIfNeed();
            }
        });
        this.mPreviousWidth = this.mWidth;
        this.mPreviousHeight = this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public void enable() {
        initDisplayOrientation();
        EasyTimer easyTimer = new EasyTimer();
        this.mEasyTimer = easyTimer;
        easyTimer.setDuration(1000);
        this.mEasyTimer.setRunnable(new Runnable() { // from class: com.huya.berry.sdkplayer.floats.listener.OrientationListener.2
            @Override // java.lang.Runnable
            public void run() {
                OrientationListener.this.tryResetScreenOrientation();
            }
        });
        this.mEasyTimer.start();
    }

    public void disable() {
        EasyTimer easyTimer = this.mEasyTimer;
        if (easyTimer != null) {
            easyTimer.stop();
            this.mEasyTimer = null;
        }
    }
}
