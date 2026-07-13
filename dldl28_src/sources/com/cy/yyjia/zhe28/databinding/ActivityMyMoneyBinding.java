package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.view.Navigation;
import com.hjq.shape.layout.ShapeLinearLayout;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityMyMoneyBinding extends ViewDataBinding {
    public final EditText et;
    public final ShapeLinearLayout ll1;
    public final ShapeLinearLayout ll2;
    public final ShapeLinearLayout ll3;
    public final ShapeLinearLayout ll4;
    public final ShapeLinearLayout ll5;
    public final ShapeLinearLayout ll6;
    public final LinearLayout llFlb;
    public final LinearLayout llVoucher;

    @Bindable
    protected boolean mAlipay;

    @Bindable
    protected UserBean mData;

    @Bindable
    protected int mPosition;
    public final Navigation navigation;
    public final ShapeTextView tvBill;
    public final ShapeTextView tvGo;
    public final TextView tvPtb;
    public final TextView tvTag1;
    public final TextView tvWx;
    public final TextView tvZfb;

    public abstract void setAlipay(boolean alipay);

    public abstract void setData(UserBean data);

    public abstract void setPosition(int position);

    protected ActivityMyMoneyBinding(Object _bindingComponent, View _root, int _localFieldCount, EditText et, ShapeLinearLayout ll1, ShapeLinearLayout ll2, ShapeLinearLayout ll3, ShapeLinearLayout ll4, ShapeLinearLayout ll5, ShapeLinearLayout ll6, LinearLayout llFlb, LinearLayout llVoucher, Navigation navigation, ShapeTextView tvBill, ShapeTextView tvGo, TextView tvPtb, TextView tvTag1, TextView tvWx, TextView tvZfb) {
        super(_bindingComponent, _root, _localFieldCount);
        this.et = et;
        this.ll1 = ll1;
        this.ll2 = ll2;
        this.ll3 = ll3;
        this.ll4 = ll4;
        this.ll5 = ll5;
        this.ll6 = ll6;
        this.llFlb = llFlb;
        this.llVoucher = llVoucher;
        this.navigation = navigation;
        this.tvBill = tvBill;
        this.tvGo = tvGo;
        this.tvPtb = tvPtb;
        this.tvTag1 = tvTag1;
        this.tvWx = tvWx;
        this.tvZfb = tvZfb;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public boolean getAlipay() {
        return this.mAlipay;
    }

    public UserBean getData() {
        return this.mData;
    }

    public static ActivityMyMoneyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMyMoneyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMyMoneyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_my_money, root, attachToRoot, component);
    }

    public static ActivityMyMoneyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMyMoneyBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMyMoneyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_my_money, null, false, component);
    }

    public static ActivityMyMoneyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMyMoneyBinding bind(View view, Object component) {
        return (ActivityMyMoneyBinding) bind(component, view, R.layout.activity_my_money);
    }
}
