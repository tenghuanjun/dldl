package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.CollectionBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemHomePicBindingImpl extends ItemHomePicBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private OnClickListenerImpl mDataOnPicClickAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final ImageView mboundView2;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemHomePicBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }

    private ItemHomePicBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
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
        setData((CollectionBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemHomePicBinding
    public void setData(CollectionBean Data) {
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
        boolean zIsEmpty;
        String name;
        OnClickListenerImpl value;
        String img_url;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CollectionBean collectionBean = this.mData;
        long j2 = j & 3;
        if (j2 != 0) {
            if (collectionBean != null) {
                name = collectionBean.getName();
                OnClickListenerImpl onClickListenerImpl = this.mDataOnPicClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mDataOnPicClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                value = onClickListenerImpl.setValue(collectionBean);
                img_url = collectionBean.getImg_url();
            } else {
                name = null;
                value = null;
                img_url = null;
            }
            zIsEmpty = TextUtils.isEmpty(name);
        } else {
            zIsEmpty = false;
            name = null;
            value = null;
            img_url = null;
        }
        if (j2 != 0) {
            this.mboundView0.setOnClickListener(value);
            DataBindingHelper.setViewGone(this.mboundView1, zIsEmpty);
            TextViewBindingAdapter.setText(this.mboundView1, name);
            DataBindingHelper.setImg(this.mboundView2, img_url, null);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private CollectionBean value;

        public OnClickListenerImpl setValue(CollectionBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onPicClick(arg0);
        }
    }
}
