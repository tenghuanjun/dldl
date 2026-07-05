package notchtools.geek.com.notchtools.helper;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.sqwan.msdk.api.IMUrl;
import notchtools.geek.com.notchtools.NotchTools;
import notchtools.geek.com.notchtools.core.NotchProperty;
import notchtools.geek.com.notchtools.core.OnNotchCallBack;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class NotchStatusBarUtils {
    public static boolean sShowNavigation = false;
    private static int statusBarHeight = -1;

    public static int getStatusBarHeight(Context context) {
        int i = statusBarHeight;
        if (i != -1) {
            return i;
        }
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", IMUrl.OS);
        if (identifier > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(identifier);
        }
        if (statusBarHeight < 0) {
            int dimensionPixelSize = 0;
            try {
                Class<?> cls = Class.forName("com.android.internal.R$dimen");
                dimensionPixelSize = context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
            } catch (Exception unused) {
            } catch (Throwable th) {
                statusBarHeight = 0;
                throw th;
            }
            statusBarHeight = dimensionPixelSize;
        }
        if (statusBarHeight < 0) {
            statusBarHeight = dip2px(context, 25.0f);
        }
        return statusBarHeight;
    }

    private static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void setFullScreenWithSystemUi(final Window window, boolean z) {
        int i;
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.flags |= 1024;
        window.setAttributes(attributes);
        if (Build.VERSION.SDK_INT >= 16) {
            i = 1028;
            if (!sShowNavigation) {
                i = 1542;
            }
        } else {
            i = 0;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            i |= 4096;
        }
        window.getDecorView().setSystemUiVisibility(i);
        if (z) {
            window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: notchtools.geek.com.notchtools.helper.NotchStatusBarUtils.1
                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i2) {
                    if (i2 == 0) {
                        NotchStatusBarUtils.setFullScreenWithSystemUi(window, false);
                    }
                }
            });
        }
    }

    public static void setStatusBarTransparent(Window window, OnNotchCallBack onNotchCallBack) {
        window.clearFlags(1024);
        if (Build.VERSION.SDK_INT >= 23) {
            window.clearFlags(67108864);
            try {
                window.getDecorView().setSystemUiVisibility(1280);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        } else if (Build.VERSION.SDK_INT >= 19) {
            window.addFlags(67108864);
        }
        if (!sShowNavigation) {
            window.getDecorView().setSystemUiVisibility(514);
        }
        NotchProperty notchProperty = new NotchProperty();
        notchProperty.setStatusBarHeight(NotchTools.getFullScreenTools().getStatusHeight(window));
        notchProperty.setNotchHeight(NotchTools.getFullScreenTools().getNotchHeight(window));
        notchProperty.setNotch(NotchTools.getFullScreenTools().isNotchScreen(window));
        if (onNotchCallBack != null) {
            onNotchCallBack.onNotchPropertyCallback(notchProperty);
        }
    }

    private static void setToolbarContainerFillStatusBar(Window window) {
        View childAt;
        int statusHeight = NotchTools.getFullScreenTools().getStatusHeight(window);
        ViewGroup toolBarContainer = getToolBarContainer(window);
        if (toolBarContainer == null || toolBarContainer.getChildCount() < 1 || (childAt = toolBarContainer.getChildAt(0)) == null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        if (layoutParams.height > 0) {
            layoutParams.height += statusHeight;
            childAt.setLayoutParams(layoutParams);
        } else {
            childAt.setPadding(0, statusHeight, 0, 0);
        }
    }

    public static void showFakeNotchView(Window window) {
        ViewGroup notchContainer = getNotchContainer(window);
        if (notchContainer == null) {
            return;
        }
        if (notchContainer.getChildCount() == 0) {
            View view = new View(window.getContext());
            view.setLayoutParams(new ViewGroup.LayoutParams(-1, NotchTools.getFullScreenTools().getNotchHeight(window)));
            view.setBackgroundColor(-16777216);
            notchContainer.addView(view);
        }
        notchContainer.setVisibility(0);
    }

    public static void showFakeNotchViewColor(Window window, int i) {
        ViewGroup notchContainer = getNotchContainer(window);
        if (notchContainer != null && notchContainer.getChildCount() == 1) {
            notchContainer.getChildAt(0).setBackgroundColor(i);
        }
    }

    public static void hideFakeNotchView(Window window) {
        ViewGroup notchContainer = getNotchContainer(window);
        if (notchContainer == null) {
            return;
        }
        notchContainer.getChildCount();
        notchContainer.setVisibility(8);
    }

    private static ViewGroup getNotchContainer(Window window) {
        View decorView = window.getDecorView();
        if (decorView == null) {
            return null;
        }
        return (ViewGroup) decorView.findViewWithTag(NotchTools.NOTCH_CONTAINER);
    }

    public static ViewGroup getToolBarContainer(Window window) {
        View decorView = window.getDecorView();
        if (decorView == null) {
            return null;
        }
        return (ViewGroup) decorView.findViewWithTag(NotchTools.TOOLBAR_CONTAINER);
    }
}
