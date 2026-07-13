package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogDealPlayedBinding extends ViewDataBinding {
    public final RecyclerView rv;

    protected DialogDealPlayedBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rv = rv;
    }

    public static DialogDealPlayedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealPlayedBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogDealPlayedBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_played, root, attachToRoot, component);
    }

    public static DialogDealPlayedBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealPlayedBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogDealPlayedBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_deal_played, null, false, component);
    }

    public static DialogDealPlayedBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDealPlayedBinding bind(View view, Object component) {
        return (DialogDealPlayedBinding) bind(component, view, R.layout.dialog_deal_played);
    }
}
