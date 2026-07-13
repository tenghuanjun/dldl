package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemItemSellGameBinding extends ViewDataBinding {

    @Bindable
    protected GameBean mData;
    public final LinearLayout name;

    public abstract void setData(GameBean data);

    protected ItemItemSellGameBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout name) {
        super(_bindingComponent, _root, _localFieldCount);
        this.name = name;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemItemSellGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemSellGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemItemSellGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_item_sell_game, root, attachToRoot, component);
    }

    public static ItemItemSellGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemSellGameBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemItemSellGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_item_sell_game, null, false, component);
    }

    public static ItemItemSellGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemSellGameBinding bind(View view, Object component) {
        return (ItemItemSellGameBinding) bind(component, view, R.layout.item_item_sell_game);
    }
}
