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
public abstract class ItemVipGiftBinding extends ViewDataBinding {

    @Bindable
    protected VipGiftBean mData;

    public abstract void setData(VipGiftBean data);

    protected ItemVipGiftBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public VipGiftBean getData() {
        return this.mData;
    }

    public static ItemVipGiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipGiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemVipGiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_vip_gift, root, attachToRoot, component);
    }

    public static ItemVipGiftBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipGiftBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemVipGiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_vip_gift, null, false, component);
    }

    public static ItemVipGiftBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemVipGiftBinding bind(View view, Object component) {
        return (ItemVipGiftBinding) bind(component, view, R.layout.item_vip_gift);
    }
}
