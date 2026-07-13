package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeGameHeadBinding extends ViewDataBinding {

    @Bindable
    protected GameBean mData;

    public abstract void setData(GameBean data);

    protected ItemHomeGameHeadBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemHomeGameHeadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeGameHeadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeGameHeadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_game_head, root, attachToRoot, component);
    }

    public static ItemHomeGameHeadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeGameHeadBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeGameHeadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_game_head, null, false, component);
    }

    public static ItemHomeGameHeadBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeGameHeadBinding bind(View view, Object component) {
        return (ItemHomeGameHeadBinding) bind(component, view, R.layout.item_home_game_head);
    }
}
