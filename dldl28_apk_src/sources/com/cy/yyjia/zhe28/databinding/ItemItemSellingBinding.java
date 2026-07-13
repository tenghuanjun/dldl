package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ItemSellRecordBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemItemSellingBinding extends ViewDataBinding {

    @Bindable
    protected ItemSellRecordBean mData;
    public final ShapeTextView tvAdjust;
    public final ShapeTextView tvOffset;

    public abstract void setData(ItemSellRecordBean data);

    protected ItemItemSellingBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView tvAdjust, ShapeTextView tvOffset) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvAdjust = tvAdjust;
        this.tvOffset = tvOffset;
    }

    public ItemSellRecordBean getData() {
        return this.mData;
    }

    public static ItemItemSellingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemSellingBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemItemSellingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_item_selling, root, attachToRoot, component);
    }

    public static ItemItemSellingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemSellingBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemItemSellingBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_item_selling, null, false, component);
    }

    public static ItemItemSellingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemItemSellingBinding bind(View view, Object component) {
        return (ItemItemSellingBinding) bind(component, view, R.layout.item_item_selling);
    }
}
