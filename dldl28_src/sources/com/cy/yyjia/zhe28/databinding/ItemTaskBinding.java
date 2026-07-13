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
import com.hjq.shape.view.ShapeButton;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemTaskBinding extends ViewDataBinding {
    public final ShapeButton btnNext;

    @Bindable
    protected TaskBean mData;
    public final TextView tv1;
    public final TextView tv2;

    public abstract void setData(TaskBean data);

    protected ItemTaskBinding(Object _bindingComponent, View _root, int _localFieldCount, ShapeButton btnNext, TextView tv1, TextView tv2) {
        super(_bindingComponent, _root, _localFieldCount);
        this.btnNext = btnNext;
        this.tv1 = tv1;
        this.tv2 = tv2;
    }

    public TaskBean getData() {
        return this.mData;
    }

    public static ItemTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_task, root, attachToRoot, component);
    }

    public static ItemTaskBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTaskBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_task, null, false, component);
    }

    public static ItemTaskBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTaskBinding bind(View view, Object component) {
        return (ItemTaskBinding) bind(component, view, R.layout.item_task);
    }
}
