package com.huya.berry.sdklive.liveTool.floating;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class SubActionButton extends FrameLayout {
    public static final int THEME_DARK = 1;
    public static final int THEME_DARKER = 3;
    public static final int THEME_LIGHT = 0;
    public static final int THEME_LIGHTER = 2;
    private View m_contentViewAdded;

    public SubActionButton(Context context, FrameLayout.LayoutParams layoutParams, int i, Drawable drawable, View view, FrameLayout.LayoutParams layoutParams2) {
        super(context);
        this.m_contentViewAdded = null;
        setLayoutParams(layoutParams);
        if (view != null) {
            setContentView(view, layoutParams2);
        }
        setClickable(true);
    }

    public void setContentView(View view, FrameLayout.LayoutParams layoutParams) {
        if (layoutParams == null) {
            layoutParams = new FrameLayout.LayoutParams(-2, -2, 51);
        }
        View view2 = this.m_contentViewAdded;
        if (view2 != null) {
            removeView(view2);
        }
        view.setClickable(false);
        addView(view, layoutParams);
        this.m_contentViewAdded = view;
    }

    public void setContentView(View view) {
        setContentView(view, null);
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
        private FrameLayout.LayoutParams layoutParams;
        private int theme;

        public Builder(Context context) {
            this.context = context;
            setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 51));
            setTheme(0);
        }

        public Builder setLayoutParams(FrameLayout.LayoutParams layoutParams) {
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

        public Builder setContentView(View view) {
            this.contentView = view;
            return this;
        }

        public Builder setContentView(View view, FrameLayout.LayoutParams layoutParams) {
            this.contentView = view;
            this.contentParams = layoutParams;
            return this;
        }

        public SubActionButton build() {
            return new SubActionButton(this.context, this.layoutParams, this.theme, this.backgroundDrawable, this.contentView, this.contentParams);
        }
    }
}
