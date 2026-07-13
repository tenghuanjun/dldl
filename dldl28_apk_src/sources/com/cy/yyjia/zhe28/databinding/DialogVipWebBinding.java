package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.VipListBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogVipWebBinding extends ViewDataBinding {

    @Bindable
    protected VipListBean.RightBean mData;
    public final WebView wv;

    public abstract void setData(VipListBean.RightBean data);

    protected DialogVipWebBinding(Object _bindingComponent, View _root, int _localFieldCount, WebView wv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.wv = wv;
    }

    public VipListBean.RightBean getData() {
        return this.mData;
    }

    public static DialogVipWebBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVipWebBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogVipWebBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_vip_web, root, attachToRoot, component);
    }

    public static DialogVipWebBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVipWebBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogVipWebBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_vip_web, null, false, component);
    }

    public static DialogVipWebBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogVipWebBinding bind(View view, Object component) {
        return (DialogVipWebBinding) bind(component, view, R.layout.dialog_vip_web);
    }
}
