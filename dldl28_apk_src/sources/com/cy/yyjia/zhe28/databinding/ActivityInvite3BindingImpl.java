package com.cy.yyjia.zhe28.databinding;

import android.text.SpannableString;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.InviteInfoBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityInvite3BindingImpl extends ActivityInvite3Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final ImageView mboundView1;
    private final TextView mboundView10;
    private final ImageView mboundView11;
    private final ImageView mboundView12;
    private final LinearLayout mboundView13;
    private final TextView mboundView14;
    private final ImageView mboundView2;
    private final ImageView mboundView3;
    private final ImageView mboundView4;
    private final LinearLayout mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;
    private final TextView mboundView9;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.vf, 16);
        sparseIntArray.put(R.id.fl1, 17);
        sparseIntArray.put(R.id.fl2, 18);
        sparseIntArray.put(R.id.tv_record, 19);
        sparseIntArray.put(R.id.tv_withdraw, 20);
        sparseIntArray.put(R.id.iv_share2, 21);
        sparseIntArray.put(R.id.tv_rule, 22);
    }

    public ActivityInvite3BindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }

    private ActivityInvite3BindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (FrameLayout) bindings[17], (FrameLayout) bindings[18], (ImageView) bindings[15], (ImageView) bindings[21], (TextView) bindings[19], (TextView) bindings[22], (ShapeTextView) bindings[20], (AdapterViewFlipper) bindings[16]);
        this.mDirtyFlags = -1L;
        this.ivShare.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        ImageView imageView = (ImageView) bindings[1];
        this.mboundView1 = imageView;
        imageView.setTag(null);
        TextView textView = (TextView) bindings[10];
        this.mboundView10 = textView;
        textView.setTag(null);
        ImageView imageView2 = (ImageView) bindings[11];
        this.mboundView11 = imageView2;
        imageView2.setTag(null);
        ImageView imageView3 = (ImageView) bindings[12];
        this.mboundView12 = imageView3;
        imageView3.setTag(null);
        LinearLayout linearLayout = (LinearLayout) bindings[13];
        this.mboundView13 = linearLayout;
        linearLayout.setTag(null);
        TextView textView2 = (TextView) bindings[14];
        this.mboundView14 = textView2;
        textView2.setTag(null);
        ImageView imageView4 = (ImageView) bindings[2];
        this.mboundView2 = imageView4;
        imageView4.setTag("0");
        ImageView imageView5 = (ImageView) bindings[3];
        this.mboundView3 = imageView5;
        imageView5.setTag(null);
        ImageView imageView6 = (ImageView) bindings[4];
        this.mboundView4 = imageView6;
        imageView6.setTag("1");
        LinearLayout linearLayout2 = (LinearLayout) bindings[5];
        this.mboundView5 = linearLayout2;
        linearLayout2.setTag(null);
        TextView textView3 = (TextView) bindings[6];
        this.mboundView6 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) bindings[7];
        this.mboundView7 = textView4;
        textView4.setTag(null);
        TextView textView5 = (TextView) bindings[8];
        this.mboundView8 = textView5;
        textView5.setTag(null);
        TextView textView6 = (TextView) bindings[9];
        this.mboundView9 = textView6;
        textView6.setTag(null);
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
        if (74 == variableId) {
            setPosition(((Integer) variable).intValue());
        } else {
            if (23 != variableId) {
                return false;
            }
            setData((InviteInfoBean) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityInvite3Binding
    public void setPosition(int Position) {
        this.mPosition = Position;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(74);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityInvite3Binding
    public void setData(InviteInfoBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 2;
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
        SpannableString label2;
        String str;
        String str2;
        boolean z5;
        boolean z6;
        String reward_money;
        SpannableString spannableString;
        String str3;
        String str4;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        InviteInfoBean.Reward reward;
        String bottomBg;
        InviteInfoBean.InviteInfo invite_info;
        SpannableString label1;
        String userreward;
        String common;
        String zhe;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        int i = this.mPosition;
        InviteInfoBean inviteInfoBean = this.mData;
        if ((j & 5) != 0) {
            z2 = i != 0;
            z3 = i == 0;
            z4 = i != 1;
            z = i == 1;
        } else {
            z = false;
            z2 = false;
            z3 = false;
            z4 = false;
        }
        long j2 = j & 6;
        if (j2 != 0) {
            if (inviteInfoBean != null) {
                reward = inviteInfoBean.getReward();
                bottomBg = inviteInfoBean.getBottomBg();
                invite_info = inviteInfoBean.getInvite_info();
                label1 = inviteInfoBean.getLabel1();
                label2 = inviteInfoBean.getLabel2();
            } else {
                label2 = null;
                reward = null;
                bottomBg = null;
                invite_info = null;
                label1 = null;
            }
            if (reward != null) {
                common = reward.getCommon();
                zhe = reward.getZhe();
                userreward = reward.getUserreward();
            } else {
                userreward = null;
                common = null;
                zhe = null;
            }
            z5 = bottomBg != null;
            z6 = bottomBg == null;
            if (j2 != 0) {
                j = z5 ? j | 64 : j | 32;
            }
            if ((j & 6) != 0) {
                j = z6 ? j | 16 : j | 8;
            }
            if (invite_info != null) {
                str2 = userreward;
                str = bottomBg;
                reward_money = invite_info.getReward_money();
                spannableString = label1;
                str3 = common;
                str4 = zhe;
            } else {
                str2 = userreward;
                str = bottomBg;
                spannableString = label1;
                str3 = common;
                str4 = zhe;
                reward_money = null;
            }
        } else {
            label2 = null;
            str = null;
            str2 = null;
            z5 = false;
            z6 = false;
            reward_money = null;
            spannableString = null;
            str3 = null;
            str4 = null;
        }
        if ((j & 72) != 0) {
            int length = str != null ? str.length() : 0;
            z8 = (j & 8) != 0 && length == 0;
            z7 = (j & 64) != 0 && length > 0;
        } else {
            z7 = false;
            z8 = false;
        }
        long j3 = 6 & j;
        if (j3 != 0) {
            if (z6) {
                z8 = true;
            }
            z9 = z5 ? z7 : false;
            z10 = z8;
        } else {
            z9 = false;
            z10 = false;
        }
        if ((j & 5) != 0) {
            DataBindingHelper.setViewGone(this.ivShare, z2);
            DataBindingHelper.setViewGone(this.mboundView1, z2);
            DataBindingHelper.setViewGone(this.mboundView13, z4);
            DataBindingHelper.setViewGone(this.mboundView2, z3);
            DataBindingHelper.setViewGone(this.mboundView3, z4);
            DataBindingHelper.setViewGone(this.mboundView4, z);
            DataBindingHelper.setViewGone(this.mboundView5, z2);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView10, label2);
            DataBindingHelper.setViewGone(this.mboundView11, z9);
            DataBindingHelper.setViewGone(this.mboundView12, z10);
            DataBindingHelper.setImg(this.mboundView12, str, null);
            TextViewBindingAdapter.setText(this.mboundView14, reward_money);
            TextViewBindingAdapter.setText(this.mboundView6, str2);
            TextViewBindingAdapter.setText(this.mboundView7, str3);
            TextViewBindingAdapter.setText(this.mboundView8, str4);
            TextViewBindingAdapter.setText(this.mboundView9, spannableString);
        }
    }
}
