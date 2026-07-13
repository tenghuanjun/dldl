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
public abstract class ItemHallGameBinding extends ViewDataBinding {
    public final LinearLayout desc;
    public final LayoutDiscountBinding discount;

    @Bindable
    protected GameBean mData;

    @Bindable
    protected int mPosition;
    public final LayoutGameNameBinding name;
    public final LinearLayout tag;

    public abstract void setData(GameBean data);

    public abstract void setPosition(int position);

    protected ItemHallGameBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout desc, LayoutDiscountBinding discount, LayoutGameNameBinding name, LinearLayout tag) {
        super(_bindingComponent, _root, _localFieldCount);
        this.desc = desc;
        this.discount = discount;
        this.name = name;
        this.tag = tag;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemHallGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHallGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHallGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_hall_game, root, attachToRoot, component);
    }

    public static ItemHallGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHallGameBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHallGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_hall_game, null, false, component);
    }

    public static ItemHallGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHallGameBinding bind(View view, Object component) {
        return (ItemHallGameBinding) bind(component, view, R.layout.item_hall_game);
    }
}
