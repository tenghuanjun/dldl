package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemGameTypeBinding extends ViewDataBinding {

    @Bindable
    protected TypeBean mData;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final ShapeTextView f465tv;

    public abstract void setData(TypeBean data);

    protected ItemGameTypeBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.f465tv = tv2;
    }

    public TypeBean getData() {
        return this.mData;
    }

    public static ItemGameTypeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameTypeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemGameTypeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_type, root, attachToRoot, component);
    }

    public static ItemGameTypeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameTypeBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemGameTypeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_game_type, null, false, component);
    }

    public static ItemGameTypeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemGameTypeBinding bind(View view, Object component) {
        return (ItemGameTypeBinding) bind(component, view, R.layout.item_game_type);
    }
}
