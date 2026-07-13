package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ItemSellRecordBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DialogItemAdjustBinding extends ViewDataBinding {
    public final EditText et;

    @Bindable
    protected ItemSellRecordBean mData;
    public final ShapeTextView tvAdjust;
    public final ShapeTextView tvCancel;

    public abstract void setData(ItemSellRecordBean data);

    protected DialogItemAdjustBinding(Object _bindingComponent, View _root, int _localFieldCount, EditText et, ShapeTextView tvAdjust, ShapeTextView tvCancel) {
        super(_bindingComponent, _root, _localFieldCount);
        this.et = et;
        this.tvAdjust = tvAdjust;
        this.tvCancel = tvCancel;
    }

    public ItemSellRecordBean getData() {
        return this.mData;
    }

    public static DialogItemAdjustBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogItemAdjustBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (DialogItemAdjustBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_item_adjust, root, attachToRoot, component);
    }

    public static DialogItemAdjustBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogItemAdjustBinding inflate(LayoutInflater inflater, Object component) {
        return (DialogItemAdjustBinding) ViewDataBinding.inflateInternal(inflater, R.layout.dialog_item_adjust, null, false, component);
    }

    public static DialogItemAdjustBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogItemAdjustBinding bind(View view, Object component) {
        return (DialogItemAdjustBinding) bind(component, view, R.layout.dialog_item_adjust);
    }
}
