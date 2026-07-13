package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.RecordBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemPointRecordBinding extends ViewDataBinding {

    @Bindable
    protected RecordBean mData;

    public abstract void setData(RecordBean data);

    protected ItemPointRecordBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public RecordBean getData() {
        return this.mData;
    }

    public static ItemPointRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPointRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemPointRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_point_record, root, attachToRoot, component);
    }

    public static ItemPointRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPointRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemPointRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_point_record, null, false, component);
    }

    public static ItemPointRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemPointRecordBinding bind(View view, Object component) {
        return (ItemPointRecordBinding) bind(component, view, R.layout.item_point_record);
    }
}
