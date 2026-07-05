package com.huya.berry.sdklive.liveTool.floating;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.huya.berry.gamesdk.utils.ResourceUtil;
import com.huya.berry.gamesdk.utils.SystemUtil;
import com.sqwan.liveshow.huya.SqR;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class FloatingActionButton extends FrameLayout {
    public static final int POSITION_BOTTOM_CENTER = 5;
    public static final int POSITION_BOTTOM_LEFT = 6;
    public static final int POSITION_BOTTOM_RIGHT = 4;
    public static final int POSITION_LEFT_CENTER = 7;
    public static final int POSITION_RIGHT_CENTER = 3;
    public static final int POSITION_TOP_CENTER = 1;
    public static final int POSITION_TOP_LEFT = 8;
    public static final int POSITION_TOP_RIGHT = 2;
    public static final int THEME_DARK = 1;
    public static final int THEME_LIGHT = 0;
    private View contentView;
    private boolean systemOverlay;

    public FloatingActionButton(Context context, ViewGroup.LayoutParams layoutParams, int i, Drawable drawable, int i2, View view, FrameLayout.LayoutParams layoutParams2, boolean z) {
        super(context);
        this.systemOverlay = z;
        if (!z && !(context instanceof Activity)) {
            throw new RuntimeException("Given context must be an instance of Activity, since this FAB is not a systemOverlay.");
        }
        setPosition(i2, layoutParams);
        if (view != null) {
            setContentView(view, layoutParams2);
        }
        setClickable(true);
        attach(layoutParams);
    }

    public void setPosition(int i, ViewGroup.LayoutParams layoutParams) {
        int i2;
        boolean z = false;
        switch (i) {
            case 1:
                i2 = 49;
                break;
            case 2:
                i2 = 53;
                break;
            case 3:
                i2 = 21;
                break;
            case 4:
            default:
                z = true;
                i2 = 85;
                break;
            case 5:
                i2 = 81;
                break;
            case 6:
                i2 = 83;
                break;
            case 7:
                i2 = 19;
                break;
            case 8:
                i2 = 51;
                break;
        }
        if (!this.systemOverlay) {
            try {
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                layoutParams2.gravity = i2;
                setLayoutParams(layoutParams2);
                return;
            } catch (ClassCastException unused) {
                throw new ClassCastException("layoutParams must be an instance of FrameLayout.LayoutParams, since this FAB is not a systemOverlay");
            }
        }
        try {
            WindowManager.LayoutParams layoutParams3 = (WindowManager.LayoutParams) layoutParams;
            layoutParams3.gravity = i2;
            if (z) {
                int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(ResourceUtil.getDimenResIDByName(SqR.dimen.hyberry_action_button_margin));
                layoutParams3.x = dimensionPixelSize;
                layoutParams3.y = dimensionPixelSize;
            }
            setLayoutParams(layoutParams3);
        } catch (ClassCastException unused2) {
            throw new ClassCastException("layoutParams must be an instance of WindowManager.LayoutParams, since this FAB is a systemOverlay");
        }
    }

    public void setContentView(View view, FrameLayout.LayoutParams layoutParams) {
        this.contentView = view;
        if (layoutParams == null) {
            layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
        }
        layoutParams.gravity = 17;
        view.setClickable(false);
        addView(view, layoutParams);
    }

    public void attach(ViewGroup.LayoutParams layoutParams) {
        if (this.systemOverlay) {
            try {
                getWindowManager().addView(this, layoutParams);
            } catch (SecurityException unused) {
                throw new SecurityException("Your application must have SYSTEM_ALERT_WINDOW permission to create a system window.");
            }
        } else {
            ((ViewGroup) getActivityContentView()).addView(this, layoutParams);
        }
    }

    public void detach() {
        if (this.systemOverlay) {
            getWindowManager().removeView(this);
        } else {
            ((ViewGroup) getActivityContentView()).removeView(this);
        }
    }

    public View getActivityContentView() {
        try {
            return ((Activity) getContext()).getWindow().getDecorView().findViewById(R.id.content);
        } catch (ClassCastException unused) {
            throw new ClassCastException("Please provide an Activity context for this FloatingActionButton.");
        }
    }

    public WindowManager getWindowManager() {
        return (WindowManager) getContext().getSystemService("window");
    }

    private void setBackgroundResource(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    public static class Builder {
        private Drawable backgroundDrawable;
        private FrameLayout.LayoutParams contentParams;
        private View contentView;
        private Context context;
        private ViewGroup.LayoutParams layoutParams;
        private int position;
        private boolean systemOverlay;
        private int theme;

        public Builder(Context context) {
            this.context = context;
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(ResourceUtil.getDimenResIDByName(SqR.dimen.hyberry_action_button_size));
            int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(ResourceUtil.getDimenResIDByName(SqR.dimen.hyberry_action_button_margin));
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize, 85);
            layoutParams.setMargins(dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2, dimensionPixelSize2);
            setLayoutParams(layoutParams);
            setTheme(0);
            setPosition(4);
            setSystemOverlay(false);
        }

        public static WindowManager.LayoutParams getDefaultSystemWindowParams(Context context) {
            WindowManager.LayoutParams defaultSystemWindowParams = SystemUtil.getDefaultSystemWindowParams();
            defaultSystemWindowParams.format = 1;
            return defaultSystemWindowParams;
        }

        public Builder setLayoutParams(ViewGroup.LayoutParams layoutParams) {
            this.layoutParams = layoutParams;
            return this;
        }

        public Builder setTheme(int i) {
            this.theme = i;
            return this;
        }

        public Builder setBackgroundDrawable(Drawable drawable) {
            this.backgroundDrawable = drawable;
            return this;
        }

        public Builder setBackgroundDrawable(int i) {
            return setBackgroundDrawable(this.context.getResources().getDrawable(i));
        }

        public Builder setPosition(int i) {
            this.position = i;
            return this;
        }

        public Builder setContentView(View view) {
            return setContentView(view, null);
        }

        public Builder setContentView(View view, FrameLayout.LayoutParams layoutParams) {
            this.contentView = view;
            this.contentParams = layoutParams;
            return this;
        }

        public Builder setSystemOverlay(boolean z) {
            this.systemOverlay = z;
            return this;
        }

        public FloatingActionButton build() {
            return new FloatingActionButton(this.context, this.layoutParams, this.theme, this.backgroundDrawable, this.position, this.contentView, this.contentParams, this.systemOverlay);
        }
    }
}
