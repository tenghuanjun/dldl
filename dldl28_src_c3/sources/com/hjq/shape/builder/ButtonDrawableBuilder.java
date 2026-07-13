package com.hjq.shape.builder;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.CompoundButton;
import androidx.core.widget.CompoundButtonCompat;
import com.hjq.shape.R;
import com.hjq.shape.config.ICompoundButtonStyleable;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class ButtonDrawableBuilder {
    private Drawable mButtonCheckedDrawable;
    private Drawable mButtonDisabledDrawable;
    private Drawable mButtonDrawable;
    private Drawable mButtonFocusedDrawable;
    private Drawable mButtonPressedDrawable;
    private Drawable mButtonSelectedDrawable;
    private final CompoundButton mCompoundButton;

    public ButtonDrawableBuilder(CompoundButton compoundButton, TypedArray typedArray, ICompoundButtonStyleable iCompoundButtonStyleable) {
        this.mCompoundButton = compoundButton;
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonDrawableStyleable())) {
            if (typedArray.getResourceId(iCompoundButtonStyleable.getButtonDrawableStyleable(), 0) != R.drawable.shape_view_placeholder) {
                this.mButtonDrawable = typedArray.getDrawable(iCompoundButtonStyleable.getButtonDrawableStyleable());
            } else {
                this.mButtonDrawable = CompoundButtonCompat.getButtonDrawable(compoundButton);
            }
        } else {
            this.mButtonDrawable = null;
            compoundButton.setButtonDrawable((Drawable) null);
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonPressedDrawableStyleable())) {
            this.mButtonPressedDrawable = typedArray.getDrawable(iCompoundButtonStyleable.getButtonPressedDrawableStyleable());
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonCheckedDrawableStyleable())) {
            this.mButtonCheckedDrawable = typedArray.getDrawable(iCompoundButtonStyleable.getButtonCheckedDrawableStyleable());
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonDisabledDrawableStyleable())) {
            this.mButtonDisabledDrawable = typedArray.getDrawable(iCompoundButtonStyleable.getButtonDisabledDrawableStyleable());
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonFocusedDrawableStyleable())) {
            this.mButtonFocusedDrawable = typedArray.getDrawable(iCompoundButtonStyleable.getButtonFocusedDrawableStyleable());
        }
        if (typedArray.hasValue(iCompoundButtonStyleable.getButtonSelectedDrawableStyleable())) {
            this.mButtonSelectedDrawable = typedArray.getDrawable(iCompoundButtonStyleable.getButtonSelectedDrawableStyleable());
        }
    }

    public ButtonDrawableBuilder setButtonDrawable(Drawable drawable) {
        Drawable drawable2 = this.mButtonPressedDrawable;
        Drawable drawable3 = this.mButtonDrawable;
        if (drawable2 == drawable3) {
            this.mButtonPressedDrawable = drawable;
        }
        if (this.mButtonCheckedDrawable == drawable3) {
            this.mButtonCheckedDrawable = drawable;
        }
        if (this.mButtonDisabledDrawable == drawable3) {
            this.mButtonDisabledDrawable = drawable;
        }
        if (this.mButtonFocusedDrawable == drawable3) {
            this.mButtonFocusedDrawable = drawable;
        }
        if (this.mButtonSelectedDrawable == drawable3) {
            this.mButtonSelectedDrawable = drawable;
        }
        this.mButtonDrawable = drawable;
        return this;
    }

    public Drawable getButtonDrawable() {
        return this.mButtonDrawable;
    }

    public ButtonDrawableBuilder setButtonPressedDrawable(Drawable drawable) {
        this.mButtonPressedDrawable = drawable;
        return this;
    }

    public Drawable getButtonPressedDrawable() {
        return this.mButtonPressedDrawable;
    }

    public ButtonDrawableBuilder setButtonCheckedDrawable(Drawable drawable) {
        this.mButtonCheckedDrawable = drawable;
        return this;
    }

    public Drawable getButtonCheckedDrawable() {
        return this.mButtonCheckedDrawable;
    }

    public ButtonDrawableBuilder setButtonDisabledDrawable(Drawable drawable) {
        this.mButtonDisabledDrawable = drawable;
        return this;
    }

    public Drawable getButtonDisabledDrawable() {
        return this.mButtonDisabledDrawable;
    }

    public ButtonDrawableBuilder setButtonFocusedDrawable(Drawable drawable) {
        this.mButtonFocusedDrawable = drawable;
        return this;
    }

    public Drawable getButtonFocusedDrawable() {
        return this.mButtonFocusedDrawable;
    }

    public ButtonDrawableBuilder setButtonSelectedDrawable(Drawable drawable) {
        this.mButtonSelectedDrawable = drawable;
        return this;
    }

    public Drawable getButtonSelectedDrawable() {
        return this.mButtonSelectedDrawable;
    }

    public void intoButtonDrawable() {
        Drawable drawable = this.mButtonDrawable;
        if (drawable == null) {
            return;
        }
        if (this.mButtonPressedDrawable == null && this.mButtonCheckedDrawable == null && this.mButtonDisabledDrawable == null && this.mButtonFocusedDrawable == null && this.mButtonSelectedDrawable == null) {
            this.mCompoundButton.setButtonDrawable(drawable);
            return;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable2 = this.mButtonPressedDrawable;
        if (drawable2 != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, drawable2);
        }
        Drawable drawable3 = this.mButtonCheckedDrawable;
        if (drawable3 != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_checked}, drawable3);
        }
        Drawable drawable4 = this.mButtonDisabledDrawable;
        if (drawable4 != null) {
            stateListDrawable.addState(new int[]{-16842910}, drawable4);
        }
        Drawable drawable5 = this.mButtonFocusedDrawable;
        if (drawable5 != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_focused}, drawable5);
        }
        Drawable drawable6 = this.mButtonSelectedDrawable;
        if (drawable6 != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_selected}, drawable6);
        }
        stateListDrawable.addState(new int[0], this.mButtonDrawable);
        this.mCompoundButton.setButtonDrawable(stateListDrawable);
    }
}
