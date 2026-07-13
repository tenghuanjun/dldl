package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.NewGameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeScheduleHeadBinding extends ViewDataBinding {

    @Bindable
    protected NewGameBean mData;

    public abstract void setData(NewGameBean data);

    protected ItemHomeScheduleHeadBinding(Object _bindingComponent, View _root, int _localFieldCount) {
        super(_bindingComponent, _root, _localFieldCount);
    }

    public NewGameBean getData() {
        return this.mData;
    }

    public static ItemHomeScheduleHeadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeScheduleHeadBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeScheduleHeadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_schedule_head, root, attachToRoot, component);
    }

    public static ItemHomeScheduleHeadBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeScheduleHeadBinding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeScheduleHeadBinding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_schedule_head, null, false, component);
    }

    public static ItemHomeScheduleHeadBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeScheduleHeadBinding bind(View view, Object component) {
        return (ItemHomeScheduleHeadBinding) bind(component, view, R.layout.item_home_schedule_head);
    }
}
