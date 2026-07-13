package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityBaofuBinding extends ViewDataBinding {

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f439tv;
    public final TextView tvService;

    protected ActivityBaofuBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tv2, TextView tvService) {
        super(_bindingComponent, _root, _localFieldCount);
        this.f439tv = tv2;
        this.tvService = tvService;
    }

    public static ActivityBaofuBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBaofuBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityBaofuBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_baofu, root, attachToRoot, component);
    }

    public static ActivityBaofuBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBaofuBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityBaofuBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_baofu, null, false, component);
    }

    public static ActivityBaofuBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityBaofuBinding bind(View view, Object component) {
        return (ActivityBaofuBinding) bind(component, view, R.layout.activity_baofu);
    }
}
