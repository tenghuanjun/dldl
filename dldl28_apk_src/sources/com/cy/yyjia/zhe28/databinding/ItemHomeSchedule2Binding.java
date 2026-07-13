package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.NewGameBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemHomeSchedule2Binding extends ViewDataBinding {
    public final LinearLayout desc;
    public final LayoutDiscountBinding discount;

    @Bindable
    protected NewGameBean mData;
    public final LayoutGameNameBinding name;
    public final TextView tag;

    public abstract void setData(NewGameBean data);

    protected ItemHomeSchedule2Binding(Object _bindingComponent, View _root, int _localFieldCount, LinearLayout desc, LayoutDiscountBinding discount, LayoutGameNameBinding name, TextView tag) {
        super(_bindingComponent, _root, _localFieldCount);
        this.desc = desc;
        this.discount = discount;
        this.name = name;
        this.tag = tag;
    }

    public NewGameBean getData() {
        return this.mData;
    }

    public static ItemHomeSchedule2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeSchedule2Binding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ItemHomeSchedule2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_schedule2, root, attachToRoot, component);
    }

    public static ItemHomeSchedule2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeSchedule2Binding inflate(LayoutInflater inflater, Object component) {
        return (ItemHomeSchedule2Binding) ViewDataBinding.inflateInternal(inflater, R.layout.item_home_schedule2, null, false, component);
    }

    public static ItemHomeSchedule2Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHomeSchedule2Binding bind(View view, Object component) {
        return (ItemHomeSchedule2Binding) bind(component, view, R.layout.item_home_schedule2);
    }
}
