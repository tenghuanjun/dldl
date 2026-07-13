package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.GMLogBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGmLogBindingImpl extends ItemGmLogBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemGmLogBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemGmLogBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
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
        setData((GMLogBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGmLogBinding
    public void setData(GMLogBean Data) {
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
        String showText;
        int code;
        String timeStr;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GMLogBean gMLogBean = this.mData;
        long j2 = j & 3;
        if (j2 == 0 || gMLogBean == null) {
            showText = null;
            code = 0;
            timeStr = null;
        } else {
            showText = gMLogBean.getShowText();
            timeStr = gMLogBean.getTimeStr();
            code = gMLogBean.getCode();
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, timeStr);
            TextViewBindingAdapter.setText(this.mboundView2, showText);
            DataBindingHelper.setLogColor(this.mboundView2, code);
        }
    }
}
