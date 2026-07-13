package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityGameReport2Binding extends ViewDataBinding {

    @Bindable
    protected String mGame;

    public abstract void setGame(String game);

    protected ActivityGameReport2Binding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public String getGame() {
        return this.mGame;
    }

    public static ActivityGameReport2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGameReport2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityGameReport2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_game_report2, root, attachToRoot, component);
    }

    public static ActivityGameReport2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGameReport2Binding inflate(LayoutInflater inflater, Object component) {
        return (ActivityGameReport2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_game_report2, null, false, component);
    }

    public static ActivityGameReport2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGameReport2Binding bind(View view, Object component) {
        return (ActivityGameReport2Binding) bind(component, view, R.layout.activity_game_report2);
    }
}
