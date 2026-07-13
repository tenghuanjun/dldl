package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.VipGiftBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemVipGiftSmallBinding extends ViewDataBinding {

    @Bindable
    protected VipGiftBean mData;

    public abstract void setData(VipGiftBean data);

    protected ItemVipGiftSmallBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public VipGiftBean getData() {
        return this.mData;
    }

    public static ItemVipGiftSmallBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipGiftSmallBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemVipGiftSmallBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_vip_gift_small, root, attachToRoot, component);
    }

    public static ItemVipGiftSmallBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipGiftSmallBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemVipGiftSmallBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_vip_gift_small, null, false, component);
    }

    public static ItemVipGiftSmallBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipGiftSmallBinding bind(View view, Object component) {
        return (ItemVipGiftSmallBinding) bind(component, view, R.layout.item_vip_gift_small);
    }
}
