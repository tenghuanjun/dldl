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
public abstract class ItemGameSearchBinding extends ViewDataBinding {
    public final LinearLayout desc;
    public final LayoutDiscountBinding discount;

    @Bindable
    protected GameBean mData;
    public final LayoutGameNameBinding name;
    public final LinearLayout tag;

    public abstract void setData(GameBean data);

    protected ItemGameSearchBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout desc, LayoutDiscountBinding discount, LayoutGameNameBinding name, LinearLayout tag) {
        super(_bindingComponent, _root, _localFieldCount);
        this.desc = desc;
        this.discount = discount;
        this.name = name;
        this.tag = tag;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemGameSearchBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameSearchBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameSearchBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_search, root, attachToRoot, component);
    }

    public static ItemGameSearchBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameSearchBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameSearchBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_search, null, false, component);
    }

    public static ItemGameSearchBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameSearchBinding bind(View view, Object component) {
        return (ItemGameSearchBinding) bind(component, view, R.layout.item_game_search);
    }
}
