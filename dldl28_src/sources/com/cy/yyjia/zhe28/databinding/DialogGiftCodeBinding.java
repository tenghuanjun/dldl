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
public abstract class DialogGiftCodeBinding extends ViewDataBinding {

    @Bindable
    protected String mData;

    @Bindable
    protected String mIntro;
    public final TextView tvCopy;

    public abstract void setData(String data);

    public abstract void setIntro(String intro);

    protected DialogGiftCodeBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tvCopy) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvCopy = tvCopy;
    }

    public String getData() {
        return this.mData;
    }

    public String getIntro() {
        return this.mIntro;
    }

    public static DialogGiftCodeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGiftCodeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogGiftCodeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_gift_code, root, attachToRoot, component);
    }

    public static DialogGiftCodeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGiftCodeBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogGiftCodeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_gift_code, null, false, component);
    }

    public static DialogGiftCodeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGiftCodeBinding bind(View view, Object component) {
        return (DialogGiftCodeBinding) bind(component, view, R.layout.dialog_gift_code);
    }
}
