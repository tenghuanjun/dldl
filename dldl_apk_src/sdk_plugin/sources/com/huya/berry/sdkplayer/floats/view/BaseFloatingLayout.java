package com.huya.berry.sdkplayer.floats.view;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.duowan.HUYA.ScreenType;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.sdkplayer.floats.FloatingVideoMgr;
import com.huya.berry.sdkplayer.floats.data.FloatingPositionInfo;
import com.huya.berry.sdkplayer.floats.listener.OrientationListener;
import com.huya.berry.sdkplayer.floats.utils.FloatingPreferences;
import com.huya.mtp.utils.DensityUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseFloatingLayout extends FrameLayout {
    public static final float FLOAT_UNIT = 1.0f;
    private static final float MIN_COVER_ALPHA = 0.0f;
    private static final String TAG = BaseFloatingLayout.class.getSimpleName();
    private boolean isMove;
    public long mAlertHelperId;
    private boolean mHasReleased;
    public WindowManager.LayoutParams mParams;
    private int mStartX;
    private int mStartY;
    private int mStopX;
    private int mStopY;
    private int mTouchCurrentX;
    private int mTouchCurrentY;
    private int mTouchStartX;
    private int mTouchStartY;
    private ImageView mWaterMark;
    public WindowManager mWindowManager;
    public ViewGroup mlayout;

    public void createPlayer() {
    }

    public void onWindowSizeChanged(int i, int i2) {
    }

    public void setWaterMark(boolean z) {
    }

    public void startVideo(ScreenType screenType, boolean z, boolean z2) {
    }

    public void stopVideo() {
    }

    public void switchBarrage(boolean z) {
    }

    public BaseFloatingLayout(Context context, WindowManager.LayoutParams layoutParams) {
        super(context);
        this.mHasReleased = false;
        this.mParams = layoutParams;
    }

    protected void initView(Context context) {
        this.mWaterMark = (ImageView) this.mlayout.findViewById(ResourceUtil.getIdResIDByName("water_mark"));
    }

    public void destroy() {
        this.mHasReleased = true;
    }

    public boolean isMove() {
        return this.isMove;
    }

    public boolean hasReleased() {
        return this.mHasReleased;
    }

    public void resetPosition() {
        if (hasReleased()) {
            return;
        }
        int width = (OrientationListener.getInstance().getWidth() - getWindowWidth()) + FloatingWindowMgr.FLOATING_WINDOW_RIGHT_X_OFFSET;
        if (this.mParams.x > width) {
            this.mParams.x = width;
        }
        int height = OrientationListener.getInstance().getHeight() - getWindowHeight();
        if (this.mParams.y > height) {
            this.mParams.y = height;
        }
        L.debug(TAG, "mParams.x:%d, mParams.y:%d, maxYInFullScreen:%d", Integer.valueOf(this.mParams.x), Integer.valueOf(this.mParams.y), Integer.valueOf(height));
        this.mWindowManager.updateViewLayout(this.mlayout, this.mParams);
    }

    protected int getWindowWidth() {
        return this.mParams.width;
    }

    protected int getWindowHeight() {
        return this.mParams.height;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void savePositionInfo() {
        FloatingPreferences.saveFloatingPositionInfo(new FloatingPositionInfo(FloatingWindowMgr.getScreenType(), FloatingWindowMgr.getCurrentVideoDirection(), this.mParams.x, this.mParams.y, getWindowWidth(), getWindowHeight(), FloatingWindowMgr.isScaleBigger(), FloatingWindowMgr.getFloatingCurrentType()));
    }

    public void closeFloatingWindow() {
        FloatingVideoMgr.getInstance().destroy();
        if (FloatingPreferences.isNeedShowFloatingClosePrompt()) {
            FloatingPreferences.saveShowFloatingClosePrompt();
        }
    }

    public void resetVoice() {
        L.info(TAG, "reset Voice true");
    }

    @Override // android.view.View
    public boolean isShown() {
        ViewGroup viewGroup = this.mlayout;
        return viewGroup != null && viewGroup.getVisibility() == 0;
    }

    public class FloatingListener implements View.OnTouchListener {
        private GestureDetector gestureDetector;

        public FloatingListener(GestureDetector gestureDetector) {
            this.gestureDetector = gestureDetector;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                BaseFloatingLayout.this.isMove = false;
                BaseFloatingLayout.this.mTouchStartX = (int) motionEvent.getRawX();
                BaseFloatingLayout.this.mTouchStartY = (int) motionEvent.getRawY();
                BaseFloatingLayout.this.mStartX = (int) motionEvent.getX();
                BaseFloatingLayout.this.mStartY = (int) motionEvent.getY();
            } else if (action == 1) {
                BaseFloatingLayout.this.mStopX = (int) motionEvent.getX();
                BaseFloatingLayout.this.mStopY = (int) motionEvent.getY();
                if (!BaseFloatingLayout.this.mHasReleased && (Math.abs(BaseFloatingLayout.this.mStartX - BaseFloatingLayout.this.mStopX) >= 3 || Math.abs(BaseFloatingLayout.this.mStartY - BaseFloatingLayout.this.mStopY) >= 3)) {
                    BaseFloatingLayout.this.isMove = true;
                }
                L.debug(BaseFloatingLayout.TAG, "slider close start remember position");
                BaseFloatingLayout.this.savePositionInfo();
            } else if (action == 2) {
                BaseFloatingLayout.this.mTouchCurrentX = (int) motionEvent.getRawX();
                BaseFloatingLayout.this.mTouchCurrentY = (int) motionEvent.getRawY();
                BaseFloatingLayout.this.mParams.x += BaseFloatingLayout.this.mTouchCurrentX - BaseFloatingLayout.this.mTouchStartX;
                BaseFloatingLayout.this.mParams.y += BaseFloatingLayout.this.mTouchCurrentY - BaseFloatingLayout.this.mTouchStartY;
                if (BaseFloatingLayout.this.mParams.x <= (-BaseFloatingLayout.this.getWindowWidth()) + DensityUtil.dip2px(ArkValue.gContext, 30.0f)) {
                    BaseFloatingLayout.this.mParams.x = (-BaseFloatingLayout.this.getWindowWidth()) + DensityUtil.dip2px(ArkValue.gContext, 30.0f);
                }
                if (BaseFloatingLayout.this.mParams.x > OrientationListener.getInstance().getWidth() - DensityUtil.dip2px(ArkValue.gContext, 30.0f)) {
                    BaseFloatingLayout.this.mParams.x = OrientationListener.getInstance().getWidth() - DensityUtil.dip2px(ArkValue.gContext, 30.0f);
                }
                if (BaseFloatingLayout.this.mParams.y <= (-BaseFloatingLayout.this.getWindowHeight()) + DensityUtil.dip2px(ArkValue.gContext, 30.0f)) {
                    BaseFloatingLayout.this.mParams.y = (-BaseFloatingLayout.this.getWindowHeight()) + DensityUtil.dip2px(ArkValue.gContext, 30.0f);
                }
                if (BaseFloatingLayout.this.mParams.y > OrientationListener.getInstance().getHeight() - DensityUtil.dip2px(ArkValue.gContext, 30.0f)) {
                    BaseFloatingLayout.this.mParams.y = OrientationListener.getInstance().getHeight() - DensityUtil.dip2px(ArkValue.gContext, 30.0f);
                }
                if (!BaseFloatingLayout.this.hasReleased()) {
                    BaseFloatingLayout.this.mWindowManager.updateViewLayout(BaseFloatingLayout.this.mlayout, BaseFloatingLayout.this.mParams);
                }
                BaseFloatingLayout baseFloatingLayout = BaseFloatingLayout.this;
                baseFloatingLayout.mTouchStartX = baseFloatingLayout.mTouchCurrentX;
                BaseFloatingLayout baseFloatingLayout2 = BaseFloatingLayout.this;
                baseFloatingLayout2.mTouchStartY = baseFloatingLayout2.mTouchCurrentY;
            }
            try {
                return this.gestureDetector.onTouchEvent(motionEvent);
            } catch (Exception e) {
                L.error(BaseFloatingLayout.TAG, "onTouchEvent failed", e);
                ArkUtils.crashIfDebug(BaseFloatingLayout.TAG, "onTouchEventFailed");
                return false;
            }
        }
    }
}
