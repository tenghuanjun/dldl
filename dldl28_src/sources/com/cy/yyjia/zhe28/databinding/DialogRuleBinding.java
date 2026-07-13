package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogRuleBinding extends ViewDataBinding {

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f453tv;
    public final WebView wv;

    protected DialogRuleBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tv2, WebView wv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.f453tv = tv2;
        this.wv = wv;
    }

    public static DialogRuleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRuleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogRuleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_rule, root, attachToRoot, component);
    }

    public static DialogRuleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRuleBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogRuleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_rule, null, false, component);
    }

    public static DialogRuleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogRuleBinding bind(View view, Object component) {
        return (DialogRuleBinding) bind(component, view, R.layout.dialog_rule);
    }
}
