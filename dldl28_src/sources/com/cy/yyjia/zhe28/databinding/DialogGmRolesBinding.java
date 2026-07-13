package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogGmRolesBinding extends ViewDataBinding {
    public final ImageView ivClose;
    public final RecyclerView rv;
    public final TextView tvTitle;

    protected DialogGmRolesBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivClose, RecyclerView rv, TextView tvTitle) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivClose = ivClose;
        this.rv = rv;
        this.tvTitle = tvTitle;
    }

    public static DialogGmRolesBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGmRolesBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogGmRolesBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_gm_roles, root, attachToRoot, component);
    }

    public static DialogGmRolesBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGmRolesBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogGmRolesBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_gm_roles, null, false, component);
    }

    public static DialogGmRolesBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGmRolesBinding bind(View view, Object component) {
        return (DialogGmRolesBinding) bind(component, view, R.layout.dialog_gm_roles);
    }
}
