package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.MainTabBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemMainTabBinding extends ViewDataBinding {
    public final ImageView iv;

    @Bindable
    protected MainTabBean mData;

    @Bindable
    protected boolean mWhite;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f469tv;

    public abstract void setData(MainTabBean data);

    public abstract void setWhite(boolean white);

    protected ItemMainTabBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv, TextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
        this.f469tv = tv2;
    }

    public boolean getWhite() {
        return this.mWhite;
    }

    public MainTabBean getData() {
        return this.mData;
    }

    public static ItemMainTabBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMainTabBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemMainTabBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_main_tab, root, attachToRoot, component);
    }

    public static ItemMainTabBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMainTabBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemMainTabBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_main_tab, null, false, component);
    }

    public static ItemMainTabBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMainTabBinding bind(View view, Object component) {
        return (ItemMainTabBinding) bind(component, view, R.layout.item_main_tab);
    }
}
