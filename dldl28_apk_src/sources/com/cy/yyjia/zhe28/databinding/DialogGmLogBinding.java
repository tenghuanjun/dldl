package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogGmLogBinding extends ViewDataBinding {
    public final RecyclerView rv;

    protected DialogGmLogBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
    }

    public static DialogGmLogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGmLogBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogGmLogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_gm_log, root, attachToRoot, component);
    }

    public static DialogGmLogBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGmLogBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogGmLogBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_gm_log, null, false, component);
    }

    public static DialogGmLogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGmLogBinding bind(View view, Object component) {
        return (DialogGmLogBinding) bind(component, view, R.layout.dialog_gm_log);
    }
}
