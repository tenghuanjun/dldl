package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.DealBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemDealSellChildBindingImpl extends ItemDealSellChildBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;

    public ItemDealSellChildBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemDealSellChildBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1);
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
        TextView textView3 = (TextView) bindings[4];
        this.mboundView4 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[5];
        this.mboundView5 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[6];
        this.mboundView6 = textView5;
        textView5.setTag(null);
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
        if (40 == variableId) {
            setIcon((String) variable);
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((DealBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemDealSellChildBinding
    public void setIcon(String Icon) {
        this.mIcon = Icon;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(40);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemDealSellChildBinding
    public void setData(DealBean Data) {
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
        return onChangeData((DealBean) object, fieldId);
    }

    private boolean onChangeData(DealBean Data, int fieldId) {
        if (fieldId != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        boolean z;
        boolean z2;
        String name;
        int platformType;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str2 = this.mIcon;
        DealBean dealBean = this.mData;
        long j2 = 6 & j;
        long j3 = j & 5;
        String totalMoney = null;
        if (j3 != 0) {
            if (dealBean != null) {
                platformType = dealBean.getPlatformType();
                totalMoney = dealBean.getTotalMoney();
                name = dealBean.getName();
            } else {
                name = null;
                platformType = 0;
            }
            z = platformType == 0;
            z2 = platformType != 2;
            z = platformType != 1;
            str = ("累计消费：" + totalMoney) + "元";
            totalMoney = name;
        } else {
            str = null;
            z = false;
            z2 = false;
        }
        if (j2 != 0) {
            DataBindingHelper.setGameIcon(this.mboundView1, str2);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, totalMoney);
            DataBindingHelper.setViewGone(this.mboundView3, z);
            DataBindingHelper.setViewGone(this.mboundView4, z2);
            DataBindingHelper.setViewGone(this.mboundView5, z);
            TextViewBindingAdapter.setText(this.mboundView6, str);
        }
    }
}
