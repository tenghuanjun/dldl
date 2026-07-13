package com.cy.yyjia.zhe28.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;

/* JADX INFO: loaded from: classes2.dex */
public class ActivitySafeBindingImpl extends ActivitySafeBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final TextView mboundView1;
    private final TextView mboundView2;
    private final TextView mboundView3;
    private final TextView mboundView4;
    private final TextView mboundView5;

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll_phone, 6);
        sparseIntArray.put(R.id.ll_auth, 7);
        sparseIntArray.put(R.id.ll_birthday, 8);
        sparseIntArray.put(R.id.ll_ali, 9);
        sparseIntArray.put(R.id.ll_address, 10);
        sparseIntArray.put(R.id.ll_pin, 11);
        sparseIntArray.put(R.id.ll_password, 12);
        sparseIntArray.put(R.id.ll_cancellation, 13);
    }

    public ActivitySafeBindingImpl(DataBindingComponent bindingComponent, View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }

    private ActivitySafeBindingImpl(DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0, (LinearLayout) bindings[10], (LinearLayout) bindings[9], (LinearLayout) bindings[7], (LinearLayout) bindings[8], (LinearLayout) bindings[13], (LinearLayout) bindings[12], (LinearLayout) bindings[6], (LinearLayout) bindings[11]);
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
        setData((UserBean) variable);
        return true;
    }

    @Override // com.cy.yyjia.zhe28.databinding.ActivitySafeBinding
    public void setData(UserBean Data) {
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
        String encodePhone;
        String str;
        String str2;
        String str3;
        String birthDay;
        String aliAccount;
        String trade_pin;
        boolean zIsAuth;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        UserBean userBean = this.mData;
        long j2 = j & 3;
        String str4 = null;
        if (j2 != 0) {
            if (userBean != null) {
                aliAccount = userBean.getAliAccount();
                trade_pin = userBean.getTrade_pin();
                zIsAuth = userBean.isAuth();
                birthDay = userBean.getBirthDay();
                encodePhone = userBean.getEncodePhone();
            } else {
                encodePhone = null;
                aliAccount = null;
                trade_pin = null;
                birthDay = null;
                zIsAuth = false;
            }
            if (j2 != 0) {
                j |= zIsAuth ? 512L : 256L;
            }
            boolean zIsEmpty = TextUtils.isEmpty(aliAccount);
            boolean zIsEmpty2 = TextUtils.isEmpty(trade_pin);
            str3 = zIsAuth ? "已认证" : "未认证";
            if ((j & 3) != 0) {
                j |= zIsEmpty ? 32L : 16L;
            }
            if ((j & 3) != 0) {
                j |= zIsEmpty2 ? 128L : 64L;
            }
            int length = birthDay != null ? birthDay.length() : 0;
            str = zIsEmpty ? "去设置" : "点击修改";
            str2 = zIsEmpty2 ? "去设置" : "点击修改";
            z = length == 0;
            if ((j & 3) != 0) {
                j |= z ? 8L : 4L;
            }
        } else {
            encodePhone = null;
            str = null;
            str2 = null;
            str3 = null;
            birthDay = null;
        }
        long j3 = j & 3;
        if (j3 != 0) {
            str4 = z ? "去设置" : birthDay;
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, encodePhone);
            TextViewBindingAdapter.setText(this.mboundView2, str3);
            TextViewBindingAdapter.setText(this.mboundView3, str4);
            TextViewBindingAdapter.setText(this.mboundView4, str);
            TextViewBindingAdapter.setText(this.mboundView5, str2);
        }
    }
}
