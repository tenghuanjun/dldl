package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentCancellation1Binding extends ViewDataBinding {

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final ShapeTextView f456tv;

    protected FragmentCancellation1Binding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.f456tv = tv2;
    }

    public static FragmentCancellation1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCancellation1Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentCancellation1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_cancellation1, root, attachToRoot, component);
    }

    public static FragmentCancellation1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCancellation1Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentCancellation1Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_cancellation1, null, false, component);
    }

    public static FragmentCancellation1Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCancellation1Binding bind(View view, Object component) {
        return (FragmentCancellation1Binding) bind(component, view, R.layout.fragment_cancellation1);
    }
}
