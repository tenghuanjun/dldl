package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogWaitBinding extends ViewDataBinding {
    public final ProgressBar bar;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f455tv;

    protected DialogWaitBinding(Object _bindingComponent, View _root, int _localFieldCount, ProgressBar bar, TextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.bar = bar;
        this.f455tv = tv2;
    }

    public static DialogWaitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWaitBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogWaitBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_wait, root, attachToRoot, component);
    }

    public static DialogWaitBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWaitBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogWaitBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_wait, null, false, component);
    }

    public static DialogWaitBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogWaitBinding bind(View view, Object component) {
        return (DialogWaitBinding) bind(component, view, R.layout.dialog_wait);
    }
}
