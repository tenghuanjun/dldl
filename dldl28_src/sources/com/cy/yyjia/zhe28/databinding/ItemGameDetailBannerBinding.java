package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBannerBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameDetailBannerBinding extends ViewDataBinding {

    @Bindable
    protected GameBannerBean mData;

    public abstract void setData(GameBannerBean data);

    protected ItemGameDetailBannerBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public GameBannerBean getData() {
        return this.mData;
    }

    public static ItemGameDetailBannerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameDetailBannerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameDetailBannerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_detail_banner, root, attachToRoot, component);
    }

    public static ItemGameDetailBannerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameDetailBannerBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameDetailBannerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_detail_banner, null, false, component);
    }

    public static ItemGameDetailBannerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameDetailBannerBinding bind(View view, Object component) {
        return (ItemGameDetailBannerBinding) bind(component, view, R.layout.item_game_detail_banner);
    }
}
