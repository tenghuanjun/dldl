package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GiftBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemSanbao648GiftBinding extends ViewDataBinding {
    public final TextView btn;

    @Bindable
    protected GiftBean mData;

    public abstract void setData(GiftBean data);

    protected ItemSanbao648GiftBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
    }

    public GiftBean getData() {
        return this.mData;
    }

    public static ItemSanbao648GiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbao648GiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSanbao648GiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sanbao_648_gift, root, attachToRoot, component);
    }

    public static ItemSanbao648GiftBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbao648GiftBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSanbao648GiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sanbao_648_gift, null, false, component);
    }

    public static ItemSanbao648GiftBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbao648GiftBinding bind(View view, Object component) {
        return (ItemSanbao648GiftBinding) bind(component, view, R.layout.item_sanbao_648_gift);
    }
}
