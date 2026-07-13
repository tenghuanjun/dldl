package com.cy.yyjia.zhe28.databinding;

import android.text.Html;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.InviteInfoBean;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityInviteWithdrewBindingImpl extends ActivityInviteWithdrewBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etandroidTextAttrChanged;
    private long mDirtyFlags;
    private final FrameLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView3;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.navigation, 5);
        sparseIntArray.put(R.id.tv_all, 6);
        sparseIntArray.put(R.id.btn, 7);
    }

    public ActivityInviteWithdrewBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }

    private ActivityInviteWithdrewBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (ShapeTextView) bindings[7], (EditText) bindings[2], (Navigation) bindings[5], (TextView) bindings[6], (TextView) bindings[4]);
        this.etandroidTextAttrChanged = new InverseBindingListener() { // from class: com.cy.yyjia.zhe28.databinding.ActivityInviteWithdrewBindingImpl.1
            @Override // androidx.databinding.InverseBindingListener
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityInviteWithdrewBindingImpl.this.et);
                String str = ActivityInviteWithdrewBindingImpl.this.mNumber;
                ActivityInviteWithdrewBindingImpl activityInviteWithdrewBindingImpl = ActivityInviteWithdrewBindingImpl.this;
                if (activityInviteWithdrewBindingImpl != null) {
                    activityInviteWithdrewBindingImpl.setNumber(textString);
                }
            }
        };
        this.mDirtyFlags = -1L;
        this.et.setTag(null);
        FrameLayout frameLayout = (FrameLayout) bindings[0];
        this.mboundView0 = frameLayout;
        frameLayout.setTag(null);
        TextView textView = (TextView) bindings[1];
        this.mboundView1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) bindings[3];
        this.mboundView3 = textView2;
        textView2.setTag(null);
        this.tvRule.setTag(null);
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
        if (23 == variableId) {
            setData((InviteInfoBean) variable);
        } else {
            if (64 != variableId) {
                return false;
            }
            setNumber((String) variable);
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityInviteWithdrewBinding
    public void setData(InviteInfoBean Data) {
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivityInviteWithdrewBinding
    public void setNumber(String Number) {
        this.mNumber = Number;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(64);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean zIsEmpty;
        String withdrawalDesc;
        String reward_profit;
        String str;
        InviteInfoBean.RewardInfo reward_info;
        InviteInfoBean.InviteInfo invite_info;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        InviteInfoBean inviteInfoBean = this.mData;
        String str2 = this.mNumber;
        long j2 = j & 5;
        if (j2 != 0) {
            if (inviteInfoBean != null) {
                invite_info = inviteInfoBean.getInvite_info();
                reward_info = inviteInfoBean.getReward_info();
            } else {
                reward_info = null;
                invite_info = null;
            }
            reward_profit = invite_info != null ? invite_info.getReward_profit() : null;
            withdrawalDesc = reward_info != null ? reward_info.getWithdrawalDesc() : null;
            zIsEmpty = TextUtils.isEmpty(withdrawalDesc);
            if (j2 != 0) {
                j = zIsEmpty ? j | 16 : j | 8;
            }
        } else {
            zIsEmpty = false;
            withdrawalDesc = null;
            reward_profit = null;
        }
        long j3 = 4 & j;
        if (j3 != 0) {
            str = "提现账户：" + Constant.INSTANCE.getUsername();
        } else {
            str = null;
        }
        long j4 = 6 & j;
        CharSequence charSequenceFromHtml = (8 & j) != 0 ? Html.fromHtml(withdrawalDesc) : null;
        long j5 = j & 5;
        if (j5 == 0) {
            charSequenceFromHtml = null;
        } else if (zIsEmpty) {
            charSequenceFromHtml = "";
        }
        if (j4 != 0) {
            TextViewBindingAdapter.setText(this.et, str2);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setTextWatcher(this.et, null, null, null, this.etandroidTextAttrChanged);
            TextViewBindingAdapter.setText(this.mboundView3, str);
        }
        if (j5 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, reward_profit);
            TextViewBindingAdapter.setText(this.tvRule, charSequenceFromHtml);
        }
    }
}
