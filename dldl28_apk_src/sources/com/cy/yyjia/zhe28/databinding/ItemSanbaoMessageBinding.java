package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.SanbaoRuleBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemSanbaoMessageBinding extends ViewDataBinding {

    @Bindable
    protected SanbaoRuleBean.Message mData;

    public abstract void setData(SanbaoRuleBean.Message data);

    protected ItemSanbaoMessageBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public SanbaoRuleBean.Message getData() {
        return this.mData;
    }

    public static ItemSanbaoMessageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbaoMessageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemSanbaoMessageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sanbao_message, root, attachToRoot, component);
    }

    public static ItemSanbaoMessageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbaoMessageBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemSanbaoMessageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_sanbao_message, null, false, component);
    }

    public static ItemSanbaoMessageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSanbaoMessageBinding bind(View view, Object component) {
        return (ItemSanbaoMessageBinding) bind(component, view, R.layout.item_sanbao_message);
    }
}
