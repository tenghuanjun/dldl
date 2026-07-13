package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentGameServerBinding extends ViewDataBinding {
    public final ImageView iv;

    @Bindable
    protected String mData;

    public abstract void setData(String data);

    protected FragmentGameServerBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView iv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.iv = iv;
    }

    public String getData() {
        return this.mData;
    }

    public static FragmentGameServerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameServerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentGameServerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_game_server, root, attachToRoot, component);
    }

    public static FragmentGameServerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameServerBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentGameServerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_game_server, null, false, component);
    }

    public static FragmentGameServerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentGameServerBinding bind(View view, Object component) {
        return (FragmentGameServerBinding) bind(component, view, R.layout.fragment_game_server);
    }
}
