package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.VipFlbBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ItemVipFlbBindingImpl extends ItemVipFlbBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final ShapeTextView mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemVipFlbBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }

    private ItemVipFlbBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
        ShapeTextView shapeTextView = (ShapeTextView) bindings[3];
        this.mboundView3 = shapeTextView;
        shapeTextView.setTag(null);
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
        setData((VipFlbBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemVipFlbBinding
    public void setData(VipFlbBean Data) {
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
        boolean z;
        String name;
        int iIsReceive;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        VipFlbBean vipFlbBean = this.mData;
        long j2 = j & 3;
        String money = null;
        if (j2 != 0) {
            if (vipFlbBean != null) {
                money = vipFlbBean.getMoney();
                name = vipFlbBean.getName();
                iIsReceive = vipFlbBean.isReceive();
            } else {
                name = null;
                iIsReceive = 0;
            }
            money = money + "福利币";
            str2 = name + "可领取";
            boolean z2 = iIsReceive != 1;
            boolean z3 = iIsReceive == 2;
            z = iIsReceive == 1;
            if (j2 != 0) {
                j |= z3 ? 8L : 4L;
            }
            str = z3 ? "已领取" : "领取";
            z = z;
            z = z2;
        } else {
            str = null;
            str2 = null;
            z = false;
        }
        if ((j & 3) != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, money);
            TextViewBindingAdapter.setText(this.mboundView2, str2);
            DataBindingHelper.setSelected(this.mboundView3, z);
            TextViewBindingAdapter.setText(this.mboundView3, str);
            this.mboundView3.setEnabled(z);
        }
    }
}
