package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CouponBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemVipCouponBindingImpl extends ItemVipCouponBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll, 7);
    }

    public ItemVipCouponBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ItemVipCouponBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ShapeTextView) bindings[5], (LinearLayout) bindings[7]);
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
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
        if (23 != variableId) {
            return false;
        }
        setData((CouponBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemVipCouponBinding
    public void setData(CouponBean Data) {
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
        return onChangeData((CouponBean) object, fieldId);
    }

    private boolean onChangeData(CouponBean Data, int fieldId) {
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
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        boolean z;
        boolean z2;
        boolean z3;
        String str6;
        String str7;
        String str8;
        String amount;
        boolean z4;
        String str9;
        int iIsReceive;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CouponBean couponBean = this.mData;
        String str10 = null;
        if ((j & 7) != 0) {
            long j2 = j & 5;
            if (j2 != 0) {
                if (couponBean != null) {
                    String desc = couponBean.getDesc();
                    String endTime = couponBean.getEndTime();
                    iIsReceive = couponBean.isReceive();
                    String conditionStr = couponBean.getConditionStr();
                    String name = couponBean.getName();
                    amount = couponBean.getAmount();
                    str10 = endTime;
                    str9 = name;
                    str8 = conditionStr;
                    str7 = desc;
                } else {
                    str9 = null;
                    str7 = null;
                    str8 = null;
                    amount = null;
                    iIsReceive = 0;
                }
                str10 = str10 + "到期";
                z = iIsReceive != 1;
                z4 = iIsReceive == 1;
                boolean z5 = iIsReceive == 2;
                str3 = str9 + "可领取";
                if (j2 != 0) {
                    j |= z5 ? 16L : 8L;
                }
                str6 = z5 ? "已领取" : "领取";
            } else {
                str6 = null;
                str3 = null;
                str7 = null;
                str8 = null;
                amount = null;
                z = false;
                z4 = false;
            }
            z3 = !(couponBean != null ? couponBean.getSelected() : false);
            str4 = str10;
            str5 = str7;
            str = amount;
            z2 = z4;
            str10 = str6;
            str2 = str8;
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
            z = false;
            z2 = false;
            z3 = false;
        }
        if ((5 & j) != 0) {
            DataBindingHelper.setSelected(this.btn, z);
            TextViewBindingAdapter.setText(this.btn, str10);
            this.btn.setEnabled(z2);
            TextViewBindingAdapter.setText(this.mboundView1, str);
            TextViewBindingAdapter.setText(this.mboundView2, str2);
            TextViewBindingAdapter.setText(this.mboundView3, str3);
            TextViewBindingAdapter.setText(this.mboundView4, str4);
            TextViewBindingAdapter.setText(this.mboundView6, str5);
        }
        if ((j & 7) != 0) {
            DataBindingHelper.setViewGone(this.mboundView6, z3);
        }
    }
}
