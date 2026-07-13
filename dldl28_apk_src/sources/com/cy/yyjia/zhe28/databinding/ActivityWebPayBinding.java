package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityWebPayBinding extends ViewDataBinding {

    @Bindable
    protected String mTitle;
    public final Navigation navigation;
    public final WebView wv;

    public abstract void setTitle(String title);

    protected ActivityWebPayBinding(Object _bindingComponent, View _root, int _localFieldCount, Navigation navigation, WebView wv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.navigation = navigation;
        this.wv = wv;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public static ActivityWebPayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWebPayBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityWebPayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_web_pay, root, attachToRoot, component);
    }

    public static ActivityWebPayBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWebPayBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityWebPayBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_web_pay, null, false, component);
    }

    public static ActivityWebPayBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWebPayBinding bind(View view, Object component) {
        return (ActivityWebPayBinding) bind(component, view, R.layout.activity_web_pay);
    }
}
