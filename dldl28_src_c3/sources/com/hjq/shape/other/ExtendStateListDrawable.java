package com.hjq.shape.other;

import android.R;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import java.util.HashMap;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class ExtendStateListDrawable extends StateListDrawable {
    private final HashMap<int[], Drawable> mDrawableMap = new HashMap<>();
    private static final int[] STATE_DEFAULT = new int[0];
    private static final int[] STATE_PRESSED = {R.attr.state_pressed};
    private static final int[] STATE_CHECKED = {R.attr.state_checked};
    private static final int[] STATE_DISABLED = {-16842910};
    private static final int[] STATE_FOCUSED = {R.attr.state_focused};
    private static final int[] STATE_SELECTED = {R.attr.state_selected};

    @Override // android.graphics.drawable.StateListDrawable
    public void addState(int[] iArr, Drawable drawable) {
        super.addState(iArr, drawable);
        if (drawable == null) {
            return;
        }
        this.mDrawableMap.put(iArr, drawable);
    }

    public void setDefaultDrawable(Drawable drawable) {
        addState(STATE_DEFAULT, drawable);
    }

    public Drawable getDefaultDrawable() {
        return this.mDrawableMap.get(STATE_DEFAULT);
    }

    public void setPressedDrawable(Drawable drawable) {
        addState(STATE_PRESSED, drawable);
    }

    public Drawable getPressedDrawable() {
        return this.mDrawableMap.get(STATE_PRESSED);
    }

    public void setCheckDrawable(Drawable drawable) {
        addState(STATE_CHECKED, drawable);
    }

    public Drawable getCheckDrawable() {
        return this.mDrawableMap.get(STATE_CHECKED);
    }

    public void setDisabledDrawable(Drawable drawable) {
        addState(STATE_DISABLED, drawable);
    }

    public Drawable getDisabledDrawable() {
        return this.mDrawableMap.get(STATE_DISABLED);
    }

    public void setFocusedDrawable(Drawable drawable) {
        addState(STATE_FOCUSED, drawable);
    }

    public Drawable getFocusedDrawable() {
        return this.mDrawableMap.get(STATE_FOCUSED);
    }

    public void setSelectDrawable(Drawable drawable) {
        addState(STATE_SELECTED, drawable);
    }

    public Drawable getSelectDrawable() {
        return this.mDrawableMap.get(STATE_SELECTED);
    }
}
