package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.layout.ShapeLinearLayout;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DialogSelectTrumpetBindingImpl extends DialogSelectTrumpetBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ShapeLinearLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tv_title, 3);
        sparseIntArray.put(R.id.tv_cancel, 4);
        sparseIntArray.put(R.id.tv_confirm, 5);
    }

    public DialogSelectTrumpetBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private DialogSelectTrumpetBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (RecyclerView) bindings[2], (TextView) bindings[4], (TextView) bindings[5], (TextView) bindings[1], (TextView) bindings[3]);
        this.mDirtyFlags = -1L;
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[0];
        this.mboundView0 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
        this.rv.setTag(null);
        this.tvTip.setTag(null);
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
        setData((List) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogSelectTrumpetBinding
    public void setData(List Data) {
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
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        List list = this.mData;
        long j2 = j & 3;
        boolean z2 = false;
        if (j2 != 0) {
            int size = list != null ? list.size() : 0;
            z = size != 0;
            if (size == 0) {
                z2 = true;
            }
        } else {
            z = false;
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.rv, z2);
            DataBindingHelper.setRvData(this.rv, list);
            DataBindingHelper.setViewGone(this.tvTip, z);
        }
    }
}
