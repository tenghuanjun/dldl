package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogSqkRuleBinding extends ViewDataBinding {
    public final ShapeTextView tvBuy;
    public final ShapeTextView tvTip;
    public final TextView tvTitle;
    public final WebView wv;

    protected DialogSqkRuleBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView tvBuy, ShapeTextView tvTip, TextView tvTitle, WebView wv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvBuy = tvBuy;
        this.tvTip = tvTip;
        this.tvTitle = tvTitle;
        this.wv = wv;
    }

    public static DialogSqkRuleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSqkRuleBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogSqkRuleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_sqk_rule, root, attachToRoot, component);
    }

    public static DialogSqkRuleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSqkRuleBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogSqkRuleBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_sqk_rule, null, false, component);
    }

    public static DialogSqkRuleBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogSqkRuleBinding bind(View view, Object component) {
        return (DialogSqkRuleBinding) bind(component, view, R.layout.dialog_sqk_rule);
    }
}
