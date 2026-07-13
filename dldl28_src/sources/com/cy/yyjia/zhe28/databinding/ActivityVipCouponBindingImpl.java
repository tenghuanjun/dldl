package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.VipCouponIndexBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.cy.yyjia.zhe28.view.Navigation;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityVipCouponBindingImpl extends ActivityVipCouponBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;
    private final Navigation mboundView1;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_rule, 7);
        sparseIntArray.put(R.id.btn, 8);
    }

    public ActivityVipCouponBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }

    private ActivityVipCouponBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (TextView) bindings[8], (ImageView) bindings[7], (RecyclerView) bindings[2]);
        this.mDirtyFlags = -1L;
        NestedScrollView nestedScrollView = (NestedScrollView) bindings[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag(null);
        Navigation navigation = (Navigation) bindings[1];
        this.mboundView1 = navigation;
        navigation.setTag(null);
        TextView textView = (TextView) bindings[3];
        this.mboundView3 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[4];
        this.mboundView4 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[5];
        this.mboundView5 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[6];
        this.mboundView6 = textView4;
        textView4.setTag(null);
        this.rv.setTag(null);
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
        if (23 == variableId) {
            setData((VipCouponIndexBean) variable);
        } else {
            if (52 != variableId) {
                return false;
            }
            setLevel(((Integer) variable).intValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityVipCouponBinding
    public void setData(VipCouponIndexBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityVipCouponBinding
    public void setLevel(int Level) {
        this.mLevel = Level;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        if (localFieldId != 0) {
            return false;
        }
        return onChangeDataVip((VipCouponIndexBean.Vip) object, fieldId);
    }

    private boolean onChangeDataVip(VipCouponIndexBean.Vip DataVip, int fieldId) {
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
        List<VipCouponIndexBean.Vip> vipList;
        String coupon;
        String desc;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        VipCouponIndexBean vipCouponIndexBean = this.mData;
        long j2 = 11 & j;
        String name = null;
        if (j2 != 0) {
            if ((j & 10) == 0 || vipCouponIndexBean == null) {
                vipList = null;
                coupon = null;
                desc = null;
            } else {
                vipList = vipCouponIndexBean.getVipList();
                coupon = vipCouponIndexBean.getCoupon();
                desc = vipCouponIndexBean.getDesc();
            }
            VipCouponIndexBean.Vip vip = vipCouponIndexBean != null ? vipCouponIndexBean.getVip() : null;
            int num = 0;
            updateRegistration(0, vip);
            if (vip != null) {
                num = vip.getNum();
                name = vip.getName();
            }
            str = num + "张";
            name = name + "可购买";
        } else {
            str = null;
            vipList = null;
            coupon = null;
            desc = null;
        }
        if ((8 & j) != 0 && getBuildSdkInt() >= 14) {
            this.mboundView1.setFitsSystemWindows(true);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.mboundView3, name);
            TextViewBindingAdapter.setText(this.mboundView4, str);
        }
        if ((j & 10) != 0) {
            TextViewBindingAdapter.setText(this.mboundView5, coupon);
            TextViewBindingAdapter.setText(this.mboundView6, desc);
            DataBindingHelper.setRvData(this.rv, vipList);
        }
    }
}
