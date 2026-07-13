package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.InviteInfoBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DialogWithdraw2BindingImpl extends DialogWithdraw2Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final TextView mboundView2;
    private final ImageView mboundView3;
    private final TextView mboundView4;
    private final ImageView mboundView5;
    private final TextView mboundView6;
    private final ShapeLinearLayout mboundView7;
    private final ImageView mboundView8;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_close, 14);
        sparseIntArray.put(R.id.et, 15);
        sparseIntArray.put(R.id.tv_all, 16);
    }

    public DialogWithdraw2BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }

    private DialogWithdraw2BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeTextView) bindings[13], (EditText) bindings[15], (AppCompatImageView) bindings[14], (TextView) bindings[16], (TextView) bindings[9]);
        this.mDirtyFlags = -1L;
        this.btn.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[11];
        this.mboundView11 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) bindings[12];
        this.mboundView12 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[2];
        this.mboundView2 = textView4;
        textView4.setTag(null);
        ImageView imageView2 = (ImageView) bindings[3];
        this.mboundView3 = imageView2;
        imageView2.setTag(null);
        TextView textView5 = (TextView) bindings[4];
        this.mboundView4 = textView5;
        textView5.setTag(null);
        ImageView imageView3 = (ImageView) bindings[5];
        this.mboundView5 = imageView3;
        imageView3.setTag(null);
        TextView textView6 = (TextView) bindings[6];
        this.mboundView6 = textView6;
        textView6.setTag(null);
        ShapeLinearLayout shapeLinearLayout = (ShapeLinearLayout) bindings[7];
        this.mboundView7 = shapeLinearLayout;
        shapeLinearLayout.setTag(null);
        ImageView imageView4 = (ImageView) bindings[8];
        this.mboundView8 = imageView4;
        imageView4.setTag(null);
        this.tvBind.setTag(null);
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
        if (7 == variableId) {
            setBind((String) variable);
        } else if (23 == variableId) {
            setData((InviteInfoBean) variable);
        } else {
            if (118 != variableId) {
                return false;
            }
            setType(((Integer) variable).intValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogWithdraw2Binding
    public void setBind(String Bind) {
        this.mBind = Bind;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogWithdraw2Binding
    public void setData(InviteInfoBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogWithdraw2Binding
    public void setType(int Type) {
        this.mType = Type;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(118);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String str2;
        String icon1;
        String str3;
        String str4;
        String str5;
        String icon3;
        String icon2;
        String desc2;
        String str6;
        int i;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i2;
        boolean z5;
        InviteInfoBean.PayList payList;
        InviteInfoBean.InviteInfo invite_info;
        String reward_profit;
        String reward_money;
        String name2;
        String name3;
        String name1;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        String str7 = this.mBind;
        InviteInfoBean inviteInfoBean = this.mData;
        int i3 = this.mType;
        int i4 = ((9 & j) > 0L ? 1 : ((9 & j) == 0L ? 0 : -1));
        long j2 = 10 & j;
        if (j2 != 0) {
            if (inviteInfoBean != null) {
                invite_info = inviteInfoBean.getInvite_info();
                payList = inviteInfoBean.getPayList();
            } else {
                payList = null;
                invite_info = null;
            }
            if (invite_info != null) {
                reward_money = invite_info.getReward_money();
                reward_profit = invite_info.getReward_profit();
            } else {
                reward_profit = null;
                reward_money = null;
            }
            if (payList != null) {
                icon3 = payList.getIcon3();
                icon2 = payList.getIcon2();
                desc2 = payList.getDesc2();
                name2 = payList.getName2();
                name3 = payList.getName3();
                name1 = payList.getName1();
                icon1 = payList.getIcon1();
            } else {
                icon1 = null;
                icon3 = null;
                icon2 = null;
                desc2 = null;
                name2 = null;
                name3 = null;
                name1 = null;
            }
            str = "当前可提现余额：" + reward_money;
            str2 = "当前可提现余额：" + reward_profit;
            str3 = name2;
            str4 = name3;
            str5 = name1;
        } else {
            str = null;
            str2 = null;
            icon1 = null;
            str3 = null;
            str4 = null;
            str5 = null;
            icon3 = null;
            icon2 = null;
            desc2 = null;
        }
        long j3 = j & 12;
        if (j3 != 0) {
            boolean z6 = i3 == 0;
            boolean z7 = i3 != 0;
            if (i3 != 2) {
                i2 = 1;
                z5 = true;
            } else {
                i2 = 1;
                z5 = false;
            }
            boolean z8 = i3 != i2;
            z2 = z5;
            boolean z9 = z8;
            str6 = str7;
            z = z6;
            z3 = z9;
            boolean z10 = z7;
            i = i4;
            z4 = z10;
        } else {
            str6 = str7;
            i = i4;
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
        }
        String str8 = str4;
        if (j3 != 0) {
            this.btn.setTag(Integer.valueOf(i3));
            DataBindingHelper.setViewGone(this.mboundView1, z2);
            DataBindingHelper.setViewGone(this.mboundView10, z3);
            DataBindingHelper.setViewGone(this.mboundView11, z);
            DataBindingHelper.setViewGone(this.mboundView12, z4);
            DataBindingHelper.setViewGone(this.mboundView2, z2);
            DataBindingHelper.setViewGone(this.mboundView3, z3);
            DataBindingHelper.setViewGone(this.mboundView4, z3);
            DataBindingHelper.setViewGone(this.mboundView5, z4);
            DataBindingHelper.setViewGone(this.mboundView6, z4);
            DataBindingHelper.setViewGone(this.mboundView7, z3);
        }
        if (j2 != 0) {
            DataBindingHelper.setImg(this.mboundView1, icon1, null);
            TextViewBindingAdapter.setText(this.mboundView10, desc2);
            TextViewBindingAdapter.setText(this.mboundView11, str);
            TextViewBindingAdapter.setText(this.mboundView12, str2);
            TextViewBindingAdapter.setText(this.mboundView2, str5);
            DataBindingHelper.setImg(this.mboundView3, icon2, null);
            TextViewBindingAdapter.setText(this.mboundView4, str3);
            DataBindingHelper.setImg(this.mboundView5, icon3, null);
            TextViewBindingAdapter.setText(this.mboundView6, str8);
            DataBindingHelper.setImg(this.mboundView8, icon2, null);
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.tvBind, str6);
        }
    }
}
