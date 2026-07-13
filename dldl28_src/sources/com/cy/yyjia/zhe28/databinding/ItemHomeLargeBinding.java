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
public abstract class ItemHomeLargeBinding extends ViewDataBinding {
    public final LinearLayout desc;
    public final LayoutDiscountBinding discount;

    @Bindable
    protected GameBean mData;
    public final LayoutGameNameBinding name;
    public final LinearLayout tag;
    public final LinearLayout tag2;

    public abstract void setData(GameBean data);

    protected ItemHomeLargeBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout desc, LayoutDiscountBinding discount, LayoutGameNameBinding name, LinearLayout tag, LinearLayout tag2) {
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

    public static ItemHomeLargeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeLargeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeLargeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_large, root, attachToRoot, component);
    }

    public static ItemHomeLargeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeLargeBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeLargeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_large, null, false, component);
    }

    public static ItemHomeLargeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeLargeBinding bind(View view, Object component) {
        return (ItemHomeLargeBinding) bind(component, view, R.layout.item_home_large);
    }
}
