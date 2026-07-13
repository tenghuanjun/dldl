package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;
import com.hjq.shape.view.ShapeView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityPtbBindingImpl extends ActivityPtbBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_view1, 9);
        sparseIntArray.put(R.id.iv_tag, 10);
        sparseIntArray.put(R.id.navigation, 11);
        sparseIntArray.put(R.id.tv_ptb, 12);
        sparseIntArray.put(R.id.tv_username, 13);
        sparseIntArray.put(R.id.et, 14);
        sparseIntArray.put(R.id.tv_tag1, 15);
        sparseIntArray.put(R.id.tv_go, 16);
    }

    public ActivityPtbBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    private ActivityPtbBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (EditText) bindings[14], (ImageView) bindings[10], (ShapeView) bindings[9], (ShapeLinearLayout) bindings[1], (ShapeLinearLayout) bindings[2], (ShapeLinearLayout) bindings[3], (ShapeLinearLayout) bindings[4], (ShapeLinearLayout) bindings[5], (ShapeLinearLayout) bindings[6], (Navigation) bindings[11], (ShapeTextView) bindings[7], (ShapeTextView) bindings[16], (TextView) bindings[12], (TextView) bindings[15], (TextView) bindings[13], (ShapeTextView) bindings[8]);
        this.mDirtyFlags = -1L;
        this.ll1.setTag(null);
        this.ll2.setTag(null);
        this.ll3.setTag(null);
        this.ll4.setTag(null);
        this.ll5.setTag(null);
        this.ll6.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.tvAlipay.setTag(null);
        this.tvWx.setTag(null);
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
        if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else {
            if (4 != variableId) {
                return false;
            }
            setAlipay(((Boolean) variable).booleanValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityPtbBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityPtbBinding
    public void setAlipay(boolean Alipay) {
        this.mAlipay = Alipay;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(4);
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
        boolean z6;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mPosition;
        boolean z7 = this.mAlipay;
        long j2 = 5 & j;
        if (j2 != 0) {
            z2 = true;
            z = i == 5;
            z3 = i == 2;
            z4 = i == 6;
            z5 = i == 4;
            z6 = i == 1;
            if (i != 3) {
                z2 = false;
            }
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
            z5 = false;
            z6 = false;
        }
        long j3 = j & 6;
        boolean z8 = j3 != 0 ? !z7 : false;
        if (j2 != 0) {
            DataBindingHelper.setSelected(this.ll1, z6);
            DataBindingHelper.setSelected(this.ll2, z3);
            DataBindingHelper.setSelected(this.ll3, z2);
            DataBindingHelper.setSelected(this.ll4, z5);
            DataBindingHelper.setSelected(this.ll5, z);
            DataBindingHelper.setSelected(this.ll6, z4);
        }
        if (j3 != 0) {
            DataBindingHelper.setViewGone(this.tvAlipay, z8);
            DataBindingHelper.setViewGone(this.tvWx, z7);
        }
    }
}
