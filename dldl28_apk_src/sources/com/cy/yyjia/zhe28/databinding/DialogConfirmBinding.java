package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogConfirmBinding extends ViewDataBinding {
    public final TextView content;

    @Bindable
    protected String mBtn;

    @Bindable
    protected String mTip;

    @Bindable
    protected String mTitle;
    public final TextView tvGo;

    public abstract void setBtn(String btn);

    public abstract void setTip(String tip);

    public abstract void setTitle(String title);

    protected DialogConfirmBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView content, TextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.content = content;
        this.tvGo = tvGo;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getTip() {
        return this.mTip;
    }

    public String getBtn() {
        return this.mBtn;
    }

    public static DialogConfirmBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogConfirmBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogConfirmBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_confirm, root, attachToRoot, component);
    }

    public static DialogConfirmBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogConfirmBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogConfirmBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_confirm, null, false, component);
    }

    public static DialogConfirmBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogConfirmBinding bind(View view, Object component) {
        return (DialogConfirmBinding) bind(component, view, R.layout.dialog_confirm);
    }
}
