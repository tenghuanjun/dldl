package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LayoutGameDetailTabBinding extends ViewDataBinding {

    @Bindable
    protected boolean mSelected;

    @Bindable
    protected String mText;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final TextView f473tv;

    public abstract void setSelected(boolean selected);

    public abstract void setText(String text);

    protected LayoutGameDetailTabBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.f473tv = tv2;
    }

    public String getText() {
        return this.mText;
    }

    public boolean getSelected() {
        return this.mSelected;
    }

    public static LayoutGameDetailTabBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGameDetailTabBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LayoutGameDetailTabBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_game_detail_tab, root, attachToRoot, component);
    }

    public static LayoutGameDetailTabBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGameDetailTabBinding inflate(LayoutInflater inflater, Object component) {
        return (LayoutGameDetailTabBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_game_detail_tab, null, false, component);
    }

    public static LayoutGameDetailTabBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutGameDetailTabBinding bind(View view, Object component) {
        return (LayoutGameDetailTabBinding) bind(component, view, R.layout.layout_game_detail_tab);
    }
}
