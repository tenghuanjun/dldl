package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.YunIndexBean;

/* JADX INFO: loaded from: classes2.dex */
public class ItemYunPopDeviceBindingImpl extends ItemYunPopDeviceBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final TextView mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemYunPopDeviceBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 1, sIncludes, sViewsWithIds));
    }

    private ItemYunPopDeviceBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        TextView textView = (TextView) bindings[0];
        this.mboundView0 = textView;
        textView.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
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
        if (23 != variableId) {
            return false;
        }
        setData((YunIndexBean.Device) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemYunPopDeviceBinding
    public void setData(YunIndexBean.Device Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        YunIndexBean.Device device = this.mData;
        long j2 = j & 3;
        int colorFromResource = 0;
        boolean selected = false;
        String name = null;
        if (j2 != 0) {
            if (device != null) {
                selected = device.getSelected();
                name = device.getName();
            }
            if (j2 != 0) {
                j |= selected ? 8L : 4L;
            }
            colorFromResource = getColorFromResource(this.mboundView0, selected ? R.color.colorPrimary : R.color.color_text_1);
        }
        if ((j & 3) != 0) {
            TextViewBindingAdapter.setText(this.mboundView0, name);
            this.mboundView0.setTextColor(colorFromResource);
        }
    }
}
