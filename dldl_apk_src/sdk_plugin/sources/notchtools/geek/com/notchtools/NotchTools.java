package notchtools.geek.com.notchtools;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import notchtools.geek.com.notchtools.core.INotchSupport;
import notchtools.geek.com.notchtools.core.OnNotchCallBack;
import notchtools.geek.com.notchtools.helper.DeviceBrandTools;
import notchtools.geek.com.notchtools.helper.NotchStatusBarUtils;
import notchtools.geek.com.notchtools.phone.CommonScreen;
import notchtools.geek.com.notchtools.phone.HuaWeiNotchScreen;
import notchtools.geek.com.notchtools.phone.MiuiNotchScreen;
import notchtools.geek.com.notchtools.phone.OppoNotchScreen;
import notchtools.geek.com.notchtools.phone.PVersionNotchScreen;
import notchtools.geek.com.notchtools.phone.PVersionNotchScreenWithFakeNotch;
import notchtools.geek.com.notchtools.phone.SamsungPunchHoleScreen;
import notchtools.geek.com.notchtools.phone.VivoNotchScreen;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class NotchTools {
    private static final int CURRENT_SDK = Build.VERSION.SDK_INT;
    public static final String NOTCH_CONTAINER = "notch_container";
    public static final String TOOLBAR_CONTAINER = "toolbar_container";
    public static final int VERSION_P = 28;
    private static NotchTools sFullScreenTolls;
    private boolean mHasJudge;
    private boolean mIsNotchScreen;
    private INotchSupport notchScreenSupport = null;

    public static NotchTools getFullScreenTools() {
        NotchStatusBarUtils.sShowNavigation = true;
        if (sFullScreenTolls == null) {
            synchronized (NotchTools.class) {
                if (sFullScreenTolls == null) {
                    sFullScreenTolls = new NotchTools();
                }
            }
        }
        return sFullScreenTolls;
    }

    private NotchTools() {
    }

    public boolean isNotchScreen(Window window) {
        if (!this.mHasJudge) {
            if (this.notchScreenSupport == null) {
                checkScreenSupportInit(window);
            }
            INotchSupport iNotchSupport = this.notchScreenSupport;
            if (iNotchSupport == null) {
                this.mHasJudge = true;
                this.mIsNotchScreen = false;
            } else {
                this.mIsNotchScreen = iNotchSupport.isNotchScreen(window);
            }
        }
        return this.mIsNotchScreen;
    }

    public boolean ignoreNotchScreen(Window window, int i, int i2) {
        INotchSupport iNotchSupport;
        if (!isNotchScreen(window) || (iNotchSupport = this.notchScreenSupport) == null) {
            return false;
        }
        return iNotchSupport.ignoreNotchScreen(window, i, i2);
    }

    public int getNotchHeight(Window window) {
        if (this.notchScreenSupport == null) {
            checkScreenSupportInit(window);
        }
        INotchSupport iNotchSupport = this.notchScreenSupport;
        if (iNotchSupport == null) {
            return 0;
        }
        return iNotchSupport.getNotchHeight(window);
    }

    public int getStatusHeight(Window window) {
        return NotchStatusBarUtils.getStatusBarHeight(window.getContext());
    }

    public NotchTools showNavigation(boolean z) {
        NotchStatusBarUtils.sShowNavigation = z;
        return this;
    }

    public void fullScreenDontUseStatusForActivityOnCreate(final Activity activity) {
        activity.getWindow().getDecorView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: notchtools.geek.com.notchtools.NotchTools.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                NotchTools.this.fullScreenDontUseStatus(activity);
            }
        });
    }

    public void fullScreenDontUseStatusForActivityOnCreate(final Activity activity, final OnNotchCallBack onNotchCallBack) {
        activity.getWindow().getDecorView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: notchtools.geek.com.notchtools.NotchTools.2
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                NotchTools.this.fullScreenDontUseStatus(activity, onNotchCallBack);
            }
        });
    }

    public void fullScreenDontUseStatusForOnWindowFocusChanged(Activity activity) {
        fullScreenDontUseStatus(activity);
    }

    public void fullScreenDontUseStatus(Activity activity) {
        fullScreenDontUseStatus(activity, null);
    }

    public void fullScreenDontUseStatus(Activity activity, OnNotchCallBack onNotchCallBack) {
        if (this.notchScreenSupport == null) {
            checkScreenSupportInit(activity.getWindow());
        }
        if (this.notchScreenSupport == null) {
            return;
        }
        if (isPortrait(activity)) {
            this.notchScreenSupport.fullScreenDontUseStatusForPortrait(activity, onNotchCallBack);
        } else {
            this.notchScreenSupport.fullScreenDontUseStatusForLandscape(activity, onNotchCallBack);
        }
    }

    public void fullScreenUseStatusForActivityOnCreate(final Activity activity) {
        activity.getWindow().getDecorView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: notchtools.geek.com.notchtools.NotchTools.3
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                NotchTools.this.fullScreenUseStatus(activity);
            }
        });
    }

    public void fullScreenUseStatusForActivityOnCreate(final Activity activity, final OnNotchCallBack onNotchCallBack) {
        activity.getWindow().getDecorView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: notchtools.geek.com.notchtools.NotchTools.4
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                NotchTools.this.fullScreenUseStatus(activity, onNotchCallBack);
            }
        });
    }

    public void fullScreenUseStatusForOnWindowFocusChanged(Activity activity) {
        fullScreenUseStatus(activity);
    }

    public void fullScreenUseStatus(Activity activity) {
        fullScreenUseStatus(activity, null);
    }

    public void fullScreenUseStatus(Activity activity, OnNotchCallBack onNotchCallBack) {
        if (this.notchScreenSupport == null) {
            checkScreenSupportInit(activity.getWindow());
        }
        INotchSupport iNotchSupport = this.notchScreenSupport;
        if (iNotchSupport != null) {
            iNotchSupport.fullScreenUseStatus(activity, onNotchCallBack);
        }
    }

    public void translucentStatusBar(Activity activity) {
        if (this.notchScreenSupport == null) {
            checkScreenSupportInit(activity.getWindow());
        }
        INotchSupport iNotchSupport = this.notchScreenSupport;
        if (iNotchSupport != null) {
            iNotchSupport.translucentStatusBar(activity);
        }
    }

    public void translucentStatusBar(Activity activity, OnNotchCallBack onNotchCallBack) {
        if (this.notchScreenSupport == null) {
            checkScreenSupportInit(activity.getWindow());
        }
        INotchSupport iNotchSupport = this.notchScreenSupport;
        if (iNotchSupport != null) {
            iNotchSupport.translucentStatusBar(activity, onNotchCallBack);
        }
    }

    private void checkScreenSupportInit(Window window) {
        if (this.notchScreenSupport != null) {
            return;
        }
        if (CURRENT_SDK < 26) {
            this.notchScreenSupport = new CommonScreen();
            return;
        }
        DeviceBrandTools deviceBrandTools = DeviceBrandTools.getInstance();
        if (CURRENT_SDK < 28) {
            if (deviceBrandTools.isHuaWei()) {
                this.notchScreenSupport = new HuaWeiNotchScreen();
                return;
            }
            if (deviceBrandTools.isMiui()) {
                this.notchScreenSupport = new MiuiNotchScreen();
                return;
            }
            if (deviceBrandTools.isVivo()) {
                this.notchScreenSupport = new VivoNotchScreen();
                return;
            }
            if (deviceBrandTools.isOppo()) {
                this.notchScreenSupport = new OppoNotchScreen();
                return;
            } else if (deviceBrandTools.isSamsung()) {
                this.notchScreenSupport = new SamsungPunchHoleScreen();
                return;
            } else {
                this.notchScreenSupport = new CommonScreen();
                return;
            }
        }
        if (deviceBrandTools.isHuaWei()) {
            this.notchScreenSupport = new PVersionNotchScreen();
        } else {
            this.notchScreenSupport = new PVersionNotchScreenWithFakeNotch();
        }
    }

    private boolean isPortrait(Activity activity) {
        return activity.getResources().getConfiguration().orientation == 1;
    }
}
