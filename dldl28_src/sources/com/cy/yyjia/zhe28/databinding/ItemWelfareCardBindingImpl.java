package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.WelfareBean;

/* JADX INFO: loaded from: classes2.dex */
public class ItemWelfareCardBindingImpl extends ItemWelfareCardBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemWelfareCardBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ItemWelfareCardBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
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
        setData((WelfareBean.Card) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemWelfareCardBinding
    public void setData(WelfareBean.Card Data) {
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
        String tag;
        String title;
        String desc;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        WelfareBean.Card card = this.mData;
        long j2 = j & 3;
        if (j2 == 0 || card == null) {
            tag = null;
            title = null;
            desc = null;
        } else {
            tag = card.getTag();
            title = card.getTitle();
            desc = card.getDesc();
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, title);
            TextViewBindingAdapter.setText(this.mboundView2, desc);
            TextViewBindingAdapter.setText(this.mboundView3, tag);
        }
    }
}
