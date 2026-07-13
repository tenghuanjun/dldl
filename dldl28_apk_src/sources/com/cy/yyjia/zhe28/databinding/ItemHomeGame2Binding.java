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
public abstract class ItemHomeGame2Binding extends ViewDataBinding {
    public final LinearLayout desc;
    public final LayoutDiscountBinding discount;

    @Bindable
    protected GameBean mData;
    public final LayoutGameNameBinding name;
    public final LinearLayout tag;
    public final LinearLayout tag2;

    public abstract void setData(GameBean data);

    protected ItemHomeGame2Binding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout desc, LayoutDiscountBinding discount, LayoutGameNameBinding name, LinearLayout tag, LinearLayout tag2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.desc = desc;
        this.discount = discount;
        this.name = name;
        this.tag = tag;
        this.tag2 = tag2;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemHomeGame2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeGame2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeGame2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_game2, root, attachToRoot, component);
    }

    public static ItemHomeGame2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeGame2Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeGame2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_game2, null, false, component);
    }

    public static ItemHomeGame2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeGame2Binding bind(View view, Object component) {
        return (ItemHomeGame2Binding) bind(component, view, R.layout.item_home_game2);
    }
}
