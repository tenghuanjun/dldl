package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.RecordBean;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityRecordDetailBindingImpl extends ActivityRecordDetailBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 8);
        sparseIntArray.put(R.id.icon, 9);
    }

    public ActivityRecordDetailBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ActivityRecordDetailBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ImageView) bindings[9], (TextView) bindings[1], (Navigation) bindings[8], (TextView) bindings[2], (TextView) bindings[6], (TextView) bindings[3], (TextView) bindings[7], (TextView) bindings[5], (TextView) bindings[4]);
        this.mDirtyFlags = -1L;
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        this.name.setTag(null);
        this.number.setTag(null);
        this.orderDetailes.setTag(null);
        this.orderId.setTag(null);
        this.orderNumber.setTag(null);
        this.orderPayType.setTag(null);
        this.orderTime.setTag(null);
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
        setData((RecordBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityRecordDetailBinding
    public void setData(RecordBean Data) {
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
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String create_time;
        String orderId;
        String payName;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        RecordBean recordBean = this.mData;
        long j2 = j & 3;
        String str7 = null;
        if (j2 != 0) {
            if (recordBean != null) {
                String title = recordBean.getTitle();
                String money = recordBean.getMoney();
                create_time = recordBean.getCreate_time();
                orderId = recordBean.getOrderId();
                payName = recordBean.getPayName();
                str7 = title;
                str6 = money;
            } else {
                str6 = null;
                create_time = null;
                orderId = null;
                payName = null;
            }
            str5 = "详情：" + str7;
            str = "-" + str6;
            str2 = "下单时间：" + create_time;
            str3 = "订单号：" + orderId;
            str4 = "购买方式：" + payName;
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.name, str7);
            TextViewBindingAdapter.setText(this.number, str);
            TextViewBindingAdapter.setText(this.orderDetailes, str5);
            TextViewBindingAdapter.setText(this.orderId, str3);
            TextViewBindingAdapter.setText(this.orderNumber, str);
            TextViewBindingAdapter.setText(this.orderPayType, str4);
            TextViewBindingAdapter.setText(this.orderTime, str2);
        }
    }
}
