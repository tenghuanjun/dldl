package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.domain.BbsBlockBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeImageView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemBbsCateBindingImpl extends ItemBbsCateBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ShapeImageView mboundView1;
    private final ImageView mboundView2;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemBbsCateBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemBbsCateBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ShapeImageView shapeImageView = (ShapeImageView) bindings[1];
        this.mboundView1 = shapeImageView;
        shapeImageView.setTag(null);
        ImageView imageView = (ImageView) bindings[2];
        this.mboundView2 = imageView;
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
        setData((BbsBlockBean.ListBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemBbsCateBinding
    public void setData(BbsBlockBean.ListBean Data) {
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
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        BbsBlockBean.ListBean listBean = this.mData;
        long j2 = j & 3;
        boolean selected = false;
        String icon = null;
        if (j2 != 0) {
            if (listBean != null) {
                selected = listBean.getSelected();
                icon = listBean.getIcon();
            }
            boolean z2 = selected;
            selected = !selected;
            z = z2;
        } else {
            z = false;
        }
        if (j2 != 0) {
            DataBindingHelper.setGameIcon(this.mboundView1, icon);
            DataBindingHelper.setViewGone(this.mboundView1, selected);
            DataBindingHelper.setGameIcon(this.mboundView2, icon);
            DataBindingHelper.setViewGone(this.mboundView2, z);
        }
    }
}
