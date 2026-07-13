package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.domain.InviteBillBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: loaded from: classes2.dex */
public class ItemInviteWithdrewRecordBindingImpl extends ItemInviteWithdrewRecordBinding {
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

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    public ItemInviteWithdrewRecordBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds));
    }

    private ItemInviteWithdrewRecordBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
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
        setData((InviteBillBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemInviteWithdrewRecordBinding
    public void setData(InviteBillBean Data) {
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
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        String payMoney;
        String payType;
        boolean zEquals;
        boolean zEquals2;
        boolean zEquals3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        InviteBillBean inviteBillBean = this.mData;
        long j2 = j & 3;
        String str5 = null;
        if (j2 != 0) {
            if (inviteBillBean != null) {
                String dateline_txt = inviteBillBean.getDateline_txt();
                String status_txt = inviteBillBean.getStatus_txt();
                String status = inviteBillBean.getStatus();
                payType = inviteBillBean.getPayType();
                payMoney = inviteBillBean.getPayMoney();
                str4 = status_txt;
                str3 = dateline_txt;
                str5 = status;
            } else {
                payMoney = null;
                str3 = null;
                str4 = null;
                payType = null;
            }
            if (str5 != null) {
                zEquals3 = str5.equals("fail");
                zEquals = str5.equals(BooleanUtils.NO);
                zEquals2 = str5.equals("paid");
            } else {
                zEquals = false;
                zEquals2 = false;
                zEquals3 = false;
            }
            str2 = "兑换方式：" + payType;
            str = "+" + payMoney;
            z = !zEquals3;
            z3 = !zEquals;
            z2 = !zEquals2;
            if (j2 != 0) {
                j = !zEquals ? j | 8 : j | 4;
            }
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            z = false;
            z2 = false;
            z3 = false;
        }
        if ((8 & j) != 0) {
            z4 = !(str5 != null ? str5.equals("confirmed") : false);
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
            z5 = !(str5 != null ? str5.equals("verified") : false);
        } else {
            z5 = false;
        }
        long j4 = j & 3;
        boolean z6 = (j4 == 0 || !z4) ? false : z5;
        if (j4 != 0) {
            DataBindingHelper.setViewGone(this.mboundView1, z);
            TextViewBindingAdapter.setText(this.mboundView1, str4);
            DataBindingHelper.setViewGone(this.mboundView2, z2);
            TextViewBindingAdapter.setText(this.mboundView2, str4);
            DataBindingHelper.setViewGone(this.mboundView3, z6);
            TextViewBindingAdapter.setText(this.mboundView3, str4);
            TextViewBindingAdapter.setText(this.mboundView4, str2);
            TextViewBindingAdapter.setText(this.mboundView5, str3);
            TextViewBindingAdapter.setText(this.mboundView6, str);
        }
    }
}
