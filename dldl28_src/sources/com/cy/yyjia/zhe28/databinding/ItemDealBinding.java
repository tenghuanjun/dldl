package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DealBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemDealBinding extends ViewDataBinding {
    public final ImageView gameIcon;
    public final TextView gameName;
    public final ImageView ivSwitch;
    public final LinearLayout ll;

    @Bindable
    protected DealBean mData;
    public final RecyclerView rv;
    public final View tagView;
    public final TextView tvModify;
    public final TextView tvOffset;
    public final TextView tvPrice;
    public final TextView tvReceive;

    public abstract void setData(DealBean data);

    protected ItemDealBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView gameIcon, TextView gameName, ImageView ivSwitch, LinearLayout ll, RecyclerView rv, View tagView, TextView tvModify, TextView tvOffset, TextView tvPrice, TextView tvReceive) {
        super(_bindingComponent, _root, _localFieldCount);
        this.gameIcon = gameIcon;
        this.gameName = gameName;
        this.ivSwitch = ivSwitch;
        this.ll = ll;
        this.rv = rv;
        this.tagView = tagView;
        this.tvModify = tvModify;
        this.tvOffset = tvOffset;
        this.tvPrice = tvPrice;
        this.tvReceive = tvReceive;
    }

    public DealBean getData() {
        return this.mData;
    }

    public static ItemDealBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDealBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal, root, attachToRoot, component);
    }

    public static ItemDealBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDealBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal, null, false, component);
    }

    public static ItemDealBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealBinding bind(View view, Object component) {
        return (ItemDealBinding) bind(component, view, R.layout.item_deal);
    }
}
