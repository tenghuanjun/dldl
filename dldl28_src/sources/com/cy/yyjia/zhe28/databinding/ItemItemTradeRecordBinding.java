package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ItemTradeRecordBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemItemTradeRecordBinding extends ViewDataBinding {

    @Bindable
    protected ItemTradeRecordBean mData;
    public final ShapeTextView tvConfirm;

    public abstract void setData(ItemTradeRecordBean data);

    protected ItemItemTradeRecordBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView tvConfirm) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvConfirm = tvConfirm;
    }

    public ItemTradeRecordBean getData() {
        return this.mData;
    }

    public static ItemItemTradeRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemTradeRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemItemTradeRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_item_trade_record, root, attachToRoot, component);
    }

    public static ItemItemTradeRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemTradeRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemItemTradeRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_item_trade_record, null, false, component);
    }

    public static ItemItemTradeRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemTradeRecordBinding bind(View view, Object component) {
        return (ItemItemTradeRecordBinding) bind(component, view, R.layout.item_item_trade_record);
    }
}
