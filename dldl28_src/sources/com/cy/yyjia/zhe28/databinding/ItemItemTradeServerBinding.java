package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemItemTradeServerBinding extends ViewDataBinding {

    @Bindable
    protected String mData;

    /* JADX INFO: renamed from: tv, reason: collision with root package name */
    public final ShapeTextView f468tv;

    public abstract void setData(String data);

    protected ItemItemTradeServerBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.f468tv = tv2;
    }

    public String getData() {
        return this.mData;
    }

    public static ItemItemTradeServerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemTradeServerBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemItemTradeServerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_item_trade_server, root, attachToRoot, component);
    }

    public static ItemItemTradeServerBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemTradeServerBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemItemTradeServerBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_item_trade_server, null, false, component);
    }

    public static ItemItemTradeServerBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemTradeServerBinding bind(View view, Object component) {
        return (ItemItemTradeServerBinding) bind(component, view, R.layout.item_item_trade_server);
    }
}
