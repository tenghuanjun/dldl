package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.TaskBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemTaskDailyBinding extends ViewDataBinding {
    public final TextView btn;

    @Bindable
    protected TaskBean mData;

    public abstract void setData(TaskBean data);

    protected ItemTaskDailyBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
    }

    public TaskBean getData() {
        return this.mData;
    }

    public static ItemTaskDailyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTaskDailyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemTaskDailyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_task_daily, root, attachToRoot, component);
    }

    public static ItemTaskDailyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTaskDailyBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemTaskDailyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_task_daily, null, false, component);
    }

    public static ItemTaskDailyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTaskDailyBinding bind(View view, Object component) {
        return (ItemTaskDailyBinding) bind(component, view, R.layout.item_task_daily);
    }
}
