package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.QiandaoBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemWelfareQiandaoBinding extends ViewDataBinding {

    @Bindable
    protected QiandaoBean.Day mData;

    public abstract void setData(QiandaoBean.Day data);

    protected ItemWelfareQiandaoBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public QiandaoBean.Day getData() {
        return this.mData;
    }

    public static ItemWelfareQiandaoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfareQiandaoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemWelfareQiandaoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare_qiandao, root, attachToRoot, component);
    }

    public static ItemWelfareQiandaoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfareQiandaoBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemWelfareQiandaoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_welfare_qiandao, null, false, component);
    }

    public static ItemWelfareQiandaoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWelfareQiandaoBinding bind(View view, Object component) {
        return (ItemWelfareQiandaoBinding) bind(component, view, R.layout.item_welfare_qiandao);
    }
}
