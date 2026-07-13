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
public abstract class ItemTaskMonthlyBinding extends ViewDataBinding {
    public final TextView btn;

    @Bindable
    protected TaskBean mData;

    public abstract void setData(TaskBean data);

    protected ItemTaskMonthlyBinding(Object _bindingComponent, View _root, int _localFieldCount, TextView btn) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btn = btn;
    }

    public TaskBean getData() {
        return this.mData;
    }

    public static ItemTaskMonthlyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTaskMonthlyBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemTaskMonthlyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_task_monthly, root, attachToRoot, component);
    }

    public static ItemTaskMonthlyBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTaskMonthlyBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemTaskMonthlyBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_task_monthly, null, false, component);
    }

    public static ItemTaskMonthlyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTaskMonthlyBinding bind(View view, Object component) {
        return (ItemTaskMonthlyBinding) bind(component, view, R.layout.item_task_monthly);
    }
}
