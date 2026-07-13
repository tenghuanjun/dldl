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
public abstract class ItemHomeGameBinding extends ViewDataBinding {

    @Bindable
    protected GameBean mData;

    @Bindable
    protected int mPosition;
    public final LinearLayout name;

    public abstract void setData(GameBean data);

    public abstract void setPosition(int position);

    protected ItemHomeGameBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout name) {
        super(_bindingComponent, _root, _localFieldCount);
        this.name = name;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemHomeGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeGameBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_game, root, attachToRoot, component);
    }

    public static ItemHomeGameBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeGameBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeGameBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_game, null, false, component);
    }

    public static ItemHomeGameBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeGameBinding bind(View view, Object component) {
        return (ItemHomeGameBinding) bind(component, view, R.layout.item_home_game);
    }
}
