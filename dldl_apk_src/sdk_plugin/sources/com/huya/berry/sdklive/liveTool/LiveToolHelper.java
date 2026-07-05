package com.huya.berry.sdklive.liveTool;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.berry.gamesdk.utils.NotchUtil;
import com.huya.berry.gamesdk.utils.UIUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class LiveToolHelper {
    private static final String TAG = "LiveToolHelper";

    public static int getAnimatorValue(float f, int i, int i2) {
        return i + ((int) ((i2 - i) * f));
    }

    public static int getMsgContainerTop(int i, boolean z) {
        return 0;
    }

    public static Rect getMsgContainerMarginsByToolMenu(View view, boolean z) {
        Rect rect = new Rect();
        if (view == null) {
            return rect;
        }
        int measuredWidth = view.getMeasuredWidth() / 2;
        int measuredHeight = view.getMeasuredHeight();
        if (z) {
            rect.left = measuredWidth;
            rect.top = getMsgContainerTop(measuredHeight, true);
            rect.right = 0;
            rect.bottom = 0;
        } else {
            rect.left = measuredWidth;
            rect.top = getMsgContainerTop(measuredHeight, false);
            rect.bottom = 0;
            rect.right = 0;
        }
        return rect;
    }

    public static int getRightLowerButtonTop(int i) {
        if (i == 0) {
            return 0;
        }
        return (((i - ((int) UIUtil.getDp(31.0f))) / 2) + MessageToolView.VIEW_HEIGHT_MINI) - i;
    }

    public static Rect calcToolMenuAndMsgContainerRect(View view, boolean z, int i, int i2, boolean z2) {
        Rect rect = new Rect();
        if (view == null) {
            return rect;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (z) {
            if (z2) {
                rect.left = i;
                rect.right = rect.left + (view.getMeasuredWidth() / 2) + MessageToolView.NOW_VIEW_WIDTH;
                rect.top = i2 - MessageToolView.VIEW_HEIGHT_EXPAND;
                rect.bottom = i2;
            } else {
                rect.left = i;
                rect.right = rect.left + (view.getMeasuredWidth() / 2) + MessageToolView.NOW_VIEW_WIDTH;
                rect.top = i2;
                rect.bottom = i2 + MessageToolView.VIEW_HEIGHT_EXPAND;
            }
        } else if (z2) {
            rect.left = i;
            rect.right = rect.left + (view.getMeasuredWidth() / 2) + MessageToolView.NOW_VIEW_WIDTH;
            rect.top = i2 - view.getMeasuredHeight();
            rect.bottom = i2;
        } else {
            rect.left = i;
            rect.right = rect.left + (view.getMeasuredWidth() / 2) + MessageToolView.NOW_VIEW_WIDTH;
            rect.top = i2;
            rect.bottom = i2 + view.getMeasuredHeight() + layoutParams.topMargin;
        }
        return rect;
    }

    public static int[] moveContainer(int i, int i2, ViewGroup viewGroup, WindowManager windowManager, int i3, int i4) {
        int[] iArr = new int[2];
        if (viewGroup == null) {
            return iArr;
        }
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) viewGroup.getLayoutParams();
        Point pointAdjustToolMenuContainerPoint = adjustToolMenuContainerPoint(layoutParams.x + i, layoutParams.y + i2, viewGroup, i3, i4);
        layoutParams.x = pointAdjustToolMenuContainerPoint.x;
        layoutParams.y = pointAdjustToolMenuContainerPoint.y;
        windowManager.updateViewLayout(viewGroup, layoutParams);
        iArr[0] = layoutParams.x;
        iArr[1] = layoutParams.y;
        return iArr;
    }

    public static void halfHideTool(View view, View view2, WindowManager windowManager) {
        if (view2 == null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
        if (layoutParams.x != 0) {
            layoutParams.x = 0;
            windowManager.updateViewLayout(view, layoutParams);
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) view2.getLayoutParams();
        layoutParams2.leftMargin = (-view2.getMeasuredWidth()) / 2;
        view2.setLayoutParams(layoutParams2);
    }

    public static void initToolButton(View view) {
        if (view == null) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = view.getMeasuredWidth();
        layoutParams.leftMargin = 0;
        view.setLayoutParams(layoutParams);
    }

    public static Point adjustToolMenuContainerPoint(int i, int i2, View view, int i3, int i4) {
        Rect rect;
        Point point = new Point(i, i2);
        if (view == null) {
            return point;
        }
        L.info(TAG, " x " + i + " view.getMeasuredWidth() " + view.getMeasuredWidth() + " appendX " + i3 + " getMeasuredHeight " + view.getMeasuredHeight());
        if (i4 > 0) {
            rect = new Rect(i, i2, view.getMeasuredWidth() + i + i3, view.getMeasuredHeight() + i2 + i4);
        } else {
            rect = new Rect(i, i4 + i2, view.getMeasuredWidth() + i + i3, view.getMeasuredHeight() + i2);
        }
        Point pointScreenSize = UIUtil.screenSize();
        if (rect.left < 0) {
            point.x = i - rect.left;
        } else if (rect.right > pointScreenSize.x) {
            point.x = i - (rect.right - pointScreenSize.x);
        }
        if (rect.top < 0) {
            point.y = i2 - rect.top;
        } else if (rect.bottom > pointScreenSize.y) {
            point.y = i2 - (rect.bottom - pointScreenSize.y);
        }
        return point;
    }

    public static Point adjustToolMenuContainerPoint(int i, int i2, View view) {
        Point point = new Point(i, i2);
        if (view == null) {
            return point;
        }
        Rect rect = new Rect(i, i2, view.getMeasuredWidth() + i, view.getMeasuredHeight() + i2);
        Point pointScreenSize = UIUtil.screenSize();
        if (rect.left < 0) {
            point.x = i - rect.left;
        } else if (rect.right > pointScreenSize.x) {
            point.x = i - (rect.right - pointScreenSize.x);
        }
        if (rect.top < 0) {
            point.y = i2 - rect.top;
        } else if (rect.bottom > pointScreenSize.y) {
            point.y = i2 - (rect.bottom - pointScreenSize.y);
        }
        return point;
    }

    public static boolean isLeftMost(View view) {
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        if (NotchUtil.needCalNotchSize(ArkValue.gContext)) {
            iArr[0] = iArr[0] - NotchUtil.getNotchSize(ArkValue.gContext);
        }
        if (iArr[0] <= 0) {
            return true;
        }
        try {
            ViewGroup.LayoutParams layoutParams = ((ViewGroup) view.getParent().getParent()).getLayoutParams();
            if (layoutParams instanceof WindowManager.LayoutParams) {
                if (((WindowManager.LayoutParams) layoutParams).x == 0) {
                    return true;
                }
            }
        } catch (Throwable unused) {
            L.error(TAG, "view.getParent().getParent().getLayoutParams() is not WindowManager.LayoutParams");
        }
        return false;
    }
}
