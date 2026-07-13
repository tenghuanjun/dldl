package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class DialogDailyCouponReceiveBindingImpl extends DialogDailyCouponReceiveBinding {
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
        sparseIntArray.put(R.id.iv_close, 3);
    }

    public DialogDailyCouponReceiveBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private DialogDailyCouponReceiveBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[1], (ImageView) bindings[3], (TextView) bindings[2]);
        this.mDirtyFlags = -1L;
        this.iv.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        this.f447tv.setTag(null);
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
        if (42 == variableId) {
            setImg((String) variable);
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogDailyCouponReceiveBinding
    public void setImg(String Img) {
        this.mImg = Img;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(42);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogDailyCouponReceiveBinding
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
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str = this.mImg;
        String str2 = this.mData;
        long j2 = 5 & j;
        long j3 = j & 6;
        if (j2 != 0) {
            DataBindingHelper.setImg(this.iv, str, null);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.f447tv, str2);
        }
    }
}
