package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentGameToolBinding extends ViewDataBinding {
    protected FragmentGameToolBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public static FragmentGameToolBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameToolBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentGameToolBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_game_tool, root, attachToRoot, component);
    }

    public static FragmentGameToolBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameToolBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentGameToolBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_game_tool, null, false, component);
    }

    public static FragmentGameToolBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameToolBinding bind(View view, Object component) {
        return (FragmentGameToolBinding) bind(component, view, R.layout.fragment_game_tool);
    }
}
