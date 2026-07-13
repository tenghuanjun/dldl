package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentCancellation2Binding extends ViewDataBinding {
    public final ImageView ivCheck;

    @Bindable
    protected boolean mCheck;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f457tv;
    public final ShapeTextView tvCancel;
    public final ShapeTextView tvGo;

    public abstract void setCheck(boolean check);

    protected FragmentCancellation2Binding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivCheck, TextView tv2, ShapeTextView tvCancel, ShapeTextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivCheck = ivCheck;
        this.f457tv = tv2;
        this.tvCancel = tvCancel;
        this.tvGo = tvGo;
    }

    public boolean getCheck() {
        return this.mCheck;
    }

    public static FragmentCancellation2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCancellation2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentCancellation2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_cancellation2, root, attachToRoot, component);
    }

    public static FragmentCancellation2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCancellation2Binding inflate(LayoutInflater inflater, Object component) {
        return (FragmentCancellation2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_cancellation2, null, false, component);
    }

    public static FragmentCancellation2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentCancellation2Binding bind(View view, Object component) {
        return (FragmentCancellation2Binding) bind(component, view, R.layout.fragment_cancellation2);
    }
}
