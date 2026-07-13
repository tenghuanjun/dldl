package com.cy.yyjia.zhe28.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CouponBean;
import com.cy.yyjia.zhe28.util.DataBindingHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ItemMyCouponBindingImpl extends ItemMyCouponBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll, 9);
    }

    public ItemMyCouponBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }

    private ItemMyCouponBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1, (ImageView) bindings[7], (LinearLayout) bindings[9]);
        this.mDirtyFlags = -1L;
        this.ivMore.setTag(null);
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
        TextView textView7 = (TextView) bindings[8];
        this.mboundView8 = textView7;
        textView7.setTag(null);
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
            setData((CouponBean) variable);
        } else {
            if (118 != variableId) {
                return false;
            }
            setType(((Integer) variable).intValue());
        }
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemMyCouponBinding
    public void setData(CouponBean Data) {
        updateRegistration(0, Data);
        this.mData = Data;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(23);
        super.requestRebind();
    }

    @Override // com.cy.yyjia.zhe28.databinding.ItemMyCouponBinding
    public void setType(int Type) {
        this.mType = Type;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(118);
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
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        boolean z;
        boolean z2;
        String str;
        String desc;
        String str2;
        String str3;
        String str4;
        boolean z3;
        boolean z4;
        String game_name;
        long j2;
        int colorFromResource;
        int colorFromResource2;
        int colorFromResource3;
        int colorFromResource4;
        boolean z5;
        String str5;
        String accountLimitStr;
        String conditionStr;
        String amount;
        String endTime;
        int i;
        String account_name;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        CouponBean couponBean = this.mData;
        int i2 = this.mType;
        if ((j & 13) != 0) {
            long j3 = j & 9;
            if (j3 != 0) {
                if (couponBean != null) {
                    desc = couponBean.getDesc();
                    endTime = couponBean.getEndTime();
                    accountLimitStr = couponBean.getAccountLimitStr();
                    account_name = couponBean.getAccount_name();
                    conditionStr = couponBean.getConditionStr();
                    game_name = couponBean.getGame_name();
                    int type = couponBean.getType();
                    amount = couponBean.getAmount();
                    i = type;
                } else {
                    desc = null;
                    endTime = null;
                    i = 0;
                    accountLimitStr = null;
                    account_name = null;
                    conditionStr = null;
                    game_name = null;
                    amount = null;
                }
                str2 = endTime + "到期";
                z3 = account_name == null;
                z4 = game_name == null;
                z2 = i != 3;
                if (j3 != 0) {
                    j |= z4 ? 2048L : 1024L;
                }
            } else {
                z2 = false;
                desc = null;
                str2 = null;
                z3 = false;
                accountLimitStr = null;
                z4 = false;
                conditionStr = null;
                game_name = null;
                amount = null;
            }
            z = !(couponBean != null ? couponBean.getSelected() : false);
            str = accountLimitStr;
            str4 = conditionStr;
            str3 = amount;
        } else {
            z = false;
            z2 = false;
            str = null;
            desc = null;
            str2 = null;
            str3 = null;
            str4 = null;
            z3 = false;
            z4 = false;
            game_name = null;
        }
        long j4 = j & 10;
        if (j4 != 0) {
            boolean z6 = i2 == 0;
            if (j4 != 0) {
                j |= z6 ? 8864L : 4432L;
            }
            TextView textView = this.mboundView2;
            colorFromResource2 = z6 ? getColorFromResource(textView, R.color.colorPrimary) : getColorFromResource(textView, R.color.color_text_3);
            TextView textView2 = this.mboundView1;
            colorFromResource4 = z6 ? getColorFromResource(textView2, R.color.colorPrimary) : getColorFromResource(textView2, R.color.color_text_3);
            colorFromResource3 = z6 ? getColorFromResource(this.mboundView4, R.color.color_text_1) : getColorFromResource(this.mboundView4, R.color.color_text_3);
            colorFromResource = z6 ? getColorFromResource(this.mboundView3, R.color.colorPrimary) : getColorFromResource(this.mboundView3, R.color.color_text_3);
            j2 = 9;
        } else {
            j2 = 9;
            colorFromResource = 0;
            colorFromResource2 = 0;
            colorFromResource3 = 0;
            colorFromResource4 = 0;
        }
        long j5 = j & j2;
        if (j5 != 0) {
            z5 = z;
            str5 = z4 ? "通用券" : game_name;
        } else {
            z5 = z;
            str5 = null;
        }
        if (j5 != 0) {
            DataBindingHelper.setViewGone(this.ivMore, z2);
            TextViewBindingAdapter.setText(this.mboundView2, str3);
            TextViewBindingAdapter.setText(this.mboundView3, str4);
            TextViewBindingAdapter.setText(this.mboundView4, str5);
            DataBindingHelper.setViewGone(this.mboundView5, z3);
            TextViewBindingAdapter.setText(this.mboundView5, str);
            TextViewBindingAdapter.setText(this.mboundView6, str2);
            TextViewBindingAdapter.setText(this.mboundView8, desc);
        }
        if ((j & 10) != 0) {
            this.mboundView1.setTextColor(colorFromResource4);
            this.mboundView2.setTextColor(colorFromResource2);
            this.mboundView3.setTextColor(colorFromResource);
            this.mboundView4.setTextColor(colorFromResource3);
        }
        if ((j & 13) != 0) {
            DataBindingHelper.setViewGone(this.mboundView8, z5);
        }
    }
}
