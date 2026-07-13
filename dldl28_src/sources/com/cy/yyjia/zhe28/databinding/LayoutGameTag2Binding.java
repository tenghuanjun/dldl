package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LayoutGameTag2Binding extends ViewDataBinding {

    @Bindable
    protected List<String> mData;

    public abstract void setData(List<String> data);

    protected LayoutGameTag2Binding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public List<String> getData() {
        return this.mData;
    }

    public static LayoutGameTag2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGameTag2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LayoutGameTag2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_game_tag2, root, attachToRoot, component);
    }

    public static LayoutGameTag2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGameTag2Binding inflate(LayoutInflater inflater, Object component) {
        return (LayoutGameTag2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_game_tag2, null, false, component);
    }

    public static LayoutGameTag2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGameTag2Binding bind(View view, Object component) {
        return (LayoutGameTag2Binding) bind(component, view, R.layout.layout_game_tag2);
    }
}
