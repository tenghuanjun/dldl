package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DialogPayBindingImpl extends DialogPayBinding {
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
        sparseIntArray.put(R.id.iv_close, 5);
        sparseIntArray.put(R.id.tv_tag1, 6);
        sparseIntArray.put(R.id.tv_pay, 7);
    }

    public DialogPayBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private DialogPayBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[5], (ShapeTextView) bindings[7], (TextView) bindings[1], (TextView) bindings[4], (TextView) bindings[6], (TextView) bindings[3], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[0];
        this.mboundView0 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
        this.tvPrice.setTag(null);
        this.tvPtb.setTag(null);
        this.tvWx.setTag(null);
        this.tvZfb.setTag(null);
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
        if (4 == variableId) {
            setAlipay(((Integer) variable).intValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogPayBinding
    public void setAlipay(int Alipay) {
        this.mAlipay = Alipay;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(4);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogPayBinding
    public void setData(String Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mAlipay;
        String str2 = this.mData;
        long j2 = 5 & j;
        boolean z3 = false;
        if (j2 != 0) {
            z = i == 1;
            z2 = i == 2;
            if (i == 3) {
                z3 = true;
            }
        } else {
            z = false;
            z2 = false;
        }
        long j3 = j & 6;
        if (j3 != 0) {
            str = "￥" + str2;
        } else {
            str = null;
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.tvPrice, str);
        }
        if (j2 != 0) {
            DataBindingHelper.setSelected(this.tvPtb, z3);
            DataBindingHelper.setSelected(this.tvWx, z);
            DataBindingHelper.setSelected(this.tvZfb, z2);
        }
    }
}
