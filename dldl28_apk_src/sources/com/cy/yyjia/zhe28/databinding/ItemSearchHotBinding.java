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
public abstract class ItemSearchHotBinding extends ViewDataBinding {
    public final LinearLayout desc;

    @Bindable
    protected GameBean mData;

    @Bindable
    protected int mPosition;
    public final LinearLayout name;

    public abstract void setData(GameBean data);

    public abstract void setPosition(int position);

    protected ItemSearchHotBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout desc, LinearLayout name) {
        super(_bindingComponent, _root, _localFieldCount);
        this.desc = desc;
        this.name = name;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemSearchHotBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSearchHotBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSearchHotBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_search_hot, root, attachToRoot, component);
    }

    public static ItemSearchHotBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSearchHotBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSearchHotBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_search_hot, null, false, component);
    }

    public static ItemSearchHotBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSearchHotBinding bind(View view, Object component) {
        return (ItemSearchHotBinding) bind(component, view, R.layout.item_search_hot);
    }
}
