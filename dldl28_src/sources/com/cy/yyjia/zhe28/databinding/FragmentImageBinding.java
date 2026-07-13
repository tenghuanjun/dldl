package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.github.chrisbanes.photoview.PhotoView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentImageBinding extends ViewDataBinding {

    @Bindable
    protected String mData;
    public final PhotoView pv;

    public abstract void setData(String data);

    protected FragmentImageBinding(Object _bindingComponent, View _root, int _localFieldCount, PhotoView pv) {
        super(_bindingComponent, _root, _localFieldCount);
        this.pv = pv;
    }

    public String getData() {
        return this.mData;
    }

    public static FragmentImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentImageBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (FragmentImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_image, root, attachToRoot, component);
    }

    public static FragmentImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentImageBinding inflate(LayoutInflater inflater, Object component) {
        return (FragmentImageBinding) ViewDataBinding.inflateInternal(inflater, R.layout.fragment_image, null, false, component);
    }

    public static FragmentImageBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentImageBinding bind(View view, Object component) {
        return (FragmentImageBinding) bind(component, view, R.layout.fragment_image);
    }
}
