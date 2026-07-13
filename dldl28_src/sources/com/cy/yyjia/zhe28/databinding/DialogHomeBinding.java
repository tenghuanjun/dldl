package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Indicator;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogHomeBinding extends ViewDataBinding {
    public final CheckBox cb;
    public final Indicator indicator;
    public final ImageView ivClose;

    @Bindable
    protected String mText;
    public final RecyclerView rv;

    public abstract void setText(String text);

    protected DialogHomeBinding(Object _bindingComponent, View _root, int _localFieldCount, CheckBox cb, Indicator indicator, ImageView ivClose, RecyclerView rv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.cb = cb;
        this.indicator = indicator;
        this.ivClose = ivClose;
        this.rv = rv;
    }

    public String getText() {
        return this.mText;
    }

    public static DialogHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogHomeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_home, root, attachToRoot, component);
    }

    public static DialogHomeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogHomeBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogHomeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_home, null, false, component);
    }

    public static DialogHomeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogHomeBinding bind(View view, Object component) {
        return (DialogHomeBinding) bind(component, view, R.layout.dialog_home);
    }
}
