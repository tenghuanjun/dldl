package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogWelfare3GiftBinding extends ViewDataBinding {
    public final TextView btn;
    public final TextView dismiss;
    public final RecyclerView rv;
    public final TextView title;

    protected DialogWelfare3GiftBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn, TextView dismiss, RecyclerView rv, TextView title) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.dismiss = dismiss;
        this.rv = rv;
        this.title = title;
    }

    public static DialogWelfare3GiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWelfare3GiftBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogWelfare3GiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_welfare3_gift, root, attachToRoot, component);
    }

    public static DialogWelfare3GiftBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWelfare3GiftBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogWelfare3GiftBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_welfare3_gift, null, false, component);
    }

    public static DialogWelfare3GiftBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWelfare3GiftBinding bind(View view, Object component) {
        return (DialogWelfare3GiftBinding) bind(component, view, R.layout.dialog_welfare3_gift);
    }
}
