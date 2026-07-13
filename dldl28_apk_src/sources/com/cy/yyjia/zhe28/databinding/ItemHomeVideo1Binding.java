package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.CollectionBean;
import com.cy.yyjia.zhe28.view.WancmsStandardPlayer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeVideo1Binding extends ViewDataBinding {

    @Bindable
    protected CollectionBean mData;
    public final WancmsStandardPlayer player;

    public abstract void setData(CollectionBean data);

    protected ItemHomeVideo1Binding(Object _bindingComponent, View _root, int _localFieldCount, WancmsStandardPlayer player) {
        super(_bindingComponent, _root, _localFieldCount);
        this.player = player;
    }

    public CollectionBean getData() {
        return this.mData;
    }

    public static ItemHomeVideo1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeVideo1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeVideo1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_video1, root, attachToRoot, component);
    }

    public static ItemHomeVideo1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeVideo1Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeVideo1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_video1, null, false, component);
    }

    public static ItemHomeVideo1Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeVideo1Binding bind(View view, Object component) {
        return (ItemHomeVideo1Binding) bind(component, view, R.layout.item_home_video1);
    }
}
