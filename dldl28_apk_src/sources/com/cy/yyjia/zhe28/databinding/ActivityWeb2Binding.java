package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityWeb2Binding extends ViewDataBinding {

    @Bindable
    protected String mTitle;
    public final Navigation navigation;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f443tv;
    public final WebView wv;

    public abstract void setTitle(String title);

    protected ActivityWeb2Binding(Object _bindingComponent, View _root, int _localFieldCount, Navigation navigation, TextView tv2, WebView wv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.navigation = navigation;
        this.f443tv = tv2;
        this.wv = wv;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public static ActivityWeb2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWeb2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityWeb2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_web2, root, attachToRoot, component);
    }

    public static ActivityWeb2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWeb2Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityWeb2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_web2, null, false, component);
    }

    public static ActivityWeb2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWeb2Binding bind(View view, Object component) {
        return (ActivityWeb2Binding) bind(component, view, R.layout.activity_web2);
    }
}
