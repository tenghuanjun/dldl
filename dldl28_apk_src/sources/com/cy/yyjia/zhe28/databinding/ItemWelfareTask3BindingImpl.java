package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.CouponBean;
import com.cy.yyjia.zhe28.domain.DailyCouponBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemWelfareTask3BindingImpl extends ItemWelfareTask3Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView2;

    public ItemWelfareTask3BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ItemWelfareTask3BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ShapeTextView) bindings[3], (TextView) bindings[1]);
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        this.tvName.setTag(null);
        setRootTag(root);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
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
        if (38 == variableId) {
            setHideLine(((Boolean) variable).booleanValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((DailyCouponBean.Tier) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemWelfareTask3Binding
    public void setHideLine(boolean HideLine) {
        this.mHideLine = HideLine;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemWelfareTask3Binding
    public void setData(DailyCouponBean.Tier Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeDataCoupon((CouponBean) object, fieldId);
    }

    private boolean onChangeDataCoupon(CouponBean DataCoupon, int fieldId) {
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
        String str2;
        String useCondition;
        String amount;
        String btnText;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        DailyCouponBean.Tier tier = this.mData;
        long j2 = 13 & j;
        String min_amount = null;
        if (j2 != 0) {
            CouponBean coupon = tier != null ? tier.getCoupon() : null;
            updateRegistration(0, coupon);
            if (coupon != null) {
                amount = coupon.getAmount();
                useCondition = coupon.getUseCondition();
            } else {
                useCondition = null;
                amount = null;
            }
            str2 = ((("送" + useCondition) + "-") + amount) + "券";
            if ((j & 12) != 0) {
                if (tier != null) {
                    min_amount = tier.getMin_amount();
                    btnText = tier.getBtnText();
                } else {
                    btnText = null;
                }
                min_amount = btnText;
                str = "实付" + min_amount;
            } else {
                str = null;
            }
        } else {
            str = null;
            str2 = null;
        }
        if ((j & 12) != 0) {
            TextViewBindingAdapter.setText(this.btn, min_amount);
            TextViewBindingAdapter.setText(this.tvName, str);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str2);
        }
    }
}
