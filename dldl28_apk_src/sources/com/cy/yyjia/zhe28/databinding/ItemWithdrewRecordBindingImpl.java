package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.WithdrewBillBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ItemWithdrewRecordBindingImpl extends ItemWithdrewRecordBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemWithdrewRecordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ItemWithdrewRecordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
        TextView textView5 = (TextView) bindings[5];
        this.mboundView5 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[6];
        this.mboundView6 = textView6;
        textView6.setTag(null);
        TextView textView7 = (TextView) bindings[7];
        this.mboundView7 = textView7;
        textView7.setTag(null);
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
        setData((WithdrewBillBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemWithdrewRecordBinding
    public void setData(WithdrewBillBean Data) {
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
        String dateline;
        String str3;
        String str4;
        boolean z;
        boolean z2;
        boolean zIsEmpty;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        String payMoney;
        String desc;
        String status;
        String statusStr;
        boolean zEquals;
        boolean zEquals2;
        boolean zEquals3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        WithdrewBillBean withdrewBillBean = this.mData;
        long j2 = j & 3;
        String typeStr = null;
        if (j2 != 0) {
            if (withdrewBillBean != null) {
                typeStr = withdrewBillBean.getTypeStr();
                dateline = withdrewBillBean.getDateline();
                desc = withdrewBillBean.getDesc();
                status = withdrewBillBean.getStatus();
                statusStr = withdrewBillBean.getStatusStr();
                payMoney = withdrewBillBean.getPayMoney();
            } else {
                payMoney = null;
                dateline = null;
                desc = null;
                status = null;
                statusStr = null;
            }
            String str5 = "提现方式：" + typeStr;
            zIsEmpty = TextUtils.isEmpty(desc);
            str2 = "备注：" + desc;
            String str6 = "+" + payMoney;
            if (status != null) {
                zEquals2 = status.equals("fail");
                zEquals3 = status.equals(BooleanUtils.NO);
                zEquals = status.equals("paid");
            } else {
                zEquals = false;
                zEquals2 = false;
                zEquals3 = false;
            }
            z2 = !zEquals2;
            z3 = !zEquals3;
            z = !zEquals;
            if (j2 != 0) {
                j = !zEquals3 ? j | 8 : j | 4;
            }
            str3 = str6;
            str = str5;
            typeStr = status;
            str4 = statusStr;
        } else {
            str = null;
            str2 = null;
            dateline = null;
            str3 = null;
            str4 = null;
            z = false;
            z2 = false;
            zIsEmpty = false;
            z3 = false;
        }
        if ((8 & j) != 0) {
            z4 = !(typeStr != null ? typeStr.equals("confirmed") : false);
        } else {
            z4 = false;
        }
        long j3 = j & 3;
        if (j3 != 0) {
            if (!z3) {
                z4 = false;
            }
            if (j3 != 0) {
                j = z4 ? j | 32 : j | 16;
            }
        } else {
            z4 = false;
        }
        if ((32 & j) != 0) {
            z5 = !(typeStr != null ? typeStr.equals("verified") : false);
        } else {
            z5 = false;
        }
        long j4 = j & 3;
        if (j4 != 0) {
            z6 = z4 ? z5 : false;
        } else {
            z6 = false;
        }
        if (j4 != 0) {
            DataBindingHelper.setViewGone(this.mboundView1, z2);
            TextViewBindingAdapter.setText(this.mboundView1, str4);
            DataBindingHelper.setViewGone(this.mboundView2, z);
            TextViewBindingAdapter.setText(this.mboundView2, str4);
            DataBindingHelper.setViewGone(this.mboundView3, z6);
            TextViewBindingAdapter.setText(this.mboundView3, str4);
            TextViewBindingAdapter.setText(this.mboundView4, str);
            DataBindingHelper.setViewGone(this.mboundView5, zIsEmpty);
            TextViewBindingAdapter.setText(this.mboundView5, str2);
            TextViewBindingAdapter.setText(this.mboundView6, dateline);
            TextViewBindingAdapter.setText(this.mboundView7, str3);
        }
    }
}
