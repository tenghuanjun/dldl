package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.domain.GameBannerBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGameDetailBannerBindingImpl extends ItemGameDetailBannerBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final CardView mboundView0;
    private final ImageView mboundView1;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemGameDetailBannerBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds));
    }

    private ItemGameDetailBannerBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        CardView cardView = (CardView) bindings[0];
        this.mboundView0 = cardView;
        cardView.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
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
        setData((GameBannerBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGameDetailBannerBinding
    public void setData(GameBannerBean Data) {
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
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        GameBannerBean gameBannerBean = this.mData;
        long j2 = j & 3;
        String pic = (j2 == 0 || gameBannerBean == null) ? null : gameBannerBean.getPic();
        if (j2 != 0) {
            DataBindingHelper.setImg(this.mboundView1, pic, null);
        }
    }
}
