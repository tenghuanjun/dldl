package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivitySubscribeBinding extends ViewDataBinding {
    public final Navigation navigation;
    public final TabLayout tab;
    public final ViewPager2 vp2;

    protected ActivitySubscribeBinding(Object _bindingComponent, View _root, int _localFieldCount, Navigation navigation, TabLayout tab, ViewPager2 vp2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.navigation = navigation;
        this.tab = tab;
        this.vp2 = vp2;
    }

    public static ActivitySubscribeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySubscribeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivitySubscribeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_subscribe, root, attachToRoot, component);
    }

    public static ActivitySubscribeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySubscribeBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivitySubscribeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_subscribe, null, false, component);
    }

    public static ActivitySubscribeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySubscribeBinding bind(View view, Object component) {
        return (ActivitySubscribeBinding) bind(component, view, R.layout.activity_subscribe);
    }
}
