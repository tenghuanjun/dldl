package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogPrivacyBinding extends ViewDataBinding {

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f451tv;
    public final ShapeTextView tvClose;
    public final ShapeTextView tvGo;

    protected DialogPrivacyBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tv2, ShapeTextView tvClose, ShapeTextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.f451tv = tv2;
        this.tvClose = tvClose;
        this.tvGo = tvGo;
    }

    public static DialogPrivacyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPrivacyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogPrivacyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_privacy, root, attachToRoot, component);
    }

    public static DialogPrivacyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPrivacyBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogPrivacyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_privacy, null, false, component);
    }

    public static DialogPrivacyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPrivacyBinding bind(View view, Object component) {
        return (DialogPrivacyBinding) bind(component, view, R.layout.dialog_privacy);
    }
}
