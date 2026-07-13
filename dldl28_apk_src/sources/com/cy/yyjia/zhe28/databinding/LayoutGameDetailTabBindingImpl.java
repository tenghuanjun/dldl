package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class LayoutGameDetailTabBindingImpl extends LayoutGameDetailTabBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public LayoutGameDetailTabBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds));
    }

    private LayoutGameDetailTabBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[1]);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        this.f473tv.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        if (94 == variableId) {
            setSelected(((Boolean) variable).booleanValue());
        } else {
            if (111 != variableId) {
                return false;
            }
            setText((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.LayoutGameDetailTabBinding
    public void setSelected(boolean Selected) {
        this.mSelected = Selected;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(94);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.LayoutGameDetailTabBinding
    public void setText(String Text) {
        this.mText = Text;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(111);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        int colorFromResource;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        boolean z = this.mSelected;
        String str = this.mText;
        long j2 = j & 5;
        if (j2 != 0) {
            if (j2 != 0) {
                j |= z ? 16L : 8L;
            }
            colorFromResource = getColorFromResource(this.f473tv, z ? R.color.color_text_1 : R.color.color_text_3);
        } else {
            colorFromResource = 0;
        }
        long j3 = 6 & j;
        if ((j & 5) != 0) {
            DataBindingHelper.setBold(this.f473tv, z);
            this.f473tv.setTextColor(colorFromResource);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.f473tv, str);
        }
    }
}
