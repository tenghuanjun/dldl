package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.DealMoneyRecordBean;

/* JADX INFO: loaded from: classes2.dex */
public class ItemDealMoneyRecordBindingImpl extends ItemDealMoneyRecordBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemDealMoneyRecordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }

    private ItemDealMoneyRecordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[2];
        this.mboundView2 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[4];
        this.mboundView4 = textView4;
        textView4.setTag(null);
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
        setData((DealMoneyRecordBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemDealMoneyRecordBinding
    public void setData(DealMoneyRecordBean Data) {
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
        String str;
        String create_time;
        String money;
        String title;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        DealMoneyRecordBean dealMoneyRecordBean = this.mData;
        long j2 = j & 3;
        String orderId = null;
        if (j2 != 0) {
            if (dealMoneyRecordBean != null) {
                title = dealMoneyRecordBean.getTitle();
                create_time = dealMoneyRecordBean.getCreate_time();
                orderId = dealMoneyRecordBean.getOrderId();
                money = dealMoneyRecordBean.getMoney();
            } else {
                title = null;
                create_time = null;
                money = null;
            }
            orderId = title;
            str = "订单号：" + orderId;
        } else {
            str = null;
            create_time = null;
            money = null;
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, orderId);
            TextViewBindingAdapter.setText(this.mboundView2, money);
            TextViewBindingAdapter.setText(this.mboundView3, str);
            TextViewBindingAdapter.setText(this.mboundView4, create_time);
        }
    }
}
