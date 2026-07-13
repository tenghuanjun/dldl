package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemGameTypeBindingImpl extends ItemGameTypeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ImageView mboundView2;

    public ItemGameTypeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemGameTypeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ShapeTextView) bindings[1]);
        this.mDirtyFlags = -1L;
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[2];
        this.mboundView2 = imageView;
        imageView.setTag(null);
        this.f465tv.setTag(null);
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
        setData((TypeBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemGameTypeBinding
    public void setData(TypeBean Data) {
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
        return onChangeData((TypeBean) object, fieldId);
    }

    private boolean onChangeData(TypeBean Data, int fieldId) {
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
        String img;
        String name;
        boolean selected;
        boolean z;
        boolean zIsEmpty;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        TypeBean typeBean = this.mData;
        long j2 = 7 & j;
        boolean z2 = false;
        if (j2 != 0) {
            if ((j & 5) != 0) {
                if (typeBean != null) {
                    img = typeBean.getImg();
                    name = typeBean.getName();
                } else {
                    img = null;
                    name = null;
                }
                zIsEmpty = TextUtils.isEmpty(img);
                z = !zIsEmpty;
            } else {
                img = null;
                name = null;
                zIsEmpty = false;
                z = false;
            }
            if (typeBean != null) {
                selected = typeBean.getSelected();
                z2 = zIsEmpty;
            } else {
                z2 = zIsEmpty;
                selected = false;
            }
        } else {
            img = null;
            name = null;
            selected = false;
            z = false;
        }
        if ((j & 5) != 0) {
            DataBindingHelper.setViewGone(this.mboundView2, z2);
            DataBindingHelper.setImg(this.mboundView2, img, null);
            DataBindingHelper.setViewGone(this.f465tv, z);
            TextViewBindingAdapter.setText(this.f465tv, name);
        }
        if (j2 != 0) {
            DataBindingHelper.setSelected(this.f465tv, selected);
        }
    }
}
