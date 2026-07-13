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
import com.cy.yyjia.zhe28.domain.QiandaoBean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ActivityDailyTaskBinding extends ViewDataBinding {
    public final ImageView ivRule;

    @Bindable
    protected QiandaoBean mData;

    @Bindable
    protected int mPosition;
    public final RecyclerView rv;
    public final TextView tvAll;

    public abstract void setData(QiandaoBean data);

    public abstract void setPosition(int position);

    protected ActivityDailyTaskBinding(Object _bindingComponent, View _root, int _localFieldCount, ImageView ivRule, RecyclerView rv, TextView tvAll) {
        super(_bindingComponent, _root, _localFieldCount);
        this.ivRule = ivRule;
        this.rv = rv;
        this.tvAll = tvAll;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public QiandaoBean getData() {
        return this.mData;
    }

    public static ActivityDailyTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDailyTaskBinding inflate(LayoutInflater inflater, ViewGroup root, boolean attachToRoot, Object component) {
        return (ActivityDailyTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_daily_task, root, attachToRoot, component);
    }

    public static ActivityDailyTaskBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDailyTaskBinding inflate(LayoutInflater inflater, Object component) {
        return (ActivityDailyTaskBinding) ViewDataBinding.inflateInternal(inflater, R.layout.activity_daily_task, null, false, component);
    }

    public static ActivityDailyTaskBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDailyTaskBinding bind(View view, Object component) {
        return (ActivityDailyTaskBinding) bind(component, view, R.layout.activity_daily_task);
    }
}
