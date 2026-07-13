package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UpdateBean;

/* JADX INFO: loaded from: classes2.dex */
public class DialogUpdateBindingImpl extends DialogUpdateBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.btn_download, 4);
        sparseIntArray.put(R.id.tv_service, 5);
    }

    public DialogUpdateBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }

    private DialogUpdateBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (RelativeLayout) bindings[4], (ProgressBar) bindings[2], (TextView) bindings[1], (TextView) bindings[3], (TextView) bindings[5]);
        this.mDirtyFlags = -1L;
        RelativeLayout relativeLayout = (RelativeLayout) bindings[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag(null);
        this.pb.setTag(null);
        this.tvContent.setTag(null);
        this.tvPb.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16L;
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
        if (55 == variableId) {
            setMax(((Integer) variable).intValue());
        } else if (64 == variableId) {
            setNumber((String) variable);
        } else if (23 == variableId) {
            setData((UpdateBean) variable);
        } else {
            if (21 != variableId) {
                return false;
            }
            setCurrent(((Integer) variable).intValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogUpdateBinding
    public void setMax(int Max) {
        this.mMax = Max;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(55);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogUpdateBinding
    public void setNumber(String Number) {
        this.mNumber = Number;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(64);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogUpdateBinding
    public void setData(UpdateBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogUpdateBinding
    public void setCurrent(int Current) {
        this.mCurrent = Current;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(21);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mMax;
        String str = this.mNumber;
        UpdateBean updateBean = this.mData;
        int i2 = this.mCurrent;
        long j2 = 17 & j;
        long j3 = 18 & j;
        long j4 = 20 & j;
        String updateDesc = (j4 == 0 || updateBean == null) ? null : updateBean.getUpdateDesc();
        long j5 = j & 24;
        if (j2 != 0) {
            this.pb.setMax(i);
        }
        if (j5 != 0) {
            this.pb.setProgress(i2);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.tvContent, updateDesc);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.tvPb, str);
        }
    }
}
