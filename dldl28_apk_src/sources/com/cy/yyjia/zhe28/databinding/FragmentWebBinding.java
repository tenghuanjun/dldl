package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentWebBinding extends ViewDataBinding {
    public final WebView wv;

    protected FragmentWebBinding(Object _bindingComponent, View _root, int _localFieldCount, WebView wv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.wv = wv;
    }

    public static FragmentWebBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWebBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentWebBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_web, root, attachToRoot, component);
    }

    public static FragmentWebBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWebBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentWebBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_web, null, false, component);
    }

    public static FragmentWebBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentWebBinding bind(View view, Object component) {
        return (FragmentWebBinding) bind(component, view, R.layout.fragment_web);
    }
}
