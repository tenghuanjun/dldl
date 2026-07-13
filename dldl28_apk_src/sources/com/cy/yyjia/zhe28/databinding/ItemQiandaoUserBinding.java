package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TaskResult;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemQiandaoUserBinding extends ViewDataBinding {

    @Bindable
    protected TaskResult.User mData;

    public abstract void setData(TaskResult.User data);

    protected ItemQiandaoUserBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public TaskResult.User getData() {
        return this.mData;
    }

    public static ItemQiandaoUserBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemQiandaoUserBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemQiandaoUserBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_qiandao_user, root, attachToRoot, component);
    }

    public static ItemQiandaoUserBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemQiandaoUserBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemQiandaoUserBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_qiandao_user, null, false, component);
    }

    public static ItemQiandaoUserBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemQiandaoUserBinding bind(View view, Object component) {
        return (ItemQiandaoUserBinding) bind(component, view, R.layout.item_qiandao_user);
    }
}
