package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.domain.BbsBannerBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class LayoutBannerBbsBindingImpl extends LayoutBannerBbsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private OnClickListenerImpl mDataToBbsAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView1;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public LayoutBannerBbsBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds));
    }

    private LayoutBannerBbsBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) bindings[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
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
        setData((BbsBannerBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.LayoutBannerBbsBinding
    public void setData(BbsBannerBean Data) {
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
        String image;
        OnClickListenerImpl value;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        BbsBannerBean bbsBannerBean = this.mData;
        long j2 = j & 3;
        if (j2 == 0 || bbsBannerBean == null) {
            image = null;
            value = null;
        } else {
            image = bbsBannerBean.getImage();
            OnClickListenerImpl onClickListenerImpl = this.mDataToBbsAndroidViewViewOnClickListener;
            if (onClickListenerImpl == null) {
                onClickListenerImpl = new OnClickListenerImpl();
                this.mDataToBbsAndroidViewViewOnClickListener = onClickListenerImpl;
            }
            value = onClickListenerImpl.setValue(bbsBannerBean);
        }
        if (j2 != 0) {
            this.mboundView1.setOnClickListener(value);
            DataBindingHelper.setImg(this.mboundView1, image, null);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BbsBannerBean value;

        public OnClickListenerImpl setValue(BbsBannerBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.toBbs(arg0);
        }
    }
}
