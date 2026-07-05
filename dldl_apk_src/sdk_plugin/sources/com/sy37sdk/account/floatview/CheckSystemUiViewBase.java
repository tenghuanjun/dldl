package com.sy37sdk.account.floatview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.sqwan.common.util.LogUtil;
import com.sy37sdk.account.floatview.FloatViewUtils;
import com.sy37sdk.account.floatview.ScreenOrientationHelper;
import com.sy37sdk.account.floatview.SystemUiCheckUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class CheckSystemUiViewBase extends RelativeLayout implements SystemUiCheckUtils.onSystemUiChangedCallback, ScreenOrientationHelper.ScreenOrientationChangeListener {
    protected final String TAG;
    public CloseCallback closeCallback;
    protected FloatViewUtils.FloatViewConfig config;
    protected WindowManager.LayoutParams floatLayoutParams;
    public Context mContext;
    protected int mTouchSlop;
    protected WindowManager mWindowManager;
    private float screenBrightness;

    public interface CloseCallback {
        void invoke();
    }

    private void uninitScreenOnType() {
    }

    public boolean isScreenOnType() {
        return false;
    }

    public void onChange(int i) {
    }

    public void onSystemUiChanged(FloatViewUtils.FloatViewConfig floatViewConfig) {
    }

    public CheckSystemUiViewBase(Context context) {
        this(context, null);
    }

    public CheckSystemUiViewBase(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CheckSystemUiViewBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.mContext = context;
        init();
    }

    private void initScreenOnType() {
        if (!isScreenOnType() || this.floatLayoutParams == null || getWindow() == null) {
            return;
        }
        this.screenBrightness = getWindow().getAttributes().screenBrightness;
        LogUtil.d(this.TAG, "initScreenOnType screenBrightness:" + this.screenBrightness);
    }

    public void init() {
        if (this.mWindowManager == null) {
            this.mWindowManager = (WindowManager) getContext().getSystemService("window");
            initLayoutParams();
            int scaledTouchSlop = ViewConfiguration.get(this.mContext).getScaledTouchSlop() / 4;
            this.mTouchSlop = scaledTouchSlop;
            if (scaledTouchSlop <= 4) {
                this.mTouchSlop = 4;
            }
            ScreenOrientationHelper.initOrAdd(this.mContext, this);
        }
        SystemUiCheckUtils.getInstance().addListener(this);
    }

    public void initLayoutParams() {
        this.config = new FloatViewUtils.FloatViewConfig(this.mContext);
        this.floatLayoutParams = new WindowManager.LayoutParams(-2, -2, 1000, 552, 1);
        setPadding(this.config.marginLeft, this.config.marginTop, 0, 0);
        this.floatLayoutParams.gravity = 51;
        initScreenOnType();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        uninitScreenOnType();
        release();
    }

    public void release() {
        SystemUiCheckUtils.getInstance().removeListener(this);
        removeView();
    }

    public void update() {
        WindowManager windowManager;
        WindowManager.LayoutParams layoutParams;
        if (!isAttachedToWindow() || (windowManager = this.mWindowManager) == null || (layoutParams = this.floatLayoutParams) == null) {
            return;
        }
        windowManager.updateViewLayout(this, layoutParams);
    }

    public void addView() {
        WindowManager windowManager;
        WindowManager.LayoutParams layoutParams;
        if (isAttachedToWindow() || (windowManager = this.mWindowManager) == null || (layoutParams = this.floatLayoutParams) == null) {
            return;
        }
        windowManager.addView(this, layoutParams);
    }

    public void removeView() {
        WindowManager windowManager;
        if (!isAttachedToWindow() || (windowManager = this.mWindowManager) == null || this.floatLayoutParams == null) {
            return;
        }
        windowManager.removeViewImmediate(this);
    }

    public void updateY(int i) {
        WindowManager.LayoutParams layoutParams = this.floatLayoutParams;
        if (layoutParams != null) {
            layoutParams.y = i;
        }
    }

    public void updateX(int i) {
        WindowManager.LayoutParams layoutParams = this.floatLayoutParams;
        if (layoutParams != null) {
            layoutParams.x = i;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (isScreenOnType()) {
            if (((motionEvent.getAction() == 1) | (motionEvent.getAction() == 0)) && this.screenBrightness != 0.0f && getWindow() != null) {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                float f = attributes.screenBrightness;
                float f2 = this.screenBrightness;
                if (f != f2) {
                    attributes.screenBrightness = f2;
                    getWindow().setAttributes(attributes);
                    LogUtil.d(this.TAG, "dispatchTouchEvent screenBrightness:" + this.screenBrightness);
                    return true;
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private Window getWindow() {
        Context context = this.mContext;
        if (context instanceof Activity) {
            return ((Activity) context).getWindow();
        }
        return null;
    }
}
