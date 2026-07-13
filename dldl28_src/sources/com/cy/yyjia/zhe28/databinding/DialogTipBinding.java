package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogTipBinding extends ViewDataBinding {

    @Bindable
    protected String mBtn;

    @Bindable
    protected String mTip;

    @Bindable
    protected String mTitle;
    public final ShapeTextView tvGo;

    public abstract void setBtn(String btn);

    public abstract void setTip(String tip);

    public abstract void setTitle(String title);

    protected DialogTipBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
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

    public static DialogTipBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTipBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogTipBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_tip, root, attachToRoot, component);
    }

    public static DialogTipBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTipBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogTipBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_tip, null, false, component);
    }

    public static DialogTipBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogTipBinding bind(View view, Object component) {
        return (DialogTipBinding) bind(component, view, R.layout.dialog_tip);
    }
}
