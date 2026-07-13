package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityLotteryRecordBindingImpl extends ActivityLotteryRecordBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final View mboundView1;
    private final View mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.rv, 5);
    }

    public ActivityLotteryRecordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private ActivityLotteryRecordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (RecyclerView) bindings[5], (ShapeTextView) bindings[2], (ShapeTextView) bindings[4]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        View view = (View) bindings[1];
        this.mboundView1 = view;
        view.setTag(null);
        View view2 = (View) bindings[3];
        this.mboundView3 = view2;
        view2.setTag(null);
        this.tv1.setTag(null);
        this.tv2.setTag(null);
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
        if (118 != variableId) {
            return false;
        }
        setType(((Integer) variable).intValue());
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityLotteryRecordBinding
    public void setType(int Type) {
        this.mType = Type;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(118);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        boolean z3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mType;
        long j2 = j & 3;
        if (j2 != 0) {
            boolean z4 = i != 1;
            z2 = i == 1;
            z3 = i != 2;
            z = i == 2;
            z = z4;
        } else {
            z = false;
            z2 = false;
            z3 = false;
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.mboundView1, z);
            DataBindingHelper.setViewGone(this.mboundView3, z3);
            DataBindingHelper.setSelected(this.tv1, z2);
            DataBindingHelper.setSelected(this.tv2, z);
        }
    }
}
