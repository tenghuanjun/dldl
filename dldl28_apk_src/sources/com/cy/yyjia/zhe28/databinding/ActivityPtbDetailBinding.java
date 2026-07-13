package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.PtbDetailBean;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityPtbDetailBinding extends ViewDataBinding {
    public final ImageView icon;

    @Bindable
    protected PtbDetailBean mData;
    public final TextView name;
    public final Navigation navigation;
    public final TextView number;
    public final TextView orderId;
    public final TextView orderPayType;
    public final TextView orderTime;

    public abstract void setData(PtbDetailBean data);

    protected ActivityPtbDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView icon, TextView name, Navigation navigation, TextView number, TextView orderId, TextView orderPayType, TextView orderTime) {
        super(_bindingComponent, _root, _localFieldCount);
        this.icon = icon;
        this.name = name;
        this.navigation = navigation;
        this.number = number;
        this.orderId = orderId;
        this.orderPayType = orderPayType;
        this.orderTime = orderTime;
    }

    public PtbDetailBean getData() {
        return this.mData;
    }

    public static ActivityPtbDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPtbDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityPtbDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_ptb_detail, root, attachToRoot, component);
    }

    public static ActivityPtbDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPtbDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityPtbDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_ptb_detail, null, false, component);
    }

    public static ActivityPtbDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPtbDetailBinding bind(View view, Object component) {
        return (ActivityPtbDetailBinding) bind(component, view, R.layout.activity_ptb_detail);
    }
}
