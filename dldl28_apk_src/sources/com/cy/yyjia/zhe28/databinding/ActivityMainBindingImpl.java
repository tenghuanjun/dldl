package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityMainBindingImpl extends ActivityMainBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ConstraintLayout mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.vp, 8);
        sparseIntArray.put(R.id.ll_tab, 9);
        sparseIntArray.put(R.id.tv_unread, 10);
        sparseIntArray.put(R.id.iv_service, 11);
    }

    public ActivityMainBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }

    private ActivityMainBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[11], (LinearLayout) bindings[1], (LinearLayout) bindings[9], (AppCompatTextView) bindings[2], (TextView) bindings[4], (TextView) bindings[5], (TextView) bindings[6], (TextView) bindings[7], (TextView) bindings[10], (ViewPager2) bindings[8]);
        this.mDirtyFlags = -1L;
        this.llHome.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[3];
        this.mboundView3 = constraintLayout;
        constraintLayout.setTag(null);
        this.tv1.setTag(null);
        this.tv2.setTag(null);
        this.tv3.setTag(null);
        this.tv4.setTag(null);
        this.tv5.setTag(null);
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

    @Override // com.cy.yyjia.zhe28.databinding.ActivityMainBinding
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
        boolean z3;
        boolean z4;
        boolean z5;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mPosition;
        long j2 = 3 & j;
        if (j2 != 0) {
            z2 = i == 0;
            z3 = i == 2;
            z4 = i == 4;
            z5 = i == 1;
            z = i == 3;
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
            z5 = false;
        }
        long j3 = j & 2;
        boolean hideTrade = j3 != 0 ? Constant.INSTANCE.getHideTrade() : false;
        if (j3 != 0) {
            DataBindingHelper.setFitWindow(this.llHome, true);
            DataBindingHelper.setViewGone(this.mboundView3, hideTrade);
            DataBindingHelper.setViewGone(this.tv3, hideTrade);
            DataBindingHelper.setViewGone(this.tv4, hideTrade);
        }
        if (j2 != 0) {
            DataBindingHelper.setBold(this.tv1, z2);
            DataBindingHelper.setSelected(this.tv1, z2);
            DataBindingHelper.setBold(this.tv2, z5);
            DataBindingHelper.setSelected(this.tv2, z5);
            DataBindingHelper.setBold(this.tv3, z3);
            DataBindingHelper.setSelected(this.tv3, z3);
            DataBindingHelper.setBold(this.tv4, z);
            DataBindingHelper.setSelected(this.tv4, z);
            DataBindingHelper.setBold(this.tv5, z4);
            DataBindingHelper.setSelected(this.tv5, z4);
        }
    }
}
