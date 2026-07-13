package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.CreditResult;
import com.hjq.shape.layout.ShapeLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ItemJfRecordBindingImpl extends ItemJfRecordBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ShapeLinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemJfRecordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ItemJfRecordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[0];
        this.mboundView0 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[4];
        this.mboundView4 = textView4;
        textView4.setTag(null);
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
        setData((CreditResult) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemJfRecordBinding
    public void setData(CreditResult Data) {
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
        String str;
        String dateline;
        String str2;
        int currCredit;
        String rewardType;
        int credit;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CreditResult creditResult = this.mData;
        long j2 = j & 3;
        String typeName = null;
        if (j2 != 0) {
            if (creditResult != null) {
                typeName = creditResult.getTypeName();
                currCredit = creditResult.getCurrCredit();
                dateline = creditResult.getDateline();
                rewardType = creditResult.getRewardType();
                credit = creditResult.getCredit();
            } else {
                currCredit = 0;
                dateline = null;
                rewardType = null;
                credit = 0;
            }
            typeName = typeName + "";
            str = "余额" + currCredit;
            str2 = rewardType + credit;
        } else {
            str = null;
            dateline = null;
            str2 = null;
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, typeName);
            TextViewBindingAdapter.setText(this.mboundView2, str2);
            TextViewBindingAdapter.setText(this.mboundView3, str);
            TextViewBindingAdapter.setText(this.mboundView4, dateline);
        }
    }
}
