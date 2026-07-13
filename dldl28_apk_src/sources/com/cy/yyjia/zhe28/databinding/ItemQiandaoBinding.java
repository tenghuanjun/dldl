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
public abstract class ItemQiandaoBinding extends ViewDataBinding {

    @Bindable
    protected QiandaoBean.Day mData;

    public abstract void setData(QiandaoBean.Day data);

    protected ItemQiandaoBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public QiandaoBean.Day getData() {
        return this.mData;
    }

    public static ItemQiandaoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemQiandaoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemQiandaoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_qiandao, root, attachToRoot, component);
    }

    public static ItemQiandaoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemQiandaoBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemQiandaoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_qiandao, null, false, component);
    }

    public static ItemQiandaoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemQiandaoBinding bind(View view, Object component) {
        return (ItemQiandaoBinding) bind(component, view, R.layout.item_qiandao);
    }
}
