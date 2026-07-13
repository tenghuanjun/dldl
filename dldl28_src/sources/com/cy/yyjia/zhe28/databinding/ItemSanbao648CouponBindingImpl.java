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

/* JADX INFO: loaded from: classes2.dex */
public class ItemSanbao648CouponBindingImpl extends ItemSanbao648CouponBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.btn, 6);
    }

    public ItemSanbao648CouponBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemSanbao648CouponBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (TextView) bindings[6], (TextView) bindings[4]);
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
        TextView textView4 = (TextView) bindings[5];
        this.mboundView5 = textView4;
        textView4.setTag(null);
        this.tvMore.setTag(null);
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

    @Override // com.cy.yyjia.zhe28.databinding.ItemSanbao648CouponBinding
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
        String name;
        boolean z;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CouponBean couponBean = this.mData;
        long j2 = j & 7;
        String gameLimitStr = null;
        if (j2 != 0) {
            selected = couponBean != null ? couponBean.getSelected() : false;
            if (j2 != 0) {
                j |= selected ? 16L : 8L;
            }
            boolean z2 = !selected;
            str2 = selected ? "收起" : "详情";
            if ((j & 5) == 0 || couponBean == null) {
                z = selected;
                name = null;
                selected = z2;
                str = null;
            } else {
                String times = couponBean.getTimes();
                name = couponBean.getName();
                gameLimitStr = couponBean.getGameLimitStr();
                z = selected;
                selected = z2;
                str = times;
            }
        } else {
            str = null;
            str2 = null;
            name = null;
            z = false;
        }
        if ((5 & j) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, gameLimitStr);
            TextViewBindingAdapter.setText(this.mboundView2, str);
            TextViewBindingAdapter.setText(this.mboundView3, name);
            TextViewBindingAdapter.setText(this.mboundView5, gameLimitStr);
        }
        if ((j & 7) != 0) {
            DataBindingHelper.setViewGone(this.mboundView5, selected);
            DataBindingHelper.setSelected(this.tvMore, z);
            TextViewBindingAdapter.setText(this.tvMore, str2);
        }
    }
}
