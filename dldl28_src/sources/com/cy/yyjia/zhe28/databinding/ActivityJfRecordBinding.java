package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityJfRecordBinding extends ViewDataBinding {
    public final RecyclerView list;

    protected ActivityJfRecordBinding(Object _bindingComponent, View _root, int _localFieldCount, RecyclerView list) {
        super(_bindingComponent, _root, _localFieldCount);
        this.list = list;
    }

    public static ActivityJfRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityJfRecordBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityJfRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_jf_record, root, attachToRoot, component);
    }

    public static ActivityJfRecordBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityJfRecordBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityJfRecordBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_jf_record, null, false, component);
    }

    public static ActivityJfRecordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityJfRecordBinding bind(View view, Object component) {
        return (ActivityJfRecordBinding) bind(component, view, R.layout.activity_jf_record);
    }
}
