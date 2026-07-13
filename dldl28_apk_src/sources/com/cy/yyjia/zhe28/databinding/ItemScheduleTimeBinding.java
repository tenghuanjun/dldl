package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.ScheduleTimeBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemScheduleTimeBinding extends ViewDataBinding {

    @Bindable
    protected ScheduleTimeBean.Day mData;

    public abstract void setData(ScheduleTimeBean.Day data);

    protected ItemScheduleTimeBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public ScheduleTimeBean.Day getData() {
        return this.mData;
    }

    public static ItemScheduleTimeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemScheduleTimeBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemScheduleTimeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_schedule_time, root, attachToRoot, component);
    }

    public static ItemScheduleTimeBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemScheduleTimeBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemScheduleTimeBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_schedule_time, null, false, component);
    }

    public static ItemScheduleTimeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemScheduleTimeBinding bind(View view, Object component) {
        return (ItemScheduleTimeBinding) bind(component, view, R.layout.item_schedule_time);
    }
}
