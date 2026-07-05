package com.huya.berry.sdkplayer.floats.view;

import android.R;
import android.content.Context;
import android.os.Build;
import android.view.WindowManager;
import com.duowan.HUYA.ScreenType;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.berry.gamesdk.utils.SystemUiUtils;
import com.huya.berry.sdkplayer.floats.data.FloatingPositionInfo;
import com.huya.berry.sdkplayer.floats.listener.OrientationListener;
import com.huya.berry.sdkplayer.floats.utils.FloatingPreferences;
import com.huya.mtp.utils.DensityUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FloatingWindowMgr {
    private static final float FLOATING_SCALE_BY_VIDEO_MAX_PERCENT = 0.733f;
    private static final float FLOATING_SCALE_BY_VIDEO_MIN_PERCENT = 0.82f;
    private static final float FLOATING_SCALE_GAME = 0.82f;
    private static final float FLOATING_SCALE_MOBILE = 0.53f;
    public static final int FLOATING_WINDOW_MIN_Y_OFFSET;
    public static final int FLOATING_WINDOW_TOP_Y_OFFSET;
    private static final int SCALE_TYPE_BIG = 1;
    private static final int SCALE_TYPE_SMALL = 0;
    private static final float SHANGJING_SCALE_MAX_PERCENT = 0.65f;
    private static final String TAG = "FloatingWindowMgr";
    private static BaseFloatingLayout mFloatingLayout;
    private static boolean mIsBigger;
    private static int mScaleTime;
    private static ScreenType mScreenType;
    private static WindowManager mWindowManager;
    private static int sCurrentVideoDirection;
    private static float sScale;
    private static WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
    private static boolean mIsInitFloating = false;
    public static int VIDEO_DIRECTION_HORIZONTAL = 0;
    public static int VIDEO_DIRECTION_VERTICAL = 1;
    private static float VIDEO_HORIZONTAL_SCALE = 1.7777778f;
    private static float VIDEO_VERTICAL_SCALE = 0.5625f;
    public static final int sDefaultMarginBottom = DensityUtil.dip2px(ArkValue.gContext, 5.0f);
    public static final int FLOATING_VIDEO_SHADOW_LEN = DensityUtil.dip2px(ArkValue.gContext, 1.0f) + 1;
    public static final int FLOATING_WINDOW_RIGHT_X_OFFSET = DensityUtil.dip2px(ArkValue.gContext, 6.0f);

    static {
        int iDip2px = DensityUtil.dip2px(ArkValue.gContext, 6.0f);
        FLOATING_WINDOW_TOP_Y_OFFSET = iDip2px;
        FLOATING_WINDOW_MIN_Y_OFFSET = iDip2px;
        mScaleTime = 0;
        mIsBigger = true;
    }

    public static void initWindow(Context context, ScreenType screenType) {
        L.info(TAG, "enter init Window");
        destroy();
        initFloatingVideoWindow(context, screenType);
    }

    private static void initFloatingVideoWindow(Context context, ScreenType screenType) {
        mWindowManager = (WindowManager) context.getApplicationContext().getSystemService("window");
        wmParams.format = 1;
        wmParams.type = getFloatingType();
        wmParams.flags = R.dimen.resolver_empty_state_container_padding_bottom;
        FloatingLayout floatingLayout = new FloatingLayout(context, wmParams, screenType);
        mFloatingLayout = floatingLayout;
        mWindowManager.addView(floatingLayout, wmParams);
    }

    public static void onVideoSizeChanged(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        initFloatingWindows(mScreenType);
    }

    public static void initFloating(ScreenType screenType, boolean z) {
        L.info(TAG, "initFloating liveRoomParam=%s", screenType);
        if (!mIsInitFloating || !screenType.equals(mScreenType)) {
            mScreenType = screenType;
            mScreenType = screenType;
            int iValue = screenType.value();
            if (iValue == 0) {
                sCurrentVideoDirection = VIDEO_DIRECTION_VERTICAL;
                sScale = VIDEO_VERTICAL_SCALE;
            } else if (iValue == 1) {
                sCurrentVideoDirection = VIDEO_DIRECTION_HORIZONTAL;
                sScale = VIDEO_HORIZONTAL_SCALE;
            }
            initFloatingWindows(screenType);
        } else if (isOutsideScreen()) {
            initFloatingWindows(mScreenType);
        }
        mFloatingLayout.startVideo(mScreenType, z, true);
        refreshWindowLayoutIfNeed(mScreenType);
    }

    public static void resetFloatingIfNeed() {
        BaseFloatingLayout baseFloatingLayout = mFloatingLayout;
        if (baseFloatingLayout != null) {
            baseFloatingLayout.resetPosition();
        }
    }

    public static boolean isShown() {
        BaseFloatingLayout baseFloatingLayout = mFloatingLayout;
        return baseFloatingLayout != null && baseFloatingLayout.isShown();
    }

    public static void stopVideo() {
        destroy();
    }

    public static void destroy() {
        BaseFloatingLayout baseFloatingLayout;
        L.info(TAG, "enter destroy");
        if (mWindowManager == null || (baseFloatingLayout = mFloatingLayout) == null) {
            return;
        }
        baseFloatingLayout.destroy();
        try {
            mWindowManager.removeView(mFloatingLayout);
        } catch (Exception e) {
            L.error(TAG, "low version could be exception when view is different width ViewAncestor e: " + e);
        }
        mFloatingLayout = null;
        mIsInitFloating = false;
        mWindowManager = null;
    }

    public static void showFloatingVideo(boolean z) {
        BaseFloatingLayout baseFloatingLayout = mFloatingLayout;
        if (baseFloatingLayout == null || baseFloatingLayout.isShown()) {
            return;
        }
        mFloatingLayout.setVisibility(0);
    }

    public static void createPlayer() {
        BaseFloatingLayout baseFloatingLayout = mFloatingLayout;
        if (baseFloatingLayout == null || !baseFloatingLayout.isShown()) {
            return;
        }
        mFloatingLayout.createPlayer();
    }

    public static void setWaterMark(boolean z) {
        BaseFloatingLayout baseFloatingLayout = mFloatingLayout;
        if (baseFloatingLayout != null) {
            baseFloatingLayout.setWaterMark(z);
        }
    }

    private static int getDefaultYOffset() {
        return DensityUtil.dip2px(ArkValue.gContext, 6.0f);
    }

    private static int getDefaultXOffset() {
        return FLOATING_WINDOW_RIGHT_X_OFFSET;
    }

    private static int getDisplayWidth() {
        int width = OrientationListener.getInstance().getWidth();
        L.info(TAG, "getFloatingSizeByScaleType, displayWidth=%d", Integer.valueOf(width));
        return width;
    }

    private static boolean isOutsideScreen() {
        return wmParams.x < 0 || wmParams.x > (getDisplayWidth() - wmParams.width) + FLOATING_WINDOW_RIGHT_X_OFFSET;
    }

    private static void initFloatingWindows(ScreenType screenType) {
        L.info(TAG, "initFloatingWindows screenType=%s", screenType);
        FloatingPositionInfo floatingPositionInfo = FloatingPreferences.getFloatingPositionInfo();
        initFloatingSizeByPositionInfo(floatingPositionInfo);
        if (wmParams.width > SystemUiUtils.getDisplayWidth() + FLOATING_WINDOW_RIGHT_X_OFFSET) {
            int[] floatingSizeByScaleType = getFloatingSizeByScaleType(0);
            wmParams.width = floatingSizeByScaleType[0];
            wmParams.height = floatingSizeByScaleType[1];
        }
        initFloatingPosition(floatingPositionInfo);
        try {
            mWindowManager.updateViewLayout(mFloatingLayout, wmParams);
            mIsInitFloating = true;
        } catch (Exception e) {
            L.error(TAG, "updataViewLayout exception!!", e);
            ArkUtils.crashIfDebug(TAG, "updataViewLayout failed!");
        }
    }

    private static void initFloatingPosition(FloatingPositionInfo floatingPositionInfo) {
        if (floatingPositionInfo != null && floatingPositionInfo.getWidth() != 0 && floatingPositionInfo.getHeight() != 0) {
            wmParams.x = floatingPositionInfo.getX();
            wmParams.y = floatingPositionInfo.getY();
        } else {
            wmParams.x = getDefaultXOffset();
            wmParams.y = getDefaultYOffset();
        }
        wmParams.gravity = 51;
    }

    private static int[] getFloatingSizeByScaleType(int i) {
        int[] iArr = {0, 0};
        int displayWidth = SystemUiUtils.getDisplayWidth();
        int i2 = sCurrentVideoDirection;
        if (i2 != VIDEO_DIRECTION_HORIZONTAL && i2 == VIDEO_DIRECTION_VERTICAL) {
            iArr[0] = (int) (displayWidth * FLOATING_SCALE_MOBILE);
        } else {
            iArr[0] = (int) (displayWidth * 0.82f);
        }
        if (i != 0) {
            int i3 = sCurrentVideoDirection;
            if (i3 == VIDEO_DIRECTION_HORIZONTAL) {
                iArr[0] = (displayWidth - FLOATING_VIDEO_SHADOW_LEN) + FLOATING_WINDOW_RIGHT_X_OFFSET;
            } else if (i3 == VIDEO_DIRECTION_VERTICAL) {
                iArr[0] = (int) (displayWidth * SHANGJING_SCALE_MAX_PERCENT);
            } else {
                iArr[0] = (int) (displayWidth * FLOATING_SCALE_BY_VIDEO_MAX_PERCENT);
            }
        }
        int i4 = (int) (iArr[0] / sScale);
        int i5 = FLOATING_VIDEO_SHADOW_LEN;
        iArr[1] = i4 + i5;
        iArr[0] = iArr[0] + i5;
        return iArr;
    }

    private static int[] getFloatingSizeByPositionInfo(FloatingPositionInfo floatingPositionInfo) {
        return floatingPositionInfo.getDirection() == sCurrentVideoDirection ? new int[]{floatingPositionInfo.getWidth(), floatingPositionInfo.getHeight()} : getFloatingSizeByScaleType(floatingPositionInfo.getCurrentType());
    }

    private static void initFloatingSizeByPositionInfo(FloatingPositionInfo floatingPositionInfo) {
        L.info(TAG, "initFloatingSizeByPositionInfo");
        if (floatingPositionInfo != null && floatingPositionInfo.getWidth() != 0 && floatingPositionInfo.getHeight() != 0) {
            int[] floatingSizeByPositionInfo = getFloatingSizeByPositionInfo(floatingPositionInfo);
            wmParams.width = floatingSizeByPositionInfo[0];
            wmParams.height = floatingSizeByPositionInfo[1];
            mIsBigger = floatingPositionInfo.isNextBigger();
            mScaleTime = floatingPositionInfo.getCurrentType();
            return;
        }
        initFloatingSizeByDefault();
    }

    private static void initFloatingSizeByDefault() {
        int[] floatingSizeByScaleType = getFloatingSizeByScaleType(0);
        wmParams.width = floatingSizeByScaleType[0];
        wmParams.height = floatingSizeByScaleType[1];
    }

    public static int getFloatingType() {
        if (Build.VERSION.SDK_INT < 23) {
            return 2005;
        }
        return Build.VERSION.SDK_INT < 26 ? 2003 : 2038;
    }

    public static synchronized void scaleFloatingWindow() {
        doScaleFloatingWindow();
        if (mFloatingLayout == null) {
            L.debug(TAG, "mFloatingLayout is null");
            return;
        }
        mFloatingLayout.onWindowSizeChanged(wmParams.width, wmParams.height);
        mWindowManager.updateViewLayout(mFloatingLayout, wmParams);
        FloatingPreferences.saveFloatingPositionInfo(new FloatingPositionInfo(mScreenType, sCurrentVideoDirection, wmParams.x, wmParams.y, wmParams.width, wmParams.height, isScaleBigger(), getFloatingCurrentType()));
    }

    private static void doScaleFloatingWindow() {
        int i = mScaleTime;
        if (i > 1) {
            L.debug(TAG, "sScale is more than 2");
            return;
        }
        if (i == 0) {
            updateScaleFloatingByTimes(1);
        } else if (i == 1) {
            updateScaleFloatingByTimes(0);
        }
        int i2 = mScaleTime;
        if (i2 == 0) {
            mIsBigger = true;
        } else if (i2 == 1) {
            mIsBigger = false;
        }
        if (mIsBigger) {
            mScaleTime++;
        } else {
            mScaleTime--;
        }
    }

    private static void updateScaleFloatingByTimes(int i) {
        int i2 = wmParams.width;
        int i3 = wmParams.height;
        int[] floatingSizeByScaleType = getFloatingSizeByScaleType(i);
        wmParams.width = floatingSizeByScaleType[0];
        wmParams.x -= (wmParams.width - i2) / 2;
        wmParams.height = floatingSizeByScaleType[1];
        wmParams.y -= (wmParams.height - i3) / 2;
    }

    public static void fullScreen(boolean z) {
        if (mFloatingLayout == null) {
            L.debug(TAG, "mFloatingLayout is null");
            return;
        }
        if (z) {
            wmParams.width = -1;
            wmParams.height = -1;
            wmParams.x = 0;
            wmParams.y = 0;
            mFloatingLayout.onWindowSizeChanged(wmParams.width, wmParams.height);
            mWindowManager.updateViewLayout(mFloatingLayout, wmParams);
            return;
        }
        updateScaleFloatingByTimes(mScaleTime);
        mFloatingLayout.onWindowSizeChanged(wmParams.width, wmParams.height);
        mWindowManager.updateViewLayout(mFloatingLayout, wmParams);
    }

    public static ScreenType getScreenType() {
        return mScreenType;
    }

    public static int getCurrentVideoDirection() {
        return sCurrentVideoDirection;
    }

    public static boolean isScaleBigger() {
        return mIsBigger;
    }

    public static int getFloatingCurrentType() {
        return mScaleTime;
    }

    public static void refreshWindowLayoutIfNeed(ScreenType screenType) {
        mWindowManager.updateViewLayout(mFloatingLayout, wmParams);
    }

    public static void removeView() {
        mWindowManager.removeView(mFloatingLayout);
    }

    public static void addView() {
        mWindowManager.addView(mFloatingLayout, wmParams);
    }
}
