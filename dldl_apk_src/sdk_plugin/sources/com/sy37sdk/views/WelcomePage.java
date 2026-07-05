package com.sy37sdk.views;

import android.app.Activity;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sy37sdk.utils.Util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class WelcomePage extends LinearLayout {
    private Activity activity;
    private ImageView iv_logo;
    private CustomProgressBar mBar;

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public WelcomePage(Activity activity) {
        super(activity);
        this.activity = activity;
        init();
        this.iv_logo = new ImageView(activity);
        this.mBar = new CustomProgressBar(activity);
        this.iv_logo.setImageResource(Util.getIdByName("sy37_logo_mobile", "drawable", activity.getPackageName(), activity));
        this.iv_logo.setPadding(0, 0, 0, (int) dpTopx(10.0f));
        addView(this.iv_logo);
        addView(this.mBar);
    }

    private void init() {
        setBackgroundResource(Util.getIdByName("sy37_welcom_main_bg", "drawable", this.activity.getPackageName(), this.activity));
        setOrientation(1);
        setGravity(17);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private float dpTopx(float f) {
        return TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }
}
