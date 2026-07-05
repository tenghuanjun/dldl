package com.sqwan.common.widget.radiobutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import com.sqwan.common.util.LogUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQRadioButton extends RelativeLayout implements Checkable {
    private boolean mBroadcasting;
    private boolean mChecked;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private OnCheckedChangeListener mOnCheckedChangeWidgetListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(SQRadioButton sQRadioButton, boolean z);
    }

    public SQRadioButton(Context context) {
        super(context);
    }

    public SQRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SQRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.sqwan.common.widget.radiobutton.Checkable
    public void setChecked(boolean z) {
        if (this.mChecked != z) {
            this.mChecked = z;
            refreshDrawableState();
            if (this.mBroadcasting) {
                return;
            }
            this.mBroadcasting = true;
            OnCheckedChangeListener onCheckedChangeListener = this.mOnCheckedChangeListener;
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(this, this.mChecked);
            }
            OnCheckedChangeListener onCheckedChangeListener2 = this.mOnCheckedChangeWidgetListener;
            if (onCheckedChangeListener2 != null) {
                onCheckedChangeListener2.onCheckedChanged(this, this.mChecked);
            }
            this.mBroadcasting = false;
        }
    }

    @Override // android.view.View
    public void refreshDrawableState() {
        super.refreshDrawableState();
        for (int i = 0; i < getChildCount(); i++) {
            KeyEvent.Callback childAt = getChildAt(i);
            if (childAt instanceof Checkable) {
                LogUtil.i("refresh image view state");
                ((Checkable) childAt).setChecked(isChecked());
            }
        }
    }

    @Override // com.sqwan.common.widget.radiobutton.Checkable
    public boolean isChecked() {
        return this.mChecked;
    }

    @Override // android.view.View
    public boolean performClick() {
        LogUtil.i("sq radio button perform click");
        toggle();
        return super.performClick();
    }

    @Override // com.sqwan.common.widget.radiobutton.Checkable
    public void toggle() {
        setChecked(!this.mChecked);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    void setOnCheckedChangeWidgetListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeWidgetListener = onCheckedChangeListener;
    }
}
