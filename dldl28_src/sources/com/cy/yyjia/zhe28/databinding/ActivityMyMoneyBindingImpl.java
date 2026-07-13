package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityMyMoneyBindingImpl extends ActivityMyMoneyBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView2;
    private final TextView mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 12);
        sparseIntArray.put(R.id.tv_bill, 13);
        sparseIntArray.put(R.id.tv_tag1, 14);
        sparseIntArray.put(R.id.ll_voucher, 15);
        sparseIntArray.put(R.id.ll_flb, 16);
        sparseIntArray.put(R.id.et, 17);
        sparseIntArray.put(R.id.tv_go, 18);
    }

    public ActivityMyMoneyBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }

    private ActivityMyMoneyBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (EditText) bindings[17], (ShapeLinearLayout) bindings[4], (ShapeLinearLayout) bindings[5], (ShapeLinearLayout) bindings[6], (ShapeLinearLayout) bindings[7], (ShapeLinearLayout) bindings[8], (ShapeLinearLayout) bindings[9], (LinearLayout) bindings[16], (LinearLayout) bindings[15], (Navigation) bindings[12], (ShapeTextView) bindings[13], (ShapeTextView) bindings[18], (TextView) bindings[1], (TextView) bindings[14], (TextView) bindings[11], (TextView) bindings[10]);
        this.mDirtyFlags = -1L;
        this.ll1.setTag(null);
        this.ll2.setTag(null);
        this.ll3.setTag(null);
        this.ll4.setTag(null);
        this.ll5.setTag(null);
        this.ll6.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) bindings[2];
        this.mboundView2 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        this.tvPtb.setTag(null);
        this.tvWx.setTag(null);
        this.tvZfb.setTag(null);
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
        if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else if (4 == variableId) {
            setAlipay(((Boolean) variable).booleanValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((UserBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityMyMoneyBinding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityMyMoneyBinding
    public void setAlipay(boolean Alipay) {
        this.mAlipay = Alipay;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(4);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityMyMoneyBinding
    public void setData(UserBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        String wallet_balance;
        String totalCoupon;
        String welfare;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mPosition;
        boolean z7 = this.mAlipay;
        UserBean userBean = this.mData;
        long j2 = 9 & j;
        if (j2 != 0) {
            z = i == 5;
            z3 = i == 2;
            z4 = i == 6;
            z5 = i == 4;
            z6 = i == 1;
            z2 = i == 3;
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
            z5 = false;
            z6 = false;
        }
        long j3 = j & 10;
        boolean z8 = j3 != 0 ? !z7 : false;
        long j4 = j & 12;
        if (j4 == 0 || userBean == null) {
            wallet_balance = null;
            totalCoupon = null;
            welfare = null;
        } else {
            wallet_balance = userBean.getWallet_balance();
            totalCoupon = userBean.getTotalCoupon();
            welfare = userBean.getWelfare();
        }
        if (j2 != 0) {
            DataBindingHelper.setSelected(this.ll1, z6);
            DataBindingHelper.setSelected(this.ll2, z3);
            DataBindingHelper.setSelected(this.ll3, z2);
            DataBindingHelper.setSelected(this.ll4, z5);
            DataBindingHelper.setSelected(this.ll5, z);
            DataBindingHelper.setSelected(this.ll6, z4);
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.mboundView2, totalCoupon);
            TextViewBindingAdapter.setText(this.mboundView3, welfare);
            TextViewBindingAdapter.setText(this.tvPtb, wallet_balance);
        }
        if (j3 != 0) {
            DataBindingHelper.setSelected(this.tvWx, z8);
            DataBindingHelper.setSelected(this.tvZfb, z7);
        }
    }
}
