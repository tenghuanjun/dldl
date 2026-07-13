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
public abstract class ItemHallGameTypeBinding extends ViewDataBinding {

    @Bindable
    protected TypeBean mData;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final ShapeTextView f466tv;

    public abstract void setData(TypeBean data);

    protected ItemHallGameTypeBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.f466tv = tv2;
    }

    public TypeBean getData() {
        return this.mData;
    }

    public static ItemHallGameTypeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHallGameTypeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHallGameTypeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_hall_game_type, root, attachToRoot, component);
    }

    public static ItemHallGameTypeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHallGameTypeBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHallGameTypeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_hall_game_type, null, false, component);
    }

    public static ItemHallGameTypeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHallGameTypeBinding bind(View view, Object component) {
        return (ItemHallGameTypeBinding) bind(component, view, R.layout.item_hall_game_type);
    }
}
