package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.VipCouponIndexBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemVipCouponLevelBindingImpl extends ItemVipCouponLevelBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;

    public ItemVipCouponLevelBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemVipCouponLevelBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
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
        setData((VipCouponIndexBean.Vip) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemVipCouponLevelBinding
    public void setData(VipCouponIndexBean.Vip Data) {
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
        return onChangeData((VipCouponIndexBean.Vip) object, fieldId);
    }

    private boolean onChangeData(VipCouponIndexBean.Vip Data, int fieldId) {
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
        float f;
        boolean selected;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        VipCouponIndexBean.Vip vip = this.mData;
        long j2 = j & 7;
        boolean my = false;
        String name = null;
        if (j2 != 0) {
            selected = vip != null ? vip.getSelected() : false;
            if (j2 != 0) {
                j |= selected ? 16L : 8L;
            }
            f = selected ? 1.0f : 0.6f;
            if ((j & 5) != 0) {
                if (vip != null) {
                    my = vip.getMy();
                    name = vip.getName();
                }
                my = !my;
            }
        } else {
            f = 0.0f;
            selected = false;
        }
        if ((5 & j) != 0) {
            DataBindingHelper.setViewGone(this.mboundView1, my);
            TextViewBindingAdapter.setText(this.mboundView2, name);
        }
        if ((j & 7) != 0) {
            DataBindingHelper.setSelected(this.mboundView2, selected);
            if (getBuildSdkInt() >= 11) {
                this.mboundView2.setAlpha(f);
            }
        }
    }
}
