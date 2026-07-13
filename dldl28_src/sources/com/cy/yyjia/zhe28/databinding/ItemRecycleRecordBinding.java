package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.RecycleRecordBean;
import com.hjq.shape.view.ShapeTextView;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemRecycleRecordBinding extends ViewDataBinding {

    @Bindable
    protected RecycleRecordBean mData;
    public final ShapeTextView tvOff;

    public abstract void setData(RecycleRecordBean data);

    protected ItemRecycleRecordBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeTextView tvOff) {
        super(_bindingComponent, _root, _localFieldCount);
        this.tvOff = tvOff;
    }

    public RecycleRecordBean getData() {
        return this.mData;
    }

    public static ItemRecycleRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRecycleRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemRecycleRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_recycle_record, root, attachToRoot, component);
    }

    public static ItemRecycleRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRecycleRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemRecycleRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_recycle_record, null, false, component);
    }

    public static ItemRecycleRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemRecycleRecordBinding bind(View view, Object component) {
        return (ItemRecycleRecordBinding) bind(component, view, R.layout.item_recycle_record);
    }
}
