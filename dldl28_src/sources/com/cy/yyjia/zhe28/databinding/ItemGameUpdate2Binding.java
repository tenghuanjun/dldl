package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameHistoryBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameUpdate2Binding extends ViewDataBinding {

    @Bindable
    protected GameHistoryBean mData;

    @Bindable
    protected boolean mTop;

    public abstract void setData(GameHistoryBean data);

    public abstract void setTop(boolean top);

    protected ItemGameUpdate2Binding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public boolean getTop() {
        return this.mTop;
    }

    public GameHistoryBean getData() {
        return this.mData;
    }

    public static ItemGameUpdate2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameUpdate2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameUpdate2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_update2, root, attachToRoot, component);
    }

    public static ItemGameUpdate2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameUpdate2Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameUpdate2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_update2, null, false, component);
    }

    public static ItemGameUpdate2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameUpdate2Binding bind(View view, Object component) {
        return (ItemGameUpdate2Binding) bind(component, view, R.layout.item_game_update2);
    }
}
