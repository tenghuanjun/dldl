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
public class ItemDailyCouponBindingImpl extends ItemDailyCouponBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ShapeTextView mboundView1;
    private final ShapeTextView mboundView2;

    public ItemDailyCouponBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ItemDailyCouponBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (TextView) bindings[3]);
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ShapeTextView shapeTextView = (ShapeTextView) bindings[1];
        this.mboundView1 = shapeTextView;
        shapeTextView.setTag(null);
        ShapeTextView shapeTextView2 = (ShapeTextView) bindings[2];
        this.mboundView2 = shapeTextView2;
        shapeTextView2.setTag(null);
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
        setData((DailyCouponBean.Tier) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemDailyCouponBinding
    public void setData(DailyCouponBean.Tier Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
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
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        DailyCouponBean.Tier tier = this.mData;
        long j2 = 7 & j;
        String btnText = null;
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
            str2 = ((useCondition + "-") + amount) + "券";
            if ((j & 6) == 0 || tier == null) {
                str = null;
            } else {
                String min_amount = tier.getMin_amount();
                btnText = tier.getBtnText();
                str = min_amount;
            }
        } else {
            str = null;
            str2 = null;
        }
        if ((j & 6) != 0) {
            TextViewBindingAdapter.setText(this.btn, btnText);
            TextViewBindingAdapter.setText(this.mboundView1, str);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, str2);
        }
    }
}
