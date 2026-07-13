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
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeNewBinding extends ViewDataBinding {
    public final ShapeTextView btn;
    public final LinearLayout desc;
    public final LayoutDiscountBinding discount;
    public final FrameLayout fl;

    @Bindable
    protected GameBean mData;

    @Bindable
    protected boolean mOrder;
    public final LayoutGameNameBinding name;
    public final LinearLayout tag;
    public final LinearLayout tag2;

    public abstract void setData(GameBean data);

    public abstract void setOrder(boolean order);

    protected ItemHomeNewBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView btn, LinearLayout desc, LayoutDiscountBinding discount, FrameLayout fl, LayoutGameNameBinding name, LinearLayout tag, LinearLayout tag2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
        this.desc = desc;
        this.discount = discount;
        this.fl = fl;
        this.name = name;
        this.tag = tag;
        this.tag2 = tag2;
    }

    public boolean getOrder() {
        return this.mOrder;
    }

    public GameBean getData() {
        return this.mData;
    }

    public static ItemHomeNewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeNewBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeNewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_new, root, attachToRoot, component);
    }

    public static ItemHomeNewBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeNewBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeNewBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_new, null, false, component);
    }

    public static ItemHomeNewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeNewBinding bind(View view, Object component) {
        return (ItemHomeNewBinding) bind(component, view, R.layout.item_home_new);
    }
}
