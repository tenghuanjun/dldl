package com.cy.yyjia.zhe28.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.domain.MonthlyTaskBean;
import com.cy.yyjia.zhe28.domain.MonthlyTaskNavBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityMonthlyTaskBinding extends ViewDataBinding {
    public final ImageView ivRule;

    @Bindable
    protected MonthlyTaskBean mData;

    @Bindable
    protected MonthlyTaskNavBean mNav;

    @Bindable
    protected int mPosition;
    public final RecyclerView rv;
    public final TextView tvAll;

    public abstract void setData(MonthlyTaskBean data);

    public abstract void setNav(MonthlyTaskNavBean nav);

    public abstract void setPosition(int position);

    protected ActivityMonthlyTaskBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivRule, RecyclerView rv, TextView tvAll) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivRule = ivRule;
        this.rv = rv;
        this.tvAll = tvAll;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public MonthlyTaskNavBean getNav() {
        return this.mNav;
    }

    public MonthlyTaskBean getData() {
        return this.mData;
    }

    public static ActivityMonthlyTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMonthlyTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityMonthlyTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_monthly_task, root, attachToRoot, component);
    }

    public static ActivityMonthlyTaskBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMonthlyTaskBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityMonthlyTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_monthly_task, null, false, component);
    }

    public static ActivityMonthlyTaskBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMonthlyTaskBinding bind(View view, Object component) {
        return (ActivityMonthlyTaskBinding) bind(component, view, R.layout.activity_monthly_task);
    }
}
