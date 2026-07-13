package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogBottomTip2Binding extends ViewDataBinding {
    public final ImageView ivBack;
    public final TextView title;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f446tv;
    public final WebView wv;

    protected DialogBottomTip2Binding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivBack, TextView title, TextView tv2, WebView wv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivBack = ivBack;
        this.title = title;
        this.f446tv = tv2;
        this.wv = wv;
    }

    public static DialogBottomTip2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBottomTip2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogBottomTip2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_bottom_tip2, root, attachToRoot, component);
    }

    public static DialogBottomTip2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBottomTip2Binding inflate(LayoutInflater inflater, Object component) {
        return (DialogBottomTip2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_bottom_tip2, null, false, component);
    }

    public static DialogBottomTip2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBottomTip2Binding bind(View view, Object component) {
        return (DialogBottomTip2Binding) bind(component, view, R.layout.dialog_bottom_tip2);
    }
}
