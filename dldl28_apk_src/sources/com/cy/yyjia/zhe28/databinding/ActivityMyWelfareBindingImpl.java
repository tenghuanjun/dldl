package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import com.google.android.material.tabs.TabLayout;
import com.hjq.shape.view.ShapeTextView;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityMyWelfareBindingImpl extends ActivityMyWelfareBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 4);
        sparseIntArray.put(R.id.tab, 5);
        sparseIntArray.put(R.id.ll_coupon, 6);
        sparseIntArray.put(R.id.srl, 7);
        sparseIntArray.put(R.id.rv, 8);
    }

    public ActivityMyWelfareBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ActivityMyWelfareBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[6], (Navigation) bindings[4], (RecyclerView) bindings[8], (SmartRefreshLayout) bindings[7], (TabLayout) bindings[5], (ShapeTextView) bindings[1], (ShapeTextView) bindings[2], (ShapeTextView) bindings[3]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        this.tv1.setTag(null);
        this.tv2.setTag(null);
        this.tv3.setTag(null);
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
        if (74 != variableId) {
            return false;
        }
        setPosition(((Integer) variable).intValue());
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityMyWelfareBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mPosition;
        long j2 = j & 3;
        if (j2 != 0) {
            z2 = i == 1;
            boolean z3 = i == 0;
            z = i == 2;
            z = z3;
        } else {
            z = false;
            z2 = false;
        }
        if (j2 != 0) {
            DataBindingHelper.setSelected(this.tv1, z);
            DataBindingHelper.setSelected(this.tv2, z2);
            DataBindingHelper.setSelected(this.tv3, z);
        }
    }
}
