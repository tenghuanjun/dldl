package notchtools.geek.com.notchtools.core;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.Window;
import android.view.WindowInsets;
import java.util.Iterator;
import java.util.List;
import notchtools.geek.com.notchtools.helper.NotchStatusBarUtils;
import notchtools.geek.com.notchtools.helper.ScreenOrientationHelper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public abstract class AbsNotchScreenSupport implements INotchSupport {
    protected String TAG = getClass().getSimpleName();

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public int getStatusHeight(Window window) {
        return NotchStatusBarUtils.getStatusBarHeight(window.getContext());
    }

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenDontUseStatus(Activity activity, OnNotchCallBack onNotchCallBack) {
        NotchStatusBarUtils.setFullScreenWithSystemUi(activity.getWindow(), false);
        onBindCallBackWithNotchProperty(activity, onNotchCallBack);
    }

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenDontUseStatusForPortrait(Activity activity, OnNotchCallBack onNotchCallBack) {
        fullScreenDontUseStatus(activity, onNotchCallBack);
    }

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenDontUseStatusForLandscape(Activity activity, OnNotchCallBack onNotchCallBack) {
        NotchStatusBarUtils.setFullScreenWithSystemUi(activity.getWindow(), false);
        onBindCallBackWithNotchProperty(activity, onNotchCallBack);
        if (isNotchScreen(activity.getWindow())) {
            NotchStatusBarUtils.hideFakeNotchView(activity.getWindow());
        }
    }

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public void fullScreenUseStatus(Activity activity, OnNotchCallBack onNotchCallBack) {
        NotchStatusBarUtils.setFullScreenWithSystemUi(activity.getWindow(), false);
        onBindCallBackWithNotchProperty(activity, getNotchHeight(activity.getWindow()), onNotchCallBack);
    }

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public void translucentStatusBar(Activity activity) {
        translucentStatusBar(activity, null);
    }

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public void translucentStatusBar(Activity activity, OnNotchCallBack onNotchCallBack) {
        NotchStatusBarUtils.hideFakeNotchView(activity.getWindow());
        NotchStatusBarUtils.setStatusBarTransparent(activity.getWindow(), onNotchCallBack);
    }

    protected void onBindCallBackWithNotchProperty(Activity activity, OnNotchCallBack onNotchCallBack) {
        if (onNotchCallBack != null) {
            NotchProperty notchProperty = new NotchProperty();
            notchProperty.setNotchHeight(getNotchHeight(activity.getWindow()));
            notchProperty.setNotch(isNotchScreen(activity.getWindow()));
            if (onNotchCallBack != null) {
                onNotchCallBack.onNotchPropertyCallback(notchProperty);
            }
        }
    }

    protected void onBindCallBackWithNotchProperty(Activity activity, int i, OnNotchCallBack onNotchCallBack) {
        if (onNotchCallBack != null) {
            NotchProperty notchProperty = new NotchProperty();
            notchProperty.setNotchHeight(getNotchHeight(activity.getWindow()));
            notchProperty.setNotch(isNotchScreen(activity.getWindow()));
            notchProperty.setMarginTop(i);
            if (onNotchCallBack != null) {
                onNotchCallBack.onNotchPropertyCallback(notchProperty);
            }
        }
    }

    public int _getNotchHeight(Window window) {
        DisplayCutout displayCutout;
        List<Rect> boundingRects;
        int i = 0;
        try {
            try {
                int i2 = ScreenOrientationHelper.currentType;
                WindowInsets rootWindowInsets = window.getDecorView().getRootWindowInsets();
                if (rootWindowInsets == null || (displayCutout = rootWindowInsets.getDisplayCutout()) == null || (boundingRects = displayCutout.getBoundingRects()) == null || boundingRects.size() <= 0) {
                    return 0;
                }
                Iterator<Rect> it = boundingRects.iterator();
                if (!it.hasNext()) {
                    return 0;
                }
                Rect next = it.next();
                if (i2 != 270 && i2 != 90) {
                    return 0;
                }
                int i3 = next.right;
                int i4 = next.left;
                i = i3 - i4;
                return i;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Throwable unused) {
        }
        return i;
    }

    @Override // notchtools.geek.com.notchtools.core.INotchSupport
    public boolean ignoreNotchScreen(Window window, int i, int i2) {
        List<Rect> boundingRects;
        try {
            try {
                if (Build.VERSION.SDK_INT < 28) {
                    return false;
                }
                int i3 = ScreenOrientationHelper.currentType;
                WindowInsets rootWindowInsets = window.getDecorView().getRootWindowInsets();
                if (rootWindowInsets == null || (boundingRects = rootWindowInsets.getDisplayCutout().getBoundingRects()) == null || boundingRects.size() <= 0) {
                    return false;
                }
                Iterator<Rect> it = boundingRects.iterator();
                if (!it.hasNext()) {
                    return false;
                }
                Rect next = it.next();
                if (i3 == 270) {
                    if (next.top != 0) {
                        return false;
                    }
                } else if (i3 != 90 || next.bottom != i2) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}
