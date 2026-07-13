package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameHistoryBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogGameUpdateDetailBinding extends ViewDataBinding {

    @Bindable
    protected GameHistoryBean mData;
    public final TextView tvClose;

    public abstract void setData(GameHistoryBean data);

    protected DialogGameUpdateDetailBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tvClose) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvClose = tvClose;
    }

    public GameHistoryBean getData() {
        return this.mData;
    }

    public static DialogGameUpdateDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGameUpdateDetailBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogGameUpdateDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_game_update_detail, root, attachToRoot, component);
    }

    public static DialogGameUpdateDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGameUpdateDetailBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogGameUpdateDetailBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_game_update_detail, null, false, component);
    }

    public static DialogGameUpdateDetailBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogGameUpdateDetailBinding bind(View view, Object component) {
        return (DialogGameUpdateDetailBinding) bind(component, view, R.layout.dialog_game_update_detail);
    }
}
