package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.YunIndexBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogYunDeviceBinding extends ViewDataBinding {

    @Bindable
    protected YunIndexBean mData;
    public final RecyclerView rv;
    public final ShapeTextView tvCancel;
    public final ShapeTextView tvGo;

    public abstract void setData(YunIndexBean data);

    protected DialogYunDeviceBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv, ShapeTextView tvCancel, ShapeTextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
        this.tvCancel = tvCancel;
        this.tvGo = tvGo;
    }

    public YunIndexBean getData() {
        return this.mData;
    }

    public static DialogYunDeviceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogYunDeviceBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogYunDeviceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_yun_device, root, attachToRoot, component);
    }

    public static DialogYunDeviceBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogYunDeviceBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogYunDeviceBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_yun_device, null, false, component);
    }

    public static DialogYunDeviceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogYunDeviceBinding bind(View view, Object component) {
        return (DialogYunDeviceBinding) bind(component, view, R.layout.dialog_yun_device);
    }
}
