package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.CardInfoBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemCardTitleBindingImpl extends ItemCardTitleBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ShapeTextView mboundView0;

    public ItemCardTitleBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 1, sIncludes, sViewsWithIds));
    }

    private ItemCardTitleBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
        this.mDirtyFlags = -1L;
        ShapeTextView shapeTextView = (ShapeTextView) bindings[0];
        this.mboundView0 = shapeTextView;
        shapeTextView.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((CardInfoBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemCardTitleBinding
    public void setData(CardInfoBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeData((CardInfoBean) object, fieldId);
    }

    private boolean onChangeData(CardInfoBean Data, int fieldId) {
        if (fieldId == 0) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        if (fieldId != 94) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        int i;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CardInfoBean cardInfoBean = this.mData;
        long j2 = j & 7;
        String title = null;
        if (j2 != 0) {
            selected = cardInfoBean != null ? cardInfoBean.getSelected() : false;
            if (j2 != 0) {
                j |= selected ? 16L : 8L;
            }
            i = selected ? 18 : 16;
            if ((j & 5) != 0 && cardInfoBean != null) {
                title = cardInfoBean.getTitle();
            }
        } else {
            i = 0;
        }
        if ((j & 7) != 0) {
            DataBindingHelper.setSelected(this.mboundView0, selected);
            DataBindingHelper.setSelectedSize(this.mboundView0, i);
        }
        if ((j & 5) != 0) {
            TextViewBindingAdapter.setText(this.mboundView0, title);
        }
    }
}
