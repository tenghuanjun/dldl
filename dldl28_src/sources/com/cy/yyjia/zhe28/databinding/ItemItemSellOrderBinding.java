package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ItemTradeRecordBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemItemSellOrderBinding extends ViewDataBinding {
    public final LinearLayout llRole;
    public final LinearLayout llRoleId;

    @Bindable
    protected ItemTradeRecordBean mData;
    public final ShapeTextView tvCancel;
    public final ShapeTextView tvGo;

    public abstract void setData(ItemTradeRecordBean data);

    protected ItemItemSellOrderBinding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout llRole, LinearLayout llRoleId, ShapeTextView tvCancel, ShapeTextView tvGo) {
        super(_bindingComponent, _root, _localFieldCount);
        this.llRole = llRole;
        this.llRoleId = llRoleId;
        this.tvCancel = tvCancel;
        this.tvGo = tvGo;
    }

    public ItemTradeRecordBean getData() {
        return this.mData;
    }

    public static ItemItemSellOrderBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemSellOrderBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemItemSellOrderBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_item_sell_order, root, attachToRoot, component);
    }

    public static ItemItemSellOrderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemSellOrderBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemItemSellOrderBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_item_sell_order, null, false, component);
    }

    public static ItemItemSellOrderBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemSellOrderBinding bind(View view, Object component) {
        return (ItemItemSellOrderBinding) bind(component, view, R.layout.item_item_sell_order);
    }
}
