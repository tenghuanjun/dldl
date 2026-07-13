package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.cy.yyjia.zhe28.view.WancmsStandardPlayer;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityVideoBinding extends ViewDataBinding {

    @Bindable
    protected String mData;
    public final Navigation navigation;
    public final WancmsStandardPlayer player;

    public abstract void setData(String data);

    protected ActivityVideoBinding(Object _bindingComponent, View _root, int _localFieldCount, Navigation navigation, WancmsStandardPlayer player) {
        super(_bindingComponent, _root, _localFieldCount);
        this.navigation = navigation;
        this.player = player;
    }

    public String getData() {
        return this.mData;
    }

    public static ActivityVideoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVideoBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityVideoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_video, root, attachToRoot, component);
    }

    public static ActivityVideoBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVideoBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityVideoBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_video, null, false, component);
    }

    public static ActivityVideoBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVideoBinding bind(View view, Object component) {
        return (ActivityVideoBinding) bind(component, view, R.layout.activity_video);
    }
}
