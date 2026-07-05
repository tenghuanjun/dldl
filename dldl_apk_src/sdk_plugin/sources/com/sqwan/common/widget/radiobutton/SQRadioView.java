package com.sqwan.common.widget.radiobutton;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQRadioView extends ImageView implements Checkable {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private boolean mChecked;

    @Override // com.sqwan.common.widget.radiobutton.Checkable
    public void toggle() {
    }

    public SQRadioView(Context context) {
        super(context);
    }

    public SQRadioView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SQRadioView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.mChecked) {
            mergeDrawableStates(iArrOnCreateDrawableState, CHECKED_STATE_SET);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // com.sqwan.common.widget.radiobutton.Checkable
    public void setChecked(boolean z) {
        this.mChecked = z;
        refreshDrawableState();
    }

    @Override // com.sqwan.common.widget.radiobutton.Checkable
    public boolean isChecked() {
        return this.mChecked;
    }
}
