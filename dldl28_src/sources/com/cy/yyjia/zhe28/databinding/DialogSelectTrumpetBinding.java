package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogSelectTrumpetBinding extends ViewDataBinding {

    @Bindable
    protected List mData;
    public final RecyclerView rv;
    public final TextView tvCancel;
    public final TextView tvConfirm;
    public final TextView tvTip;
    public final TextView tvTitle;

    public abstract void setData(List data);

    protected DialogSelectTrumpetBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv, TextView tvCancel, TextView tvConfirm, TextView tvTip, TextView tvTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
        this.tvCancel = tvCancel;
        this.tvConfirm = tvConfirm;
        this.tvTip = tvTip;
        this.tvTitle = tvTitle;
    }

    public List getData() {
        return this.mData;
    }

    public static DialogSelectTrumpetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSelectTrumpetBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogSelectTrumpetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_select_trumpet, root, attachToRoot, component);
    }

    public static DialogSelectTrumpetBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSelectTrumpetBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogSelectTrumpetBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_select_trumpet, null, false, component);
    }

    public static DialogSelectTrumpetBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSelectTrumpetBinding bind(View view, Object component) {
        return (DialogSelectTrumpetBinding) bind(component, view, R.layout.dialog_select_trumpet);
    }
}
