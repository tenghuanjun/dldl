package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityImageBinding extends ViewDataBinding {
    public final Navigation navigation;
    public final ViewPager2 vp;

    protected ActivityImageBinding(Object _bindingComponent, View _root, int _localFieldCount, Navigation navigation, ViewPager2 vp) {
        super(_bindingComponent, _root, _localFieldCount);
        this.navigation = navigation;
        this.vp = vp;
    }

    public static ActivityImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_image, root, attachToRoot, component);
    }

    public static ActivityImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityImageBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_image, null, false, component);
    }

    public static ActivityImageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityImageBinding bind(View view, Object component) {
        return (ActivityImageBinding) bind(component, view, R.layout.activity_image);
    }
}
