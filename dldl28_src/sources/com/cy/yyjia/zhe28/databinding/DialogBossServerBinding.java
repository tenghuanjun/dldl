package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.BossServerBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogBossServerBinding extends ViewDataBinding {
    public final ImageView iv;

    @Bindable
    protected BossServerBean.Privilege mData;

    public abstract void setData(BossServerBean.Privilege data);

    protected DialogBossServerBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
    }

    public BossServerBean.Privilege getData() {
        return this.mData;
    }

    public static DialogBossServerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBossServerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogBossServerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_boss_server, root, attachToRoot, component);
    }

    public static DialogBossServerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBossServerBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogBossServerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_boss_server, null, false, component);
    }

    public static DialogBossServerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogBossServerBinding bind(View view, Object component) {
        return (DialogBossServerBinding) bind(component, view, R.layout.dialog_boss_server);
    }
}
