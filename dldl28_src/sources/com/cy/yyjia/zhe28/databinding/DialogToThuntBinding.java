package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.SignResultBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogToThuntBinding extends ViewDataBinding {
    public final TextView btn;

    @Bindable
    protected SignResultBean mData;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f454tv;
    public final TextView tv1;

    public abstract void setData(SignResultBean data);

    protected DialogToThuntBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, TextView tv2, TextView tv1) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.f454tv = tv2;
        this.tv1 = tv1;
    }

    public SignResultBean getData() {
        return this.mData;
    }

    public static DialogToThuntBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogToThuntBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogToThuntBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_to_thunt, root, attachToRoot, component);
    }

    public static DialogToThuntBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogToThuntBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogToThuntBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_to_thunt, null, false, component);
    }

    public static DialogToThuntBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogToThuntBinding bind(View view, Object component) {
        return (DialogToThuntBinding) bind(component, view, R.layout.dialog_to_thunt);
    }
}
