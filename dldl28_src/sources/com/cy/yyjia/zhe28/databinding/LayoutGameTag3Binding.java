package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LayoutGameTag3Binding extends ViewDataBinding {

    @Bindable
    protected List<GameBean.Tag> mData;

    public abstract void setData(List<GameBean.Tag> data);

    protected LayoutGameTag3Binding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public List<GameBean.Tag> getData() {
        return this.mData;
    }

    public static LayoutGameTag3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGameTag3Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LayoutGameTag3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_game_tag3, root, attachToRoot, component);
    }

    public static LayoutGameTag3Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGameTag3Binding inflate(LayoutInflater inflater, Object component) {
        return (LayoutGameTag3Binding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_game_tag3, null, false, component);
    }

    public static LayoutGameTag3Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGameTag3Binding bind(View view, Object component) {
        return (LayoutGameTag3Binding) bind(component, view, R.layout.layout_game_tag3);
    }
}
