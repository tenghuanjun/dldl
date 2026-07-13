package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.ReceiveStatusBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemSelectTrumpetBindingImpl extends ItemSelectTrumpetBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ShapeTextView mboundView2;

    public ItemSelectTrumpetBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemSelectTrumpetBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (TextView) bindings[1]);
        this.mDirtyFlags = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[2];
        this.mboundView2 = shapeTextView;
        shapeTextView.setTag(null);
        this.name.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((ReceiveStatusBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemSelectTrumpetBinding
    public void setData(ReceiveStatusBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((ReceiveStatusBean) object, fieldId);
    }

    private boolean onChangeData(ReceiveStatusBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 94) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean selected;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ReceiveStatusBean receiveStatusBean = this.mData;
        long j2 = 7 & j;
        boolean zIsCollected = false;
        String name = null;
        if (j2 != 0) {
            selected = receiveStatusBean != null ? receiveStatusBean.getSelected() : false;
            if ((j & 5) != 0) {
                if (receiveStatusBean != null) {
                    zIsCollected = receiveStatusBean.isCollected();
                    name = receiveStatusBean.getName();
                }
                zIsCollected = !zIsCollected;
            }
        } else {
            selected = false;
        }
        if ((j & 5) != 0) {
            DataBindingHelper.setViewGone(this.mboundView2, zIsCollected);
            this.name.setEnabled(zIsCollected);
            TextViewBindingAdapter.setText(this.name, name);
        }
        if (j2 != 0) {
            DataBindingHelper.setSelected(this.name, selected);
        }
    }
}
