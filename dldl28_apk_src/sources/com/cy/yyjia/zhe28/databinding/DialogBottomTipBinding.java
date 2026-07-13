package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameDetailBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogBottomTipBinding extends ViewDataBinding {

    @Bindable
    protected GameDetailBean mData;
    public final RecyclerView rvVip;
    public final TextView tvPermission;
    public final TextView tvPrivacy;

    public abstract void setData(GameDetailBean data);

    protected DialogBottomTipBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView rvVip, TextView tvPermission, TextView tvPrivacy) {
        super(_bindingComponent, _root, _localFieldCount);
        this.rvVip = rvVip;
        this.tvPermission = tvPermission;
        this.tvPrivacy = tvPrivacy;
    }

    public GameDetailBean getData() {
        return this.mData;
    }

    public static DialogBottomTipBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBottomTipBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogBottomTipBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_bottom_tip, root, attachToRoot, component);
    }

    public static DialogBottomTipBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBottomTipBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogBottomTipBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_bottom_tip, null, false, component);
    }

    public static DialogBottomTipBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBottomTipBinding bind(View view, Object component) {
        return (DialogBottomTipBinding) bind(component, view, R.layout.dialog_bottom_tip);
    }
}
