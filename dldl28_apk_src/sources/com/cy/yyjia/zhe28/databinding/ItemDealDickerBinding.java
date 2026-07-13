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
import com.cy.yyjia.zhe28.domain.DickerMessageBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemDealDickerBinding extends ViewDataBinding {
    public final ImageView gameIcon;
    public final TextView gameName;
    public final LinearLayout llBtn;

    @Bindable
    protected DickerMessageBean mData;
    public final RecyclerView rv;
    public final View tagView;
    public final TextView tvDicker;
    public final TextView tvDickerAgree;
    public final TextView tvDickerCancel;
    public final TextView tvDickerModify;
    public final TextView tvDickerRefuse;
    public final TextView tvReceive;

    public abstract void setData(DickerMessageBean data);

    protected ItemDealDickerBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView gameIcon, TextView gameName, LinearLayout llBtn, RecyclerView rv, View tagView, TextView tvDicker, TextView tvDickerAgree, TextView tvDickerCancel, TextView tvDickerModify, TextView tvDickerRefuse, TextView tvReceive) {
        super(_bindingComponent, _root, _localFieldCount);
        this.gameIcon = gameIcon;
        this.gameName = gameName;
        this.llBtn = llBtn;
        this.rv = rv;
        this.tagView = tagView;
        this.tvDicker = tvDicker;
        this.tvDickerAgree = tvDickerAgree;
        this.tvDickerCancel = tvDickerCancel;
        this.tvDickerModify = tvDickerModify;
        this.tvDickerRefuse = tvDickerRefuse;
        this.tvReceive = tvReceive;
    }

    public DickerMessageBean getData() {
        return this.mData;
    }

    public static ItemDealDickerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealDickerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemDealDickerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_dicker, root, attachToRoot, component);
    }

    public static ItemDealDickerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealDickerBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemDealDickerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_deal_dicker, null, false, component);
    }

    public static ItemDealDickerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemDealDickerBinding bind(View view, Object component) {
        return (ItemDealDickerBinding) bind(component, view, R.layout.item_deal_dicker);
    }
}
