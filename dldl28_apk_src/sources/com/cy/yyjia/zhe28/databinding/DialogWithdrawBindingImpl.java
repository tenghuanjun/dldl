package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
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
import com.hjq.shape.layout.ShapeFrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class DialogWithdrawBindingImpl extends DialogWithdrawBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final ImageView mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView12;
    private final ImageView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final ImageView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_close, 13);
    }

    public DialogWithdrawBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private DialogWithdrawBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (AppCompatImageView) bindings[13], (ShapeFrameLayout) bindings[1], (ShapeFrameLayout) bindings[5], (ShapeFrameLayout) bindings[9]);
        this.mDirtyFlags = -1L;
        this.llFlb.setTag(null);
        this.llPtb.setTag(null);
        this.llZfb.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[10];
        this.mboundView10 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[11];
        this.mboundView11 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[12];
        this.mboundView12 = textView2;
        textView2.setTag(null);
        ImageView imageView2 = (ImageView) bindings[2];
        this.mboundView2 = imageView2;
        imageView2.setTag(null);
        TextView textView3 = (TextView) bindings[3];
        this.mboundView3 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[4];
        this.mboundView4 = textView4;
        textView4.setTag(null);
        ImageView imageView3 = (ImageView) bindings[6];
        this.mboundView6 = imageView3;
        imageView3.setTag(null);
        TextView textView5 = (TextView) bindings[7];
        this.mboundView7 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[8];
        this.mboundView8 = textView6;
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
        setData((InviteInfoBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.DialogWithdrawBinding
    public void setData(InviteInfoBean Data) {
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
        String icon1;
        String icon3;
        String desc1;
        String icon2;
        String desc3;
        String str;
        String str2;
        String str3;
        String str4;
        boolean z;
        boolean z2;
        String desc2;
        InviteInfoBean.Platform alipay;
        String name2;
        String name3;
        String name1;
        InviteInfoBean.Platform welfare;
        InviteInfoBean.Platform platform;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        InviteInfoBean inviteInfoBean = this.mData;
        long j2 = j & 3;
        if (j2 != 0) {
            InviteInfoBean.PayList payList = inviteInfoBean != null ? inviteInfoBean.getPayList() : null;
            if (payList != null) {
                icon3 = payList.getIcon3();
                desc1 = payList.getDesc1();
                icon2 = payList.getIcon2();
                desc3 = payList.getDesc3();
                desc2 = payList.getDesc2();
                alipay = payList.getAlipay();
                name2 = payList.getName2();
                name3 = payList.getName3();
                name1 = payList.getName1();
                welfare = payList.getWelfare();
                platform = payList.getPlatform();
                icon1 = payList.getIcon1();
            } else {
                icon1 = null;
                icon3 = null;
                desc1 = null;
                icon2 = null;
                desc3 = null;
                desc2 = null;
                alipay = null;
                name2 = null;
                name3 = null;
                name1 = null;
                welfare = null;
                platform = null;
            }
            z2 = alipay == null;
            boolean z3 = welfare == null;
            String str5 = desc2;
            z = platform == null;
            z = z3;
            str4 = name1;
            str3 = name3;
            str2 = name2;
            str = str5;
        } else {
            icon1 = null;
            icon3 = null;
            desc1 = null;
            icon2 = null;
            desc3 = null;
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            z = false;
            z2 = false;
        }
        if (j2 != 0) {
            DataBindingHelper.setViewGone(this.llFlb, z);
            DataBindingHelper.setViewGone(this.llPtb, z);
            DataBindingHelper.setViewGone(this.llZfb, z2);
            DataBindingHelper.setImg(this.mboundView10, icon2, null);
            TextViewBindingAdapter.setText(this.mboundView11, str2);
            TextViewBindingAdapter.setText(this.mboundView12, str);
            DataBindingHelper.setImg(this.mboundView2, icon3, null);
            TextViewBindingAdapter.setText(this.mboundView3, str3);
            TextViewBindingAdapter.setText(this.mboundView4, desc3);
            DataBindingHelper.setImg(this.mboundView6, icon1, null);
            TextViewBindingAdapter.setText(this.mboundView7, str4);
            TextViewBindingAdapter.setText(this.mboundView8, desc1);
        }
    }
}
