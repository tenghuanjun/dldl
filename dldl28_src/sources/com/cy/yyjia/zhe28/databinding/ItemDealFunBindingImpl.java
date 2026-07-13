package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.BtnBean;
import com.cy.yyjia.zhe28.domain.DealIndexBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemDealFunBindingImpl extends ItemDealFunBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private OnClickListenerImpl mDataLinkOnClickAndroidViewViewOnClickListener;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemDealFunBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ItemDealFunBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
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
        setData((DealIndexBean.FunBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemDealFunBinding
    public void setData(DealIndexBean.FunBean Data) {
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
        OnClickListenerImpl value;
        String name;
        String desc;
        String icon;
        BtnBean link;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        DealIndexBean.FunBean funBean = this.mData;
        long j2 = j & 3;
        if (j2 != 0) {
            if (funBean != null) {
                link = funBean.getLink();
                name = funBean.getName();
                desc = funBean.getDesc();
                icon = funBean.getIcon();
            } else {
                link = null;
                name = null;
                desc = null;
                icon = null;
            }
            if (link != null) {
                OnClickListenerImpl onClickListenerImpl = this.mDataLinkOnClickAndroidViewViewOnClickListener;
                if (onClickListenerImpl == null) {
                    onClickListenerImpl = new OnClickListenerImpl();
                    this.mDataLinkOnClickAndroidViewViewOnClickListener = onClickListenerImpl;
                }
                value = onClickListenerImpl.setValue(link);
            } else {
                value = null;
            }
        } else {
            value = null;
            name = null;
            desc = null;
            icon = null;
        }
        if (j2 != 0) {
            this.mboundView0.setOnClickListener(value);
            DataBindingHelper.setImg(this.mboundView1, icon, null);
            TextViewBindingAdapter.setText(this.mboundView2, name);
            TextViewBindingAdapter.setText(this.mboundView3, desc);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BtnBean value;

        public OnClickListenerImpl setValue(BtnBean value) {
            this.value = value;
            if (value == null) {
                return null;
            }
            return this;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View arg0) {
            this.value.onClick(arg0);
        }
    }
}
