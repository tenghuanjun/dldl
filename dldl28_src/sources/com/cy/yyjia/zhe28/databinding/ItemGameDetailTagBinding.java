package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameDetailTagBinding extends ViewDataBinding {

    @Bindable
    protected GameBean.Tag mData;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f464tv;

    public abstract void setData(GameBean.Tag data);

    protected ItemGameDetailTagBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.f464tv = tv2;
    }

    public GameBean.Tag getData() {
        return this.mData;
    }

    public static ItemGameDetailTagBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameDetailTagBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameDetailTagBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_detail_tag, root, attachToRoot, component);
    }

    public static ItemGameDetailTagBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameDetailTagBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameDetailTagBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_detail_tag, null, false, component);
    }

    public static ItemGameDetailTagBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameDetailTagBinding bind(View view, Object component) {
        return (ItemGameDetailTagBinding) bind(component, view, R.layout.item_game_detail_tag);
    }
}
