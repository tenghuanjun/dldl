package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.GameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class LayoutDiscountBinding extends ViewDataBinding {
    public final FrameLayout discount;
    public final LinearLayout first;
    public final LinearLayout llFree;

    @Bindable
    protected GameBean mData;

    @Bindable
    protected boolean mGame;
    public final LinearLayout other;

    public abstract void setData(GameBean data);

    public abstract void setGame(boolean game);

    protected LayoutDiscountBinding(Object _bindingComponent, View _root, int _localFieldCount, FrameLayout discount, LinearLayout first, LinearLayout llFree, LinearLayout other) {
        super(_bindingComponent, _root, _localFieldCount);
        this.discount = discount;
        this.first = first;
        this.llFree = llFree;
        this.other = other;
    }

    public boolean getGame() {
        return this.mGame;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static LayoutDiscountBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutDiscountBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (LayoutDiscountBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_discount, root, attachToRoot, component);
    }

    public static LayoutDiscountBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutDiscountBinding inflate(LayoutInflater inflater, Object component) {
        return (LayoutDiscountBinding) ViewDataBinding.inflateInternal(inflater, R.layout.layout_discount, null, false, component);
    }

    public static LayoutDiscountBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutDiscountBinding bind(View view, Object component) {
        return (LayoutDiscountBinding) bind(component, view, R.layout.layout_discount);
    }
}
