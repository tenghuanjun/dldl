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
public abstract class DialogShareBinding extends ViewDataBinding {

    @Bindable
    protected View.OnClickListener mOnClick;
    public final TextView tv1;
    public final TextView tv2;
    public final TextView tv3;
    public final TextView tv4;
    public final TextView tv5;
    public final TextView tvCancel;
    public final TextView tvChat;
    public final TextView tvGame;

    public abstract void setOnClick(View.OnClickListener onClick);

    protected DialogShareBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tv1, TextView tv2, TextView tv3, TextView tv4, TextView tv5, TextView tvCancel, TextView tvChat, TextView tvGame) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.tv3 = tv3;
        this.tv4 = tv4;
        this.tv5 = tv5;
        this.tvCancel = tvCancel;
        this.tvChat = tvChat;
        this.tvGame = tvGame;
    }

    public View.OnClickListener getOnClick() {
        return this.mOnClick;
    }

    public static DialogShareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogShareBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogShareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_share, root, attachToRoot, component);
    }

    public static DialogShareBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogShareBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogShareBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_share, null, false, component);
    }

    public static DialogShareBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogShareBinding bind(View view, Object component) {
        return (DialogShareBinding) bind(component, view, R.layout.dialog_share);
    }
}
