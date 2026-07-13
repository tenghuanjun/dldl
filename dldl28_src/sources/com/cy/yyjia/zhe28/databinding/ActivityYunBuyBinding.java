package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.YunIndexBean;
import com.cy.yyjia.zhe28.domain.YunPrice;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityYunBuyBinding extends ViewDataBinding {
    public final Button btnBuy;
    public final FrameLayout btnWx;
    public final FrameLayout btnZfb;
    public final ImageView ivAdd;
    public final ImageView ivSubtract;

    @Bindable
    protected boolean mAlipay;

    @Bindable
    protected int mNum;

    @Bindable
    protected YunPrice mPrice;

    @Bindable
    protected boolean mRenew;

    @Bindable
    protected YunIndexBean mYun;
    public final Navigation navigation;
    public final RecyclerView rv;
    public final TextView tvYunDevice;

    public abstract void setAlipay(boolean alipay);

    public abstract void setNum(int num);

    public abstract void setPrice(YunPrice price);

    public abstract void setRenew(boolean renew);

    public abstract void setYun(YunIndexBean yun);

    protected ActivityYunBuyBinding(Object _bindingComponent, View _root, int _localFieldCount, Button btnBuy, FrameLayout btnWx, FrameLayout btnZfb, ImageView ivAdd, ImageView ivSubtract, Navigation navigation, RecyclerView rv, TextView tvYunDevice) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnBuy = btnBuy;
        this.btnWx = btnWx;
        this.btnZfb = btnZfb;
        this.ivAdd = ivAdd;
        this.ivSubtract = ivSubtract;
        this.navigation = navigation;
        this.rv = rv;
        this.tvYunDevice = tvYunDevice;
    }

    public boolean getAlipay() {
        return this.mAlipay;
    }

    public boolean getRenew() {
        return this.mRenew;
    }

    public int getNum() {
        return this.mNum;
    }

    public YunIndexBean getYun() {
        return this.mYun;
    }

    public YunPrice getPrice() {
        return this.mPrice;
    }

    public static ActivityYunBuyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityYunBuyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityYunBuyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_yun_buy, root, attachToRoot, component);
    }

    public static ActivityYunBuyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityYunBuyBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityYunBuyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_yun_buy, null, false, component);
    }

    public static ActivityYunBuyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityYunBuyBinding bind(View view, Object component) {
        return (ActivityYunBuyBinding) bind(component, view, R.layout.activity_yun_buy);
    }
}
