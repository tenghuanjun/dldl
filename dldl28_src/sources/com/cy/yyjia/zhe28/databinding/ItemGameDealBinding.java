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
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.DealBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameDealBinding extends ViewDataBinding {
    public final ImageView gameIcon;
    public final LinearLayout linTag1;
    public final LinearLayout linTag2;

    @Bindable
    protected DealBean mData;
    public final TextView tvName;
    public final TextView tvPrice;

    public abstract void setData(DealBean data);

    protected ItemGameDealBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView gameIcon, LinearLayout linTag1, LinearLayout linTag2, TextView tvName, TextView tvPrice) {
        super(_bindingComponent, _root, _localFieldCount);
        this.gameIcon = gameIcon;
        this.linTag1 = linTag1;
        this.linTag2 = linTag2;
        this.tvName = tvName;
        this.tvPrice = tvPrice;
    }

    public DealBean getData() {
        return this.mData;
    }

    public static ItemGameDealBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameDealBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameDealBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_deal, root, attachToRoot, component);
    }

    public static ItemGameDealBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameDealBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameDealBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_deal, null, false, component);
    }

    public static ItemGameDealBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameDealBinding bind(View view, Object component) {
        return (ItemGameDealBinding) bind(component, view, R.layout.item_game_deal);
    }
}
