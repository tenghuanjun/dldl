package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.AccountListBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemTrumpet1Binding extends ViewDataBinding {
    public final ImageView gameIcon;
    public final TextView gameName;

    @Bindable
    protected AccountListBean mData;
    public final RecyclerView rv;

    public abstract void setData(AccountListBean data);

    protected ItemTrumpet1Binding(Object _bindingComponent, View _root, int _localFieldCount, ImageView gameIcon, TextView gameName, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.gameIcon = gameIcon;
        this.gameName = gameName;
        this.rv = rv;
    }

    public AccountListBean getData() {
        return this.mData;
    }

    public static ItemTrumpet1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTrumpet1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemTrumpet1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_trumpet1, root, attachToRoot, component);
    }

    public static ItemTrumpet1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTrumpet1Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemTrumpet1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_trumpet1, null, false, component);
    }

    public static ItemTrumpet1Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTrumpet1Binding bind(View view, Object component) {
        return (ItemTrumpet1Binding) bind(component, view, R.layout.item_trumpet1);
    }
}
