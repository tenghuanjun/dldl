package com.sqwan.common.widget.radiobutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.sqwan.common.util.LogUtil;
import com.sqwan.common.widget.radiobutton.SQRadioButton;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SQRadioGroup extends LinearLayout {
    private int mCheckedId;
    private SQRadioButton.OnCheckedChangeListener mChildOnCheckedChangeListener;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private PassThroughHierarchyChangeListener mPassThroughListener;
    private boolean mProtectFromCheckedChange;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(SQRadioGroup sQRadioGroup, int i);
    }

    public SQRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCheckedId = -1;
        this.mProtectFromCheckedChange = false;
        init();
    }

    public SQRadioGroup(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCheckedId = -1;
        this.mProtectFromCheckedChange = false;
        init();
    }

    private void init() {
        this.mChildOnCheckedChangeListener = new CheckedStateTracker();
        PassThroughHierarchyChangeListener passThroughHierarchyChangeListener = new PassThroughHierarchyChangeListener();
        this.mPassThroughListener = passThroughHierarchyChangeListener;
        super.setOnHierarchyChangeListener(passThroughHierarchyChangeListener);
    }

    public void check(int i) {
        if (i == -1 || i != this.mCheckedId) {
            int i2 = this.mCheckedId;
            if (i2 != -1) {
                setCheckedStateForView(i2, false);
            }
            if (i != -1) {
                setCheckedStateForView(i, true);
            }
            setCheckedId(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedId(int i) {
        LogUtil.i("current id is " + i);
        this.mCheckedId = i;
        OnCheckedChangeListener onCheckedChangeListener = this.mOnCheckedChangeListener;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(this, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckedStateForView(int i, boolean z) {
        View viewFindViewById = findViewById(i);
        if (viewFindViewById instanceof SQRadioButton) {
            ((SQRadioButton) viewFindViewById).setChecked(z);
        }
    }

    public int getCheckedRadioButtonId() {
        return this.mCheckedId;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    private class CheckedStateTracker implements SQRadioButton.OnCheckedChangeListener {
        private CheckedStateTracker() {
        }

        @Override // com.sqwan.common.widget.radiobutton.SQRadioButton.OnCheckedChangeListener
        public void onCheckedChanged(SQRadioButton sQRadioButton, boolean z) {
            if (SQRadioGroup.this.mProtectFromCheckedChange) {
                return;
            }
            SQRadioGroup.this.mProtectFromCheckedChange = true;
            if (SQRadioGroup.this.mCheckedId != -1) {
                SQRadioGroup sQRadioGroup = SQRadioGroup.this;
                sQRadioGroup.setCheckedStateForView(sQRadioGroup.mCheckedId, false);
            }
            SQRadioGroup.this.mProtectFromCheckedChange = false;
            SQRadioGroup.this.setCheckedId(sQRadioButton.getId());
        }
    }

    private class PassThroughHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        private PassThroughHierarchyChangeListener() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            if (view == SQRadioGroup.this && (view2 instanceof SQRadioButton)) {
                if (view2.getId() == -1) {
                    view2.setId(View.generateViewId());
                }
                ((SQRadioButton) view2).setOnCheckedChangeWidgetListener(SQRadioGroup.this.mChildOnCheckedChangeListener);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            if (view == SQRadioGroup.this && (view2 instanceof SQRadioButton)) {
                ((SQRadioButton) view2).setOnCheckedChangeWidgetListener(null);
            }
        }
    }
}
