package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameDetailBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameVipBinding extends ViewDataBinding {
    public final LinearLayout bg;

    @Bindable
    protected GameDetailBean.VipBean mData;

    public abstract void setData(GameDetailBean.VipBean data);

    protected ItemGameVipBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout bg) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bg = bg;
    }

    public GameDetailBean.VipBean getData() {
        return this.mData;
    }

    public static ItemGameVipBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameVipBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameVipBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_vip, root, attachToRoot, component);
    }

    public static ItemGameVipBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameVipBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameVipBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_vip, null, false, component);
    }

    public static ItemGameVipBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameVipBinding bind(View view, Object component) {
        return (ItemGameVipBinding) bind(component, view, R.layout.item_game_vip);
    }
}
