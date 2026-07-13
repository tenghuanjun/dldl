package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.CountdownView;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentCancellation3Binding extends ViewDataBinding {
    public final CountdownView btnCode;

    @Bindable
    protected String mCode;

    @Bindable
    protected String mPhone;
    public final ShapeTextView tvGo;

    public abstract void setCode(String code);

    public abstract void setPhone(String phone);

    protected FragmentCancellation3Binding(Object _bindingComponent, View _root, int _localFieldCount, CountdownView btnCode, ShapeTextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnCode = btnCode;
        this.tvGo = tvGo;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public String getCode() {
        return this.mCode;
    }

    public static FragmentCancellation3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCancellation3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentCancellation3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_cancellation3, root, attachToRoot, component);
    }

    public static FragmentCancellation3Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCancellation3Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentCancellation3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_cancellation3, null, false, component);
    }

    public static FragmentCancellation3Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCancellation3Binding bind(View view, Object component) {
        return (FragmentCancellation3Binding) bind(component, view, R.layout.fragment_cancellation3);
    }
}
