package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.view.Navigation;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityCancellationBinding extends ViewDataBinding {
    public final FrameLayout body;
    public final Navigation navigation;

    protected ActivityCancellationBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout body, Navigation navigation) {
        super(_bindingComponent, _root, _localFieldCount);
        this.body = body;
        this.navigation = navigation;
    }

    public static ActivityCancellationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCancellationBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityCancellationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_cancellation, root, attachToRoot, component);
    }

    public static ActivityCancellationBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCancellationBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityCancellationBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_cancellation, null, false, component);
    }

    public static ActivityCancellationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCancellationBinding bind(View view, Object component) {
        return (ActivityCancellationBinding) bind(component, view, R.layout.activity_cancellation);
    }
}
