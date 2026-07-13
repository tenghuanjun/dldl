package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.layout.ShapeConstraintLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DialogDealBuyNoticeBindingImpl extends DialogDealBuyNoticeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ShapeConstraintLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int variableId, Object variable) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.title, 1);
        sparseIntArray.put(R.id.tv_top_tag1, 2);
        sparseIntArray.put(R.id.tv_top_tag2, 3);
        sparseIntArray.put(R.id.tv_top_tag3, 4);
        sparseIntArray.put(R.id.tv_top_tag4, 5);
        sparseIntArray.put(R.id.tv_tag1, 6);
        sparseIntArray.put(R.id.f438tv, 7);
        sparseIntArray.put(R.id.tv_cancel, 8);
        sparseIntArray.put(R.id.tv_go, 9);
    }

    public DialogDealBuyNoticeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private DialogDealBuyNoticeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (TextView) bindings[1], (TextView) bindings[7], (ShapeTextView) bindings[8], (ShapeTextView) bindings[9], (TextView) bindings[6], (TextView) bindings[2], (TextView) bindings[3], (TextView) bindings[4], (TextView) bindings[5]);
        this.mDirtyFlags = -1L;
        ShapeConstraintLayout shapeConstraintLayout = (ShapeConstraintLayout) bindings[0];
        this.mboundView0 = shapeConstraintLayout;
        shapeConstraintLayout.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
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
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
    }
}
